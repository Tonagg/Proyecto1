package src.factory;

import java.util.*;
import java.util.function.Function;

import src.adapter.AdaptadorGPUNvidia;
import src.adapter.AdaptadorMotherboardIntel;

/**
 * Fábrica concreta para componentes AMD, implementando el patrón Abstract Factory.
 * Internamente utiliza una {@link IntelNvidiaFactory} para delegar creación
 * de componentes Intel/Nvidia y adapta aquellos necesarios para AMD.
 */
public final class AmdFactory implements ComponenteFactory {

    /** Fábrica Intel+Nvidia delegada para la creación base de componentes. */
    private final IntelNvidiaFactory intel = new IntelNvidiaFactory();

    /** Mapa de modelos de CPU AMD disponibles. */
    private static final Map<String, CPU> CPU_MAP = Map.ofEntries(
        Map.entry("Ryzen 5 5600G",   new CPUAMD("Ryzen 5 5600G",   2449,  6)),
        Map.entry("Ryzen 5 7600X",   new CPUAMD("Ryzen 5 7600X",   4789,  6)),
        Map.entry("Ryzen 7 7700X",   new CPUAMD("Ryzen 7 7700X",   7043,  8)),
        Map.entry("Ryzen 9 7950X3D", new CPUAMD("Ryzen 9 7950X3D",15059, 16))
    );

    // --- Peticiones puntuales ---

    /**
     * Crea una CPU AMD del modelo especificado.
     *
     * @param modelo clave del modelo en el catálogo AMD
     * @return instancia de {@link CPU} AMD
     * @throws IllegalArgumentException si el modelo no existe
     */
    @Override
    public CPU cpu(String modelo) {
        return get(CPU_MAP, modelo);
    }

    /**
     * Crea una GPU adaptada de la fábrica Intel/Nvidia para AMD.
     *
     * @param modelo clave del modelo Nvidia
     * @return instancia de {@link GPU} adaptada para AMD
     */
    @Override
    public GPU gpu(String modelo) {
        return new AdaptadorGPUNvidia(intel.gpu(modelo));
    }

    /**
     * Crea una placa base adaptada de la fábrica Intel/Nvidia para AMD.
     *
     * @param m clave del modelo Intel/MSI
     * @return instancia de {@link Motherboard} adaptada para AMD
     */
    @Override
    public Motherboard motherboard(String m) {
        return new AdaptadorMotherboardIntel(intel.motherboard(m));
    }

    /** Delegación a la fábrica Intel/Nvidia para módulos RAM. */
    @Override public RAM ram(int gb, Marca marca) { return intel.ram(gb, marca); }
    /** Delegación para fuentes de poder. */
    @Override public FuenteDePoder fuente(String modeloClave) { return intel.fuente(modeloClave); }
    /** Delegación para SSD. */
    @Override public Almacenamiento ssd(int gb) { return intel.ssd(gb); }
    /** Delegación para HDD. */
    @Override public Almacenamiento hdd(int gb) { return intel.hdd(gb); }
    /** Delegación para gabinetes. */
    @Override public Gabinete gabinete(String m) { return intel.gabinete(m); }

    // --- Catálogos completos ---

    @Override public Collection<CPU> catalogoCPU() {
        return CPU_MAP.values();
    }
    @Override public Collection<GPU> catalogoGPU() {
        return wrap(intel.catalogoGPU(), AdaptadorGPUNvidia::new);
    }
    @Override public Collection<RAM> catalogoRAM() {
        return intel.catalogoRAM();
    }
    @Override public Collection<Motherboard> catalogoMotherboard() {
        return wrap(intel.catalogoMotherboard(), AdaptadorMotherboardIntel::new);
    }
    @Override public Collection<FuenteDePoder> catalogoPSU() {
        return intel.catalogoPSU();
    }
    @Override public Collection<Almacenamiento> catalogoStorage() {
        return intel.catalogoStorage();
    }
    @Override public Collection<Gabinete> catalogoGabinetes() {
        return intel.catalogoGabinetes();
    }

    /**
     * Verifica compatibilidad nativa AMD-AMD (siempre true, pues adapta internamente).
     *
     * @return siempre {@code true}
     */
    @Override
    public boolean compatibles(CPU cpu, Motherboard mb) {
        return true;
    }

    // --- Métodos auxiliares ---

    /**
     * Obtiene un valor del mapa o lanza excepción si no existe.
     *
     * @param map mapa de elementos
     * @param key clave a buscar
     * @param <K> tipo de clave
     * @param <V> tipo de valor
     * @return valor asociado a la clave
     * @throws IllegalArgumentException si no existe la clave
     */
    private static <K, V> V get(Map<K, V> map, K key) {
        V v = map.get(key);
        if (v == null) throw new IllegalArgumentException("No existe: " + key);
        return v;
    }

    /**
     * Convierte una colección usando la función de mapeo indicada.
     *
     * @param src colección original
     * @param f función de mapeo
     * @param <T> tipo de elementos originales
     * @param <R> tipo de elementos resultantes
     * @return colección resultante mapeada
     */
    private static <T, R> Collection<R> wrap(Collection<T> src, Function<T, R> f) {
        return src.stream().map(f).toList();
    }
}
