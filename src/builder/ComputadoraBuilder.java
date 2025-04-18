package src.builder;

import src.*;
import src.factory.Almacenamiento;
import src.factory.CPU;
import src.factory.FuenteDePoder;
import src.factory.GPU;
import src.factory.Gabinete;
import src.factory.Motherboard;
import src.factory.RAM;

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
