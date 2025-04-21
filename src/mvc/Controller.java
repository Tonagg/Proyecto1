package src.mvc;

import src.Sucursal;
import src.Computadora;
import src.Ticket;
import src.builder.ComputadoraPersonalizadaBuilder;
import src.builder.ComputadoraPrearmadaBuilder;
import src.compatibilidad.CompatibilidadManager;
import src.factory.ComponenteFactory;
import src.factory.CPU;
import src.factory.GPU;
import src.factory.RAM;
import src.factory.Almacenamiento;
import src.factory.FuenteDePoder;
import src.factory.Motherboard;
import src.factory.Gabinete;
import src.decorator.AutocadDecorator;
import src.decorator.OfficeDecorator;
import src.decorator.PhotoshopDecorator;
import src.decorator.WindowsDecorator;
import src.decorator.WSLDecorator;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador del patrón MVC para el flujo de compra y registro de historial.
 * Gestiona la interacción con el usuario mediante {@link View} y la lógica de dominio mediante {@link Model}.
 * Soporta:
 * <ul>
 *   <li>Realizar una compra paso a paso o pre‑armada con selección de software adicional.</li>
 *   <li>Verificación y adaptación de compatibilidad de componentes.</li>
 *   <li>Generación y almacenamiento de tickets de compra por sucursal.</li>
 * </ul>
 */
public class Controller {

    private static final String DIR  = "tickets";
    private static final String FILE = "historial.txt";

    private final Model model;
    private final View  view;
    private final Sucursal sucursal;
    private final CompatibilidadManager compat = new CompatibilidadManager();

    /**
     * Construye un Controller con el modelo, la vista y la sucursal especificados.
     * Asegura que exista el archivo de historial en disco.
     *
     * @param m         instancia de {@link Model} que contiene la lógica de negocio
     * @param v         instancia de {@link View} para interacción con el usuario
     * @param sucursal  sucursal de origen de la compra
     */
    public Controller(Model m, View v, Sucursal sucursal) {
        this.model     = m;
        this.view      = v;
        this.sucursal  = sucursal;
        ensureFile();
    }

    /**
     * Ejecuta el flujo completo de una nueva compra:
     * <ol>
     *   <li>Seleccionar configuración personalizada o pre‑armada.</li>
     *   <li>Opcionalmente agregar software mediante decoradores.</li>
     *   <li>Generar el ticket final y guardarlo en el historial de la sucursal.</li>
     * </ol>
     */
    public void realizarCompra() {
        view.mostrarMensaje("Sucursal: " + sucursal);
        Computadora pc;
        ComponenteFactory f = model.getFactory();

        int modo = view.mostrarMenu();
        if (modo == 1) {
            pc = pasoAPaso(f);
        } else {
            pc = prearmada(f);
        }

        while (view.confirmarAgregarSoftware()) {
            int sw = view.seleccionarSoftware();
            try {
                switch (sw) {
                    case 1 -> pc = new WindowsDecorator(pc);
                    case 2 -> pc = new OfficeDecorator(pc);
                    case 3 -> pc = new PhotoshopDecorator(pc);
                    case 4 -> pc = new WSLDecorator(pc);
                    case 5 -> pc = new AutocadDecorator(pc);
                    case 0 -> { break; }
                    default -> { view.mostrarMensaje("Opción inválida."); continue; }
                }
                model.setComputadoraActual(pc);
                view.mostrarPcParcial(pc);
            } catch (IllegalStateException ex) {
                view.mostrarMensaje(ex.getMessage());
            }
        }

        model.setComputadoraActual(pc);
        Ticket t = model.generarTicket("");
        view.mostrarTicket(t);
        appendTicket(t);
    }

    /**
     * Muestra en consola el historial de tickets de la sucursal actual.
     */
    public void mostrarHistorial() {
        Path dir  = Paths.get(DIR, sucursal.name());
        Path file = dir.resolve(FILE);
        view.mostrarMensaje("=== Historial de Tickets (" + sucursal + ") ===");
        try {
            List<String> lines = Files.readAllLines(file);
            if (lines.isEmpty()) {
                view.mostrarMensaje("No hay tickets en el historial de " + sucursal + ".");
            } else {
                lines.forEach(view::mostrarMensaje);
            }
        } catch (IOException e) {
            view.mostrarMensaje("Error al leer historial: " + e.getMessage());
        }
    }

    /**
     * Constructor de flujo personalizado, seleccionando cada componente paso a paso.
     *
     * @param f fábrica de componentes para construir la PC personalizada
     * @return instancia de {@link Computadora} ensamblada
     */
    private Computadora pasoAPaso(ComponenteFactory f) {
        ComputadoraPersonalizadaBuilder b = new ComputadoraPersonalizadaBuilder();
        Computadora pc = null;
        List<CPU> cpus = new ArrayList<>(f.catalogoCPU());
        CPU cpu = cpus.get(view.seleccionarDeCatalogo("CPU", cpus) - 1);
        b.agregarCPU(cpu); pc = finalize(b);
        List<GPU> gpus = new ArrayList<>(f.catalogoGPU());
        GPU gpu = gpus.get(view.seleccionarDeCatalogo("GPU", gpus) - 1);
        b.agregarGPU(gpu); pc = finalize(b);
        List<RAM> rams = new ArrayList<>(f.catalogoRAM());
        int nRam = view.solicitarCantidad("módulos de RAM", 4);
        for (int i = 0; i < nRam; i++)
            b.agregarRAM(rams.get(view.seleccionarDeCatalogo("RAM", rams) - 1));
        pc = finalize(b);
        List<Almacenamiento> ds = new ArrayList<>(f.catalogoStorage());
        int nD = view.solicitarCantidad("discos (HDD/SSD)", 4);
        for (int i = 0; i < nD; i++)
            b.agregarDisco(ds.get(view.seleccionarDeCatalogo("disco", ds) - 1));
        pc = finalize(b);
        List<FuenteDePoder> psus = new ArrayList<>(f.catalogoPSU());
        b.agregarFuente(psus.get(view.seleccionarDeCatalogo("Fuente de Poder", psus) - 1));
        pc = finalize(b);
        List<Motherboard> mbs = new ArrayList<>(f.catalogoMotherboard());
        b.agregarMotherboard(mbs.get(view.seleccionarDeCatalogo("Motherboard", mbs) - 1));
        pc = finalize(b);
        List<Gabinete> gab = new ArrayList<>(f.catalogoGabinetes());
        b.agregarGabinete(gab.get(view.seleccionarDeCatalogo("Gabinete", gab) - 1));
        pc = finalize(b);
        return pc;
    }

    /**
     * Constructor de flujo pre‑armado, seleccionando un preset del catálogo.
     *
     * @param f fábrica de componentes para construir la PC pre‑armada
     * @return instancia de {@link Computadora} ensamblada
     */
    private Computadora prearmada(ComponenteFactory f) {
        List<String> modelos = List.of("Gamer","Basica","Estudio","Oficina","Render");
        List<Computadora> presets = new ArrayList<>();
        for (String m : modelos) {
            presets.add(new ComputadoraPrearmadaBuilder(f, m).obtenerComputadora());
        }
        int idx = view.seleccionarPrearmada(modelos, presets);
        Computadora pc = model.crearComputadoraPrearmada(modelos.get(idx - 1));
        checkCompat(pc, "pre‑armada");
        return pc;
    }

    /**
     * Finaliza cada paso de ensamblaje personalizado y verifica compatibilidad.
     *
     * @param b builder personalizado que gestiona el ensamblaje
     * @return computadora actualizada tras verificación
     */
    private Computadora finalize(ComputadoraPersonalizadaBuilder b) {
        Computadora pc = b.obtenerComputadora();
        model.setComputadoraActual(pc);
        checkCompat(pc, "paso");
        return pc;
    }

    /**
     * Verifica conflictos de compatibilidad y aplica adaptaciones si el usuario lo autoriza.
     *
     * @param pc     computadora a verificar
     * @param etapa  descripción de la etapa para mensajes de error
     */
    private void checkCompat(Computadora pc, String etapa) {
        List<String> c = compat.verificar(pc);
        if (!c.isEmpty()) {
            view.mostrarConflictos(c);
            if (view.confirmarAdaptacion()) {
                String notas = compat.adaptar(pc);
                view.mostrarNotasAdaptacion(notas);
            } else {
                view.mostrarMensaje("Cancelado en etapa: " + etapa);
                System.exit(0);
            }
        }
    }

    /**
     * Asegura la existencia del directorio y archivo de historial para la sucursal.
     */
    private void ensureFile() {
        try {
            Path dir  = Paths.get(DIR, sucursal.name());
            Path file = dir.resolve(FILE);
            if (!Files.exists(dir))   Files.createDirectories(dir);
            if (!Files.exists(file))  Files.createFile(file);
        } catch (IOException e) {
            view.mostrarMensaje("No se pudo inicializar historial: " + e.getMessage());
        }
    }

    /**
     * Agrega la representación del ticket al archivo de historial.
     *
     * @param t ticket generado para guardar
     */
    private void appendTicket(Ticket t) {
        try {
            Path path = Paths.get(DIR, sucursal.name(), FILE);
            Files.writeString(path, t.imprimir(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            view.mostrarMensaje("Error al guardar ticket: " + e.getMessage());
        }
    }
}
