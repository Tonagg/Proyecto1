package src.factory; 

/**
 * Interfaz que representa una Unidad de Procesamiento Grafico (GPU) en el sistema.
 * Define las operaciones basicas para obtener informacion tecnica y comercial
 * de cualquier tarjeta grafica compatible.
 */
    public interface GPU { 

        /**
        * Genera una descripcion tecnica detallada de la GPU
        * 
        * @return Cadena con especificaciones tecnicas completas
        */
        String getDescripcion(); 
        /**
        * Obtiene el precio de mercado.
        * 
        * @return Valor del GPU
        */
        double getPrecio(); 
    }
