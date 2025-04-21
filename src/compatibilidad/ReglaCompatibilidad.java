package src.compatibilidad;

import src.Computadora;

/**
 * Interfaz que define el contrato para reglas de compatibilidad entre componentes de una computadora.
 * Cada implementación debe detectar conflictos, describirlos, indicar si son adaptables,
 * aplicar la adaptación correspondiente y proporcionar un mensaje de adaptación.
 */
public interface ReglaCompatibilidad {

    /**
     * Verifica si existe un conflicto de compatibilidad en la computadora dada.
     *
     * @param pc instancia de {@link Computadora} a evaluar
     * @return {@code true} si se detecta un conflicto, {@code false} en caso contrario
     */
    boolean hayConflicto(Computadora pc);

    /**
     * Proporciona un mensaje descriptivo del conflicto detectado.
     *
     * @param pc instancia de {@link Computadora} con conflicto
     * @return cadena con la descripción del conflicto
     */
    String getMensajeConflicto(Computadora pc);

    /**
     * Indica si el conflicto detectado puede ser resuelto mediante adaptación.
     *
     * @return {@code true} si es adaptable, {@code false} si no lo es
     */
    boolean esAdaptable();

    /**
     * Aplica la adaptación necesaria para resolver el conflicto en la computadora.
     *
     * @param pc instancia de {@link Computadora} a la que se aplicará la adaptación
     */
    void aplicarAdaptacion(Computadora pc);

    /**
     * Proporciona un mensaje descriptivo de la adaptación realizada.
     *
     * @param pc instancia de {@link Computadora} que fue adaptada
     * @return cadena con la descripción de la adaptación
     */
    String getMensajeAdaptacion(Computadora pc);
}
