package src.builder;

import java.util.*;
import java.util.function.Consumer;

import src.Computadora;
import src.factory.CPU;
import src.factory.RAM;
import src.factory.GPU;
import src.factory.Almacenamiento;
import src.factory.FuenteDePoder;
import src.factory.Motherboard;
import src.factory.Gabinete;
import src.factory.ComponenteFactory;
import src.factory.Marca;

/**
 * Builder para computadoras pre‑armadas, implementando el patrón Builder.
 * Cada configuración está predefinida en un catálogo interno de lambdas
 * para evitar modificaciones directas al código al añadir nuevos modelos.
 */
public class ComputadoraPrearmadaBuilder implements ComputadoraBuilder {

    /**
     * Instancia de Computadora que se va construyendo.
     */
    private final Computadora pc = new Computadora();

    /**
     * Fábrica de componentes utilizada para generar los elementos del preset.
     */
    private final ComponenteFactory factory;

    /**
     * Mapa de nombres de configuración a lambdas que aplican cada preset.
     */
    private static final Map<String, Consumer<ComputadoraPrearmadaBuilder>> CONFIGS = new HashMap<>();

    static {
        // Presets Intel
        CONFIGS.put("gamer",   b -> b.configGamerIntel());
        CONFIGS.put("basica",  b -> b.configBasicaIntel());
        CONFIGS.put("estudio", b -> b.configEstudioIntel());
        CONFIGS.put("oficina", b -> b.configOficinaIntel());
        CONFIGS.put("render",  b -> b.configRenderIntel());
        // Presets AMD
        CONFIGS.put("gamer_amd",   b -> b.configGamerAmd());
        CONFIGS.put("basica_amd",  b -> b.configBasicaAmd());
        CONFIGS.put("estudio_amd", b -> b.configEstudioAmd());
        CONFIGS.put("oficina_amd", b -> b.configOficinaAmd());
        CONFIGS.put("render_amd",  b -> b.configRenderAmd());
    }

    /**
     * Crea un builder de computadora pre‑armada aplicando el preset indicado.
     *
     * @param f       fábrica de componentes a utilizar
     * @param modelo  identificador del preset (e.g., "Gamer", "Basica", etc.)
     * @throws IllegalArgumentException si el modelo no existe en el catálogo
     */
    public ComputadoraPrearmadaBuilder(ComponenteFactory f, String modelo) {
        this.factory = f;
        String key = modelo.toLowerCase();
        if (f instanceof src.factory.AmdFactory) key += "_amd";
        Consumer<ComputadoraPrearmadaBuilder> cfg = CONFIGS.get(key);
        if (cfg == null) {
            throw new IllegalArgumentException("Modelo desconocido: " + modelo);
        }
        cfg.accept(this);
    }

    // ===== Presets Intel =====

    /** Configuración pre‑armada "Gamer" con componentes Intel. */
    private void configGamerIntel() {
        pc.setCpu(factory.cpu("Core i7-13700K"));
        addRam(32, 4, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 4080"));
        pc.agregarDisco(factory.ssd(500));
        pc.setFuente(factory.fuente("EVGA1000"));
        pc.setMotherboard(factory.motherboard("ROG Maximus Z790 Hero"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    /** Configuración pre‑armada "Basica" con componentes Intel. */
    private void configBasicaIntel() {
        pc.setCpu(factory.cpu("Core i3-13100"));
        addRam(8, 1, Marca.KINGSTON);
        pc.setGpu(factory.gpu("GTX 1660"));
        pc.agregarDisco(factory.hdd(500));
        pc.setFuente(factory.fuente("EVGA800"));
        pc.setMotherboard(factory.motherboard("TUF Gaming B760-Plus WIFI D4"));
        pc.setGabinete(factory.gabinete("Yeyian Lancer"));
    }

    /** Configuración pre‑armada "Estudio" con componentes Intel. */
    private void configEstudioIntel() {
        pc.setCpu(factory.cpu("Core i5-13600K"));
        addRam(16, 2, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 3060"));
        pc.agregarDisco(factory.ssd(1000));
        pc.setFuente(factory.fuente("EVGA1500"));
        pc.setMotherboard(factory.motherboard("MEG Z790 Godlike"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    /** Configuración pre‑armada "Oficina" con componentes Intel. */
    private void configOficinaIntel() {
        pc.setCpu(factory.cpu("Core i3-13100"));
        addRam(8, 2, Marca.KINGSTON);
        pc.setGpu(factory.gpu("RTX 3060"));
        pc.agregarDisco(factory.ssd(500));
        pc.setFuente(factory.fuente("CORSAIR800"));
        pc.setMotherboard(factory.motherboard("TUF Gaming B760-Plus WIFI D4"));
        pc.setGabinete(factory.gabinete("Yeyian Lancer"));
    }

    /** Configuración pre‑armada "Render" con componentes Intel. */
    private void configRenderIntel() {
        pc.setCpu(factory.cpu("Core i9-13900K"));
        addRam(32, 2, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 4090"));
        pc.agregarDisco(factory.ssd(2000));
        pc.setFuente(factory.fuente("EVGA1500"));
        pc.setMotherboard(factory.motherboard("MEG Z790 Godlike"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    // ===== Presets AMD =====

    /** Configuración pre‑armada "Gamer" con componentes AMD. */
    private void configGamerAmd() {
        pc.setCpu(factory.cpu("Ryzen 7 7700X"));
        addRam(32, 4, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 4080"));
        pc.agregarDisco(factory.ssd(500));
        pc.setFuente(factory.fuente("EVGA1000"));
        pc.setMotherboard(factory.motherboard("ROG Maximus Z790 Hero"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    /** Configuración pre‑armada "Basica" con componentes AMD. */
    private void configBasicaAmd() {
        pc.setCpu(factory.cpu("Ryzen 5 5600G"));
        addRam(8, 1, Marca.KINGSTON);
        pc.setGpu(factory.gpu("GTX 1660"));
        pc.agregarDisco(factory.hdd(500));
        pc.setFuente(factory.fuente("EVGA800"));
        pc.setMotherboard(factory.motherboard("TUF Gaming B760-Plus WIFI D4"));
        pc.setGabinete(factory.gabinete("Yeyian Lancer"));
    }

    /** Configuración pre‑armada "Estudio" con componentes AMD. */
    private void configEstudioAmd() {
        pc.setCpu(factory.cpu("Ryzen 5 7600X"));
        addRam(16, 2, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 3060"));
        pc.agregarDisco(factory.ssd(1000));
        pc.setFuente(factory.fuente("EVGA1500"));
        pc.setMotherboard(factory.motherboard("MEG Z790 Godlike"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    /** Configuración pre‑armada "Oficina" con componentes AMD. */
    private void configOficinaAmd() {
        pc.setCpu(factory.cpu("Ryzen 5 5600G"));
        addRam(16, 2, Marca.KINGSTON);
        pc.setGpu(factory.gpu("RTX 3060"));
        pc.agregarDisco(factory.ssd(500));
        pc.setFuente(factory.fuente("CORSAIR800"));
        pc.setMotherboard(factory.motherboard("TUF Gaming B760-Plus WIFI D4"));
        pc.setGabinete(factory.gabinete("Yeyian Lancer"));
    }

    /** Configuración pre‑armada "Render" con componentes AMD. */
    private void configRenderAmd() {
        pc.setCpu(factory.cpu("Ryzen 9 7950X3D"));
        addRam(32, 2, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 4090"));
        pc.agregarDisco(factory.ssd(2000));
        pc.setFuente(factory.fuente("EVGA1500"));
        pc.setMotherboard(factory.motherboard("MEG Z790 Godlike"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    /**
     * Agrega múltiples módulos de RAM a la computadora.
     *
     * @param gb       capacidad en GB de cada módulo
     * @param cantidad número de módulos a agregar
     * @param marca    marca de los módulos de RAM
     */
    private void addRam(int gb, int cantidad, Marca marca) {
        RAM modulo = factory.ram(gb, marca);
        for (int i = 0; i < cantidad; i++) {
            pc.agregarRAM(modulo);
        }
    }

    // ===== Métodos del contrato ComputadoraBuilder =====

    @Override public void agregarCPU(CPU c)               { }
    @Override public void agregarRAM(RAM r)               { }
    @Override public void agregarGPU(GPU g)               { }
    @Override public void agregarDisco(Almacenamiento d)   { }
    @Override public void agregarFuente(FuenteDePoder f)  { }
    @Override public void agregarMotherboard(Motherboard m){ }
    @Override public void agregarGabinete(Gabinete g)     { }

    /**
     * Devuelve la computadora pre‑armada resultante.
     *
     * @return instancia de {@link Computadora} ensamblada con el preset solicitado
     */
    @Override
    public Computadora obtenerComputadora() {
        return pc;
    }
}
