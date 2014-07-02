package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class Spiteful implements Strategy {

	private boolean _spiteful = false;

	public Spiteful() {
	}

	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore,
			int opponentScore) {
		if (!_spiteful && lastOpponentDecision == Decision.DEFECT) {
			_spiteful = true;
		}
		return (_spiteful ? Decision.DEFECT : Decision.COOPERATE);
	}

}
