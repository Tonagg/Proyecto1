package src.decorator;

import src.Computadora;

/**
 * Decorator que añade el software Microsoft Office 365 a una instancia de {@link Computadora}.
 * Implementa el patrón Decorator, ampliando la descripción y el costo total.
 */
public class OfficeDecorator extends SoftwareDecorator {

    /**
     * Crea un nuevo decorator que agrega Microsoft Office 365 a la computadora dada.
     *
     * @param pc instancia de {@link Computadora} a la que se añadirá Office 365
     */
    public OfficeDecorator(Computadora pc) {
        super(pc, "Microsoft Office 365", 500);
    }
}
