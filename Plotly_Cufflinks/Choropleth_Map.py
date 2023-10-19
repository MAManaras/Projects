#from chart_studio import plotly
import matplotlib.pyplot as plt
import plotly.graph_objs as go
from plotly.offline import download_plotlyjs, init_notebook_mode, plot, iplot
import pandas as pd

""""
# USA map in html
data = dict(type = 'choropleth',
            locations = ['AZ','CA','NY'],
            locationmode = 'USA-states',
            colorscale= 'Portland',
            text= ['Arizona','California','New York'],
            z=[1.0,2.0,3.0],        #colorbar representing
            colorbar = {'title':'Colorbar Title'})

layout = dict(geo = {'scope':'usa'})
choromap = go.Figure(data = [data],layout = layout)
iplot(choromap)
"""""

df = pd.read_csv('2011_US_AGRI_Exports')
df.head()

data = dict(type='choropleth',
            colorscale = 'ylorbr',
            locations = df['code'],
            z = df['total exports'],
            locationmode = 'USA-states',
            text = df['text'],
            marker = dict(line = dict(color = 'rgb(255,255,255)',width = 2)),
            colorbar = {'title':"Millions USD"}
            )

layout = dict(title = '2011 US Agriculture Exports by State',
              geo = dict(scope='usa',
                         showlakes = True,
                         lakecolor = 'rgb(85,173,240)') #nice blue color for lakes
             )

choromap = go.Figure(data = [data],layout = layout)
iplot(choromap)




print(data)
plt.show()







