All:
	javacc Jagger.jj
	javac *.java
	
check : all
	echo "Test reussis :"
	java Jagger < ./Tests/Passed/Operations.txt
	java Jagger < ./Tests/Passed/Print.txt
	java Jagger < ./Tests/Passed/Char_Concatenation.txt
	java Jagger < ./Tests/Passed/Char_Comparison.txt
	java Jagger < ./Tests/Passed/ifThenElse_Imbrique.txt
	java Jagger < ./Tests/Passed/ifThenElse_Simple.txt
	java Jagger < ./Tests/Passed/ifThenElse_With_Exp

	
	echo "Tests provoquant une erreur (volontaire) :"
	java Jagger < ./Tests/ShallNot/Loosy_ifThenElse

	echo "Tests qui devraient fonctionner si le .jj était bien fonctionnel"
	java Jagger < ./Tests/Failed/Scope1.txt
	java Jagger < ./Tests/Failed/Scope2.txt
	java Jagger < ./Tests/Failed/Scope3.txt
	java Jagger < ./Tests/Failed/Assignation.txt
	java Jagger < ./Tests/Failed/While.txt
	java Jagger < ./Tests/Failed/For.txt
