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
public class main
{
    public static void main(String[] args) throws IOException, InterruptedException{
        Scanner input = new Scanner(System.in);
        Matriz matriz = new Matriz();
        while (true){
            System.out.println("Se generan los coeficientes");
            matriz.generarCoeficientes();
            matriz.visualizarSistema();
            System.out.println("Se genera el vector de resultados");
            matriz.generarResultados();
            matriz.visualizarSistema();

            System.out.println("Desea salir (1:Si, 2:No)");
            int eleccion = input.nextInt();
            if (eleccion == 1){
                break;
            }
            
            //"Reinicio" el sistema para repetir el proceso
            matriz.setSistema(new int[3][4]);
            //Limpio la pantalla
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }
}