package src.factory;

/**
 * Implementación de {@link Motherboard} para placas base de la línea Intel.
 * Extiende {@link Componente} y proporciona el atributo específico de chipset.
 */
public final class MotherboardIntel extends Componente implements Motherboard {

    /**
     * Identificador del chipset soportado por la placa base.
     */
    private final String chipset;

    /**
     * Construye una nueva placa base Intel con las especificaciones dadas.
     *
     * @param modelo nombre o modelo comercial de la placa base
     * @param precio precio de venta al público
     * @param marca  marca del fabricante (e.g., ASUS, MSI)
     * @param chipset nombre del chipset soportado
     */
    public MotherboardIntel(String modelo, double precio, Marca marca, String chipset) {
        super(modelo, precio, marca, TipoComponente.MOTHERBOARD);
        this.chipset = chipset;
    }

    /**
     * Obtiene el chipset soportado por la placa base.
     *
     * @return nombre del chipset
     */
    @Override
    public String getChipset() {
        return chipset;
    }

    /**
     * Obtiene la descripción técnica detallada de la placa base,
     * incluyendo el modelo y el chipset.
     *
     * @return cadena con descripción de la placa base
     */
    @Override
    public String getDescripcion() {
        return String.format("%s | chipset %s", super.toString(), chipset);
    }
}