
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Caballo.inicializarMatrizCarrera(5);
        Caballo[] caballos = new Caballo[5];
        for (int i = 0; i < caballos.length; i++) {
            caballos[i] = new Caballo("Caballo " + (i+1));
        }
        for (Caballo caballo : caballos) {
            caballo.start();
        }
    }
}
