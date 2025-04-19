/* src/factory/GabineteCase.java */
package src.factory;

public final class ProductoGabinete extends Componente implements Gabinete {

    public ProductoGabinete(String modelo, double precio, Marca marca) {
        super(modelo, precio, marca, TipoComponente.CASE);
    }

    @Override public String getDescripcion() { return super.toString(); }
}
