package src.decorator;

import src.Computadora;

public class AutocadDecorator extends SoftwareDecorator {
    public AutocadDecorator(Computadora pc) {
        super(pc, "Autocad", 1500);
    }
}
