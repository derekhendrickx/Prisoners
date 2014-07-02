package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class Mistrust implements Strategy {

	public Mistrust(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		return (round == 0 ? Decision.DEFECT : lastOpponentDecision);
	}

}
