import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final int TAMANHO_LISTA = 60;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        ListaAlunos listaAlunos = new ListaAlunos(TAMANHO_LISTA);

        char ch;
        do {
            System.out.println("\n--------MENU--------");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Mostrar todos os alunos e suas disciplinas");
            System.out.println("3 - Buscar aluno");
            System.out.println("4 - Remover aluno");
            System.out.println("0 - Sair");

            ch = sc.nextLine().charAt(0);

            limpaTela();
            switch (ch) {
                case '1' -> cadastrarAluno(listaAlunos);
                case '2' -> mostarAlunos(listaAlunos);
                case '3' -> buscaAluno(listaAlunos);
                case '4' -> removerAluno(listaAlunos);
                case '0' -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida");
            }
        } while (ch != '0');

        sc.close();
    }

    public static void cadastrarAluno(ListaAlunos listaAlunos) {
        System.out.println("•Cadastro de aluno");

        System.out.println("Informe o RGM do aluno:");
        int rgm = sc.nextInt();
        sc.nextLine();

        System.out.println("Informe o nome do aluno:");
        String nomeAluno = sc.nextLine();

        char opcaoDisciplina;
        boolean respostaValida;
        do {
            System.out.println("Deseja cadastrar uma disciplina ao aluno? (1 - Sim, 2 - Não)");
            opcaoDisciplina = sc.nextLine().charAt(0);

            if (opcaoDisciplina == '1' || opcaoDisciplina == '2') {
                respostaValida = true;
            } else {
                System.out.println("\nOpção inválida. Por favor, digite 1 para sim ou 2 para não.\n");
                respostaValida = false;
            }
        } while (!respostaValida);


        ListaDisciplinas listaDisciplinas = new ListaDisciplinas();


        while (opcaoDisciplina == '1') {
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

            do {
                System.out.println("Deseja cadastrar mais uma disciplina ao aluno? (1 - Sim, 2 - Não)");
                opcaoDisciplina = sc.nextLine().charAt(0);

                if (opcaoDisciplina == '1' || opcaoDisciplina == '2') {
                    respostaValida = true;
                } else {
                    System.out.println("\nOpção inválida. Por favor, digite 1 para sim ou 2 para não.\n");
                    respostaValida = false;
                }
            } while (!respostaValida);
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


        char chBusca;
        do {
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

                chBusca = sc.nextLine().charAt(0);
                limpaTela();


                switch (chBusca) {
                    case '1':
                        char chNovaDisciplina;
                        do {
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

                            boolean respostaValida;
                            do {
                                System.out.println("Mais alguma disciplina? (1 - Sim, 2 - Não)");
                                chNovaDisciplina = sc.nextLine().charAt(0);

                                if (chNovaDisciplina == '1' || chNovaDisciplina == '2') {
                                    respostaValida = true;
                                } else {
                                    System.out.println("\nOpção inválida. Por favor, digite 1 para sim ou 2 para não.\n");
                                    respostaValida = false;
                                }
                            } while (!respostaValida);

                        } while (chNovaDisciplina == '1');
                        break;

                    case '2':
                        System.out.println("\n•Deletar disciplina\n");
                        System.out.println("Disciplinas:");
                        alunoBusca.mostrarDisciplinas();
                        System.out.println("\nDigite o nome da disciplina:");
                        String deletaDisciplina = sc.nextLine();

                        boolean deletaDisciplinaBusca = alunoBusca.removerDisciplina(deletaDisciplina);
                        if(!deletaDisciplinaBusca) {
                            System.out.println("\u001b[36;1mDisciplina inválida!\u001b[0m");
                            sleep(1000);
                            break;
                        }

                        System.out.println("Disciplina removida com sucesso! ");
                        break;

                    case '3':
                        System.out.println("\n•Alterar disciplina\n");
                        System.out.println("Disciplinas:");
                        alunoBusca.mostrarDisciplinas();
                        System.out.println("\nDigite o nome da disciplina:");
                        String disciplina = sc.nextLine();

                        Disciplina disciplinaBusca = alunoBusca.buscarDisciplina(disciplina);
                        if(disciplinaBusca == null) {
                            System.out.println("\u001b[31mDisciplina inválida!\u001b[0m");
                            sleep(1500);
                            break;
                        }

                        char chDisciplinaBusca;
                        do {
                            System.out.println("\nEscolha uma opção:");
                            System.out.println("1 - Alterar nome");
                            System.out.println("2 - Alterar carga horária");
                            System.out.println("3 - Alterar nota");
                            System.out.println("0 - Voltar para o menu");

                            chDisciplinaBusca = sc.nextLine().charAt(0);

                            limpaTela();

                            switch (chDisciplinaBusca) {
                                case '1':
                                    System.out.println("Disciplinas do aluno: " + alunoBusca.getNome());
                                    alunoBusca.mostrarDisciplinas();
                                    System.out.println("Qual o novo nome da disciplina?");
                                    String novoNomeDisciplina = sc.nextLine();
                                    disciplinaBusca.setNome(novoNomeDisciplina);
                                    break;

                                case '2':
                                    System.out.println("Disciplinas do aluno: " + alunoBusca.getNome());
                                    alunoBusca.mostrarDisciplinas();
                                    System.out.println("Qual a nova carga horária da disciplina " + disciplina + "?");
                                    int novaCargaHorariaDisciplina = sc.nextInt();
                                    sc.nextLine();
                                    disciplinaBusca.setCargaHoraria(novaCargaHorariaDisciplina);
                                    break;

                                case '3':
                                    System.out.println("Disciplinas do aluno: " + alunoBusca.getNome());
                                    alunoBusca.mostrarDisciplinas();
                                    System.out.println("Qual a nova nota do aluno na disciplina " + disciplina + "?");
                                    double novaNotaDisciplina = Double.parseDouble(sc.nextLine());
                                    disciplinaBusca.setNota(novaNotaDisciplina);
                                    break;

                                case '0':
                                    limpaTela();
                                    chBusca = '0';
                                    break;

                                default:
                                    System.out.println("\n\u001b[36;1mOpção inválida!\u001b[0m");
                                    sleep(1000);
                                    break;
                            }
                        } while (chDisciplinaBusca != '0');
                        break;

                    case '0':
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

                chBusca = sc.nextLine().charAt(0);
                limpaTela();

                switch (chBusca) {
                    case '1':
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

                            boolean respostaValida;
                            do {
                                System.out.println("Mais alguma disciplina? (1 - Sim, 2 - Não)");
                                opcaoNovaDisciplina = sc.nextLine().charAt(0);

                                if (opcaoNovaDisciplina == '1' || opcaoNovaDisciplina == '2') {
                                    respostaValida = true;
                                } else {
                                    System.out.println("\nOpção inválida. Por favor, digite 1 para sim ou 2 para não.\n");
                                    respostaValida = false;
                                }
                            } while (!respostaValida);
                        }
                        break;

                    case '0':
                        System.out.println("Voltando...");
                        limpaTela();
                        break;

                    default:
                        System.out.println("\u001b[36;1mOpção inválida!\u001b[0m\n");
                        sleep(1000);
                        break;
                }
            }
        } while (chBusca != '0');
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
        limpaTela();
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