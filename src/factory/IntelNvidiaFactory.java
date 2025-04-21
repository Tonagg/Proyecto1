package src.factory;

import java.util.*;

/**
 * Fábrica concreta que suministra todos los componentes “oficiales”
 * de la alianza Intel + Nvidia (sin adaptadores).
 * <p>
 * Implementa el contrato {@link ComponenteFactory}, devolviendo:
 * <ul>
 *   <li>a petición puntual, la pieza solicitada;</li>
 *   <li>para la interfaz gráfica, los catálogos completos de componentes.</li>
 * </ul>
 * </p>
 */
public final class IntelNvidiaFactory implements ComponenteFactory {

    // ───────────────────────── Catálogos internos ─────────────────────────

    /**
     * Catálogo de CPUs Intel disponibles, mapeado por nombre.
     */
    private static final Map<String, CPU> CPU_MAP = Map.ofEntries(
        Map.entry("Core i3-13100",  new CPUIntel("Core i3-13100", 1750,  4)),
        Map.entry("Core i5-13600K", new CPUIntel("Core i5-13600K",2600, 14)),
        Map.entry("Core i7-13700K", new CPUIntel("Core i7-13700K",6200, 16)),
        Map.entry("Core i9-13900K", new CPUIntel("Core i9-13900K",5100, 24))
    );

    /**
     * Catálogo de GPUs Nvidia disponibles, mapeado por nombre.
     */
    private static final Map<String, GPU> GPU_MAP = Map.ofEntries(
        Map.entry("GTX 1660", new GPUNvidia("GTX 1660",4000, "GDDR6")),
        Map.entry("RTX 3060", new GPUNvidia("RTX 3060",5470, "GDDR6")),
        Map.entry("RTX 4070", new GPUNvidia("RTX 4070",13400, "GDDR6")),
        Map.entry("RTX 4080", new GPUNvidia("RTX 4080",43630, "GDDR6X")),
        Map.entry("RTX 4090", new GPUNvidia("RTX 4090",48720, "GDDR6X"))
    );

    /**
     * Catálogo de módulos de RAM ADATA y Kingston disponibles.
     */
    private static final Map<String, RAM> RAM_MAP = Map.ofEntries(
        Map.entry("ADATA-8",  new ProductoRAM("ADATA 8 GB",350, Marca.ADATA,8)),
        Map.entry("ADATA-16", new ProductoRAM("ADATA 16 GB",550, Marca.ADATA,16)),
        Map.entry("ADATA-32", new ProductoRAM("ADATA 32 GB",990, Marca.ADATA,32)),
        Map.entry("KING-8",   new ProductoRAM("Kingston 8 GB",400, Marca.KINGSTON,8)),
        Map.entry("KING-16",  new ProductoRAM("Kingston 16 GB",680,Marca.KINGSTON,16)),
        Map.entry("KING-32",  new ProductoRAM("Kingston 32 GB",1240,Marca.KINGSTON,32))
    );

    /**
     * Catálogo de motherboards Intel/ASUS/MSI disponibles.
     */
    private static final Map<String, Motherboard> MB_MAP = Map.ofEntries(
        Map.entry("ROG Maximus Z790 Hero",   new MotherboardIntel("ROG Maximus Z790 Hero",12110,Marca.ASUS,"Z790")),
        Map.entry("TUF Gaming B760-Plus WIFI D4", new MotherboardIntel("TUF Gaming B760-Plus WIFI D4",4650,Marca.ASUS,"B760")),
        Map.entry("MEG Z790 Godlike",        new MotherboardIntel("MEG Z790 Godlike",13600,Marca.MSI,"Z790")),
        Map.entry("MAG B760 Tomahawk WIFI DDR4", new MotherboardIntel("MAG B760 Tomahawk WIFI DDR4",4710,Marca.MSI,"B760"))
    );

    /**
     * Catálogo de fuentes de poder disponibles.
     */
    private static final Map<String, FuenteDePoder> PSU_MAP = Map.ofEntries(
        Map.entry("EVGA800",   new ProductoFuenteDePoder("EVGA 800 W",1920,Marca.EVGA,800)),
        Map.entry("EVGA1000",  new ProductoFuenteDePoder("EVGA 1000 W",4010,Marca.EVGA,1000)),
        Map.entry("EVGA1500",  new ProductoFuenteDePoder("EVGA 1500 W",7970,Marca.EVGA,1500)),
        Map.entry("CORSAIR800",  new ProductoFuenteDePoder("Corsair 800 W",2880,Marca.CORSAIR,800)),
        Map.entry("CORSAIR1000", new ProductoFuenteDePoder("Corsair 1000 W",3120,Marca.CORSAIR,1000)),
        Map.entry("CORSAIR1500", new ProductoFuenteDePoder("Corsair 1500 W",8600,Marca.CORSAIR,1500)),
        Map.entry("XPG800",      new ProductoFuenteDePoder("XPG 800 W",2460,Marca.XPG,800)),
        Map.entry("XPG1000",     new ProductoFuenteDePoder("XPG 1000 W",3220,Marca.XPG,1000)),
        Map.entry("XPG1500",     new ProductoFuenteDePoder("XPG 1500 W",3480,Marca.XPG,1500))
    );

    /**
     * Catálogo de dispositivos de almacenamiento SSD y HDD.
     */
    private static final Map<Integer, Almacenamiento> SSD_MAP = Map.ofEntries(
        Map.entry(500,  new ProductoAlmacenamiento("Kingston SSD 500 GB",820,Marca.KINGSTON,500,true)),
        Map.entry(1000, new ProductoAlmacenamiento("Kingston SSD 1 TB",1190,Marca.KINGSTON,1000,true)),
        Map.entry(2000, new ProductoAlmacenamiento("Kingston SSD 2 TB",2160,Marca.KINGSTON,2000,true)),
        Map.entry(4000, new ProductoAlmacenamiento("Kingston SSD 4 TB",5540,Marca.KINGSTON,4000,true))
    );
    private static final Map<Integer, Almacenamiento> HDD_MAP = Map.ofEntries(
        Map.entry(500, new ProductoAlmacenamiento("WD Blue 500 GB",1100,Marca.WD,500,false)),
        Map.entry(1000,new ProductoAlmacenamiento("WD Blue 1 TB",1300,Marca.WD,1000,false)),
        Map.entry(1001,new ProductoAlmacenamiento("Seagate Barracuda 1 TB",1150,Marca.SEAGATE,1000,false)),
        Map.entry(2000,new ProductoAlmacenamiento("Seagate Barracuda 2 TB",1600,Marca.SEAGATE,2000,false))
    );

    /**
     * Catálogo de gabinetes disponibles.
     */
    private static final Map<String, Gabinete> CASE_MAP = Map.of(
        "NZXT H6 Flow", new ProductoGabinete("NZXT H6 Flow",2180,Marca.NZXT),
        "Yeyian Lancer", new ProductoGabinete("Yeyian Lancer",1320,Marca.YEYIAN)
    );

    // ───────────────────────── Implementación ─────────────────────────

    /**
     * Crea una CPU según el modelo solicitado.
     *
     * @param modelo clave del modelo en el catálogo
     * @return instancia de {@link CPU}
     * @throws IllegalArgumentException si no existe el modelo
     */
    @Override
    public CPU cpu(String modelo) {
        return get(CPU_MAP, modelo);
    }

    /**
     * Crea una GPU según el modelo solicitado.
     *
     * @param modelo clave del modelo en el catálogo
     * @return instancia de {@link GPU}
     * @throws IllegalArgumentException si no existe el modelo
     */
    @Override
    public GPU gpu(String modelo) {
        return get(GPU_MAP, modelo);
    }

    /**
     * Crea una motherboard según el modelo solicitado.
     *
     * @param modelo clave del modelo en el catálogo
     * @return instancia de {@link Motherboard}
     * @throws IllegalArgumentException si no existe el modelo
     */
    @Override
    public Motherboard motherboard(String modelo) {
        return get(MB_MAP, modelo);
    }

    /**
     * Crea una fuente de poder según la clave solicitada.
     *
     * @param modeloClave clave del modelo en el catálogo
     * @return instancia de {@link FuenteDePoder}
     * @throws IllegalArgumentException si no existe la clave
     */
    @Override
    public FuenteDePoder fuente(String modeloClave) {
        return get(PSU_MAP, modeloClave.toUpperCase());
    }

    /**
     * Crea un módulo de RAM según especificaciones.
     *
     * @param gb    capacidad en GB
     * @param marca marca de la RAM
     * @return instancia de {@link RAM}
     * @throws IllegalArgumentException si no existe la combinación
     */
    @Override
    public RAM ram(int gb, Marca marca) {
        String key = (marca == Marca.ADATA ? "ADATA-" : "KING-") + gb;
        return get(RAM_MAP, key);
    }

    /**
     * Crea un dispositivo SSD según capacidad.
     *
     * @param gb capacidad en GB
     * @return instancia de {@link Almacenamiento} tipo SSD
     * @throws IllegalArgumentException si no existe
     */
    @Override
    public Almacenamiento ssd(int gb) {
        return get(SSD_MAP, gb);
    }

    /**
     * Crea un dispositivo HDD según capacidad.
     *
     * @param gb capacidad en GB
     * @return instancia de {@link Almacenamiento} tipo HDD
     * @throws IllegalArgumentException si no existe
     */
    @Override
    public Almacenamiento hdd(int gb) {
        return get(HDD_MAP, gb);
    }

    /**
     * Crea un gabinete según el modelo solicitado.
     *
     * @param modelo clave del modelo en el catálogo
     * @return instancia de {@link Gabinete}
     * @throws IllegalArgumentException si no existe el modelo
     */
    @Override
    public Gabinete gabinete(String modelo) {
        return get(CASE_MAP, modelo);
    }

    // --- Catálogos completos ---

    @Override public Collection<CPU> catalogoCPU() { return CPU_MAP.values(); }
    @Override public Collection<GPU> catalogoGPU() { return GPU_MAP.values(); }
    @Override public Collection<RAM> catalogoRAM() { return RAM_MAP.values(); }
    @Override public Collection<Motherboard> catalogoMotherboard() { return MB_MAP.values(); }
    @Override public Collection<FuenteDePoder> catalogoPSU() { return PSU_MAP.values(); }
    @Override public Collection<Almacenamiento> catalogoStorage() {
        List<Almacenamiento> lista = new ArrayList<>(SSD_MAP.values());
        lista.addAll(HDD_MAP.values());
        return lista;
    }
    @Override public Collection<Gabinete> catalogoGabinetes() { return CASE_MAP.values(); }

    // --- Utilidades ---

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
        if (v == null) {
            throw new IllegalArgumentException("No existe: " + key);
        }
        return v;
    }
}
