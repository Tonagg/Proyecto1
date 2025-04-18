package src.mvc;
// ComputadoraView.java
import java.util.Scanner;
import src.decorator.*;
import src.*;


public class View {
    private final Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("=== MONOSCHINOS MX ===");
        System.out.println("1) Computadora personalizada");
        System.out.println("2) Computadora prearmada");
        System.out.print("Elige opción (1 ó 2): ");
        return scanner.nextInt();
    }

    public String solicitarModeloPrearmada() {
        System.out.print("Introduce modelo prearmado (Gamer/Basica/...): ");
        return scanner.next();
    }

    /** Pregunta si el usuario quiere agregar más software. */
    public boolean confirmarAgregarSoftware() {
        System.out.print("¿Desea agregar software? (s/n): ");
        String resp = scanner.next();
        return resp.equalsIgnoreCase("s");
    }

    /** Muestra las opciones de software y devuelve la elegida. */
    public int seleccionarSoftware() {
        System.out.println("Qué software desea agregar:");
        System.out.println("1) Windows 10/11");
        System.out.println("2) Office 365");
        System.out.println("3) Adobe Photoshop");
        System.out.println("4) WSL (Terminal tipo hacker)");
        System.out.println("0) Ninguno, salir");
        System.out.print("Elige opción: ");
        return scanner.nextInt();
    }

    public void mostrarComputadora(Computadora pc) {
        System.out.println("\n--- CONFIGURACIÓN FINAL ---");
        System.out.println(pc.getDescripcion());
        System.out.printf("Precio total: $%.2f\n", pc.calcularPrecioTotal());
    }
}
