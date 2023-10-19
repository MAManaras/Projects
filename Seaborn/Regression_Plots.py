import matplotlib.pyplot as plt
import seaborn as sns


tips = sns.load_dataset('tips')     #ready dataset in seaborn
print(tips.head())

sns.lmplot(x='total_bill',y='tip',data=tips,hue='sex', col= 'sex')  # i can use row and col for seperation



plt.show()