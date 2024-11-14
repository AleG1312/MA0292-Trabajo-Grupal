import java.util.Scanner;

/**
 * Write a description of class Matriz here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jugador {

    // Solicita fila, columna y valor para una matriz
    public int[] recibirComponentes() {
        Scanner input = new Scanner(System.in);
        int[] componentes = new int[3];
        System.out.print("Ingrese la fila: ");
        componentes[0] = input.nextInt();
        System.out.print("Ingrese la columna: ");
        componentes[1] = input.nextInt();
        System.out.print("Ingrese el valor: ");
        componentes[2] = input.nextInt();
        return componentes;
    }

}