package src;

import java.util.Scanner;
import src.factory.*;
import src.mvc.*;
import src.compatibilidad.*;

public final class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Elegir fábrica de componentes
        System.out.println("=== MONOSCHINOS MX ===");
        System.out.println("Seleccione tipo de componentes:");
        System.out.println("  1) Intel + Nvidia");
        System.out.println("  2) AMD");
        System.out.print("Opción (1-2): ");
        int fabChoice = sc.nextInt();

        ComponenteFactory factory = (fabChoice == 2)
            ? new AmdFactory()
            : new IntelNvidiaFactory();

        // 2) Elegir modo de compatibilidad
        System.out.println("Modo de compatibilidad:");
        System.out.println("  1) Estricto (sin adaptaciones)");
        System.out.println("  2) Flexible (con adaptadores)");
        System.out.print("Opción (1-2): ");
        int compChoice = sc.nextInt();

        Model model = new Model(factory);
        if (compChoice == 1) {
            model.setVerificador(new CompatibilidadEstricta());
        } else {
            model.setVerificador(new CompatibilidadFlexible());
        }

        // 3) Arrancar MVC
        View view = new View();
        new Controller(model, view).iniciar();
    }
}
