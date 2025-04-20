// src/adapter/AdaptadorMotherboardIntel.java
package src.adapter;

import src.factory.Componente;
import src.factory.Motherboard;
import src.factory.TipoComponente;

public final class AdaptadorMotherboardIntel extends Componente implements Motherboard {
    private final Motherboard mb;

    public AdaptadorMotherboardIntel(Motherboard mb) {
        super(
          ((Componente)mb).getModelo(),
          ((Componente)mb).getPrecio(),
          ((Componente)mb).getMarca(),
          TipoComponente.MOTHERBOARD
        );
        this.mb = mb;
    }

    @Override public String getDescripcion() { return "[Adaptado] " + mb.getDescripcion(); }
    @Override public double getPrecio()      { return mb.getPrecio(); }
    @Override public String getChipset()     { return mb.getChipset(); }
}
