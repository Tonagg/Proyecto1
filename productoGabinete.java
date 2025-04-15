


public class productoGabinete extends Componente implements Gabinete {
    

    public productoGabinete (String nombre, double precio, String marca, String tipoComponente) {
        // La marca se fija en "Intel"
        super(nombre, precio, marca, "Gabinete");
    }

    @Override
    public String getDescripcion() {
        return nombre ;
    }
}