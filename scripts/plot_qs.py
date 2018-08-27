import matplotlib.pyplot as plt
import numpy as np


def adjacent_values(vals, q1, q3):
    upper_adjacent_value = q3 + (q3 - q1) * 1.5
    upper_adjacent_value = np.clip(upper_adjacent_value, q3, vals[-1])

    lower_adjacent_value = q1 - (q3 - q1) * 1.5
    lower_adjacent_value = np.clip(lower_adjacent_value, vals[0], q1)
    return lower_adjacent_value, upper_adjacent_value


def set_axis_style(ax, labels):
    ax.get_yaxis().set_tick_params(direction='out')
    ax.xaxis.set_ticks_position('bottom')
    ax.set_yticks(np.arange(1, len(labels) + 1))
    ax.set_yticklabels(labels)
    ax.set_ylim(0.25, len(labels) + 0.75)
    ax.set_ylabel('Sample name')


def normalize_data(data):

    result = [[] for i in range(len(data))]
    mx = len(data[0])

    for c in range(len(data)):
        if(len(data[c]) > mx):
            mx = len(data[c])
    for c in range(len(data)):
        delta = mx - len(data[c])
        result[c] = data[c] + [float('NaN') for i in range(delta)]

    return result

# create test data
np.random.seed(19680801)
data = [
   [0.6521739130434783,0.9347826086956522,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,0.6624365482233503,1.0,1.0,1.0,0.6481927710843374,0.9975903614457832,1.0,1.0,1.0,0.875,0.027472527472527472,0.0,1.0,1.0,0.9891304347826086,1.0,0.3170731707317073,1.0,1.0,0.9021739130434783,0.32,0.9,0.6373626373626373,0.0,0.4230769230769231,0.2087912087912088,0.13186813186813187,0.4010989010989011,0.0,]
   ,[0.03333333333333333,0.03333333333333333,0.03333333333333333,0.06666666666666667,1.0,1.0,1.0,1.0,1.0,1.0,0.8888888888888888,0.9382716049382716,1.0,1.0,0.9382716049382716,0.8888888888888888,0.8888888888888888,0.8888888888888888,0.202880658436214,0.8888888888888888,0.9382716049382716,0.8888888888888888,0.202880658436214,0.8888888888888888,0.8888888888888888,0.8888888888888888,0.202880658436214,0.8888888888888888,0.9382716049382716,0.9629629629629629,1.0,1.0,0.8902439024390244,0.8902439024390244,1.0,0.8888888888888888,0.8888888888888888,0.9382716049382716,0.8888888888888888,0.9008230452674897,0.8888888888888888,0.8888888888888888,0.8888888888888888,0.9382716049382716,0.9382716049382716,1.0,0.8271604938271605,0.9629629629629629,1.0,0.9259259259259259,0.8271604938271605,0.8888888888888888,0.9259259259259259,0.9259259259259259,0.9259259259259259,],

   ]
perc_data = normalize_data(data)


fig, ax2 = plt.subplots(nrows=1, ncols=1, figsize=(10, 10), sharey=True)

ax2.set_title('Customized violin plot')
parts = ax2.violinplot(
        data,vert=False, showmeans=False, showmedians=False,
        showextrema=False)

for pc in parts['bodies']:
    pc.set_facecolor('#D43F3A')
    pc.set_edgecolor('black')
    pc.set_alpha(0.8)


quartile1, medians, quartile3 = np.nanpercentile(perc_data, [25, 50, 75], axis=1)

inds = np.arange(1, len(medians) + 1)

ax2.scatter(medians, inds, marker='o', color='white', s=30, zorder=3)
ax2.hlines(inds, quartile1, quartile3, color='k', linestyle='-', lw=5)
#ax2.hlines(inds, whiskersMin, whiskersMax, color='k', linestyle='-', lw=1)

# set style for the axes
labels = ['Quicksort', "Sudoku"]
set_axis_style(ax2, labels)

plt.subplots_adjust(bottom=0.15, wspace=0.05)
plt.show()