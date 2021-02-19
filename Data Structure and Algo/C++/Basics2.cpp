#include <iostream>
using namespace std;
/* run this program using the console pauser or add your own getch, system("pause") or input loop */
class Array {
	private:
		int *A;
		int arraySize;
		int length;
	public:
		Array() { // Non-parameterized Constructor
			arraySize = 10;
			length = 0;
			A = new int[arraySize];
		}
		Array(int sz) { // Parameterized Constructor
			arraySize = sz;
			length = 0;
			A = new int[arraySize];
		}
		~Array() {
			delete[]A;
		}
		void Display();
		void insertElement();
		void Delete();
};
void Array::Display() {
	cout << "Array Elements:"<<endl;
	for(int i=0; i<length; i++) {
		cout << A[i] << " ";
	}
}
void Array::insertElement() {
	int pos,value;
	cout << "\nEnter the position where you to insert the element[Starts from 0]:";
	cin >> pos;
	if(pos <= length && pos >= 0 ) {
		cout << "\nElement Value:";
		cin >> value;
		for(int i = length; i>pos; i--) {
			A[i] = A[i-1] ;
		}
		A[pos] = value;
		length++;
	} else {
		cout << "\nInvalid Position";
	}
}
void Array::Delete() {
	int pos;
	cout << "\nEnter the position which will be deleted";
	cin >> pos;
	if(pos <=length && pos >= 0) {
		for(int i = pos; i < length-1; i++) {
			A[i] = A[i+1] ;
		}
		A[length-1] = 0;
		length --;
	} else {
		cout << "\n Incorrect Index";
	}
}

int main(int argc, char** argv) {
	cout << "Say Hello";
	int n;
	cout << "\nHow many numbers\n" ;
	cin >> n;
	Array myArray1(n);

	cout << "\nEnter All Elements:";

	int operation=1,exitVar;
	while(operation != 0) {
		cout << "\nSelect Operation:"
		     << endl;
		cout << "1. DISPLAY " << endl;
		cout << "2. ADD/APPEND " << endl;
		cout << "3. DELETE " << endl;
		cout << "4. ARRAY ELEMENTS LENGTH " << endl;
		cout << "0. EXIT " << endl;
		cin >> operation;


		switch(operation) {
			case 1 :
				myArray1.Display();
				break;
			case 2 :
				myArray1.insertElement();
				break;
			case 3 :
				myArray1.Delete();
				break;
			case 0:
				break;
			default :
				cout << "Invalid Operation" << endl;
		}
	}
	return 0;
}