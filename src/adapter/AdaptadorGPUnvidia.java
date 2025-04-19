/* AdaptadorGPUnvidia.java */
package src.adapter;

import src.factory.GPU;

public class AdaptadorGPUNvidia implements GPU {

    private final GPU gpu;

    public AdaptadorGPUNvidia(GPU gpu) { this.gpu = gpu; }

    @Override public String  getDescripcion() { return "[Adaptado] " + gpu.getDescripcion(); }
    @Override public double  getPrecio()      { return gpu.getPrecio(); }
}
