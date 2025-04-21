package src.factory;

/**
 * Interfaz que representa una placa base (motherboard) en el sistema.
 * Define operaciones básicas para obtener información técnica y comercial.
 */
public interface Motherboard {

    /**
     * Obtiene una descripción detallada del componente, incluyendo modelo y chipset.
     *
     * @return cadena con descripción técnica de la placa base
     */
    String getDescripcion();

    /**
     * Obtiene el precio de venta al público de la placa base.
     *
     * @return valor numérico del precio
     */
    double getPrecio();

    /**
     * Obtiene el chipset soportado por la placa base.
     *
     * @return cadena con el nombre del chipset
     */
    String getChipset();
}
