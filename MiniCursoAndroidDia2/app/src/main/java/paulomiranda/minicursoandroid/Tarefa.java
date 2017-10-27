package paulomiranda.minicursoandroid;

public class Tarefa {
    private String titulo;
    private String descricao;
    private String disciplina;
    private String data;

    public Tarefa(){

    }

    public Tarefa(String titulo, String descricao, String disciplina, String data) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.disciplina = disciplina;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
