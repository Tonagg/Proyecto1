package src.mvc;
// ComputadoraModel.java
import java.util.List;
import src.decorator.*;
import src.factory.Almacenamiento;
import src.factory.CPU;
import src.factory.FuenteDePoder;
import src.factory.GPU;
import src.factory.Gabinete;
import src.factory.Motherboard;
import src.factory.RAM;
import src.*;
import src.builder.ComputadoraDirector;
import src.builder.ComputadoraPersonalizadaBuilder;
import src.builder.ComputadoraPrearmadaBuilder;


public class Model {
    private final ComponenteFactory factory;
    private ComputadoraDirector director;

    public Model(ComponenteFactory factory) {
        this.factory = factory;
    }

    public Computadora crearComputadoraPersonalizada() {
        ComputadoraPersonalizadaBuilder builder = new ComputadoraPersonalizadaBuilder();
        director = new ComputadoraDirector(builder);

        CPU cpu = factory.getCPUs().get(0);            // ejemplo
        List<RAM> rams = factory.getRAM().subList(0, 4);
        GPU gpu = factory.getGPUs().get(0);
        List<Almacenamiento> discos = factory.getAlmacenamiento().subList(0, 1);
        FuenteDePoder fuente = factory.getFuente().get(0);
        Motherboard mb = factory.getMotherboard().get(0);
        Gabinete gab = factory.getGabinete().get(0);

        director.construirComputadora(cpu, rams, gpu, discos, fuente, mb, gab);
        return director.obtenerComputadora();
    }

    public Computadora crearComputadoraPrearmada(String modelo) {
        ComputadoraPrearmadaBuilder builder = new ComputadoraPrearmadaBuilder(factory, modelo);
        return builder.obtenerComputadora();
    }
}
