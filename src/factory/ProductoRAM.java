package src.factory;

/**
 * Implementación de {@link RAM} para módulos de memoria RAM.
 * Extiende {@link Componente} y proporciona el atributo de capacidad en GB.
 */
public final class ProductoRAM extends Componente implements RAM {

    /** Capacidad del módulo de RAM en gigabytes. */
    private final int capacidadGB;

    /**
     * Construye un nuevo módulo de RAM con las especificaciones dadas.
     *
     * @param modelo       nombre o modelo comercial del módulo de RAM
     * @param precio       precio de venta al público
     * @param marca        fabricante del módulo de RAM
     * @param capacidadGB  capacidad en gigabytes
     */
    public ProductoRAM(String modelo, double precio, Marca marca, int capacidadGB) {
        super(modelo, precio, marca, TipoComponente.RAM);
        this.capacidadGB = capacidadGB;
    }

    /**
     * Obtiene la capacidad del módulo en gigabytes.
     *
     * @return valor entero representando la capacidad en GB
     */
    @Override
    public int getCapacidadGB() {
        return capacidadGB;
    }

    /**
     * Obtiene una descripción técnica del módulo, incluyendo la capacidad.
     *
     * @return cadena con la descripción del módulo de RAM
     */
    @Override
    public String getDescripcion() {
        return String.format("%s | %d GB", super.toString(), capacidadGB);
    }
}