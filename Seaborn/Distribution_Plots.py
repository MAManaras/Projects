import matplotlib.pyplot as plt
import seaborn as sns


tips = sns.load_dataset('tips')     #ready dataset in seaborn
print(tips.head())

#sns.distplot(tips['total_bill'])   #with histogram
sns.kdeplot(tips['total_bill'])     # only kdeplot, ESTIMATION PLOT!

# Compare plots by x and y
sns.jointplot(x='total_bill',y='tip',data=tips,kind='scatter')      #we have different type of kinds
sns.jointplot(x='total_bill',y='tip',data=tips,kind='reg')

#Making all pairs of the dataset file
sns.pairplot(tips,hue='sex')      # i can use hue for categorical data and make colors for what i need



plt.show()