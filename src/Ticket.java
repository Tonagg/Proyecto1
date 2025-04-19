/* src/Ticket.java */
package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Comprobante de compra inmutable.
 */
public final class Ticket {

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy‑MM‑dd HH:mm");

    private final Computadora    pc;
    private final double         total;
    private final String         notas;
    private final LocalDateTime  fechaEmision = LocalDateTime.now();

    public Ticket(Computadora pc, String notasCompatibilidad) {
        this.pc    = pc;
        this.total = pc.calcularPrecioTotal();
        this.notas = (notasCompatibilidad == null) ? "" : notasCompatibilidad.strip();
    }

    /** Representación lista para imprimir por consola. */
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

    /* -------- getters útiles para pruebas -------- */
    public Computadora   getPc()         { return pc;          }
    public double        getTotal()      { return total;       }
    public String        getNotas()      { return notas;       }
    public LocalDateTime getFecha()      { return fechaEmision;}
}
