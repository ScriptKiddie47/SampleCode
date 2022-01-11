#include <iostream>

using namespace std;

class Array
{
public:
    int *A;
    int arraySize;
    int length;
    Array() // Non-parameterized Constructor
    {
        arraySize = 10;
        length = 0;
        A = new int[arraySize];
    }
    Array(int sz) // Parameterized Constructor
    {
        arraySize = sz;
        length = 0;
        A = new int[arraySize];
    }
    ~Array()
    {
        delete[]A;
    }
    void addOrAppendElement();
    void insertElement();
    void Display();
};
int main()
{
    Array myArray;
    int n;
    cout << "Enter Size of An Array\n";
    cin >> myArray.arraySize;
    myArray.A = new int[myArray.arraySize];
    myArray.length = 0;

    cout << "\nHow many numbers\n" ;
    cin >> n;

    cout << "\nEnter All Elements:";
    for(int i = 0; i<n; i++)
    {
        cout << "\narr1["<<i<<"]:";
        cin >> myArray.A[i];
    }

    myArray.length = n;
    int operation,exitVar = 0;
    while(exitVar == 0)
    {
        cout << "\nSelect Operation:"
             << endl;
        cout << "1. DISPLAY " << endl;
        cout << "2. ADD/APPEND " << endl;
        cout << "3. INSERT INTO POSITION " << endl;
        cout << "4. ARRAY ELEMENTS LENGTH " << endl;
        cout << "0. EXIT " << endl;
        cin >> operation;


        switch(operation)
        {
        case 1 :
            myArray.Display();
            break;
        case 2 :
           myArray.addOrAppendElement();
            break;
        case 3 :
            myArray.insertElement();
            break;
        case 4 :
            cout << "\n Number of Elements in the Array : " << myArray.length << endl;
            break;
        case 0 :
            return 0;
        default :
            cout << "Invalid Operation" << endl;
        }
    }
    return 0;
}
void Array::Display()
{
    cout << "\n*******DISPLAY*******\n";
    for(int i = 0; i<length; i++)
    {
        cout << "\nArr["<<i<<"]:"<<A[i];
    }
    cout << "\n*******DISPLAY ENDS*******\n\n";
}
void Array::addOrAppendElement()
{
    int value;
    cout << "\nEnter the Number: ";
    cin >> value;
    A[length] = value;
    length++;
    cout << "Element Inserted at Position:"<< length;
}
void Array::insertElement()
{
    int pos,value;
    cout << "\nEnter the position where you to insert the element[Starts from 0]:";
    cin >> pos;
    if(pos <= length && pos > 0 )
    {
        cout << "\nElement Value:";
        cin >> value;
        for(int i = length; i>pos; i--)
        {
            A[i] = A[i-1] ;
        }
        A[pos] = value;
        length++;
    }
    else
    {
        cout << "\nInvalid Position";
    }
}
