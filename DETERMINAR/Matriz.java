import java.util.Random;
/**
 * Write a description of class Matriz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Matriz
{
    // instance variables - replace the example below with your own
    private int[][] sistema;

    //Método constructor
    public Matriz()
    {
        sistema = new int[3][4];
    }
    
    public int[][] getSistema(){
        return this.sistema;
    }
    public void setSistema(int[][] sistema){
        this.sistema = sistema;
    }
    
    //Genero los coeficientes de la matriz
    public void generarCoeficientes(){
        Random randomNum = new Random();
        
        int tamañoFilas = this.sistema.length;
        int tamañoColumnas = this.sistema[0].length;
        
        //Relleno los coeficientes
        for (int i = 0; i < tamañoFilas; i++){
            for (int j = 0; j < (tamañoColumnas-1); j++){
                this.sistema[i][j] = randomNum.nextInt(-10,10);
            }
        }
    }    
    
    //Genera el vector de resultados
    public void generarResultados(){
        Random randomNum = new Random();
        
        int tamañoFilas = this.sistema.length;
        int ultimaColumna = this.sistema[0].length - 1;
        
        //Relleno la última columna
        for (int i = 0; i < tamañoFilas; i++){
            this.sistema[i][ultimaColumna] = randomNum.nextInt(-10,10);
        }
    }
    
    //Para visualizar el sistema de ecuaciones
    public void visualizarSistema(){
        String linea;
        for (int i = 0; i < this.sistema.length; i++){
            linea = "";
            for (int j = 0; j < this.sistema[0].length; j++){
                switch (j){
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
}