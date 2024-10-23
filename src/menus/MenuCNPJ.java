package menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import geradores.CNPJGenerator;

public class MenuCNPJ {

	private int quantidadeCNPJ;
	private int opcaoFormatacao;
	private String padraoFormatacao;
	private boolean menu = true;

	public MenuCNPJ(Scanner sc) {
		menuCPF(sc);
	}

	public void menuCPF(Scanner sc) {

		while (menu) {
			try {
				System.out.println();
				System.out.print("Informe a quantidade de CNPJ que deseja gerar: ");
				quantidadeCNPJ = sc.nextInt();

				if (quantidadeCNPJ < 0) {
					System.out.println();
					System.out.println("Informe um valor maior que 0!");
					System.out.println();
					continue;
				}

				if (quantidadeCNPJ == 0) {
					break;
				}

				System.out.println();
				System.out.println("Escolhe um padrão de formatação para a massa de CNPJ:");
				System.out.println("Opção 1 - '12345678000101'");
				System.out.println("Opção 2 - '12.345.678/0001-01'");
				System.out.println("Opção 3 - Definir um padrão");
				System.out.println();
				System.out.print("Informe a opção: ");
				opcaoFormatacao = sc.nextInt();

				if (opcaoFormatacao < 1 || opcaoFormatacao > 3) {
					System.out.println();
					System.out.println("Opção de formatação inválida");
					System.out.println();
					continue;
				}
				
				if (opcaoFormatacao == 3) {
					System.out.println();
					System.out.println("Defina um exemplo de formatação conforme exemplo abaixo:");
					System.out.println("12.345.678/0001-01 -> digitar ##.###.###/####-## (não colocar espaços)");
					System.out.println();
					System.out.print("Digite o padrão: ");
					padraoFormatacao = sc.next();
				}

				new CNPJGenerator(quantidadeCNPJ, opcaoFormatacao, padraoFormatacao);

				menu = false;

			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Valor inválido!");
				System.out.println();
				sc.next();

			} catch (Exception e) {
				System.out.println();
				System.out.println("Erro: " + e.getMessage());
				System.out.println();
				sc.next();
			}

		}

	}

}