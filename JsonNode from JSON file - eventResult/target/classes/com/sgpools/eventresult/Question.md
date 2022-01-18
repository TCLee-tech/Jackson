Write a simple function to print out the outcome(s) of all selections within an EventResult object. In this context, outcome refers to whether the selection is “win”, “loss” or “draw”.
Please refer to the following to answer the questions below.

(a) For market with the name of “Total Goals Over/Under”, if the sum of total scores (Home 
+ Away) for that event is greater or equals to the market’s handicap value, selection with 
selectionCode=”H” will be winning, selections with other selectionCode will be losing. If 
the sum of total scores for that event is smaller than the market’s handicap value, 
selection with selectionCode=”L” will be winning, selections with other selectionCode 
will be losing.

(b) For market with the name of “Half Goal”, if the difference between home score and 
away score plus the market’s handicap value (Home – Away + Handicap Value) is greater 
than 0, selection with selectionCode=”H” will be winning, selections with other 
selectionCode will be losing. If the difference between home score and away score plus 
market’s handicap value is smaller than 0, selection with selectionCode=”A” will be the 
winning outcome, selections with other selectionCode will be losing. If the difference 
between home score and away score plus market’s handicap value is equals to 0, 
selection with selectionCode=”L” will be the winning, selections with other 
selectionCode will be losing. 

(c) Handling of “Asian Handicap” market will be different. Due to legacy system limitation, 
the market’s handicap value stated needs to be divided by 4 before using it. Nature of 
“Asian Handicap” also requires the splitting of handicap value into 2 parts if the 
handicap value ends with “.25” or “.75”. There is no need to split the handicap value if it 
ends with “0” or “.5”.

For example, if the handicap value is within (0.25, 0.75, 1.25, 1.75, 2.25, 2.75 etc.). We 
will need to split the handicap value into 2 parts by adding and subtracting 0.25 to itself 
(handicap value/4).

Split the handicap value into 2 parts:
If handicap value is 0.25,
1
st part of handicap value is 0
2
nd part of handicap value is 0.5
If handicap value is 1.75,
1
st part of handicap value is 1.5
2
nd part of handicap value is 2

Compare individual parts of handicap value with each selection to derive the outcome 
(“win”, “loss” or “draw”). If the difference between home score and away score plus the 
market’s handicap value (Home – Away + Handicap Value) is greater than 0, selection 
with selectionCode=”H” will be winning, selections with other selectionCode will be 
losing. If the difference between home score and away score plus the market’s handicap 
value is smaller than 0, selection with selectionCode=”A” will be winning, selections with 
other selectionCode will be losing. If the difference between home score and away score 
plus the market’s handicap value is equals to 0, all selections will be “draw”. 
And yes, if the handicap value requires splitting, there will be more than 1 outcome for 
each selection.