fname = input("Enter file name: ")
if len(fname) < 1: fname = "mbox-short.txt"

fh = open(fname)
count = 0

for line in fh:
    if line.startswith('From:'):
        count += 1
        lst = line.split()
        print(lst[1])

print("There were", count, "lines in the file with From as the first word")
