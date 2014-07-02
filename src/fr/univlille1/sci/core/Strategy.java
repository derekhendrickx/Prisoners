package fr.univlille1.sci.core;

public interface Strategy {
	
	Decision play(int round, Decision lastOpponentDecision, int myScore, int opponentScore);

}
