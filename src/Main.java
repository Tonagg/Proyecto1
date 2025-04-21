package src;

import java.util.Scanner;
import src.factory.AmdFactory;
import src.factory.IntelNvidiaFactory;
import src.factory.ComponenteFactory;
import src.mvc.Controller;
import src.mvc.Model;
import src.mvc.View;

/**
 * Punto de entrada de la aplicación MonosChinos MX.
 * Gestiona la selección de sucursal, plataforma de componentes y el flujo principal de compras.
 */
public final class Main {

    /**
     * Método principal que inicia la ejecución de la consola.
     * <ol>
     *   <li>Selecciona sucursal de origen.</li>
     *   <li>Muestra menú para nueva compra o consulta de historial.</li>
     *   <li>Dependiendo de la plataforma, usa fábrica Intel+Nvidia o AMD.</li>
     * </ol>
     *
     * @param args parámetros de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // — Selección de sucursal —
        System.out.println("Seleccione sucursal de origen:");
        for (Sucursal s : Sucursal.values()) {
            System.out.printf(" %d) %s%n", s.ordinal() + 1, s);
        }
        int sucOp = leerEntero(sc, 1, Sucursal.values().length);
        Sucursal sucursal = Sucursal.values()[sucOp - 1];
        System.out.println("Sucursal seleccionada: " + sucursal + "\n");

        while (true) {
            // ─── Menú principal ───
            System.out.println("=== MONOSCHINOS MX (" + sucursal + ") ===");
            System.out.println("1) Nueva compra");
            System.out.println("2) Consultar historial de tickets");
            System.out.println("0) Salir");
            System.out.print("Elige opción: ");
            int mainOp = leerEntero(sc, 0, 2);

            if (mainOp == 0) {
                System.out.println("¡Hasta luego!");
                break;
            }

            View view = new View();

            if (mainOp == 2) {
                // Mostrar historial de esta sucursal
                Model dummyModel = new Model(new IntelNvidiaFactory());
                Controller ctrlHist = new Controller(dummyModel, view, sucursal);
                ctrlHist.mostrarHistorial();
                continue;
            }

            // ─── Nueva compra ───
            System.out.println("Seleccione plataforma de componentes:");
            System.out.println(" 1) Intel + Nvidia");
            System.out.println(" 2) AMD");
            System.out.print("Opción (1‑2): ");
            int plat = leerEntero(sc, 1, 2);

            ComponenteFactory factory =
                (plat == 2) ? new AmdFactory()
                            : new IntelNvidiaFactory();

            Model model = new Model(factory);
            Controller ctrl = new Controller(model, view, sucursal);
            ctrl.realizarCompra();
        }

        sc.close();
    }

    /**
     * Lee un entero del usuario dentro de un rango dado, reintentando hasta que sea válido.
     *
     * @param sc  escáner para lectura de consola
     * @param min valor mínimo permitido (inclusive)
     * @param max valor máximo permitido (inclusive)
     * @return número entero ingresado por el usuario dentro del rango
     */
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
