#include<iostream>

using namespace std;

int main()
{
    int d, m, n, count=0;
    int current=0, last=0;
    // Input - 1
    cin >> d >> m >> n;
    int s[n+2];
    s[0] = 0;
    s[n+1] = d;
    // Input - 2
    for(int i=1; i<=n; i++)
        cin >> s[i];
    // Counting
    while(current <= n)
    {
        last = current;
        while(current<=n && (s[current+1]-s[last])<=m)
            current++;
        if(current == last)
        {
            count = -1;
            break;
        }
        if(current <= n)
            count++;
    }
    // Output
    cout << count << endl;
    return 0;
}
