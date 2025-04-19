/* src/decorator/WSLDecorator.java */
package src.decorator;

import src.Computadora;

public class WSLDecorator extends SoftwareDecorator {
    public WSLDecorator(Computadora pc) {
        super(pc, "WSL / Terminal Hacker", 1000);
    }
}
