fname = input("Enter file name: ")
fh = open(fname)

lst = list()

for line in fh:
    line = line.rstrip()
    demo = line.split()
    for test in demo:
        if test not in lst:
            lst.append(test)

lst.sort()
print(lst)
