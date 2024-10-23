package geradores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class CPFGenerator {

	private static int counter = 100000000;

	public static String generateCPF(int opcaoFormatacao, String padraoFormatacao) throws ParseException {
		counter++;
		String baseCPF = String.format("%09d", counter);
		String CPF = baseCPF + calculateCheckDigits(baseCPF);

		if (opcaoFormatacao == 1) {
			return CPF;
		}

		if (opcaoFormatacao == 2) {
			return String.format("%s.%s.%s-%s", CPF.substring(0, 3), CPF.substring(3, 6), CPF.substring(6, 9),
					CPF.substring(9, 11));
		}
		
		MaskFormatter maskFormatter = new MaskFormatter(padraoFormatacao);
		maskFormatter.setValueContainsLiteralCharacters(false);
		return maskFormatter.valueToString(CPF);

	}

	private static String calculateCheckDigits(String baseCPF) {
		int firstDigit = calculateDigit(baseCPF, new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2 });
		int secondDigit = calculateDigit(baseCPF + firstDigit, new int[] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 });
		return "" + firstDigit + secondDigit;
	}

	private static int calculateDigit(String cpf, int[] weights) {
		int sum = 0;
		for (int i = 0; i < weights.length; i++) {
			sum += Character.getNumericValue(cpf.charAt(i)) * weights[i];
		}
		int remainder = sum % 11;
		return remainder < 2 ? 0 : 11 - remainder;
	}

	public CPFGenerator(int quantidadeCPF, int opcaoFormatacao, String padraoFormatacao) throws IOException, ParseException {

		String massaCPF = Paths.get("").toAbsolutePath().toString() + "\\massa_cpf_" + new Date().getTime() + ".csv";

		FileWriter fw = new FileWriter(massaCPF, true);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < quantidadeCPF; i++) {

			bw.write(generateCPF(opcaoFormatacao, padraoFormatacao) + "\n");
		}

		System.out.println();
		System.out.println("Massa de CPF gerada com sucesso: " + massaCPF);
		System.out.println();

		bw.close();
		fw.close();

	}
}
