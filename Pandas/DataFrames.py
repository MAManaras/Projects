import pandas as pd
import numpy as np
from numpy.random import randn
np.random.seed(101)

df = pd.DataFrame(randn(5, 4), ['A','B', 'C', 'D', 'E'], ['W', 'X', 'Y', 'Z'])

print(df)

print(df['W'])

# Pass a list of column names
print(df[['W','Z']])

#Creating a new column:
df['new'] = df['W'] + df['Y']
print(df)

#Removing Columns  axis= 0 drop rows
df.drop('new',axis=1,inplace=True)      # xreiazomai to inplac giati alliws den svinetai pliroforia gia na min xathei kati katalathos

# Return row
print(df.loc['A'])


print(df.loc[['A','B'],['W','Y']])







