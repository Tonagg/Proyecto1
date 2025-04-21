package src;

import java.util.Scanner;

import src.factory.*;
import src.mvc.*;

public final class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // — Selección de sucursal —
        System.out.println("Seleccione sucursal de origen:");
        System.out.println(" 1) CDMX (central)");
        System.out.println(" 2) Chihuahua");
        System.out.println(" 3) Jalisco");
        System.out.println(" 4) Yucatán");
        System.out.print("Opción (1–4): ");
        int sucOp = leerEntero(sc, 1, 4);
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

            // Creamos un View genérico
            View view = new View();

            if (mainOp == 2) {
                // Solo mostramos historial (no necesitamos fábrica real)
                Model dummyModel = new Model(new IntelNvidiaFactory());
                Controller ctrlHist = new Controller(dummyModel, view);
                ctrlHist.mostrarHistorial();
                continue;
            }

            // ─── Nueva compra ───
            // Preguntamos plataforma **solo si** se va a comprar
            System.out.println("Seleccione plataforma de componentes:");
            System.out.println("  1) Intel + Nvidia");
            System.out.println("  2) AMD");
            System.out.print("Opción (1‑2): ");
            int plat = leerEntero(sc, 1, 2);

            ComponenteFactory factory =
                (plat == 2) ? new AmdFactory()
                            : new IntelNvidiaFactory();

            // Inyectamos la fábrica en el Modelo y arrancamos la compra
            Model model = new Model(factory);
            Controller ctrl = new Controller(model, view);
            ctrl.realizarCompra();
        }

        sc.close();
    }

    // Reutilizamos tu helper
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
