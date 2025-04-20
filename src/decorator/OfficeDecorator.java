/* src/decorator/OfficeDecorator.java */
package src.decorator;

import src.Computadora;

public class OfficeDecorator extends SoftwareDecorator {
    public OfficeDecorator(Computadora pc) {
        super(pc, "Microsoft Office 365", 500);
    }
}
