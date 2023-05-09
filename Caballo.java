import java.util.Random;

public class Caballo extends Thread{
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static String caballoGanador;
    private static int caballosTerminados;
    private static int distanciaTotal = 100; // La distancia total de la carrera
    private static int numCaballos;
    private String nombre;
    private int distanciaRecorrida;
    private Random random = new Random();
    private static char[][] matrizCarrera;

    public Caballo(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        System.out.println(nombre + " empieza la carrera!");
        while (distanciaRecorrida < distanciaTotal) {
            distanciaRecorrida += random.nextInt(10); // El caballo avanza una distancia aleatoria entre 0 y 9
            try {
                Thread.sleep(random.nextInt(1000)); // El caballo espera un tiempo aleatorio entre 0 y 999 milisegundos antes de volver a avanzar
            } catch (InterruptedException e) {
                e.getMessage();// El hilo ha sido interrumpido, no hacemos nada especial
            }
            // Actualizar la matriz de la carrera con la posición del caballo actual
            synchronized (Caballo.class) {
                int fila = Integer.parseInt(nombre.substring(8)) - 1; // La fila que corresponde al caballo actual
                for (int i = 0; i < distanciaTotal; i++) {
                    if (i == distanciaRecorrida) {
                        matrizCarrera[fila][i] = '&'; // El caballo está en esta posición
                    } else {
                        matrizCarrera[fila][i] = '-'; // No hay caballo en esta posición
                    }
                }
                // Imprimir el progreso de la carrera en la consola
                System.out.print("\033[H\033[2J"); // Limpiar la pantalla
                for (int i = 0; i < numCaballos; i++) {
                    System.out.println(ANSI_BLACK+"Caballo " + (i + 1) + " " +ANSI_RESET+ANSI_BLUE+ new String(matrizCarrera[i])); // Imprimir la línea que corresponde al caballo actual
                }
                System.out.flush();
            }
            try {
                Thread.sleep(100); // Esperar un segundo antes de imprimir la siguiente línea
            } catch (InterruptedException e) {
                // El hilo ha sido interrumpido, no hacemos nada especial
            }
        }
        // Actualizar el caballo ganador
        synchronized (Caballo.class) {
            if (Caballo.caballoGanador == null) {
                Caballo.caballoGanador = nombre;
            }
        }
        System.out.println(nombre + " ha llegado a la meta!");
        // Imprimir el caballo ganador si todos los caballos han terminado la carrera
        synchronized (Caballo.class ) {
            if (++Caballo.caballosTerminados == numCaballos){
                System.out.println(ANSI_RED+"El caballo ganador es: "+Caballo.caballoGanador);
            }}
    }

    public static void inicializarMatrizCarrera(int n) {
        numCaballos = n;
        matrizCarrera = new char[n][distanciaTotal];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < distanciaTotal; j++) {
                matrizCarrera[i][j] = '-';
            }
        }
    }
}
