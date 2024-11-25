public class pruebas {
    public static void main(String[] args) {
        
        Jugador jugador1 = new Jugador("Jugador 1"); 
        Jugador jugador2 = new Jugador("Jugador 2");
        Jugador jugador3 = new Jugador("Jugador 3");

        Jugador[] jugadores = new Jugador[] { jugador1, jugador2, jugador3 }; // Creo un array con mis jugadores

        jugador1.setPuntajeTotal(10);
        jugador1.setRondaDePuntaje(2);
        jugador2.setPuntajeTotal(10);
        jugador2.setRondaDePuntaje(0);
        jugador3.setPuntajeTotal(-1);
        jugador3.setRondaDePuntaje(0);

        mostrarPuntajes(jugadores);
        desplegarGanador(jugadores);
    }


    private static void mostrarPuntajes(Jugador[] jugadores){
        for(Jugador jugador:jugadores){
            System.out.println(jugador.getNombre() + ": " + jugador.getPuntajeTotal());
            System.out.println("Ronda de puntaje: " + jugador.getRondaDePuntaje());
        }
    }
    
    private static void desplegarGanador(Jugador[] jugadores){
        boolean todosNegativos = todosNegativos(jugadores);
        boolean huboEmpate = huboEmpate(jugadores);
        if(huboEmpate){
            System.out.println("Estoy en empate");
            ganadorEmpate(jugadores, todosNegativos);
        }
        else{   //No hubo empate
            if(todosNegativos){
                Jugador perdedor = (jugadores[0].getPuntajeTotal() <= jugadores[1].getPuntajeTotal())?
                (jugadores[0].getPuntajeTotal() <= jugadores[2].getPuntajeTotal())? jugadores[0] : jugadores[2]
                : (jugadores[1].getPuntajeTotal() <= jugadores[2].getPuntajeTotal())? jugadores[1] : jugadores[2];

                System.out.println("\nEl perdedor ha sido: " + perdedor.getNombre());
            }else{
                Jugador ganador = (jugadores[0].getPuntajeTotal() >= jugadores[1].getPuntajeTotal())?
                (jugadores[0].getPuntajeTotal() >= jugadores[2].getPuntajeTotal())? jugadores[0] : jugadores[2]
                : (jugadores[1].getPuntajeTotal() >= jugadores[2].getPuntajeTotal())? jugadores[1] : jugadores[2];

                System.out.println("\nEl ganador ha sido: " + ganador.getNombre());
            }
        }
    }
    
    private static boolean todosNegativos(Jugador[] jugadores){
        boolean todosNegativos = true;
        for (Jugador jugador:jugadores){
            if (jugador.getPuntajeTotal() >= 0){
                todosNegativos = false;
                break;
            }
        }
        return todosNegativos;
    }
    
    private static boolean huboEmpate(Jugador[] jugadores){
        //Identificamos caso de empate
        boolean huboEmpate = false;
        for(int i = 0; i < jugadores.length; i ++){
            for(int j = i+1; j < jugadores.length; j ++){
                if(jugadores[i].getPuntajeTotal() == jugadores[j].getPuntajeTotal()){
                    huboEmpate = true;
                }
            }
        }
        return huboEmpate;
    }
    
    private static void ganadorEmpate(Jugador[] jugadores, boolean todosNegativos){       
        /**
         * Para identificar a los que repitieron valor
         */
        Jugador jugadorDiferente = null;
        Jugador[] jugadoresRepetidos = new Jugador[2];
        if(jugadores[0].getPuntajeTotal() == jugadores[1].getPuntajeTotal()){
            jugadoresRepetidos[0] = jugadores[0];
            jugadoresRepetidos[1] = jugadores[1];
            jugadorDiferente = jugadores[2];
        }else{
            if(jugadores[0].getPuntajeTotal() == jugadores[2].getPuntajeTotal()){
                jugadoresRepetidos[0] = jugadores[0];
                jugadoresRepetidos[1] = jugadores[2];
                jugadorDiferente = jugadores[1];
            }else{
                jugadoresRepetidos[0] = jugadores[1];
                jugadoresRepetidos[1] = jugadores[2];
                jugadorDiferente = jugadores[0];
            }
        }

        if(todosNegativos){
            /**
             * Si el perdedor es el diferente
             */
            if (jugadorDiferente.getPuntajeTotal() < jugadoresRepetidos[0].getPuntajeTotal()){
                System.out.println("\nEl perdedor ha sido: " + jugadorDiferente.getNombre());
            }else{
                if(jugadoresRepetidos[0].getRondaDePuntaje() < jugadoresRepetidos[1].getRondaDePuntaje()){
                    System.out.println("\nEl perdedor ha sido: " + jugadoresRepetidos[0].getNombre());
                }else if(jugadoresRepetidos[1].getRondaDePuntaje() < jugadoresRepetidos[0].getRondaDePuntaje()){
                    System.out.println("\nEl perdedor ha sido: " + jugadoresRepetidos[1].getNombre());
                }else{
                    System.out.println("\nEl perdedor ha sido: " + jugadoresRepetidos[0].getNombre());
                }
            }
        }else{
            /**
             * Si el ganador es el diferente
             */
            if (jugadorDiferente.getPuntajeTotal() > jugadoresRepetidos[0].getPuntajeTotal()){
                System.out.println("\nEl ganador ha sido: " + jugadorDiferente.getNombre());
            }else{
                if(jugadoresRepetidos[0].getRondaDePuntaje() < jugadoresRepetidos[1].getRondaDePuntaje()){
                    System.out.println("\nEl ganador ha sido: " + jugadoresRepetidos[0].getNombre());
                }else if(jugadoresRepetidos[1].getRondaDePuntaje() < jugadoresRepetidos[0].getRondaDePuntaje()){
                    System.out.println("\nEl ganador ha sido: " + jugadoresRepetidos[1].getNombre());
                }else{
                    System.out.println("\nEl ganador ha sido: " + jugadoresRepetidos[0].getNombre());
                }
            }
        }
    }
}

