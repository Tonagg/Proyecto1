import java.util.List;

public class ComputadoraPrearmadaBuilder implements ComputadoraBuilder {
    private final Computadora computadora;
    private final ComponenteFactory factory;

    /**
     * @param factory Instancia de intel_Nvidia o AmdFactory
     * @param modelo  Nombre del modelo prearmado ("Gamer", "Basica", etc.)
     */
    public ComputadoraPrearmadaBuilder(ComponenteFactory factory, String modelo) {
        this.factory     = factory;
        this.computadora = new Computadora();
        configurarModelo(modelo);
    }

    private void configurarModelo(String modelo) {
        List<CPU>               cpus    = factory.getCPUs();
        List<GPU>               gpus    = factory.getGPUs();
        List<RAM>               rams    = factory.getRAM();
        List<Almacenamiento>    discos  = factory.getAlmacenamiento();
        List<FuenteDePoder>     fuentes = factory.getFuente();
        List<Motherboard>       mbs     = factory.getMotherboard();
        List<Gabinete>          gabs    = factory.getGabinete();

        switch (modelo.toLowerCase()) {
            case "gamer":
                computadora.setCpu(cpus.get(2));           // Core i7
                for (int i = 0; i < 4; i++)                 // 4 x 32 GB
                    computadora.agregarRAM(rams.get(2));
                computadora.setGpu(gpus.get(3));           // RTX 4080
                computadora.agregarDisco(discos.get(4));    // SSD 500 GB
                computadora.setFuente(fuentes.get(1));      // Corsair 1000 W
                computadora.setMotherboard(mbs.get(0));     // ROG Z790 Hero
                computadora.setGabinete(gabs.get(0));       // NZXT H6 Flow
                break;

            case "basica":
                computadora.setCpu(cpus.get(0));            // Core i3
                computadora.agregarRAM(rams.get(0));        // 8 GB
                computadora.setGpu(gpus.get(0));            // GTX 1660
                computadora.agregarDisco(discos.get(0));    // HDD 500 GB
                computadora.setFuente(fuentes.get(0));      // EVGA 800 W
                computadora.setMotherboard(mbs.get(1));     // TUF B760-Plus
                computadora.setGabinete(gabs.get(1));       // Yeyian Lancer
                break;

            case "estudio":
                // Ejemplo de nuevo modelo "Estudio"
                computadora.setCpu(cpus.get(1));            // Core i5
                computadora.agregarRAM(rams.get(1));        // 16 GB
                computadora.setGpu(gpus.get(1));            // RTX 3060
                computadora.agregarDisco(discos.get(5));    // SSD 1 TB
                computadora.setFuente(fuentes.get(2));      // EVGA 1500 W
                computadora.setMotherboard(mbs.get(2));     // MEG Z790 Godlike
                computadora.setGabinete(gabs.get(0));       // NZXT H6 Flow
                break;

            // añade aquí tantos casos como quieras

            default:
                throw new IllegalArgumentException("Modelo desconocido: " + modelo);
        }
    }

    // Métodos del Builder no aplican en prearmado
    @Override public void agregarCPU(CPU cpu)                   { /* no-op */ }
    @Override public void agregarRAM(RAM ram)                   { /* no-op */ }
    @Override public void agregarGPU(GPU gpu)                   { /* no-op */ }
    @Override public void agregarDisco(Almacenamiento disco)    { /* no-op */ }
    @Override public void agregarFuente(FuenteDePoder fuente)   { /* no-op */ }
    @Override public void agregarMotherboard(Motherboard mb)    { /* no-op */ }
    @Override public void agregarGabinete(Gabinete gab)         { /* no-op */ }

    @Override
    public Computadora obtenerComputadora() {
        return computadora;
    }
}
