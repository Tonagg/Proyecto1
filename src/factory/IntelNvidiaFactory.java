package src.factory;

import java.util.*;

/**
 * Fábrica concreta que suministra todos los componentes “oficiales”
 * de la alianza Intel + Nvidia (sin adaptadores).
 *
 * <p>Implementa el contrato {@link ComponenteFactory} devolviendo
 *         – a petición puntual – la pieza solicitada, y
 *         – para la interfaz gráfica – los catálogos completos.</p>
 */
public final class IntelNvidiaFactory implements ComponenteFactory {

    /* ─────────────────────────  Catálogos internos  ───────────────────────── */

    /* --- CPU Intel --------------------------------------------------------- */
    private static final Map<String, CPU> CPU_MAP = Map.ofEntries(
        Map.entry("Core i3‑13100",  new CPUIntel("Core i3‑13100",  2_805,  4)),
        Map.entry("Core i5‑13600K", new CPUIntel("Core i5‑13600K", 5_799, 14)),
        Map.entry("Core i7‑13700K", new CPUIntel("Core i7‑13700K", 8_751, 16)),
        Map.entry("Core i9‑13900K", new CPUIntel("Core i9‑13900K",10_899, 24))
    );

    /* --- GPU Nvidia -------------------------------------------------------- */
    private static final Map<String, GPU> GPU_MAP = Map.ofEntries(
        Map.entry("GTX 1660", new GPUNvidia("GTX 1660",  4_000, "GDDR6")),
        Map.entry("RTX 3060", new GPUNvidia("RTX 3060",  6_329, "GDDR6")),
        Map.entry("RTX 4070", new GPUNvidia("RTX 4070", 25_000, "GDDR6")),
        Map.entry("RTX 4080", new GPUNvidia("RTX 4080", 34_553, "GDDR6X")),
        Map.entry("RTX 4090", new GPUNvidia("RTX 4090", 64_950, "GDDR6X"))
    );

    /* --- RAM ADATA / Kingston --------------------------------------------- */
    private static final Map<String, RAM> RAM_MAP = Map.ofEntries(
        Map.entry("ADATA‑8",  new ProductoRAM("ADATA 8 GB",  351, Marca.ADATA   ,  8)),
        Map.entry("ADATA‑16", new ProductoRAM("ADATA 16 GB", 596, Marca.ADATA   , 16)),
        Map.entry("ADATA‑32", new ProductoRAM("ADATA 32 GB", 999, Marca.ADATA   , 32)),
        Map.entry("KING‑8",   new ProductoRAM("Kingston 8 GB",391, Marca.KINGSTON,  8)),
        Map.entry("KING‑16",  new ProductoRAM("Kingston 16 GB",710, Marca.KINGSTON, 16)),
        Map.entry("KING‑32",  new ProductoRAM("Kingston 32 GB",1_261,Marca.KINGSTON,32))
    );

    /* --- Motherboards Intel (ASUS / MSI) ----------------------------------- */
    private static final Map<String, Motherboard> MB_MAP = Map.ofEntries(
        Map.entry("ROG Maximus Z790 Hero",
            new MotherboardIntel("ROG Maximus Z790 Hero", 13_490, Marca.ASUS, "Z790")),
        Map.entry("TUF Gaming B760‑Plus WIFI D4",
            new MotherboardIntel("TUF Gaming B760‑Plus WIFI D4", 4_836, Marca.ASUS, "B760")),
        Map.entry("MEG Z790 Godlike",
            new MotherboardIntel("MEG Z790 Godlike", 13_822, Marca.MSI, "Z790")),
        Map.entry("MAG B760 Tomahawk WIFI DDR4",
            new MotherboardIntel("MAG B760 Tomahawk WIFI DDR4", 5_863, Marca.MSI, "B760"))
    );

    /* --- Fuentes de poder (EVGA / Corsair) --------------------------------- */
    private static final Map<Integer, FuenteDePoder> PSU_MAP = Map.ofEntries(
        Map.entry( 800, new ProductoFuenteDePoder("EVGA 800 W",   1_919, Marca.EVGA   ,  800)),
        Map.entry(1000, new ProductoFuenteDePoder("EVGA 1000 W",  4_039, Marca.EVGA   , 1000)),
        Map.entry(1200, new ProductoFuenteDePoder("Corsair 1200 W",5_441, Marca.CORSAIR,1200)),
        Map.entry(1500, new ProductoFuenteDePoder("EVGA 1500 W",  7_548, Marca.EVGA   , 1500))
    );

    /* --- Almacenamiento ---------------------------------------------------- */
    private static final Map<Integer, Almacenamiento> SSD_MAP = Map.ofEntries(
        Map.entry( 500, new ProductoAlmacenamiento("Kingston SSD 500 GB",   710, Marca.KINGSTON,  500, true)),
        Map.entry(1000, new ProductoAlmacenamiento("Kingston SSD 1 TB",   1_154, Marca.KINGSTON, 1000, true)),
        Map.entry(2000, new ProductoAlmacenamiento("Kingston SSD 2 TB",   2_160, Marca.KINGSTON, 2000, true)),
        Map.entry(4000, new ProductoAlmacenamiento("Kingston SSD 4 TB",  11_049, Marca.KINGSTON, 4000, true))
    );
    private static final Map<Integer, Almacenamiento> HDD_MAP = Map.ofEntries(
        Map.entry( 500, new ProductoAlmacenamiento("WD Blue 500 GB",        515, Marca.WD     ,  500, false)),
        Map.entry(1000, new ProductoAlmacenamiento("WD Blue 1 TB",        1_548, Marca.WD     , 1000, false)),
        Map.entry(1001, new ProductoAlmacenamiento("Seagate Barracuda 1 TB",1_141, Marca.SEAGATE,1000,false)),
        Map.entry(2000, new ProductoAlmacenamiento("Seagate Barracuda 2 TB",3_721, Marca.SEAGATE,2000,false))
    );

    /* --- Gabinetes --------------------------------------------------------- */
    private static final Map<String, Gabinete> CASE_MAP = Map.of(
        "NZXT H6 Flow", new ProductoGabinete("NZXT H6 Flow", 2_173, Marca.NZXT),
        "Yeyian Lancer",new ProductoGabinete("Yeyian Lancer",1_319, Marca.YEYIAN)
    );

    /* ──────────────────────────  Implementación  ─────────────────────────── */

    /* --- Peticiones puntuales --------------------------------------------- */
    @Override public CPU          cpu(String modelo)            { return get(CPU_MAP , modelo); }
    @Override public GPU          gpu(String modelo)            { return get(GPU_MAP , modelo); }
    @Override public Motherboard  motherboard(String modelo)    { return get(MB_MAP  , modelo); }
    @Override public FuenteDePoder fuente(int w)                { return get(PSU_MAP , w);      }

    @Override public RAM ram(int gb, Marca marca) {
        String key = (marca == Marca.ADATA ? "ADATA‑" : "KING‑") + gb;
        return get(RAM_MAP, key);
    }

    @Override public Almacenamiento ssd(int gb) { return get(SSD_MAP, gb); }
    @Override public Almacenamiento hdd(int gb) { return get(HDD_MAP, gb); }
    @Override public Gabinete gabinete(String modelo) { return get(CASE_MAP, modelo); }

    /* --- Catálogos completos ---------------------------------------------- */
    @Override public Collection<CPU>          catalogoCPU()       { return CPU_MAP.values(); }
    @Override public Collection<GPU>          catalogoGPU()       { return GPU_MAP.values(); }
    @Override public Collection<RAM>          catalogoRAM()       { return RAM_MAP.values(); }
    @Override public Collection<Motherboard>  catalogoMotherboard(){ return MB_MAP.values();  }
    @Override public Collection<FuenteDePoder> catalogoPSU()      { return PSU_MAP.values();  }

    @Override public Collection<Almacenamiento> catalogoStorage() {
        List<Almacenamiento> list = new ArrayList<>(SSD_MAP.values());
        list.addAll(HDD_MAP.values());
        return list;
    }

    @Override public Collection<Gabinete> catalogoGabinetes() { return CASE_MAP.values(); }

    /* --- Compatibilidad (la default ya sirve) ----------------------------- */

    /* ───────────────────────────  utilidades  ───────────────────────────── */
    private static <K,V> V get(Map<K,V> map, K key) {
        V v = map.get(key);
        if (v == null) throw new IllegalArgumentException("No existe: " + key);
        return v;
    }
}
