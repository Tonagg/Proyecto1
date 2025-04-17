

public class GPUnvidia extends Componente implements GPU {
    private String tipoDeMemoria;

    public GPUnvidia(String nombre, double precio, String marca, String tipoComponente, String tipoDeMemoria) {
        // La marca se fija en "Intel"
        super(nombre, precio, "Nvidia", "GPU");
        this.tipoDeMemoria = tipoDeMemoria;
    }

    @Override
    public String getDescripcion() {
        return nombre + "de marca:" + marca + "con " + tipoDeMemoria +"tipo de memoria"  + " PRECIO:" + precio;
    
    }
    public double getPrecio() {
        return precio;
    }
}