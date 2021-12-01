#include <iostream>

using namespace std;

long long last_Fibonacci(long long n1, long long n2);

int main()
{
    long long n1, n2;
    cin >> n1 >> n2;
    cout << last_Fibonacci(n1, n2);
    return 0;
}

long long last_Fibonacci(long long n1, long long n2)
{
    long long a=0, b=1, c, sum = 0;
    int rem;
    c = a + b;
    // 60 is pisano period for 10
    n1 %= 60;
    n2 %= 60;
    if(n2<n1)
        n2 += 60;
    for(long long i=1; i<=n2; i++)
    {
        c = (a + b)%10;
        a = b;
        b = c;
        if(i>=n1 && i<= n2)
            sum = (sum + a)%10;
    }
    return sum;
}
