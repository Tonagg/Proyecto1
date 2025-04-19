package src.compatibilidad;

import src.Computadora;
import src.factory.*;

import src.adapter.AdaptadorGPUnvidia;
import src.adapter.AdaptadorMotherboardIntel;

/** Intenta resolver los problemas mediante *Adapter*. */
public class CompatibilidadFlexible extends CompatibilidadBase {

    @Override
    public String adaptar(Computadora pc) {

        StringBuilder nota = new StringBuilder();

        CPU         cpu = pc.getCpu();
        GPU         gpu = pc.getGpu();
        Motherboard mb  = pc.getMotherboard();

        /* CPU AMD + GPU Nvidia → adaptar GPU */
        if ("AMD".equals(getMarca(cpu)) && "Nvidia".equals(getMarca(gpu))
            && !(gpu instanceof AdaptadorGPUnvidia)) {

            pc.setGpu(new AdaptadorGPUnvidia(gpu));
            nota.append("‑ GPU Nvidia adaptada para CPU AMD.\n");
        }

        /* CPU AMD + Motherboard Intel → adaptar MB */
        if ("AMD".equals(getMarca(cpu)) &&
            mb.getDescripcion().toLowerCase().contains("intel") &&
            !(mb instanceof AdaptadorMotherboardIntel)) {

            pc.setMotherboard(new AdaptadorMotherboardIntel(mb));
            nota.append("‑ Motherboard Intel adaptada para CPU AMD.\n");
        }

        /* Resultado final */
        if (!getConflictos(pc).isEmpty()) {
            nota.append("⚠ Persisten incompatibilidades: ")
                .append(String.join(", ", getConflictos(pc)))
                .append('\n');
        }
        return nota.toString();
    }

    /* util privado para evitar repetir código */
    private String getMarca(Object comp) {
        return (comp instanceof src.factory.Componente c) ? c.getMarca() : "Desconocida";
    }
}
