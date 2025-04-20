package src.mvc;

import java.util.ArrayList;
import java.util.List;

import src.Computadora;
import src.Ticket;
import src.builder.ComputadoraDirector;
import src.builder.ComputadoraPersonalizadaBuilder;
import src.builder.ComputadoraPrearmadaBuilder;
import src.compatibilidad.CompatibilidadManager;
import src.factory.ComponenteFactory;
import src.decorator.*;

/**
 * Controlador del patrón MVC. Orquesta la interacción
 * entre la vista de consola y el modelo de dominio.
 */
public class Controller {

    private final Model model;
    private final View  view;
    private final CompatibilidadManager compatManager = new CompatibilidadManager();

    public Controller(Model m, View v) {
        this.model = m;
        this.view  = v;
    }

    /** Punto de entrada de la UI de consola */
    public void iniciar() {
        Computadora pc;
        int op = view.mostrarMenu();
        ComponenteFactory factory = model.getFactory();

        if (op == 1) {
            // —— PERSONALIZACIÓN PASO A PASO ——
            ComputadoraPersonalizadaBuilder builder = new ComputadoraPersonalizadaBuilder();
            ComputadoraDirector director = new ComputadoraDirector(builder);

            // 1) CPU
            List<src.factory.CPU> cpus = new ArrayList<>(factory.catalogoCPU());
            src.factory.CPU cpu = cpus.get(view.seleccionarDeCatalogo("CPU", cpus) - 1);
            builder.agregarCPU(cpu);
            pc = finalizeStep(builder);

            // 2) GPU
            List<src.factory.GPU> gpus = new ArrayList<>(factory.catalogoGPU());
            src.factory.GPU gpu = gpus.get(view.seleccionarDeCatalogo("GPU", gpus) - 1);
            builder.agregarGPU(gpu);
            pc = finalizeStep(builder);

            // 3) RAM (hasta 4 módulos)
            List<src.factory.RAM> rams = new ArrayList<>(factory.catalogoRAM());
            int nRam = view.solicitarCantidad("módulos de RAM", 4);
            for (int i = 0; i < nRam; i++) {
                src.factory.RAM ram = rams.get(view.seleccionarDeCatalogo("RAM", rams) - 1);
                builder.agregarRAM(ram);
            }
            pc = finalizeStep(builder);

            // 4) Almacenamiento (1–4 discos)
            List<src.factory.Almacenamiento> discos = new ArrayList<>(factory.catalogoStorage());
            int nDisk = view.solicitarCantidad("discos (HDD/SSD)", 4);
            for (int i = 0; i < nDisk; i++) {
                src.factory.Almacenamiento d = discos.get(view.seleccionarDeCatalogo("disco", discos) - 1);
                builder.agregarDisco(d);
            }
            pc = finalizeStep(builder);

            // 5) Fuente de poder
            List<src.factory.FuenteDePoder> psus = new ArrayList<>(factory.catalogoPSU());
            src.factory.FuenteDePoder psu = psus.get(view.seleccionarDeCatalogo("Fuente de Poder", psus) - 1);
            builder.agregarFuente(psu);
            pc = finalizeStep(builder);

            // 6) Motherboard
            List<src.factory.Motherboard> mbs = new ArrayList<>(factory.catalogoMotherboard());
            src.factory.Motherboard mb = mbs.get(view.seleccionarDeCatalogo("Motherboard", mbs) - 1);
            builder.agregarMotherboard(mb);
            pc = finalizeStep(builder);

            // 7) Gabinete
            List<src.factory.Gabinete> gabinetes = new ArrayList<>(factory.catalogoGabinetes());
            src.factory.Gabinete gabinete = gabinetes.get(view.seleccionarDeCatalogo("Gabinete", gabinetes) - 1);
            builder.agregarGabinete(gabinete);
            pc = finalizeStep(builder);

        } else {
            // —— PRE‑ARMADA CLÁSICA ——
            List<String> modelos = List.of("Gamer", "Basica", "Estudio", "Oficina", "Render");
            List<Computadora> presets = new ArrayList<>();
            for (String m : modelos) {
                presets.add(new ComputadoraPrearmadaBuilder(factory, m).obtenerComputadora());
            }

            int elegido = view.seleccionarPrearmada(modelos, presets);
            String modeloSel = modelos.get(elegido - 1);

            pc = model.crearComputadoraPrearmada(modeloSel);
            checarCompatibilidad(pc, "configuración prearmada");
        }

        // —— Software adicional (Decorators) ——
        while (view.confirmarAgregarSoftware()) {
            int opcion = view.seleccionarSoftware();
            try {
                switch (opcion) {
                    case 1 -> pc = new WindowsDecorator(pc);
                    case 2 -> pc = new OfficeDecorator(pc);
                    case 3 -> pc = new PhotoshopDecorator(pc);
                    case 4 -> pc = new WSLDecorator(pc);
                    case 0 -> { break; }
                    default -> { view.mostrarMensaje("Opción inválida."); continue; }
                }
                // Actualizamos el modelo con la PC ya decorada
                model.setComputadoraActual(pc);
                view.mostrarPcParcial(pc);
            } catch (IllegalStateException e) {
                view.mostrarMensaje(e.getMessage());
            }
        }

        // —— Ticket final ——
        // Aseguramos que el modelo tenga la PC completa (con software)
        model.setComputadoraActual(pc);
        Ticket ticket = model.generarTicket("");
        view.mostrarTicket(ticket);
    }

    /**
     * Finaliza cada paso de armado: guarda PC en el modelo y chequea compatibilidad.
     */
    private Computadora finalizeStep(ComputadoraPersonalizadaBuilder builder) {
        Computadora pc = builder.obtenerComputadora();
        model.setComputadoraActual(pc);
        checarCompatibilidad(pc, "");
        return pc;
    }

    /**
     * Revisa compatibilidad según reglas, muestra conflictos,
     * pregunta adaptación y aplica si el usuario acepta.
     */
    private void checarCompatibilidad(Computadora pc, String etapa) {
        List<String> conflictos = compatManager.verificar(pc);
        if (!conflictos.isEmpty()) {
            view.mostrarConflictos(conflictos);
            if (view.confirmarAdaptacion()) {
                String notas = compatManager.adaptar(pc);
                view.mostrarNotasAdaptacion(notas);
            } else {
                view.mostrarMensaje("Proceso cancelado en etapa: " + etapa);
                System.exit(0);
            }
        }
    }
}
