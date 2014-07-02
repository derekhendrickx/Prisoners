all:
	javac -d bin -sourcepath src src/fr/univlille1/sci/main/Prisoners.java
	javac -d bin -sourcepath src src/fr/univlille1/sci/core/strategies/*.java
	jar cfe Prisoners.jar fr.univlille1.sci.main.Prisoners -C bin/ .