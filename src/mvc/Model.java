// src/mvc/Model.java
package src.mvc;

import java.util.List;

import src.*;
import src.builder.*;
import src.factory.*;
import src.compatibilidad.*;
import src.Ticket;

public class Model {

    /* -------- dependencias inyectadas -------- */
    private final ComponenteFactory factory;
    private VerificaCompatibilidad  verificador = new CompatibilidadFlexible(); // default

    /* -------- builder/director internos ------ */
    private ComputadoraDirector director;
    private Computadora          computadoraActual;   // la PC que se está armando

    public Model(ComponenteFactory factory) {
        this.factory = factory;
    }

    /* ---------------- construcción de PC ---------------- */

    public Computadora crearComputadoraPersonalizada() {
        ComputadoraPersonalizadaBuilder builder = new ComputadoraPersonalizadaBuilder();
        director = new ComputadoraDirector(builder);

        // ‑‑‑ EJEMPLO de selección (luego la Vista debería permitir elegir) ‑‑‑
        CPU cpu = factory.getCPUs().get(0);
        List<RAM> rams = factory.getRAM().subList(0, 2);
        GPU gpu = factory.getGPUs().get(0);
        List<Almacenamiento> discos = List.of(factory.getAlmacenamiento().get(0));
        FuenteDePoder fuente = factory.getFuente().get(0);
        Motherboard mb = factory.getMotherboard().get(0);
        Gabinete gab = factory.getGabinete().get(0);

        director.construirComputadora(cpu, rams, gpu, discos, fuente, mb, gab);
        computadoraActual = director.obtenerComputadora();
        return computadoraActual;
    }

    public Computadora crearComputadoraPrearmada(String modelo) {
        ComputadoraPrearmadaBuilder builder = new ComputadoraPrearmadaBuilder(factory, modelo);
        computadoraActual = builder.obtenerComputadora();
        return computadoraActual;
    }

    /* ---------------- compatibilidad ---------------- */

    public boolean esCompatible() {
        return verificador.esCompatible(computadoraActual);
    }

    /** Aplica adaptadores y devuelve notas */
    public String adaptar() {
        return verificador.adaptar(computadoraActual);
    }

    /* ---------------- ticket ---------------- */

    public Ticket generarTicket(String notas) {
        return new Ticket(computadoraActual, notas);
    }

    /* ---------------- setters opcionales -------------- */

    public void setVerificador(VerificaCompatibilidad v) {
        this.verificador = v;
    }
}
