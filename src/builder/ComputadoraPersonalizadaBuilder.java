package src.builder;

import src.Computadora;
import src.factory.CPU;
import src.factory.RAM;
import src.factory.GPU;
import src.factory.Almacenamiento;
import src.factory.FuenteDePoder;
import src.factory.Motherboard;
import src.factory.Gabinete;

/**
 * Builder concreto que implementa el patrón Builder para la creación de computadoras
 * personalizadas. Permite ensamblar una instancia de {@link src.Computadora} pieza por pieza.
 */
public class ComputadoraPersonalizadaBuilder implements ComputadoraBuilder {

    /**
     * Instancia interna de la computadora que se va ensamblando.
     */
    private final Computadora pc = new Computadora();

    /**
     * Agrega la CPU seleccionada a la computadora.
     *
     * @param c procesador {@link CPU} a instalar
     */
    @Override
    public void agregarCPU(CPU c) {
        pc.setCpu(c);
    }

    /**
     * Agrega un módulo de RAM a la computadora.
     *
     * @param r módulo de {@link RAM} a instalar
     */
    @Override
    public void agregarRAM(RAM r) {
        pc.agregarRAM(r);
    }

    /**
     * Agrega la GPU seleccionada a la computadora.
     *
     * @param g tarjeta gráfica {@link GPU} a instalar
     */
    @Override
    public void agregarGPU(GPU g) {
        pc.setGpu(g);
    }

    /**
     * Agrega un dispositivo de almacenamiento a la computadora.
     *
     * @param d unidad de {@link Almacenamiento} a instalar
     */
    @Override
    public void agregarDisco(Almacenamiento d) {
        pc.agregarDisco(d);
    }

    /**
     * Agrega la fuente de poder (PSU) a la computadora.
     *
     * @param f fuente de poder {@link FuenteDePoder} a instalar
     */
    @Override
    public void agregarFuente(FuenteDePoder f) {
        pc.setFuente(f);
    }

    /**
     * Agrega la placa base (Motherboard) a la computadora.
     *
     * @param m placa base {@link Motherboard} a instalar
     */
    @Override
    public void agregarMotherboard(Motherboard m) {
        pc.setMotherboard(m);
    }

    /**
     * Agrega el gabinete a la computadora.
     *
     * @param g gabinete {@link Gabinete} a instalar
     */
    @Override
    public void agregarGabinete(Gabinete g) {
        pc.setGabinete(g);
    }

    /**
     * Devuelve la instancia de {@link src.Computadora} construida con los componentes
     * agregados hasta el momento.
     *
     * @return computadora ensamblada
     */
    @Override
    public Computadora obtenerComputadora() {
        return pc;
    }
}
