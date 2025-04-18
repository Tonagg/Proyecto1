package src;

import src.factory.ComponenteFactory;
import src.factory.intel_Nvidia;
import src.mvc.Controller;
import src.mvc.Model;
import src.mvc.View;

// Main.java
public class Main {
    public static void main(String[] args) {
        ComponenteFactory factory = new intel_Nvidia(); // o new AmdFactory()
        Model model         = new Model(factory);
        View  view          = new View();
        Controller controller = new Controller(model, view);
        controller.iniciar();
    }
}
