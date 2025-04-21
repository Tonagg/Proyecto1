package src.mvc;

import java.util.List;
import src.Computadora;
import src.Ticket;
import src.builder.ComputadoraPersonalizadaBuilder;
import src.builder.ComputadoraPrearmadaBuilder;
import src.builder.ComputadoraDirector;
import src.compatibilidad.CompatibilidadManager;
import src.factory.*;

/**
 * Modelo de dominio que coordina la construcción de computadoras,
 * la verificación de compatibilidad y la generación de tickets.
 *
 * <p>Responsabilidades principales:
 * <ul>
 *   <li>Construir equipos personalizados o pre‑armados.</li>
 *   <li>Verificar y adaptar compatibilidad mediante {@link CompatibilidadManager}.</li>
 *   <li>Generar instancias de {@link Ticket} con el ensamblaje actual.</li>
 * </ul>
 * </p>
 */
public class Model {

    /** Fábrica de componentes inyectada para crear hardware. */
    private final ComponenteFactory factory;
    /** Gestor de reglas de compatibilidad. */
    private final CompatibilidadManager compatManager = new CompatibilidadManager();

    /** Computadora que actualmente se está ensamblando o ya fue ensamblada. */
    private Computadora computadoraActual;

    /**
     * Construye el modelo con la fábrica de componentes especificada.
     *
     * @param factory fábrica utilizada para generar componentes
     */
    public Model(ComponenteFactory factory) {
        this.factory = factory;
    }

    // =================================================
    // ================ CATÁLOGOS ======================
    // =================================================

    /**
     * Obtiene la fábrica de componentes bajo demanda.
     *
     * @return instancia de {@link ComponenteFactory}
     */
    public ComponenteFactory getFactory() {
        return factory;
    }

    // =================================================
    // =========== CONSTRUCCIÓN DE COMPUTADORAS ========
    // =================================================

    /**
     * Crea un builder vacío para ensamblar una computadora paso a paso.
     * Establece la computadoraActual al builder inicial.
     *
     * @return builder personalizado para construcción manual
     */
    public ComputadoraPersonalizadaBuilder nuevoBuilderPersonalizado() {
        ComputadoraPersonalizadaBuilder b = new ComputadoraPersonalizadaBuilder();
        computadoraActual = b.obtenerComputadora();
        return b;
    }

    /**
     * Construye de manera inmediata una computadora pre‑armada según el modelo.
     * Establece la computadoraActual a la instancia resultante.
     *
     * @param modelo nombre del preset pre‑armado
     * @return computadora pre‑armada construida
     */
    public Computadora crearComputadoraPrearmada(String modelo) {
        ComputadoraPrearmadaBuilder b = new ComputadoraPrearmadaBuilder(factory, modelo);
        computadoraActual = b.obtenerComputadora();
        return computadoraActual;
    }

    // =================================================
    // =============== COMPATIBILIDAD ===================
    // =================================================

    /**
     * Establece la computadora que se está montando.
     *
     * @param pc instancia de {@link Computadora}
     */
    public void setComputadoraActual(Computadora pc) {
        this.computadoraActual = pc;
    }

    /**
     * Obtiene la computadora en construcción o construida.
     *
     * @return instancia de {@link Computadora}
     */
    public Computadora getComputadoraActual() {
        return computadoraActual;
    }

    /**
     * Verifica si la computadora actual no tiene conflictos de compatibilidad.
     *
     * @return {@code true} si es compatible; {@code false} si hay conflictos
     */
    public boolean esCompatible() {
        return compatManager.verificar(computadoraActual).isEmpty();
    }

    /**
     * Verifica y devuelve mensajes de cualquier conflicto de compatibilidad.
     *
     * @return lista de descripciones de conflictos
     */
    public List<String> verificarCompatibilidad() {
        return compatManager.verificar(computadoraActual);
    }

    /**
     * Aplica todos los adaptadores posibles para resolver conflictos detectados.
     *
     * @return cadena con notas de adaptaciones aplicadas
     */
    public String adaptar() {
        return compatManager.adaptar(computadoraActual);
    }

    // =================================================
    // =============== TICKET ===========================
    // =================================================

    /**
     * Genera un ticket final con la configuración actual y notas opcionales.
     *
     * @param notasExtras texto adicional para el ticket
     * @return instancia de {@link Ticket}
     */
    public Ticket generarTicket(String notasExtras) {
        return new Ticket(computadoraActual, notasExtras);
    }

    // =================================================
    // == MÉTODOS DE PRUEBA / DEMO =====================
    // =================================================

    /**
     * Construye una computadora de ejemplo mínima completamente funcional.
     * Útil para pruebas unitarias o demostraciones.
     *
     * @return computadora de ejemplo ensamblada
     */
    public Computadora demoPersonalizadaMinima() {
        ComputadoraPersonalizadaBuilder b = new ComputadoraPersonalizadaBuilder();
        ComputadoraDirector d = new ComputadoraDirector(b);

        CPU cpu = factory.catalogoCPU().iterator().next();
        GPU gpu = factory.catalogoGPU().iterator().next();
        RAM ram = factory.catalogoRAM().iterator().next();
        Motherboard mb = factory.catalogoMotherboard().iterator().next();
        FuenteDePoder psu = factory.catalogoPSU().iterator().next();
        Almacenamiento ssd = factory.catalogoStorage().iterator().next();
        Gabinete gabinete = factory.catalogoGabinetes().iterator().next();

        d.construirComputadora(
            cpu,
            List.of(ram),
            gpu,
            List.of(ssd),
            psu,
            mb,
            gabinete
        );

        computadoraActual = d.obtenerComputadora();
        return computadoraActual;
    }
}