// Componente.java
// Clase abstracta que define atributos y método común para todos los componentes.
public abstract class Componente {
    protected String nombre;
    protected double precio;
    protected String marca;

    public Componente(String nombre, double precio, String marca, String tipoComponente) {
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.tipoComponente = tipoComponente;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String TipoComponente(){
        return tipoComponente
    }
    
    // Método abstracto para obtener la descripción del componente.
    public abstract String getDescripcion();
}
