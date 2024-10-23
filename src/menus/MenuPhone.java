package menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import geradores.PhoneGenerator;

public class MenuPhone {

	private int quantidadeTelefones;
	private int opcaoFormatacao;
	private String padraoFormatacao;
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
					System.out.println("(11) 91234 - 5678 -> digitar (##)#####-#### (não colocar espaços)");
					System.out.println();
					System.out.print("Digite o padrão: ");
					padraoFormatacao = sc.next();
				}

				new PhoneGenerator(quantidadeTelefones, opcaoFormatacao, padraoFormatacao);

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