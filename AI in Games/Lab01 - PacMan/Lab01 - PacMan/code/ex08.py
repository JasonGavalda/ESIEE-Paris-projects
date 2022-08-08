from collections import defaultdict
from pacman.util import raiseNotDefined
import numpy as np


def value_iteration(mdp, discount, iterations):
    """
    Value iteration is an algorithm that estimates the Q-values of an MDP.
    It runs for the given number of iterations, using the supplied discount factor.

    Some useful MDP methods you will use:
        mdp.getStates()
        mdp.getPossibleActions(state)
        mdp.getTransitionStatesAndProbs(state, action)
        mdp.getReward(state, action, nextState)
        mdp.isTerminal(state)
    """
    q_table = defaultdict(lambda: defaultdict(float))  # dict of dicts with default 0
    v_table = defaultdict(float)

    for k in range(iterations):
        ### YOUR CODE ###
        for state in mdp.getStates():
            if(mdp.isTerminal(state)):
                v_table[state]=0
            else:
                v_table[state] = -np.Inf
                for action in mdp.getPossibleActions(state):
                    v_table[state] = max(v_table[state], q_table[state][action])
    
        for state in mdp.getStates():
            for action in mdp.getPossibleActions(state):
                q=0
                for successor, probability in mdp.getTransitionStatesAndProbs(state, action):
                    r=mdp.getReward(state, action, successor)
                    q+= probability*(r+discount*v_table[successor])
                q_table[state][action] = q

    return q_table
