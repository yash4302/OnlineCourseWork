#include <iostream>

using namespace std;

long long last_Fibonacci(long long n);

int main()
{
    long long n;
    int sum = 0;
    cin >> n;
    cout << last_Fibonacci(n);
    return 0;
}

long long last_Fibonacci(long long n)
{
    long long a=0, b=1, c, sum = 0;
    c = a + b;
    // 60 is pisano period for 10
    n %= 60;
    for(long long i=1; i<=n; i++)
    {
        c = (a + b)%10;
        a = b;
        b = c;
        sum = (sum + a)%10;
    }
    return sum;
}
