public abstract class ComputadoraDecorator extends Computadora {
    protected Computadora computadora;

    public ComputadoraDecorator(Computadora computadora) {
        this.computadora = computadora;
    }

    @Override
    public double calcularPrecioTotal() {
        return computadora.calcularPrecioTotal();
    }

    @Override
    public String getDescripcion() {
        return computadora.getDescripcion();
    }
}
