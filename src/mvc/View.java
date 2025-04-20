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
        while (true) {
            System.out.println("=== MONOSCHINOS MX ===");
            System.out.println("1) Computadora personalizada");
            System.out.println("2) Computadora prearmada");
            System.out.print("Elige opción: ");
            try {
                int opcion = Integer.parseInt(sc.nextLine().trim());
                if (opcion == 1 || opcion == 2)
                    return opcion;
                else
                    System.out.println("Opción no válida. Intenta de nuevo.\n");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Escribe solo un número (1 o 2).\n");
            }
        }
    }

    public String solicitarModeloPrearmada() {
        System.out.print("Modelo prearmado (Gamer/Basica/Estudio): ");
        return sc.nextLine().trim();
    }

    /* ---------------- Compatibilidad ---------------- */
    public void mostrarConflictos(List<String> conflictos) {
        System.out.println("¡Se detectaron conflictos de compatibilidad!");
        conflictos.forEach(c -> System.out.println(" - " + c));
    }

    public boolean confirmarAdaptacion() {
        while (true) {
            System.out.print("¿Desea aplicar adaptadores para resolverlos? (s/n): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("s")) return true;
            if (input.equals("n")) return false;
            System.out.println("Entrada inválida. Escribe 's' para sí o 'n' para no.");
        }
    }

    public void mostrarNotasAdaptacion(String notas) {
        if (notas != null && !notas.isBlank()) {
            System.out.println("Adaptaciones realizadas:");
            System.out.println(notas);
        }
    }

    /* ---------------- Software / Decorators --------- */
    public boolean confirmarAgregarSoftware() {
        while (true) {
            System.out.print("¿Agregar software extra? (s/n): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("s")) return true;
            if (input.equals("n")) return false;
            System.out.println("Entrada inválida. Escribe 's' para sí o 'n' para no.");
        }
    }

    public int seleccionarSoftware() {
        while (true) {
            System.out.println("""
                Elija software:
                 1) Windows 10/11
                 2) Office 365
                 3) Adobe Photoshop
                 4) WSL Terminal
                 0) Ninguno
                """);
            System.out.print("Opción: ");
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intenta con un número del 0 al 4.\n");
            }
        }
    }

    /* ---------------- Selección genérica de catálogo ---------------- */
    public <T> int seleccionarDeCatalogo(String nombre, List<T> opciones) {
        System.out.println("Seleccione " + nombre + ":");
        for (int i = 0; i < opciones.size(); i++) {
            System.out.printf("  %2d) %s%n", i + 1, opciones.get(i).toString());
        }

        while (true) {
            System.out.printf("Opción (1–%d): ", opciones.size());
            try {
                int idx = Integer.parseInt(sc.nextLine().trim());
                if (idx >= 1 && idx <= opciones.size()) return idx;
                System.out.println("Opción fuera de rango. Intenta de nuevo.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intenta con un número.");
            }
        }
    }

    /* ---------------- Cantidad de recursos ---------------- */
    public int solicitarCantidad(String nombre, int max) {
        while (true) {
            System.out.printf("¿Cuántos %s desea? (1–%d): ", nombre, max);
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n < 1) return 1;
                if (n > max) return max;
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intenta con un número.");
            }
        }
    }

    /* ---------------- Mensajes / Salida -------------- */
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarPcParcial(Computadora pc) {
        System.out.println("--- Config actual ---");
        System.out.println(pc.getDescripcion());
        System.out.printf("Subtotal: $%.2f%n", pc.calcularPrecioTotal());
    }

    public void mostrarTicket(Ticket t) {
        System.out.println(t.imprimir());
    }
}
