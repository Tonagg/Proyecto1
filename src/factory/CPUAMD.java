package src.factory;

/**
 * Implementacion concreta de una CPU AMD que hereda de Componente 
 * e implementa la interfaz CPU. Representa procesadores de la marca AMD
 * con funcionalidades especificas.
 */
public final class CPUAMD extends Componente implements CPU {

    /** Numero de nucleos fisicos del procesador */
    private final int nucleos;

    /**
     * Construye una nueva CPU AMD con especificaciones tecnicas detalladas.
     * 
     * @param modelo   Identificador comercial 
     * @param precio   Costo en moneda local 
     * @param nucleos  Cantidad de nucleos de procesamiento
     */
    public CPUAMD(String modelo, double precio, int nucleos) {
        super(modelo, precio, Marca.AMD, TipoComponente.CPU);
        this.nucleos = nucleos;
    }

    /**
     * Genera una descripcion tecnica detallada que combina informacion de la
     * clase base con atributos especificos de AMD.
     * 
     * @return Cadena con informacion tecnica completa
     */
    public String getDescripcion() {
        return "%s | %d nucleos".formatted(super.toString(), nucleos);
    }
}
