package src.decorator;

import src.Computadora;

/**
 * Decorator que añade el sistema operativo Windows 11 a una instancia de {@link Computadora}.
 * Implementa el patrón Decorator, ampliando la descripción y el costo total.
 */
public class WindowsDecorator extends SoftwareDecorator {

    /**
     * Crea un nuevo decorator que agrega Windows 11 a la computadora dada.
     *
     * @param pc instancia de {@link Computadora} a la que se añadirá Windows 11
     */
    public WindowsDecorator(Computadora pc) {
        super(pc, "Windows 11", 1000);
    }
}
