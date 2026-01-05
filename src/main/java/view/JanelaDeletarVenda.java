package view;

import controller.VendaController;

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
        btnConfirmar.addActionListener(e -> deletarVenda());

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void deletarVenda() {
        try {
            // Tranformar os valores vazios em Null
            String IdExcluir = stringOrNull(txtIdExcluir.getText());

            // Verificar se o valor a ser inserido é apenas digito
            if (IdExcluir != null) {
                for (char c : IdExcluir.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        throw new IllegalArgumentException("Id inválido!\nPor favor insira novamente");
                    }
                }
            }

            // Deletar venda
            VendaController vendaController = new VendaController();
            vendaController.deletarVenda(IdExcluir);

            // Limpar campos e mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Registro deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            txtIdExcluir.setText("");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            MensagemErro mensagemErro = new MensagemErro(e);
            JOptionPane.showMessageDialog(this, mensagemErro.getMensagem(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String stringOrNull(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        return input.trim();
    }
}