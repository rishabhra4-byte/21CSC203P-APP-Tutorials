"""
Tutorial 10 - Question 1
A college maintains the marks obtained by students in a programming examination.
Functional programming concepts: filter(), map(), reduce() and lambda.
"""

from functools import reduce

# Marks obtained by students (name, marks)
students = [
    ("Arun", 78),
    ("Bala", 45),
    ("Charan", 97),
    ("Divya", 50),
    ("Esther", 32),
    ("Farhan", 88),
    ("Gayathri", 61),
]

print("Original marks:")
for name, mark in students:
    print(f"  {name:<10} {mark}")

# a) Students who scored 50 marks or above using filter()
passed = list(filter(lambda s: s[1] >= 50, students))
print("\na) Students who scored 50 or above (filter):")
for name, mark in passed:
    print(f"  {name:<10} {mark}")

# b) Award 5 grace marks to every student using map(),
#    making sure the maximum mark does not exceed 100
with_grace = list(map(lambda s: (s[0], min(s[1] + 5, 100)), students))
print("\nb) Marks after adding 5 grace marks (map, capped at 100):")
for (name, old), (_, new) in zip(students, with_grace):
    print(f"  {name:<10} {old:>3} -> {new}")

# c) Total marks using reduce()
total_original = reduce(lambda acc, s: acc + s[1], students, 0)
total_with_grace = reduce(lambda acc, s: acc + s[1], with_grace, 0)
print(f"\nc) Total marks (reduce): original = {total_original}, after grace = {total_with_grace}")

# d) How lambda functions simplify the implementation
print("""
d) Why lambda functions help:
  - The small rules (mark >= 50, add 5 but cap at 100, running sum) are written
    inline as one-line anonymous functions, so no separate named functions are needed.
  - They plug directly into filter(), map() and reduce(), keeping each step to a single line.
  - The code describes WHAT is done to the data rather than HOW to loop over it,
    and the original list is never modified.""")
