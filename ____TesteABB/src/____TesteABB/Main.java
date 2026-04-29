package ____TesteABB;

import java.util.Scanner;

public class Main {

    private static ABB<Funcionario> abb = new ABB<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Opção: ");
            System.out.println();

            switch (opcao) {
                case 1: opcao1_cadastrarFuncionario();    break;
                case 2: opcao2_mostrarTodos();            break;
                case 3: opcao3_gastosComSalarios();       break;
                case 4: opcao4_totalPorSexo();            break;
                case 5: opcao5_totalPorCategoria();       break;
                case 6: opcao6_funcionariosPorIdade();    break;
                case 7: opcao7_simularCadastro();         break;
                case 8: opcao8_sair();                    break;
                default:
                    System.out.println("  Opção inválida! Digite um número de 1 a 8. ");
            }

        } while (opcao != 8);

        sc.close();
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("    --   Sistema de Funcionários   --");
        System.out.println(" 1 - Cadastrar funcionário ");
        System.out.println(" 2 - Mostrar dados de todos os funcionários");
        System.out.println(" 3 - Mostrar gastos com salários");
        System.out.println(" 4 - Mostrar total de funcionários por sexo");
        System.out.println(" 5 - Mostrar quantidade de funcionários por categoria");
        System.out.println(" 6 - Mostrar funcionários a partir de certa idade");
        System.out.println(" 7 - Simular cadastro de funcionários");
        System.out.println(" 8 - Sair");
    }

    // OPÇÃO 1 – Cadastrar funcionário
    private static void opcao1_cadastrarFuncionario() {
        System.out.println("─── Cadastrar Funcionário");

        int id = lerInteiro("  ID (número inteiro único): ");

        Funcionario temp = new Funcionario(id, 'P', "", "", 'M', 0, 0.0);
        if (abb.search(temp) != null) {
            System.out.println("  Erro: já existe um funcionário com o ID " + id + ".");
            return;
        }

        System.out.print("  Nome: ");
        String nome = sc.nextLine().trim();

        System.out.print("  Cargo: ");
        String cargo = sc.nextLine().trim();

        char sexo = lerChar("  Sexo (F/M): ", new char[]{'F', 'M'});
        char cat  = lerChar("  Categoria (P/O/H): ", new char[]{'P', 'O', 'H'});
        int    idade  = lerInteiro("  Idade: ", 0, true);
        double salario = lerDouble("  Salário (R$): ", 0.0, true);

        Funcionario f = new Funcionario(id, cat, nome, cargo, sexo, idade, salario);
        abb.inserir(f);
        System.out.println("\n  ✔ Funcionário cadastrado com sucesso!");
        System.out.println("  " + f);
    }

  
    // OPÇÃO 2 – Mostrar dados de todos os funcionários cadastrados
    private static void opcao2_mostrarTodos() {
        System.out.println("─── Funcionários Cadastrados (ordem por ID) ──────");

        if (abb.isEmpty()) {
            System.out.println("  Nenhum funcionário cadastrado.");
            return;
        }

        System.out.println();
        int total = abb.mostrarTodos();
        System.out.println("\n  Total: " + total + " funcionário(s).");
    }

    // OPÇÃO 3 – Mostrar gastos com salários dos funcionários
    private static void opcao3_gastosComSalarios() {
        System.out.println("─── Gastos com Salários ──────────────────────────");

        if (abb.isEmpty()) {
            System.out.println("  Nenhum funcionário cadastrado.");
            return;
        }

        double total = abb.calcularSalarios();
        System.out.printf("  Gasto total mensal com salários: R$ %,.2f%n", total);
    }


    // OPÇÃO 4 – Mostrar total de funcionários de determinado sexo
    private static void opcao4_totalPorSexo() {
        System.out.println("─── Total de Funcionários por Sexo ───────────────");

        if (abb.isEmpty()) {
            System.out.println("  Nenhum funcionário cadastrado.");
            return;
        }

        char sexo = lerChar("  Informe o sexo (F/M): ", new char[]{'F', 'M'});
        int count = abb.contarPorSexo(sexo);
        String desc = (sexo == 'F') ? "Feminino" : "Masculino";
        System.out.printf("  Total de funcionários do sexo %s: %d%n", desc, count);
    }

    // OPÇÃO 5 – Mostrar total de funcionários de determinada categoria
    private static void opcao5_totalPorCategoria() {
        System.out.println("─── Total de Funcionários por Categoria ──────────");

        if (abb.isEmpty()) {
            System.out.println("  Nenhum funcionário cadastrado.");
            return;
        }

        char categoria = lerChar("  Informe a categoria (P/O/H): ", new char[]{'P', 'O', 'H'});
        int count = abb.contarPorCategoria(categoria);
        System.out.printf("  Total de funcionários da categoria %c: %d%n", categoria, count);
    }

    // OPÇÃO 6 – Mostrar total de funcionários de determinada idade
    private static void opcao6_funcionariosPorIdade() {
        System.out.println("─── Total de Funcionários por Idade ───────────────");

        if (abb.isEmpty()) {
            System.out.println("  Nenhum funcionário cadastrado.");
            return;
        }

        int idade = lerInteiro("  Informe a idade: ", 0, true);
        int count = abb.contarPorIdade(idade);
        System.out.printf("  Total de funcionários com %d anos: %d%n", idade, count);
    }

    // OPÇÃO 7 – Simular cadastro de funcionários (insere 20 funcionários)
    private static void opcao7_simularCadastro() {
        System.out.println("─── Simulação: Cadastro de 20 Funcionários ───────");

        Object[][] dados = {
            {1001, 'P', "Ana Lima",        "Analista",        'F', 28, 5800.00},
            {1002, 'O', "Bruno Souza",     "Desenvolvedor",   'M', 35, 7200.00},
            {1003, 'H', "Carla Mendes",    "Gerente",         'F', 42, 12000.00},
            {1004, 'P', "Diego Nunes",     "Estagiário",      'M', 22, 1800.00},
            {1005, 'O', "Eliane Rocha",    "Designer",        'F', 31, 4900.00},
            {1006, 'H', "Felipe Torres",   "Scrum Master",    'M', 38, 9500.00},
            {1007, 'P', "Gabriela Costa",  "Suporte",         'F', 26, 3200.00},
            {1008, 'O', "Henrique Dias",   "DevOps",          'M', 33, 8100.00},
            {1009, 'H', "Isabel Ferreira", "QA",              'F', 29, 5200.00},
            {1010, 'P', "João Martins",    "Arquiteto",       'M', 45, 15000.00},
            {1011, 'O', "Karen Ribeiro",   "RH",              'F', 37, 5600.00},
            {1012, 'H', "Lucas Alves",     "Produto",         'M', 30, 7800.00},
            {1013, 'P', "Mariana Gomes",   "Financeiro",      'F', 27, 4200.00},
            {1014, 'O', "Natan Cardoso",   "Marketing",       'M', 24, 3800.00},
            {1015, 'H', "Olívia Castro",   "Jurídico",        'F', 40, 10500.00},
            {1016, 'P', "Paulo Oliveira",  "Infraestrutura",  'M', 36, 7400.00},
            {1017, 'O', "Quintina Santos", "Vendas",          'F', 32, 4600.00},
            {1018, 'H', "Rafael Pereira",  "Diretor TI",      'M', 50, 22000.00},
            {1019, 'P', "Sandra Melo",     "Contabilidade",   'F', 44, 6800.00},
            {1020, 'O', "Thiago Lima",     "Segurança Info",  'M', 39, 9200.00}
        };

        int inseridos = 0, ignorados = 0;
        for (Object[] d : dados) {
            int    id    = (int)    d[0];
            char   cat   = (char)   d[1];
            String nome  = (String) d[2];
            String cargo = (String) d[3];
            char   sexo  = (char)   d[4];
            int    idade = (int)    d[5];
            double sal   = (double) d[6];

            Funcionario temp = new Funcionario(id, 'P', "", "", 'M', 0, 0.0);
            if (abb.search(temp) == null) {
                abb.inserir(new Funcionario(id, cat, nome, cargo, sexo, idade, sal));
                System.out.printf("  ✔ Inserido: %05d – %s%n", id, nome);
                inseridos++;
            } else {
                System.out.printf("  ✗ Ignorado (ID já existe): %05d – %s%n", id, nome);
                ignorados++;
            }
        }
        System.out.printf("%n  Resultado: %d inserido(s), %d ignorado(s).%n",
                          inseridos, ignorados);
    }

    // OPÇÃO 8 – Sair 
    private static void opcao8_sair() {
        System.out.print("  Deseja realmente sair do sistema? (S/N): ");
        String resp = sc.nextLine().trim().toUpperCase();
        if (resp.equals("S")) {
            System.out.println("\n  Encerrando o sistema. Até logo!");
            System.exit(0);
        } else {
            System.out.println("  Operação cancelada. Retornando ao menu...");
        }
    }


    /** Lê um inteiro com tratamento de entrada inválida. */
    private static int lerInteiro(String msg) {
        while (true) {
            System.out.print(msg);
            String linha = sc.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("  *** Entrada inválida: informe um número inteiro. ***");
            }
        }
    }

    private static int lerInteiro(String msg, int minimo, boolean validar) {
        while (true) {
            int v = lerInteiro(msg);
            if (!validar || v >= minimo) return v;
            System.out.printf("  *** O valor deve ser maior ou igual a %d. ***%n", minimo);
        }
    }

    private static double lerDouble(String msg, double minimo, boolean validar) {
        while (true) {
            System.out.print(msg);
            String linha = sc.nextLine().trim().replace(',', '.');
            try {
                double v = Double.parseDouble(linha);
                if (!validar || v > minimo) return v;
                System.out.printf("  *** O valor deve ser maior que %.2f. ***%n", minimo);
            } catch (NumberFormatException e) {
                System.out.println("  *** Entrada inválida: informe um número real. ***");
            }
        }
    }

    /** Lê um caractere validado contra lista de opções. */
    private static char lerChar(String msg, char[] validos) {
        while (true) {
            System.out.print(msg);
            String linha = sc.nextLine().trim().toUpperCase();
            if (linha.length() == 1) {
                char c = linha.charAt(0);
                for (char v : validos) {
                    if (c == v) return c;
                }
            }
            System.out.print("  *** Opção inválida. Escolha entre: ");
            for (char v : validos) System.out.print(v + " ");
            System.out.println("***");
        }
    }
}