import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import classification_report,confusion_matrix

df = pd.read_csv('KNN_Project_Data')
print(df.head())

#sns.pairplot(df,hue='TARGET CLASS',palette='coolwarm')

#Standarize
scaler = StandardScaler()
scaler.fit(df.drop('TARGET CLASS',axis=1))
scaled_features = scaler.transform(df.drop('TARGET CLASS',axis=1))

#Convert the scaled features to a dataframe
df_feat = pd.DataFrame(scaled_features,columns=df.columns[:-1])
print(df_feat.head())

#Train test
X = df_feat
y = df['TARGET CLASS']
X_train, X_test, y_train, y_test = train_test_split(scaled_features,df['TARGET CLASS'], test_size=0.30, random_state=101)

#KNN
knn = KNeighborsClassifier(n_neighbors=1)
knn.fit(X_train,y_train)

#Predictions
pred = knn.predict(X_test)
print(confusion_matrix(y_test,pred))
print(classification_report(y_test,pred))

#Pick good value
error_rate = []
for i in range(1, 60):
    knn = KNeighborsClassifier(n_neighbors=i)
    knn.fit(X_train, y_train)
    pred_i = knn.predict(X_test)
    error_rate.append(np.mean(pred_i != y_test))

#Creating figure with error rates  !Hyperparameter Tuning!
plt.figure(figsize=(10,6))
plt.plot(range(1,60),error_rate,color='blue', linestyle='dashed', marker='o',
         markerfacecolor='red', markersize=10)
plt.title('Error Rate vs. K Value')
plt.xlabel('K')
plt.ylabel('Error Rate')

#Retrain with new K Value
knn = KNeighborsClassifier(n_neighbors=31)  #87% precision achieved
knn.fit(X_train, y_train)
pred = knn.predict(X_test)
print('WITH K=30')
print('\n')
print(confusion_matrix(y_test,pred))
print('\n')
print(classification_report(y_test,pred))


plt.show()