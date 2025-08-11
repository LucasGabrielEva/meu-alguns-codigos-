package org.example;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funcionario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> funcionarios = new ArrayList<>();
        List<String> chegada = new ArrayList<>();
        List<String> saida = new ArrayList<>();

        // ===== CADASTRO DE FUNCIONÁRIOS =====
        System.out.println("=== CADASTRO DE FUNCIONÁRIOS ===");
        while (true) {
            System.out.print("Digite o nome do funcionário (ou ENTER para encerrar): ");
            String nome = sc.nextLine().trim();
            if (nome.isEmpty()) break;
            funcionarios.add(nome);
            chegada.add(null); // Ainda sem registro
            saida.add(null);   // Ainda sem registro
        }

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado. Encerrando...");
            return;
        }

        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        // ===== MENU PRINCIPAL =====
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Registrar Chegada");
            System.out.println("2 - Registrar Saída");
            System.out.println("3 - Mostrar Relatório");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            String opcao = sc.nextLine().trim();

            switch (opcao) {
                case "1": // Registrar chegada
                    mostrarFuncionarios(funcionarios);
                    System.out.print("Digite o número do funcionário: ");
                    int idxChegada = lerIndice(sc, funcionarios.size());
                    if (idxChegada == -1) break;
                    if (chegada.get(idxChegada) != null) {
                        System.out.println("⚠ " + funcionarios.get(idxChegada) +
                                " já registrou chegada às " + chegada.get(idxChegada));
                    } else {
                        String hora = LocalTime.now().format(formatoHora);
                        chegada.set(idxChegada, hora);
                        System.out.println("✅ Chegada registrada: " +
                                funcionarios.get(idxChegada) + " às " + hora);
                    }
                    break;

                case "2": // Registrar saída
                    mostrarFuncionarios(funcionarios);
                    System.out.print("Digite o número do funcionário: ");
                    int idxSaida = lerIndice(sc, funcionarios.size());
                    if (idxSaida == -1) break;
                    if (chegada.get(idxSaida) == null) {
                        System.out.println("⚠ " + funcionarios.get(idxSaida) + " ainda não registrou chegada.");
                    } else if (saida.get(idxSaida) != null) {
                        System.out.println("⚠ " + funcionarios.get(idxSaida) +
                                " já registrou saída às " + saida.get(idxSaida));
                    } else {
                        String hora = LocalTime.now().format(formatoHora);
                        saida.set(idxSaida, hora);
                        System.out.println("✅ Saída registrada: " +
                                funcionarios.get(idxSaida) + " às " + hora);
                    }
                    break;

                case "3": // Relatório
                    System.out.println("\n📄 RELATÓRIO DO DIA:");
                    for (int i = 0; i < funcionarios.size(); i++) {
                        String horaChegada = (chegada.get(i) != null) ? chegada.get(i) : "—";
                        String horaSaida = (saida.get(i) != null) ? saida.get(i) : "—";
                        System.out.println(funcionarios.get(i) +
                                " | Chegada: " + horaChegada +
                                " | Saída: " + horaSaida);
                    }
                    break;

                case "0": // Sair
                    System.out.println("Encerrando programa...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Função para mostrar funcionários
    private static void mostrarFuncionarios(List<String> funcionarios) {
        System.out.println("\nFuncionários:");
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println((i + 1) + " - " + funcionarios.get(i));
        }
    }

    // Função para ler e validar o índice
    private static int lerIndice(Scanner sc, int total) {
        try {
            int numero = Integer.parseInt(sc.nextLine().trim());
            if (numero < 1 || numero > total) {
                System.out.println("Número inválido.");
                return -1;
            }
            return numero - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return -1;
        }
    }
}



