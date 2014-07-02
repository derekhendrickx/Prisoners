package fr.univlille1.sci.main;

import fr.univlille1.sci.core.EcologicalEvolution;
import fr.univlille1.sci.data.CSV;
import fr.univlille1.sci.utils.Constants;
import fr.univlille1.sci.utils.Parameters;

public class Prisoners {

	public static void main(String[] args) {
		/* Parameters */
		Parameters param = Parameters.GetInstance();
		// Check if empty parameters
		if (param.GetParam("rounds").isEmpty()
				|| param.GetParam("evolutions").isEmpty()
				|| param.GetParam("strategies").isEmpty()
				|| param.GetParam("populationPerStrategies").isEmpty()
				|| param.GetParam("debug").isEmpty()
				|| param.GetParam("csvGeneration").isEmpty()
				|| param.GetParam("csvFileName").isEmpty()) {
			System.err.println("Missing values in configuration file.");
			System.exit(-1);
		}

		Constants.MAX_ROUND = convertStringToNumber(param.GetParam("rounds"));
		Constants.MAX_EVOLUTION = convertStringToNumber(param
				.GetParam("evolutions"));

		String[] typesAgents = param.GetParam("strategies").split(
				Constants.CONF_SEPARATOR);
		String[] nbAgentsTemp = param.GetParam("populationPerStrategies")
				.split(Constants.CONF_SEPARATOR);
		if (typesAgents.length != nbAgentsTemp.length) {
			System.err
					.println("Missing values for strategies and/or population per strategies in configuration file.");
			System.exit(-3);
		}

		for (int i = 0; i < typesAgents.length; i++) {
			strategyAvailable(typesAgents[i]);
		}

		Constants.DEBUG = Boolean.parseBoolean(param.GetParam("debug"));
		boolean csvGeneration = Boolean.parseBoolean(param
				.GetParam("csvGeneration"));

		String csvFileName = param.GetParam("csvFileName");

		int[] nbAgents = new int[nbAgentsTemp.length];
		for (int i = 0; i < nbAgentsTemp.length; i++) {
			nbAgents[i] = convertStringToNumber(nbAgentsTemp[i]);
		}

		long lStartTime = System.currentTimeMillis();

		/* Work */
		int[][] matrix = EcologicalEvolution.getInstance().processEvolution(
				typesAgents, nbAgents);

		if (csvGeneration) {
			/* Tournament */
			int[][] scoresTournament = EcologicalEvolution.getInstance()
					.get_scoresTournois();
			String[][] tournament = new String[scoresTournament.length + 1][scoresTournament[0].length + 1];

			// Lignes en-tête (nom stratégie)
			tournament[0][0] = "";
			for (int i = 0; i < scoresTournament.length; i++) {
				tournament[0][i + 1] = typesAgents[i];
			}

			// Colonne en-tête (nom stratégie)
			for (int i = 0; i < scoresTournament[0].length; i++) {
				tournament[i + 1][0] = typesAgents[i];
			}

			// Values
			for (int i = 0; i < scoresTournament.length; i++) {
				for (int j = 0; j < scoresTournament[0].length; j++) {
					tournament[i + 1][j + 1] = Integer
							.toString(scoresTournament[i][j]);
				}
			}

			CSV.GenerateCSV(csvFileName + "_tournament", tournament);

			/* Evolution */
			String[][] evolution = new String[matrix[0].length + 1][matrix.length + 1];
			// Lignes en-tête (numéro tour)
			evolution[0][0] = "";
			for (int i = 1; i < matrix.length + 1; i++) {
				evolution[0][i] = Integer.toString(i);
			}

			// Colonne en-tête (nom stratégie)
			for (int i = 0; i < matrix[0].length; i++) {
				evolution[i + 1][0] = typesAgents[i];
			}

			// Valeurs
			for (int i = 0; i < matrix[0].length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					evolution[i + 1][j + 1] = Integer.toString(matrix[j][i]);
				}
			}

			CSV.GenerateCSV(csvFileName + "_evolution", evolution);
		}

		long lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;

		if (Constants.DEBUG) {
			System.out.println("##### Execution time [ms] #####");
			System.out.println("Used Time: " + difference);
		}

		System.exit(0);
	}

	private static int convertStringToNumber(String number) {
		int result = 0;
		try {
			result = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			System.err.println("Incorrect values in configuration file. " + number + " is not a number.");
			System.exit(-2);
		}
		return result;
	}

	private static void strategyAvailable(String strategy) {
		try {
			Class.forName("fr.univlille1.sci.core.strategies." + strategy, false, Prisoners.class.getClassLoader());
		} catch (ClassNotFoundException e) {
			System.err.println("Incorrect values in configuration file. The strategy " + strategy + " doesn't exist.");
			System.exit(-4);
		}
	}
}
