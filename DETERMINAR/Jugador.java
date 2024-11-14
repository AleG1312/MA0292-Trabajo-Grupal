import java.util.Scanner;
/**
 * Write a description of class Jugador here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jugador {
    public Jugador(){
        
    }
    // Solicita fila, columna y valor para una matriz
    public int[] recibirComponentes() {
        Scanner input = new Scanner(System.in);
        int[] componentes = new int[3];
        System.out.print("Ingrese la fila: (0,2): ");
        componentes[0] = input.nextInt();
        System.out.print("Ingrese la columna: (0,2): ");
        componentes[1] = input.nextInt();
        System.out.print("Ingrese el valor: (-10,10): ");
        componentes[2] = input.nextInt();
        return componentes;
    }
}