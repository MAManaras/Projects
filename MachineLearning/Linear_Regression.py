#from sklearn.family import Model
from sklearn.linear_model import LinearRegression
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn import metrics

df = pd.read_csv('USA_Housing.csv')

sns.pairplot(df)
#plt.figure()
print("Debug")
#sns.distplot(df['Price'])
# Training model
X = df[['Avg. Area Income', 'Avg. Area House Age', 'Avg. Area Number of Rooms', 'Avg. Area Number of Bedrooms', 'Area Population']]
y = df['Price']

print("Debug")
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.4, random_state=101)
print("Debug")
lm = LinearRegression()     #create object

lm.fit(X_train,y_train)     #fit model in training data
print(lm.coef_)
coeff_df = pd.DataFrame(lm.coef_, X.columns, columns=['Coefficient'])
print(coeff_df)

#Predictions from our Model.
plt.figure()
predictions = lm.predict(X_test)
plt.scatter(y_test,predictions)

#Regression Evaluation Metrics

print('MAE:', metrics.mean_absolute_error(y_test, predictions))
print('MSE:', metrics.mean_squared_error(y_test, predictions))
print('RMSE:', np.sqrt(metrics.mean_squared_error(y_test, predictions)))



plt.show()