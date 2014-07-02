package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class Pavlov implements Strategy {

	private Decision _decision;
	
	public Pavlov(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore,	int opponentScore) {
		_decision = (round == 0 ? Decision.COOPERATE : (_decision == lastOpponentDecision ? Decision.COOPERATE : Decision.DEFECT));
		return _decision;
	}

}
