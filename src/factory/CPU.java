package src.factory; 

/**
 * Interfaz que representa una Unidad Central de Procesamiento (CPU) en el sistema.
 * Define los metodos basicos que deben implementar todos los componentes de CPU.
 * 
 */
public interface CPU {

     /**
     * Devuelve una descripcion tecnica detallada de la CPU.
     * 
     * @return Cadena con especificaciones tecnicas
     */
     String getDescripcion(); 
     
     /**
     * Obtiene el precio de venta al publico de la CPU.
     * 
     * @return Valor numerico con el precio unitario
     */
     double getPrecio(); 
}
