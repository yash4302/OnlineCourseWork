import re

file = "regex_sum.txt"
file = open(file, "r")
total = 0

for line in file:
    line.strip()
    numbers = re.findall('[0-9]+', line)
    for data in numbers:
        total += int(data)

print(total)
