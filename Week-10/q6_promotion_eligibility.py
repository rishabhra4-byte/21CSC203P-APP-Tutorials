"""
Tutorial 10 - Question 6  (Logic programming with kanren)
Promotion conditions:
  - Minimum five years of experience
  - Completion of mandatory training
  - Performance rating of at least 4
"""

from kanren import Relation, facts, run, var, lall, membero

# a) Employee information as logical facts
employees = [
    # (Employee, Experience in years, Training, Rating)
    ("Ravi",  6, "Completed",     5),
    ("Priya", 3, "Completed",     4),
    ("Kumar", 8, "Not Completed", 5),
    ("Meena", 7, "Completed",     4),
]

experience = Relation()
training = Relation()
rating = Relation()
facts(experience, *[(e, yrs) for e, yrs, _, _ in employees])
facts(training,   *[(e, t)   for e, _, t, _ in employees])
facts(rating,     *[(e, r)   for e, _, _, r in employees])

# Allowed values used by the rule (relational way of writing ">= 5" and ">= 4")
MIN_EXPERIENCE = list(range(5, 41))   # 5 .. 40 years
GOOD_RATING = [4, 5]                  # rating scale 1 .. 5


# b) Rule:
# promotable(E) :- experience(E, Y), Y >= 5,
#                  training(E, completed),
#                  rating(E, R), R >= 4.
def promotable(emp):
    y, r = var(), var()
    return lall(
        experience(emp, y), membero(y, MIN_EXPERIENCE),
        training(emp, "Completed"),
        rating(emp, r), membero(r, GOOD_RATING),
    )


print("a) Facts:")
for e, yrs, t, r in employees:
    print(f"   experience({e}, {yrs}).  training({e}, '{t}').  rating({e}, {r}).")

print("\nb) Rule:")
print("   promotable(E) :- experience(E, Y), Y >= 5, training(E, 'Completed'), rating(E, R), R >= 4.")

# c) Query: employees eligible for promotion
e = var()
eligible = sorted(run(0, e, promotable(e)))
print("\nc) Query: ?- promotable(E).")
print("   Employees eligible for promotion:", eligible)

print("\n   Reasoning for each employee:")
for name, yrs, t, r in employees:
    ok = bool(run(1, var(), promotable(name)))
    reasons = []
    if yrs < 5:
        reasons.append("experience below 5 years")
    if t != "Completed":
        reasons.append("training not completed")
    if r < 4:
        reasons.append("rating below 4")
    print(f"   {name:<6} -> {'Eligible' if ok else 'Not eligible (' + ', '.join(reasons) + ')'}")
