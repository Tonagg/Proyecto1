package src.decorator;

import src.Computadora;

/**
 * Decorador genérico de “software”.
 * Cada subclase solo indica `descripcionExtra` y `costoExtra`.
 */
public abstract class SoftwareDecorator extends Computadora {

    protected final Computadora base;
    private final String  descripcionExtra;
    private final double  costoExtra;

    protected SoftwareDecorator(Computadora base,
                                String descripcionExtra,
                                double costoExtra) {

        /* --- evita instalar dos veces el mismo software --- */
        if (base instanceof SoftwareDecorator d &&
            d.contains(descripcionExtra)) {
            throw new IllegalStateException(
                "Ya se había añadido " + descripcionExtra);
        }

        this.base             = base;
        this.descripcionExtra = descripcionExtra;
        this.costoExtra       = costoExtra;
    }

    /* ---------- delegaciones ------------- */

    @Override
    public double calcularPrecioTotal() {
        return base.calcularPrecioTotal() + costoExtra;
    }

    @Override
    public String getDescripcion() {
        return base.getDescripcion() + " + " + descripcionExtra;
    }

    /* ---------- util interno ------------- */

    /** true ↔ en la cadena de decoradores ya está la descripción. */
    private boolean contains(String desc) {
        return getDescripcion().contains(desc);
    }
}
