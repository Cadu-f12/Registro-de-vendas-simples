package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
    private static String url = null;
    private static String user = null;
    private static String password = null;

    private static void carregarPropriedades() {
        if (url != null) {
            // já foi carregado, não precisa carregar novamente
            return;
        }

        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/bd.properties")) {
            properties.load(inputStream);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo de propriedades: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        carregarPropriedades();

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro de SQL na conexao: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexao: " + e.getMessage());
        }
    }
}