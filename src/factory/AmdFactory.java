package src.factory;

import java.util.*;

import src.adapter.AdaptadorGPUNvidia;
import src.adapter.AdaptadorMotherboardIntel;

public final class AmdFactory implements ComponenteFactory {

    private final IntelNvidiaFactory intel = new IntelNvidiaFactory();

    /* CPU propias */
    private static final Map<String, CPU> CPU_MAP = Map.ofEntries(
        Map.entry("Ryzen 5 5600G",  new CPUAMD("Ryzen 5 5600G",  2449, 6)),
        Map.entry("Ryzen 5 7600X",  new CPUAMD("Ryzen 5 7600X",  4789, 6)),
        Map.entry("Ryzen 7 7700X",  new CPUAMD("Ryzen 7 7700X",  7043, 8)),
        Map.entry("Ryzen 9 7950X3D",new CPUAMD("Ryzen 9 7950X3D",15059,16))
    );

    /* --- Peticiones puntuales --- */
    @Override public CPU cpu(String modelo)       { return get(CPU_MAP, modelo); }
    @Override public GPU gpu(String modelo)       { return new AdaptadorGPUNvidia(intel.gpu(modelo)); }
    @Override public Motherboard motherboard(String m) {
        return new AdaptadorMotherboardIntel(intel.motherboard(m));
    }
    @Override public RAM ram(int gb, Marca marca) { return intel.ram(gb, marca); }
    @Override public FuenteDePoder fuente(String modeloClave) { return intel.fuente(modeloClave);}
    @Override public Almacenamiento ssd(int gb)   { return intel.ssd(gb);        }
    @Override public Almacenamiento hdd(int gb)   { return intel.hdd(gb);        }
    @Override public Gabinete gabinete(String m)  { return intel.gabinete(m);    }

    /* --- Catálogos --- */
    @Override public Collection<CPU> catalogoCPU()            { return CPU_MAP.values(); }
    @Override public Collection<GPU> catalogoGPU()            { return wrap(intel.catalogoGPU(), AdaptadorGPUNvidia::new); }
    @Override public Collection<RAM> catalogoRAM()            { return intel.catalogoRAM(); }
    @Override public Collection<Motherboard> catalogoMotherboard() {
        return wrap(intel.catalogoMotherboard(), AdaptadorMotherboardIntel::new);
    }
    @Override public Collection<FuenteDePoder> catalogoPSU()  { return intel.catalogoPSU(); }
    @Override public Collection<Almacenamiento> catalogoStorage() { return intel.catalogoStorage(); }
    @Override public Collection<Gabinete> catalogoGabinetes() { return intel.catalogoGabinetes(); }

    /* --- Compatibilidad AMD propia (acepta GPU Nvidia adaptada) --- */
    @Override public boolean compatibles(CPU cpu, Motherboard mb) {
        return true;   // la fábrica ya entrega motherboard adaptada
    }

    /* ---------- helpers ---------- */
    private static <K,V> V get(Map<K,V> map, K key) {
        V v = map.get(key);
        if (v == null) throw new IllegalArgumentException("No existe: "+key);
        return v;
    }
    private static <T,R> Collection<R> wrap(Collection<T> src, java.util.function.Function<T,R> f) {
        return src.stream().map(f).toList();
    }
}
