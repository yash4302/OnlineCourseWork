#include <iostream>

using namespace std;

int pisanoPeriod(int m);
int fibonacci_modulo(long long n, int m);

int main()
{
	long long num;
	int mod;
	cin >> num >> mod;
	cout << fibonacci_modulo(num, mod);
	return 0;
}

int fibonacci_modulo(long long n, int m)
{
    long long a=0, b=1, c;
    long long rem = n % pisanoPeriod(m);
    c = rem;
    for(int i=1; i<rem; i++)
    {
        c = (a+b)%m;
        a = b;
        b = c;
    }
    return c%m;
}

int pisanoPeriod(int m)
{
    long long a=0, b=1, c=a+b;
    for(int i=0; i<m*m; i++)
    {
        c = (a+b)%m;
        a = b;
        b = c;
        if(a==0 && b==1)
            return i+1;
    }
}
