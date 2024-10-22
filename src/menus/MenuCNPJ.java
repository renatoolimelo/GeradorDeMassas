package menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import geradores.CNPJGenerator;

public class MenuCNPJ {

	private int quantidadeCNPJ;
	private int opcaoFormatacao;
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
				System.out.println();
				System.out.print("Informe a opção: ");
				opcaoFormatacao = sc.nextInt();

				if (opcaoFormatacao < 1 || opcaoFormatacao > 2) {
					System.out.println();
					System.out.println("Opção de formatação inválida");
					System.out.println();
					continue;
				}

				new CNPJGenerator(quantidadeCNPJ, opcaoFormatacao);

				menu = false;

			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("valor inválido!");
				System.out.println();
				sc.next();

			} catch (Exception e) {
				System.out.println();
				System.out.println("valor inválido!");
				System.out.println();
				sc.next();
			}

		}

	}

}