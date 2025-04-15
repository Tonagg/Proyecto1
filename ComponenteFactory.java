// ComponenteFactory.java
// Interfaz para la Abstract Factory, define métodos para obtener componentes.
import java.util.List;

public interface ComponenteFactory {
    List<CPU> getCPUs();
    List<GPU> getGPUs();
    List<RAM> getRAM();
    List<Motherboard> getMotherboard();
    List<Fuente> getFuente();
    List<Almacenamineto> getAlmacenamiento();
    List<Gabinete> getGabinete();

   
}
