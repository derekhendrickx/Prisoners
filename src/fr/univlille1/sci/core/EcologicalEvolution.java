package fr.univlille1.sci.core;

import java.util.ArrayList;
import java.util.List;

import fr.univlille1.sci.utils.Constants;

public class EcologicalEvolution {

	private String[] _typesAgents;
	private int[] _nbAgents;
	private int _nbAgentsTotal;
	private long[] _scoresParType;
	private int[][] _scoresTournois;
	private List<String> _population = new ArrayList<String>();

	private EcologicalEvolution() {
	}

	private static EcologicalEvolution _INSTANCE;

	public static EcologicalEvolution getInstance() {
		if (_INSTANCE == null) {
			_INSTANCE = new EcologicalEvolution();
		}

		return _INSTANCE;
	}

	public int[][] get_scoresTournois() {
		return _scoresTournois;
	}

	public void init(String[] typesAgents, int[] nbAgentsParType) {
		_typesAgents = typesAgents;
		_nbAgents = nbAgentsParType;
		_nbAgentsTotal = 0;

		for (int nb : _nbAgents)
			_nbAgentsTotal += nb;
		_scoresParType = new long[_nbAgents.length];

		// On effectue le tournois pour savoir le score du combat entre chaque
		// stratégies
		_population.clear();
		for (String strat : _typesAgents)
			_population.add(strat);

		// Le tournois n'a besoin d'etre effectué qu'une seule fois car un match
		// entre deux strategies donnera toujours le meme résultat, il n'y a que
		// les coefficients qui changent par la suite
		_scoresTournois = Tournament.contest(_population);
	}

	/***
	 * Calcule la nouvelle population compte tenu du résultat du tournois
	 * 
	 * @return la nouvelle répartition de la population entre les stratégies
	 */
	public int[] evolve() {
		/*
		 * Méthode abandonnée car trop de calculs redondants et du coup
		 * quasiment 2secondes de calcul pour un tour d'evolution ...
		 * 
		 * 
		 * //Vide la liste des agents _population.clear();
		 * 
		 * //Remplie la liste des agents en fonction de la population pour
		 * chaque stratégie for(int i=0; i<_typesAgents.length; i++) { for(int
		 * j=0; j<_nbAgents[i]; j++) {
		 * _population.add(StrategyMaker.instantiateStrategy(_typesAgents[i]));
		 * } }
		 * 
		 * //Calcul du résultat du tournois et récupération des scores int
		 * matScores[][] = Tournament.contest(_population); int scores[] = new
		 * int[matScores.length];
		 * 
		 * for(int i=0; i<matScores.length; i++) { scores[i] = 0; for(int j=0;
		 * j<matScores[i].length; j++) { scores[i] += matScores[i][j]; } }
		 * 
		 * //Calcul du score de chaque Stratégie int l=0; int total=0; for(int
		 * i=0; i<_typesAgents.length; i++) { _scores[i] = 0; for(int j=0;
		 * j<_nbAgents[i]; j++) { _scores[i] += scores[l]; total += scores[l];
		 * l++; } }
		 * 
		 * //Recalcul de la population for(int i=0; i<_nbAgents.length; i++)
		 * _nbAgents[i] = Math.round(_scores[i] / (float)total *
		 * _nbAgentsTotal);
		 */

		// Calcul des scores pour chaque type de strategy dans population donnée
		long scoreTotal = 0;
		for (int i = 0; i < _scoresParType.length; i++) {
			_scoresParType[i] = 0;

			// Calcul du score d'un agent de type X dans la configuration de
			// population donnée
			for (int j = 0; j < _typesAgents.length; j++)
				_scoresParType[i] += _scoresTournois[i][j] * (i==j ? _nbAgents[j] - 1 : _nbAgents[j]);

			// Calcul du score total du type X dans la population donnée
			_scoresParType[i] = _scoresParType[i] * _nbAgents[i];
			scoreTotal += _scoresParType[i];
		}

		// Recalcul de la population
		for (int i = 0; i < _nbAgents.length; i++)
			_nbAgents[i] = (int) Math.floor(_scoresParType[i]
					/ (float) scoreTotal * _nbAgentsTotal);

		return _nbAgents;
	}
	
	public int[][] processEvolution(String[] typesAgents, int[] nbAgents) {
		init(typesAgents, nbAgents);
		int[][] result = new int[1000][_typesAgents.length];
		
		int[] nbAgentsOld = nbAgents.clone();
		int i = 0;
		while (i < (Constants.MAX_ROUND - 1) && (diff(nbAgentsOld, nbAgents) || i == 0)) {
			result[i] = nbAgents.clone();
			nbAgentsOld = nbAgents.clone();
			nbAgents = EcologicalEvolution.getInstance().evolve();
			i++;
		}
		result[i] = nbAgents.clone();
		return result;
	}
	
	private boolean diff(int[] nbAgentsOld, int[] nbAgents) {
		for (int i = 0; i < nbAgentsOld.length; i++) {
			if (nbAgentsOld[i] != nbAgents[i]) {
				return true;
			}
		}
		return false;
	}

}
