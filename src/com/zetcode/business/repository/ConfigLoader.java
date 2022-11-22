package com.zetcode.business.repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    public static Properties initConfig() {

        try (
                FileReader fr = new FileReader("src/resources/db.properties")
        ) {
            Properties properties = new Properties();
            properties.load(fr);

            return properties;

        } catch (IOException e) {
            System.err.println("Error al cargar las propiedades");
            System.err.println(e.getMessage());
        }

        return null;

    }

}
