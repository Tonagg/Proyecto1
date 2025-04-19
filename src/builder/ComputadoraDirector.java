/* src/builder/ComputadoraDirector.java */
package src.builder;

import java.util.List;
import src.Computadora;
import src.factory.*;

public class ComputadoraDirector {

    private final ComputadoraBuilder builder;

    public ComputadoraDirector(ComputadoraBuilder builder) {
        this.builder = builder;
    }

    public void construirComputadora(
            CPU cpu, List<RAM> ram,
            GPU gpu, List<Almacenamiento> discos,
            FuenteDePoder psu, Motherboard mb, Gabinete case_) {

        builder.agregarCPU(cpu);
        ram.forEach(builder::agregarRAM);
        builder.agregarGPU(gpu);
        discos.forEach(builder::agregarDisco);
        builder.agregarFuente(psu);
        builder.agregarMotherboard(mb);
        builder.agregarGabinete(case_);
    }

    public Computadora obtenerComputadora() {
        return builder.obtenerComputadora();
    }
}
