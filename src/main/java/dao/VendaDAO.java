package dao;

import model.venda.Venda;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class VendaDAO {

    public void registrarVenda(Venda venda) {
        ConversorDeDados conversorDeDados = new ConversorDeDados(venda);

        String sql = """
                    INSERT INTO vendas (data_registro, forma_pagamento, nome_vendedor, quantidade, nome_produto, total)
                    VALUES (?, ?, ?, ?, ?, ?)""";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, conversorDeDados.getNovaData());
            pstmt.setString(2, conversorDeDados.getNovoPagamento());
            pstmt.setString(3, conversorDeDados.getNovoVendedor());
            pstmt.setInt(4, venda.getQuantidade());
            pstmt.setString(5, venda.getNomeProduto());
            pstmt.setBigDecimal(6, venda.getTotal());

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}