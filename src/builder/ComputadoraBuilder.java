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
 * Interfaz que define el contrato para construir instancias de {@link src.Computadora}.
 * Implementa el patrón Builder, permitiendo la creación paso a paso de componentes individuales
 * y la obtención del objeto final completo.
 */
public interface ComputadoraBuilder {

    /**
     * Agrega un procesador (CPU) al ensamblaje de la computadora.
     *
     * @param cpu instancia de {@link CPU} a incorporar
     */
    void agregarCPU(CPU cpu);

    /**
     * Agrega un módulo de memoria RAM al ensamblaje de la computadora.
     *
     * @param modulo instancia de {@link RAM} a incorporar
     */
    void agregarRAM(RAM modulo);

    /**
     * Agrega una tarjeta gráfica (GPU) al ensamblaje de la computadora.
     *
     * @param gpu instancia de {@link GPU} a incorporar
     */
    void agregarGPU(GPU gpu);

    /**
     * Agrega un dispositivo de almacenamiento (HDD/SSD) al ensamblaje.
     *
     * @param disco instancia de {@link Almacenamiento} a incorporar
     */
    void agregarDisco(Almacenamiento disco);

    /**
     * Agrega una fuente de poder (PSU) al ensamblaje de la computadora.
     *
     * @param fuente instancia de {@link FuenteDePoder} a incorporar
     */
    void agregarFuente(FuenteDePoder fuente);

    /**
     * Agrega la placa base (Motherboard) al ensamblaje de la computadora.
     *
     * @param motherboard instancia de {@link Motherboard} a incorporar
     */
    void agregarMotherboard(Motherboard motherboard);

    /**
     * Agrega un gabinete al ensamblaje de la computadora.
     *
     * @param gabinete instancia de {@link Gabinete} a incorporar
     */
    void agregarGabinete(Gabinete gabinete);

    /**
     * Devuelve la instancia de {@link src.Computadora} resultante tras
     * haber agregado todos los componentes deseados.
     *
     * @return objeto completamente ensamblado de tipo Computadora
     */
    Computadora obtenerComputadora();
}
