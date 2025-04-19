/* src/compatibilidad/VerificaCompatibilidad.java */
package src.compatibilidad;

import src.Computadora;

/** Estrategia que revisa compatibilidad y, si falla, puede intentar adaptarla. */
public interface VerificaCompatibilidad {

    boolean esCompatible(Computadora pc);

    /** @return notas de adaptación (cadena vacía si no se hizo nada) */
    String adaptar(Computadora pc);
}
