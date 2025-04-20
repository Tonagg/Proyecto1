package src.mvc;

import java.util.List;
import java.util.stream.Collectors;

import src.Computadora;
import src.Ticket;
import src.builder.ComputadoraDirector;
import src.builder.ComputadoraPersonalizadaBuilder;
import src.builder.ComputadoraPrearmadaBuilder;
import src.factory.ComponenteFactory;
import src.factory.RAM;
import src.factory.CPU;
import src.factory.GPU;
import src.factory.Almacenamiento;
import src.factory.FuenteDePoder;
import src.factory.Motherboard;
import src.factory.Gabinete;
import src.compatibilidad.CompatibilidadManager;

/**
 * Modelo de dominio: construye computadoras, verifica compatibilidad
 * y genera tickets.  La fábrica de componentes (Intel/Nvidia, AMD, …)
 * se inyecta por el constructor para mantener bajo acoplamiento.
 */
public class Model {

    /* -------- dependencias inyectadas -------- */
    private final ComponenteFactory factory;
    private final CompatibilidadManager compatManager = new CompatibilidadManager();

    /* -------- builder/director internos ------ */
    private ComputadoraDirector director;
    private Computadora          computadoraActual;

    public Model(ComponenteFactory factory) {
        this.factory = factory;
    }

    /**
     * Permite al Controller acceder al catálogo completo de componentes.
     */
    public ComponenteFactory getFactory() {
        return factory;
    }

    /**
     * Guarda la PC que el Controller va montando paso a paso.
     */
    public void setComputadoraActual(Computadora pc) {
        this.computadoraActual = pc;
    }

    /* ----------------------------------------------------------
       Construcción de PC personalizada (Builder + Director)
       ---------------------------------------------------------- */
    public Computadora crearComputadoraPersonalizada() {
        ComputadoraPersonalizadaBuilder builder = new ComputadoraPersonalizadaBuilder();
        director = new ComputadoraDirector(builder);

        CPU cpu = factory.catalogoCPU().stream().findFirst().orElseThrow();
        List<RAM> rams = factory.catalogoRAM().stream().limit(2).collect(Collectors.toList());
        GPU gpu = factory.catalogoGPU().stream().findFirst().orElseThrow();
        List<Almacenamiento> discos = List.of(
            factory.catalogoStorage().stream().findFirst().orElseThrow()
        );
        FuenteDePoder fuente = factory.catalogoPSU().stream().findFirst().orElseThrow();
        Motherboard mb = factory.catalogoMotherboard().stream().findFirst().orElseThrow();
        Gabinete gab = factory.catalogoGabinetes().stream().findFirst().orElseThrow();

        director.construirComputadora(cpu, rams, gpu, discos, fuente, mb, gab);
        computadoraActual = director.obtenerComputadora();
        return computadoraActual;
    }

    /* ----------------------------------------------------------
       Construcción de PC pre‑armada (Builder específico)
       ---------------------------------------------------------- */
    public Computadora crearComputadoraPrearmada(String modelo) {
        ComputadoraPrearmadaBuilder builder = 
            new ComputadoraPrearmadaBuilder(factory, modelo);
        computadoraActual = builder.obtenerComputadora();
        return computadoraActual;
    }

    /* ---------------- compatibilidad ---------------- */
    /**
     * Devuelve la lista de conflictos actuales.
     */
    public List<String> verificarCompatibilidad() {
        return compatManager.verificar(computadoraActual);
    }

    /**
     * Aplica adaptaciones automáticamente y devuelve notas.
     */
    public String adaptar() {
        return compatManager.adaptar(computadoraActual);
    }

    /* ---------------- ticket ---------------- */
    public Ticket generarTicket(String notas) {
        return new Ticket(computadoraActual, notas);
    }
}
