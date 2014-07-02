package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class SlowTft implements Strategy {

	private boolean _cooperate;
	private Decision _veryLastDecision = Decision.NOTHING;
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		_cooperate = (round == 0) || (round == 1) || (_veryLastDecision != Decision.DEFECT && lastOpponentDecision != Decision.DEFECT);
		_veryLastDecision = lastOpponentDecision;
		return (_cooperate ? Decision.COOPERATE : Decision.DEFECT);
	}

}
