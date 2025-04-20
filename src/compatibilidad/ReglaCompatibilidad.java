package src.compatibilidad;

import src.Computadora;


/**
 * Regla genérica de compatibilidad.
 */
public interface ReglaCompatibilidad {
    /** Retorna true si hay conflicto con la PC dada. */
    boolean hayConflicto(Computadora pc);
    /** Mensaje que describe el conflicto detectado. */
    String getMensajeConflicto(Computadora pc);
    /** Indica si este conflicto se puede adaptar. */
    boolean esAdaptable();
    /** Aplica la adaptación (p.ej. un Adapter) sobre la PC. */
    void aplicarAdaptacion(Computadora pc);
    /** Mensaje que describe la adaptación realizada. */
    String getMensajeAdaptacion(Computadora pc);
}