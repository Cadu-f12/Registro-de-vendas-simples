package view;

import javax.swing.*;
import java.awt.*;

public class JanelaDeletarVenda extends JDialog {

    private JTextField txtIdExcluir;
    private JButton btnConfirmar, btnCancelar;

    public JanelaDeletarVenda(Frame parent) {
        super(parent, "Excluir Registro de Venda", true);
        setSize(600, 300); // Resolução mantida conforme solicitado
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // --- PAINEL CENTRAL (FORMULÁRIO) ---
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBorder(BorderFactory.createTitledBorder("Dados para Exclusão"));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Label explicativa (Voltou ao padrão)
        gbc.gridx = 0; gbc.gridy = 0;
        painelCentral.add(new JLabel("Digite o ID que deseja excluir:"), gbc);

        // Campo para o ID (Mantendo apenas o preenchimento de altura aprovado antes)
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.ipady = 15;
        txtIdExcluir = new JTextField();
        painelCentral.add(txtIdExcluir, gbc);

        add(painelCentral, BorderLayout.CENTER);

        // --- PAINEL INFERIOR (BOTÕES CENTRALIZADOS) ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");

        // Tamanho dos botões seguindo a proporção da janela
        btnConfirmar.setPreferredSize(new Dimension(150, 40));
        btnCancelar.setPreferredSize(new Dimension(150, 40));

        btnCancelar.addActionListener(e -> dispose());

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);
    }
}