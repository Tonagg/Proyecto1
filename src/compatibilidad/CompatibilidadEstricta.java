package src.compatibilidad;

import src.Computadora;

/** No permite ninguna adaptación: si hay conflictos => error. */
public class CompatibilidadEstricta extends CompatibilidadBase {

    @Override
    public String adaptar(Computadora pc) {
        return """
               Modo estricto ‑ sin adaptaciones.
               Conflictos: %s
               """.formatted(String.join(", ", getConflictos(pc)));
    }
}
