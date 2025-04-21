package src.factory;

/**
 * Implementación de {@link Almacenamiento} para dispositivos de almacenamiento genéricos.
 * Extiende {@link Componente} y proporciona detalles de capacidad y tipo (SSD o HDD).
 */
public final class ProductoAlmacenamiento extends Componente implements Almacenamiento {

    /** Capacidad de almacenamiento en gigabytes. */
    private final int capacidadGB;
    /** Indicador de si el dispositivo es SSD. */
    private final boolean ssd;

    /**
     * Construye un nuevo dispositivo de almacenamiento con las especificaciones dadas.
     *
     * @param modelo      nombre o modelo comercial del dispositivo
     * @param precio      precio de venta al público
     * @param marca       marca del fabricante
     * @param capacidadGB capacidad en gigabytes
     * @param ssd         {@code true} si es SSD; {@code false} si es HDD
     */
    public ProductoAlmacenamiento(String modelo, double precio, Marca marca,
                                  int capacidadGB, boolean ssd) {
        super(modelo, precio, marca, TipoComponente.STORAGE);
        this.capacidadGB = capacidadGB;
        this.ssd = ssd;
    }

    /**
     * Obtiene la capacidad de almacenamiento en gigabytes.
     *
     * @return número entero representando la capacidad en GB
     */
    @Override
    public int getCapacidadGB() {
        return capacidadGB;
    }

    /**
     * Indica si el dispositivo es de estado sólido (SSD).
     *
     * @return {@code true} si es SSD; {@code false} si es HDD
     */
    @Override
    public boolean esSSD() {
        return ssd;
    }

    /**
     * Obtiene una descripción técnica del dispositivo, incluyendo capacidad y tipo.
     *
     * @return cadena con la descripción formateada
     */
    @Override
    public String getDescripcion() {
        return String.format("%s | %d GB %s", super.toString(), capacidadGB, ssd ? "SSD" : "HDD");
    }
}
