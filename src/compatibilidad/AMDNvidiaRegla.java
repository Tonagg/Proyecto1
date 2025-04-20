package src.compatibilidad;

import src.adapter.AdaptadorGPUNvidia;
import src.factory.Marca;
import src.Computadora;

/**
 * Regla que detecta y adapta CPU AMD con GPU Nvidia.
 */
public class AMDNvidiaRegla implements ReglaCompatibilidad {
    @Override
    public boolean hayConflicto(Computadora pc) {
        if (pc.getCpu() == null || pc.getGpu() == null) return false;
        Marca cpuMarca = ((src.factory.Componente)pc.getCpu()).getMarca();
        Marca gpuMarca = ((src.factory.Componente)pc.getGpu()).getMarca();
        return cpuMarca == Marca.AMD && gpuMarca == Marca.NVIDIA
            && !(pc.getGpu() instanceof AdaptadorGPUNvidia);
    }

    @Override
    public String getMensajeConflicto(Computadora pc) {
        return "CPU AMD con GPU Nvidia sin adaptar.";
    }

    @Override
    public boolean esAdaptable() { return true; }

    @Override
    public void aplicarAdaptacion(Computadora pc) {
        pc.setGpu(new AdaptadorGPUNvidia(pc.getGpu()));
    }

    @Override
    public String getMensajeAdaptacion(Computadora pc) {
        return "GPU Nvidia adaptada para CPU AMD.";
    }
}