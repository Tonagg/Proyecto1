
package src.compatibilidad;

import src.Computadora;
/**
 * Administra todas las reglas de compatibilidad.
 */
public class CompatibilidadManager {
    private final java.util.List<ReglaCompatibilidad> reglas = java.util.List.of(
        new AMDNvidiaRegla(),
        new AMDMotherboardRegla()
    );

    /**
     * Devuelve una lista de mensajes de conflicto sin adaptar.
     */
    public java.util.List<String> verificar(Computadora pc) {
        var conflictos = new java.util.ArrayList<String>();
        for (var regla : reglas) {
            if (regla.hayConflicto(pc)) {
                conflictos.add(regla.getMensajeConflicto(pc));
            }
        }
        return conflictos;
    }

    /**
     * Aplica todas las adaptaciones posibles y devuelve notas.
     */
    public String adaptar(Computadora pc) {
        var notas = new StringBuilder();
        for (var regla : reglas) {
            if (regla.hayConflicto(pc) && regla.esAdaptable()) {
                regla.aplicarAdaptacion(pc);
                notas.append(regla.getMensajeAdaptacion(pc)).append("");
            }
        }
        return notas.toString();
    }
}