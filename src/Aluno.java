
public class Aluno {
    private int rgm;
    private String nome;
    private ListaDisciplinas disciplinas;

    public Aluno(int rgm, String nome, ListaDisciplinas disciplinas) {
        this.rgm = rgm;
        this.nome = nome;
        this.disciplinas = disciplinas;
    }

    public int getRgm() {
        return rgm;
    }

    public String getNome() {
        return nome;
    }

    public ListaDisciplinas getDisciplinas() {
        return disciplinas;
    }

    public void adicionarDisciplina(Disciplina novaDisciplina) {
        this.disciplinas.adicionarDisciplina(novaDisciplina);
    }

    public Disciplina buscarDisciplina(String nomeDisciplina) {
        return this.disciplinas.buscarDisciplina(nomeDisciplina);
    }

    public boolean removerDisciplina(String nomeDisciplina) {
        return this.disciplinas.removerDisciplina(nomeDisciplina);
    }

    public void mostrarDisciplinas() {
        this.disciplinas.mostrarLista();
    }

    public Double calcularCRA() {
        return this.disciplinas.calcularCRA();
    }

    public boolean existeDisciplinas() {
        return this.disciplinas.getPrimeiraDisciplina() != null;
    }

}
