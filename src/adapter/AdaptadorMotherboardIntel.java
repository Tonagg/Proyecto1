/* src/adapter/AdaptadorMotherboardIntel.java */
package src.adapter;

import src.factory.Motherboard;

/**
 * Adapter que permite usar una motherboard Intel
 * (chipset Z790/B760, etc.) con una CPU AMD.
 */
public final class AdaptadorMotherboardIntel implements Motherboard {

    private final Motherboard mb;

    public AdaptadorMotherboardIntel(Motherboard mb) {
        this.mb = mb;
    }

    /* ——— delegaciones ——— */
    @Override public String  getDescripcion() { return "[Adaptado] " + mb.getDescripcion(); }
    @Override public double  getPrecio()      { return mb.getPrecio(); }
    @Override public String  getChipset()     { return mb.getChipset(); }

    @Override public String toString() {
        return getDescripcion();
    }
}

