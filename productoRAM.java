


public class productoRAM extends Componente implements RAM {
    private int cantidadNucleos;

    public productoRAM (String nombre, double precio, String marca, String tipoComponente, int tamanioDeMemoria) {
        // La marca se fija en "Intel"
        super(nombre, precio, marca, "RAM");
        this.tamanioDeMemoria = tamanioDeMemoria;
    }

    @Override
    public String getDescripcion() {
        return nombre + " (" + tamanioDeMemoria + " Tamanio de Memoria, " + marca + ")";
    }
}