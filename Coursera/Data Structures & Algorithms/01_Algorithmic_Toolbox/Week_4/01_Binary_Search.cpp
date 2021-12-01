#include <iostream>

using namespace std;

int binarySearch(int arr[], int x, int left, int right);

int main()
{
	int N, t;
	cin >> N;
	int arr[N];

	for(int i=0; i<N; i++)
        cin >> arr[i];

    cin >> t;
    while(t--) {
        int key;
        cin >> key;
        cout << binarySearch(arr, key, 0, N-1);
    }
	return 0;
}

int binarySearch(int arr[], int x, int left, int right) {
    int mid = (left + right)/2;

    if(left != right) {
        if(arr[mid] == x)
            return mid;
        else if(arr[mid] > x)
            return binarySearch(arr, x, left, mid);
        else if(arr[mid] < x)
            return binarySearch(arr, x, mid+1, right);
    }
    else if(arr[mid] == x)
        return mid;
    return -1;
}
