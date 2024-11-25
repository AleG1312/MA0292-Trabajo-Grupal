import java.util.Scanner;
/**
 * Clase Jugador
 * 
 * Descripción: Agrupa las características necesarias que debe tener el jugador en el juego
 */
public class Jugador {
    // Atributos:
    private String nombre;
    private boolean turnoActivo;    //Corresponde a si el jugador juega en la ronda en la que está o no
    private int puntajeTotal;
    private int rondaDePuntaje;     //Corresponde a la primera ronda en la que obtuve mi puntaje actual

    // Método constructor:
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.turnoActivo = true;
        this.puntajeTotal = 0;
        this.rondaDePuntaje = 0;
    }

    // Setters y getters:
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean esTurnoActivo() {
        return this.turnoActivo;
    }

    public void setTurnoActivo(boolean turnoActivo) {
        this.turnoActivo = turnoActivo;
    }

    public int getPuntajeTotal() {
        return this.puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }
    public int getRondaDePuntaje(){
        return this.rondaDePuntaje;
    }
    public void setRondaDePuntaje(int rondaDePuntaje){
        this.rondaDePuntaje = rondaDePuntaje;
    }

    //Para calcular la ronda de puntaje:
    public void calcularRondaDePuntaje(int ronda, int puntajeNuevo){
        //Únicamente se actualiza si el puntaje nuevo es diferente al acumulado
        if(this.puntajeTotal != puntajeNuevo){
            this.rondaDePuntaje = ronda;
        }
    }

    //Para calcular el puntaje según los requerimientos del proyecto:
    public int calcularPuntaje(int detTurno, int detOriginal) {
        int nuevoPuntaje = 0;
        if (detTurno > 0) {
            nuevoPuntaje = Math.abs(detOriginal - detTurno);
            nuevoPuntaje = this.getPuntajeTotal() + nuevoPuntaje;

        } else {
            nuevoPuntaje = detTurno;
            nuevoPuntaje = this.getPuntajeTotal() + nuevoPuntaje;
        }
        return nuevoPuntaje;

    }

    //Para solicitar los componentes de fila, columna y valor
    public int[] solicitarComponentes() {
        Scanner input = new Scanner(System.in);
        int[] componentes = new int[3];
        boolean reintentar = true;

        // Se solicita la fila
        do {
            System.out.print("Ingrese la fila: (0,2): ");
            componentes[0] = input.nextInt();
            if (componentes[0] < 0 || componentes[0] > 2) {
                System.out.println("El valor ingresado es inválido. Intente nuevamente");
                reintentar = true;
            } else {
                reintentar = false;
            }
        } while (reintentar);

        // Se solicita la columna
        do {
            System.out.print("Ingrese la columna: (0,2): ");
            componentes[1] = input.nextInt();
            if (componentes[1] < 0 || componentes[1] > 2) {
                System.out.println("El valor ingresado es inválido. Intente nuevamente");
                reintentar = true;
            } else {
                reintentar = false;
            }
        } while (reintentar);

        // Se solicta el valor
        do {
            System.out.print("Ingrese el valor: (-10,10): ");
            componentes[2] = input.nextInt();
            if (componentes[2] < -10 || componentes[2] > 10) {
                System.out.println("El valor ingresado es inválido. Intente nuevamente");
                reintentar = true;
            } else {
                reintentar = false;
            }
        } while (reintentar);
        return componentes;
    }
}