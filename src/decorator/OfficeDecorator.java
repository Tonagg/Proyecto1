package src.decorator;
import src.Computadora;
import src.ComputadoraDecorator;

public class OfficeDecorator extends ComputadoraDecorator {

    public OfficeDecorator(Computadora computadora) {
        super(computadora);
    }

    @Override
    public double calcularPrecioTotal() {
        return super.calcularPrecioTotal() + 800;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Office 365";
    }
}
