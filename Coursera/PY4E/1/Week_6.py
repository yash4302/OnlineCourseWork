def computepay(h,r):
    if h<=40:
        return h*r
    else:
        return 40*r + 1.5*(h-40)*r

hrs = input("Enter Hours:")
rate = input("Enter Rate:")

p = computepay(float(hrs), float(rate))
print("Pay",p)