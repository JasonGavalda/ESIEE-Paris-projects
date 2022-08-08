Groupe : Jason GAVALDA

Ce qui a été fait : 
 - Tout jusqu'au support des chaînes de caractères est fonctionnel dans le parser (petit souci avec le ifThenElse détaillé dans la section difficultés).
 - L'AST contient tous les codes java pour le reste du projet, j'ai malheureusement eu trop de difficultés avec le .jj (et notamment la mise en place des scopes) pour pouvoir vérifier leur fonctionnement.

On a donc :

	- Tarball
	- Makefile
	- Make Check
	- Entiers avec leurs opérations
	- Print
	- If-Then-Else (petit souci avec les if imbriqués, mais les opérations en leur sein fonctionnent et sont prioritaires).
	- Chaînes de caractères (Concaténation et Comparaisons)

	Tout ce qui est ci-dessus est pleinement fonctionnel

	Ce qui suit est implémenté dans l'AST, et les tentatives de les mettre en place sur le .jj sont présentes, mais hélas non fonctionnelles.

	- Variables et Scopes (gros blocage fatal pour la suite)
	- Affectation (impossible à vérifier du fait du problème précédent)
	- While et For (pour les mêmes raisons)

Difficultés :

L'implémentation de la grammaire des scopes a posé un gros problème.
Je pense que ce que j'ai codé en java est bon à ce sujet, bien qu'il m'a fallu de NOMBREUSES heures pour venir à bout d'un problème d'initialisation des variables (qui n'étaient pas enregistrées dans les scopes).
On peut voir d'ailleurs sur le test Scope1.txt que les variables sont bien initialisées (le pretty print est donc fonctionnel), mais le passage aux in et end ne passe pas pour le parser.
Je pense que cela vient surtout de ma méconnaissance du langage utilisé pour le .jj (pas de cours précédents et une introduction, il faut le dire, trop brève dessus).
J'ai vraiment l'impression que j'aurais pu aller plus loin si je n'étais pas resté bloqué sur ces scopes, mais ils étaient nécessaires pour le fonctionnement des boucles whiles et for d'une part, et pour l'affectation de variables d'autre part.

Pour la compilation :

Ayant réalisé le projet sur un windows je n'avais à la base pas réalisé de makefile.
J'en ai créé un pour linux mais n'ayant pas installé java dessus je n'ai pas pu le tester.

Dans le cas où il ne fonctionnerait pas il suffit de se rendre dans le répertoire et de taper les lignes de commandes indiquées : celle du jagger.jj : javacc Jagger.jj
et enfin celle de tous les fichiers java présents : javac *.java
