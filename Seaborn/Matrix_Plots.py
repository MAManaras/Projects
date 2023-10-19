import seaborn as sns
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

flights = sns.load_dataset('flights')


print(flights.pivot_table(values='passengers',index='month',columns='year'))

pvflights = flights.pivot_table(values='passengers',index='month',columns='year')
sns.heatmap(pvflights, cmap='magma')


plt.show()
