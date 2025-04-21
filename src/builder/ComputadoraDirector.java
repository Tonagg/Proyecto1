package src.builder;

import java.util.List;
import src.Computadora;
import src.factory.CPU;
import src.factory.RAM;
import src.factory.GPU;
import src.factory.Almacenamiento;
import src.factory.FuenteDePoder;
import src.factory.Motherboard;
import src.factory.Gabinete;

/**
 * Director que encapsula la construcción de instancias de {@link Computadora}
 * siguiendo el patrón Builder. Define el orden y la lógica para armar
 * un equipo completo utilizando un {@link ComputadoraBuilder}.
 */
public class ComputadoraDirector {

    /**
     * Builder utilizado para ensamblar paso a paso los componentes.
     */
    private final ComputadoraBuilder builder;

    /**
     * Crea un nuevo director con el builder especificado.
     *
     * @param builder instancia de {@link ComputadoraBuilder} que gestionará el ensamblaje
     */
    public ComputadoraDirector(ComputadoraBuilder builder) {
        this.builder = builder;
    }

    /**
     * Ejecuta el proceso de construcción de la computadora, agregando todos
     * los componentes en el orden correcto.
     *
     * @param cpu    procesador a instalar
     * @param ram    lista de módulos de memoria RAM a instalar
     * @param gpu    tarjeta gráfica a instalar
     * @param discos lista de dispositivos de almacenamiento a instalar
     * @param psu    fuente de poder a instalar
     * @param mb     placa base a instalar
     * @param case_  gabinete a instalar
     */
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

    /**
     * Obtiene la instancia de {@link Computadora} final construida por el builder.
     *
     * @return objeto completamente ensamblado de tipo Computadora
     */
    public Computadora obtenerComputadora() {
        return builder.obtenerComputadora();
    }
}
