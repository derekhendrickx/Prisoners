package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class EasyGo implements Strategy {

	private boolean _easygo = false;
	
	public EasyGo(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		if(!_easygo && lastOpponentDecision == Decision.COOPERATE){ _easygo = true; }
		return (_easygo ? Decision.COOPERATE : Decision.DEFECT);
	}

}
