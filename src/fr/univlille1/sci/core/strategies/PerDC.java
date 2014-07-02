package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class PerDC implements Strategy {

	public PerDC(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		return (round % 2 == 0 ? Decision.DEFECT : Decision.COOPERATE);
	}

}
