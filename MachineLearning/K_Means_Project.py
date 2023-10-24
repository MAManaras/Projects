import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn import metrics
from sklearn.cluster import KMeans
from sklearn.metrics import confusion_matrix,classification_report

df = pd.read_csv('College_Data',index_col=0)
print(df.head())

sns.lmplot(x = 'Room.Board', y= 'Grad.Rate', data=df, hue='Private', palette='coolwarm',aspect=1,fit_reg=False)

#Private vs Public schools grad rates
sns.set_style('darkgrid')
g = sns.FacetGrid(df, hue="Private", palette='coolwarm', aspect=2)
g = g.map(plt.hist,'Grad.Rate',bins=20,alpha=0.7)

#Why there is a number more than 100?
print(df[df['Grad.Rate'] > 100])

#Lets set it to 100
df['Grad.Rate']['Cazenovia College'] = 100

#Test it
print(df[df['Grad.Rate'] > 100])

#Clustering
kmeans = KMeans(n_clusters=2) # 2 groups public private
kmeans.fit(df.drop('Private',axis=1))
print(kmeans.cluster_centers_)

def converter(private):
    if private =='Yes':
        return 1
    else:
        return 0

df['Cluster'] = df['Private'].apply(converter)

print(confusion_matrix(df['Cluster'], kmeans.labels_))
print('\n')
print(classification_report(df['Cluster'], kmeans.labels_))














plt.show()