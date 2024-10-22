package menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import geradores.PhoneGenerator;

public class MenuPhone {

	private int quantidadeTelefones;
	private int opcaoFormatacao;
	private boolean menu = true;

	public MenuPhone(Scanner sc) {
		menuCPF(sc);
	}

	public void menuCPF(Scanner sc) {

		while (menu) {
			try {
				System.out.println();
				System.out.print("Informe a quantidade de telefones que deseja gerar: ");
				quantidadeTelefones = sc.nextInt();

				if (quantidadeTelefones < 0) {
					System.out.println();
					System.out.println("Informe um valor maior que 0!");
					System.out.println();
					continue;
				}

				if (quantidadeTelefones == 0) {
					break;
				}

				System.out.println();
				System.out.println("Escolha um padrão de formatação para a massa de telefones:");
				System.out.println("Opção 1 - '11912345678'");
				System.out.println("Opção 2 - '(11)91234-5678'");
				System.out.println();
				System.out.print("Informe a opção: ");
				opcaoFormatacao = sc.nextInt();

				if (opcaoFormatacao < 1 || opcaoFormatacao > 2) {
					System.out.println();
					System.out.println("Opção de formatação inválida");
					System.out.println();
					continue;
				}

				new PhoneGenerator(quantidadeTelefones, opcaoFormatacao);

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