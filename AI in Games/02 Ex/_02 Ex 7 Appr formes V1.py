import numpy as np
import matplotlib.pyplot as plt
import random
import torch
import torch.nn.functional as FNT
import torch.nn as nn

######################################################

# (x,y, category)
points = [ [(0.5,0.4),0],
           [(0.8,0.3),0],
            [(0.3,0.8),0],
            [(-.4,0.3),1],
            [(-.3,0.7),1],
            [(-.7,0.2),1],
            [(-.4,-.5),1],
            [(0.7,-.4),2],
            [(0.5,-.6),2]]

######################################################
#
#  outils d'affichage -  NE PAS TOUCHER

def DessineFond():
    iS = ComputeCatPerPixel()
    levels = [-1, 0, 1, 2]
    c1 = ('r', 'g', 'b')
    plt.contourf(XXXX, YYYY, iS, levels, colors = c1)

def DessinePoints():
    c2 = ('darkred','darkgreen','lightblue')
    for point in points:
        coord = point[0]
        cat   = point[1]
        plt.scatter(coord[0], coord[1] ,  s=50, c=c2[cat],  marker='o')

XXXX , YYYY = np.meshgrid(np.arange(-1, 1, 0.01), np.arange(-1, 1, 0.01))


##############################################################
#
#  PROJET

# Nous devons apprendre 3 catégories : 0 1 ou 2 suivant ce couple (x,y)

# Pour chaque échantillon, nous avons comme information [(x,y),cat]

# Construisez une couche Linear pour un échantillon prédit un score pour chaque catégorie
layer = torch.nn.Linear(2,3)
# Le plus fort score est associé à la catégorie retenue

# Pour calculer l'erreur, on connait la bonne catégorie k de l'échantillon de l'échantillon.
# On calcule Err = Sigma_(j=0 à nb_cat) max(0,Sj-Sk)  avec Sj score de la cat j

def error(score, id_cat) :
    Err = 0
    for i in range(len(score)) :
        Err += torch.max(torch.FloatTensor([0,0,0]), score[i] - score[i][id_cat[i]])
    return Err

def error_d(score, id_cat, d) :
    Err = 0
    for i in range(len(score)) :
        Err += torch.max(torch.FloatTensor([0,0,0]), score[i] - score[i][id_cat[i]] - d)
    return Err

# Comment interpréter cette formule :
# La grandeur Sj-Sk nous donne l'écart entre le score de la bonne catégorie et le score de la cat j.
# Si j correspond à k, la contribution à l'erreur vaut 0, on ne tient pas compte de la valeur Sj=k dans l'erreur
# Sinon Si cet écart est positif, ce n'est pas bon signe, car cela sous entend que le plus grand
#          score ne correspond pas à la bonne catégorie et donc on obtient un malus.
#          Plus le mauvais score est grand? plus le malus est important.
#       Si cet écart est négatif, cela sous entend que le score de la bonne catégorie est supérieur
#          au score de la catégorie courante. Tout va bien. Mais il ne faut pas que cela influence
#          l'erreur car l'algorithme doit corriger les mauvaises prédictions. Pour cela, max(0,.)
#          permet de ne pas tenir compte de cet écart négatif dans l'erreur.

class Net(nn.Module) :
    def __init__(self):
        super().__init__()
        self.couche1 = layer

    def forward(self,x):
        x = self.couche1(x)
        return x

model = Net()

def ComputeCatPerPixel():
    XY = torch.stack((torch.FloatTensor(XXXX), torch.FloatTensor(YYYY)), 2)
    score = model(XY)
    CCCC = score.argmax(axis= 2)
    return CCCC

d = 0.01
input = torch.FloatTensor([i[0] for i in points] )
id_cat = torch.LongTensor([j[1] for j in points] )
optimizer = torch.optim.SGD(model.parameters(), lr=0.5)

for iteration in range(10):
    optimizer.zero_grad()
    score = model(input)
    err = error_d(score, id_cat, d)
    sum_error = torch.sum(err)
    sum_error.backward()
    optimizer.step()

    DessineFond()
    DessinePoints()

    plt.title(str(iteration))
    plt.show(block=False)
    plt.pause(0.5)  # pause avec duree en secondes

