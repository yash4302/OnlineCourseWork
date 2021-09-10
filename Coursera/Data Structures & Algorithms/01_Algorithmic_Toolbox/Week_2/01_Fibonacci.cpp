#include <iostream>

using namespace std;

int fibonacci(int n);

int main()
{
    int num;
    cin >> num;
	cout << fibonacci(num);
	return 0;
}

int fibonacci(int n)
{
    int arr[n+1] = {0, 1};
    for(int i=2; i<=n; i++)
        arr[i] = arr[i-1] + arr[i-2];
    return arr[n];
}
