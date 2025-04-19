package src.mvc;

import java.util.ArrayList;
import java.util.List;

import src.*;
import src.builder.ComputadoraDirector;
import src.builder.ComputadoraPersonalizadaBuilder;
import src.factory.*;
import src.decorator.*;
import src.Ticket;

/**
 * Controlador del patrón MVC.  Orquesta la interacción
 * entre la vista de consola y el modelo de dominio.
 */
public class Controller {

    private final Model model;
    private final View  view;

    public Controller(Model m, View v) {
        this.model = m;
        this.view  = v;
    }

    /** Punto de entrada de la UI de consola */
    public void iniciar() {

        Computadora pc;
        int op = view.mostrarMenu();

        if (op == 1) {
            // —— PERSONALIZACIÓN PASO A PASO ——
            ComponenteFactory factory = model.getFactory();
            ComputadoraPersonalizadaBuilder builder = new ComputadoraPersonalizadaBuilder();
            ComputadoraDirector director = new ComputadoraDirector(builder);

            // 1) CPU
            List<CPU> cpus = new ArrayList<>(factory.catalogoCPU());
            CPU cpu = cpus.get(view.seleccionarDeCatalogo("CPU", cpus) - 1);
            builder.agregarCPU(cpu);
            model.setComputadoraActual(builder.obtenerComputadora());
            if (!model.esCompatible()) {
                view.mostrarMensaje("¡Incompatibilidad tras elegir CPU!");
                if (view.confirmarForzarCompat()) view.mostrarMensaje(model.adaptar());
            }

            // 2) GPU
            List<GPU> gpus = new ArrayList<>(factory.catalogoGPU());
            GPU gpu = gpus.get(view.seleccionarDeCatalogo("GPU", gpus) - 1);
            builder.agregarGPU(gpu);
            model.setComputadoraActual(builder.obtenerComputadora());
            if (!model.esCompatible()) {
                view.mostrarMensaje("¡Incompatibilidad tras elegir GPU!");
                if (view.confirmarForzarCompat()) view.mostrarMensaje(model.adaptar());
            }

            // 3) RAM (hasta 4 módulos)
            List<RAM> rams = new ArrayList<>(factory.catalogoRAM());
            int nRam = view.solicitarCantidad("módulos de RAM", 4);
            for (int i = 0; i < nRam; i++) {
                RAM ram = rams.get(view.seleccionarDeCatalogo("RAM", rams) - 1);
                builder.agregarRAM(ram);
            }
            model.setComputadoraActual(builder.obtenerComputadora());

            // 4) Almacenamiento (1–4 discos)
            List<Almacenamiento> discos = new ArrayList<>(factory.catalogoStorage());
            int nDisk = view.solicitarCantidad("discos (HDD/SSD)", 4);
            for (int i = 0; i < nDisk; i++) {
                Almacenamiento d = discos.get(view.seleccionarDeCatalogo("disco", discos) - 1);
                builder.agregarDisco(d);
            }
            model.setComputadoraActual(builder.obtenerComputadora());

            // 5) Fuente de poder
            List<FuenteDePoder> psus = new ArrayList<>(factory.catalogoPSU());
            FuenteDePoder psu = psus.get(view.seleccionarDeCatalogo("Fuente de Poder", psus) - 1);
            builder.agregarFuente(psu);
            model.setComputadoraActual(builder.obtenerComputadora());

            // 6) Motherboard
            List<Motherboard> mbs = new ArrayList<>(factory.catalogoMotherboard());
            Motherboard mb = mbs.get(view.seleccionarDeCatalogo("Motherboard", mbs) - 1);
            builder.agregarMotherboard(mb);
            model.setComputadoraActual(builder.obtenerComputadora());

            // 7) Gabinete
            List<Gabinete> gabinetes = new ArrayList<>(factory.catalogoGabinetes());
            Gabinete gabinete = gabinetes.get(view.seleccionarDeCatalogo("Gabinete", gabinetes) - 1);
            builder.agregarGabinete(gabinete);
            model.setComputadoraActual(builder.obtenerComputadora());

            // PC completamente armada
            pc = builder.obtenerComputadora();

        } else {
            // —— PRE‑ARMADA CLÁSICA ——
            String mod = view.solicitarModeloPrearmada();
            pc = model.crearComputadoraPrearmada(mod);
        }

        // —— Compatibilidad final ——
        if (!model.esCompatible()) {
            view.mostrarMensaje("¡Incompatibilidad detectada!");
            if (view.confirmarForzarCompat()) {
                String notas = model.adaptar();
                view.mostrarMensaje(notas);
            } else {
                view.mostrarMensaje("Compra cancelada.");
                return;
            }
        }

        // —— Software adicional (Decorators) ——
        while (view.confirmarAgregarSoftware()) {
            switch (view.seleccionarSoftware()) {
                case 1 -> pc = new WindowsDecorator(pc);
                case 2 -> pc = new OfficeDecorator(pc);
                case 3 -> pc = new PhotoshopDecorator(pc);
                case 4 -> pc = new WSLDecorator(pc);
                case 0 -> { /* salir */ }
                default -> view.mostrarMensaje("Opción inválida.");
            }
            view.mostrarPcParcial(pc);
        }

        // —— Ticket final ——
        Ticket ticket = model.generarTicket("");
        view.mostrarTicket(ticket);
    }
}
