import java.util.Random;

public class Matriz {
    //Atributos
    private int[][] sistema;
    private int determinante;

    // Método constructor
    public Matriz() {
        this.sistema = new int[3][4];        //Cada entrada se inicializa en 0
        this.determinante = calcularDeterminante();
    }
    // Setters y getters
    public int[][] getSistema() {
        return this.sistema;
    }
    public void setSistema(int[][] sistema) {
        this.sistema = sistema;
        this.determinante = calcularDeterminante();
    }
    public int getDeterminante(){
        return this.determinante;
    }
    public void setDeterminante(int determinante){
        this.determinante = determinante;
    }

    // Genero la matriz de coeficientes
    public void generarMatrizCoeficientes() {
        Random randomNum = new Random();

        int tamañoFilas = this.sistema.length;
        int tamañoColumnas = this.sistema[0].length;

        do { // Relleno la matriz de coeficientes
            for (int i = 0; i < tamañoFilas; i++) {
                for (int j = 0; j < (tamañoColumnas - 1); j++) {
                    this.sistema[i][j] = randomNum.nextInt(-10, 10);
                }
            }
        } while (calcularDeterminante() == 0); // Si el determinante de la matriz generada es cero, volver a realizar el proceso
        setDeterminante(calcularDeterminante());
    }
    // Genera el vector de resultados
    public void generarVectorResultados() {
        Random randomNum = new Random();

        int tamañoFilas = this.sistema.length;
        int ultimaColumna = this.sistema[0].length - 1;
        int[] resultados = new int[3];
        do { // Genero el vector de resultados
            for (int i = 0; i < tamañoFilas; i++) {
                int random = randomNum.nextInt(-10, 10); // Genero mi número aleatorio
                this.sistema[i][ultimaColumna] = random; // Agrego el número a mi sistema
                resultados[i] = random; // Lo agrego a mi vector de resultados para su posterior análisis
            }
        } while (esNulo(resultados)); // Si el vector de resultados es nulo, volver a realizar el proceso
    }
    //Generar matriz
    public void generarMatriz(){
        generarMatrizCoeficientes();    // Se generan los coeficientes:
        generarVectorResultados();      // Se genera el vector de resultados:
    }

    // Para visualizar el sistema de ecuaciones
    public void detalles(String nombre){
        System.out.println(nombre);
        for (int i = 0; i < this.sistema.length; i++) {
            for (int j = 0; j < this.sistema[0].length; j++) {
                switch (j) {
                    case 0:
                        System.out.printf("(%3d", this.sistema[i][j]);
                        break;
                    case 1:
                    System.out.printf("%5d", this.sistema[i][j]);
                        break;
                    case 2:
                    System.out.printf("%5d  |", this.sistema[i][j]);
                        break;
                    case 3:
                        System.out.printf("%4d)", this.sistema[i][j]);
                        break;
                }
            }
            System.out.println();
        }
        System.out.println("El determinante asociado es: " + this.determinante);
    }

    // Verifica si un vector es nulo (todas sus entradas son cero)
    public boolean esNulo(int[] array) {
        boolean nulo = true; // Se asume que es nulo
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) { // Si alguna componente es distinta de cero, el vector no es nulo
                nulo = false;
                return nulo;
            }
        }
        return nulo;
    }

    public int calcularDeterminante() {
        int[][] matrizSarrus = new int[3][5];

        // Copia el parametro y duplica la columna 1-2 en la 4-5 en un nuevo sistema.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (j < 3) {
                    matrizSarrus[i][j] = this.sistema[i][j];
                } else {
                    matrizSarrus[i][j] = this.sistema[i][j - 3];
                }
            }
        }
        // Se encarga de calcular el determinante
        int producto = 1;
        int sumaUno = 0;
        int sumaDos = 0;
        int contador = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    producto *= matrizSarrus[j][j];
                } else if (i == 1) {
                    producto *= matrizSarrus[j][j + 1];
                } else {
                    producto *= matrizSarrus[j][j + 2];
                }
            }
            sumaUno += producto;
            producto = 1;
            for (int j = 2; j >= 0; j--) {
                if (j == 2) {
                    producto *= matrizSarrus[j][contador];
                } else if (j == 1) {
                    producto *= matrizSarrus[j][contador + 1];
                } else {
                    producto *= matrizSarrus[j][contador + 2];
                }
            }
            sumaDos += producto;
            producto = 1;
            contador += 1;
        }
        return (sumaUno - sumaDos);
    }
    
    public void cambiarEntrada(int[] parametros){
        int filaPorCambiar = parametros[0];
        int columnaPorCambiar = parametros[1];
        int valorNuevo = parametros[2];
        this.sistema[filaPorCambiar][columnaPorCambiar] = valorNuevo;
        //Se recalcula el determinante automáticamente
        this.determinante = calcularDeterminante();
    }

    public void mapearDatos(int[][] sistema){
        for (int i = 0; i < sistema.length; i++) {
            for (int j = 0; j < sistema[0].length; j++) {
                this.sistema[i][j] = sistema[i][j];
            }
        }
        this.determinante = calcularDeterminante();
    }
}