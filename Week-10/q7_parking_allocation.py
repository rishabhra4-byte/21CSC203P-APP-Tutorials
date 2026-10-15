"""
Tutorial 10 - Question 7  (Logic programming with kanren)
A shopping mall uses a rule-based parking allocation system.
"""

from kanren import Relation, facts, run, var

# a) Parking slot details as facts: slot(Slot, VehicleType, Availability)
slots = [
    ("S1", "Car",  "Available"),
    ("S2", "Bike", "Occupied"),
    ("S3", "Car",  "Available"),
    ("S4", "Bike", "Available"),
    ("S5", "Car",  "Occupied"),
]
slot = Relation()
facts(slot, *slots)


# b) Rule: available_slot(S, Type) :- slot(S, Type, available).
def available_slot(s, vehicle_type):
    return slot(s, vehicle_type, "Available")


print("a) Facts:")
for s_id, vtype, status in slots:
    print(f"   slot({s_id}, {vtype}, {status}).")

print("\nb) Rule:")
print("   available_slot(S, Type) :- slot(S, Type, Available).")

# c) Query: available car parking slots
s = var()
print("\nc) Query: ?- available_slot(S, Car).")
print("   Available car slots:", sorted(run(0, s, available_slot(s, "Car"))))

# Same rule works for any vehicle type
print("\n   Query: ?- available_slot(S, Bike).")
print("   Available bike slots:", sorted(run(0, s, available_slot(s, "Bike"))))
