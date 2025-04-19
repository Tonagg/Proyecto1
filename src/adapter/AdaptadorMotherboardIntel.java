/* AdaptadorMotherboardIntel.java */
package src.adapter;

import src.factory.Motherboard;

public class AdaptadorMotherboardIntel implements Motherboard {

    private final Motherboard mb;

    public AdaptadorMotherboardIntel(Motherboard mb) { this.mb = mb; }

    @Override public String  getDescripcion() { return "[Adaptado] " + mb.getDescripcion(); }
    @Override public double  getPrecio()      { return mb.getPrecio(); }
}
