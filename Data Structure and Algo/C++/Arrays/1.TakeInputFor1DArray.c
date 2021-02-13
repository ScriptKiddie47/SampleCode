#include <iostream>
using namespace std;

int main() {
	int numbers[100];
	int arraySize=0;
    //  store input from user to array
    for (int i = 0; i < 100; ++i) {
        cin >> numbers[i];
        arraySize++;
        if(numbers[i] == 42)
            break;
    }
    int realArray[arraySize-1];

    for(int i = 0; i < arraySize-1; ++i){
        realArray[i] = numbers[i];
    }
    for(int i = 0; i < arraySize-1; ++i){
            cout << "realArray[" <<i<<"]:" << realArray[i] << "\n";
    }
    cout << "ArraySize:" <<  sizeof(realArray) / sizeof(realArray[0]);
	return 0;
}
