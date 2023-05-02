public class ListaDisciplinas {
    private Disciplina primeiraDisciplina;
    private Disciplina ultimaDisciplina;

    public ListaDisciplinas() {
        this.primeiraDisciplina = null;
        this.ultimaDisciplina = null;
    }

    public void adicionarDisciplina(Disciplina novaDisciplina) {
        if (this.primeiraDisciplina == null) {
            this.primeiraDisciplina = novaDisciplina;
        } else {
            this.ultimaDisciplina.setProximo(novaDisciplina);
        }
        this.ultimaDisciplina = novaDisciplina;
    }

    public Disciplina buscarDisciplina(String nomeDisciplina) {
        Disciplina disciplinaAtual = this.primeiraDisciplina;
        while (disciplinaAtual != null) {
            if(disciplinaAtual.getNome().equals(nomeDisciplina)){
                return disciplinaAtual;
            }
            disciplinaAtual = disciplinaAtual.getProximo();
        }
        return null;
    }

    public boolean removerDisciplina(String nome) {
        Disciplina anterior = null;
        Disciplina atual = this.primeiraDisciplina;
        while(atual != null) {
            if(atual.getNome().equals(nome)) {
                if(anterior == null) {
                    this.primeiraDisciplina = atual.getProximo();
                } else {
                    anterior.setProximo(atual.getProximo());
                }
                if(atual == this.ultimaDisciplina) {
                    this.ultimaDisciplina = anterior;
                }
                return true;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
        return false;
    }

    public void mostrarLista() {
        Disciplina atual = this.primeiraDisciplina;
        if(atual == null) {
            System.out.println("\t\u001b[36;1mO Aluno não está cadastrado em nenhuma disciplina\u001b[0m");
            return;
        }
        System.out.format("\t%-20s %-15s %-15s\n", "Nome", "Carga Horária", "Nota");
        while (atual != null) {
            //System.out.println("\t" + atual.getNome() + " - " + atual.getCargaHoraria() + "h - " + atual.getNota());
            System.out.format("\t%-20s %-15s %-5.2f\n", atual.getNome(), atual.getCargaHoraria(), atual.getNota());
            atual = atual.getProximo();
        }
    }

    public Double calcularCRA() {
        double somaNotasHoras = 0;
        double somaHoras = 0;
        Disciplina atual = this.primeiraDisciplina;

        while (atual != null) {
            somaNotasHoras += atual.getNota() * atual.getCargaHoraria();
            somaHoras += atual.getCargaHoraria();
            atual = atual.getProximo();
        }

        return somaNotasHoras / somaHoras;
    }

    public Disciplina getPrimeiraDisciplina() {
        return primeiraDisciplina;
    }
}
