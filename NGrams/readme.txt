An N-gram is a sequence of N consecutive characters from a given word. For the word "pilot" there are three 3-grams: "pil", "ilo" and "lot".

Given a set of words and an n-gram length:

Write a function that finds the n-gram that is the most frequent one among all the words

if there are multiple n-grams having the same maximum frequency please print the one that is the smallest lexicographically (the first one according to the dictionary sorting order)


For example,
text: “aaaab a0a baaab c”
ngramLength: 3


The output should be: aaa
