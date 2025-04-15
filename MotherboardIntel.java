
public class MotherboardIntel extends Componente implements Motherboard {
    private int cantidadNucleos;

    public CPUIntel(String nombre, double precio, String marca, String tipoComponente, String tipoDeChipSet) {
        // La marca se fija en "Intel"
        super(nombre, precio, marca, "Motherboard");
        this.tipoDeChipSet = tipoDeChipSet;
    }

    @Override
    public String getDescripcion() {
        return nombre + " (" + tipoDeChipSet + " Tipo de chipset, " + marca + ")";
    }
}