/* src/factory/FuentePoder.java */
package src.factory;

public final class ProductoFuenteDePoder extends Componente implements FuenteDePoder {

    private final int potenciaW;

    public ProductoFuenteDePoder(String modelo, double precio, Marca marca, int potenciaW) {
        super(modelo, precio, marca, TipoComponente.PSU);
        this.potenciaW = potenciaW;
    }

    @Override public int getPotenciaW() { return potenciaW; }

    @Override public String getDescripcion() {
        return "%s | %d W".formatted(super.toString(), potenciaW);
    }
}
