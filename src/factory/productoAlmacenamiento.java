package src.factory;



public class productoAlmacenamiento extends Componente implements Almacenamiento {
    private int capacidadAlmacenamiento;
     private String tipodeAlmacenamiento;


    public productoAlmacenamiento (String nombre, double precio, String marca, String tipoComponente, int capacidadAlmacenamiento, String tipodeAlmacenamiento) {
        // La marca se fija en "Intel"
        super(nombre, precio, marca, "Almacenamineto" );
        this.capacidadAlmacenamiento = capacidadAlmacenamiento;
        this.tipodeAlmacenamiento= tipodeAlmacenamiento;


}

    @Override
    public String getDescripcion() {
        return nombre + " de marca: " + marca + " con " + capacidadAlmacenamiento +
       "GB de capacidad de almacenamiento, tipo " + tipodeAlmacenamiento +
       ". PRECIO: " + precio;
    }

    public double getPrecio() {
        return precio;
    }
}