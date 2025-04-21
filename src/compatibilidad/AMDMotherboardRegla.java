package src.compatibilidad;

import src.adapter.AdaptadorMotherboardIntel;
import src.factory.Marca;
import src.Computadora;

/**
 * Regla de compatibilidad que detecta conflictos entre CPUs AMD y motherboards de marca Intel o MSI,
 * y permite adaptar la placa base usando un adaptador específico.
 */
public class AMDMotherboardRegla implements ReglaCompatibilidad {

    /**
     * Verifica si existe un conflicto de compatibilidad entre una CPU AMD y una motherboard de diferente marca
     * que no esté ya adaptada.
     *
     * @param pc la computadora a evaluar
     * @return {@code true} si la CPU es AMD y la placa base no es AMD ni un adaptador; {@code false} en otro caso
     */
    @Override
    public boolean hayConflicto(Computadora pc) {
        if (pc.getCpu() == null || pc.getMotherboard() == null) {
            return false;
        }
        Marca cpuMarca = ((src.factory.Componente) pc.getCpu()).getMarca();
        Marca mbMarca  = ((src.factory.Componente) pc.getMotherboard()).getMarca();
        return cpuMarca == Marca.AMD
            && mbMarca != Marca.AMD
            && !(pc.getMotherboard() instanceof AdaptadorMotherboardIntel);
    }

    /**
     * Genera el mensaje que describe el tipo de conflicto detectado.
     *
     * @param pc la computadora con conflicto
     * @return mensaje indicando la marca de la motherboard incompatible
     */
    @Override
    public String getMensajeConflicto(Computadora pc) {
        Marca mbMarca = ((src.factory.Componente) pc.getMotherboard()).getMarca();
        return String.format("Motherboard %s usada con CPU AMD.", mbMarca);
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
     * Aplica la adaptación a la computadora, envolviendo la motherboard con un adaptador Intel-to-AMD.
     *
     * @param pc la computadora cuya motherboard se adaptará
     */
    @Override
    public void aplicarAdaptacion(Computadora pc) {
        pc.setMotherboard(new AdaptadorMotherboardIntel(pc.getMotherboard()));
    }

    /**
     * Devuelve el mensaje que describe la adaptación realizada.
     *
     * @param pc la computadora adaptada
     * @return mensaje indicando que la motherboard se adaptó para CPU AMD
     */
    @Override
    public String getMensajeAdaptacion(Computadora pc) {
        return "Motherboard adaptada para CPU AMD.";
    }
}
