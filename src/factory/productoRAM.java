/* src/factory/RAMModulo.java */
package src.factory;

public final class ProductoRAM extends Componente implements RAM {

    private final int capacidadGB;

    public ProductoRAM(String modelo, double precio, Marca marca, int capacidadGB) {
        super(modelo, precio, marca, TipoComponente.RAM);
        this.capacidadGB = capacidadGB;
    }

    @Override public int getCapacidadGB() { return capacidadGB; }

    @Override public String getDescripcion() {
        return "%s | %d GB".formatted(super.toString(), capacidadGB);
    }
}
