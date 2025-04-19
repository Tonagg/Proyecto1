/* src/compatibilidad/CompatibilidadFlexible.java */
package src.compatibilidad;

import src.Computadora;
import src.adapter.AdaptadorGPUNvidia;
import src.adapter.AdaptadorMotherboardIntel;
import src.factory.*;

/**
 * Intenta resolver problemas automáticamente usando el patrón *Adapter*.
 */
public class CompatibilidadFlexible extends CompatibilidadBase {

    @Override public String adaptar(Computadora pc) {

        StringBuilder nota = new StringBuilder();

        CPU         cpu = pc.getCpu();
        GPU         gpu = pc.getGpu();
        Motherboard mb  = pc.getMotherboard();

        /* CPU AMD + GPU Nvidia → adaptar GPU */
        if (marca(cpu) == Marca.AMD && marca(gpu) == Marca.NVIDIA &&
            !(gpu instanceof AdaptadorGPUNvidia)) {

            pc.setGpu(new AdaptadorGPUNvidia(gpu));
            nota.append("‑ GPU Nvidia adaptada para CPU AMD.\n");
        }

        /* CPU AMD + Motherboard Intel / MSI → adaptar MB */
        if (marca(cpu) == Marca.AMD && marca(mb) == Marca.INTEL &&
            !(mb instanceof AdaptadorMotherboardIntel)) {

            pc.setMotherboard(new AdaptadorMotherboardIntel(mb));
            nota.append("‑ Motherboard Intel adaptada para CPU AMD.\n");
        }

        /* Resultado final */
        var pendientes = conflictos(pc);
        if (!pendientes.isEmpty()) {
            nota.append("⚠ Persisten incompatibilidades: ")
                .append(String.join(", ", pendientes))
                .append('\n');
        }
        return nota.toString();
    }
}
