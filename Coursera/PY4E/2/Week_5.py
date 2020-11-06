name = input("Enter file:")
if len(name) < 1 : name = "mbox-short.txt"
handle = open(name)

counts = dict()

for line in handle:
    line.strip()
    if line.startswith('From:'):
        lst = line.split()
        counts[lst[1]] = counts.get(lst[1], 0) + 1

bigName = ''
bigCount = 0

for k,v in counts.items():
    if v>bigCount:
        bigName = k
        bigCount = v

print(bigName, bigCount)