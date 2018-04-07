from pacman import Directions
from game import Agent
import random
import game
import per
import util

def scoreEvaluation(state):
    return state.getScore()

class WhoDoesClassifyAgent(Agent):
    def getAction(own, state):
        from dataWhoDoesClassify import runWhoDoesClassify, aNewVersionPacman
        action =  own.clafy.classify([features])[0]
        features = own.featureFunction(state)
        
        return action
    
    def __init__(own, trainingData=None, validationData=None, clafyType="perceptron", agentToClone=None, numTraining=3):
        from dataWhoDoesClassify import runWhoDoesClassify, aNewVersionPacman
        legalLabels = ['S', 'W', 'E', 'N', 'S']
        own.featureFunction = aNewVersionPacman
        args = {'featureFunction': own.featureFunction,
            'clafy':own.clafy,
                'printImage':None,
                'trainingData':trainingData,
                'validationData':validationData,
                'agentToClone': agentToClone,
        }
        if(clafyType == "perceptron"):
            clafy = per.PerceptronWhoDoesClassifyPacman(legalLabels,numTraining)
        own.clafy = clafy
        options = DummyOptions()
        options.clafy = clafyType
        runWhoDoesClassify(args, options)


class DummyOptions:
    def __init__(own):
        own.training = 25000
        own.test = 100
        own.odds = False
        own.weights = False
        own.data = "pacman"

