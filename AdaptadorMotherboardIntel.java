
// Adapter para adaptar una GPUIntel para ser utilizada en el contexto de la fábrica AMD.
public class AdaptadorMotherboardIntel implements GPU {
    private GPU gpuIntel; // Instancia de GPU que se desea adaptar.

    public AdaptadorGPUIntel(Motherboard motherboardIntel) {
        this.motherboardIntel = motherboardIntel;
    }

    

    @Override
    public String getDescripcion() {
        // Se añade una etiqueta para indicar la adaptación.
        return "[Adaptado para AMD] " + motherboardIntel.getDescripcion();
    }

    
}
