

public class CPUIntel extends Componente implements CPU {
    private int cantidadNucleos;

    public CPUIntel(String nombre, double precio, String marca, String tipoComponente, int cantidadNucleos) {
        // La marca se fija en "Intel"
        super(nombre, precio, "Intel", "CPU");
        this.cantidadNucleos = cantidadNucleos;
    }

    @Override
    public String getDescripcion() {
        return nombre + " (" + cantidadNucleos + " núcleos, " + marca + ")";
    }
}
