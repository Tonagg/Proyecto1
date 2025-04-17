

public class ComputadoraPersonalizadaBuilder implements ComputadoraBuilder {
    private Computadora computadora;

    public ComputadoraPersonalizadaBuilder() {
        computadora = new Computadora();
    }

    @Override
    public void agregarCPU(CPU cpu) {
        computadora.setCpu(cpu);
    }

    @Override
    public void agregarRAM(RAM modulo) {
        computadora.agregarRAM(modulo);
    }

    @Override
    public void agregarGPU(GPU gpu) {
        computadora.setGpu(gpu);
    }

    @Override
    public void agregarDisco(Almacenamiento disco) {
        computadora.agregarDisco(disco);
    }

    @Override
    public void agregarFuente(FuenteDePoder fuente) {
        computadora.setFuente(fuente);
    }

    @Override
    public void agregarMotherboard(Motherboard motherboard) {
        computadora.setMotherboard(motherboard);
    }

    @Override
    public void agregarGabinete(Gabinete gabinete) {
        computadora.setGabinete(gabinete);
    }

    @Override
    public Computadora obtenerComputadora() {
        return computadora;
    }
}
