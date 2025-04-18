package src.factory;


public class productoFuenteDePoder extends Componente implements FuenteDePoder {
    private int potenciaMaxima;

    public productoFuenteDePoder (String nombre, double precio, String marca, String tipoComponente, int potenciaMaxima) {
        // La marca se fija en 
        super(nombre, precio, marca, "Fuente de poder");
        this.potenciaMaxima = potenciaMaxima;
    }

    @Override
    public String getDescripcion() {
        return nombre + "de marca:" + marca + "con " + potenciaMaxima +"potencia Maxima"  + " PRECIO:" + precio;
    }
    public double getPrecio() {
        return precio;
    }
}