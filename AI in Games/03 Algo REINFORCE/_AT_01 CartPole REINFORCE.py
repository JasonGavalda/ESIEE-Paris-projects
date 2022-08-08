import gym
import time
from pyglet.window import key
import torch
import torch.nn as nn
import torch.nn.functional as F
from   torchvision import datasets, transforms

env = gym.make('CartPole-v0')


# IA basique
# si le charriot est à gauche du point de départ, alors => à droite
# si le charriot est à droite du point de départ, alors => à gauche


def GetAction(x):
    if x < 0 : return 1
    else : return 0

class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.FC1 = nn.Linear(4, 20)
        self.FC2 = nn.Linear(20,2)
        self.softmax = nn.Softmax()

    def forward(self, x):
        x = self.FC1(x)
        x = self.FC2(x)
        x = self.softmax(x)
        x = torch.distributions.categorical.Categorical(x)


# Main game loop

while True :
    done = False
    observation = env.reset()
    TotalScore = 0
    env.reset()

    while not done  :
        w = env.render()
        x = observation[0]

        action = GetAction(x)
        observation, reward, done, info = env.step(action)
        TotalScore += reward
        time.sleep(0.02)


    print("Score final : " , TotalScore)
env.close()