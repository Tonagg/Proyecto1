package src.factory;

/**
 * Implementación de {@link GPU} para tarjetas gráficas NVIDIA.
 * Extiende {@link Componente} proporcionando el tipo de memoria especializado.
 */
public final class GPUNvidia extends Componente implements GPU {

    /** Tipo de memoria de la GPU (e.g., GDDR6, GDDR6X) */
    private final String tipoMemoria;

    /**
     * Construye una nueva instancia de GPUNvidia con los detalles especificados.
     *
     * @param modelo       nombre o modelo comercial de la GPU
     * @param precio       precio de venta al público
     * @param tipoMemoria  tipo de memoria gráfica (por ejemplo, "GDDR6X")
     */
    public GPUNvidia(String modelo, double precio, String tipoMemoria) {
        super(modelo, precio, Marca.NVIDIA, TipoComponente.GPU);
        this.tipoMemoria = tipoMemoria;
    }

    /**
     * Obtiene la descripción técnica detallada de la GPU, incluyendo capacidad de memoria.
     *
     * @return cadena con la descripción de la GPU y su tipo de memoria
     */
    @Override
    public String getDescripcion() {
        return String.format("%s | memoria %s", super.toString(), tipoMemoria);
    }
}