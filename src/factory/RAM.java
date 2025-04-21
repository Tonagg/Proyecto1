package src.factory;

/**
 * Interfaz que representa un módulo de memoria RAM.
 * Define operaciones para obtener la descripción, precio y capacidad del módulo.
 */
public interface RAM {

    /**
     * Obtiene una descripción técnica del módulo de RAM.
     *
     * @return cadena con la descripción del módulo
     */
    String getDescripcion();

    /**
     * Obtiene el precio de venta al público del módulo de RAM.
     *
     * @return valor numérico del precio
     */
    double getPrecio();

    /**
     * Obtiene la capacidad del módulo en gigabytes.
     *
     * @return número de gigabytes del módulo
     */
    int getCapacidadGB();
}
