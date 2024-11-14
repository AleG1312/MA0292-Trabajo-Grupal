
//Ingreso la librería para poder solicitarle datos al usuario
import java.util.Scanner;
//Ingreso la librería para poder limpiar la pantalla en plena ejecución
import java.io.IOException;

/**
 * Write a description of class main here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        Matriz matriz = new Matriz();
        boolean generarNuevaMatriz = true;
        while (generarNuevaMatriz) {
            matriz.generarMatrizCoeficientes(); // Se generan los coeficientes:
            matriz.generarVectorResultados(); // Se genera el vector de resultados:
            // Se muestran los resultados en pantalla:
            System.out.println("Determinante:" + matriz.calcularDeterminante());
            matriz.visualizarSistema();

            boolean eleccionValida = false;
            do { // Ciclo para aceptar únicamente las respuestas esperadas en cuanto a generar
                 // otra matriz o no
                System.out.println("¿Desea generar otra matriz? (1:Si, 2:No)");
                int eleccion = input.nextInt();
                switch (eleccion) {
                    case 1:
                        matriz.setSistema(new int[3][4]); // "Reinicio" el sistema para repetir el proceso
                        eleccionValida = true;
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Limpio la pantalla
                        break;
                    case 2:
                        eleccionValida = true;
                        generarNuevaMatriz = false;
                        break;
                    default:
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Limpio la pantalla
                        System.out.println("Determinante:" + matriz.calcularDeterminante());
                        matriz.visualizarSistema();
                        System.out.println("\nPor favor ingrese una opción válida.\n");
                        break;
                }
            } while (!eleccionValida);
        }
        //Ya en este punto tenemos la matriz final con la que empezaremos el juego
        Jugador jugador1 = new Jugador();
        jugador1.recibirComponentes();
    }
}