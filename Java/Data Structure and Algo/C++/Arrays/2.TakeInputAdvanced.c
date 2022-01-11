/*
For help on this problem, please check out our tutorial Input and Output (I/O)

Your program is to use the brute-force approach in order to find the Answer to Life,
 the Universe, and Everything. More precisely… rewrite small numbers from input to output.
 Stop processing input after reading in the number 42. All numbers at input are integers of one or two digits.

Sample Input:
1
2
88
42
99
Sample Output:
1
2
88
*/
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int numbers[1000];
    int arraySize=0;
    //  store input from user to array
    for (int i = 0; i < 10000; ++i)
    {
        cin >> numbers[i];
        arraySize++;
        if(numbers[i] == 42)
            break;
    }
    int realArray[arraySize-1];

    for(int i = 0; i < arraySize-1; ++i)
    {
        realArray[i] = numbers[i];
    }
    sort(realArray,realArray+arraySize);
    for(int i = 0; i < arraySize-1; ++i)
    {
        cout << "realArray[" <<i<<"]:" << realArray[i] << "\n";
    }
    cout << "ArraySize:" <<  sizeof(realArray) / sizeof(realArray[0]);
    return 0;
}
