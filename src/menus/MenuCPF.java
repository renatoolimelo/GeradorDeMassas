package menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import geradores.CPFGenerator;

public class MenuCPF {

	private int quantidadeCPF;
	private int opcaoFormatacao;
	private boolean menu = true;

	public MenuCPF(Scanner sc) {
		menuCPF(sc);
	}

	public void menuCPF(Scanner sc) {

		while (menu) {
			try {
				System.out.println();
				System.out.print("Informe a quantidade de CPF que deseja gerar: ");
				quantidadeCPF = sc.nextInt();

				if (quantidadeCPF < 0) {
					System.out.println();
					System.out.println("Informe um valor maior que 0!");
					System.out.println();
					continue;
				}

				if (quantidadeCPF == 0) {
					break;
				}

				System.out.println();
				System.out.println("Escolha um padrão de formatação para a massa de CPF:");
				System.out.println("Opção 1 - '12345678901'");
				System.out.println("Opção 2 - '123.456.789-01'");
				System.out.println();
				System.out.print("Informe a opção: ");
				opcaoFormatacao = sc.nextInt();

				if (opcaoFormatacao < 1 || opcaoFormatacao > 2) {
					System.out.println();
					System.out.println("Opção de formatação inválida");
					System.out.println();
					continue;
				}

				new CPFGenerator(quantidadeCPF, opcaoFormatacao);

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