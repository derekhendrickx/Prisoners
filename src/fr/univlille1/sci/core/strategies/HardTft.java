package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class HardTft implements Strategy {

	private boolean _defect = false;
	private Decision _veryLastDecision = Decision.NOTHING;
	
	public HardTft(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		_defect = (_veryLastDecision == Decision.DEFECT || lastOpponentDecision == Decision.DEFECT);
		_veryLastDecision = lastOpponentDecision;
		return (_defect ? Decision.DEFECT : Decision.COOPERATE);
	}

}
