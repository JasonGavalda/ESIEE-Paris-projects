import json


#Ajouter une balise dans le .json
def ajout_balise():
    #On commence par afficher tout ce qui existe pour etre sur de ne pas avoir de doublon
    print("Vérifiez si ce que vous voulez ajouter n'existe pas déjà dans la liste suivante :")
    with open("donnees.json",encoding="utf-8") as document:
        donnee = json.load(document)
    print("[", end='')
    for intention in donnee["intentions"]:
        print(intention["balise"], end='\t')
    print("]")
    
    
    rep = input("Continuer ? (oui/non)\n")
    if rep=="oui" or rep=="o":
        while True:
            #On précise que le processus peut etre arreter a tout moment
            print("Vous pouvez taper \"quitter\" à tout moment pour arrêter")
            #Le nom de la balise
            balise = input("Indiquez le nom de la balise (en quelques mots clés comme bonjour, webaurion, problème payement, etc...) :\n")
            if balise.lower()  == "quitter":
                break
            
            #Les 3 phrases type pour alimenter le modele initial
            modele1 = input("Indiquez 3 questions types qui correspondraient à la balise :\n")
            if modele1.lower()  == "quitter":
                break
            modele2 = input()
            if modele2.lower()  == "quitter":
                break
            modele3 = input()
            if modele3.lower()  == "quitter":
                break
            
            #La catégorie de la balise
            print("Sélectionnez la catégorie correspondante :")
            print("1.general 2.E1 3.CPGE 4.DUT 5.Quitter\n")
            num = input()
            if num.lower()  == "5":
                break
            elif num.lower() == "4":
                categorie = "DUT"
            elif num.lower() == "3":
                categorie = "CPGE"
            elif num.lower() == "2":
                categorie = "E1"
            elif num.lower() == "1":
                categorie = "general"
            ##Menu deroulant donc est dans [1, 5]
            
            #Ajout dans la base de données
            balise = cleanString(balise)
            modele1 = cleanString(modele1)
            modele2 = cleanString(modele2)
            modele3 = cleanString(modele3)
            categorie = cleanString(categorie)
            
            intention = format_intentions(balise, [modele1, modele2, modele3], categorie)
            ajout_dans_JSON("donnees.json", intention)
            
            
            print("Les informations ont bien été ajoutées")
            break
        
    print("Le programme est terminé")


#Supprimer une balise dans le .json
def suppression_balise():
    #Affichage de toute les balises pour selectionner laquel/lesquelles supprimer
    print("Veuillez sélectionner parmis la liste suivante l'intention que vous souhaiter supprimer :")
    with open("donnees.json",encoding="utf-8") as document:
        donnee = json.load(document)
    print("[", end='')
    for intention in donnee["intentions"]:
        print(intention["balise"], end='\t')
    print("]")
    
    #Reponse de l'utilisateur
    rep = input()
    
    #Confirmation de la reponse de l'utilisateur
    confirmation = input("Etes-vous sur ? (oui/non)\n")
    if confirmation=="oui" or confirmation=="o":
        #Suppresion de l'intention concernee
        suppr_dans_JSON("donnees.json", rep)
        
        print("L'opération a bien été réalisée")
    else:
        print("L'opération a bien été annulée")


#Modification d'une balise du .json (ajout d'éléments dans le modèle, ou changement ponctuel de la réponse, comme la date de rentree)
def modifier_balise():
    #Affichage de toute les balises pour selectionner laquel modifier
    print("Veuillez sélectionner parmis la liste suivante l'intention que vous souhaiter modifier :")
    with open("donnees.json",encoding="utf-8") as document:
        donnee = json.load(document)
    print("[", end='')
    for intention in donnee["intentions"]:
        print(intention["balise"], end='\t')
    print("]")
    
    #Reponse de l'utilisateur
    rep = input()
    
    print("Voulez-vous modifier les modèles (1) ou la réponse (2) ?")
    cat = input()
    
    try:
        cat = int(cat)
        if cat==1:
            donnees = "modeles"
        elif cat==2:
            donnees = "reponses"
        else:
            print("La réponse n'est pas valide")
    except ValueError:
        print("Il faut mettre 1 ou 2")
    
    #Valider ou non
    #Afficher tout dans les barres du site (trop complexe a faire la) et donner la main pour modifier le texte (ou rajouter une phrase pour modele)
    print("Voici tout :")
    
    for intention in donnee["intentions"]:
        if intention["balise"]==rep:
            for modele in intention[donnees]:
                print(modele)
    
    print("\n")
    print("Tout est bien qui finit bien")


#Pour ajouter des donnees dans un JSON, au format d'une intention classique (fonction auxiliaire de ajout_balise())
def ajout_dans_JSON(nomJSON, donnees):
    with open(nomJSON, encoding="utf-8") as json_file:
        don = json.load(json_file)
        temp = don['intentions']
        
        #objet python a ajouter
        y = donnees
        
        #ajout donnees a intentions
        temp.append(y)
    
    saveToJSON(nomJSON, don)


#Pour supprimer des donnees dans un JSON par une intention specifique (fonction auxiliaire de suppression_balise())
def suppr_dans_JSON(nomJSON, intention):
    #ouverture du .json
    with open(nomJSON, 'r', encoding="utf-8") as json_file:
        data = json.load(json_file)
    
    #verification de la presence de la balise par un bouclage
    for intent in data["intentions"][:]:
        if (intent["balise"] == intention):
            #suppression si la balise est trouvee
            data["intentions"].remove(intent)
    
    saveToJSON(nomJSON, data)


#Format pour écrire une balise, sans réponse de base (pour ajouter/modifier la base de donnees)
def format_intentions(balise, modeles, categorie, reponses=[]):
    #mise au format pour notre .json
    intention = {"balise": balise,\
         "modeles": [],\
         "reponses": [],\
         "categorie": categorie\
        }
    #on ajoute tout les modeles (il y en a plusieurs a priori)
    for k in range(len(modeles)):
        intention["modeles"].append(modeles[k])
    #on ajoute toute les reponses (il y en a plusieurs a priori)
    for k in range(len(reponses)):
        intention["reponses"].append(reponses[k])
    
    return intention


#Modifier une string pour qu'elle soit propre et sécurisée (pour les donnees venant de l'utilisateur)
def cleanString(string):
    #Change les apostrophes et les tirets en espaces pour separer les mots
    string = string.replace("\'", " ").replace("-", " ")
    
    #Nettoie la string de tout les caracteres speciaux, sauf les espaces
    string = ''.join(c for c in string if c.isalnum() or c==' ')
    
    #Limite la taille de la string a 100 caracteres
    string = string[:100] + (string[100:] and '')
    
    return string


#Enregistrement propre d'un .json (pour tout ce qui est modification)
def saveToJSON(nomFichier, donnees, chemin='./'):
    nomCheminFichierExt = './' + chemin + '/' + nomFichier
    with open(nomCheminFichierExt, 'w', encoding="utf-8") as fp:
        json.dump(donnees, fp, indent=4, sort_keys=True, ensure_ascii=False)


#Permet d'obtenir toutes les balises du fichier en question
def recupere_balises(nomFichier):
    #Charge le fichier .json dans une variable
    with open(nomFichier,encoding="utf-8") as document:
        donnee = json.load(document)
    
    #Creation et remplissage de la liste qui contiendra les balises
    balises = []
    for intention in donnee["intentions"]:
        balises.append(intention["balise"])
    
    return balises


#Permet de recuperer une liste d'objet dans un json (modeles ou reponses dans notre cas)
def objet_dans_JSON(nomFichier, balise, objet):
    #Charge le fichier .json dans une variable
    with open(nomFichier,encoding="utf-8") as document:
        donnee = json.load(document)
    
    #La liste contenant les objets à remplir
    objets = []
    
    #On rentre dans la bonne balise, puis on recupere l'objet voulu
    for intention in donnee["intentions"]:
        if intention["balise"]==balise:
            if objet=="categorie":
                objets.append(intention[objet])
            else:
                for obj in intention[objet]:
                    objets.append(obj)
    return objets


#Remplace les objets concerné dans le fichier json (modeles ou la reponse si elle change)
def remplace_objet(nomFichier, balise, objet, objets):
    #Charge le fichier .json, puis remplace l'objet voulu par celui mis en parametre
    with open(nomFichier,encoding="utf-8") as document:
        donnee = json.load(document)
        for intention in donnee["intentions"]:                      #on rentre dans les intentions
            if intention["balise"] in [balise]:                     #on cherche la bonne balise
                intention[objet] = objets                           #remplace l'objet en question
    
    saveToJSON(nomFichier, donnee)


#Stocke le feedback de l'utilisateur dans un .txt
def stocke(nomTexte, balise, objet):                                #3 ligne pour chaque suggestion
    f = open(nomTexte, "a")                                         #ouvre le fichier en ajout
    f.write(balise + "\n")                                          #la ligne 1 est pour la balise
    f.write(cleanString(objet) + "\n")                              #la ligne 2 est pour le modele
    f.write("\n")                                                   #la ligne 3 est pour separer de la suivante


#Extraction du premier modele suggerer dans le .txt
def extraire_suggestion(nomTexte):
    f = open(nomTexte, "r")                                         #recupere les deux premieres lignes
    balise = f.readline()
    modele = f.readline()
    
    nb_lignes = 2                                                   #compte le nombre de ligne
    
    for x in f:
        nb_lignes += 1
    nb_suggestions = nb_lignes//3                                    #chaque suggestion est sur 3 lignes
        
    f.close()
    
    return balise, modele, nb_suggestions


#Permet de retirer la premiere suggestion du .txt
def retirer_suggestion(nomTexte):
    lignes = []
    f = open(nomTexte, "r")
    
    for x in f:                                                     #stocke toute les lignes
        lignes.append(x)
    
    f.close()
    
    fi = open(nomTexte, "w")
    
    for k in range(len(lignes)-3):                                  #reecrit tout sauf les 3 premieres lignes
        fi.write(lignes[k+3])
    
    fi.close()


#Ajoute la suggestion apres modification humaine, puis la retirer du .txt
def ajout_suggestion(nomFichier, nomTexte, balise, modele):
    objets = objet_dans_JSON(nomFichier, balise, "modeles")         #recupere le modele voulu
    objets.append(modele)                                           #ajoute le nouveau modele
    
    remplace_objet(nomFichier, balise, "modeles", objets)           #le met dans la base de donnee
    
    retirer_suggestion(nomTexte)                                    #supprime le modele qui vient d'etre ajouter de fichier de stockage