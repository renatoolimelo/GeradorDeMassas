package geradores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class CNPJGenerator {
	private static int counter = 10000000;

	public static String generateCNPJ(int opcaoFormatacao, String padraoFormatacao) throws ParseException {
		counter++;
		String baseCNPJ = String.format("%08d0001", counter);
		String CNPJ = baseCNPJ + calculateCheckDigits(baseCNPJ);

		if (opcaoFormatacao == 1) {
			return CNPJ;
		}

		if (opcaoFormatacao == 2) {
			return String.format("%s.%s.%s/%s-%s", CNPJ.substring(0, 2), CNPJ.substring(2, 5), CNPJ.substring(5, 8),
					CNPJ.substring(8, 12), CNPJ.substring(12, 14));
		}
		
		MaskFormatter maskFormatter = new MaskFormatter(padraoFormatacao);
		maskFormatter.setValueContainsLiteralCharacters(false);
		return maskFormatter.valueToString(CNPJ);
		
	}

	private static String calculateCheckDigits(String baseCNPJ) {
		int[] weights1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		int[] weights2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

		int firstDigit = calculateDigit(baseCNPJ, weights1);
		int secondDigit = calculateDigit(baseCNPJ + firstDigit, weights2);

		return "" + firstDigit + secondDigit;
	}

	private static int calculateDigit(String cnpj, int[] weights) {
		int sum = 0;
		for (int i = 0; i < weights.length; i++) {
			sum += Character.getNumericValue(cnpj.charAt(i)) * weights[i];
		}
		int remainder = sum % 11;
		return remainder < 2 ? 0 : 11 - remainder;
	}

	public CNPJGenerator(int quantidadeCNPJ, int opcaoFormatacao, String padraoFormatacao) throws IOException, ParseException {

		String massaCNPJ = Paths.get("").toAbsolutePath().toString() + "\\massa_cnpj_" + new Date().getTime() + ".csv";

		FileWriter fw = new FileWriter(massaCNPJ, true);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < quantidadeCNPJ; i++) {

			bw.write(generateCNPJ(opcaoFormatacao, padraoFormatacao) + "\n");
		}

		System.out.println();
		System.out.println("Massa de CNPJ gerada com sucesso: " + massaCNPJ);
		System.out.println();

		bw.close();
		fw.close();
	}
}
