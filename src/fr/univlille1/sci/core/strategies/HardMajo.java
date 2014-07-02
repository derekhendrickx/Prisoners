package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class HardMajo implements Strategy {

	private int _defectCount = 0;
	private int _cooperateCount = 0;
	
	public HardMajo(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		if(lastOpponentDecision == Decision.DEFECT)
			_defectCount++;
		else if(lastOpponentDecision == Decision.COOPERATE)
			_cooperateCount++;
		
		return (_defectCount == _cooperateCount ? Decision.DEFECT : (_defectCount > _cooperateCount ? Decision.DEFECT : Decision.COOPERATE));
	}

}
