import javax.swing.*;
import java.awt.*;

public class JanelaEditarVenda extends JDialog {

    private JTextField txtIdBusca;
    private JButton btnCarregar;
    private JTextField txtFormaPagamento, txtVendedor, txtQuantidade, txtProduto, txtTotal;
    private JButton btnConfirmar, btnCancelar;

    public JanelaEditarVenda(Frame parent) {
        super(parent, "Editar Registro de Venda", true);
        // Resolução aumentada para garantir que todos os campos apareçam
        setSize(1000, 700);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(15, 15));

        // --- PAINEL SUPERIOR (Container Lado a Lado) ---
        JPanel painelSuperiorContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        painelSuperiorContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- PAINEL ESQUERDO (BUSCA) ---
        JPanel painelEsquerdo = new JPanel(new GridBagLayout());
        painelEsquerdo.setBorder(BorderFactory.createTitledBorder(" 1. Localizar Registro "));
        GridBagConstraints gbcL = new GridBagConstraints();
        gbcL.insets = new Insets(15, 15, 15, 15);
        gbcL.fill = GridBagConstraints.HORIZONTAL;
        gbcL.weightx = 1.0;
        gbcL.anchor = GridBagConstraints.NORTH; // Alinha os itens ao topo

        gbcL.gridx = 0; gbcL.gridy = 0;
        painelEsquerdo.add(new JLabel("ID da Venda:"), gbcL);

        gbcL.gridx = 0; gbcL.gridy = 1;
        gbcL.ipady = 15;
        txtIdBusca = new JTextField();
        painelEsquerdo.add(txtIdBusca, gbcL);

        gbcL.gridx = 0; gbcL.gridy = 2;
        gbcL.ipady = 10;
        btnCarregar = new JButton("Carregar Registro");
        painelEsquerdo.add(btnCarregar, gbcL);

        // Espaçador para empurrar tudo para cima no painel de busca
        gbcL.gridy = 3; gbcL.weighty = 1.0;
        painelEsquerdo.add(new JPanel(), gbcL);

        // --- PAINEL DIREITO (FORMULÁRIO VERTICAL) ---
        JPanel painelDireito = new JPanel(new GridBagLayout());
        painelDireito.setBorder(BorderFactory.createTitledBorder(" 2. Editar Dados "));
        GridBagConstraints gbcR = new GridBagConstraints();
        gbcR.insets = new Insets(8, 15, 8, 15);
        gbcR.fill = GridBagConstraints.HORIZONTAL;
        gbcR.weightx = 1.0;

        String[] labels = {"Produto:", "Vendedor:", "Quantidade:", "Forma Pagamento:", "Total:"};
        JTextField[] campos = {
                txtProduto = new JTextField(),
                txtVendedor = new JTextField(),
                txtQuantidade = new JTextField(),
                txtFormaPagamento = new JTextField(),
                txtTotal = new JTextField()
        };

        for (int i = 0; i < labels.length; i++) {
            gbcR.gridx = 0; gbcR.gridy = i * 2;
            painelDireito.add(new JLabel(labels[i]), gbcR);

            gbcR.gridx = 0; gbcR.gridy = (i * 2) + 1;
            gbcR.ipady = 15; // Altura interna generosa para os campos
            painelDireito.add(campos[i], gbcR);
        }

        painelSuperiorContainer.add(painelEsquerdo);
        painelSuperiorContainer.add(painelDireito);

        add(painelSuperiorContainer, BorderLayout.CENTER);

        // --- PAINEL INFERIOR (BOTÕES) ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        btnConfirmar = new JButton("Confirmar Alteração");
        btnCancelar = new JButton("Cancelar");

        btnConfirmar.setPreferredSize(new Dimension(200, 50));
        btnCancelar.setPreferredSize(new Dimension(200, 50));

        btnCancelar.addActionListener(e -> dispose());

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);
    }
}