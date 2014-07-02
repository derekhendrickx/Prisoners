package fr.univlille1.sci.core;

import java.lang.reflect.Constructor;

public class StrategyMaker {

	public static Strategy instantiateStrategy(String strategyName) {
		try {
			Class<?> clazz = Class.forName("fr.univlille1.sci.core.strategies."
					+ strategyName);
			Constructor<?> ctor = clazz.getConstructors()[0];
			Strategy strategy = (Strategy) ctor.newInstance();
			return strategy;
		} catch (Exception ex) {
			return null;
		}
	}

}
