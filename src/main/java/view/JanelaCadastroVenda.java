import javax.swing.*;
import java.awt.*;

public class JanelaCadastroVenda extends JDialog {

    private JTextField txtFormaPagamento, txtVendedor, txtQuantidade, txtProduto, txtTotal;
    private JButton btnConfirmar, btnCancelar;

    public JanelaCadastroVenda(Frame parent) {
        super(parent, "Registrar Nova Venda", true);
        setSize(800, 350); // Aumentei um pouco a largura para os campos respirarem
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // --- PAINEL DO FORMULÁRIO ---
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados da Venda"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os elementos
        gbc.fill = GridBagConstraints.HORIZONTAL; // Faz o campo esticar horizontalmente
        gbc.weightx = 1.0; // Dá peso horizontal para os campos crescerem
        gbc.ipady = 10;    // Aumenta a altura interna dos campos de texto

        // --- PRIMEIRA LINHA (3 campos) ---
        // Produto
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0; // Label não precisa crescer
        painelFormulario.add(new JLabel("Produto:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; // Campo cresce
        txtProduto = new JTextField();
        painelFormulario.add(txtProduto, gbc);

        // Vendedor
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0;
        painelFormulario.add(new JLabel("Vendedor:"), gbc);
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 1.0;
        txtVendedor = new JTextField();
        painelFormulario.add(txtVendedor, gbc);

        // Quantidade
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 0;
        painelFormulario.add(new JLabel("Qtd:"), gbc);
        gbc.gridx = 5; gbc.gridy = 0; gbc.weightx = 0.5;
        txtQuantidade = new JTextField();
        painelFormulario.add(txtQuantidade, gbc);

        // --- SEGUNDA LINHA (2 campos) ---
        // Forma de Pagamento
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painelFormulario.add(new JLabel("Pagamento:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        txtFormaPagamento = new JTextField();
        painelFormulario.add(txtFormaPagamento, gbc);

        // Total
        gbc.gridx = 2; gbc.gridy = 1; gbc.weightx = 0;
        painelFormulario.add(new JLabel("Total:"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; gbc.weightx = 1.0;
        txtTotal = new JTextField();
        painelFormulario.add(txtTotal, gbc);

        add(painelFormulario, BorderLayout.CENTER);

        // --- PAINEL INFERIOR (BOTÕES CENTRALIZADOS) ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        btnConfirmar = new JButton("Confirmar Registro");
        btnCancelar = new JButton("Cancelar Registro");

        // Opcional: Aumentar um pouco os botões também
        btnConfirmar.setPreferredSize(new Dimension(180, 40));
        btnCancelar.setPreferredSize(new Dimension(180, 40));

        btnCancelar.addActionListener(e -> dispose());

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);
    }
}