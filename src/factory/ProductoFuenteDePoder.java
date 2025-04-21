package src.factory;

/**
 * Implementación de {@link FuenteDePoder} para fuentes de alimentación genéricas.
 * Extiende {@link Componente} y proporciona el atributo de potencia máxima.
 */
public final class ProductoFuenteDePoder extends Componente implements FuenteDePoder {

    /** Potencia nominal máxima de la fuente en vatios. */
    private final int potenciaW;

    /**
     * Construye una nueva fuente de poder con las especificaciones dadas.
     *
     * @param modelo   nombre o modelo comercial de la fuente
     * @param precio   precio de venta al público
     * @param marca    marca del fabricante
     * @param potenciaW potencia máxima en vatios (W)
     */
    public ProductoFuenteDePoder(String modelo, double precio, Marca marca, int potenciaW) {
        super(modelo, precio, marca, TipoComponente.PSU);
        this.potenciaW = potenciaW;
    }

    /**
     * Obtiene la potencia nominal máxima que puede suministrar la fuente.
     *
     * @return potencia en vatios
     */
    @Override
    public int getPotenciaW() {
        return potenciaW;
    }

    /**
     * Obtiene una descripción técnica de la fuente, incluyendo potencia.
     *
     * @return cadena con descripción formateada de la fuente de poder
     */
    @Override
    public String getDescripcion() {
        return String.format("%s | %d W", super.toString(), potenciaW);
    }
}
