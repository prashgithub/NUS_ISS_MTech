from Orange.data import Domain, Table
from Orange.classification import LogisticRegressionLearner
from Orange.classification import NaiveBayesLearner
from Orange.classification import TreeLearner
from Orange.classification import RandomForestLearner
from Orange.classification import KNNLearner
from Orange.classification import SVMLearner
import sys

### create models ###

models = [
    #LogisticRegressionLearner(),
    #NaiveBayesLearner(),
    #TreeLearner(),
    #RandomForestLearner(),
    KNNLearner(),
    #SVMLearner(),
]

def main():
    print(sys.argv[1])
    print(sys.argv[2])
    predict(sys.argv[1],sys.argv[2])

def predict(train_data_file, input_data_file):
    #train = Table.from_file('train.csv')
    train = Table.from_file(train_data_file)
    # move `sex` from X to Y (from attributes/features to class_var/target)
    domain = Domain(train.domain.attributes[1:], train.domain.attributes[0])
    train = train.transform(domain)

    print('\n=== train.X ===')
    print(train.X)
    print('\n=== train.Y ===')
    print(train.Y)

    ### read predict data ###

    #predict = Table.from_file('predict.csv')
    predict = Table.from_file(input_data_file)


    print('\n=== predict.X ===')
    print(predict.X)

    ### train and predict ###

    print('\n=== results ===')

    class_values = train.domain.class_var.values

    for learner in models:
        # train
        classifier = learner(train)

        # predict
        result = classifier(predict)

        # print result
        result = class_values[int(result[0])]
        print('{:25s}: {}'.format(learner.__class__.__name__, result))
    
if __name__ == "__main__":
    main()