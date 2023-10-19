import pandas as pd
import numpy as np
from numpy.random import randn
np.random.seed(101)

df = pd.DataFrame(randn(5, 4), ['A','B', 'C', 'D', 'E'], ['W', 'X', 'Y', 'Z'])



print(df[df['W']>0][['Y','Z']])     # row C out and keep columns Y,Z

#MUKLTIPLE CONDITIONS ONLY WITH & and not working
print(df[(df['W']> 0) & (df['Y'] > 1)])





