package fr.univlille1.sci.core;

import java.util.List;

public class Tournament {
	
	public static int[][] contest(List<String> strategies) {
		int[][] scores = new int[strategies.size()][strategies.size()];
		
		for (int i = 0; i < scores.length; i++) {
			for (int j = 0; j < scores.length; j++) {
				scores[i][j] = 0;
			}
		}
		
		// Parcours lignes (i)
		for (int i = 0; i < strategies.size(); i++) {
			Strategy strategyA = StrategyMaker.instantiateStrategy(strategies.get(i));
			// Parcours colonnes (j)
			for (int j = i; j < strategies.size(); j++) {
				Strategy strategyB = StrategyMaker.instantiateStrategy(strategies.get(j));
				Match match = new Match(strategyA, strategyB);
				match.fight();
				scores[i][j] = match.getScoreA();
				scores[j][i] = match.getScoreB();
			}
		}
		
		return scores;
	}
	
	public static void matrix(int[][] scores, List<Strategy> strategies) {
		System.out.print("\t\t");
		for (int i = 0; i < strategies.size(); i++) {
			System.out.print(name(strategies.get(i)) + "\t");
		}
		System.out.println("Total");
		
		for (int i = 0; i < scores.length; i++) {
			System.out.print(name(strategies.get(i)) + "\t");
			int sum = 0;
			for (int j = 0; j < scores.length; j++) {
				System.out.print(scores[i][j] + "\t\t");
				sum += scores[i][j];
			}
			System.out.println(sum);
		}
	}
	
	public static String name(Strategy strat)
	{
		return strat.getClass().getSimpleName() + (strat.getClass().getSimpleName().length() > 7 ? "" : "\t");
	}
}
