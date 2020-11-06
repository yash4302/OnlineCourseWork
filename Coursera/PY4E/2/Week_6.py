name = input("Enter file:")
if len(name) < 1 : name = "mbox-short.txt"
handle = open(name)

count = dict()

for line in handle:
    line.strip()
    if line.startswith('From') and not line.startswith('From:'):
        lst = line.split()
        lsts = lst[5].split(':')
        count[lsts[0]] = count.get(lsts[0], 0) + 1

for k,v in sorted(count.items()):
    print(k,v)