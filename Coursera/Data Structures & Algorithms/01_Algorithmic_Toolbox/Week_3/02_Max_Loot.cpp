#include <iostream>
#include <iomanip>

using namespace std;

inline int min(int a, int b);
inline void swap(double &a, double &b);
inline void swap(int &a, int &b);

int main()
{
    unsigned int n, W;
    double value = 0;
    // Input - 1
    cin >> n >> W;
    unsigned int v[n], w[n];
    double A[n]={0.0};
    // Input - 2
    for(int i=0; i<n; i++)
        cin >> v[i] >> w[i];
    // Sorting
    for(int i=0; i<n; i++)
        A[i] = double(v[i])/w[i];
    for(int i=0; i<n-1; i++)
    {
        for(int j=i+1; j<n; j++)
        {
            if(A[j] >= A[i])
            {
                swap(A[i], A[j]);
                swap(w[i], w[j]);
            }
        }
    }
    // Counting Value
    for(int i=0; i<n; i++)
    {
        if(W==0)
            break;
        else
        {
            int a;
            a = min(W, w[i]);
            value = value + a*A[i];
            W -= a;
        }
    }
    // Output
    cout << setprecision(3) << fixed << value << endl;
    return 0;
}

int min(int a, int b)
{
    if(a<b)
        return a;
    else
        return b;
}

void swap(double &a, double &b)
{
    double temp = a;
     a = b;
     b = temp;
}

void swap(int &a, int &b)
{
    int temp = a;
     a = b;
     b = temp;
}
