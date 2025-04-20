/* src/factory/Componente.java */
package src.factory;

/**
 * Clase base abstracta e inmutable para representar componentes de hardware 
 * en un sistema de ensamblado de computadoras. Proporciona atributos comunes 
 * y metodos genericos para todas las subclases concretas.
 */
public abstract class Componente {
    private final String modelo;
    private final double precio;
    private final Marca  marca;
    private final TipoComponente tipo;

    /**
     * Constructor protegido para inicializar los atributos base 
     * del componente. Solo accesible por subclases.
     * 
     * @param modelo Identificador unico del componente
     * @param precio Costo en moneda local.
     * @param marca Fabricante del componente
     * @param tipo Categoria funcional del componente
     */
    protected Componente(String modelo,
                         double precio,
                         Marca  marca,
                         TipoComponente tipo) {
        this.modelo = modelo;
        this.precio = precio;
        this.marca  = marca;
        this.tipo   = tipo;
    }

    /**
     * Devuelve el precio del componente.
     * 
     * @return Valor numerico en moneda local
     */
    public double getPrecio() {
         return precio; 
    }

    /**
     * Obtiene el modelo comercial del componente.
     * 
     * @return Cadena con el nombre del modelo
     */
    public String getModelo()  {
         return modelo; 
    }

    /**
     * Obtiene la marca fabricante del componente.
     * 
     * @return Instancia de la Marca
     */
    public Marca getMarca() {
         return marca; 
    }

    /**
     * Obtiene la categoria funcional del componente.
     * 
     * @return Instancia del TipoComponente
     */
    public TipoComponente getTipo() {
         return tipo;   
    }

    /**
     * Genera una representacion textual generica del componente 
     * en formato: marca, modelo y precio.
     * 
     * @return Cadena formateada con informacion basica
     */
    @Override public String toString() {
        return "%s %s – $%,.2f".formatted(marca, modelo, precio);
    }

    /**
     * Metodo abstracto que debe ser implementado por subclases para 
     * generar una descripcion tecnica detallada del componente.
     *
     * @return Descripción tecnica especifica del componente
     */
    public abstract String getDescripcion();
}
