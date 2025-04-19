/* src/decorator/PhotoshopDecorator.java */
package src.decorator;

import src.Computadora;

public class PhotoshopDecorator extends SoftwareDecorator {
    public PhotoshopDecorator(Computadora pc) {
        super(pc, "Adobe Photoshop", 1_200);
    }
}
