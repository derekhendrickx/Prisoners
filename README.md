# Prisoners
## Philosophie
Un tournoi est organisé pour opposer chaque stratégie indiquée dans le fichier de configuration. Lors d'un tournoi on ne calcule que la demi-matrice.
L'évolution se sert en suite des résultats de ce tournoir pour calculer une nouvele population à chaque tour.
Chaque stratégie implémente une interface et possède une méthode qui renvoi une décision appropriée sachant un contexte particulier.

## Paramètres
On peut paramétrer le programme à l'aide du fichier de configuration prisoners.conf

Les fichiers de configuration qui ont permis de générer les résultats pour le tournoi et l'évolution écologique demandé sont également enregistrés dans l'archive (prisoners.conf_tournament et prisoners.conf_evolution).

rounds : le nombre de round d'un tournoi
evolutions : le nombre de tour d'évolution
strategies : les stratégies qui s'affrontent (cf. strategies.txt pour toutes les stratégies disponibles)
populationPerStrategies : la population initiale par stratégie
debug : affiche les messages de debug
csvGeneration : génère un fichier CSV pour la matrice du tournoi ainsi que l'évolution
csvFileName : nom du fichier CSV

### Exemple
rounds=1000
evolutions=1000
strategies=AllC,AllD,PerCCD,TFT
populationPerStrategies=100,100,100,100
debug=true
csvGeneration=true
csvFileName=prisoners

## Compilation
javac -d bin -sourcepath src src/fr/univlille1/sci/main/Prisoners.java
javac -d bin -sourcepath src src/fr/univlille1/sci/core/strategies/*.java

## Exécution
java -cp bin fr.univlille1.sci.main.Prisoners

## Jar file
### Création du jar
jar cfe Prisoners.jar fr.univlille1.sci.main.Prisoners -C bin/ .

### Exécution du jar
java -jar Prisoners.jar
