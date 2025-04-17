


public class CPUamd extends Componente implements CPU {
    private int cantidadNucleos;

     public CPUamd(String nombre, double precio, String marca, String tipoComponente, int cantidadNucleos) {
        // La marca es fija"Amd" y tambien el tipo de componente
        super(nombre, precio, "AMD", "CPU");
        this.cantidadNucleos = cantidadNucleos;
    }

    @Override
    public String getDescripcion() {
        return nombre + "de marca:" + marca + "con " + cantidadNucleos +"nucleos"  + " PRECIO:" + precio;
    }

    public double getPrecio() {
        return precio;
    }
}
