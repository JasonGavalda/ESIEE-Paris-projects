
VALEUR_DACCEPTATION = 0.65
VALEUR_EXCLUSION = 0.0 
AIDE ="Si la réponse vous convient tapez oui "

import nltk                                                    #librairie qui permmet le traitement de langage
from nltk.stem.snowball import FrenchStemmer                   #fonction de racinisation en francais
stemmer = FrenchStemmer()

import numpy
import tflearn                                                 #librairie pour le machine learning
import tensorflow
import json                                                    #fichier dictionnaire qui nous sert de base de données
import pickle                                                 #fichier qui nous sert a sauvegarder les paramètres du modele tflearn

from flask import Markup    
    
    
def __reseau(entrainement,sortie):
    tensorflow.reset_default_graph()

    reseau = tflearn.input_data(shape=[None, len(entrainement[0])])
    reseau = tflearn.fully_connected(reseau, 8)
    reseau = tflearn.fully_connected(reseau, 8)
    reseau = tflearn.fully_connected(reseau, len(sortie[0]), activation="softmax")
    reseau = tflearn.regression(reseau)
    return reseau

def __sac_de_mots(p, mots):                                        #fonction qui sépare les string en token et qui fait du one hot encoding avec les mots en paramètre
    sac = [0 for _ in range(len(mots))]
    
    p_mots = nltk.word_tokenize(p)                               #prend la string en paramètre et crée un tableau avec tous les mots séparé
    p_mots = [stemmer.stem(mot.lower()) for mot in p_mots]       #racinistaion de tous les tokens
    
    for ph in p_mots:                                            #boucle pour le one hot encoding
        for i, m in enumerate(mots):
            if m == ph:
                sac[i] = 1
                
    return numpy.array(sac)
    
def __choix_modele(ent,modele):                         #choix du modele pour les différentes pages
    if(modele =="Home"):        
        resultats = modeleHome.predict([__sac_de_mots(ent, mots)])[0]         #utilise sac de mots pour predire avec du one hot encoding en fonction du modele choisis
        bal_test = etiquette
    elif(modele == "CPGE"):
        resultats = modeleCPGE.predict([__sac_de_mots(ent, motsCPGE)])[0]
        bal_test = etiquetteCPGE
    elif(modele == "PB"):
        resultats = modelePB.predict([__sac_de_mots(ent, motsPB)])[0]
        bal_test = etiquettePB
    elif(modele == "DUT"):
        resultats = modeleDUT.predict([__sac_de_mots(ent, motsDUT)])[0]
        bal_test = etiquetteDUT
    else:
        return False
    return resultats,bal_test
    
#Permet de retourner les 3 valeurs les plus elevees de la liste entrante, en excluant d'abord le max
def __trois_autres_plus_grand(liste):
    #On minimise le maximum actuel (ce sont des pourcentages)
    liste[numpy.argmax(liste)] = VALEUR_EXCLUSION
    
    #On stocke les suivants dans la liste qu'on renvoit
    resultats = []
    resultats.append(numpy.argmax(liste))
    liste[resultats[0]] = VALEUR_EXCLUSION
    resultats.append(numpy.argmax(liste))
    liste[resultats[1]] = VALEUR_EXCLUSION
    resultats.append(numpy.argmax(liste))
    
    return resultats
    
def envoie_trois_plus_grand(ent,modele): #permet de renvoyer un form avec le choix des balises pour que l'utilisateur 
    bal_plus_probable="Veuillez choisir le sens de votre question, ou autres si il n'est pas présent \n<form class='needs-validation' method='POST' novalidate>"
    resultats,bal_test = __choix_modele(ent,modele)
    plus_probable = __trois_autres_plus_grand(resultats) #récupère les trois balises les plus problables après celle envoyée
    for element in plus_probable:
        bal_plus_probable += "<div class='form-check form-check-inline'><input class='form-check-input' type='radio' name='amelio' id='inlineRadio1' value='"+bal_test[element]+"' required><label class='form-check-label' for='inlineRadio1'>"+bal_test[element]+"</label></div>" #créée les trois boutons radio pour les balises
    bal_plus_probable += "<div class='form-check form-check-inline'><input class='form-check-input' type='radio' name='amelio' id='inlineRadio1' value='autres' required checked><label class='form-check-label' for='inlineRadio1'> autres</label></div><button type='submit' class='btn btn-success'>Valider</button></form>"  #créée le bouton radio pour autre 
    return Markup(bal_plus_probable) #markup la string pour que flask prenne en compte les balises

    

with open("donnees.json",encoding="utf-8") as file:
    donnee = json.load(file)                                   #chargemente du dictionnaire
   
with open("donneehome.pickle", "rb") as f:                      
    mots, etiquette, entrainement, sortie = pickle.load(f)     #chargement des paramètres du modele générale




modeleHome = tflearn.DNN(__reseau(entrainement,sortie))



modeleHome.load("modelehome.tflearn")                         #chargement du modèle entrainé lors du fichier entraînement Post-bac





##############################################################

with open("donneeCPGE.pickle", "rb") as f:
    motsCPGE, etiquetteCPGE, entrainementCPGE, sortieCPGE = pickle.load(f)     #chargement des paramètres du modele CPGE




modeleCPGE = tflearn.DNN(__reseau(entrainementCPGE,sortieCPGE))



modeleCPGE.load("modeleCPGE.tflearn")                               #chargement du modèle entrainé lors du fichier entraînement Post-bac

        
        
##############################################################        

with open("donneePost_bac.pickle", "rb") as f:
    motsPB, etiquettePB, entrainementPB, sortiePB = pickle.load(f)      #chargement des paramètres du modele Post-bac




modelePB = tflearn.DNN(__reseau(entrainementPB,sortiePB))



modelePB.load("modelePost_bac.tflearn")                             #chargement du modèle entrainé lors du fichier entraînement Post-bac

        
###############################################################

with open("donneeDUT.pickle", "rb") as f:
    motsDUT, etiquetteDUT, entrainementDUT, sortieDUT = pickle.load(f)


modeleDUT = tflearn.DNN(__reseau(entrainementDUT,sortieDUT))



modeleDUT.load("modeleDUT.tflearn")                             #chargement du modèle entrainé lors du fichier entraînement Post-bac

################################################################
def traitement_reponse(ent,modele,aide=""):
    if ent.lower()  == "quitter":                               
        return "Au revoir"
            
    resultats,bal_test = __choix_modele(ent,modele)
        
    resultats_index = numpy.argmax(resultats)
    balise = bal_test[resultats_index]
    if resultats[resultats_index] > VALEUR_DACCEPTATION:          #Renvoie une réponse si le modele predit une balise avec un pourcentage plus élevé que la valeur d'acceptation
        for bal in donnee["intentions"]:
            if bal['balise'] == balise:
                chaine = bal['reponses'][0] +aide                  #renvoie la réponse associé à la balise
        return Markup(chaine)                                      #markup permet de ne pas escape la string renvoyer sur flask(empêche que les balisse soit traduit &gt et &lt)
    else:
        return "Je n'ai pas bien compris, essaie encore."
            

def chat(ent,modele,amelioration):       #fonction qui prend en paramètres deux strings une pour choisir le modele et une renvoyer la réponse associer
    if amelioration == "True":            #si la personne a accepter d'améliorer le bot
        aide = "<br /> <i>Si la réponse vous convient tapez 'oui', sinon tapez 'non'</i>"
        if ent[-1].lower() == "non":    #regarde si la personne est entrain de répondre a 'aide'
            if ent[-2].lower() != "":
                return envoie_trois_plus_grand(ent[-2],modele)         #renvoie le form des trois plus grandes balises si la condition est vérifié             
            else:
                return "Posez une question d'abord"
        elif ent[-1].lower() == "oui":
            return Markup("Merci pour votre retour <br /> <i>Vous pouvez poser une nouvelle question.</i>")
        elif ent[-1].lower() == "autres":
            return "Merci, votre réponse a bien été prise en compte!"
        else:
            return traitement_reponse(ent[-1],modele,aide)     #sinon on fait la réponse classique du bot
    return traitement_reponse(ent[-1],modele)                  #si la personne n'a pas accepté d'améliorier le bot