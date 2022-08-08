import torch, numpy, matplotlib.pyplot as plt

layer = torch.nn.Linear(1,1)	# creation de la couche Linear
activ = torch.nn.ReLU()         # fonction d’activation ReLU
Lx = numpy.linspace(-2,2,50)    # échantillonnage de 50 valeurs dans [-2,2]
Ly = []

#eval
#for x in Lx:
#  input = torch.FloatTensor([x])	# création d’un tenseur de taille 1
#  v1 = layer(input)			        # utilisation du neurone
#  v2 = activ(v1)			        # application de la fnt activation ReLU
#  Ly.append(v2.item())		        # on stocke le résultat dans la liste

#etape 1 :
row = torch.Tensor(numpy.array(Lx))
input = torch.reshape(row, (50,1))

#etape 2
layer_1 = torch.nn.Linear(1,3)
layer_2 = torch.nn.Linear(3,1)

v1 = layer_1(input)
v2 = activ(v1)
v3 = layer_2(v2)
Ly = v3.detach().numpy()

# tracé
plt.plot(Lx,Ly,'.') 	# dessine un ensemble de points
plt.axis('equal') 		# repère orthonormé
plt.show() 			    # ouvre la fenêtre d'affichage
