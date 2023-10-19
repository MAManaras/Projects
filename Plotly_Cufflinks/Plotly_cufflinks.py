import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
from plotly import __version__
from plotly.offline import download_plotlyjs, init_notebook_mode, plot, iplot
import cufflinks as cf

import plotly.graph_objs as go




df = pd.DataFrame(np.random.randn(100,4), columns= 'A B C D'.split())
df2 = pd.DataFrame({'Category':['A','B','C'],'Values':[32,43,50]})

#INTERACTIVE PLOT
#df.iplot(kind='scatter',x='A',y='B',mode='markers',size=10)

df3 = pd.DataFrame({'x':[1,2,3,4,5],'y':[10,20,30,20,10],'z':[5,4,3,2,1]})
df3.iplot(kind='surface',colorscale='ylorbr')


plt.show()
