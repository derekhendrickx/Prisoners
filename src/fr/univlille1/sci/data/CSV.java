package fr.univlille1.sci.data;

import java.io.FileWriter;
import java.io.IOException;

import fr.univlille1.sci.utils.Constants;

public class CSV {

	public static boolean GenerateCSV(String fileName, String[][] matrix) {
		try {
			// OUVERTURE
			FileWriter writer = new FileWriter(fileName + ".csv");

			// ECRITURE
			for (String[] line : matrix) {
				for (String value : line) {
					writer.append(value);
					writer.append(Constants.CSV_SEPARATOR);
				}
				writer.append('\n');
			}

			// FERMETURE
			writer.flush();
			writer.close();

			return true;
		} catch (IOException e) {
			System.out.println("Erreur pendant la création du fichier CSV");
			return false;
		}
	}

}
