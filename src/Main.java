/* ────── src/Main.java ────── */
package src;

import java.util.Scanner;

import src.factory.*;
import src.mvc.*;

/**
 * Punto de arranque de la aplicación.
 * <p>
 * ‑ Pregunta la plataforma (Intel+Nvidia o AMD)  
 * ‑ Inyecta la fábrica correspondiente en el <i>Model</i>  
 * ‑ Dispara el flujo MVC por consola
 * </p>
 */
public final class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /* ─── selección de plataforma ─── */
        System.out.println("=== MONOSCHINOS MX ===");
        System.out.println("Seleccione plataforma de componentes:");
        System.out.println("  1) Intel + Nvidia");
        System.out.println("  2) AMD");
        System.out.print("Opción (1‑2): ");

        int opcion = leerEntero(sc, 1, 2);

        ComponenteFactory factory =
                (opcion == 2) ? new AmdFactory()
                              : new IntelNvidiaFactory();

        /* ─── arranque MVC ─── */
        Model       model = new Model(factory);
        View        view  = new View();
        Controller  ctrl  = new Controller(model, view);

        ctrl.iniciar();
    }

    /* ---- helper robusto de lectura ---- */
    private static int leerEntero(Scanner sc, int min, int max) {
        while (true) {
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException ignored) { }
            System.out.printf("Introduce un número entre %d y %d: ", min, max);
        }
    }
}
