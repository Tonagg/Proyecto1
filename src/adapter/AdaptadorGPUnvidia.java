package src.adapter;

import src.factory.GPU;

// Adapter para adaptar una GPUIntel para ser utilizada en el contexto de la fábrica AMD.
public class AdaptadorGPUnvidia implements GPU {
    private GPU gpunvidia; // Instancia de GPU que se desea adaptar.

    public AdaptadorGPUnvidia(GPU gpunvidia) {
        this.gpunvidia = gpunvidia;
    }

    

    @Override
    public String getDescripcion() {
        // Se añade una etiqueta para indicar la adaptación.
        return "[Adaptado para AMD] " + gpunvidia.getDescripcion();
    }

    
}
