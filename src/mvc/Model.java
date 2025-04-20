/* ────── src/mvc/Model.java ────── */
package src.mvc;

import java.util.List;
import java.util.stream.Collectors;

import src.Computadora;
import src.Ticket;
import src.builder.*;
import src.compatibilidad.CompatibilidadManager;
import src.factory.*;

/**
 *   Modelo de dominio.
 *
 *   • Construye equipos personalizados o pre‑armados  
 *   • Verifica / adapta compatibilidad a través de {@link CompatibilidadManager}  
 *   • Genera el {@link Ticket} final
 */
public class Model {

    /* ──────────── dependencias inyectadas ──────────── */
    private final ComponenteFactory     factory;
    private final CompatibilidadManager compatManager = new CompatibilidadManager();

    /* ──────────── estado interno ──────────── */
    private Computadora computadoraActual;

    public Model(ComponenteFactory factory) {
        this.factory = factory;
    }

    /* =================================================
       =================  C A T Á L O G O S  =============
       ================================================= */
    public ComponenteFactory getFactory() { return factory; }

    /* =================================================
       ===========  CONSTRUCCIÓN DE COMPUTADORAS =========
       ================================================= */

    /** Builder ‘vacío’ para que el Controller añada piezas una a una. */
    public ComputadoraPersonalizadaBuilder nuevoBuilderPersonalizado() {
        ComputadoraPersonalizadaBuilder b = new ComputadoraPersonalizadaBuilder();
        computadoraActual = b.obtenerComputadora();
        return b;
    }

    /** Crea de golpe una configuración pre‑armada y la deja como “actual”. */
    public Computadora crearComputadoraPrearmada(String modelo) {
        ComputadoraPrearmadaBuilder b = new ComputadoraPrearmadaBuilder(factory, modelo);
        computadoraActual = b.obtenerComputadora();
        return computadoraActual;
    }

    /* =================================================
       ==============  COMPATIBILIDAD  ==================
       ================================================= */

    /** PC que el Controller va montando paso a paso. */
    public void setComputadoraActual(Computadora pc) { this.computadoraActual = pc; }

    public Computadora getComputadoraActual()        { return computadoraActual; }

    /** @return <code>true</code> si no hay conflictos pendientes. */
    public boolean esCompatible() {
        return compatManager.verificar(computadoraActual).isEmpty();
    }

    /** Lista legible de conflictos encontrados. */
    public List<String> verificarCompatibilidad() {
        return compatManager.verificar(computadoraActual);
    }

    /**
     * Aplica todos los adaptadores posibles
     * y devuelve un texto con las acciones tomadas.
     */
    public String adaptar() {
        return compatManager.adaptar(computadoraActual);
    }

    /* =================================================
       ===============  T I C K E T  ====================
       ================================================= */
    public Ticket generarTicket(String notasExtras) {
        return new Ticket(computadoraActual, notasExtras);
    }

    /* =================================================
       ===========  MÉTODOS DE PRUEBA / UTIL  ============
       ================================================= */

    /** Construye una PC de ejemplo totalmente funcional (útil para tests). */
    public Computadora demoPersonalizadaMinima() {
        ComputadoraPersonalizadaBuilder b = new ComputadoraPersonalizadaBuilder();
        ComputadoraDirector d = new ComputadoraDirector(b);

        CPU  cpu  = factory.catalogoCPU().iterator().next();
        GPU  gpu  = factory.catalogoGPU().iterator().next();
        RAM  ram  = factory.catalogoRAM().iterator().next();
        Motherboard mb = factory.catalogoMotherboard().iterator().next();
        FuenteDePoder psu = factory.catalogoPSU().iterator().next();
        Almacenamiento ssd = factory.catalogoStorage().iterator().next();
        Gabinete case_ = factory.catalogoGabinetes().iterator().next();

        d.construirComputadora(
            cpu,
            List.of(ram),
            gpu,
            List.of(ssd),
            psu,
            mb,
            case_
        );

        computadoraActual = d.obtenerComputadora();
        return computadoraActual;
    }
}
