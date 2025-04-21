package src.factory;

/**
 * Enumeración de categorías válidas de componentes de hardware gestionados en la aplicación.
 * Se utiliza para clasificar el tipo funcional de cada {@link Componente}.
 */
public enum TipoComponente {

    /** Unidad central de procesamiento (CPU). */
    CPU,
    /** Unidad de procesamiento gráfico (GPU). */
    GPU,
    /** Módulo de memoria RAM. */
    RAM,
    /** Placa base o motherboard. */
    MOTHERBOARD,
    /** Fuente de poder (PSU). */
    PSU,
    /** Dispositivo de almacenamiento (HDD/SSD). */
    STORAGE,
    /** Gabinete o chasis de la computadora. */
    CASE
}
