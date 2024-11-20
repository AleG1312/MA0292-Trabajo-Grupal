
//Ingreso la librería para poder solicitarle datos al usuario
import java.util.Scanner;
//Ingreso la librería para poder limpiar la pantalla en plena ejecución
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        Matriz matrizOriginal = definirMatrizInicial(); // Defino la matriz con la que empezaré el juego
        matrizOriginal.detalles("Matriz original: "); // Detalles de la matriz original

        Matriz matrizEnModificacion = new Matriz(); // Creo una copia. Será la matriz a la que se le harán las
                                                    // modificaciones
        matrizEnModificacion.mapearDatos(matrizOriginal.getSistema());

        Jugador jugador1 = new Jugador("Jugador 1"); // Creo mis jugadores
        Jugador jugador2 = new Jugador("Jugador 2");
        Jugador jugador3 = new Jugador("Jugador 3");

        Jugador[] jugadores = new Jugador[] { jugador1, jugador2, jugador3 }; // Creo un array con mis jugadores

        int[] determinantes = new int[15]; // Creo mi lista de determinantes y mi determinante original
        int determinanteOriginal = matrizOriginal.getDeterminante();
        int determinanteAnterior = determinanteOriginal;

        int rondas = 5; // Información importante para el juego
        int iteracion = 0;

        /**
         * Lógica del juego
         */
        for (int ronda = 0; ronda < rondas; ronda++) { // Para el número de rondas especificado inicialmente
            for (Jugador jugador : jugadores) { // Para los jugadores definidos
                // System.out.println("\n------------------Ronda: " + ronda + " | " +
                // jugador.getNombre() + "------------------");
                if (jugador.esTurnoActivo()) { // Si el jugador está vivo
                    boolean repiteTurno = false;
                    do {
                        determinantes = jugada(ronda, jugador, matrizEnModificacion, determinantes, iteracion); // Función
                                                                                                                // de
                                                                                                                // realizar
                                                                                                                // jugada
                        int determinanteActual = determinantes[iteracion];

                        visualizarTabero(matrizOriginal, matrizEnModificacion, determinantes, determinanteActual,
                                determinanteAnterior);

                        repiteTurno = condicionSiguienteTurno(jugador, determinanteActual, determinanteAnterior,
                                determinanteOriginal, determinantes, iteracion);

                        determinanteAnterior = determinanteActual;
                    } while (repiteTurno);

                    jugador.setPuntajeTotal(jugador.calcularPuntaje(determinanteAnterior, determinanteOriginal));
                    System.out.println(jugador.getNombre() + " Puntaje total = " + jugador.getPuntajeTotal());

                } else { // Si el jugador no está vivo
                    System.out.println("\n------------------Ronda: " + ronda + "  |  " + jugador.getNombre()
                            + "------------------");
                    System.out.println("No está vivo. Jugará en el siguiente turno.");
                    jugador.setTurnoActivo(true); // Si no está vivo en el turno actual, lo estará en el siguiente
                }
                iteracion++;
                System.out.println("\nIngrese 'enter' para pasar a la siguiente ronda");
                input.nextLine();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Limpio la pantalla
            }
        }
        System.out.println("Fin del juego");
        input.close();
    }

    private static Matriz definirMatrizInicial() throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        Matriz matriz = new Matriz();

        matriz.generarMatriz(); // Genero mi primera matriz
        matriz.detalles("Matriz Tentativa:"); // Se muestran los resultados en pantalla

        int eleccion = 0;
        do { // Ciclo para generar otra matriz en caso deseado
            System.out.println("\n¿Desea generar otra matriz? (1:Si, 2:No)");
            eleccion = input.nextInt();
            switch (eleccion) {
                case 1:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Limpio la pantalla
                    matriz.generarMatriz();
                    matriz.detalles("Matriz Tentativa:");
                    break;
                case 2:
                    break;
                default:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Limpio la pantalla
                    matriz.detalles("Matriz Tentativa:");
                    System.out.println("\nPor favor ingrese una opción válida.");
                    break;
            }
        } while (eleccion != 2);
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Limpio la pantalla
        input.close();
        return matriz;
    }

    // Solo entra si el jugador está vivo
    private static int[] jugada(int ronda, Jugador jugador, Matriz matrizEnModificacion, int[] determinantes,
            int iteracion) { // Esta función se encarga únicamente de realizar la jugada
        System.out
                .println("\n------------------Ronda: " + ronda + "  |  " + jugador.getNombre() + "------------------");
        matrizEnModificacion.cambiarEntrada(jugador.solicitarComponentes()); // Cambio de entrada en la matriz
        determinantes[iteracion] = matrizEnModificacion.getDeterminante();
        return determinantes;
    }

    private static boolean condicionSiguienteTurno(Jugador jugador, int determinanteActual, int determinanteAnterior,
            int determinanteOriginal, int[] determinantes, int indice) {
        boolean condicion1 = (determinanteActual == determinanteOriginal); // Determinante del turno actual igual al
                                                                           // determinante original
        boolean condicion2 = (determinanteActual == determinanteAnterior); // Determinante del turno actual igual al
                                                                           // determinante anterior
        boolean condicion3 = (seEncuentra(determinantes, determinanteActual, indice)); // Determinante del turno actual
                                                                                       // repite un valor anterior
        boolean repiteTurno = false;
        System.out.println();
        if (condicion1) {
            System.out.println(
                    "El determinante obtenido es igual al original. " + jugador.getNombre() + " repite el turno.");
            repiteTurno = true;
        } else if (condicion2) {
            System.out.println(
                    "El determinante obtenido no cambió. " + jugador.getNombre() + " no jugará el siguiente turno.");
            jugador.setTurnoActivo(false);
        } else if (condicion3) {
            System.out.println("El determinante obtenido ha repetido un valor anterior (distinto al original). "
                    + jugador.getNombre() + " no jugará el siguiente turno.");
            jugador.setTurnoActivo(false);
        } else {
            System.out.println(jugador.getNombre() + " jugará el siguiente turno de forma normal.");
        }
        return repiteTurno;
    }

    private static void visualizarTabero(Matriz matrizOriginal, Matriz matrizEnModificacion, int[] determinantes,
            int determinanteActual, int determinanteAnterior) {
        matrizOriginal.detalles("\nMatriz original: "); // Detalles de la matriz original
        matrizEnModificacion.detalles("\nMatriz modificada: "); // Detalles de la matriz modificada
        imprimirLista("\nLista de determinantes obtenidos (distintos al original): ", determinantes); // Visualizamos la
                                                                                                      // lista de
                                                                                                      // determinantes
        System.out.println("\nDeterminante Actual: " + determinanteActual);
        System.out.println("Determinante Anterior: " + determinanteAnterior);
    }

    private static void imprimirLista(String nombre, int[] lista) {
        System.out.println(nombre);
        String resultado = "";
        for (int i : lista) {
            resultado += i;
            resultado += ",";
        }
        System.out.println(resultado);
    }

    private static boolean seEncuentra(int[] lista, int elemento, int posicion) {
        boolean seEncuentra = false;
        for (int i = 0; i < posicion; i++) {
            if (elemento == lista[i]) {
                seEncuentra = true;
                return seEncuentra;
            }
        }
        return seEncuentra;
    }
}