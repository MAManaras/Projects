import matplotlib.pyplot as plt
import numpy as np



x = np.linspace(0, 5, 11)
y = x ** 2
"""""
plt.plot(x, y, 'r')                         # 'r' is the color red
plt.xlabel('X Label')
plt.ylabel('Y Label')
plt.title('Nice Plot')
"""""
# object oriented method for matplots

fig = plt.figure()
# Axes 1
axes = fig.add_axes([0.1, 0.1, 0.8, 0.8])
axes.plot(x, x**2, label = 'Xsquare')
axes.set_xlabel('X Label')
axes.set_ylabel('Y Label')
axes.set_title('Nice Plot')

# Axes 2
axes2 = fig.add_axes([0.58,0.19, 0.3, 0.2])
axes2.plot(y, x , 'r-*')
axes2.set_xlabel('X Label')
axes2.set_ylabel('Y Label')
axes2.set_title('New Plot')

# Print the labels under the functions
axes.legend()

# Subplots iteration

fig, axes = plt.subplots(nrows=2, ncols=3)          # CAUTION! axes is two dimensional list!!!!
for ax1 in range(2):
    for ax2 in range(3):
        axes[ax1,ax2].plot(x, y, 'b')

fig.savefig("filename.png", dpi=200)        #save the plot in a file type

# i can zoom in a specific part of the plot with
"""""
axes.set_xlim([0,1])
axes.set_ylim([0,2])
"""""

plt.tight_layout()
plt.show()




























