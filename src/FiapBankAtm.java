import java.util.Scanner;

public class FiapBankAtm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("   Bem-vindo ao FIAP Bank - Terminal ATM");
        System.out.println("==============================================");
        System.out.print("Por favor, informe seu nome completo: ");
        String nomeCompleto = scanner.nextLine().trim();

        int indicePrimeiroEspaco = nomeCompleto.indexOf(" ");
        String primeiroNome;
        if (indicePrimeiroEspaco == -1) {
            primeiroNome = nomeCompleto;
        } else {
            primeiroNome = nomeCompleto.substring(0, indicePrimeiroEspaco);
        }

        System.out.println("\nOlá, " + primeiroNome + "! Vamos cadastrar sua senha de acesso.");

        String regexSenhaForte = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_+=?><]).{8,}$";
        String senhaCadastrada = "";
        boolean senhaValida = false;

        while (!senhaValida) {
            System.out.println("\nA senha deve conter:");
            System.out.println("  - No mínimo 8 caracteres");
            System.out.println("  - Ao menos um número");
            System.out.println("  - Ao menos uma letra maiúscula");
            System.out.println("  - Ao menos um caractere especial: !@#$%^&*()-_+=?><");
            System.out.print("Digite sua nova senha: ");
            String senhaDigitada = scanner.nextLine();

            if (senhaDigitada.matches(regexSenhaForte)) {
                senhaValida = true;
                senhaCadastrada = senhaDigitada;
                System.out.println("✔ Senha cadastrada com sucesso!");
            } else {
                System.out.println("✘ Senha fraca! Por favor, tente novamente.");
            }
        }

        System.out.println("\n----------------------------------------------");
        System.out.println("  Autenticação - Terminal FIAP Bank");
        System.out.println("----------------------------------------------");

        int tentativasRestantes = 3;
        boolean autenticado = false;

        while (tentativasRestantes > 0 && !autenticado) {
            System.out.print("Digite sua senha para acessar: ");
            String senhaLogin = scanner.nextLine();

            if (senhaLogin.equals(senhaCadastrada)) {
                autenticado = true;
                System.out.println("✔ Acesso autorizado! Bem-vindo, " + primeiroNome + ".");
            } else {
                tentativasRestantes--;
                if (tentativasRestantes > 0) {
                    System.out.println("✘ Senha incorreta! Tentativas restantes: " + tentativasRestantes);
                }
            }
        }

        if (!autenticado) {
            System.out.println("\n!!! ACESSO BLOQUEADO !!!");
            System.out.println("Número máximo de tentativas atingido. Contate o suporte.");
            scanner.close();
            return;
        }


        double saldo = 0.00;
        int opcaoMenu = 0;

        while (opcaoMenu != 4) {
            System.out.println("\n==============================================");
            System.out.println("   FIAP Bank - Menu Principal");
            System.out.println("==============================================");
            System.out.printf("   Saldo disponível: R$ %.2f%n", saldo);
            System.out.println("----------------------------------------------");
            System.out.println("   [ 1 ] Consultar Saldo");
            System.out.println("   [ 2 ] Fazer Depósito");
            System.out.println("   [ 3 ] Fazer Saque");
            System.out.println("   [ 4 ] Sair");
            System.out.println("----------------------------------------------");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcaoMenu = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("✘ Entrada inválida! Por favor, digite um número entre 1 e 4.");
                scanner.nextLine();
                continue;
            }

            switch (opcaoMenu) {

                case 1:
                    System.out.println("\n--- Consulta de Saldo ---");
                    System.out.printf("Seu saldo atual é: R$ %.2f%n", saldo);
                    break;

                case 2:
                    System.out.println("\n--- Depósito ---");
                    System.out.print("Informe o valor a depositar: R$ ");

                    if (scanner.hasNextDouble()) {
                        double valorDeposito = scanner.nextDouble();
                        scanner.nextLine();

                        if (valorDeposito <= 0) {
                            System.out.println("✘ Valor inválido! O depósito deve ser maior que R$ 0,00.");
                        } else {
                            saldo += valorDeposito;
                            System.out.printf("✔ Depósito de R$ %.2f realizado com sucesso!%n", valorDeposito);
                            System.out.printf("   Novo saldo: R$ %.2f%n", saldo);
                        }
                    } else {
                        System.out.println("✘ Valor inválido! Por favor, informe um número válido.");
                        scanner.nextLine();
                    }
                    break;

                case 3:
                    System.out.println("\n--- Saque ---");
                    System.out.print("Informe o valor a sacar: R$ ");

                    if (scanner.hasNextDouble()) {
                        double valorSaque = scanner.nextDouble();
                        scanner.nextLine();

                        if (valorSaque <= 0) {
                            System.out.println("✘ Valor inválido! O saque deve ser maior que R$ 0,00.");
                        } else if (valorSaque > saldo) {
                            System.out.println("✘ Saldo insuficiente!");
                            System.out.printf("   Saldo disponível: R$ %.2f%n", saldo);
                        } else {
                            saldo -= valorSaque;
                            System.out.printf("✔ Saque de R$ %.2f realizado com sucesso!%n", valorSaque);
                            System.out.printf("   Novo saldo: R$ %.2f%n", saldo);
                        }
                    } else {
                        System.out.println("✘ Valor inválido! Por favor, informe um número válido.");
                        scanner.nextLine();
                    }
                    break;

                case 4:
                    System.out.println("\n==============================================");
                    System.out.println("  O FIAP Bank agradece sua preferência!");
                    System.out.println("  Até logo, " + primeiroNome + "!");
                    System.out.println("==============================================");
                    break;

                default:
                    System.out.println("✘ Opção inválida! Escolha entre 1 e 4.");
                    break;
            }
        }

        scanner.close();
    }
}