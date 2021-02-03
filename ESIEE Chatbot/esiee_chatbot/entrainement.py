
import nltk                                                    #librairie qui permmet le traitement de langage
from nltk.corpus import stopwords                              #fonction qui définit les mots vides
from nltk.stem.snowball import FrenchStemmer                   #fonction de racinisation en francais
stemmer = FrenchStemmer()


stopwords = set(stopwords.words('french'))                     
import numpy
import tflearn                                                 #librairie pour le machine learning
import tensorflow
import json                                                    #fichier dictionnaire qui nous sert de base de données
import pickle                                                  #fichier qui nous sert a sauvegarder les paramètres du modele tflearn


with open("donnees.json",encoding="utf-8") as document:         #chargemente du dictionnaire
    donnee = json.load(document)
   
def entrainement(categorie_nom,epoque,lot):

    categorie = ["general",categorie_nom]                               #définition des categorie a prendre dans le fichier json pour entraîner le bot
    mots = []                                                       #variables qui va contenir les différents mots du dictionnaires
    etiquette = []                                                  #variables qui va contenir les différentes étiquettes du dictionnaires
    docs_x = []
    docs_y = []

    for intention in donnee["intentions"]:                          #permet de de parcourir le fichier json pour remplir les différentes variables
        if intention["categorie"] in categorie:                     
            for modele in intention["modeles"]:
                mts = nltk.word_tokenize(modele)                    #crée des jetons 
                for m in mts:
                    if not m in stopwords:
                        mots.append(m)                              #ajoutes le jetons dans la variable mots si il n'est pas un mot vide
                docs_x.append(mts)
                docs_y.append(intention["balise"]) 
                
            if intention["balise"] not in etiquette:
                etiquette.append(intention["balise"])
     
    mots = [stemmer.stem(m.lower()) for m in mots if m != "?"]      #racinisaton des mots 
    mots = sorted(list(set(mots)))

    etiquette = sorted(etiquette)

    entrainement = []
    sortie = []

    sortie_vide = [0 for _ in range(len(etiquette))]

    for x, doc in enumerate(docs_x):                                #boucle pour définir les phrase d'entrainement pour le machine learning
        sac = []
        
        mts = [stemmer.stem(m) for m in doc]                        #racinisation des mots
        
        for m in mots:                                              
            if m in mts:
                sac.append(1)                                       #le one hot encoding sur les mots
            else:
                sac.append(0)
        
        sortie_row = sortie_vide[:]
        sortie_row[etiquette.index(docs_y[x])] = 1

        entrainement.append(sac)                                    
        sortie.append(sortie_row)

    entrainement = numpy.array(entrainement)
    sortie = numpy.array(sortie)

    with open("donnee"+categorie_nom+".pickle", "wb") as f:                       #sauvegarde les paramètres du modèle dans un fichier pickle
        pickle.dump((mots, etiquette, entrainement, sortie), f)

    tensorflow.reset_default_graph()

    reseau = tflearn.input_data(shape=[None, len(entrainement[0])])
    reseau = tflearn.fully_connected(reseau, 8)
    reseau = tflearn.fully_connected(reseau, 8)
    reseau = tflearn.fully_connected(reseau, len(sortie[0]), activation="softmax")     #sortie égale au nombre d'étiquettes possible   
    reseau = tflearn.regression(reseau)

    modele = tflearn.DNN(reseau)

    modele.fit(entrainement, sortie, n_epoch=epoque, batch_size=lot, show_metric=True)
    modele.save("modele"+categorie_nom+".tflearn")
    
entrainement("CPGE",300,8)
entrainement("home",300,8)
entrainement("DUT",300,8)
entrainement("Post_bac",300,8)

