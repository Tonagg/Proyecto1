package src.compatibilidad;

import java.util.ArrayList;
import java.util.List;

import src.Computadora;
import src.factory.*;

import src.adapter.AdaptadorGPUnvidia;
import src.adapter.AdaptadorMotherboardIntel;

/**
 * Implementa el 90 % de la lógica de compatibilidad.
 * Las sub­clases solo deciden qué hacer cuando hay conflictos.
 */
public abstract class CompatibilidadBase implements VerificaCompatibilidad {

    /* ===== API pública que impone la interfaz ===== */

    @Override
    public final boolean esCompatible(Computadora pc) {
        return getConflictos(pc).isEmpty();
    }

    /* ===== Helpers que reutilizan las subclases ===== */

    /** Devuelve la lista de problemas detectados.  */
    protected List<String> getConflictos(Computadora pc) {

        List<String> problemas = new ArrayList<>();

        CPU         cpu = pc.getCpu();
        GPU         gpu = pc.getGpu();
        Motherboard mb  = pc.getMotherboard();

        // si aún no se han asignado piezas no evaluamos
        if (cpu == null || gpu == null || mb == null) return problemas;

        String marcaCPU = getMarca(cpu);
        String marcaGPU = getMarca(gpu);
        String chipset  = mb.getDescripcion(); // no tenemos un getter formal

        /* Regla 1 – CPU AMD + GPU Nvidia  */
        if ("AMD".equals(marcaCPU) && "Nvidia".equals(marcaGPU)
            && !(gpu instanceof AdaptadorGPUnvidia)) {
            problemas.add("CPU AMD con GPU Nvidia sin adaptar.");
        }

        /* Regla 2 – CPU y Motherboard de marcas distintas */
        if (!chipset.toLowerCase().contains(marcaCPU.toLowerCase())
            && !(mb instanceof AdaptadorMotherboardIntel)) {
            problemas.add("CPU " + marcaCPU + " con motherboard de chipset distinto.");
        }

        // …aquí puedes añadir más reglas sin tocar las subclases
        return problemas;
    }

    /* ===== util ===== */

    /** Obtiene la marca usando `instanceof Componente` sin perder la interfaz. */
    private String getMarca(Object comp) {
        return (comp instanceof src.factory.Componente c) ? c.getMarca() : "Desconocida";
    }
}
