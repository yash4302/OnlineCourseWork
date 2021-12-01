#include <iostream>

using namespace std;

int main()
{
	int amount;
	cin >> amount;
	cout << (amount/10) + ((amount%10)/5) + (amount%5);
	return 0;
}
