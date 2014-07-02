package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class Tf2t implements Strategy {

	private boolean _doubleDefect = false;
	private Decision _veryLastDecision = Decision.NOTHING;
	
	public Tf2t(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		_doubleDefect = (_veryLastDecision == Decision.DEFECT && lastOpponentDecision == Decision.DEFECT);
		_veryLastDecision = lastOpponentDecision;
		return (_doubleDefect ? Decision.DEFECT : Decision.COOPERATE);
	}

}
