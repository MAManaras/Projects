import pandas as pd

df = pd.DataFrame({'col1':[1,2,3,4],'col2':[444,555,666,444],'col3':['abc','def','ghi','xyz']})

print(df)
print("")
print(df['col2'].nunique())     # returns the number of the uniques values

#Select from DataFrame using criteria from multiple columns
newdf = df[(df['col1']>2) & (df['col2']==444)]

print("")
print(newdf)
print("")

def times2(x):
    return x*2

print(df['col1'].apply(times2)) # or print(df['col1'].apply(lambda x: x*2))

#PIVOT TABLE

data = {'A':['foo','foo','foo','bar','bar','bar'],
     'B':['one','one','two','two','one','one'],
       'C':['x','y','x','y','x','y'],
       'D':[1,3,2,5,4,1]}

df = pd.DataFrame(data)
print(df)
print(" ")
print(df.pivot_table(values='D',index=['A', 'B'],columns=['C']))
























