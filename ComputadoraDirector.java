// 4) Director que orquesta el Builder personalizado
import java.util.List;

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
