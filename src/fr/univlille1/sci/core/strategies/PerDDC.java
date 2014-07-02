package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class PerDDC implements Strategy {

	public PerDDC(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		return (round % 3 == 2 ? Decision.COOPERATE : Decision.DEFECT);
	}

}
