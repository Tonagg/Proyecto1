package src.compatibilidad;

import src.Computadora;
import src.factory.CPU;
import src.factory.GPU;
import src.factory.Motherboard;

public class CompatibilidadEstricta implements VerificaCompatibilidad {

    @Override
    public boolean esCompatible(Computadora pc) {
        return conflictos(pc).isEmpty();
    }

    @Override
    public String adaptar(Computadora pc) {
        // En modo estricto NO se permiten adaptaciones.
        return "Modo estricto: no se realizaron adaptaciones.\n" +
               String.join("\n", conflictos(pc));
    }

    /* ---------------------------------------------------------- */
    /* ----------  Reglas concretas de compatibilidad ----------- */
    /* ---------------------------------------------------------- */
    private java.util.List<String> conflictos(Computadora pc) {
        java.util.List<String> problemas = new java.util.ArrayList<>();

        CPU cpu = pc.getCpu();
        GPU gpu = pc.getGpu();
        Motherboard mb = pc.getMotherboard();

        // Regla 1: CPU AMD con GPU Nvidia
        if (cpu.getDescripcion().contains("AMD") && gpu.getDescripcion().contains("Nvidia")) {
            problemas.add("CPU AMD con GPU Nvidia no recomendada.");
        }
        // Regla 2: CPU & Motherboard de marcas distintas
        if (!cpu.getDescripcion().contains(mb.getDescripcion().split(" ")[0])) {
            problemas.add("El chipset de la motherboard no coincide con la marca de la CPU.");
        }
        // … añade más reglas si lo deseas
        return problemas;
    }
}
