/* src/builder/ComputadoraBuilder.java */
package src.builder;

import src.Computadora;
import src.factory.*;

public interface ComputadoraBuilder {

    /* piezas individuales */
    void agregarCPU(CPU cpu);
    void agregarRAM(RAM modulo);
    void agregarGPU(GPU gpu);
    void agregarDisco(Almacenamiento disco);
    void agregarFuente(FuenteDePoder fuente);
    void agregarMotherboard(Motherboard motherboard);
    void agregarGabinete(Gabinete gabinete);

    /* resultado final */
    Computadora obtenerComputadora();
}
