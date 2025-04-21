package src.mvc;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import src.Computadora;
import src.Ticket;
import src.builder.*;
import src.compatibilidad.CompatibilidadManager;
import src.factory.*;
import src.decorator.*;

/**
 * Controlador del patrón MVC, ahora con dos puntos de entrada:
 *  - realizarCompra(): todo el flujo de armado + software + ticket
 *  - mostrarHistorial(): lectura del .txt de tickets
 */
public class Controller {

    private static final String DIR  = "tickets";
    private static final String FILE = "historial.txt";

    private Model model;
    private View  view;
    private final CompatibilidadManager compat = new CompatibilidadManager();

    public Controller(Model m, View v) {
        this.model = m;
        this.view  = v;
        ensureFile();
    }

    /** Flujo completo de nueva compra */
    public void realizarCompra() {
        Computadora pc;
        ComponenteFactory f = model.getFactory();

        // 1) Personalizada vs pre‑armada
        int modo = view.mostrarMenu();
        if (modo == 1) {
            pc = pasoAPaso(f);
        } else {
            pc = prearmada(f);
        }

        // 2) Software adicional
        while (view.confirmarAgregarSoftware()) {
            int sw = view.seleccionarSoftware();
            try {
                switch (sw) {
                    case 1 -> pc = new WindowsDecorator(pc);
                    case 2 -> pc = new OfficeDecorator(pc);
                    case 3 -> pc = new PhotoshopDecorator(pc);
                    case 4 -> pc = new WSLDecorator(pc);
                    case 5 -> pc = new AutocadDecorator(pc);
                    case 0 -> {break;}
                    default -> { view.mostrarMensaje("Opción inválida."); continue; }
                }
                model.setComputadoraActual(pc);
                view.mostrarPcParcial(pc);
            } catch (IllegalStateException ex) {
                view.mostrarMensaje(ex.getMessage());
            }
        }

        // 3) Generar y guardar ticket
        model.setComputadoraActual(pc);
        Ticket t = model.generarTicket("");
        view.mostrarTicket(t);
        appendTicket(t);
    }

    /** Muestra en consola todo el historial de tickets */
    public void mostrarHistorial() {
        Path path = Paths.get(DIR, FILE);
        view.mostrarMensaje("=== Historial de Tickets ===");
        try {
            List<String> lines = Files.readAllLines(path);
            if (lines.isEmpty()) {
                view.mostrarMensaje("No hay tickets en el historial.");
            } else {
                lines.forEach(view::mostrarMensaje);
            }
        } catch (IOException e) {
            view.mostrarMensaje("Error al leer historial: " + e.getMessage());
        }
    }

    // ——— Métodos auxiliares privados ———

    private Computadora pasoAPaso(ComponenteFactory f) {
        ComputadoraPersonalizadaBuilder b = new ComputadoraPersonalizadaBuilder();
        Computadora pc = null;

        // CPU
        List<CPU> cpus = new ArrayList<>(f.catalogoCPU());
        CPU cpu = cpus.get(view.seleccionarDeCatalogo("CPU", cpus) - 1);
        b.agregarCPU(cpu); pc = finalize(b);

        // GPU
        List<GPU> gpus = new ArrayList<>(f.catalogoGPU());
        GPU gpu = gpus.get(view.seleccionarDeCatalogo("GPU", gpus) - 1);
        b.agregarGPU(gpu); pc = finalize(b);

        // RAM
        List<RAM> rams = new ArrayList<>(f.catalogoRAM());
        int nRam = view.solicitarCantidad("módulos de RAM", 4);
        for (int i = 0; i < nRam; i++)
            b.agregarRAM(rams.get(view.seleccionarDeCatalogo("RAM", rams) - 1));
        pc = finalize(b);

        // Discos
        List<Almacenamiento> ds = new ArrayList<>(f.catalogoStorage());
        int nD = view.solicitarCantidad("discos (HDD/SSD)", 4);
        for (int i = 0; i < nD; i++)
            b.agregarDisco(ds.get(view.seleccionarDeCatalogo("disco", ds) - 1));
        pc = finalize(b);

        // PSU
        List<FuenteDePoder> psus = new ArrayList<>(f.catalogoPSU());
        b.agregarFuente(psus.get(view.seleccionarDeCatalogo("Fuente de Poder", psus) - 1));
        pc = finalize(b);

        // Motherboard
        List<Motherboard> mbs = new ArrayList<>(f.catalogoMotherboard());
        b.agregarMotherboard(mbs.get(view.seleccionarDeCatalogo("Motherboard", mbs) - 1));
        pc = finalize(b);

        // Gabinete
        List<Gabinete> gab = new ArrayList<>(f.catalogoGabinetes());
        b.agregarGabinete(gab.get(view.seleccionarDeCatalogo("Gabinete", gab) - 1));
        pc = finalize(b);

        return pc;
    }

    private Computadora prearmada(ComponenteFactory f) {
        List<String> modelos = List.of("Gamer","Basica","Estudio","Oficina","Render");
        List<Computadora> presets = new ArrayList<>();
        for (String m : modelos) {
            presets.add(new ComputadoraPrearmadaBuilder(f,m).obtenerComputadora());
        }
        int idx = view.seleccionarPrearmada(modelos, presets);
        Computadora pc = model.crearComputadoraPrearmada(modelos.get(idx-1));
        checkCompat(pc,"pre‑armada");
        return pc;
    }

    private Computadora finalize(ComputadoraPersonalizadaBuilder b) {
        Computadora pc = b.obtenerComputadora();
        model.setComputadoraActual(pc);
        checkCompat(pc,"paso");
        return pc;
    }

    private void checkCompat(Computadora pc, String etapa) {
        List<String> c = compat.verificar(pc);
        if (!c.isEmpty()) {
            view.mostrarConflictos(c);
            if (view.confirmarAdaptacion()) {
                String notas = compat.adaptar(pc);
                view.mostrarNotasAdaptacion(notas);
            } else {
                view.mostrarMensaje("Cancelado en etapa: "+etapa);
                System.exit(0);
            }
        }
    }

    private void ensureFile() {
        try {
            Path dir  = Paths.get(DIR);
            Path file = dir.resolve(FILE);
            if (!Files.exists(dir))  Files.createDirectories(dir);
            if (!Files.exists(file)) Files.createFile(file);
        } catch (IOException e) {
            view.mostrarMensaje("No se pudo inicializar historial: "+e.getMessage());
        }
    }

    private void appendTicket(Ticket t) {
        try {
            Path path = Paths.get(DIR, FILE);
            Files.writeString(path, t.imprimir(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            view.mostrarMensaje("Error al guardar ticket: "+e.getMessage());
        }
    }
}
