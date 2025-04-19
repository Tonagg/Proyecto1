package src;
import java.util.ArrayList;
import java.util.List;

import src.factory.Almacenamiento;
import src.factory.CPU;
import src.factory.FuenteDePoder;
import src.factory.GPU;
import src.factory.Gabinete;
import src.factory.Motherboard;
import src.factory.RAM;

public class Computadora {
    private CPU cpu;
    private List<RAM> ramModules;
    private GPU gpu;
    private List<Almacenamiento> discos;
    private FuenteDePoder fuente;
    private Motherboard motherboard;
    private Gabinete gabinete;
    
    public Computadora() {
        this.ramModules = new ArrayList<>();
        this.discos = new ArrayList<>();
    }
    
    // Getters y Setters para CPU, GPU, Fuente, Motherboard y Gabinete
    public CPU getCpu() { 
        return cpu;
         }
    public void setCpu(CPU cpu) {
     this.cpu = cpu;
      }
    
    public GPU getGpu() { 
        return gpu;
         }
    public void setGpu(GPU gpu) {
     this.gpu = gpu;
      }
    
    public FuenteDePoder getFuente() {
     return fuente;
      }
    public void setFuente(FuenteDePoder fuente) { 
        this.fuente = fuente;
         }
    
    public Motherboard getMotherboard() { 
        return motherboard;
         }
    public void setMotherboard(Motherboard motherboard) {
     this.motherboard = motherboard; 
 }
    
    public Gabinete getGabinete() { 
        return gabinete;
         }
    public void setGabinete(Gabinete gabinete) {
     this.gabinete = gabinete;
      }
    
    // Métodos para manejar múltiples módulos de RAM
    public List<RAM> getRamModules() {
        return ramModules;
    }
    
    public void agregarRAM(RAM modulo) {
        // Verificar que no se superen 4 módulos
        if (ramModules.size() >= 4) {
            throw new IllegalArgumentException("Solo se permiten hasta 4 módulos de RAM.");
        }
        // Verificar que la capacidad total no exceda 128 GB
        int capacidadActual = ramModules.stream().mapToInt(RAM::getCapacidadGB).sum();
        if (capacidadActual + modulo.getCapacidadGB() > 128) {
            throw new IllegalArgumentException("La capacidad total de RAM no puede exceder 128 GB.");
        }
        ramModules.add(modulo);
    }
    
    // Métodos para manejar múltiples discos de almacenamiento
    public List<Almacenamiento> getDiscos() {
        return discos;
    }
    
    public void agregarDisco(Almacenamiento disco) {
        discos.add(disco);
    }
    
    // Nota: Para los demás componentes (Fuente, Motherboard, Gabinete, etc.),
    // en un sistema real se podría implementar métodos de selección que permitan al usuario elegir
    // entre múltiples opciones disponibles. En este ejemplo se instancian directamente en el Modelo o Builder.
    
    public double calcularPrecioTotal() {
        double total = 0;
        if (cpu != null) total += cpu.getPrecio();
        if (gpu != null) total += gpu.getPrecio();
        if (fuente != null) total += fuente.getPrecio();
        if (motherboard != null) total += motherboard.getPrecio();
        if (gabinete != null) total += gabinete.getPrecio();
        for (RAM r : ramModules) {
            total += r.getPrecio();
        }
        for (Almacenamiento d : discos) {
            total += d.getPrecio();
        }
        return total;
    }
    
    public String getDescripcion() {
        StringBuilder sb = new StringBuilder();
        if (cpu != null) sb.append(cpu.getDescripcion()).append("\n");
        if (gpu != null) sb.append(gpu.getDescripcion()).append("\n");
        if (motherboard != null) sb.append(motherboard.getDescripcion()).append("\n");
        if (fuente != null) sb.append(fuente.getDescripcion()).append("\n");
        if (gabinete != null) sb.append(gabinete.getDescripcion()).append("\n");
        for (RAM r : ramModules) {
            sb.append(r.getDescripcion()).append("\n");
        }
        for (Almacenamiento d : discos) {
            sb.append(d.getDescripcion()).append("\n");
        }
        return sb.toString();
    }
}
