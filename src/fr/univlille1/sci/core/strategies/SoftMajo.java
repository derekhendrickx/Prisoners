package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class SoftMajo implements Strategy {

	private int _defectCount = 0;
	private int _cooperateCount = 0;
	
	public SoftMajo(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		if(lastOpponentDecision == Decision.DEFECT)
			_defectCount++;
		else if(lastOpponentDecision == Decision.COOPERATE)
			_cooperateCount++;
		
		return (_defectCount == _cooperateCount ? Decision.COOPERATE : (_defectCount > _cooperateCount ? Decision.DEFECT : Decision.COOPERATE));
	}

}
