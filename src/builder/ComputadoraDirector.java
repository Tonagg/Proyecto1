package src.builder;
// 4) Director que orquesta el Builder personalizado
import java.util.List;

import src.*;
import src.factory.Almacenamiento;
import src.factory.CPU;
import src.factory.FuenteDePoder;
import src.factory.GPU;
import src.factory.Gabinete;
import src.factory.Motherboard;
import src.factory.RAM;

public class ComputadoraDirector {
    private final ComputadoraBuilder builder;

    public ComputadoraDirector(ComputadoraBuilder builder) {
        this.builder = builder;
    }

    public void construirComputadora(
        CPU cpu,
        List<RAM> ramModules,
        GPU gpu,
        List<Almacenamiento> discos,
        FuenteDePoder fuente,
        Motherboard mb,
        Gabinete gab
    ) {
        builder.agregarCPU(cpu);
        ramModules.forEach(builder::agregarRAM);
        builder.agregarGPU(gpu);
        discos.forEach(builder::agregarDisco);
        builder.agregarFuente(fuente);
        builder.agregarMotherboard(mb);
        builder.agregarGabinete(gab);
    }

    public Computadora obtenerComputadora() {
        return builder.obtenerComputadora();
    }
}
