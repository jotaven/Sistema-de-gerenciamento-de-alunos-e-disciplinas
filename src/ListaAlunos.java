public class ListaAlunos {
    private Aluno[] listaAlunos;
    private int tamanhoMaximo;
    private int tamanho;

    public ListaAlunos(int tamanhoMaximo) {
        this.listaAlunos = new Aluno[tamanhoMaximo];
        this.tamanhoMaximo = tamanhoMaximo;
        this.tamanho = 0;
    }


    public void adicionarAluno(Aluno novoAluno){
        if(this.tamanhoMaximo == this.tamanho) {
            System.out.println("A lista de alunos está cheia.");
            return;
        }

        int i;
        for(i=this.tamanho-1; i>=0 && this.listaAlunos[i].getRgm() > novoAluno.getRgm(); i--) {
            this.listaAlunos[i+1] = this.listaAlunos[i];
        }

        this.listaAlunos[i+1] = novoAluno;
        this.tamanho++;
    }
    public Aluno buscarAluno(int rgm){
        for(int i=0; i<this.tamanho; i++) {
            if(rgm == this.listaAlunos[i].getRgm()){
                return this.listaAlunos[i];
            }
        }
        return null;
    }
    public boolean removerAluno(int rgm){

        for(int i=0; i<this.tamanho; i++) {
            if(rgm == this.listaAlunos[i].getRgm()) {
                for (int j = i; j < this.tamanho - 1; j++) {
                    this.listaAlunos[j] = this.listaAlunos[j + 1];
                }
                this.tamanho--;
                this.listaAlunos[this.tamanho] = null;
                return true;
            }
        }
        return false;
    }

    public void mostrarLista() {
        if(tamanho == 0) {
            System.out.println("\t\033[36;1mA Lista está vazia.\033[0m");
        }
        for(int i=0; i<tamanho; i++) {
            System.out.println((i+1) + ". RGM " + this.listaAlunos[i].getRgm() + " - " + this.listaAlunos[i].getNome() + ":");
            this.listaAlunos[i].getDisciplinas().mostrarLista();
        }
    }

    public int getTamanhoMaximo() {
        return tamanhoMaximo;
    }

    public int getTamanho() {
        return tamanho;
    }

}
