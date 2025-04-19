/* src/factory/Componente.java */
package src.factory;

/**
 * Todos los productos de hardware heredan de esta clase inmutable.
 * Implementa un {@code toString()} genérico y deja a las sub‑clases
 * añadir detalles concretos en {@link #getDescripcion()}.
 */
public abstract class Componente {
    private final String modelo;
    private final double precio;
    private final Marca  marca;
    private final TipoComponente tipo;

    protected Componente(String modelo,
                         double precio,
                         Marca  marca,
                         TipoComponente tipo) {
        this.modelo = modelo;
        this.precio = precio;
        this.marca  = marca;
        this.tipo   = tipo;
    }

    public double         getPrecio()  { return precio; }
    public String         getModelo()  { return modelo; }
    public Marca          getMarca()   { return marca;  }
    public TipoComponente getTipo()    { return tipo;   }

    /** Descripción genérica “MARCA modelo – $precio”. */
    @Override public String toString() {
        return "%s %s – $%,.2f".formatted(marca, modelo, precio);
    }

    /** Cada sub‑clase añade sus propios atributos. */
    public abstract String getDescripcion();
}
