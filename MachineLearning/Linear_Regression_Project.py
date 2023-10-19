import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn import metrics

customers = pd.read_csv("Ecommerce Customers")
print(customers.head())
print(customers.describe())

sns.jointplot(x='Time on Website',y='Yearly Amount Spent',data=customers)

sns.pairplot(customers)
#more detail for length of memebership and amount spent
sns.lmplot(x='Length of Membership',y='Yearly Amount Spent',data=customers)

#Training and Testing Data
y = customers['Yearly Amount Spent']
X = customers[['Avg. Session Length', 'Time on App','Time on Website', 'Length of Membership']]
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=101)

lm = LinearRegression()
lm.fit(X_train,y_train)
print('Coefficients: \n', lm.coef_)

#Predicting Test Data
plt.figure()
predictions = lm.predict(X_test)

#compare predicted with real data
plt.scatter(y_test,predictions)
plt.xlabel('Y Test (True Values)')
plt.ylabel('Predicted Y (Predicted Values')

#Evaluating the Model
print('MAE:', metrics.mean_absolute_error(y_test, predictions))
print('MSE:', metrics.mean_squared_error(y_test, predictions))
print('RMSE:', np.sqrt(metrics.mean_squared_error(y_test, predictions)))

coeffecients = pd.DataFrame(lm.coef_,X.columns)
coeffecients.columns = ['Coeffecient']
print(coeffecients)


plt.show()