package src.factory;

/**
 * Interfaz que representa un dispositivo de almacenamiento (HDD o SSD).
 * Define las operaciones básicas para obtener descripción, precio,
 * capacidad y tipo de almacenamiento.
 */
public interface Almacenamiento {

    /**
     * Obtiene una descripción técnica del dispositivo de almacenamiento.
     *
     * @return cadena con modelo, marcas y características del dispositivo
     */
    String getDescripcion();

    /**
     * Obtiene el precio de venta al público del dispositivo.
     *
     * @return valor numérico del precio
     */
    double getPrecio();

    /**
     * Obtiene la capacidad de almacenamiento en gigabytes.
     *
     * @return número entero de gigabytes disponibles
     */
    int getCapacidadGB();

    /**
     * Indica si el dispositivo es de estado sólido (SSD).
     *
     * @return {@code true} si es SSD; {@code false} si es HDD
     */
    boolean esSSD();
}
