package view;

import com.toedter.calendar.JCalendar;
import controller.VendaController;
import model.venda.Venda;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

public class JanelaEditarVenda extends JDialog {

    private JTabbedPane tabbedPane;
    private JTextField txtIdBusca;

    // Campos da Aba de Edição
    private JTextField txtProduto, txtQuantidade, txtTotal, txtData;
    private ButtonGroup grupoPagamento, grupoVendedor;
    private JButton btnConfirmar, btnCancelar;

    private final DecimalFormat df = new DecimalFormat("R$ #,##0.00");

    public JanelaEditarVenda(Frame parent, String idSelecionado) {
        super(parent, "Editar Registro de Venda", true);
        setSize(800, 700); // Resolução aumentada para melhor respiro dos botões
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        // --- ABA 1: LOCALIZAR ---
        JPanel painelBusca = criarPainelBusca(idSelecionado);
        tabbedPane.addTab("1. Localizar Registro", painelBusca);

        // --- ABA 2: EDITAR ---
        JPanel painelEdicao = criarPainelEdicao();
        tabbedPane.addTab("2. Editar Dados", painelEdicao);
        tabbedPane.setEnabledAt(1, false);

        add(tabbedPane, BorderLayout.CENTER);

        // --- PAINEL DE BOTÕES INFERIOR ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        btnConfirmar = new JButton("Carregar Registro");
        btnCancelar = new JButton("Cancelar");
        btnConfirmar.setPreferredSize(new Dimension(200, 45));
        btnCancelar.setPreferredSize(new Dimension(200, 45));

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 0) {
                btnConfirmar.setText("Carregar Registro");
            } else {
                btnConfirmar.setText("Confirmar Alteração");
            }
        });

        btnCancelar.addActionListener(e -> dispose());
        btnConfirmar.addActionListener(e -> {
            if (tabbedPane.getSelectedIndex() == 0) carregarDadosParaEdicao();
            else salvarAlteracoes();
        });

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private JPanel criarPainelBusca(String id) {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        p.add(new JLabel("ID da Venda para busca:"), gbc);
        gbc.gridy = 1; gbc.ipady = 15;
        txtIdBusca = new JTextField(id);
        p.add(txtIdBusca, gbc);
        gbc.gridy = 2; gbc.weighty = 1.0;
        p.add(new JPanel(), gbc);
        return p;
    }

    private JPanel criarPainelEdicao() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // 1. DATA
        gbc.gridy = 0; p.add(new JLabel("Data da Venda:"), gbc);
        JPanel pnlData = new JPanel(new BorderLayout(5, 0));
        txtData = new JTextField();
        txtData.setEditable(false);
        JButton btnCalendario = new JButton("Selecionar Data");
        btnCalendario.addActionListener(e -> abrirCalendario());
        pnlData.add(txtData, BorderLayout.CENTER);
        pnlData.add(btnCalendario, BorderLayout.EAST);
        gbc.gridy = 1; gbc.ipady = 10; p.add(pnlData, gbc);

        // 2. FORMA DE PAGAMENTO
        gbc.gridy = 2; gbc.ipady = 0; p.add(new JLabel("Forma de Pagamento:"), gbc);
        JPanel pnlPag = new JPanel(new FlowLayout(FlowLayout.LEFT));
        grupoPagamento = new ButtonGroup();
        String[] pags = {"PIX", "Dinheiro", "Cartão de crédito", "Cartão de débito"};
        for(String s : pags) {
            JRadioButton rb = new JRadioButton(s);
            grupoPagamento.add(rb);
            pnlPag.add(rb);
        }
        gbc.gridy = 3; p.add(pnlPag, gbc);

        // 3. VENDEDOR
        gbc.gridy = 4; p.add(new JLabel("Vendedor:"), gbc);
        JPanel pnlVend = new JPanel(new FlowLayout(FlowLayout.LEFT));
        grupoVendedor = new ButtonGroup();
        String[] vends = {"Carlos", "Viviane", "Helena"};
        for(String s : vends) {
            JRadioButton rb = new JRadioButton(s);
            grupoVendedor.add(rb);
            pnlVend.add(rb);
        }
        gbc.gridy = 5; p.add(pnlVend, gbc);

        // 4. PRODUTO
        gbc.gridy = 6; p.add(new JLabel("Nome do Produto:"), gbc);
        txtProduto = new JTextField();
        gbc.gridy = 7; gbc.ipady = 15; p.add(txtProduto, gbc);

        // 5. QUANTIDADE
        gbc.gridy = 8; gbc.ipady = 0; p.add(new JLabel("Quantidade:"), gbc);
        txtQuantidade = new JTextField();
        gbc.gridy = 9; gbc.ipady = 15; p.add(txtQuantidade, gbc);

        // --- CAMPO TOTAL ---
        gbc.gridy = 10; gbc.insets = new Insets(15, 15, 5, 15);
        JLabel lblT = new JLabel("Total da Venda:", SwingConstants.CENTER);
        lblT.setFont(new Font("Arial", Font.BOLD, 13));
        p.add(lblT, gbc);

        txtTotal = new JTextField("R$ 0,00"); // Inicia com o prefixo
        txtTotal.setEditable(true);
        txtTotal.setHorizontalAlignment(JTextField.CENTER);
        txtTotal.setFont(new Font("Arial", Font.BOLD, 18));
        txtTotal.setBackground(Color.WHITE);

// Aplicando a proteção do "R$ " e filtro de números
        ((javax.swing.text.AbstractDocument) txtTotal.getDocument()).setDocumentFilter(new javax.swing.text.DocumentFilter() {
            @Override
            public void remove(FilterBypass fb, int offset, int length) throws javax.swing.text.BadLocationException {
                // Bloqueia remover se estiver tentando apagar os 3 primeiros caracteres ("R$ ")
                if (offset < 3) return;
                super.remove(fb, offset, length);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs)
                    throws javax.swing.text.BadLocationException {
                // Bloqueia editar os 3 primeiros caracteres
                if (offset < 3) return;

                // Só permite inserir se for número ou vírgula
                if (text != null && text.matches("[0-9,]*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        gbc.gridy = 11; gbc.ipady = 20;
        p.add(txtTotal, gbc);
        return p;
    }

    // --- MÉTODOS AUXILIARES ---

    private void abrirCalendario() {
        JDialog d = new JDialog(this, "Escolha a Data", true);
        d.setSize(400, 300);
        d.setLocationRelativeTo(this);
        JCalendar cal = new JCalendar();
        JButton btnOk = new JButton("Confirmar");
        btnOk.addActionListener(e -> {
            txtData.setText(new SimpleDateFormat("dd/MM/yy").format(cal.getDate()));
            d.dispose();
        });
        d.add(cal, BorderLayout.CENTER);
        d.add(btnOk, BorderLayout.SOUTH);
        d.setVisible(true);
    }

    private String formatarDataParaUI(String dataBanco) {
        if (dataBanco == null || dataBanco.isEmpty() || dataBanco.equals("null")) return "";
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat uiFormat = new SimpleDateFormat("dd/MM/yy");
            return uiFormat.format(isoFormat.parse(dataBanco));
        } catch (Exception e) {
            return dataBanco;
        }
    }

    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) return button.getText();
        }
        return null;
    }

    private String normalizar(String texto) {
        if (texto == null) return "";
        return texto.toLowerCase()
                .replaceAll("[áàâãä]", "a")
                .replaceAll("[éèêë]", "e")
                .replaceAll("[íìîï]", "i")
                .replaceAll("[óòôõö]", "o")
                .replaceAll("[úùûü]", "u")
                .replaceAll("[ç]", "c")
                .replace("_", "")      // Remove o underline (cartao_credito -> cartaocredito)
                .replace(" de ", "")   // Remove a preposição (cartao de credito -> cartaocredito)
                .replaceAll("\\s+", "") // Remove todos os espaços restantes
                .trim();
    }

    private String stringOrNull(String input) {
        return (input == null || input.trim().isEmpty()) ? null : input.trim();
    }

    // --- LÓGICA PRINCIPAL ---

    private void carregarDadosParaEdicao() {
        String id = stringOrNull(txtIdBusca.getText());
        try {
            VendaController controller = new VendaController();
            Venda v = controller.carregarVenda(id);

            if (v != null) {
                txtData.setText(formatarDataParaUI(String.valueOf(v.getData())));
                txtProduto.setText(v.getNomeProduto());
                txtQuantidade.setText(String.valueOf(v.getQuantidade()));
                txtTotal.setText(df.format(v.getTotal())); // Carrega formatado, mas pode ser editado

                selecionarRadio(grupoPagamento, String.valueOf(v.getFormaPagamento()));
                selecionarRadio(grupoVendedor, String.valueOf(v.getNomeVendedor()));

                tabbedPane.setEnabledAt(1, true);
                tabbedPane.setSelectedIndex(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar: " + e.getMessage());
        }
    }

    private void selecionarRadio(ButtonGroup group, String valorBanco) {
        String valorBancoLimpo = normalizar(valorBanco);

        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton b = buttons.nextElement();
            String textoBotaoLimpo = normalizar(b.getText());

            // Agora comparamos os termos "secos": cartaocredito vs cartaocredito
            if (textoBotaoLimpo.equals(valorBancoLimpo) ||
                    textoBotaoLimpo.contains(valorBancoLimpo) ||
                    valorBancoLimpo.contains(textoBotaoLimpo)) {
                b.setSelected(true);
                break;
            }
        }
    }

    private String extrairSomenteValor(String textoComMoeda) {
        if (textoComMoeda == null) return "0,00";
        // Remove "R$", espaços e mantém apenas números e a vírgula
        return textoComMoeda.replace("R$", "").replace(" ", "").trim();
    }

    private void salvarAlteracoes() {
        try {
            String id = stringOrNull(txtIdBusca.getText());
            String data = stringOrNull(txtData.getText());
            String pagamento = getSelectedButtonText(grupoPagamento);
            String vendedor = getSelectedButtonText(grupoVendedor);
            String produto = txtProduto.getText();
            String quantidade = txtQuantidade.getText();

            // AQUI A MÁGICA: Pega "R$ 35,00" e transforma em "35,00"
            String totalParaBanco = extrairSomenteValor(txtTotal.getText());

            // CHAMA SEU CONTROLLER
            // controller.atualizarVenda(id, data, pagamento, vendedor, produto, quantidade, totalParaBanco);

            JOptionPane.showMessageDialog(this, "Registro " + id + " atualizado com sucesso!");
            dispose();

        } catch (Exception e) {
            MensagemErro mensagem = new MensagemErro(e);
            JOptionPane.showMessageDialog(this, "Erro: " + mensagem.getMensagem());
        }
    }
}