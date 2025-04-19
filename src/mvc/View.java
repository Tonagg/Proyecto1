package src.mvc;

import java.util.Scanner;
import src.Ticket;
import src.Computadora;

/**
 * Vista muy sencilla basada en consola.
 * Encapsula todo el I/O para que el modelo y el controlador
 * permanezcan independientes de la UI concreta.
 */
public class View {

    private final Scanner sc = new Scanner(System.in);

    /* ---------------- Menú principal ---------------- */
    public int mostrarMenu() {
        System.out.println("=== MONOSCHINOS MX ===");
        System.out.println("1) Computadora personalizada");
        System.out.println("2) Computadora prearmada");
        System.out.print("Elige opción: ");
        return sc.nextInt();
    }

    public String solicitarModeloPrearmada() {
        System.out.print("Modelo prearmado (Gamer/Basica/Estudio): ");
        return sc.next();
    }

    /* ---------------- Compatibilidad ---------------- */
    public boolean confirmarForzarCompat() {
        System.out.print("¿Desea forzar compatibilidad con adaptadores? (s/n): ");
        return sc.next().equalsIgnoreCase("s");
    }

    /* ---------------- Software / Decorators --------- */
    public boolean confirmarAgregarSoftware() {
        System.out.print("¿Agregar software extra? (s/n): ");
        return sc.next().equalsIgnoreCase("s");
    }

    public int seleccionarSoftware() {
        System.out.println("""
            Elija software:
             1) Windows 10/11
             2) Office 365
             3) Adobe Photoshop
             4) WSL Terminal
             0) Ninguno
            """);
        System.out.print("Opción: ");
        return sc.nextInt();
    }

    /* ---------------- Mensajes / Salida -------------- */

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarTicket(Ticket t) {
        System.out.println(t.imprimir());
    }

    public void mostrarPcParcial(Computadora pc) {
        System.out.println("--- Config actual ---");
        System.out.println(pc.getDescripcion());
        System.out.printf("Subtotal: $%.2f\n", pc.calcularPrecioTotal());
    }
}