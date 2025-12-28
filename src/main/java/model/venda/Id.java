package model.venda;

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