/* src/factory/FuenteDePoder.java */  
package src.factory; 
/**
 * Interfaz que representa una fuente de poder (PSU) en el sistema de componentes.
 * Define las operaciones basicas para obtener informacion tecnica y comercial
 * de cualquier fuente de poder compatible.
 */
public interface FuenteDePoder  { 

    /**
     * Devuelve una descripcion tecnica completa de la fuente de poder.
     * 
     * @return Cadena descriptiva con especificaciones tecnicas.
     */
    String getDescripcion(); 

    /**
     * Obtiene el precio de venta al publico de la fuente de poder.
     * 
     * @return Valor de la fuente
     */
    double getPrecio(); 

    /**
     * Devuelve la potencia nominal maxima en vatios que puede suministrar
     * la fuente de manera continua.
     * 
     * @return Potencia en vatios (W)
     */
    int getPotenciaW(); 
}
