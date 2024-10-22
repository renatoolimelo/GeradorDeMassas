package menus;

import java.util.Scanner;

public class MenuGeradorDeMassa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String opcao;
		boolean menu = true;

		while (menu) {
			System.out.println("----------Gerador de massas----------");
			System.out.println();
			System.out.println("Escolha uma das massas para serem geradas:");
			System.out.println("1 - CPF");
			System.out.println("2 - CNPJ");
			System.out.println("3 - Telefone");
			System.out.println("0 - Exit");
			System.out.println();
			System.out.print("Informe a opção: ");
			opcao = sc.next();

			switch (opcao) {
			case "1":
				new MenuCPF(sc);
				break;
			case "2":
				new MenuCNPJ(sc);
				break;
			case "3":
				new MenuPhone(sc);
				break;
			case "0":
				System.out.println("Fim da execução");
				menu = false;
				break;
			default:
				System.out.println();
				System.out.println("Opção inválida");
				System.out.println();
				break;
			}
		}

		sc.close();

	}

}
