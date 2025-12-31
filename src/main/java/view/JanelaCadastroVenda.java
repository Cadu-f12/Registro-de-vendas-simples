package view;

import controller.VendaController;
import controller.VendaReplace;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Enumeration;

public class JanelaCadastroVenda extends JDialog {

    private JTextField txtQuantidade, txtProduto, txtPreco, txtTotal;
    private JButton btnConfirmar, btnCancelar;

    // Grupos para as opções de seleção
    private ButtonGroup grupoPagamento, grupoVendedor;

    public JanelaCadastroVenda(Frame parent) {
        super(parent, "Registrar Nova Venda", true);
        setSize(700, 550); // Ajuste de altura para acomodar o layout vertical
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // --- PAINEL DO FORMULÁRIO ---
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados da Venda"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // 1. FORMA DE PAGAMENTO (Radio Buttons)
        gbc.gridx = 0; gbc.gridy = 0;
        painelFormulario.add(new JLabel("Forma de Pagamento:"), gbc);

        JPanel pnlPagamento = new JPanel(new FlowLayout(FlowLayout.LEFT));
        grupoPagamento = new ButtonGroup();
        String[] opcoesPagamento = {"PIX", "Dinheiro", "Cartão de crédito", "Cartão de débito"};
        for (String opt : opcoesPagamento) {
            JRadioButton rb = new JRadioButton(opt);
            grupoPagamento.add(rb);
            pnlPagamento.add(rb);
            if(opt.equals("PIX")) rb.setSelected(true); // Seleção padrão
        }
        gbc.gridy = 1;
        painelFormulario.add(pnlPagamento, gbc);

        // 2. VENDEDOR (Radio Buttons)
        gbc.gridx = 0; gbc.gridy = 2;
        painelFormulario.add(new JLabel("Vendedor que fez a venda:"), gbc);

        JPanel pnlVendedor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        grupoVendedor = new ButtonGroup();
        String[] vendedores = {"Carlos", "Viviane", "Helena"};
        for (String v : vendedores) {
            JRadioButton rb = new JRadioButton(v);
            grupoVendedor.add(rb);
            pnlVendedor.add(rb);
            if(v.equals("Carlos")) rb.setSelected(true); // Seleção padrão
        }
        gbc.gridy = 3;
        painelFormulario.add(pnlVendedor, gbc);

        // 3. PRODUTO (Largura total)
        gbc.gridx = 0; gbc.gridy = 4;
        painelFormulario.add(new JLabel("Nome do produto:"), gbc);
        gbc.gridy = 5;
        txtProduto = new JTextField();
        painelFormulario.add(txtProduto, gbc);

        // 4. QUANTIDADE E PREÇO (Lado a lado)
        JPanel pnlNumerico = new JPanel(new GridLayout(2, 2, 10, 5));
        pnlNumerico.add(new JLabel("Quantidade que foi vendida:"));
        pnlNumerico.add(new JLabel("Preço do produto:"));

        txtQuantidade = new JTextField();
        txtPreco = new JTextField();
        pnlNumerico.add(txtQuantidade);
        pnlNumerico.add(txtPreco);

        gbc.gridy = 6;
        painelFormulario.add(pnlNumerico, gbc);

        // 5. TOTAL (Lá embaixo e centralizado)
        JPanel pnlTotal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTotal = new JLabel("Total: ");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        txtTotal = new JTextField(10);
        txtTotal.setEditable(false);
        txtTotal.setFocusable(false);
        txtTotal.setHorizontalAlignment(JTextField.CENTER);
        txtTotal.setFont(new Font("Arial", Font.BOLD, 18));
        txtTotal.setBackground(new Color(230, 230, 230)); // Destaque visual

        pnlTotal.add(lblTotal);
        pnlTotal.add(txtTotal);

        gbc.gridy = 7;
        gbc.insets = new Insets(20, 10, 10, 10);
        painelFormulario.add(pnlTotal, gbc);

        add(painelFormulario, BorderLayout.CENTER);

        // Configuração de cálculo
        configurarCalculoAutomatico();

        // --- PAINEL INFERIOR (BOTÕES) ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        btnConfirmar = new JButton("Confirmar Registro");
        btnCancelar = new JButton("Cancelar Registro");
        btnConfirmar.setPreferredSize(new Dimension(180, 40));
        btnCancelar.setPreferredSize(new Dimension(180, 40));

        btnCancelar.addActionListener(e -> dispose());
        btnConfirmar.addActionListener(e -> registrarVenda());

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void configurarCalculoAutomatico() {
        DocumentListener documentListener = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { calcularTotal(); }
            @Override public void removeUpdate(DocumentEvent e) { calcularTotal(); }
            @Override public void changedUpdate(DocumentEvent e) { calcularTotal(); }
        };
        txtQuantidade.getDocument().addDocumentListener(documentListener);
        txtPreco.getDocument().addDocumentListener(documentListener);
    }

    private void calcularTotal() {
        try {
            String qtdStr = txtQuantidade.getText().replace(",", ".");
            String precoStr = txtPreco.getText().replace(",", ".");
            if (qtdStr.isEmpty() || precoStr.isEmpty()) {
                txtTotal.setText("");
                return;
            }
            double total = Double.parseDouble(qtdStr) * Double.parseDouble(precoStr);
            DecimalFormat df = new DecimalFormat("R$ #,##0.00");
            txtTotal.setText(df.format(total));
        } catch (NumberFormatException ex) {
            txtTotal.setText("Erro");
        }
    }

    // Auxiliar para pegar o texto do Radio Button selecionado
    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) return button.getText();
        }
        return "";
    }

    private void registrarVenda() {
        try {


            // Pegando os valores dos RadioButtons e Fields
            String pagamento = getSelectedButtonText(grupoPagamento);
            String vendedor = getSelectedButtonText(grupoVendedor);

            VendaReplace vendaReplace = new VendaReplace(pagamento, vendedor, txtQuantidade.getText(), txtProduto.getText(), txtPreco.getText());

            VendaController vendaController = new VendaController();
            vendaController.registrarVenda(vendaReplace);

            JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            //Limpar campos após sucesso
            txtQuantidade.setText("");
            txtPreco.setText("");
            txtProduto.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}