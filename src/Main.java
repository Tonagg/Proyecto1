package src;

import src.factory.ComponenteFactory;
import src.factory.IntelNvidiaFactory;
import src.mvc.Controller;
import src.mvc.Model;
import src.mvc.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model(new IntelNvidiaFactory());   // o new Amd()
        View  view  = new View();
        new Controller(model, view).iniciar();
    }
}