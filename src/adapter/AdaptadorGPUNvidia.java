package src.adapter;

import src.factory.Componente;
import src.factory.GPU;
import src.factory.TipoComponente;

/**
 * Adaptador que envuelve una GPU de NVIDIA para hacerla compatible con CPUs AMD.
 * Extiende {@link Componente} e implementa {@link GPU}, delegando las operaciones al componente original.
 */
public class AdaptadorGPUNvidia extends Componente implements GPU {

    private final GPU gpuIntel;

    /**
     * Construye un nuevo Adaptador de GPU para AMD basado en una GPU existente.
     *
     * @param gpuIntel la instancia original de {@link GPU} que se adaptará
     */
    public AdaptadorGPUNvidia(GPU gpuIntel) {
        super(
            ((Componente) gpuIntel).getModelo(),
            ((Componente) gpuIntel).getPrecio(),
            ((Componente) gpuIntel).getMarca(),
            TipoComponente.GPU
        );
        this.gpuIntel = gpuIntel;
    }

    /**
     * Obtiene la descripción de la GPU adaptada, indicando que ha sido adaptada para AMD.
     *
     * @return cadena con la descripción de la GPU adaptada
     */
    @Override
    public String getDescripcion() {
        return "[Adaptado a AMD] " + gpuIntel.getDescripcion();
    }

    /**
     * Obtiene el precio de la GPU subyacente.
     *
     * @return precio de la GPU original
     */
    @Override
    public double getPrecio() {
        return gpuIntel.getPrecio();
    }
}
