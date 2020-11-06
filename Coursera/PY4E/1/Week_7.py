largest = None
smallest = None

while True:
    # Input
    num = input("Enter a number: ")
    # Exit Control
    if num == "done":
        break
    # Error in Input
    try:
        number = int(num)
        # Condition for Maximum
        if largest is None:
            largest = number
        elif largest < number:
            largest = number
        # Condition for Minimum
        if smallest is None:
            smallest = number
        elif smallest > number:
            smallest = number
    except:
        print("Invalid input")

print("Maximum is", largest)
print("Minimum is", smallest)
