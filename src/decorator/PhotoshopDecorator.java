package src.decorator;
import src.Computadora;

public class PhotoshopDecorator extends ComputadoraDecorator {
    public PhotoshopDecorator(Computadora computadora) {
        super(computadora);
    }

    @Override
    public double calcularPrecioTotal() {
        return super.calcularPrecioTotal() + 1200;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Adobe Photoshop";
    }
}
