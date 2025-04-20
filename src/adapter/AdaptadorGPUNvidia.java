// src/adapter/AdaptadorGPUNvidia.java
package src.adapter;

import src.factory.Componente;
import src.factory.GPU;
import src.factory.TipoComponente;

public class AdaptadorGPUNvidia extends Componente implements GPU {
    private final GPU gpuIntel;

    public AdaptadorGPUNvidia(GPU gpuIntel) {
        super(
          // extraemos los datos del gpu envuelto (que es Componente)
          ((Componente)gpuIntel).getModelo(),
          ((Componente)gpuIntel).getPrecio(),
          ((Componente)gpuIntel).getMarca(),
          TipoComponente.GPU
        );
        this.gpuIntel = gpuIntel;
    }

    @Override
    public String getDescripcion() {
        return "[Adaptado a AMD] " + gpuIntel.getDescripcion();
    }

    @Override
    public double getPrecio() {
        return gpuIntel.getPrecio();
    }
}
