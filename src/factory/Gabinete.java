package src.factory; 

/**
 * Interfaz que representa un gabinete de computadora en el sistema.
 * Define las operaciones basicas para obtener informacion comercial y tecnica
 * de cualquier tipo de gabinete compatible.
 */
  public interface Gabinete { 

    /**
     * Proporciona una descripcion tecnica detallada del gabinete
     * 
     * @return Cadena con especificaciones completas
     */
    String getDescripcion(); 

    /**
     * Obtiene el precio de venta recomendado del gabinete.
     * 
     * @return Valor del gabinete
     */
    double getPrecio(); 
  }
