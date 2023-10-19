import pandas as pd
import numpy as np
from numpy.random import randn
np.random.seed(101)


outside = ['G1','G1','G1','G2','G2','G2']
inside = [1,2,3,1,2,3]
hier_index = list(zip(outside,inside))
hier_index = pd.MultiIndex.from_tuples(hier_index)

df = pd.DataFrame(np.random.randn(6,2),index=hier_index,columns=['A','B'])
print(df)

print(df.loc['G1'])
# apo e3w pros ta mesa epilegw tin timi tou B
print(df.loc['G1'].loc[1]['B'])
# me to xs epilegw eswterika, dialegw to index 1 apo opoia stili thelw kia mou ta fernei ola
#print(df.xs(1,level='Num'))


