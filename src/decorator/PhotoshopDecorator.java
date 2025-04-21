package src.decorator;

import src.Computadora;

/**
 * Decorator que añade el software Adobe Photoshop a una instancia de {@link Computadora}.
 * Implementa el patrón Decorator, ampliando la descripción y el costo total.
 */
public class PhotoshopDecorator extends SoftwareDecorator {

    /**
     * Crea un nuevo decorator que agrega Adobe Photoshop a la computadora dada.
     *
     * @param pc instancia de {@link Computadora} a la que se añadirá Adobe Photoshop
     */
    public PhotoshopDecorator(Computadora pc) {
        super(pc, "Adobe Photoshop", 1200);
    }
}
