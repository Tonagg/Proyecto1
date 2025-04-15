

public class productoFuenteDePoder extends Componente implements FuenteDePoder {
    private int cantidadNucleos;

    public productoFuenteDePoder (String nombre, double precio, String marca, String tipoComponente, int potenciaMaxima) {
        // La marca se fija en 
        super(nombre, precio, marca, "Fuente de poder");
        this.potenciaMaxima = potenciaMaxima;
    }

    @Override
    public String getDescripcion() {
        return nombre + " (" + potenciaMaxima + " Tamanio de Memoria, " + marca + ")";
    }
}