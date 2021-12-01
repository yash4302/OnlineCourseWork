#include <iostream>

using namespace std;

int majortyElement(int arr[], int left, int right);
int getFrequency(int arr[], int key, int left, int right);

int main()
{
	int N;
	cin >> N;

	int arr[N];
	for(int i=0; i<N; i++)
        cin >> arr[i];

    int majority = majortyElement(arr, 0, N-1);

    (majority != -1) ? cout << "1" : cout << "0";
	return 0;
}

int majortyElement(int arr[], int left, int right) {
    if(left == right)
        return arr[left];

    int mid = (left + right)/2;

    int left_major = majortyElement(arr, left, mid);
    int right_major = majortyElement(arr, mid+1, right);

    if(left_major == right_major)
        return left_major;
    else if(left_major == -1 && right_major == -1)
        return -1;

    int left_major_freqency = (left_major == -1) ? -1 : getFrequency(arr, left_major, left, right);
    int right_major_freqency = (right_major == -1) ? -1 : getFrequency(arr, right_major, left, right);

    if(right_major_freqency > left_major_freqency) {
        if(right_major_freqency > (right - left + 1)/2)
            return right_major;
    }
    else if(left_major_freqency > right_major_freqency) {
        if(left_major_freqency > (right - left + 1)/2)
            return left_major;
    }
    return -1;
}

int getFrequency(int arr[], int key, int left, int right) {
    int count = 0;
    for(int i=left; i<=right; i++)
        if(arr[i] == key)
            count++;
    return count;
}
