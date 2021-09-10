#include <iostream>

using namespace std;

int lastDigitFibonacci(int n);

int main()
{
    int num;
    cin >> num;
	cout << lastDigitFibonacci(num);
	return 0;
}

int lastDigitFibonacci(int n)
{
    int arr[n+1] = {0, 1};
    for(int i=2; i<=n; i++)
        arr[i] = (arr[i-1] + arr[i-2])%10;
    return arr[n]%10;
}
