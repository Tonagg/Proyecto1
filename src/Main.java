package src;

import src.factory.ComponenteFactory;
import src.factory.intel_Nvidia;
import src.mvc.Controller;
import src.mvc.Model;
import src.mvc.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model(new intel_Nvidia());   // o new Amd()
        View  view  = new View();
        new Controller(model, view).iniciar();
    }
}