/* src/factory/Drive.java */
package src.factory;

public final class ProductoAlmacenamiento extends Componente implements Almacenamiento {

    private final int  capacidadGB;
    private final boolean ssd;

    public ProductoAlmacenamiento(String modelo, double precio, Marca marca,
                 int capacidadGB, boolean ssd) {
        super(modelo, precio, marca, TipoComponente.STORAGE);
        this.capacidadGB = capacidadGB;
        this.ssd         = ssd;
    }

    @Override public int  getCapacidadGB() { return capacidadGB; }
    @Override public boolean esSSD()       { return ssd; }

    @Override public String getDescripcion() {
        return "%s | %d GB %s".formatted(
            super.toString(), capacidadGB, ssd ? "SSD" : "HDD");
    }
}
