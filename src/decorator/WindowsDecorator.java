/* WindowsDecorator.java */
package src.decorator;

import src.Computadora;

public class WindowsDecorator extends SoftwareDecorator {
    public WindowsDecorator(Computadora pc) {
        super(pc, "Windows 11", 1000);
    }
}
