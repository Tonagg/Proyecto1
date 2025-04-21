package src.adapter;

import src.factory.Componente;
import src.factory.Motherboard;
import src.factory.TipoComponente;

/**
 * Adaptador que envuelve una Motherboard Intel para hacerla compatible con CPUs AMD.
 * Extiende {@link Componente} e implementa {@link Motherboard}, delegando las operaciones al componente original.
 */
public final class AdaptadorMotherboardIntel extends Componente implements Motherboard {

    /**
     * La instancia original de {@link Motherboard} que será adaptada.
     */
    private final Motherboard mb;

    /**
     * Construye un nuevo adaptador de Motherboard Intel a AMD.
     *
     * @param mb la placa base original que se adaptará
     */
    public AdaptadorMotherboardIntel(Motherboard mb) {
        super(
            ((Componente) mb).getModelo(),
            ((Componente) mb).getPrecio(),
            ((Componente) mb).getMarca(),
            TipoComponente.MOTHERBOARD
        );
        this.mb = mb;
    }

    /**
     * Obtiene la descripción de la placa base adaptada, indicando que ha sido adaptada.
     *
     * @return cadena con la descripción de la placa base adaptada
     */
    @Override
    public String getDescripcion() {
        return "[Adaptado] " + mb.getDescripcion();
    }

    /**
     * Obtiene el precio de la placa base subyacente.
     *
     * @return precio de la placa base original
     */
    @Override
    public double getPrecio() {
        return mb.getPrecio();
    }

    /**
     * Obtiene el chipset de la placa base subyacente.
     *
     * @return nombre del chipset de la placa base original
     */
    @Override
    public String getChipset() {
        return mb.getChipset();
    }
}
