"""
Tutorial 10 - Question 5  (Logic programming with kanren)
A student may register for the Advanced Programming course only if they
have passed BOTH Python Programming and Data Structures.
"""

from kanren import Relation, facts, run, var, lall

# a) Represent the student results as logical facts
#    result(Student, Subject, Status)
records = [
    ("Arun",   "Python", "Pass"), ("Arun",   "DataStructures", "Pass"),
    ("Bala",   "Python", "Pass"), ("Bala",   "DataStructures", "Fail"),
    ("Charan", "Python", "Pass"), ("Charan", "DataStructures", "Pass"),
    ("Divya",  "Python", "Fail"), ("Divya",  "DataStructures", "Pass"),
]
result = Relation()
facts(result, *records)


# b) Rule: eligible(S) :- result(S, Python, Pass), result(S, DataStructures, Pass).
def eligible(student):
    return lall(
        result(student, "Python", "Pass"),
        result(student, "DataStructures", "Pass"),
    )


print("a) Facts:")
for student, subject, status in records:
    print(f"   result({student}, {subject}, {status}).")

print("\nb) Rule:")
print("   eligible(S) :- result(S, Python, Pass), result(S, DataStructures, Pass).")

# c) Query: all eligible students
s = var()
eligible_students = sorted(run(0, s, eligible(s)))
print("\nc) Query: ?- eligible(S).")
print("   Eligible students:", eligible_students)

print("\n   Individual checks:")
for name in ["Arun", "Bala", "Charan", "Divya"]:
    ok = bool(run(1, var(), eligible(name)))
    print(f"   ?- eligible({name}).  ->  {'Yes' if ok else 'No'}")
