/* src/factory/CPUIntel.java */
package src.factory;

/**
 * Implementacion concreta de una CPU Intel que hereda de Componente 
 * e implementa la interfaz CPU. Representa procesadores de la marca Intel
 * con especificaciones tecnicas detalladas.
 */
public final class CPUIntel extends Componente implements CPU {

    /** Cantidad de nucleos de procesamiento*/
    private final int nucleos;

    /**
     * Construye una nueva CPU Intel con arquitectura hibrida.
     * 
     * @param modelo   Denominacion tecnica 
     * @param precio   Valor del producto
     * @param nucleos  Total de nucleos 
     */
    public CPUIntel(String modelo, double precio, int nucleos) {
        super(modelo, precio, Marca.INTEL, TipoComponente.CPU);
        this.nucleos = nucleos;
    }

    /**
     * Genera una descripcion tecnica que combina informacion base del componente
     * con caracteristicas especificas de la arquitectura Intel.
     * 
     * @return Cadena descriptiva con especificaciones completas
     */
    public String getDescripcion() {
        return "%s | %d nucleos".formatted(super.toString(), nucleos);
    }
}
