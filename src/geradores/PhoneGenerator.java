package geradores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

public class PhoneGenerator {

	private static long basePhone = 11900000000L;

	public static String generatePhone(int opcaoFormatacao) {
		basePhone++;
		String phone = Long.toString(basePhone);

		if (opcaoFormatacao == 1) {
			return phone;
		}

		return String.format("(%s)%s-%s", phone.substring(0, 2), phone.substring(2, 7), phone.substring(7, 11));
	}

	public PhoneGenerator(int quantidadeTelefones, int opcaoFormatacao) throws IOException {

		String massaPhone = Paths.get("").toAbsolutePath().toString() + "\\massa_phone_" + new Date().getTime()
				+ ".csv";

		FileWriter fw = new FileWriter(massaPhone, true);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < quantidadeTelefones; i++) {

			bw.write(generatePhone(opcaoFormatacao) + "\n");
		}

		System.out.println();
		System.out.println("Massa de telefone gerada com sucesso: " + massaPhone);
		System.out.println();

		bw.close();
		fw.close();
	}
}
