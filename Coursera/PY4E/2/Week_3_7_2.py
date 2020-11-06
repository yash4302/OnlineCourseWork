# Use the file name mbox-short.txt as the file name
fname = input("Enter file name: ")
fh = open(fname)
add = 0.0
count = 0

for line in fh:
    if not line.startswith("X-DSPAM-Confidence:"):
        continue
    index = line.find('0')
    add += float(line[index-1:])
    count += 1
print("Average spam confidence:", add/count)
