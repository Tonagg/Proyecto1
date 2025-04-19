/* src/builder/ComputadoraPersonalizadaBuilder.java */
package src.builder;

import src.Computadora;
import src.factory.*;

public class ComputadoraPersonalizadaBuilder implements ComputadoraBuilder {

    private final Computadora pc = new Computadora();

    @Override public void agregarCPU       (CPU c)               { pc.setCpu(c); }
    @Override public void agregarRAM       (RAM r)               { pc.agregarRAM(r); }
    @Override public void agregarGPU       (GPU g)               { pc.setGpu(g); }
    @Override public void agregarDisco     (Almacenamiento d)    { pc.agregarDisco(d); }
    @Override public void agregarFuente    (FuenteDePoder f)     { pc.setFuente(f); }
    @Override public void agregarMotherboard(Motherboard m)      { pc.setMotherboard(m); }
    @Override public void agregarGabinete  (Gabinete g)          { pc.setGabinete(g); }

    @Override public Computadora obtenerComputadora()            { return pc; }
}
