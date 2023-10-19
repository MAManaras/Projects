import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
sns.set_style('whitegrid')


df = pd.read_csv('911.csv')

df.head(3)

df['zip'].value_counts().head(5)
df['title'].nunique()

df['Reason'] = df['title'].apply(lambda title: title.split(':')[0])

sns.countplot(x = 'Reason', data= df)

df['timeStamp'] = pd.to_datetime(df['timeStamp'])

df['Hour'] = df['timeStamp'].apply(lambda time: time.hour)
df['Month'] = df['timeStamp'].apply(lambda time: time.month)
df['Day of Week'] = df['timeStamp'].apply(lambda time: time.dayofweek)

dmap = {0:'Mon',1:'Tue',2:'Wed',3:'Thu',4:'Fri',5:'Sat',6:'Sun'}
df['Day of Week'] = df['Day of Week'].map(dmap)

#countplot of the Day of Week
sns.countplot(x='Day of Week',data=df,hue='Reason',palette='viridis')
# To relocate the legend
plt.legend(bbox_to_anchor=(1.05, 1), loc=2, borderaxespad=0.)

df['Date'] = df['timeStamp'].apply(lambda t: t.date())
print(df['Date'])
print(df.head())
plt.figure()
df.groupby('Date').count()['lat'].plot()
plt.tight_layout()


plt.show()