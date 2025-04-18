package src.decorator;
import src.Computadora;
import src.ComputadoraDecorator;


public class WindowsDecorator extends ComputadoraDecorator {

    public WindowsDecorator(Computadora computadora) {
        super(computadora);
    }

    @Override
    public double calcularPrecioTotal() {
        // Aumenta el precio en 1000 por el sistema operativo Windows
        return super.calcularPrecioTotal() + 1000;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Windows 11";
    }
}
