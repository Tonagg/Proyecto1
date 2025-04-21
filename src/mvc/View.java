package src.mvc;

import java.util.List;
import java.util.Scanner;

import src.Computadora;
import src.Ticket;

/**
 * Vista de consola simplificada.
 * <p>
 * Se ha re‑escrito para trabajar con la nueva lógica de compatibilidad basada
 * en {@link src.compatibilidad.CompatibilidadManager CompatibilidadManager}.
 * Todos los textos de la antigua "forzar compatibilidad" han sido sustituidos
 * por mensajes genéricos de "adaptar".
 * </p>
 */
public class View {

    private final Scanner sc = new Scanner(System.in);

    /* ─────────────────────────  Menú principal  ───────────────────────── */

    /**
     * Muestra el menú inicial y devuelve la opción elegida (1 ó 2).
     */
    public int mostrarMenu() {
        while (true) {
            System.out.println("=== MONOSCHINOS MX ===");
            System.out.println("1) Computadora personalizada");
            System.out.println("2) Computadora pre‑armada");
            System.out.print("Elige opción: ");

            try {
                int op = Integer.parseInt(sc.nextLine().trim());
                if (op == 1 || op == 2) return op;
            } catch (NumberFormatException ignored) { /* loop */ }
            System.out.println("Opción inválida. Intenta de nuevo.\n");
        }
    }

    /** Pide el nombre de la configuración pre‑armada. */
    public String solicitarModeloPrearmada() {
        System.out.print("Modelo pre‑armado (Gamer/Basica/Estudio): ");
        return sc.nextLine().trim();
    }

    /* ───────────────────  Compatibilidad / Adaptadores  ────────────────── */

    /**
     * Muestra los conflictos encontrados.
     */
    public void mostrarConflictos(List<String> conflictos) {
        System.out.println("¡Se detectaron conflictos de compatibilidad!");
        conflictos.forEach(c -> System.out.println("  – " + c));
        System.out.println();
    }

    /**
     * Pregunta al usuario si desea aplicar adaptaciones automáticas.
     */
    public boolean confirmarAdaptacion() {
        while (true) {
            System.out.print("¿Aplicar adaptadores para resolverlos? (s/n): ");
            String in = sc.nextLine().trim().toLowerCase();
            if (in.equals("s")) return true;
            if (in.equals("n")) return false;
            System.out.println("Entrada inválida. Escribe 's' o 'n'.\n");
        }
    }

    /** Muestra las notas generadas tras aplicar adaptaciones. */
    public void mostrarNotasAdaptacion(String notas) {
        if (notas != null && !notas.isBlank()) {
            System.out.println("Adaptaciones aplicadas:");
            System.out.println(notas);
        }
    }

    /* ───────────────────  Software opcional (Decorators)  ─────────────── */

    /** Pregunta si se desea instalar software adicional. */
    public boolean confirmarAgregarSoftware() {
        while (true) {
            System.out.print("¿Agregar software extra? (s/n): ");
            String in = sc.nextLine().trim().toLowerCase();
            if (in.equals("s")) return true;
            if (in.equals("n")) return false;
            System.out.println("Entrada inválida. Escribe 's' o 'n'.\n");
        }
    }

    /** Devuelve la opción de software elegida (0–4). */
    public int seleccionarSoftware() {
        while (true) {
            System.out.println("""
                Elija software:
                 1) Windows 10/11      – $1,000.00
                 2) Office 365         – $  500.00
                 3) Adobe Photoshop    – $1,200.00
                 4) WSL Terminal (modo hacker) – $1,000.00
                 5) Autocad – $1,500.00
                 0) Ninguno
                """);
            System.out.print("Opción: ");
            try {
                int op = Integer.parseInt(sc.nextLine().trim());
                if (op >= 0 && op <= 5) return op;
            } catch (NumberFormatException ignored) {}
            System.out.println("Entrada inválida. Intenta nuevamente.\n");
        }
    }

    /* ─────────────  Selección genérica de un catálogo  ────────────────── */

    /**
     * Muestra un listado numerado de <T> y devuelve el índice (1‑n) elegido.
     */
    public <T> int seleccionarDeCatalogo(String nombre, List<T> opciones) {
        System.out.println("Seleccione " + nombre + ":");
        for (int i = 0; i < opciones.size(); i++) {
            System.out.printf("  %2d) %s%n", i + 1, opciones.get(i));
        }
        while (true) {
            System.out.printf("Opción (1–%d): ", opciones.size());
            try {
                int idx = Integer.parseInt(sc.nextLine().trim());
                if (idx >= 1 && idx <= opciones.size()) return idx;
            } catch (NumberFormatException ignored) {}
            System.out.println("Entrada inválida. Intenta nuevamente.\n");
        }
    }

        /**
     * Muestra todos los modelos pre‑armados con su descripción y precio,
     * y devuelve la opción elegida (1–n).
     */
    public int seleccionarPrearmada(List<String> nombres, List<Computadora> pcs) {
        System.out.println("Modelos pre‑armados disponibles:");
        for (int i = 0; i < nombres.size(); i++) {
            System.out.printf(" %2d) %s%n", i+1, nombres.get(i));
            System.out.println(pcs.get(i).getDescripcion().indent(4));
            System.out.printf("     Precio: $%,.2f%n%n", pcs.get(i).calcularPrecioTotal());
        }
        while (true) {
            System.out.printf("Elige modelo (1–%d): ", nombres.size());
            try {
                int idx = Integer.parseInt(sc.nextLine().trim());
                if (idx >= 1 && idx <= nombres.size()) return idx;
            } catch (NumberFormatException ignored) {}
            System.out.println("Entrada inválida. Intenta nuevamente.\n");
        }
    }


    /* ────────────  Solicitud de cantidades (RAM, discos, …)  ──────────── */

    public int solicitarCantidad(String nombre, int max) {
        while (true) {
            System.out.printf("¿Cuántos %s desea? (1–%d): ", nombre, max);
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n < 1) n = 1;
                if (n > max) n = max;
                return n;
            } catch (NumberFormatException ignored) {
                System.out.println("Entrada inválida. Intenta nuevamente.\n");
            }
        }
    }

    /* ───────────────────────────  Salida variada  ─────────────────────── */

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarPcParcial(Computadora pc) {
        System.out.println("--- Configuración actual ---");
        System.out.println(pc.getDescripcion());
        System.out.printf("Subtotal: $%,.2f%n", pc.calcularPrecioTotal());
    }

    public void mostrarTicket(Ticket t) {
        System.out.println(t.imprimir());
    }
}
