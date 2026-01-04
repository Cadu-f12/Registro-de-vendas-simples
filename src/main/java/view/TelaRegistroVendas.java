package view;

import com.toedter.calendar.JCalendar;
import controller.VendaController;

import model.venda.Venda;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate; // Import necessário para pegar a data atual
import java.time.format.DateTimeFormatter; // Import para formatar a data atual
import java.util.ArrayList;
import java.util.Date;

public class TelaRegistroVendas extends JFrame {

    private JTable tabelaVendas;
    private DefaultTableModel modeloTabela;
    private JTextField txtData;

    public TelaRegistroVendas() {
        setTitle("Sistema de Gestão de Vendas - Registro Atual");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- PAINEL SUPERIOR (DATA CENTRALIZADA COM DATA ATUAL) ---
        JPanel painelSuperior = new JPanel(new GridBagLayout());
        painelSuperior.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);

        JLabel lblData = new JLabel("Filtrar por Data: ");
        lblData.setFont(new Font("Arial", Font.BOLD, 14));

        // --- LÓGICA DA DATA ATUAL ---
        LocalDate hoje = LocalDate.now(); // Pega a data de hoje do sistema
        DateTimeFormatter formatadorBr = DateTimeFormatter.ofPattern("dd/MM/yy"); // Define o padrão
        String dataHojeFormatada = hoje.format(formatadorBr); // Transforma em texto

        txtData = new JTextField(dataHojeFormatada, 12); // Inicia o campo com a data de hoje
        txtData.setEditable(false);
        txtData.setHorizontalAlignment(JTextField.CENTER);
        txtData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirCalendario();
            }
        });

        painelSuperior.add(lblData, gbc);
        painelSuperior.add(txtData, gbc);
        add(painelSuperior, BorderLayout.NORTH);

        // --- PAINEL CENTRAL (TABELA) ---
        String[] colunas = {"ID", "Forma de Pagamento", "Vendedor", "Quantidade", "Nome do Produto", "Total"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaVendas = new JTable(modeloTabela);

        ajustarLayoutTabela(); // Formatação da tabela

        JScrollPane scrollPane = new JScrollPane(tabelaVendas);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createEtchedBorder()
        ));

        add(scrollPane, BorderLayout.CENTER);

        // --- PAINEL INFERIOR (BOTÕES CENTRALIZADOS) ---
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        painelInferior.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnDeletar = new JButton("Deletar");
        JButton btnEditar = new JButton("Editar");

        Dimension dimBotao = new Dimension(160, 45);
        btnRegistrar.setPreferredSize(dimBotao);
        btnDeletar.setPreferredSize(dimBotao);
        btnEditar.setPreferredSize(dimBotao);

        // Adicionando apenas os botões de ação
        painelInferior.add(btnRegistrar);
        painelInferior.add(btnDeletar);
        painelInferior.add(btnEditar);

        add(painelInferior, BorderLayout.SOUTH);

        // Ações
        btnRegistrar.addActionListener(e -> registrarVenda());
        btnDeletar.addActionListener(e -> deletarVenda());
        btnEditar.addActionListener(e -> editarVenda());

        // --- CARREGAMENTO AUTOMÁTICO INICIAL ---
        carregarDados();
    }

    private void abrirCalendario() {
        JDialog dialog = new JDialog(this, "Selecionar Data", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JCalendar calendar = new JCalendar();
        JButton btnSelecionar = new JButton("Confirmar Data");
        btnSelecionar.addActionListener(e -> {
            Date dataSelecionada = calendar.getDate();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yy");
            txtData.setText(formatador.format(dataSelecionada));
            dialog.dispose();

            // Atualiza automaticamente ao trocar a data
            carregarDados();
        });

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSelecionar);
        dialog.add(calendar, BorderLayout.CENTER);
        dialog.add(painelBotoes, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void carregarDados() {
        try {
            VendaController vendaController = new VendaController();
            ArrayList<Venda> vendas = vendaController.carregarDados(txtData.getText());

            modeloTabela.setRowCount(0);

            for (Venda v : vendas) {
                Object[] linha = {
                        v.getId(),
                        v.getFormaPagamento(),
                        v.getNomeVendedor(),
                        v.getQuantidade(),
                        v.getNomeProduto(),
                        new java.text.DecimalFormat("R$ #,##0.00").format(v.getTotal())
                };
                modeloTabela.addRow(linha);
            }

        } catch (Exception e) {
            MensagemErro mensagemErro = new MensagemErro(e);
            JOptionPane.showMessageDialog(this, mensagemErro.getMensagem(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarVenda() {
        new JanelaCadastroVenda(this).setVisible(true);
        carregarDados();
    }

    private void deletarVenda() {
        new JanelaDeletarVenda(this).setVisible(true);
        carregarDados();
    }

    private void editarVenda() {
        new JanelaEditarVenda(this).setVisible(true);
        carregarDados();
    }

    private void ajustarLayoutTabela() {
        tabelaVendas.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabelaVendas.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabelaVendas.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabelaVendas.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabelaVendas.getColumnModel().getColumn(4).setPreferredWidth(250);
        tabelaVendas.getColumnModel().getColumn(5).setPreferredWidth(100);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer aDireita = new DefaultTableCellRenderer();
        aDireita.setHorizontalAlignment(JLabel.RIGHT);

        tabelaVendas.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tabelaVendas.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        tabelaVendas.getColumnModel().getColumn(5).setCellRenderer(aDireita);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { e.printStackTrace(); }

        SwingUtilities.invokeLater(() -> {
            new TelaRegistroVendas().setVisible(true);
        });
    }
}