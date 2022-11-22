package com.zetcode.business.repository;

import com.zetcode.business.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ScoreRepository {

    public static final String GREEN = "\033[0;32m";
    private static final String RESET = "\033[0m";

    private static Properties properties;

    static {
        try {
            properties = ConfigLoader.initConfig();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<Player> getScore() {

        List<Player> puntuaciones = new ArrayList<>();

        try (

                Connection connection = DriverManager.getConnection(properties.getProperty("db.url"));
                PreparedStatement ps = connection.prepareStatement("select * from puntuaciones order by puntuacion desc limit 10");
                ResultSet rs = ps.executeQuery();

                ) {

            while(rs.next()){

                puntuaciones.add(new Player(rs.getString("usuario"), rs.getInt("puntuacion")));

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        return puntuaciones;

    }

    public static void saveScore(Player nuevoPlayer) {

        List<Player> puntuaciones = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        // MODIFICAR LA QUERY PARA QUE SOLAMENTE DEVUELVA LOS 10 JUGADORES CON MAS PUNTUACION
        query.append("INSERT INTO puntuaciones (usuario, puntuacion) VALUES ('");
        query.append(nuevoPlayer.getUser().toUpperCase());
        query.append("',");
        query.append(nuevoPlayer.getScore());
        query.append(");");

        try (

                Connection connection = DriverManager.getConnection(properties.getProperty("db.url"));
                PreparedStatement ps = connection.prepareStatement(query.toString());

        ) {
            ps.executeUpdate();
            System.out.println(GREEN+"Usuario registrado"+RESET);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

}
