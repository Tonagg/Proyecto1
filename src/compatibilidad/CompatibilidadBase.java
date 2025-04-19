/* src/compatibilidad/CompatibilidadBase.java */
package src.compatibilidad;

import java.util.ArrayList;
import java.util.List;

import src.Computadora;
import src.adapter.AdaptadorGPUNvidia;
import src.adapter.AdaptadorMotherboardIntel;
import src.factory.*;

/**
 * Implementa el 90 % de la lógica de compatibilidad.
 * Las sub‑clases solo deciden *qué hacer* cuando hay conflictos.
 */
public abstract class CompatibilidadBase implements VerificaCompatibilidad {

    /* ——— API pública ——— */
    @Override public final boolean esCompatible(Computadora pc) {
        return conflictos(pc).isEmpty();
    }

    /* ——— Helpers a disposición de las sub‑clases ——— */
    protected final List<String> conflictos(Computadora pc) {

        List<String> errores = new ArrayList<>();

        CPU         cpu = pc.getCpu();
        GPU         gpu = pc.getGpu();
        Motherboard mb  = pc.getMotherboard();

        /* piezas incompletas → no evaluamos todavía */
        if (cpu == null || gpu == null || mb == null) return errores;

        Marca marcaCPU = marca(cpu);
        Marca marcaGPU = marca(gpu);
        Marca marcaMB  = marca(mb);

        /* Regla 1 – CPU AMD + GPU Nvidia sin adaptar */
        if (marcaCPU == Marca.AMD && marcaGPU == Marca.NVIDIA &&
            !(gpu instanceof AdaptadorGPUNvidia)) {
            errores.add("CPU AMD con GPU Nvidia sin adaptar.");
        }

        /* Regla 2 – CPU y Motherboard de marcas distintas */
        if (marcaCPU != marcaMB &&
            !(mb instanceof AdaptadorMotherboardIntel)) {
            errores.add("Motherboard %s usada con CPU %s."
                        .formatted(marcaMB, marcaCPU));
        }

        // …añade aquí más reglas si las necesitas
        return errores;
    }

    /* ——— util ——— */
    protected final Marca marca(Object c) {
        return (c instanceof Componente comp) ? comp.getMarca() : null;
    }
}
