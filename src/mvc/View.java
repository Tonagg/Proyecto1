package src.mvc;

import java.util.List;
import java.util.Scanner;

import src.Computadora;
import src.Ticket;

/**
 * Vista de consola que gestiona todas las interacciones con el usuario.
 * Proporciona menús, preguntas y despliegue de información para el flujo
 * de compra y consulta de historial de tickets.
 */
public class View {

    /**
     * Escáner para lectura de entradas por consola.
     */
    private final Scanner sc = new Scanner(System.in);

    // ───────────────────────── Menú principal ─────────────────────────

    /**
     * Muestra el menú inicial y obtiene la opción elegida por el usuario.
     *
     * @return 1 para computadora personalizada, 2 para computadora pre‑armada
     */
    public int mostrarMenu() {
        while (true) {
            System.out.println("=== MONOSCHINOS MX ===");
            System.out.println("1) Computadora personalizada");
            System.out.println("2) Computadora pre‑armada");
            System.out.print("Elige opción: ");

            try {
                int op = Integer.parseInt(sc.nextLine().trim());
                if (op == 1 || op == 2) {
                    return op;
                }
            } catch (NumberFormatException ignored) {
                // repite el bucle en caso de error
            }
            System.out.println("Opción inválida. Intenta de nuevo.\n");
        }
    }

    /**
     * Solicita al usuario el nombre del modelo pre‑armado.
     *
     * @return nombre del preset ingresado por consola
     */
    public String solicitarModeloPrearmada() {
        System.out.print("Modelo pre‑armado (Gamer/Basica/Estudio): ");
        return sc.nextLine().trim();
    }

    // ─────────────────── Compatibilidad / Adaptadores ──────────────────

    /**
     * Muestra los conflictos de compatibilidad detectados.
     *
     * @param conflictos lista de mensajes de conflicto
     */
    public void mostrarConflictos(List<String> conflictos) {
        System.out.println("¡Se detectaron conflictos de compatibilidad!");
        conflictos.forEach(c -> System.out.println("  – " + c));
        System.out.println();
    }

    /**
     * Pregunta al usuario si desea aplicar las adaptaciones automáticas.
     *
     * @return true si el usuario acepta ("s"), false si rechaza ("n")
     */
    public boolean confirmarAdaptacion() {
        while (true) {
            System.out.print("¿Aplicar adaptadores para resolverlos? (s/n): ");
            String in = sc.nextLine().trim().toLowerCase();
            if (in.equals("s")) {
                return true;
            }
            if (in.equals("n")) {
                return false;
            }
            System.out.println("Entrada inválida. Escribe 's' o 'n'.\n");
        }
    }

    /**
     * Muestra las notas de las adaptaciones aplicadas tras la verificación.
     *
     * @param notas texto con las adaptaciones realizadas
     */
    public void mostrarNotasAdaptacion(String notas) {
        if (notas != null && !notas.isBlank()) {
            System.out.println("Adaptaciones aplicadas:");
            System.out.println(notas);
        }
    }

    // ─────────────────── Software opcional (Decorators) ───────────────

    /**
     * Pregunta si el usuario desea instalar software adicional.
     *
     * @return true si desea agregar más software, false en caso contrario
     */
    public boolean confirmarAgregarSoftware() {
        while (true) {
            System.out.print("¿Agregar software extra? (s/n): ");
            String in = sc.nextLine().trim().toLowerCase();
            if (in.equals("s")) {
                return true;
            }
            if (in.equals("n")) {
                return false;
            }
            System.out.println("Entrada inválida. Escribe 's' o 'n'.\n");
        }
    }

    /**
     * Muestra el menú de software disponible y retorna la opción elegida.
     *
     * @return índice de opción de 0 (ninguno) a 5 (Autocad)
     */
    public int seleccionarSoftware() {
        while (true) {
            System.out.println("Elija software:");
            System.out.println(" 1) Windows 10/11      – $1,000.00");
            System.out.println(" 2) Office 365         – $  500.00");
            System.out.println(" 3) Adobe Photoshop    – $1,200.00");
            System.out.println(" 4) WSL Terminal (modo hacker) – $1,000.00");
            System.out.println(" 5) Autocad – $1,500.00");
            System.out.println(" 0) Ninguno");
            System.out.print("Opción: ");
            try {
                int op = Integer.parseInt(sc.nextLine().trim());
                if (op >= 0 && op <= 5) {
                    return op;
                }
            } catch (NumberFormatException ignored) {
                // sigue iterando
            }
            System.out.println("Entrada inválida. Intenta nuevamente.\n");
        }
    }

    // ───────────── Selección genérica de un catálogo ──────────────────

    /**
     * Muestra un listado numerado de opciones y retorna la elección.
     *
     * @param <T>      tipo de elementos en el catálogo
     * @param nombre   nombre del catálogo que se muestra
     * @param opciones lista de elementos a mostrar
     * @return índice (1–n) elegido por el usuario
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
                if (idx >= 1 && idx <= opciones.size()) {
                    return idx;
                }
            } catch (NumberFormatException ignored) {
                // repite el bucle
            }
            System.out.println("Entrada inválida. Intenta nuevamente.\n");
        }
    }

    /**
     * Muestra los modelos pre‑armados con su descripción y precio, y retorna la selección.
     *
     * @param nombres lista de nombres de presets
     * @param pcs     lista de instancias de computadoras pre‑armadas
     * @return índice (1–n) del modelo elegido
     */
    public int seleccionarPrearmada(List<String> nombres, List<Computadora> pcs) {
        System.out.println("Modelos pre‑armados disponibles:");
        for (int i = 0; i < nombres.size(); i++) {
            System.out.printf(" %2d) %s%n", i + 1, nombres.get(i));
            System.out.println(pcs.get(i).getDescripcion().indent(4));
            System.out.printf("     Precio: $%,.2f%n%n", pcs.get(i).calcularPrecioTotal());
        }
        while (true) {
            System.out.printf("Elige modelo (1–%d): ", nombres.size());
            try {
                int idx = Integer.parseInt(sc.nextLine().trim());
                if (idx >= 1 && idx <= nombres.size()) {
                    return idx;
                }
            } catch (NumberFormatException ignored) {
                // repite el bucle
            }
            System.out.println("Entrada inválida. Intenta nuevamente.\n");
        }
    }

    // ─────────── Solicitud de cantidades (RAM, discos, …) ────────────

    /**
     * Solicita al usuario la cantidad deseada de elementos hasta un máximo.
     *
     * @param nombre descripción de los elementos (e.g., "módulos de RAM")
     * @param max    número máximo permitido
     * @return cantidad válida (1–max) ingresada por el usuario
     */
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

    // ───────────────── Salida variada ───────────────────────

    /**
     * Muestra un mensaje genérico en consola.
     *
     * @param msg texto a imprimir
     */
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    /**
     * Muestra la configuración actual y el subtotal de precio.
     *
     * @param pc computadora cuya configuración se muestra
     */
    public void mostrarPcParcial(Computadora pc) {
        System.out.println("--- Configuración actual ---");
        System.out.println(pc.getDescripcion());
        System.out.printf("Subtotal: $%,.2f%n", pc.calcularPrecioTotal());
    }

    /**
     * Muestra el ticket final en consola.
     *
     * @param t instancia de {@link Ticket} a imprimir
     */
    public void mostrarTicket(Ticket t) {
        System.out.println(t.imprimir());
    }
}
