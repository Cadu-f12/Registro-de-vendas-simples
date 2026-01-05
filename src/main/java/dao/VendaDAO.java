package dao;

import model.venda.*;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VendaDAO {

    public void registrarVenda(Venda venda) {
        // Converter dados para tipos especificos do DataBase
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

    public ArrayList<Venda> carregarDados(Venda venda) {
        // Converter data para o tipo do banco de dados
        ConversorDeDados conversorDeDados = new ConversorDeDados(venda);
        ArrayList<Venda> vendas = new ArrayList<>();
        Venda registro;

        String sql = """
                    SELECT * FROM vendas
                    WHERE data_registro = ?""";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, conversorDeDados.getNovaData());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Id id = new Id(rs.getInt("id_venda"));
                Pagamento pagamento = Pagamento.valueOf(rs.getString("forma_pagamento"));
                Vendedor vendedor = Vendedor.valueOf(rs.getString("nome_vendedor"));
                Produto produto = new Produto(rs.getString("nome_produto"));
                Quantidade quantidade = new Quantidade(rs.getInt("quantidade"));
                Total total = new Total(rs.getBigDecimal("total"));
                registro = new Venda(id, null, pagamento, vendedor, quantidade, produto, total);
                vendas.add(registro);
            }

            rs.close();
            return vendas;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarVenda(Venda venda) {
        String sql = """
                    DELETE FROM vendas
                    WHERE id_venda = ?""";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, venda.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}