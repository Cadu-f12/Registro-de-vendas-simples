package dao;

import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IntegridadeDeDados {

    public int consultarExistenciaId(int id) {
        String sql = """
                    SELECT COUNT(*) AS n FROM vendas
                    WHERE id_venda = ?""";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            int result = rs.getInt("n");
            rs.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}