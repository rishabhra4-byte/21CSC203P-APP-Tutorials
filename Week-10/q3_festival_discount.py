"""
Tutorial 10 - Question 3
Festival sale: 20% discount on products costing more than Rs.1,000.
Pure functions with map() and filter().
"""

products = [
    ("Headphones", 1499),
    ("Phone Case", 399),
    ("Smart Watch", 3999),
    ("USB Cable", 249),
    ("Bluetooth Speaker", 2200),
    ("Notebook", 1000),
]


# a) Pure function to calculate the discounted price.
#    Output depends only on its input and it changes nothing outside itself.
def discounted_price(price):
    return round(price * 0.80, 2) if price > 1000 else price


print("Original product list:")
for name, price in products:
    print(f"  {name:<18} Rs.{price}")

# b) Apply the discount using map()
sale_products = list(map(lambda p: (p[0], discounted_price(p[1])), products))
print("\nb) Prices after applying the festival discount (map):")
for (name, old), (_, new) in zip(products, sale_products):
    note = "  (20% off)" if new != old else ""
    print(f"  {name:<18} Rs.{old:>8.2f} -> Rs.{new:>8.2f}{note}")

# c) Products costing more than Rs.1,000 using filter()
costly = list(filter(lambda p: p[1] > 1000, products))
print("\nc) Products costing more than Rs.1,000 (filter):")
for name, price in costly:
    print(f"  {name:<18} Rs.{price}")

# d) Show that the original list was not modified
print("\nd) Original list after processing (unchanged):")
print(" ", products)
print("""
   The pure function discounted_price() only reads the price passed to it and returns
   a new value. It never assigns to the products list, so map() builds a brand-new list
   (sale_products) and the original catalogue stays exactly as it was.""")
