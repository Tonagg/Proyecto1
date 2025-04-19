/* src/factory/CPUIntel.java */
package src.factory;

public final class CPUIntel extends Componente implements CPU {

    private final int nucleos;

    public CPUIntel(String modelo, double precio, int nucleos) {
        super(modelo, precio, Marca.INTEL, TipoComponente.CPU);
        this.nucleos = nucleos;
    }

    @Override public String getDescripcion() {
        return "%s | %d núcleos".formatted(super.toString(), nucleos);
    }
}
