package view;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate; // Import necessário para pegar a data atual
import java.time.format.DateTimeFormatter; // Import para formatar a data atual
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

        JScrollPane scrollPane = new JScrollPane(tabelaVendas);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createEtchedBorder()
        ));

        add(scrollPane, BorderLayout.CENTER);

        // --- PAINEL INFERIOR (BOTÕES) ---
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        painelInferior.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        JButton btnCarregar = new JButton("Carregar Dados");
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnDeletar = new JButton("Deletar");
        JButton btnEditar = new JButton("Editar");

        Dimension dimBotao = new Dimension(160, 45);
        btnCarregar.setPreferredSize(dimBotao);
        btnRegistrar.setPreferredSize(dimBotao);
        btnDeletar.setPreferredSize(dimBotao);
        btnEditar.setPreferredSize(dimBotao);

        painelInferior.add(btnCarregar);
        painelInferior.add(btnRegistrar);
        painelInferior.add(btnDeletar);
        painelInferior.add(btnEditar);

        add(painelInferior, BorderLayout.SOUTH);

        // Ações
        btnCarregar.addActionListener(e -> carregarDados());
        btnRegistrar.addActionListener(e -> registrarVenda());
        btnDeletar.addActionListener(e -> deletarVenda());
        btnEditar.addActionListener(e -> editarVenda());
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
        });

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSelecionar);
        dialog.add(calendar, BorderLayout.CENTER);
        dialog.add(painelBotoes, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void carregarDados() { System.out.println("Carregando registros de: " + txtData.getText()); }
    private void registrarVenda() { new JanelaCadastroVenda(this).setVisible(true); }
    private void deletarVenda() { new JanelaDeletarVenda(this).setVisible(true); }
    private void editarVenda() { new JanelaEditarVenda(this).setVisible(true); }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { e.printStackTrace(); }

        SwingUtilities.invokeLater(() -> {
            new TelaRegistroVendas().setVisible(true);
        });
    }
}