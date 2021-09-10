#include <iostream>

using namespace std;

int sumOfTwoDigits(int num1, int num2);

int main()
{
    int num1, num2;
    cin >> num1 >> num2;
    cout << sumOfTwoDigits(num1, num2);
    return 0;
}

int sumOfTwoDigits(int num1, int num2)
{
    return num1+num2;
}
