import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
import seaborn as sns
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, LabelEncoder
import tensorflow as tf
from tensorflow.python.keras import Sequential
from tensorflow.python.keras.layers import Dense, Dropout



#Telling the info of the column name
def feat_info(col_name):
    print(data_info.loc[col_name]['Description'])

data_info = pd.read_csv('lending_club_info.csv',index_col='LoanStatNew')
print(data_info.loc['revol_util']['Description'])

feat_info('mort_acc')       #What is it

df = pd.read_csv('lending_club_loan_two.csv')
print(df.head())

#Visualization
sns.countplot(data= df, x='loan_status', color='orange')
plt.figure(figsize=(12,4))
sns.displot(df['loan_amnt'],kde=False,bins=40)
plt.xlim(0,45000)

#describe
feat_info('installment')
feat_info('loan_amnt')

plt.figure()
sns.scatterplot(x='installment',y='loan_amnt',data=df,)

print(df.groupby('loan_status')['loan_amnt'].describe())

#F and G loans dont get paid
plt.figure(figsize=(12,4))
subgrade_order = sorted(df['sub_grade'].unique())
sns.countplot(x='sub_grade',data=df,order = subgrade_order,palette='coolwarm' )

#Represent the f and g loans
f_and_g = df[(df['grade']=='G') | (df['grade']=='F')]

plt.figure(figsize=(12,4))
subgrade_order = sorted(f_and_g['sub_grade'].unique())
sns.countplot(x='sub_grade',data=f_and_g,order = subgrade_order,hue='loan_status')

#Create a new column called 'load_repaid' which will
#contain a 1 if the loan status was "Fully Paid" and a 0 if it was "Charged Off".

df['loan_repaid'] = df['loan_status'].map({'Fully Paid':1,'Charged Off':0})
print(df[['loan_repaid','loan_status']])

#Data PreProcessing

df.head()
print(df.isnull().sum())

#make it percentages for importance

print(100* (df.isnull().sum())/len(df)) #emp_title , emp_length, mort_acc most significant

print(df['emp_title'].nunique())
print("\nValue Counts ")
print(df['emp_title'].value_counts())

df = df.drop('emp_title',axis=1)

emp_length_order = [ '< 1 year',
                     '1 year',
                     '2 years',
                     '3 years',
                     '4 years',
                     '5 years',
                     '6 years',
                     '7 years',
                     '8 years',
                     '9 years',
                     '10+ years']

plt.figure(figsize=(12,4))
sns.countplot(x='emp_length',data=df,order=emp_length_order)
plt.figure(figsize=(12,4))
sns.countplot(x='emp_length',data=df,order=emp_length_order,hue='loan_status')

#percantage of people that dont pay back their loans by category of years working
emp_co = df[df['loan_status']=="Charged Off"].groupby("emp_length").count()['loan_status']
emp_fp = df[df['loan_status']=="Fully Paid"].groupby("emp_length").count()['loan_status']
emp_len = emp_co/(emp_co +emp_fp)
plt.figure()
emp_len.plot(kind='bar')

df.drop('emp_length', axis=1)


df['mort_acc'].value_counts()
print("\n\n\n")


df = df.drop('revol_util', axis=1)
df = df.drop('pub_rec_bankruptcies', axis=1)

print(df.isnull().sum())



#CHANGING THE PROBLEM WITH STRING 36 MONTHS
df['term'] = df['term'].apply(lambda term: int(term[:3]))



print("Mean of mort_acc column per total_acc")
print(df.groupby('total_acc').mean(numeric_only=True)['mort_acc'])
total_acc_avg = df.groupby('total_acc').mean(numeric_only=True)['mort_acc']

def fill_mort_acc(total_acc, mort_acc):
    '''
    Accepts the total_acc and mort_acc values for the row.
    Checks if the mort_acc is NaN , if so, it returns the avg mort_acc value
    for the corresponding total_acc value for that row.

    total_acc_avg here should be a Series or dictionary containing the mapping of the
    groupby averages of mort_acc per total_acc values.
    '''
    if np.isnan(mort_acc):
        return total_acc_avg[total_acc]
    else:
        return mort_acc

print(df.isnull().sum())
df['mort_acc'] = df.apply(lambda x: fill_mort_acc(x['total_acc'], x['mort_acc']), axis=1)
print("\n\n")
print(df.isnull().sum())

#DEALING WITH CATEGORICAL DATA
print(df.select_dtypes(['object']).columns)
print(df['term'].value_counts())
df = df.drop('grade',axis=1)

# CONVERT SUBGRADE INTO DUMMY, DROP THE ORIGINAL SUBGRADE, CONCAT THE NEW TO DF

subgrade_dummies = pd.get_dummies(df['sub_grade'],drop_first=True)
df = pd.concat([df.drop('sub_grade',axis=1),subgrade_dummies],axis=1)

#THE SAME FOR THE BELOW COLS

dummies = pd.get_dummies(df[['verification_status', 'application_type', 'initial_list_status', 'purpose']], drop_first=True)
df = df.drop(['verification_status', 'application_type', 'initial_list_status', 'purpose'], axis=1)
df = pd.concat([df, dummies],axis=1)

#PLACE THE NONE,ANY AND OTHER TOGETHER IN THE HOME_OWNERSHIP COL

df['home_ownership']= df['home_ownership'].replace(['NONE', 'ANY'], 'OTHER')
dummies = pd.get_dummies(df['home_ownership'],drop_first=True)
df = df.drop('home_ownership',axis=1)
df = pd.concat([df,dummies],axis=1)

#TAKE THE ZIP CODE AND MAKE DUMMY
df['zip_code'] = df['address'].apply(lambda address:address[-5:])
dummies = pd.get_dummies(df['zip_code'],drop_first=True)
df = df.drop(['zip_code','address'],axis=1)
df = pd.concat([df,dummies],axis=1)

#DOESNT MATTER
df = df.drop('issue_d',axis=1)

#TAKE THE LAST 4 OF THE COL , I DONT WANT TO MAKE THEM DUMMY BECAUSE THE DATE CAN BE USED AS DATA TYPE
df['earliest_cr_year'] = df['earliest_cr_line'].apply(lambda date:int(date[-4:]))
df = df.drop('earliest_cr_line',axis=1)
df = df.drop('loan_status',axis=1)

# TRAIN THE MODEL

X = df.drop('loan_repaid', axis=1).values
y = df['loan_repaid'].values

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=101)

#NORMALIZING DATA

scaler = MinMaxScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

#Creating NEURONS Model

model = Sequential()

# input layer
model.add(Dense(78,  activation='relu'))
model.add(Dropout(0.2))

# hidden layer
model.add(Dense(39, activation='relu'))
model.add(Dropout(0.2))

# hidden layer
model.add(Dense(19, activation='relu'))
model.add(Dropout(0.2))

# output layer
model.add(Dense(units=1,activation='sigmoid'))

# Compile model
model.compile(loss='binary_crossentropy', optimizer='adam')

model.fit(x=X_train,
          y=y_train,
          epochs=25,
          batch_size=256,
          validation_data=(X_test, y_test),
          )


#Evaluating Model Performance

losses = pd.DataFrame(model.history.history)
losses[['loss','val_loss']].plot() # loss is training dataset and val_loss is test set


predictions = model.predict_classes(X_test)
print(classification_report(y_test,predictions))
print(confusion_matrix(y_test,predictions))




plt.show()