package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.text.DecimalFormat;

public class JanelaCadastroVenda extends JDialog {

    // Adicionei o txtTotal aqui
    private JTextField txtFormaPagamento, txtVendedor, txtQuantidade, txtProduto, txtPreco, txtTotal;
    private JButton btnConfirmar, btnCancelar;

    public JanelaCadastroVenda(Frame parent) {
        super(parent, "Registrar Nova Venda", true);
        setSize(900, 350); // Aumentei ligeiramente a largura para acomodar o 3º campo na linha 2
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // --- PAINEL DO FORMULÁRIO ---
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados da Venda"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.ipady = 10;

        // --- PRIMEIRA LINHA (3 campos) ---
        // Produto
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        painelFormulario.add(new JLabel("Produto:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
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
        painelFormulario.add(new JLabel("Quantidade:"), gbc);
        gbc.gridx = 5; gbc.gridy = 0; gbc.weightx = 0.5;
        txtQuantidade = new JTextField();
        painelFormulario.add(txtQuantidade, gbc);

        // --- SEGUNDA LINHA (Agora com 3 campos) ---
        // Forma de Pagamento
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painelFormulario.add(new JLabel("Forma de pagamento:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        txtFormaPagamento = new JTextField();
        painelFormulario.add(txtFormaPagamento, gbc);

        // Preço do Produto
        gbc.gridx = 2; gbc.gridy = 1; gbc.weightx = 0;
        painelFormulario.add(new JLabel("Preço do produto:"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; gbc.weightx = 1.0;
        txtPreco = new JTextField();
        painelFormulario.add(txtPreco, gbc);

        // --- NOVO CAMPO: Total ---
        gbc.gridx = 4; gbc.gridy = 1; gbc.weightx = 0; // Peso 0 para a Label
        painelFormulario.add(new JLabel("Total:"), gbc);
        gbc.gridx = 5; gbc.gridy = 1; gbc.weightx = 0.5; // Peso 0.5 para alinhar com Quantidade
        txtTotal = new JTextField();
        txtTotal.setEditable(false); // O usuário não pode digitar aqui, é automático
        txtTotal.setFocusable(false); // Pula esse campo ao usar TAB
        painelFormulario.add(txtTotal, gbc);

        add(painelFormulario, BorderLayout.CENTER);

        // --- CONFIGURAÇÃO DOS LISTENERS PARA CÁLCULO AUTOMÁTICO ---
        configurarCalculoAutomatico();


        // --- PAINEL INFERIOR (BOTÕES CENTRALIZADOS) ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        btnConfirmar = new JButton("Confirmar Registro");
        btnCancelar = new JButton("Cancelar Registro");

        btnConfirmar.setPreferredSize(new Dimension(180, 40));
        btnCancelar.setPreferredSize(new Dimension(180, 40));

        btnCancelar.addActionListener(e -> dispose());
//        btnConfirmar.addActionListener(e -> );

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    /**
     * Configura os "ouvintes" nos campos de Quantidade e Preço.
     * Sempre que algo for digitado neles, o cálculo do total será acionado.
     */
    private void configurarCalculoAutomatico() {
        // Cria um ouvinte genérico que chama o método calcularTotal()
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { calcularTotal(); }
            @Override
            public void removeUpdate(DocumentEvent e) { calcularTotal(); }
            @Override
            public void changedUpdate(DocumentEvent e) { calcularTotal(); }
        };

        // Adiciona esse ouvinte aos documentos dos campos de texto
        txtQuantidade.getDocument().addDocumentListener(documentListener);
        txtPreco.getDocument().addDocumentListener(documentListener);
    }

    /**
     * Realiza a matemática: Pega os textos, converte para números, multiplica e formata.
     */
    private void calcularTotal() {
        try {
            // Pega o texto e substitui vírgula por ponto para garantir que o Java entenda como número decimal
            String qtdStr = txtQuantidade.getText().replace(",", ".");
            String precoStr = txtPreco.getText().replace(",", ".");

            // Se algum campo estiver vazio, não calcula e limpa o total
            if (qtdStr.isEmpty() || precoStr.isEmpty()) {
                txtTotal.setText("");
                return;
            }

            // Converte para double (número decimal)
            double quantidade = Double.parseDouble(qtdStr);
            double preco = Double.parseDouble(precoStr);

            // Calcula
            double total = quantidade * preco;

            // Formata o resultado para 2 casas decimais (padrão monetário)
            DecimalFormat df = new DecimalFormat("#,##0.00");
            txtTotal.setText(df.format(total));

        } catch (NumberFormatException ex) {
            // Se o usuário digitar letras ou algo inválido, limpa o total ou mostra erro
            txtTotal.setText("Erro no valor");
        }
    }
}