package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class HardTf2t implements Strategy {

	private boolean _doubleDefect = false;
	private Decision _veryLastDecision = Decision.NOTHING;
	private Decision _veryVeryLastDecision = Decision.NOTHING;
	
	public HardTf2t(){}
	
	@Override	
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		_doubleDefect = (_veryVeryLastDecision == Decision.DEFECT && _veryLastDecision == Decision.DEFECT) || (_veryLastDecision == Decision.DEFECT && lastOpponentDecision == Decision.DEFECT);
		_veryVeryLastDecision = _veryLastDecision;
		_veryLastDecision = lastOpponentDecision;		
		return (_doubleDefect ? Decision.DEFECT : Decision.COOPERATE);
	}

}
