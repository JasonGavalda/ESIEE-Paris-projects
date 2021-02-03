# ______Imports_______

from flask import Flask, escape, request, render_template, session, redirect, url_for
from flask_bootstrap import Bootstrap
from chat_utilisation_web import chat as bot
from chat_utilisation_web import envoie_trois_plus_grand as etpg
from fonction import ajout_dans_JSON as aj
from fonction import format_intentions as fi
from fonction import recupere_balises as recup_balises
from fonction import suppr_dans_JSON as suppr_balise
from fonction import objet_dans_JSON as odj
from fonction import remplace_objet as ro
from fonction import stocke as sto
from fonction import extraire_suggestion as exsug
from fonction import ajout_suggestion as ajsug
from fonction import retirer_suggestion as resug

# ______Initialisation du programme_________

app = Flask(__name__)  # Initialisation de flask, ne pas changer
app.secret_key = "PastekMayo"  # Clé de chiffrement des cookies de session
Bootstrap(app)  # Initialisation de bootstrap


# ____________Variables globales_____________

fichier_donnees = "donnees.json"


# ______Création de l'application flask______


def initCookie(modif=False):

    if(modif == True):

        modif = "On"
        session['modif'] = modif

    else:

        # Initialisation des valeurs que l'on stocke dans le cookie de session
        questions_g = ""
        reponses_g = ""
        questions_pb = ""
        reponses_pb = ""
        questions_c = ""
        reponses_c = ""
        questions_ldb = ""
        reponses_ldb = ""
        modif = ""
        feedback = "False"

        # Initialisation des champs du cookie de session
        session['questions_pb'] = questions_pb
        session['reponses_pb'] = reponses_pb
        session['questions_g'] = questions_g
        session['reponses_g'] = reponses_g
        session['questions_c'] = questions_c
        session['reponses_c'] = reponses_c
        session['questions_ldb'] = questions_ldb
        session['reponses_ldb'] = reponses_ldb
        session['modif'] = modif
        session['feedback'] = feedback


def page(champ_q, champ_r, chatzone, botname, feedback="False"):

    # Stockage de l'historique de chat
    if (request.cookies.get('session') == None):

      # Initialisation du cookie de session

        initCookie()

        # Stockage de l'historique de chat sous forme de string

        questions = session[champ_q]  # Récupération des données du cookie
        reponses = session[champ_r]

        questions += request.form[chatzone]  # On ajoute la première question
        # On ajoute la première réponse
        reponses += bot((session[champ_q] + "|" +
                         request.form[chatzone]).split("|"), botname, feedback)
        session[champ_q] = questions  # On stocke dans le cookie de session
        session[champ_r] = reponses  # On stocke dans le cookie de session

    else:

        # Stockage de l'historique de chat sous forme de string
        # On récupère le contenu des questions posées et on le stocke dans une variable
        questions = session[champ_q]
        # On récupère le contenu des réponses données et on le stocke dans une variable
        reponses = session[champ_r]

        if((((session[champ_q]).split("|"))[-1] == "non") & (feedback == "True")):
            #ancien_chatzone = chatzone
            chatzone = "amelio"

        if(chatzone == "amelio"):
            sto("amelioration.txt",
                request.form[chatzone], (session[champ_q].split("|"))[-2])

        # On vérifie que le premier message est vide, si oui on le remplace par la première question et la première réponse
        if((questions == "") & (reponses == "")):
            reponses = bot(
                (session[champ_q] + "|" + request.form[chatzone]).split("|"), botname, feedback)
            questions = request.form[chatzone]

        else:
            # On ajoute les nouvelles questions à la variable
            questions += "|" + request.form[chatzone]
            # On ajoute les nouvelles réponses à la variable
            # True c'est pour le cookie amélioration
            reponses += "|" + \
                bot((session[champ_q] + "|" + request.form[chatzone]
                     ).split("|"), botname, feedback)

        # On stocke la nouvelle variable de questions dans le cookie
        session[champ_q] = questions
        # On stocke la nouvelle variable de réponses dans le cookie
        session[champ_r] = reponses

    # On récupère le champ du cookie de questions correspondant à la page, qu'on split
    quest = session[champ_q].split("|")
    # On récupère le champ du cookie de réponses correspondant à la page, qu'on split
    rep = session[champ_r].split("|")

    return quest, rep


@app.route('/')  # Sert à définir l'emplacement de la page sur le site
def home():  # Fonction qui s'exécute lorsqu'on est sur cette page

    # Affiche le template passé en argument
    return render_template('index.html')


# Méthodes utilisées afin de récupérer des données
@app.route('/', methods=['POST', 'GET'])
def home_post():

    # if(request.cookies.get('session')):

    #feedback = request.form['feedback']
    quest, rep = page('questions_g', 'reponses_g',
                      'chatzone_g', 'Home', "True")

    # On affiche la page d'index en envoyant les listes de réponses et questions (affichées par une boucle for dans le html)
    return render_template('index.html', chat_q=quest, chat_r=rep, cookie_session=request.cookies.get('session'))


@app.route('/post_bac')  # Voir explications au dessus
def postBac():
    # La variable "title" sert juste à afficher le titre de la page
    return render_template('postBac.html', title='Post Bac')


@app.route('/post_bac', methods=['POST', 'GET'])  # Voir explications au dessus
def postBac_post():

    quest, rep = page('questions_pb', 'reponses_pb',
                      'chatzone_pb', 'PB', "True")

    return render_template('postBac.html', chat_q=quest, chat_r=rep, cookie_session=request.cookies.get('session'))


@app.route('/cpge')  # Voir explications au dessus
def cpge():
    return render_template('cpge.html', title='CPGE')


@app.route('/cpge', methods=['POST', 'GET'])  # Voir explications au dessus
def cpge_post():

    quest, rep = page('questions_c', 'reponses_c', 'chatzone_c', 'CPGE')

    return render_template('cpge.html', chat_q=quest, chat_r=rep, cookie_session=request.cookies.get('session'))


@app.route('/licence_dut_bts')  # Voir explications au dessus
def licenceDutBts():
    return render_template('licence_dut_bts.html', title='Licence & DUT & BTS')


# Voir explications au dessus
@app.route('/licence_dut_bts', methods=['POST', 'GET'])
def licenceDutBts_post():

    quest, rep = page('questions_ldb', 'reponses_ldb',
                      'chatzone_ldb', 'DUT', "True")

    return render_template('licence_dut_bts.html', chat_q=quest, chat_r=rep, cookie_session=request.cookies.get('session'))


@app.route('/clear')
def clear():  # Fonction qui sert à supprimer le cookie de session, et donc l'historique de chat de toutes les pages

    session.clear()

    return redirect("/")  # Renvoie sur la page d'accueil


@app.route('/rech')   # Emplacement de la page de recherche de le site de maj du json
def rech():

    balises = recup_balises(fichier_donnees)   # récupère les balises du json

    return render_template("rech.html", balises=balises)   # affiche la page de recherche en lui envoyant les données utiles


@app.route('/rech', methods=['POST', 'GET'])   # Emplacement de la page de recherche de le site de maj du json (avec les méthodes de recup de données)
def rech_post():

    balises = recup_balises(fichier_donnees)   # récupère les balises du json

    balise = request.form['balise']   # Recupere l'info du champs balise
    modeles = odj(fichier_donnees, balise, "modeles")   # Recupere les modeles de la balise dans le json
    reponses = odj(fichier_donnees, balise, "reponses")   # Recupere les réponses de la balise dans le json
    tout = []
    tout.append(balise)
    tout.append(modeles)
    tout.append(reponses)

    return render_template('rech.html', balises=balises, tout=tout)   # affiche la page de recherche en lui envoyant les données utiles


@app.route('/ajout')   # Emplacement de la page d'ajout de le site de maj du json
def maj():

    return render_template("ajout.html")   # affiche la page d'ajout en lui envoyant les données utiles


@app.route('/ajout', methods=['POST', 'GET'])   # Emplacement de la page d'ajout de le site de maj du json (avec les méthodes de recup de données)
def maj_post():

    bal = request.form['Balise']   # Recupere l'info du champs Balise
    questions = []
    questions.append(request.form['Q1'])   # Recupere l'info du champs Q1 et l'ajoute à la liste questions
    questions.append(request.form['Q2'])   # Recupere l'info du champs Q2 et l'ajoute à la liste questions
    questions.append(request.form['Q3'])   # Recupere l'info du champs Q3 et l'ajoute à la liste questions
    categ = request.form['Categorie']   # Recupere l'info du champs Categorie
    rep = []
    rep.append(request.form['Reponses'])   # Recupere l'info du champs Q3 et l'ajoute à la liste rep

    intention = fi(bal, questions, categ, rep)   # Met les informations sous la forme d'une intention du json

    aj(fichier_donnees, intention)   # Ajoute l'intention au json

    return render_template('ajout.html')   # affiche la page d'ajout en lui envoyant les données utiles


@app.route('/suppr')   # Emplacement de la page de suppression de le site de maj du json
def suppr():

    balises = recup_balises(fichier_donnees)   # récupère les balises du json

    return render_template("suppr.html", balises=balises)   # affiche la page de suppression en lui envoyant les données utiles


@app.route('/suppr', methods=['POST', 'GET'])   # Emplacement de la page de suppression de le site de maj du json (avec les méthodes de recup de données)
def suppr_post():

    bal = request.form['suppression']   # Recupere l'info du champs suppression

    suppr_balise(fichier_donnees, bal)   # supprime la balise du json

    balises = recup_balises(fichier_donnees)   # récupère les balises du json

    return render_template('suppr.html', balises=balises)   # affiche la page de suppression en lui envoyant les données utiles


@app.route('/modif')   # Emplacement de la page de modification de le site de maj du json
def modif():

    balises = recup_balises(fichier_donnees)   # récupère les balises du json

    return render_template('modif.html', balises=balises)   # affiche la page de modification en lui envoyant les données utiles


@app.route('/modif', methods=['POST', 'GET'])   # Emplacement de la page de modification de le site de maj du json (avec les méthodes de recup de données)
def modif_post():

    balises = recup_balises(fichier_donnees)   # récupère les balises du json

    if((not request.cookies.get('session'))):   # Si il n'existe pas de cookies de session

        initCookie(modif=True)   # On initialise les cookies avec le booleen de modification à True

    elif(session['modif'] == ""):

        initCookie(modif=True)  # On initialise les cookies avec le booleen de modification à True

    if(session['modif'] == "On"):

        session['modif'] = "Off"   # On donne la valeur Off au cookies de modification
        balise = request.form['balise']   # Recupere l'info du champs balise
        objet = request.form['objet']   # Recupere l'info du champs objet
        obj = odj(fichier_donnees, balise, objet)   # On récupere tous les éléments de l'objet choisit
        session['balise'] = balise
        session['objet'] = objet
        session['obj'] = obj

        return render_template('modif2.html', objets=obj, page=objet)   # affiche la deuxième page de modification en lui envoyant les données utiles

    else:

        liste_modif = []
        balise = session['balise']
        objet = session['objet']
        obj = session['obj']
        for i in range(len(obj)):
            modif = 'modif' + str(i)
            liste_modif.append(request.form[modif])   # Recupere l'info du champs modif[i] et l'ajoute à la liste liste_modif
        if(objet == "modeles"):
            liste_modif.append(request.form['plus'])   # Recupere l'info du champs plus (pour les modeles) et l'ajoute à la liste liste_modif

        ro(fichier_donnees, balise, objet, liste_modif)   # Remplace les éléments dans le json
        session['modif'] = "On"   # On donne la valeur On au cookies de modification

        return render_template('modif.html', balises=balises)   # affiche la page de modification en lui envoyant les données utiles


@app.route('/cookiegen', methods=['POST', 'GET'])
def cookie_gen():

    initCookie()

    return redirect('/')


@app.route('/suggestions')   # Emplacement de la page de modification de le site de maj du json
def prop():

    balise, modele, nb_sug = exsug('amelioration.txt')   # Récupere les suggestions stockées dans le txt

    return render_template('suggestions.html', balise=balise, modele=modele, nb_sug=nb_sug)   # affiche la page de suggestion en lui envoyant les données utiles


@app.route('/suggestions', methods=['POST', 'GET'])   # Emplacement de la page de modification de le site de maj du json (avec les méthodes de recup de données)
def prop_post():

    balise_form = request.form['balise']   # Recupere l'info du champs balise
    modele_form = request.form['modele']   # Recupere l'info du champs modele
    application = request.form['application']   # Recupere l'info du champs application

    if (application == "true"):
        ajsug(fichier_donnees, "amelioration.txt", balise_form, modele_form)   # Ajoute la suggestion (en prennant en compte les modifs) au json

    else:
        resug("amelioration.txt")   # Supprimer la suggestion du txt

    return redirect('/suggestions')    # Redirige vers la page de suggestion


if __name__ == '__main__':  # Provisoire, pour le serveur local
    app.run(debug=True)
