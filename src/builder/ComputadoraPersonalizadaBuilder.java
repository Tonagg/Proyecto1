package src.builder;


import src.*;
import src.factory.Almacenamiento;
import src.factory.CPU;
import src.factory.FuenteDePoder;
import src.factory.GPU;
import src.factory.Gabinete;
import src.factory.Motherboard;
import src.factory.RAM;

// 2) Builder para PC Personalizada
public class ComputadoraPersonalizadaBuilder implements ComputadoraBuilder {
    private final Computadora computadora;

    public ComputadoraPersonalizadaBuilder() {
        this.computadora = new Computadora();
    }

    @Override
    public void agregarCPU(CPU cpu) {
        computadora.setCpu(cpu);
    }

    @Override
    public void agregarRAM(RAM ram) {
        computadora.agregarRAM(ram);
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
    public void agregarMotherboard(Motherboard mb) {
        computadora.setMotherboard(mb);
    }

    @Override
    public void agregarGabinete(Gabinete gab) {
        computadora.setGabinete(gab);
    }

    @Override
    public Computadora obtenerComputadora() {
        return computadora;
    }
}
