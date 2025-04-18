package src;

import java.time.LocalDateTime;
import src.Computadora;

public class Ticket {
    private final Computadora pc;
    private final double total;
    private final String notas;
    private final LocalDateTime fecha = LocalDateTime.now();

    public Ticket(Computadora pc, String notasCompatibilidad) {
        this.pc    = pc;
        this.total = pc.calcularPrecioTotal();
        this.notas = notasCompatibilidad;
    }

    public String imprimir() {
        return """
               ========== TICKET ==========
               Fecha/hora: %s
               ---------------------------
               %s
               ---------------------------
               Precio total: $%.2f
               %s
               ===========================
               """.formatted(
                    fecha,
                    pc.getDescripcion(),
                    total,
                    notas.isBlank() ? "" : "Notas: " + notas
               );
    }
}
