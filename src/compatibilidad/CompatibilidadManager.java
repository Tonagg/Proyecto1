package src.compatibilidad;

import src.Computadora;

/**
 * Administrador de reglas de compatibilidad que centraliza la verificación
 * y aplicación de adaptaciones para una instancia de {@link Computadora}.
 *
 * Esta clase recorre un conjunto de {@link ReglaCompatibilidad} y permite:
 *  - Identificar conflictos sin adaptar
 *  - Aplicar adaptaciones automáticas a los componentes incompatibles
 */
public class CompatibilidadManager {

    /**
     * Lista inmutable de reglas de compatibilidad a evaluar.
     */
    private final java.util.List<ReglaCompatibilidad> reglas = java.util.List.of(
        new AMDNvidiaRegla(),
        new AMDMotherboardRegla()
    );

    /**
     * Verifica todas las reglas en búsqueda de conflictos en la computadora dada.
     *
     * @param pc computadora a evaluar
     * @return lista de mensajes descriptivos de cada conflicto detectado
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
     * Aplica todas las adaptaciones disponibles para resolver conflictos.
     *
     * @param pc computadora cuyas reglas adaptables se aplicarán
     * @return cadena con mensajes concatenados de cada adaptación realizada
     */
    public String adaptar(Computadora pc) {
        var notas = new StringBuilder();
        for (var regla : reglas) {
            if (regla.hayConflicto(pc) && regla.esAdaptable()) {
                regla.aplicarAdaptacion(pc);
                notas.append(regla.getMensajeAdaptacion(pc));
            }
        }
        return notas.toString();
    }
}
