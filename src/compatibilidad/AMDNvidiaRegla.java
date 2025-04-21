package src.compatibilidad;

import src.adapter.AdaptadorGPUNvidia;
import src.factory.Marca;
import src.Computadora;

/**
 * Regla de compatibilidad que detecta conflictos entre CPUs AMD y GPUs Nvidia,
 * y permite adaptar la GPU usando un adaptador específico.
 */
public class AMDNvidiaRegla implements ReglaCompatibilidad {

    /**
     * Verifica si existe un conflicto de compatibilidad: CPU AMD con GPU Nvidia
     * que no esté ya adaptada.
     *
     * @param pc la computadora a evaluar
     * @return {@code true} si la CPU es AMD y la GPU es Nvidia sin adaptador; {@code false} en caso contrario
     */
    @Override
    public boolean hayConflicto(Computadora pc) {
        if (pc.getCpu() == null || pc.getGpu() == null) {
            return false;
        }
        Marca cpuMarca = ((src.factory.Componente) pc.getCpu()).getMarca();
        Marca gpuMarca = ((src.factory.Componente) pc.getGpu()).getMarca();
        return cpuMarca == Marca.AMD
            && gpuMarca == Marca.NVIDIA
            && !(pc.getGpu() instanceof AdaptadorGPUNvidia);
    }

    /**
     * Genera el mensaje que describe el conflicto detectado.
     *
     * @param pc la computadora con conflicto
     * @return mensaje indicando que la GPU Nvidia no está adaptada para CPU AMD
     */
    @Override
    public String getMensajeConflicto(Computadora pc) {
        return "CPU AMD con GPU Nvidia sin adaptar.";
    }

    /**
     * Indica que este conflicto es adaptable mediante un adaptador.
     *
     * @return siempre {@code true}
     */
    @Override
    public boolean esAdaptable() {
        return true;
    }

    /**
     * Aplica la adaptación a la computadora, envolviendo la GPU con un adaptador Nvidia-to-AMD.
     *
     * @param pc la computadora cuya GPU se adaptará
     */
    @Override
    public void aplicarAdaptacion(Computadora pc) {
        pc.setGpu(new AdaptadorGPUNvidia(pc.getGpu()));
    }

    /**
     * Devuelve el mensaje que describe la adaptación realizada.
     *
     * @param pc la computadora adaptada
     * @return mensaje indicando que la GPU Nvidia se adaptó para CPU AMD
     */
    @Override
    public String getMensajeAdaptacion(Computadora pc) {
        return "GPU Nvidia adaptada para CPU AMD.";
    }
}
