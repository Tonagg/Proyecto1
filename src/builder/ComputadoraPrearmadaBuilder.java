/* src/builder/ComputadoraPrearmadaBuilder.java */
package src.builder;

import java.util.*;
import java.util.function.Consumer;

import src.Computadora;
import src.factory.*;

/**
 * Builder para equipos **pre‑armados**.
 * Cada configuración se describe con lambdas para no tocar código al añadir modelos.
 */
public class ComputadoraPrearmadaBuilder implements ComputadoraBuilder {

    /* ——— estado interno ——— */
    private final Computadora pc = new Computadora();
    private final ComponenteFactory factory;

    /* ——— catálogo de configuraciones ——— */
    private static final Map<String, Consumer<ComputadoraPrearmadaBuilder>> CONFIGS = new HashMap<>();

    static {
        // Intel presets
        CONFIGS.put("gamer",   b -> b.configGamerIntel());
        CONFIGS.put("basica",  b -> b.configBasicaIntel());
        CONFIGS.put("estudio", b -> b.configEstudioIntel());
        CONFIGS.put("oficina", b -> b.configOficinaIntel());
        CONFIGS.put("render",  b -> b.configRenderIntel());
        // AMD presets
        CONFIGS.put("gamer_amd",   b -> b.configGamerAmd());
        CONFIGS.put("basica_amd",  b -> b.configBasicaAmd());
        CONFIGS.put("estudio_amd", b -> b.configEstudioAmd());
        CONFIGS.put("oficina_amd", b -> b.configOficinaAmd());
        CONFIGS.put("render_amd",  b -> b.configRenderAmd());
    }

    /* ——— ctor ——— */
    public ComputadoraPrearmadaBuilder(ComponenteFactory f, String modelo) {
        this.factory = f;
        String key = modelo.toLowerCase();
        // si usamos AMD, añadimos sufijo
        if (f instanceof src.factory.AmdFactory) key += "_amd";
        Consumer<ComputadoraPrearmadaBuilder> cfg = CONFIGS.get(key);
        if (cfg == null) throw new IllegalArgumentException("Modelo desconocido: " + modelo);
        cfg.accept(this);
    }

    /* — Intel Presets — */

    private void configGamerIntel() {
        pc.setCpu(factory.cpu("Core i7‑13700K"));
        addRam(32, 4, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 4080"));
        pc.agregarDisco(factory.ssd(500));
        pc.setFuente(factory.fuente("EVGA1000"));
        pc.setMotherboard(factory.motherboard("ROG Maximus Z790 Hero"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    private void configBasicaIntel() {
        pc.setCpu(factory.cpu("Core i3‑13100"));
        addRam(8, 1, Marca.KINGSTON);
        pc.setGpu(factory.gpu("GTX 1660"));
        pc.agregarDisco(factory.hdd(500));
        pc.setFuente(factory.fuente("EVGA800"));
        pc.setMotherboard(factory.motherboard("TUF Gaming B760‑Plus WIFI D4"));
        pc.setGabinete(factory.gabinete("Yeyian Lancer"));
    }

    private void configEstudioIntel() {
        pc.setCpu(factory.cpu("Core i5‑13600K"));
        addRam(16, 2, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 3060"));
        pc.agregarDisco(factory.ssd(1000));
        pc.setFuente(factory.fuente("EVGA1500"));
        pc.setMotherboard(factory.motherboard("MEG Z790 Godlike"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    private void configOficinaIntel() {
        pc.setCpu(factory.cpu("Core i3‑13100"));
        addRam(8, 2, Marca.KINGSTON);        // 2×8 GB = 16 GB
        pc.setGpu(factory.gpu("RTX 3060"));   // suficiente para tareas ligeras
        pc.agregarDisco(factory.ssd(500));
        pc.setFuente(factory.fuente("CORSAIR800"));
        pc.setMotherboard(factory.motherboard("TUF Gaming B760‑Plus WIFI D4"));
        pc.setGabinete(factory.gabinete("Yeyian Lancer"));
    }

    private void configRenderIntel() {
        pc.setCpu(factory.cpu("Core i9‑13900K"));
        addRam(32, 2, Marca.ADATA);           // 2×32 GB = 64 GB
        pc.setGpu(factory.gpu("RTX 4090"));
        pc.agregarDisco(factory.ssd(2000));
        pc.setFuente(factory.fuente("EVGA1500"));
        pc.setMotherboard(factory.motherboard("MEG Z790 Godlike"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    /* — AMD Presets — */

    private void configGamerAmd() {
        pc.setCpu(factory.cpu("Ryzen 7 7700X"));
        addRam(32, 4, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 4080"));
        pc.agregarDisco(factory.ssd(500));
        pc.setFuente(factory.fuente("EVGA1000"));
        pc.setMotherboard(factory.motherboard("ROG Maximus Z790 Hero"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    private void configBasicaAmd() {
        pc.setCpu(factory.cpu("Ryzen 5 5600G"));
        addRam(8, 1, Marca.KINGSTON);
        pc.setGpu(factory.gpu("GTX 1660"));
        pc.agregarDisco(factory.hdd(500));
        pc.setFuente(factory.fuente("EVGA800"));
        pc.setMotherboard(factory.motherboard("TUF Gaming B760‑Plus WIFI D4"));
        pc.setGabinete(factory.gabinete("Yeyian Lancer"));
    }

    private void configEstudioAmd() {
        pc.setCpu(factory.cpu("Ryzen 5 7600X"));
        addRam(16, 2, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 3060"));
        pc.agregarDisco(factory.ssd(1000));
        pc.setFuente(factory.fuente("EVGA1500"));
        pc.setMotherboard(factory.motherboard("MEG Z790 Godlike"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    private void configOficinaAmd() {
        pc.setCpu(factory.cpu("Ryzen 5 5600G"));
        addRam(16, 2, Marca.KINGSTON);       // 2×16 GB = 32 GB
        pc.setGpu(factory.gpu("RTX 3060"));
        pc.agregarDisco(factory.ssd(500));
        pc.setFuente(factory.fuente("CORSAIR800"));
        pc.setMotherboard(factory.motherboard("TUF Gaming B760‑Plus WIFI D4"));
        pc.setGabinete(factory.gabinete("Yeyian Lancer"));
    }

    private void configRenderAmd() {
        pc.setCpu(factory.cpu("Ryzen 9 7950X3D"));
        addRam(32, 2, Marca.ADATA);           // 2×32 GB = 64 GB
        pc.setGpu(factory.gpu("RTX 4090"));
        pc.agregarDisco(factory.ssd(2000));
        pc.setFuente(factory.fuente("EVGA1500"));
        pc.setMotherboard(factory.motherboard("MEG Z790 Godlike"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    /* — helpers — */
    private void addRam(int gb, int cantidad, Marca marca) {
        RAM modulo = factory.ram(gb, marca);
        for (int i = 0; i < cantidad; i++) pc.agregarRAM(modulo);
    }

    /* — métodos del contrato — */
    @Override public void agregarCPU(CPU c)                 {}
    @Override public void agregarRAM(RAM r)                 {}
    @Override public void agregarGPU(GPU g)                 {}
    @Override public void agregarDisco(Almacenamiento d)     {}
    @Override public void agregarFuente(FuenteDePoder f)     {}
    @Override public void agregarMotherboard(Motherboard m)  {}
    @Override public void agregarGabinete(Gabinete g)        {}

    @Override public Computadora obtenerComputadora()        { return pc; }
}
