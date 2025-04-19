/* src/decorator/SoftwareDecorator.java */
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
 * Decorador genérico de “software”.
 * Delega *todos* los métodos de {@link Computadora} y
 * añade {@code descripcionExtra} + {@code costoExtra}.
 */
public abstract class SoftwareDecorator extends Computadora {

    protected final Computadora base;
    private final String descripcionExtra;
    private final double costoExtra;

    protected SoftwareDecorator(Computadora base,
                                String descripcionExtra,
                                double costoExtra) {

        Objects.requireNonNull(base);
        this.base             = base;
        this.descripcionExtra = descripcionExtra;
        this.costoExtra       = costoExtra;

        /* — evita duplicados — */
        if (getDescripcion().contains(descripcionExtra))
            throw new IllegalStateException(
                "Ya se había añadido %s".formatted(descripcionExtra));
    }

    /* ──────── delegaciones automáticas ──────── */

    @Override public double calcularPrecioTotal()      { return base.calcularPrecioTotal() + costoExtra; }
    @Override public String  getDescripcion()          { return base.getDescripcion() + " + " + descripcionExtra; }

    /* getters de hardware */
    @Override public CPU            getCpu()           { return base.getCpu(); }
    @Override public GPU            getGpu()           { return base.getGpu(); }
    @Override public FuenteDePoder  getFuente()        { return base.getFuente(); }
    @Override public Motherboard    getMotherboard()   { return base.getMotherboard(); }
    @Override public Gabinete       getGabinete()      { return base.getGabinete(); }
    @Override public List<RAM>      getRamModules()    { return base.getRamModules(); }
    @Override public List<Almacenamiento> getDiscos()  { return base.getDiscos(); }

    /* setters siguen prohibidos (instalación de software no cambia HW) */
}
