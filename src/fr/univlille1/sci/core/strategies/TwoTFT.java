package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class TwoTFT implements Strategy {

	private Decision _decision;
	private int _stillAngry = 0;
	
	public TwoTFT(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore) {
		if(lastOpponentDecision == Decision.DEFECT)
		{
			_decision = Decision.DEFECT;
			_stillAngry = 1;
		}
		else if(lastOpponentDecision == Decision.COOPERATE && _stillAngry > 0)
		{
			_decision = Decision.DEFECT;
			_stillAngry--;
		}
		else
			_decision = Decision.COOPERATE;
		
		return (round == 0 ? Decision.COOPERATE : _decision);
	}

}
