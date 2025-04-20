package src.factory;

import java.util.Collection;

/**
 * Interfaz que define una fabrica abstracta para crear componentes de hardware
 * y gestionar su compatibilidad basica. Proporciona metodos para crear componentes
 * individuales y obtener catalogos completos.
 */
public interface ComponenteFactory {

    /**
     * Crea una unidad central de procesamiento (CPU).
     * 
     * @param modelo Identificador del modelo
     * @return Instancia de CPU configurada
     * @throws IllegalArgumentException si el modelo no existe
     */
    CPU          cpu         (String modelo);

    /**
     * Crea una unidad de procesamiento grafico (GPU).
     * 
     * @param modelo Identificador del modelo
     * @return Instancia de GPU configurada
     * @throws IllegalArgumentException si el modelo no existe
     */
    GPU          gpu         (String modelo);

    /**
     * Crea un modulo de memoria RAM 
     * 
     * @param capacidadGB Capacidad en gigabytes
     * @param marca Fabricante de la memoria 
     * @return Instancia de RAM configurada
     * @throws IllegalArgumentException si el modelo no existe
     */
    RAM          ram         (int capacidadGB, Marca marca);

    /**
     * Crea una placa base
     * 
     * @param modelo Identificador del modelo
     * @return Instancia de Motherboard configurada
     * @throws IllegalArgumentException si el modelo no existe
     */
    Motherboard  motherboard (String modelo);

    /**
     * Crea una fuente de poder.
     * 
     * @param potenciaW Potencia en vatios
     * @return Instancia de FuenteDePoder configurada
     */
    FuenteDePoder fuente     (String modeloClave);

    /**
     * Crea una unidad de estado solido (SSD).
     * 
     * @param capacidadGB Capacidad en gigabytes
     * @return Instancia de Almacenamiento tipo SSD
     */
    Almacenamiento ssd       (int capacidadGB);

    /**
     * Crea un disco duro mecanico (HDD).
     * 
     * @param capacidadGB Capacidad en gigabytes
     * @return Instancia de Almacenamiento tipo HDD
     */
    Almacenamiento hdd       (int capacidadGB);

    /**
     * Crea un gabinete
     * 
     * @param modelo Identificador del modelo
     * @return Instancia de Gabinete configurado
     */
    Gabinete     gabinete    (String modelo);

    /**
     * Obtiene todos los modelos de CPU disponibles.
     * @return Coleccion no modificable de CPUs
     */
    Collection<CPU>          catalogoCPU();

    /**
     * Obtiene todos los modelos de GPU disponibles.
     * @return Coleccion no modificable de GPUs
     */
    Collection<GPU>          catalogoGPU();

    /**
     * Obtiene configuraciones de RAM disponibles.
     * @return Coleccion no modificable de modulos RAM
     */
    Collection<RAM>          catalogoRAM();

    /**
     * Obtiene todos los modelos de placas base disponibles.
     * @return Coleccion no modificable de Motherboards
     */
    Collection<Motherboard>  catalogoMotherboard();

    /**
     * Obtiene todas las fuentes de poder disponibles.
     * @return Coleccion no modificable de FuentesDePoder
     */
    Collection<FuenteDePoder> catalogoPSU();

    /**
     * Obtiene todas las unidades de almacenamiento disponibles.
     * @return Coleccion no modificable de dispositivos de almacenamiento
     */
    Collection<Almacenamiento> catalogoStorage();
    
    /**
     * Obtiene todos los gabinetes disponibles.
     * @return Coleccion no modificable de Gabinetes
     */
    Collection<Gabinete>     catalogoGabinetes();

    /**
     * Verifica compatibilidad basica entre CPU y Motherboard.
     * 
     * @param cpu Procesador a verificar
     * @param mb Placa base a verificar
     * @return true si son compatibles segun las reglas de la fabrica
     */
    default boolean compatibles(CPU cpu, Motherboard mb) {
        return (cpu instanceof Componente c1) &&
               (mb  instanceof Componente c2) &&
               c1.getMarca() == c2.getMarca();
    }
}
