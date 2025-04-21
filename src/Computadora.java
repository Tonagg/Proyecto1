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

/**
 * Representa una computadora ensamblada con diferentes componentes de hardware.
 * Permite agregar y obtener CPU, GPU, memoria RAM, dispositivos de almacenamiento,
 * fuente de poder, motherboard y gabinete, así como calcular el precio total y
 * generar una descripción detallada del ensamblaje.
 */
public class Computadora {

    private CPU cpu;
    private List<RAM> ramModules;
    private GPU gpu;
    private List<Almacenamiento> discos;
    private FuenteDePoder fuente;
    private Motherboard motherboard;
    private Gabinete gabinete;

    /**
     * Construye una instancia vacía de Computadora con listas inicializadas para RAM y discos.
     */
    public Computadora() {
        this.ramModules = new ArrayList<>();
        this.discos = new ArrayList<>();
    }

    /**
     * Obtiene la CPU instalada.
     * @return instancia de {@link CPU} o {@code null} si no está establecida
     */
    public CPU getCpu() {
        return cpu;
    }

    /**
     * Instala la CPU especificada.
     * @param cpu instancia de {@link CPU} a instalar
     */
    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    /**
     * Obtiene la GPU instalada.
     * @return instancia de {@link GPU} o {@code null} si no está establecida
     */
    public GPU getGpu() {
        return gpu;
    }

    /**
     * Instala la GPU especificada.
     * @param gpu instancia de {@link GPU} a instalar
     */
    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    /**
     * Obtiene la fuente de poder instalada.
     * @return instancia de {@link FuenteDePoder} o {@code null} si no está establecida
     */
    public FuenteDePoder getFuente() {
        return fuente;
    }

    /**
     * Instala la fuente de poder especificada.
     * @param fuente instancia de {@link FuenteDePoder} a instalar
     */
    public void setFuente(FuenteDePoder fuente) {
        this.fuente = fuente;
    }

    /**
     * Obtiene la motherboard instalada.
     * @return instancia de {@link Motherboard} o {@code null} si no está establecida
     */
    public Motherboard getMotherboard() {
        return motherboard;
    }

    /**
     * Instala la motherboard especificada.
     * @param motherboard instancia de {@link Motherboard} a instalar
     */
    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    /**
     * Obtiene el gabinete instalado.
     * @return instancia de {@link Gabinete} o {@code null} si no está establecido
     */
    public Gabinete getGabinete() {
        return gabinete;
    }

    /**
     * Instala el gabinete especificado.
     * @param gabinete instancia de {@link Gabinete} a instalar
     */
    public void setGabinete(Gabinete gabinete) {
        this.gabinete = gabinete;
    }

    /**
     * Obtiene la lista de módulos de RAM instalados.
     * @return lista de instancias de {@link RAM}
     */
    public List<RAM> getRamModules() {
        return ramModules;
    }

    /**
     * Agrega un módulo de RAM verificando restricciones: máximo 4 módulos y capacidad total ≤128 GB.
     * @param modulo instancia de {@link RAM} a agregar
     * @throws IllegalArgumentException si se excede el número de módulos o la capacidad máxima
     */
    public void agregarRAM(RAM modulo) {
        if (ramModules.size() >= 4) {
            throw new IllegalArgumentException("Solo se permiten hasta 4 módulos de RAM.");
        }
        int capacidadActual = ramModules.stream().mapToInt(RAM::getCapacidadGB).sum();
        if (capacidadActual + modulo.getCapacidadGB() > 128) {
            throw new IllegalArgumentException("La capacidad total de RAM no puede exceder 128 GB.");
        }
        ramModules.add(modulo);
    }

    /**
     * Obtiene la lista de dispositivos de almacenamiento instalados.
     * @return lista de instancias de {@link Almacenamiento}
     */
    public List<Almacenamiento> getDiscos() {
        return discos;
    }

    /**
     * Agrega un dispositivo de almacenamiento.
     * @param disco instancia de {@link Almacenamiento} a agregar
     */
    public void agregarDisco(Almacenamiento disco) {
        discos.add(disco);
    }

    /**
     * Calcula el precio total sumando los precios de todos los componentes instalados.
     * @return suma de precios de CPU, GPU, fuente, motherboard, gabinete, RAM y discos
     */
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

    /**
     * Genera una descripción detallada de cada componente y software instalado.
     * @return cadena con descripciones individuales separadas por líneas
     */
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
