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

results = './results'
import os


import os

data = []

labels = []
files = os.listdir(".")
for f in files:
    if f.endswith("python_array.txt"):
        content = open("./" + f, 'rb')
        evaluation = eval(content.read())
        data += [evaluation['data']]
        labels += [evaluation['label']]

perc_data = normalize_data(data)


fig, ax2 = plt.subplots(nrows=1, ncols=1, figsize=(10, 10), sharey=True)

ax2.set_title('Customized violin plot')
parts = ax2.violinplot(
        data,vert=False, showmeans=False, showmedians=False,
        widths=0.2,
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
set_axis_style(ax2, labels)

plt.subplots_adjust(bottom=0.15, wspace=0.05)
plt.show()