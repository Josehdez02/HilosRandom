import java.util.Random;

public class Caballo {
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
                        matrizCarrera[fila][i] = '*'; // El caballo está en esta posición
                    } else {
                        matrizCarrera[fila][i] = '-'; // No hay caballo en esta posición
                    }
                }
                // Imprimir el progreso de la carrera en la consola
                System.out.print("\033[H\033[2J"); // Limpiar la pantalla
                for (int i = 0; i < numCaballos; i++) {
                    System.out.println("Caballo " + (i+1) + " " + new String(matrizCarrera[i])); // Imprimir la línea que corresponde al caballo actual
                }
