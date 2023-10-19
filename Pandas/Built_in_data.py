import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

df1 = pd.read_csv('df1', index_col=0)
df2 = pd.read_csv('df2')

df1['A'].hist()
print(df2.head())

# i can use bar, line, hist, area, scatter,  barh, density, box, kde, pie
df2.plot.bar(stacked = True)

plt.show()

