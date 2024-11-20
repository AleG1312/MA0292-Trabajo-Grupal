import java.util.Scanner;

public class Jugador {
    // Atributos:
    private String nombre;
    private boolean turnoActivo;
    private int puntajeTotal;

    /**
     * Agregar atributo de puntaje total, con sus setters y getters respectivos
     * Agregar una función de calcular puntaje total, que reciba como parámetro un
     * determinante y cumpla con las condiciones del proyecto (punto c)
     */

    // Método constructor:
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.turnoActivo = true;
        this.puntajeTotal = 0;
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

    // Solicita fila, columna y valor para una matriz
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

        input.close();

        return componentes;
    }
}