package src.decorator;

import java.util.Objects;
import java.util.List;
import src.Computadora;
import src.factory.CPU;
import src.factory.GPU;
import src.factory.FuenteDePoder;
import src.factory.Motherboard;
import src.factory.Gabinete;
import src.factory.RAM;
import src.factory.Almacenamiento;

/**
 * Decorador genérico para añadir software a una instancia de {@link Computadora}.
 * Implementa el patrón Decorator, delegando todas las operaciones de hardware
 * a la instancia base y añadiendo una descripción y costo extra por el software.
 */
public abstract class SoftwareDecorator extends Computadora {

    /** Computadora original que se está decorando. */
    protected final Computadora base;

    /** Descripción del software adicional. */
    private final String descripcionExtra;

    /** Costo extra asociado al software. */
    private final double costoExtra;

    /**
     * Construye un nuevo decorador de software sobre la computadora dada.
     *
     * @param base              computadora original a decorar
     * @param descripcionExtra  descripción del software agregado para la presentación
     * @param costoExtra        costo adicional que representa el software
     * @throws NullPointerException     si la computadora base es nula
     * @throws IllegalStateException    si el software ya había sido agregado previamente
     */
    protected SoftwareDecorator(Computadora base,
                                String descripcionExtra,
                                double costoExtra) {

        Objects.requireNonNull(base, "La computadora base no puede ser nula");
        this.base = base;
        this.descripcionExtra = descripcionExtra;
        this.costoExtra = costoExtra;

        // Evita duplicar la instalación del mismo software
        if (base.getDescripcion().contains(descripcionExtra)) {
            throw new IllegalStateException(
                String.format("Ya se había añadido %s", descripcionExtra)
            );
        }
    }

    /**
     * Calcula el precio total incluyendo el costo adicional del software.
     *
     * @return precio total combinado de hardware y software
     */
    @Override
    public double calcularPrecioTotal() {
        return base.calcularPrecioTotal() + costoExtra;
    }

    /**
     * Obtiene la descripción completa de la computadora, incluyendo el software agregado.
     *
     * @return descripción extendida con el nombre del software
     */
    @Override
    public String getDescripcion() {
        return base.getDescripcion() + " + " + descripcionExtra;
    }

    // Métodos que delegan la consulta de hardware a la computadora base

    @Override public CPU getCpu() { return base.getCpu(); }
    @Override public GPU getGpu() { return base.getGpu(); }
    @Override public FuenteDePoder getFuente() { return base.getFuente(); }
    @Override public Motherboard getMotherboard() { return base.getMotherboard(); }
    @Override public Gabinete getGabinete() { return base.getGabinete(); }
    @Override public List<RAM> getRamModules() { return base.getRamModules(); }
    @Override public List<Almacenamiento> getDiscos() { return base.getDiscos(); }

    // Nota: No se permiten setters en el decorador de software, ya que
    // la instalación de software no modifica el hardware.
}
