package src.factory;

import java.util.Collection;

public interface ComponenteFactory {

    /* --- Peticiones puntuales --- */
    CPU          cpu         (String modelo);
    GPU          gpu         (String modelo);
    RAM          ram         (int capacidadGB, Marca marca);
    Motherboard  motherboard (String modelo);
    FuenteDePoder fuente     (int potenciaW);
    Almacenamiento ssd       (int capacidadGB);
    Almacenamiento hdd       (int capacidadGB);
    Gabinete     gabinete    (String modelo);

    /* --- Catálogos completos (para la UI) --- */
    Collection<CPU>          catalogoCPU();
    Collection<GPU>          catalogoGPU();
    Collection<RAM>          catalogoRAM();
    Collection<Motherboard>  catalogoMotherboard();
    Collection<FuenteDePoder> catalogoPSU();
    Collection<Almacenamiento> catalogoStorage();
    Collection<Gabinete>     catalogoGabinetes();

    /* --- Regla simple de compatibilidad.  Override si es necesario --- */
    default boolean compatibles(CPU cpu, Motherboard mb) {
        return (cpu instanceof Componente c1) &&
               (mb  instanceof Componente c2) &&
               c1.getMarca() == c2.getMarca();
    }
}
