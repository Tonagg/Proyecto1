/* src/factory/MotherboardIntel.java */
package src.factory;

public final class MotherboardIntel extends Componente implements Motherboard {

    private final String chipset;

    public MotherboardIntel(String modelo, double precio, Marca marca, String chipset) {
        super(modelo, precio, marca, TipoComponente.MOTHERBOARD);
        this.chipset = chipset;
    }

    @Override public String getChipset() { return chipset; }

    @Override public String getDescripcion() {
        return "%s | chipset %s".formatted(super.toString(), chipset);
    }
}
