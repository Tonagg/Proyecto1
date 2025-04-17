

public interface ComputadoraBuilder {
    void agregarCPU(CPU cpu);
    void agregarRAM(RAM modulo);
    void agregarGPU(GPU gpu);
    void agregarDisco(Almacenamiento disco);
    void agregarFuente(FuenteDePoder fuente);
    void agregarMotherboard(Motherboard motherboard);
    void agregarGabinete(Gabinete gabinete);
    Computadora obtenerComputadora();
}
