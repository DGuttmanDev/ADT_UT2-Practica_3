package com.zetcode.business.service;

import com.zetcode.business.model.Player;
import com.zetcode.business.repository.ScoreRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PlayerService {

    private static final String CYAN = "\033[0;36m";
    private static final String RESET = "\033[0m";
    private static final String PURPLE = "\033[1;35m";
    public static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m";

    public static void checkScore(int scoreNuevo) {
        List<Player> players = ScoreRepository.getScore();
        int contador = 0;
        System.out.println(GREEN+"=========="+RESET);
        System.out.println(CYAN+"TOP  SCORE"+RESET);
        for (Player player:players){
            System.out.println(PURPLE+player.getUser()+"    "+player.getScore()+RESET);
            if(scoreNuevo > player.getScore()){
                contador++;
            }
        }
        System.out.println(GREEN+"=========="+RESET);

        if(players.size() < 10 || contador > 10){
            Scanner scanner = new Scanner(System.in);
            System.out.println(CYAN+"Estas entre los 10 mejores!");
            System.out.print("Introduce tus iniciales: ");
            String usuario = "";
            while (usuario.length()<3 || usuario.length()>3){
                usuario = scanner.nextLine();
                if(usuario.length()<3 || usuario.length()>3){
                    System.out.println(RED+"Usuario no válido, debe contener solo 3 letras"+RESET);
                    System.out.print(CYAN+"Introduce tus iniciales: "+RESET);
                }
            }
            ScoreRepository.saveScore(new Player(usuario, scoreNuevo));
        }

    }

}
