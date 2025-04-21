package src.decorator;

import src.Computadora;

/**
 * Decorator que añade el entorno WSL (Windows Subsystem for Linux) como terminal de estilo hacker
 * a una instancia de {@link Computadora}. Implementa el patrón Decorator, ampliando la descripción
 * y el costo total.
 */
public class WSLDecorator extends SoftwareDecorator {

    /**
     * Crea un nuevo decorator que agrega WSL / Terminal Hacker a la computadora dada.
     *
     * @param pc instancia de {@link Computadora} a la que se añadirá WSL
     */
    public WSLDecorator(Computadora pc) {
        super(pc, "WSL / Terminal Hacker", 1000);
    }
}