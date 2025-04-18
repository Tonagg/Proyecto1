package src.factory;



public class productoRAM extends Componente implements RAM {
    private int tamanioDeMemoria;

    public productoRAM (String nombre, double precio, String marca, String tipoComponente, int tamanioDeMemoria) {
        // La marca se fija en "Intel"
        super(nombre, precio, marca, "RAM");
        this.tamanioDeMemoria = tamanioDeMemoria;
    }

    @Override
    public String getDescripcion() {
        return nombre + "de marca:" + marca + "con " + tamanioDeMemoria +"tamanio de memoria"  + " PRECIO:" + precio;
    }

    public double getPrecio(){

        return precio;
    }

    public int getTamanioDeMemoria(){

        return tamanioDeMemoria;
    }
}