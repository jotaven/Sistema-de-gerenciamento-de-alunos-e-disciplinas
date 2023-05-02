import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final int TAMANHO_LISTA = 60;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        ListaAlunos listaAlunos = new ListaAlunos(TAMANHO_LISTA);

        int opcao;
        do {
            System.out.println("\n--------MENU--------");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Mostrar todos os alunos e suas disciplinas");
            System.out.println("3 - Buscar aluno");
            System.out.println("4 - Remover aluno");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();
            limpaTela();
            switch (opcao) {
                case 1:
                    cadastrarAluno(listaAlunos);
                    break;

                case 2:
                    mostarAlunos(listaAlunos);
                    break;

                case 3:
                    buscaAluno(listaAlunos);
                    break;

                case 4:
                    removerAluno(listaAlunos);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 0);

        sc.close();
    }

    public static void cadastrarAluno(ListaAlunos listaAlunos) {
        System.out.println("•Cadastro de aluno");

        System.out.println("Informe o RGM do aluno:");
        int rgm = sc.nextInt();
        sc.nextLine();

        System.out.println("Informe o nome do aluno:");
        String nomeAluno = sc.nextLine();

        System.out.println("Deseja cadastrar uma disciplina ao aluno? (1 - Sim, 2 - Não)");
        int opcaoDisciplina = sc.nextInt();
        sc.nextLine();

        ListaDisciplinas listaDisciplinas = new ListaDisciplinas();


        while (opcaoDisciplina == 1) {
            System.out.println("\n•Cadastro de disciplinas do aluno");
            System.out.println("Informe o nome da disciplina:");
            String nomeDisciplina = sc.nextLine();

            System.out.println("Informe a carga horária da disciplina:");
            int cargaHoraria = sc.nextInt();
            sc.nextLine();

            System.out.println("Informe a nota da disciplina:");
            double nota = Double.parseDouble(sc.nextLine());

            Disciplina disciplina = new Disciplina(nomeDisciplina, cargaHoraria, nota);
            listaDisciplinas.adicionarDisciplina(disciplina);

            System.out.println("Mais alguma disciplina? (1 - Sim, 2 - Não)");
            opcaoDisciplina = sc.nextInt();
            sc.nextLine();
            limpaTela();
        }

        Aluno aluno = new Aluno(rgm, nomeAluno, listaDisciplinas);
        listaAlunos.adicionarAluno(aluno);

        System.out.println("\u001b[32mAluno cadastrado com sucesso!\u001b[0m");
        sleep(1500);

        limpaTela();
    }

    public static void mostarAlunos(ListaAlunos listaAlunos) {
        System.out.println("Lista de alunos e disciplinas:");
        listaAlunos.mostrarLista();
    }

    public static void buscaAluno(ListaAlunos listaAlunos){
        if(listaAlunos.getTamanho() == 0) {
            System.out.println("\u001b[36;1mA lista está vazia!\u001b[0m");
            return;
        }

        System.out.println("Buscar aluno");
        System.out.println("Informe o RGM do aluno:");
        int rgmBusca = sc.nextInt();
        sc.nextLine();

        Aluno alunoBusca = listaAlunos.buscarAluno(rgmBusca);

        if (alunoBusca == null) {
            System.out.println("\u001b[31mAluno não encontrado!\u001b[0m");
            sleep(1000);
            return;
        }


        int opcaoBusca = -1;
        while (opcaoBusca != 0) {
            limpaTela();
            System.out.println("RGM: " + alunoBusca.getRgm());
            System.out.println("Nome: " + alunoBusca.getNome());
            if (alunoBusca.existeDisciplinas())
                System.out.printf("CRA: %.2f\n", alunoBusca.calcularCRA());
            System.out.println("Disciplinas:");
            alunoBusca.mostrarDisciplinas();

            if(alunoBusca.existeDisciplinas()) {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Adicionar disciplina");
                System.out.println("2 - Deletar disciplina");
                System.out.println("3 - Alterar disciplina");
                System.out.println("0 - Voltar");

                opcaoBusca = sc.nextInt();
                sc.nextLine();
                limpaTela();


                switch (opcaoBusca) {
                    case 1:
                        int opcaoNovaDisciplina = 1;
                        while (opcaoNovaDisciplina == 1) {
                            System.out.println("•Adicionar disciplina\n");
                            System.out.println("Informe o nome da disciplina:");
                            String nomeDisciplina = sc.nextLine();

                            System.out.println("Informe a carga horária da disciplina:");
                            int cargaHoraria = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Informe a nota da disciplina:");
                            double nota = Double.parseDouble(sc.nextLine());

                            Disciplina novaDisciplina = new Disciplina(nomeDisciplina, cargaHoraria, nota);

                            alunoBusca.adicionarDisciplina(novaDisciplina);

                            System.out.println("Mais alguma disciplina? (1 - Sim, 2 - Não)");
                            opcaoNovaDisciplina = sc.nextInt();
                            sc.nextLine();
                        }
                        break;

                    case 2:
                        System.out.println("\n•Deletar disciplina\n");
                        System.out.println("Digite o nome da disciplina:");
                        String deletaDisciplina = sc.nextLine();

                        Boolean deletaDisciplinaBusca = alunoBusca.removerDisciplina(deletaDisciplina);
                        if(deletaDisciplinaBusca == false) {
                            System.out.println("\u001b[36;1mDisciplina inválida!\u001b[0m");
                            sleep(1000);
                            opcaoBusca = -1;
                            break;
                        }

                        System.out.println("Disciplina removida com sucesso! ");
                        break;

                    case 3:
                        System.out.println("\n•Alterar disciplina\n");
                        System.out.println("Digite o nome da disciplina:");
                        String disciplina = sc.nextLine();

                        Disciplina disciplinaBusca = alunoBusca.buscarDisciplina(disciplina);
                        if(disciplinaBusca == null) {
                            System.out.println("\u001b[31mDisciplina inválida!\u001b[0m");
                            sleep(1500);
                            opcaoBusca = -1;
                            break;
                        }

                        int opcaoDisciplinaBusca = -1;
                        while(opcaoDisciplinaBusca != 0) {
                            System.out.println("\nEscolha uma opção:");
                            System.out.println("1 - Alterar nome");
                            System.out.println("2 - Alterar carga horária");
                            System.out.println("3 - Alterar nota");
                            System.out.println("0 - Voltar para o menu");

                            opcaoDisciplinaBusca = sc.nextInt();
                            sc.nextLine();

                            limpaTela();

                            System.out.println("Disciplinas do aluno : " + alunoBusca.getNome());
                            alunoBusca.mostrarDisciplinas();
                            switch (opcaoDisciplinaBusca) {
                                case 1:
                                    System.out.println("Qual o novo nome da disciplina?");
                                    String novoNomeDisciplina = sc.nextLine();
                                    disciplinaBusca.setNome(novoNomeDisciplina);
                                    break;

                                case 2:
                                    System.out.println("Qual a nova carga horária da disciplina " + disciplina + "?");
                                    int novaCargaHorariaDisciplina = sc.nextInt();
                                    sc.nextLine();
                                    disciplinaBusca.setCargaHoraria(novaCargaHorariaDisciplina);
                                    break;

                                case 3:
                                    System.out.println("Qual a nova nota do aluno na disciplina " + disciplina + "?");
                                    double novaNotaDisciplina = Double.parseDouble(sc.nextLine());
                                    disciplinaBusca.setNota(novaNotaDisciplina);
                                    break;

                                case 0:
                                    opcaoBusca = 0;
                                    break;

                                default:
                                    System.out.println("\u001b[36;1mOpção inválida!\u001b[0m\n");
                                    sleep(1000);
                                    break;
                            }
                        }
                        break;

                    case 0:
                        System.out.println("Voltando...");
                        limpaTela();
                        break;

                    default:
                        System.out.println("\u001b[36;1mOpção inválida!\u001b[0m\n");
                        sleep(1000);
                        break;
                }
            } else {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Adicionar disciplina");
                System.out.println("0 - Voltar");

                opcaoBusca = sc.nextInt();
                sc.nextLine();
                limpaTela();

                switch (opcaoBusca) {
                    case 1:
                        limpaTela();
                        int opcaoNovaDisciplina = 1;
                        while (opcaoNovaDisciplina == 1) {
                            System.out.println("•Adicionar disciplina\n");
                            System.out.println("Informe o nome da disciplina:");
                            String nomeDisciplina = sc.nextLine();

                            System.out.println("Informe a carga horária da disciplina:");
                            int cargaHoraria = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Informe a nota da disciplina:");
                            double nota = Double.parseDouble(sc.nextLine());

                            Disciplina novaDisciplina = new Disciplina(nomeDisciplina, cargaHoraria, nota);

                            alunoBusca.adicionarDisciplina(novaDisciplina);

                            System.out.println("Mais alguma disciplina? (1 - Sim, 2 - Não)");
                            opcaoNovaDisciplina = sc.nextInt();
                            sc.nextLine();
                        }
                        break;

                    case 0:
                        System.out.println("Voltando...");
                        limpaTela();
                        break;

                    default:
                        System.out.println("\u001b[36;1mOpção inválida!\u001b[0m\n");
                        sleep(1000);
                        break;
                }
            }
        }
    }

    public static void removerAluno(ListaAlunos listaAlunos) {
        if(listaAlunos.getTamanho() == 0) {
            System.out.println("\u001b[36;1mA lista está vazia!\u001b[0m");
            return;
        }

        System.out.println("Remover aluno");
        System.out.println("Informe o RGM do aluno:");
        int rgmRemover = sc.nextInt();
        sc.nextLine();
        if (listaAlunos.removerAluno(rgmRemover)) {
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Aluno não encontrado");
        }

    }

    public static void limpaTela() {
        for(int i=0; i<20; i++) {
            System.out.println("\n");
        }
//        System.out.println("Limpando a tela....");
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}