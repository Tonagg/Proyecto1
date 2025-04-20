package src.compatibilidad;

import src.adapter.AdaptadorMotherboardIntel;
import src.factory.Marca;
import src.Computadora;

/**
 * Regla que detecta y adapta CPU AMD con motherboards Intel/MSI.
 */
public class AMDMotherboardRegla implements ReglaCompatibilidad {
    @Override
    public boolean hayConflicto(Computadora pc) {
        if (pc.getCpu() == null || pc.getMotherboard() == null) return false;
        Marca cpuMarca = ((src.factory.Componente)pc.getCpu()).getMarca();
        Marca mbMarca  = ((src.factory.Componente)pc.getMotherboard()).getMarca();
        return cpuMarca == Marca.AMD && mbMarca != Marca.AMD
            && !(pc.getMotherboard() instanceof AdaptadorMotherboardIntel);
    }

    @Override
    public String getMensajeConflicto(Computadora pc) {
        Marca mbMarca = ((src.factory.Componente)pc.getMotherboard()).getMarca();
        return String.format("Motherboard %s usada con CPU AMD.", mbMarca);
    }

    @Override
    public boolean esAdaptable() { return true; }

    @Override
    public void aplicarAdaptacion(Computadora pc) {
        pc.setMotherboard(new AdaptadorMotherboardIntel(pc.getMotherboard()));
    }

    @Override
    public String getMensajeAdaptacion(Computadora pc) {
        return "Motherboard adaptada para CPU AMD.";
    }
}