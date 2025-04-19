package src.mvc;

import java.util.List;
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

    /* ---------------- Selección genérica de catálogo ---------------- */
    /**
     * Muestra un catálogo numerado y lee la opción elegida.
     *
     * @param <T>       Tipo de los elementos del catálogo (CPU, GPU, RAM, etc.)
     * @param nombre    Nombre del tipo de elementos (p.ej. "CPU", "GPU", "RAM", ...)
     * @param opciones  Lista de opciones a mostrar
     * @return índice (1–opciones.size()) de la opción elegida
     */
    public <T> int seleccionarDeCatalogo(String nombre, List<T> opciones) {
        System.out.println("Seleccione " + nombre + ":");
        for (int i = 0; i < opciones.size(); i++) {
            System.out.printf("  %2d) %s%n", i + 1, opciones.get(i).toString());
        }
        int idx;
        do {
            System.out.printf("Opción (1–%d): ", opciones.size());
            idx = sc.nextInt();
        } while (idx < 1 || idx > opciones.size());
        return idx;
    }

    /**
     * Pide al usuario cuántas unidades de un recurso desea (p.ej. módulos de RAM o discos),
     * con un mínimo de 1 y un máximo especificado.
     *
     * @param nombre  Nombre de lo que se está preguntando ("módulos de RAM", "discos", ...)
     * @param max     Cantidad máxima permitida
     * @return número entre 1 y max
     */
    public int solicitarCantidad(String nombre, int max) {
        System.out.printf("¿Cuántos %s desea? (1–%d): ", nombre, max);
        int n = sc.nextInt();
        if (n < 1) n = 1;
        if (n > max) n = max;
        return n;
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
        System.out.printf("Subtotal: $%.2f%n", pc.calcularPrecioTotal());
    }
}
