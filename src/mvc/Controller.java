// src/mvc/Controller.java
package src.mvc;

import src.*;
import src.decorator.*;
import src.Ticket;

public class Controller {

    private final Model model;
    private final View  view;

    public Controller(Model m, View v) {
        this.model = m;
        this.view  = v;
    }

    public void iniciar() {

        /* -------- Selección de tipo de equipo -------- */
        Computadora pc;
        int op = view.mostrarMenu();
        if (op == 1) pc = model.crearComputadoraPersonalizada();
        else {
            String mod = view.solicitarModeloPrearmada();
            pc = model.crearComputadoraPrearmada(mod);
        }

        /* -------- Compatibilidad -------- */
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

        /* -------- Agregar software (Decorators) ------ */
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

        /* -------- Ticket final -------- */
        Ticket ticket = model.generarTicket(""); // aquí puedes pasar notas extra si guardaste
        view.mostrarTicket(ticket);
    }
}
