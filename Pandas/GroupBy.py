import pandas as pd


# Create dictionary

d = {'Company':['GOOG','GOOG','MSFT','MSFT','FB','FB'],
       'Person':['Sam','Charlie','Amy','Vanessa','Carl','Sarah'],
       'Sales':[200,120,340,124,243,350]}

# Passing into dataframe
df = pd.DataFrame(d)
print(df)
by_comp = df.groupby('Company').sum()
print(by_comp)

print(df.groupby('Company').describe().transpose())            # kanw tis etaireies apo line column






