/* src/factory/CPUAMD.java */
package src.factory;

public final class CPUAMD extends Componente implements CPU {

    private final int nucleos;

    public CPUAMD(String modelo, double precio, int nucleos) {
        super(modelo, precio, Marca.AMD, TipoComponente.CPU);
        this.nucleos = nucleos;
    }

    @Override public String getDescripcion() {
        return "%s | %d núcleos".formatted(super.toString(), nucleos);
    }
}
