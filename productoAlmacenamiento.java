


public class productoAlmacenamiento extends Componente implements Almacenamineto {
    private int capacidadAlamacenamiento;
     private String tipodeAlmacenamiento;


    public productoRAM (String nombre, double precio, String marca, String tipoComponente, int capacidadAlamacenamiento, String tipodeAlmacenamiento) {
        // La marca se fija en "Intel"
        super(nombre, precio, marca, "Almacenamineto",  );
        this.capacidadAlamacenamiento = capacidadAlamacenamiento;
        this.tipodeAlmacenamiento= tipodeAlmacenamiento;




    @Override
    public String getDescripcion() {
        return nombre + " (" + tamanioDeMemoria + " Tamanio de Memoria, " + marca + ")";
    }
}