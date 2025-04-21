package src.factory;

/**
 * Implementación de {@link Gabinete} para gabinetes de computadora.
 * Extiende {@link Componente} y representa un gabinete genérico.
 */
public final class ProductoGabinete extends Componente implements Gabinete {

    /**
     * Construye un nuevo gabinete con las especificaciones dadas.
     *
     * @param modelo nombre o modelo comercial del gabinete
     * @param precio precio de venta al público
     * @param marca  fabricante del gabinete
     */
    public ProductoGabinete(String modelo, double precio, Marca marca) {
        super(modelo, precio, marca, TipoComponente.CASE);
    }

    /**
     * Obtiene una descripción textual del gabinete, basada en la implementación de la clase base.
     *
     * @return cadena que representa el gabinete y su precio
     */
    @Override
    public String getDescripcion() {
        return super.toString();
    }
}