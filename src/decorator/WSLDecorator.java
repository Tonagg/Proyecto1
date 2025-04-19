/* WSLDecorator.java */
package src.decorator;

import src.Computadora;

public class WSLDecorator extends SoftwareDecorator {
    public WSLDecorator(Computadora pc) {
        super(pc, "WSL / Terminal hacker", 1000); // asumí costo cero; ajusta si necesitas
    }
}
