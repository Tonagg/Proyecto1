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
    private final Computadora       pc      = new Computadora();
    private final ComponenteFactory factory;

    /* ——— catálogo de configuraciones ——— */
    private static final Map<String, Consumer<ComputadoraPrearmadaBuilder>> CONFIGS =
            new HashMap<>();

    static {
        CONFIGS.put("gamer",   b -> b.configGamer());
        CONFIGS.put("basica",  b -> b.configBasica());
        CONFIGS.put("estudio", b -> b.configEstudio());
    }

    /* ——— ctor ——— */
    public ComputadoraPrearmadaBuilder(ComponenteFactory f, String modelo) {
        this.factory = f;
        Consumer<ComputadoraPrearmadaBuilder> cfg =
                CONFIGS.get(modelo.toLowerCase());

        if (cfg == null) throw new IllegalArgumentException("Modelo desconocido: " + modelo);
        cfg.accept(this);          // aplica la configuración
    }

    /* ==============================================================
       ===============  CONFIGURACIONES CONCRETAS  ===================
       ==============================================================*/

    private void configGamer() {
        pc.setCpu(factory.cpu("Core i7‑13700K"));
        addRam(32, 4, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 4080"));
        pc.agregarDisco(factory.ssd(500));
        pc.setFuente(factory.fuente("EVGA1000"));
        pc.setMotherboard(factory.motherboard("ROG Maximus Z790 Hero"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    private void configBasica() {
        pc.setCpu(factory.cpu("Core i3‑13100"));
        addRam(8, 1, Marca.KINGSTON);
        pc.setGpu(factory.gpu("GTX 1660"));
        pc.agregarDisco(factory.hdd(500));
        pc.setFuente(factory.fuente("EVGA800"));
        pc.setMotherboard(factory.motherboard("TUF Gaming B760‑Plus WIFI D4"));
        pc.setGabinete(factory.gabinete("Yeyian Lancer"));
    }

    private void configEstudio() {
        pc.setCpu(factory.cpu("Core i5‑13600K"));
        addRam(16, 2, Marca.ADATA);
        pc.setGpu(factory.gpu("RTX 3060"));
        pc.agregarDisco(factory.ssd(1000));
        pc.setFuente(factory.fuente("EVGA1500"));
        pc.setMotherboard(factory.motherboard("MEG Z790 Godlike"));
        pc.setGabinete(factory.gabinete("NZXT H6 Flow"));
    }

    /* ——— helpers ——— */
    private void addRam(int gb, int cantidad, Marca marca) {
        RAM modulo = factory.ram(gb, marca);
        for (int i = 0; i < cantidad; i++) pc.agregarRAM(modulo);
    }

    /* ==============================================================
       ==============  MÉTODOS DEL CONTRATO BUILDER  =================
       ==============================================================*/

    /* En el pre‑armado el cliente no añade piezas a mano,
       por lo que los métodos de la interfaz quedan vacíos.          */
    @Override public void agregarCPU       (CPU c)                {}
    @Override public void agregarRAM       (RAM r)                {}
    @Override public void agregarGPU       (GPU g)                {}
    @Override public void agregarDisco     (Almacenamiento d)     {}
    @Override public void agregarFuente    (FuenteDePoder f)      {}
    @Override public void agregarMotherboard(Motherboard m)       {}
    @Override public void agregarGabinete  (Gabinete g)           {}

    @Override public Computadora obtenerComputadora()             { return pc; }
}
