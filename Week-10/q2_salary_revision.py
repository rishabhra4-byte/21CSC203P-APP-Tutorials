"""
Tutorial 10 - Question 2
A company gives a 10% salary increment to employees earning below Rs.40,000.
Functional programming with map(), filter(), reduce() and lambda.
"""

from functools import reduce

employees = [
    ("Ravi", 35000),
    ("Priya", 52000),
    ("Kumar", 28000),
    ("Meena", 40000),
    ("Sathish", 39500),
    ("Anitha", 61000),
]

print("Current salaries:")
for name, sal in employees:
    print(f"  {name:<8} Rs.{sal:,}")

# b) Employees eligible for the increment (filter)
eligible = list(filter(lambda e: e[1] < 40000, employees))
print("\nb) Eligible for 10% increment (salary < Rs.40,000):")
for name, sal in eligible:
    print(f"  {name:<8} Rs.{sal:,}")

# a) Revised salaries using map() and lambda
revised = list(map(lambda e: (e[0], round(e[1] * 1.10, 2) if e[1] < 40000 else e[1]), employees))
print("\na) Revised salaries (map + lambda):")
for (name, old), (_, new) in zip(employees, revised):
    tag = "  (+10%)" if new != old else ""
    print(f"  {name:<8} Rs.{old:>9,.2f} -> Rs.{new:>9,.2f}{tag}")

# c) Total salary expenditure after revision
total_before = reduce(lambda acc, e: acc + e[1], employees, 0)
total_after = reduce(lambda acc, e: acc + e[1], revised, 0)
print(f"\nc) Total expenditure before revision: Rs.{total_before:,.2f}")
print(f"   Total expenditure after revision : Rs.{total_after:,.2f}")
print(f"   Increase                         : Rs.{total_after - total_before:,.2f}")

# d) Why functional programming suits salary processing
print("""
d) Why functional programming suits salary records:
  - Salary data is sensitive; map() returns a NEW list, so the original records stay
    unchanged and can be audited or compared later.
  - Each rule (eligibility, increment, total) is a small independent function that is
    easy to test and change, e.g. changing 10% to 12% touches one lambda.
  - No shared mutable state means fewer bugs and the same input always gives the same output.""")
