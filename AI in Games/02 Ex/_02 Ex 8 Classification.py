import torch
import torch.nn as nn
import torch.nn.functional as F
from   torchvision import datasets, transforms

####  1 couche Linear
#	Qu1 : quel est le % de bonnes prédictions obtenu au lancement du programme , pourquoi ?
#	Qu2 : quel est le % de bonnes prédictions obtenu avec 1 couche Linear ?
#	Qu3 : pourquoi le test_loader n’est pas découpé en batch ?
#   Qu4 : pourquoi la couche Linear comporte-t-elle 784 entrées ?
#   Qu5 : pourquoi la couche Linear comporte-t-elle 10 sorties ?

####  2 couches Linear
#   Qu6 : quelles sont les tailles des deux couches Linear ?
# 	Qu7 : quel est l’ordre de grandeur du nombre de poids utilisés dans ce réseau ?
#	Qu8 : quel est le % de bonnes prédictions obtenu avec 2 couches Linear ?

####  3 couches Linear
#   Qu9 : obtient-on un réel gain sur la qualité des prédictions ?

####  Fonction Softmax
#   Qu10 : pourquoi est il inutile de changer le code de la fonction TestOK ?




####  1 couche Linear

class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.FC1 = nn.Linear(784, 10)

    def forward(self, x):
        n = x.shape[0]
        x = x.reshape((n,784))
        output = self.FC1(x)
        return output
    (...)

# Qu1 : quel est le % de bonnes prédictions obtenu au lancement du programme , pourquoi ? 
# -> Le % obtenu est d'environ 10% (Test set:   Accuracy: 1099/10000 (10.99%) ) ce qui est très faible 
# car les poids sont initialisés aléatoirement : le réseau n'est pas entrainé
        
# Qu2 : quel est le % de bonnes prédictions obtenu avec 1 couche Linear ?
#-> Dès le début de l'entrainement, on obtient un % proche de 90% qui augmente encore :

#    Train Epoch:   3
#    Test set:   Accuracy: 8950/10000 (89.50%)

#    Train Epoch:  39 
#    Test set:   Accuracy: 9047/10000 (90.47%)

#Qu3 : pourquoi le test_loader n’est pas découpé en batch ?
#-> Parce que l'on souhaite tester le modèle sur toute la base de test pour avoir une meilleur #évaluation de la précision.

#Qu4 : pourquoi la couche Linear comporte-t-elle 784 entrées ?
#-> 784 correspond au nombre de neurones le plus bas possible pour avoir un réseau performant 

#Qu5 : pourquoi la couche Linear comporte-t-elle 10 sorties ?
#-> 10 correspond au nombrede classes que l'on veut prédir

####  2 couches Linear

class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.FC1 = nn.Linear(784, 128)
        self.FC2 = nn.Linear(128,10)
        self.relu = F.relu

    def forward(self, x):
        n = x.shape[0]
        x = x.reshape((n,784))
        v = self.FC1(x)
        activ = self.relu(v)
        output = self.FC2(activ)
        return output
    (...)

#Qu6 : quelles sont les tailles des deux couches Linear ?
#-> FC1 : 784x128 & FC2 : 128x10

#Qu7 : quel est l’ordre de grandeur du nombre de poids utilisés dans ce réseau ?
#-> 784*128 + 128*10 ≃ 10^5 

#Qu8 : quel est le % de bonnes prédictions obtenu avec 2 couches Linear ?
#-> On arrive à un % supérieur à 97% :
#Train Epoch:  39
#Test set:   Accuracy: 9770/10000 (97.70%)

####  3 couches Linear

class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.FC1 = nn.Linear(784, 128)
        self.FC2 = nn.Linear(128,64)
        self.FC3 = nn.Linear(64,10)
        self.relu = F.relu

    def forward(self, x):
        n = x.shape[0]
        x = x.reshape((n,784))
        v = self.FC1(x)
        activ = self.relu(v)
        v2 = self.FC2(activ)
        activ2 = self.relu(v2)
        output = self.FC3(activ2)
        return output
    (...)

#Qu9 : obtient-on un réel gain sur la qualité des prédictions ?
#-> Il n'y a pas de réel gain sur la qualité des prédictions :
#Train Epoch:  39
#Test set:   Accuracy: 9754/10000 (97.54%)


####  Fonction Softmax

class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.FC1 = nn.Linear(784, 128)
        self.FC2 = nn.Linear(128,64)
        self.FC3 = nn.Linear(64,10)
        self.relu = F.relu
        self.softmax = F.log_softmax

    def forward(self, x):
        n = x.shape[0]
        x = x.reshape((n,784))
        v = self.FC1(x)
        activ = self.relu(v)
        v2 = self.FC2(activ)
        activ2 = self.relu(v2)
        v3 = self.FC3(activ2)
        output = self.softmax(v3, dim=1)
        return output

    def Loss(self,Scores,target):
        return F.nll_loss(Scores, target)

#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
#Train Epoch:  37
#Test set:   Accuracy: 9806/10000 (98.06%)

#Qu10 : pourquoi est il inutile de changer le code de la fonction TestOK 
#-> La fonction TestOK ne fait que compter le nombre de bons résultats et ne tient pas compte
#des probabilités attribuées par le réseau