package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class PerCCD implements Strategy {

	public PerCCD(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		return (round % 3 == 2 ? Decision.DEFECT : Decision.COOPERATE);
	}

}
