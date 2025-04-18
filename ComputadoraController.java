// ComputadoraController.java
public class ComputadoraController {
    private final ComputadoraModel model;
    private final ComputadoraView view;

    public ComputadoraController(ComputadoraModel model, ComputadoraView view) {
        this.model = model;
        this.view  = view;
    }

    public void iniciar() {
        int opcion = view.mostrarMenu();
        Computadora pc;
        if (opcion == 1) {
            pc = model.crearComputadoraPersonalizada();
        } else {
            String modelo = view.solicitarModeloPrearmada();
            pc = model.crearComputadoraPrearmada(modelo);
        }

        // Bucle para agregar software via Decorator
        while (view.confirmarAgregarSoftware()) {
            int sw = view.seleccionarSoftware();
            switch (sw) {
                case 1:
                    pc = new WindowsDecorator(pc);
                    break;
                case 2:
                    pc = new OfficeDecorator(pc);
                    break;
                case 3:
                    pc = new PhotoshopDecorator(pc);
                    break;
                case 4:
                    pc = new WSLDecorator(pc);  // asumiendo que ya tienes este decorador
                    break;
                case 0:
                    // sale del bucle
                    sw = 0;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            if (sw == 0) break;
        }

        view.mostrarComputadora(pc);
    }
}
