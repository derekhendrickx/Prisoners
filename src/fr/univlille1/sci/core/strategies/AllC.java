package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class AllC implements Strategy {
	
	public AllC(){}

	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		return Decision.COOPERATE;
	}

}
