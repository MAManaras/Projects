import seaborn as sns
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns


tips = sns.load_dataset('tips')
tips.head()

sns.barplot(x='sex',y='total_bill',data=tips,estimator=np.std)

sns.boxplot(x="day", y="total_bill", hue="smoker",data=tips, palette="coolwarm")

sns.violinplot(x="day", y="total_bill", data=tips,hue='sex',split=True,palette='Set1')


plt.show()






