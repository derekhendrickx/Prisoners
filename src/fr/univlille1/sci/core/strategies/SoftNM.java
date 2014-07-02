package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class SoftNM implements Strategy {

	public SoftNM(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		return (opponentScore > myScore ? Decision.DEFECT : Decision.COOPERATE);
	}

}
