package src.compatibilidad;
import src.Computadora;

/**
 * Estrategia que revisa si los componentes de una computadora
 * son compatibles y, si no lo son, puede intentar adaptarlos.
 */
public interface VerificaCompatibilidad {

    /**
     * @param pc computadora ya armada (sin software)
     * @return true  -> todo es compatible  
     *         false -> se detectó al menos un conflicto
     */
    boolean esCompatible(Computadora pc);

    /**
     * Intenta forzar compatibilidad cambiando piezas por adaptadores.
     * @param pc computadora sobre la que actuar
     * @return  String con las notas de lo que se adaptó ("" si nada)
     */
    String adaptar(Computadora pc);
}
