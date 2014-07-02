package fr.univlille1.sci.core.strategies;

import fr.univlille1.sci.core.Decision;
import fr.univlille1.sci.core.Strategy;

public class Prober implements Strategy {

	public boolean _alld = true;
	
	public Prober(){}
	
	@Override
	public Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore){
		if(round == 0) return Decision.DEFECT;
		else if(round == 1 || round == 2)
		{
			if(lastOpponentDecision == Decision.DEFECT){ _alld = false; }
			return Decision.COOPERATE;
		}
		else 
			return (_alld ? Decision.DEFECT : lastOpponentDecision);
	}

}
