// Main.java
public class Main {
    public static void main(String[] args) {
        ComponenteFactory factory = new intel_Nvidia(); // o new AmdFactory()
        ComputadoraModel model         = new ComputadoraModel(factory);
        ComputadoraView  view          = new ComputadoraView();
        ComputadoraController controller = new ComputadoraController(model, view);
        controller.iniciar();
    }
}
