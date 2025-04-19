/* AdaptadorGPUnvidia.java */
package src.adapter;

import src.factory.GPU;

public class AdaptadorGPUnvidia implements GPU {

    private final GPU gpu;

    public AdaptadorGPUnvidia(GPU gpu) { this.gpu = gpu; }

    @Override public String  getDescripcion() { return "[Adaptado] " + gpu.getDescripcion(); }
    @Override public double  getPrecio()      { return gpu.getPrecio(); }
}
