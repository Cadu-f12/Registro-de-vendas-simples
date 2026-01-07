package model.venda;

import dao.IntegridadeDeDados;

public class Id {
    private final int id;

    public Id(int id) {
        validarTamanho(id);
        this.id = id;
    }

    private void validarTamanho(int id) {
        if (id <= 0) {
            throw new AtributoForaDoIntervaloException("id_venda inválido: id <= 0");
        }
    }

    public void verificarExistenciaId() {
        // Chamar classe responsável por verificar o banco de dados
        IntegridadeDeDados integridade = new IntegridadeDeDados();
        int resultado = integridade.consultarExistenciaId(id);
        // Disparar exceção caso seja encontrado o problema
        if (resultado == 0) {
            throw new AtributoNaoEncontradoException("id_venda inválido: id não encontrado no banco de dados");
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Id{" +
                "id=" + id +
                '}';
    }
}