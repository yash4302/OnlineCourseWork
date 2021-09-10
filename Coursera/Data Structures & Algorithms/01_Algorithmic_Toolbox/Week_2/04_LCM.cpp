#include <iostream>

using namespace std;

int GCD(int a, int b);
long long LCM(int a, int b);

int main()
{
    int num1, num2;
    cin >> num1 >> num2;
	cout << LCM(num1, num2);
	return 0;
}

int GCD(int a, int b)
{
    if(a < b)
        return GCD(b,a);
    else if(b == 0)
        return a;
    return GCD(b, a%b);
}

long long LCM(int a, int b)
{
    return (long long)a*b/GCD(a, b);
}
