import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report

ad_data = pd.read_csv('advertising.csv')
print(ad_data.head())

sns.histplot(ad_data["Age"],bins=30)
sns.jointplot(x='Age',y='Area Income',data=ad_data)
#plt.figure()
sns.jointplot(x='Age',y='Daily Time Spent on Site',data=ad_data, color='red', kind='kde')
sns.pairplot(ad_data,hue='Clicked on Ad',palette='bwr')

#Logistic Regression
X = ad_data[['Daily Time Spent on Site', 'Age', 'Area Income','Daily Internet Usage', 'Male']]
y = ad_data['Clicked on Ad']
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size= 0.33, random_state= 101)
logmodel = LogisticRegression(solver='lbfgs', max_iter=250)
logmodel.fit(X_train, y_train)

#Prediction
predictions = logmodel.predict(X_test)
print(classification_report(y_test,predictions))


plt.show()
