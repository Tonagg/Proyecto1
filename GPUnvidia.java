

public class GPUIntel extends Componente implements GPU {
    private int cantidadNucleos;

    public CPUIntel(String nombre, double precio, String marca, String tipoComponente, String tipoDeMemoria) {
        // La marca se fija en "Intel"
        super(nombre, precio, "Nvidia", "GPU");
        this.tipoDeMemoria = tipoDeMemoria;
    }

    @Override
    public String getDescripcion() {
        return nombre + " (" + tipoDeMemoria + " Tipo de Memoria, " + marca + ")";
    }
}