package src.compatibilidad;

import src.Computadora;
import src.factory.*;
import src.adapter.*;

public class CompatibilidadFlexible implements VerificaCompatibilidad {

    @Override
    public boolean esCompatible(Computadora pc) {
        return conflictos(pc).isEmpty();
    }

    @Override
    public String adaptar(Computadora pc) {
        StringBuilder nota = new StringBuilder();

        CPU cpu = pc.getCpu();
        GPU gpu = pc.getGpu();
        Motherboard mb = pc.getMotherboard();

        /* --------- regla CPU AMD + GPU Nvidia → adaptar GPU -------- */
        if (cpu.getDescripcion().contains("AMD") && gpu.getDescripcion().contains("Nvidia")) {
            GPU adaptada = new AdaptadorGPUnvidia(gpu);
            pc.setGpu(adaptada);
            nota.append("Se adaptó GPU Nvidia para CPU AMD.\n");
        }

        /* ------ regla CPU AMD + Motherboard Intel → adaptar MB ----- */
        if (cpu.getDescripcion().contains("AMD") && mb.getDescripcion().contains("Intel")) {
            Motherboard adaptada = new AdaptadorMotherboardIntel(mb);
            pc.setMotherboard(adaptada);
            nota.append("Se adaptó Motherboard Intel para CPU AMD.\n");
        }

        // al finalizar ﹣ si quedaron conflictos sin resolver, los listamos
        if (!esCompatible(pc)) {
            nota.append("Persisten incompatibilidades: ")
                .append(String.join(", ", conflictos(pc)))
                .append("\n");
        }

        return nota.toString();
    }

    /* Re‑usa el mismo método que en la versión estricta */
    private java.util.List<String> conflictos(Computadora pc) {
        java.util.List<String> problemas = new java.util.ArrayList<>();

        CPU cpu = pc.getCpu();
        GPU gpu = pc.getGpu();
        Motherboard mb = pc.getMotherboard();

        if (cpu.getDescripcion().contains("AMD") && gpu.getDescripcion().contains("Nvidia")) {
            problemas.add("CPU AMD con GPU Nvidia");
        }
        if (cpu.getDescripcion().contains("AMD") && mb.getDescripcion().contains("Intel")) {
            problemas.add("CPU AMD con Motherboard Intel");
        }
        return problemas;
    }
}
