package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un comprobante de compra inmutable para una computadora ensamblada.
 * Incluye fecha de emisión, descripción de los componentes, precio total y notas opcionales.
 */
public final class Ticket {

    /** Formato para mostrar la fecha y hora de emisión del ticket. */
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** Computadora asociada al ticket. */
    private final Computadora pc;
    /** Precio total calculado de la computadora. */
    private final double total;
    /** Notas adicionales de compatibilidad o adaptación. */
    private final String notas;
    /** Fecha y hora de emisión del ticket. */
    private final LocalDateTime fechaEmision = LocalDateTime.now();

    /**
     * Construye un ticket con la computadora y notas proporcionadas.
     * Calcula el precio total en el momento de creación.
     *
     * @param pc                 computadora ensamblada
     * @param notasCompatibilidad notas sobre adaptaciones o conflictos resueltos (puede ser null)
     */
    public Ticket(Computadora pc, String notasCompatibilidad) {
        this.pc    = pc;
        this.total = pc.calcularPrecioTotal();
        this.notas = (notasCompatibilidad == null) ? "" : notasCompatibilidad.strip();
    }

    /**
     * Genera la representación del ticket lista para imprimir por consola.
     *
     * @return cadena formateada con fecha, componentes, precio y notas
     */
    public String imprimir() {
        String ln = System.lineSeparator();
        return new StringBuilder()
                .append("========== TICKET ==========").append(ln)
                .append("Fecha/hora: ").append(FMT.format(fechaEmision)).append(ln)
                .append("---------------------------").append(ln)
                .append(pc.getDescripcion()).append(ln)
                .append("---------------------------").append(ln)
                .append("Precio total: $").append(String.format("%,.2f", total)).append(ln)
                .append(notas.isBlank() ? "" : "Notas: " + notas + ln)
                .append("===========================").append(ln)
                .toString();
    }

    // -------- getters útiles para pruebas --------

    /**
     * @return computadora asociada al ticket
     */
    public Computadora getPc()     { return pc; }

    /**
     * @return precio total registrado en el ticket
     */
    public double getTotal()       { return total; }

    /**
     * @return notas de compatibilidad o adaptaciones
     */
    public String getNotas()       { return notas; }

    /**
     * @return fecha y hora de emisión del ticket
     */
    public LocalDateTime getFecha(){ return fechaEmision; }
}
