#include<iostream>

using namespace std;

void sort(int n, int a[]);
void swap(int &a, int &b);

int main()
{
    int n;
    long long sum=0;
    // Input
    cin >> n;
    int a[n], b[n];
    for(int i=0; i<n; i++)
        cin >> a[i];
    for(int i=0; i<n; i++)
        cin >> b[i];
    // Multiplication
    sort(n, a);
    sort(n, b);
    for(int i=0; i<n; i++)
        sum = sum + ((long long)(a[i])*b[i]);
    cout << sum << endl;
    return 0;
}

void sort(int n, int a[])
{
    for(int i=0; i<n-1; i++)
        for(int j=i+1; j<n; j++)
            if(a[i] > a[j])
                swap(a[i], a[j]);
}

void swap(int &a, int &b)
{
    int temp = a;
    a = b;
    b = temp;
}
