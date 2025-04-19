/* src/Main.java */
package src;

import src.factory.*;
import src.mvc.*;
import src.compatibilidad.*;

/**
 * Punto de entrada de la aplicación.
 * <pre>
 *   java -jar app.jar [--amd|--intel] [--estricto|--flex]
 * </pre>
 */
public final class Main {

    public static void main(String[] args) {

        /* ---------- Fábrica de componentes ---------- */
        ComponenteFactory factory = parseFactory(args);
        Model model              = new Model(factory);

        /* ---------- Estrategia de compatibilidad ----- */
        if (hasArg(args, "--estricto")) {
            model.setVerificador(new CompatibilidadEstricta());
        } else if (hasArg(args, "--flex")) {           // explícito, aunque es el default
            model.setVerificador(new CompatibilidadFlexible());
        }

        /* ---------- Despega el MVC ------------------ */
        View view = new View();
        new Controller(model, view).iniciar();
    }

    /* ===== helpers internos ===== */

    /** Devuelve la fábrica pedida por línea de comandos (Intel por defecto). */
    private static ComponenteFactory parseFactory(String[] args) {
        if (hasArg(args, "--amd"))   return new AmdFactory();
        if (hasArg(args, "--intel")) return new IntelNvidiaFactory();
        return new IntelNvidiaFactory();               // default
    }

    /** @return {@code true} si {@code flag} aparece en los argumentos. */
    private static boolean hasArg(String[] args, String flag) {
        for (String a : args) if (a.equalsIgnoreCase(flag)) return true;
        return false;
    }
}
