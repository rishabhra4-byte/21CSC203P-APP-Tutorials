"""
Tutorial 10 - Question 4
A smart electricity meter records the daily consumption (units) of a household.
"""

from functools import reduce

RATE_PER_UNIT = 6  # Rs. per unit

daily_units = {
    "Mon": 8.5,
    "Tue": 12.0,
    "Wed": 9.0,
    "Thu": 14.5,
    "Fri": 10.0,
    "Sat": 16.0,
    "Sun": 11.5,
}

print("Daily consumption (units):")
for day, units in daily_units.items():
    print(f"  {day}: {units}")

# a) Days when consumption exceeded 10 units
high_days = list(filter(lambda d: daily_units[d] > 10, daily_units))
print("\na) Days with consumption above 10 units (filter):", high_days)

# b) Electricity cost for each day at Rs.6 per unit
daily_cost = list(map(lambda item: (item[0], item[1] * RATE_PER_UNIT), daily_units.items()))
print("\nb) Daily cost at Rs.6 per unit (map):")
for day, cost in daily_cost:
    print(f"  {day}: Rs.{cost:.2f}")

# c) Total consumption using reduce()
total_units = reduce(lambda a, b: a + b, daily_units.values())
print(f"\nc) Total consumption (reduce): {total_units} units")


# d) Pure function to calculate the bill without changing the original data
def calculate_bill(consumption, rate):
    """Returns the bill amount; reads the data but never modifies it."""
    return sum(consumption.values()) * rate


bill = calculate_bill(daily_units, RATE_PER_UNIT)
print(f"\nd) Weekly electricity bill (pure function): Rs.{bill:.2f}")
print("   Original data after billing (unchanged):", daily_units)
