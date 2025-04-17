
public class MotherboardIntel extends Componente implements Motherboard {
    private String tipoDeChipSet;

    public MotherboardIntel(String nombre, double precio, String marca, String tipoComponente, String tipoDeChipSet) {
        // La marca se fija en "Intel"
        super(nombre, precio, marca, "Motherboard");
        this.tipoDeChipSet = tipoDeChipSet;
    }

    @Override
    public String getDescripcion() {
        return nombre + "de marca:" + marca + "con " + tipoDeChipSet +"tipo de tipo de ChipSet"  + " PRECIO:" + precio;
    }
    public double getPrecio() {
        return precio;
    }
}