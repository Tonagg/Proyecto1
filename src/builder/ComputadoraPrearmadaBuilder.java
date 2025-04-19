package src.builder;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Consumer;

import src.*;
import src.factory.*;

/**
 * Builder para equipos pre‑armados.  
 * La tabla de configuraciones evita modificar el código
 * cuando se agregan nuevos modelos.
 */
public class ComputadoraPrearmadaBuilder implements ComputadoraBuilder {

    private final Computadora computadora = new Computadora();
    private final ComponenteFactory factory;

    /** Tabla <modelo, acción que configura la computadora>. */
    private static final Map<String, Consumer<ComputadoraPrearmadaBuilder>> CONFIGS =
            new HashMap<>();

    static {
        /* Las lambdas usan los números de parte NOMBRE en lugar
           de índices duros. Así, si cambia el orden del inventario
           tu código sigue funcionando.                         */

        CONFIGS.put("gamer", b -> {
            b.cpu   ("Core i7-13700K");
            b.ram   ("32GB", 4);
            b.gpu   ("RTX 4080");
            b.disco ("SSD Kingston 500GB");
            b.fuente("EVGA 1000W");
            b.mb    ("ROG Maximus Z790 Hero");
            b.gab   ("H6 Flow ATX");
        });

        CONFIGS.put("basica", b -> {
            b.cpu   ("Core i3-13100");
            b.ram   ("8GB", 1);
            b.gpu   ("GTX 1660");
            b.disco ("HDD Western Digital Blue 500GB");
            b.fuente("EVGA 800W");
            b.mb    ("TUF Gaming B760-Plus WIFI D4");
            b.gab   ("Lancer ATX");
        });

        CONFIGS.put("estudio", b -> {
            b.cpu   ("Core i5-13600K");
            b.ram   ("16GB", 2);
            b.gpu   ("RTX 3060");
            b.disco ("SSD Kingston 1TB");
            b.fuente("EVGA 1500W");
            b.mb    ("MEG Z790 Godlike");
            b.gab   ("H6 Flow ATX");
        });
    }

    /* ------------ constructor ----------- */

    public ComputadoraPrearmadaBuilder(ComponenteFactory factory,
                                       String modelo) {
        this.factory = factory;
        Consumer<ComputadoraPrearmadaBuilder> cfg =
                CONFIGS.get(modelo.toLowerCase());

        if (cfg == null)
            throw new IllegalArgumentException("Modelo desconocido: " + modelo);

        cfg.accept(this);          // aplica la configuración
    }

    /* ------------- helpers “DSL” --------------- */

    private void cpu(String nombre) {
        computadora.setCpu(
            factory.getCPUs().stream()
                   .filter(c -> c.getDescripcion().contains(nombre))
                   .findFirst().orElseThrow());
    }

    private void gpu(String nombre) {
        computadora.setGpu(
            factory.getGPUs().stream()
                   .filter(g -> g.getDescripcion().contains(nombre))
                   .findFirst().orElseThrow());
    }

    private void ram(String tamanio, int cantidad) {
        RAM modulo = factory.getRAM().stream()
                            .filter(r -> r.getDescripcion().contains(tamanio))
                            .findFirst().orElseThrow();
        for (int i = 0; i < cantidad; i++)
            computadora.agregarRAM(modulo);
    }

    private void disco(String nombre) {
        computadora.agregarDisco(
            factory.getAlmacenamiento().stream()
                   .filter(a -> a.getDescripcion().contains(nombre))
                   .findFirst().orElseThrow());
    }

    private void fuente(String nombre) {
        computadora.setFuente(
            factory.getFuente().stream()
                   .filter(f -> f.getDescripcion().contains(nombre))
                   .findFirst().orElseThrow());
    }

    private void mb(String nombre) {
        computadora.setMotherboard(
            factory.getMotherboard().stream()
                   .filter(m -> m.getDescripcion().contains(nombre))
                   .findFirst().orElseThrow());
    }

    private void gab(String nombre) {
        computadora.setGabinete(
            factory.getGabinete().stream()
                   .filter(g -> g.getDescripcion().contains(nombre))
                   .findFirst().orElseThrow());
    }

    /* ------------- interface vacía (no‑op) ---------- */

    @Override public void agregarCPU      (CPU c)               {}
    @Override public void agregarRAM      (RAM r)               {}
    @Override public void agregarGPU      (GPU g)               {}
    @Override public void agregarDisco    (Almacenamiento d)    {}
    @Override public void agregarFuente   (FuenteDePoder f)     {}
    @Override public void agregarMotherboard(Motherboard m)     {}
    @Override public void agregarGabinete (Gabinete g)          {}

    @Override
    public Computadora obtenerComputadora() {
        return computadora;
    }
}
