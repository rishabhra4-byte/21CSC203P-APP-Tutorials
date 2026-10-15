# Tutorial 10 — Functional & Logic Programming in Python

**Course:** 21CSC203P – Advanced Programming Practice  
**Tutorial date:** 15-10-2026

- **Q1–Q4:** Functional programming (`map`, `filter`, `reduce`, `lambda`, pure functions)
- **Q5–Q7:** Logic programming with facts, rules and queries using the [`kanren`](https://pypi.org/project/kanren/) library

**Run:**
```bash
pip install kanren        # only needed for Q5–Q7
python q1_student_marks.py
```

## Q1. Student marks – filter(), map(), reduce(), lambda

Students scoring ≥ 50 (filter), +5 grace marks capped at 100 (map), total marks (reduce), and how lambdas simplify the code.

**Code:** [`q1_student_marks.py`](q1_student_marks.py)

**Output:**

```text
Original marks:
  Arun       78
  Bala       45
  Charan     97
  Divya      50
  Esther     32
  Farhan     88
  Gayathri   61

a) Students who scored 50 or above (filter):
  Arun       78
  Charan     97
  Divya      50
  Farhan     88
  Gayathri   61

b) Marks after adding 5 grace marks (map, capped at 100):
  Arun        78 -> 83
  Bala        45 -> 50
  Charan      97 -> 100
  Divya       50 -> 55
  Esther      32 -> 37
  Farhan      88 -> 93
  Gayathri    61 -> 66

c) Total marks (reduce): original = 451, after grace = 484

d) Why lambda functions help:
  - The small rules (mark >= 50, add 5 but cap at 100, running sum) are written
    inline as one-line anonymous functions, so no separate named functions are needed.
  - They plug directly into filter(), map() and reduce(), keeping each step to a single line.
  - The code describes WHAT is done to the data rather than HOW to loop over it,
    and the original list is never modified.
```

## Q2. Salary revision – 10% increment below ₹40,000

Revised salaries with map() + lambda, eligible employees with filter(), total expenditure after revision, and why FP suits salary records.

**Code:** [`q2_salary_revision.py`](q2_salary_revision.py)

**Output:**

```text
Current salaries:
  Ravi     Rs.35,000
  Priya    Rs.52,000
  Kumar    Rs.28,000
  Meena    Rs.40,000
  Sathish  Rs.39,500
  Anitha   Rs.61,000

b) Eligible for 10% increment (salary < Rs.40,000):
  Ravi     Rs.35,000
  Kumar    Rs.28,000
  Sathish  Rs.39,500

a) Revised salaries (map + lambda):
  Ravi     Rs.35,000.00 -> Rs.38,500.00  (+10%)
  Priya    Rs.52,000.00 -> Rs.52,000.00
  Kumar    Rs.28,000.00 -> Rs.30,800.00  (+10%)
  Meena    Rs.40,000.00 -> Rs.40,000.00
  Sathish  Rs.39,500.00 -> Rs.43,450.00  (+10%)
  Anitha   Rs.61,000.00 -> Rs.61,000.00

c) Total expenditure before revision: Rs.255,500.00
   Total expenditure after revision : Rs.265,750.00
   Increase                         : Rs.10,250.00

d) Why functional programming suits salary records:
  - Salary data is sensitive; map() returns a NEW list, so the original records stay
    unchanged and can be audited or compared later.
  - Each rule (eligibility, increment, total) is a small independent function that is
    easy to test and change, e.g. changing 10% to 12% touches one lambda.
  - No shared mutable state means fewer bugs and the same input always gives the same output.
```

## Q3. Festival sale – 20% discount above ₹1,000

Pure function for the discounted price, map() to apply it, filter() for products above ₹1,000, and proof the original list is unchanged.

**Code:** [`q3_festival_discount.py`](q3_festival_discount.py)

**Output:**

```text
Original product list:
  Headphones         Rs.1499
  Phone Case         Rs.399
  Smart Watch        Rs.3999
  USB Cable          Rs.249
  Bluetooth Speaker  Rs.2200
  Notebook           Rs.1000

b) Prices after applying the festival discount (map):
  Headphones         Rs. 1499.00 -> Rs. 1199.20  (20% off)
  Phone Case         Rs.  399.00 -> Rs.  399.00
  Smart Watch        Rs. 3999.00 -> Rs. 3199.20  (20% off)
  USB Cable          Rs.  249.00 -> Rs.  249.00
  Bluetooth Speaker  Rs. 2200.00 -> Rs. 1760.00  (20% off)
  Notebook           Rs. 1000.00 -> Rs. 1000.00

c) Products costing more than Rs.1,000 (filter):
  Headphones         Rs.1499
  Smart Watch        Rs.3999
  Bluetooth Speaker  Rs.2200

d) Original list after processing (unchanged):
  [('Headphones', 1499), ('Phone Case', 399), ('Smart Watch', 3999), ('USB Cable', 249), ('Bluetooth Speaker', 2200), ('Notebook', 1000)]

   The pure function discounted_price() only reads the price passed to it and returns
   a new value. It never assigns to the products list, so map() builds a brand-new list
   (sale_products) and the original catalogue stays exactly as it was.
```

## Q4. Smart electricity meter

Days above 10 units (filter), daily cost at ₹6/unit (map), total consumption (reduce), and a pure bill function.

**Code:** [`q4_electricity_meter.py`](q4_electricity_meter.py)

**Output:**

```text
Daily consumption (units):
  Mon: 8.5
  Tue: 12.0
  Wed: 9.0
  Thu: 14.5
  Fri: 10.0
  Sat: 16.0
  Sun: 11.5

a) Days with consumption above 10 units (filter): ['Tue', 'Thu', 'Sat', 'Sun']

b) Daily cost at Rs.6 per unit (map):
  Mon: Rs.51.00
  Tue: Rs.72.00
  Wed: Rs.54.00
  Thu: Rs.87.00
  Fri: Rs.60.00
  Sat: Rs.96.00
  Sun: Rs.69.00

c) Total consumption (reduce): 81.5 units

d) Weekly electricity bill (pure function): Rs.489.00
   Original data after billing (unchanged): {'Mon': 8.5, 'Tue': 12.0, 'Wed': 9.0, 'Thu': 14.5, 'Fri': 10.0, 'Sat': 16.0, 'Sun': 11.5}
```

## Q5. Course eligibility – logic programming

Student results as facts, an eligibility rule (pass both Python and Data Structures), and a query for all eligible students.

**Code:** [`q5_course_eligibility.py`](q5_course_eligibility.py)

**Output:**

```text
a) Facts:
   result(Arun, Python, Pass).
   result(Arun, DataStructures, Pass).
   result(Bala, Python, Pass).
   result(Bala, DataStructures, Fail).
   result(Charan, Python, Pass).
   result(Charan, DataStructures, Pass).
   result(Divya, Python, Fail).
   result(Divya, DataStructures, Pass).

b) Rule:
   eligible(S) :- result(S, Python, Pass), result(S, DataStructures, Pass).

c) Query: ?- eligible(S).
   Eligible students: ['Arun', 'Charan']

   Individual checks:
   ?- eligible(Arun).  ->  Yes
   ?- eligible(Bala).  ->  No
   ?- eligible(Charan).  ->  Yes
   ?- eligible(Divya).  ->  No
```

## Q6. Promotion eligibility – logic programming

Employee facts, a promotion rule (≥ 5 years, training completed, rating ≥ 4), and a query for eligible employees.

**Code:** [`q6_promotion_eligibility.py`](q6_promotion_eligibility.py)

**Output:**

```text
a) Facts:
   experience(Ravi, 6).  training(Ravi, 'Completed').  rating(Ravi, 5).
   experience(Priya, 3).  training(Priya, 'Completed').  rating(Priya, 4).
   experience(Kumar, 8).  training(Kumar, 'Not Completed').  rating(Kumar, 5).
   experience(Meena, 7).  training(Meena, 'Completed').  rating(Meena, 4).

b) Rule:
   promotable(E) :- experience(E, Y), Y >= 5, training(E, 'Completed'), rating(E, R), R >= 4.

c) Query: ?- promotable(E).
   Employees eligible for promotion: ['Meena', 'Ravi']

   Reasoning for each employee:
   Ravi   -> Eligible
   Priya  -> Not eligible (experience below 5 years)
   Kumar  -> Not eligible (training not completed)
   Meena  -> Eligible
```

## Q7. Parking allocation – logic programming

Parking slot facts, a rule for available slots by vehicle type, and a query for available car slots.

**Code:** [`q7_parking_allocation.py`](q7_parking_allocation.py)

**Output:**

```text
a) Facts:
   slot(S1, Car, Available).
   slot(S2, Bike, Occupied).
   slot(S3, Car, Available).
   slot(S4, Bike, Available).
   slot(S5, Car, Occupied).

b) Rule:
   available_slot(S, Type) :- slot(S, Type, Available).

c) Query: ?- available_slot(S, Car).
   Available car slots: ['S1', 'S3']

   Query: ?- available_slot(S, Bike).
   Available bike slots: ['S4']
```
