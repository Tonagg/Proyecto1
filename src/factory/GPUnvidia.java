/* src/factory/GPUNvidia.java */
package src.factory;

public final class GPUNvidia extends Componente implements GPU {

    private final String tipoMemoria;

    public GPUNvidia(String modelo, double precio, String tipoMemoria) {
        super(modelo, precio, Marca.NVIDIA, TipoComponente.GPU);
        this.tipoMemoria = tipoMemoria;
    }

    @Override public String getDescripcion() {
        return "%s | memoria %s".formatted(super.toString(), tipoMemoria);
    }
}
