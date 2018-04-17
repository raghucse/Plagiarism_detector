import game
import util
import perceptron_pacman
import random
from pacman import Directions
from game import Agent

def scoreEvaluation(state):
    return state.getScore()

class ClassifierAgent(Agent):
    
    def getAction(self, state):
        features = self.featureFunction(state)
        action =  self.classifier.classify([features])[0]
        from dataClassifier import runClassifier, enhancedFeatureExtractorPacman
        
        return action
    
    def __init__(self, trainingData=None, validationData=None, classifierType="perceptron", agentToClone=None, numTraining=3):
        self.classifier = classifier
        self.featureFunction = enhancedFeatureExtractorPacman
        args = {'featureFunction': self.featureFunction,
            'classifier':self.classifier,
                'printImage':None,
                'trainingData':trainingData,
                'validationData':validationData,
                'agentToClone': agentToClone,
        }
        from dataClassifier import runClassifier, enhancedFeatureExtractorPacman
        legalLabels = ['Stop', 'West', 'East', 'North', 'South']
        if(classifierType == "perceptron"):
            classifier = perceptron_pacman.PerceptronClassifierPacman(legalLabels,numTraining)
        options = DummyOptions()
        options.classifier = classifierType
        runClassifier(args, options)


class DummyOptions:
    def __init__(self):
        self.training = 25000
        self.test = 100
        self.odds = False
        self.data = "pacman"
        self.weights = False
