package geradores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class PhoneGenerator {

	private static long basePhone = 11900000000L;

	public static String generatePhone(int opcaoFormatacao, String padraoFormatacao) throws ParseException {
		basePhone++;
		String phone = Long.toString(basePhone);

		if (opcaoFormatacao == 1) {
			return phone;
		}

		if (opcaoFormatacao == 2) {
			return String.format("(%s)%s-%s", phone.substring(0, 2), phone.substring(2, 7), phone.substring(7, 11));
		}
		
		MaskFormatter maskFormatter = new MaskFormatter(padraoFormatacao);
		maskFormatter.setValueContainsLiteralCharacters(false);
		return maskFormatter.valueToString(phone);
	}

	public PhoneGenerator(int quantidadeTelefones, int opcaoFormatacao, String padraoFormatacao) throws IOException, ParseException {

		String massaPhone = Paths.get("").toAbsolutePath().toString() + "\\massa_phone_" + new Date().getTime()
				+ ".csv";

		FileWriter fw = new FileWriter(massaPhone, true);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < quantidadeTelefones; i++) {

			bw.write(generatePhone(opcaoFormatacao, padraoFormatacao) + "\n");
		}

		System.out.println();
		System.out.println("Massa de telefone gerada com sucesso: " + massaPhone);
		System.out.println();

		bw.close();
		fw.close();
	}
}
