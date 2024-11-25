
//Ingreso la librería para poder solicitarle datos al usuario
import java.util.Scanner;
//Ingreso la librería para poder limpiar la pantalla en plena ejecución
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        /**
         * Bloque #1: Escogencia de la matriz inicial
         */
        Scanner input = new Scanner(System.in);
        Matriz matrizOriginal = definirMatrizInicial(); // Defino la matriz con la que empezaré el juego
        matrizOriginal.detalles("Matriz original: "); // Detalles de la matriz original
        System.out.println("\nIngrese 'enter' para pasar a la siguiente ronda");
        input.nextLine();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Limpio la pantalla



        /**
         * Bloque #2: Definir las variables necesarias para el desarrollo del juego
         */
        Matriz matrizEnModificacion = new Matriz(); // Será la matriz a la que se le harán las modificaciones
        matrizEnModificacion.mapearDatos(matrizOriginal.getSistema());  //Copio los datos de la matriz original a la matriz en modificacion. 
        
        Jugador jugador1 = new Jugador("Jugador 1"); // Creo mis jugadores
        Jugador jugador2 = new Jugador("Jugador 2");
        Jugador jugador3 = new Jugador("Jugador 3");

        Jugador[] jugadores = new Jugador[] { jugador1, jugador2, jugador3 }; // Creo un array con mis jugadores

        int[] determinantes = new int[15]; // Creo mi lista de determinantes
        int determinanteOriginal = matrizOriginal.getDeterminante(); //Defino el determinante original
        int determinanteAnterior = determinanteOriginal;    //Defino el determinante Anterior

        int rondas = 1; // Número de rondas
        int iteracion = 0;  //Identificador para agregar números a la lista de determinantes




        /**
         * Bloque #3: Lógica del juego
         */
        for (int ronda = 0; ronda < rondas; ronda++) { // Para el número de rondas especificado inicialmente
            for (Jugador jugador : jugadores) { // Para los jugadores definidos
                System.out.println("\n-----------Ronda: " + ronda + "  |  " + jugador.getNombre()   //Encabezado de ronda
                    + "  |  Puntaje Actual: " + jugador.getPuntajeTotal() + "-----------");


                /**
                 * Bloque 3.1: Jugador Vivo
                 */
                if (jugador.esTurnoActivo()) { 
                    boolean repiteTurno = false;
                    do {
                        //Función de realizar jugada
                        determinantes = jugada(ronda, jugador, matrizEnModificacion, determinantes, iteracion); 
                        int determinanteActual = determinantes[iteracion];

                        /**
                         * Bloque 3.1.1: Analizar la jugada del jugador
                         */
                        
                        if(determinanteActual == 21){   //Fin del juego si el determinante obtenido es 21
                            System.out.println("\nSe ha obtenido 21 en el determinante. " + jugador.getNombre() + " ha ganado el juego.\n");
                            return;
                        }
                        else{
                            visualizarTabero(matrizOriginal, matrizEnModificacion, determinantes, determinanteActual,
                                determinanteAnterior);
                            System.out.println("\n----------------------------------");

                            repiteTurno = condicionSiguienteTurno(jugador, determinanteActual, determinanteAnterior,
                                determinanteOriginal, determinantes, iteracion);

                            determinanteAnterior = determinanteActual;

                        }
                    } while (repiteTurno);




                    /**
                     * Bloque 3.1.2: Lo relativo al puntaje del jugador
                     */
                    int nuevoPuntaje = jugador.calcularPuntaje(determinanteAnterior, determinanteOriginal);

                    jugador.calcularRondaDePuntaje(ronda, nuevoPuntaje);    //La primera ronda en la que se obtuvo el puntaje actual
                    
                    jugador.setPuntajeTotal(nuevoPuntaje);  //Establezco el nuevo puntaje

                    System.out.println("\nActualización de puntaje:");
                    System.out.println(jugador.getNombre() + ", Puntaje acumulado = " + jugador.getPuntajeTotal());

                } 


                /**
                 * Bloque 3.2: En caso de que el jugador esté muerto
                 */
                else { 
                    System.out.println("No está vivo. Jugará en el siguiente turno.");
                    jugador.setTurnoActivo(true); // Si no está vivo en el turno actual, lo estará en el siguiente
                }



                /**
                 * Bloque 3.3: Lo que sucede al final de cada ronda
                 */
                iteracion++;
                System.out.println("\n-----------------Opciones-----------------");
                System.out.println("1: Detener el juego.");
                System.out.println("2: Reiniciar el juego.");
                System.out.println("3: Continuar normal.");

                String decision = input.nextLine();

                switch (decision){
                    case "1":
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Limpio la pantalla
                        System.out.println("--------------------------------");
                        System.out.println("Juego Finalizado");
                        System.out.println("--------------------------------");
                        return;
                    case "2":
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Limpio la pantalla
                        System.out.println("--------------------------------");
                        System.out.println("Nuevo Juego:");
                        System.out.println("--------------------------------\n");
                        main(args);
                        return;
                    default:
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Limpio la pantalla
                        break;
                }
            }
        }
        System.out.println("--------------Puntajes Finales--------------");
        mostrarPuntajes(jugadores);
        desplegarGanador(jugadores);
        System.out.println("Fin del juego");
        //input.close();
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
        //input.close();
        return matriz;
    }

    private static int[] jugada(int ronda, Jugador jugador, Matriz matrizEnModificacion, int[] determinantes,
            int iteracion) { // Esta función se encarga únicamente de realizar la jugada
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
        //Visualizamos la lista de determinantes:
        imprimirLista("\nLista de determinantes obtenidos (distintos al original): ", determinantes); 
        System.out.println("\nDeterminante Original: " + matrizOriginal.getDeterminante());
        System.out.println("Determinante Actual: " + determinanteActual);
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
    
    private static void mostrarPuntajes(Jugador[] jugadores){
        for(Jugador jugador:jugadores){
            System.out.println(jugador.getNombre() + ": " + jugador.getPuntajeTotal());
            System.out.println("Ronda de puntaje: " + jugador.getRondaDePuntaje());
        }
    }
    
    private static void desplegarGanador(Jugador[] jugadores){
        boolean todosNegativos = todosNegativos(jugadores);
        boolean huboEmpate = huboEmpate(jugadores);
        if(huboEmpate){
            ganadorEmpate(jugadores, todosNegativos);
        }
        else{   //No hubo empate
            if(todosNegativos){
                Jugador perdedor = (jugadores[0].getPuntajeTotal() <= jugadores[1].getPuntajeTotal())?
                (jugadores[0].getPuntajeTotal() <= jugadores[2].getPuntajeTotal())? jugadores[0] : jugadores[2]
                : (jugadores[1].getPuntajeTotal() <= jugadores[2].getPuntajeTotal())? jugadores[1] : jugadores[2];

                System.out.println("\nEl perdedor ha sido: " + perdedor.getNombre());
            }else{
                Jugador ganador = (jugadores[0].getPuntajeTotal() >= jugadores[1].getPuntajeTotal())?
                (jugadores[0].getPuntajeTotal() >= jugadores[2].getPuntajeTotal())? jugadores[0] : jugadores[2]
                : (jugadores[1].getPuntajeTotal() >= jugadores[2].getPuntajeTotal())? jugadores[1] : jugadores[2];

                System.out.println("\nEl ganador ha sido: " + ganador.getNombre());
            }
        }
    }
    
    private static boolean todosNegativos(Jugador[] jugadores){
        boolean todosNegativos = true;
        for (Jugador jugador:jugadores){
            if (jugador.getPuntajeTotal() >= 0){
                todosNegativos = false;
                break;
            }
        }
        return todosNegativos;
    }
    
    private static boolean huboEmpate(Jugador[] jugadores){
        //Identificamos caso de empate
        boolean huboEmpate = false;
        for(int i = 0; i < jugadores.length; i ++){
            for(int j = i+1; j < jugadores.length; j ++){
                if(jugadores[i] == jugadores[j]){
                    huboEmpate = true;
                }
            }
        }
        return huboEmpate;
    }
    
    private static void ganadorEmpate(Jugador[] jugadores, boolean todosNegativos){       
        /**
         * Para identificar a los que repitieron valor
         */
        Jugador jugadorDiferente = null;
        Jugador[] jugadoresRepetidos = new Jugador[2];
        if(jugadores[0].getPuntajeTotal() == jugadores[1].getPuntajeTotal()){
            jugadoresRepetidos[0] = jugadores[0];
            jugadoresRepetidos[1] = jugadores[1];
            jugadorDiferente = jugadores[2];
        }else{
            if(jugadores[0].getPuntajeTotal() == jugadores[2].getPuntajeTotal()){
                jugadoresRepetidos[0] = jugadores[0];
                jugadoresRepetidos[1] = jugadores[2];
                jugadorDiferente = jugadores[1];
            }else{
                jugadoresRepetidos[0] = jugadores[1];
                jugadoresRepetidos[1] = jugadores[2];
                jugadorDiferente = jugadores[0];
            }
        }

        if(todosNegativos){
            /**
             * Si el perdedor es el diferente
             */
            if (jugadorDiferente.getPuntajeTotal() < jugadoresRepetidos[0].getPuntajeTotal()){
                System.out.println("\nEl perdedor ha sido: " + jugadorDiferente.getNombre());
            }else{
                if(jugadoresRepetidos[0].getRondaDePuntaje() < jugadoresRepetidos[1].getRondaDePuntaje()){
                    System.out.println("\nEl perdedor ha sido: " + jugadoresRepetidos[0].getNombre());
                }else if(jugadoresRepetidos[1].getRondaDePuntaje() < jugadoresRepetidos[0].getRondaDePuntaje()){
                    System.out.println("\nEl perdedor ha sido: " + jugadoresRepetidos[1].getNombre());
                }else{
                    System.out.println("\nEl perdedor ha sido: " + jugadoresRepetidos[0].getNombre());
                }
            }
        }else{
            /**
             * Si el ganador es el diferente
             */
            if (jugadorDiferente.getPuntajeTotal() > jugadoresRepetidos[0].getPuntajeTotal()){
                System.out.println("\nEl ganador ha sido: " + jugadorDiferente.getNombre());
            }else{
                if(jugadoresRepetidos[0].getRondaDePuntaje() > jugadoresRepetidos[1].getRondaDePuntaje()){
                    System.out.println("\nEl ganador ha sido: " + jugadoresRepetidos[0].getNombre());
                }else if(jugadoresRepetidos[1].getRondaDePuntaje() > jugadoresRepetidos[0].getRondaDePuntaje()){
                    System.out.println("\nEl ganador ha sido: " + jugadoresRepetidos[1].getNombre());
                }else{
                    System.out.println("\nEl ganador ha sido: " + jugadoresRepetidos[0].getNombre());
                }
            }
        }
    }
}