import java.util.Scanner;

public class Jugador {
    //Atributos:
    private String nombre;
    private boolean turnoActivo;

    //Método constructor:
    public Jugador(String nombre){
        this.nombre = nombre;
        this.turnoActivo = true;
    }

    //Setters y getters:
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public boolean esTurnoActivo(){
        return this.turnoActivo;
    }
    public void setTurnoActivo(boolean turnoActivo){
        this.turnoActivo = turnoActivo;
    }

    // Solicita fila, columna y valor para una matriz
    public int[] solicitarComponentes() {
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