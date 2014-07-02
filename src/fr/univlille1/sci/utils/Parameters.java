package fr.univlille1.sci.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Parameters {

	private Parameters() {
		InitParams();
		if (!_enabled)
			ProvideParams();
	}

	private Properties _params = new Properties();
	private boolean _enabled = false;

	private void InitParams() {
		try {
			FileInputStream fis = new FileInputStream(
					Constants.CONFIGURATION_FILE_NAME);
			_params.load(fis);
			fis.close();

			// if ok
			_enabled = true;

		} catch (IOException e) {
			System.out
					.println("Erreur de chargement du fichier de configuration");
		}
	}

	public boolean IsEnabled() {
		return _enabled;
	}

	public String GetParam(String paramName) {
		if (!_enabled)
			return "";

		return _params.getProperty(paramName);
	}

	/***
	 * Provide parameters if the file can't be loaded
	 */
	private void ProvideParams() {
		// Remplir ici a la main pour pouvoir tester en execution sans JAR
		_params.put("rounds", "1000");
		_params.put("evolutions", "1000");
		_params.put("strategies", "AllC,AllD,PerCCD,TFT");
		_params.put("populationPerStrategies", "100,100,100,100");
		_params.put("debug", "true");
		_params.put("csvGeneration", "true");
		_params.put("csvFileName", "prisoners");

		_enabled = true;
	}

	private static Parameters _INSTANCE;

	public static Parameters GetInstance() {
		if (_INSTANCE == null)
			_INSTANCE = new Parameters();
		return _INSTANCE;
	}
}
