

public class productoFuenteDePoder extends Componente implements FuenteDePoder {
    private int cantidadNucleos;

    public productoFuenteDePoder (String nombre, double precio, String marca, String tipoComponente, int potenciaMaxima) {
        // La marca se fija en "Intel"
        super(nombre, precio, marca, "RAM");
        this.potenciaMaxima = potenciaMaxima;
    }

    @Override
    public String getDescripcion() {
        return nombre + " (" + tamanioDeMemoria + " Tamanio de Memoria, " + marca + ")";
    }
}