package src.adapter;

import src.factory.GPU;

public class AdaptadorGPUNvidia implements GPU {

    private final GPU gpuIntel;

    public AdaptadorGPUNvidia(GPU gpuIntel) {
        this.gpuIntel = gpuIntel;
    }

    @Override
    public String getDescripcion() {
        return "[Adaptado a AMD] " + gpuIntel.getDescripcion();
    }

    @Override
    public double getPrecio() {
        return gpuIntel.getPrecio(); // 👈 esta línea resuelve el error
    }

    @Override
    public String toString() {
        return getDescripcion(); // opcional, más claro
    }
}

