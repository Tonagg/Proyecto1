public class ComputadoraPrearmadaBuilder implements ComputadoraBuilder {
    private Computadora computadora;

    // Constructor que configura la computadora prearmada según el modelo
    public ComputadoraPrearmadaBuilder(String modelo) {
        computadora = new Computadora();
        
        if (modelo.equalsIgnoreCase("Gamer")) {
            // Configuración predefinida Gamer
            computadora.setCpu(new CPUIntel("Core i7-13700K", 5000, 8));
            
            // Supongamos que para la configuración Gamer utilizamos 4 módulos de RAM de 32GB cada uno
            computadora.agregarRAM(new RAMConcreta(32, 1200, "Kingston"));
            computadora.agregarRAM(new RAMConcreta(32, 1200, "Kingston"));
            computadora.agregarRAM(new RAMConcreta(32, 1200, "Kingston"));
            computadora.agregarRAM(new RAMConcreta(32, 1200, "Kingston"));
            
            computadora.setGpu(new GPUIntel("RTX 4080", 6000, "GDDR6"));
            computadora.setMotherboard(new MotherboardIntel("ASUS ROG Maximus", 3500, "Z790"));
            computadora.setFuente(new Fuente("Corsair 1000W", 1500, "Corsair"));
            computadora.setGabinete(new Gabinete("NZXT H510", 1200, "NZXT"));
            
            // Puedes agregar discos u otros componentes si lo necesitas.
            computadora.agregarDisco(new Almacenamiento("SSD de 1TB", 2000, "Kingston"));
            
        } else if (modelo.equalsIgnoreCase("Basica")) {
            // Configuración predefinida Básica
            computadora.setCpu(new CPUIntel("Core i3-13100", 2000, 4));
            
            // Configuración con menos RAM: por ejemplo, un módulo de 8GB
            computadora.agregarRAM(new RAMConcreta(8, 800, "Kingston"));
            
            computadora.setGpu(new GPUIntel("GTX 1660", 3000, "GDDR5"));
            computadora.setMotherboard(new MotherboardIntel("ASUS TUF Gaming", 2500, "B760"));
            computadora.setFuente(new Fuente("EVGA 800W", 1000, "EVGA"));
            computadora.setGabinete(new Gabinete("Cooler Master", 800, "Cooler Master"));
            
            computadora.agregarDisco(new Almacenamiento("HDD de 2TB", 1500, "Western Digital"));
        }
        // Puedes agregar más modelos y configuraciones según lo requieras.
    }
    
    // En un builder prearmado, los métodos agregar no se usan, ya que la configuración
    // se realiza en el constructor. Se implementan dejando el cuerpo vacío.
    
    @Override
    public void agregarCPU(CPU cpu) { }
    
    @Override
    public void agregarRAM(RAM modulo) { }
    
    @Override
    public void agregarGPU(GPU gpu) { }
    
    @Override
    public void agregarDisco(Almacenamiento disco) { }
    
    @Override
    public void agregarFuente(Fuente fuente) { }
    
    @Override
    public void agregarMotherboard(Motherboard motherboard) { }
    
    @Override
    public void agregarGabinete(Gabinete gabinete) { }
    
    @Override
    public Computadora obtenerComputadora() {
        return computadora;
    }
}
