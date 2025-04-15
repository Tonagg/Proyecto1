



// Adapter para adaptar una GPUIntel para ser utilizada en el contexto de la fábrica AMD.
public class AdaptadorGPUIntel implements GPU {
    private GPU gpuIntel; // Instancia de GPU que se desea adaptar.

    public AdaptadorGPUIntel(GPU gpuIntel) {
        this.gpuIntel = gpuIntel;
    }

    

    @Override
    public String getDescripcion() {
        // Se añade una etiqueta para indicar la adaptación.
        return "[Adaptado para AMD] " + gpuIntel.getDescripcion();
    }

    
}
