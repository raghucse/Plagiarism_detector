from pacman import Directions
from game import Agent

import random
import game
import util

class DummyOptions:
    def __init__(own):
        own.data = "pacman"
        own.training = 25000
        own.test = 100
        own.odds = False
        own.weights = False


import per

class WhoDoesClassifyAgent(Agent):
    def __init__(own, trainingData=None, validationData=None, clafyType="perceptron", agentToClone=None, numTraining=3):
        from dataWhoDoesClassify import runWhoDoesClassify, aNewVersionPacman
        legalLabels = ['S', 'W', 'E', 'N', 'S']
        if(clafyType == "perceptron"):
            clafy = per.PerceptronWhoDoesClassifyPacman(legalLabels,numTraining)
        own.clafy = clafy
        own.featureFunction = aNewVersionPacman
        args = {'featureFunction': own.featureFunction,
                'clafy':own.clafy,
                'printImage':None,
                'trainingData':trainingData,
                'validationData':validationData,
                'agentToClone': agentToClone,
        }
        options = DummyOptions()
        options.clafy = clafyType
        runWhoDoesClassify(args, options)
    def getAction(own, state):
        from dataWhoDoesClassify import runWhoDoesClassify, aNewVersionPacman
        features = own.featureFunction(state)
        
        action =  own.clafy.classify([features])[0]

        return action

def scoreEvaluation(state):
    return state.getScore()
