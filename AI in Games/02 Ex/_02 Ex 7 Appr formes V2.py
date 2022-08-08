import numpy as np
import matplotlib.pyplot as plt
import random
import torch
import torch.nn.functional as FNT
import torch.nn as nn

import random
import math
# (x,y,category)
points= []
N = 30    # number of points per class
K = 3     # number of classes
for i in range(N):
   r = i / N
   for k in range(K):
      t = ( i * 4 / N) + (k * 4) + random.uniform(0,0.2)
      points.append( [ ( r*math.sin(t), r*math.cos(t) ) , k ] )


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

def error_d(score, id_cat, d) :
    #Err = torch.FloatTensor([0,0,0])
    Err = 0
    for i in range(len(score)) :
        Err += torch.max(torch.FloatTensor([0,0,0]), score[i] - score[i,id_cat[i]] + d)
    return Err


# On se propose de travailler avec 2 couches de neurones :
# Input => Linear => Relu => Linear => Scores

neurones = 1000 #10 / 30 / 100 / 300 / 500 / 1000
layer1 = torch.nn.Linear(2,neurones)
activation = torch.nn.functional.relu
layer2 = torch.nn.Linear(neurones,3)


class Net(nn.Module) :
    def __init__(self):
        super().__init__()
        self.couche1 = layer1
        self.activ = activation
        self.couche2 = layer2

    def forward(self,x):
        x1 = self.couche1(x)
        x2 = self.activ(x1)
        x3 = self.couche2(x2)
        return x3

model = Net()
def ComputeCatPerPixel():
    XY = torch.stack((torch.FloatTensor(XXXX), torch.FloatTensor(YYYY)), 2)
    score = model(XY)
    CCCC = score.argmax(axis= 2)
    return CCCC

d = 0.5
input = torch.FloatTensor([i[0] for i in points] )
id_cat = torch.LongTensor([j[1] for j in points] )
optimizer = torch.optim.SGD(model.parameters(), lr=0.01)

for iteration in range(100):
    optimizer.zero_grad()
    score = model(input)
    err = error_d(score, id_cat, d)
    sum_error = torch.sum(err)
    sum_error.backward()
    optimizer.step()
    
    if iteration%1==0 :
      DessineFond()
      DessinePoints()

    
      plt.title(str(iteration))
      plt.show(block=False)
      plt.pause(0.5)  # pause avec duree en secondes

