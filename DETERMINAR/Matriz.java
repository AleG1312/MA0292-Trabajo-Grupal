import java.util.Random;

/**
 * Write a description of class Matriz here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Matriz {
    // instance variables - replace the example below with your own
    private int[][] sistema;

    // Método constructor
    public Matriz() {
        sistema = new int[3][4];
    }

    public int[][] getSistema() {
        return this.sistema;
    }

    public void setSistema(int[][] sistema) {
        this.sistema = sistema;
    }

    // Genero los coeficientes de la matriz
    public void generarCoeficientes() {
        Random randomNum = new Random();

        int tamañoFilas = this.sistema.length;
        int tamañoColumnas = this.sistema[0].length;
        
        do{
            // Relleno los coeficientes
            for (int i = 0; i < tamañoFilas; i++) {
                for (int j = 0; j < (tamañoColumnas - 1); j++) {
                    this.sistema[i][j] = randomNum.nextInt(-10, 10);
                }
            }
        }while(calcularDeterminante(sistema) == 0);
        
        System.out.println("Determinante:" + calcularDeterminante(sistema));
    }

    // Genera el vector de resultados
    public void generarResultados() {
        Random randomNum = new Random();

        int tamañoFilas = this.sistema.length;
        int ultimaColumna = this.sistema[0].length - 1;
        int[] resultados = new int[3];
        // Relleno la última columna
        for (int i = 0; i < tamañoFilas; i++) {
            this.sistema[i][ultimaColumna] = randomNum.nextInt(-10, 10);

            resultados[i] = this.sistema[i][ultimaColumna];
        }
        // TODO: generar un nuevo array si esHomogeneo(resultados); retorna true
        esHomogeneo(resultados);
    }

    // Para visualizar el sistema de ecuaciones
    public void visualizarSistema() {
        String linea;
        for (int i = 0; i < this.sistema.length; i++) {
            linea = "";
            for (int j = 0; j < this.sistema[0].length; j++) {
                switch (j) {
                    case 0:
                        linea += "(" + this.sistema[i][j] + ",";
                        break;
                    case 1:
                        linea += this.sistema[i][j] + ",";
                        break;
                    case 2:
                        linea += this.sistema[i][j] + "|";
                        break;
                    case 3:
                        linea += this.sistema[i][j] + ")";
                        break;
                }
            }
            System.out.println(linea);
        }
    }

    // Verifica si el sistema es homogeneo
    public boolean esHomogeneo(int[] array) {
        boolean homogeneo = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                homogeneo = false;
            }
        }
        return homogeneo;
    }

    public int calcularDeterminante(int[][] matriz){
        int[][] matrizSarrus = new int[3][5];
        
        //Copia el parametro y duplica la columna 1-2 en la 4-5 en un nuevo sistema.
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++){
                if(j < 3){
                    matrizSarrus[i][j] = matriz[i][j];    
                }else{
                    matrizSarrus[i][j] = matriz[i][j-3];
                }
                
            }
        }
        
        //Se encarga de calcular el determinante
        int producto = 1;
        int sumaUno = 0;
        int sumaDos = 0;
        int contador = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(i == 0){
                    producto *= matrizSarrus[j][j];
                }else if(i == 1){
                    producto *= matrizSarrus[j][j+1];
                }else{
                    producto *= matrizSarrus[j][j+2];
                }
                
            }
            sumaUno += producto;
            producto = 1;
            for(int j = 2; j >= 0; j--){
                if(j == 2){
                    producto *= matrizSarrus[j][contador];
                }else if(j == 1){
                    producto *= matrizSarrus[j][contador+1];
                }else{
                    producto *= matrizSarrus[j][contador+2];
                }
                
            }
            sumaDos += producto;
            producto = 1;
            contador += 1;
        }
        
        return (sumaUno - sumaDos);

    }
}