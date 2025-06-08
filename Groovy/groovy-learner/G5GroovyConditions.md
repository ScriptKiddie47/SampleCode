# Conditions

1. Operators are as usual

    ```groovy
    def ageOld = 6;
    if(ageOld == 5){
        println("Go to Kindergarten");
    }else if((ageOld > 5) && (ageOld < 18)){
        println("Go to Grade"); // Go to Grade
    }else{
        println("Go to College");
    }
    ```

1. Ternary Operator

    ```groovy
    def canVote = false;
    println(canVote ? "Can Vote":"Can't Vote"); // Can't Vote
    ```

1. Swtich Operator

    ```groovy
    def value = 16;
    switch(value) {
        case 16: println("You can drive"); break; // You can drive
        case 18: println("You can vote"); break;
        default: println("Have Fun");
    }
    ```

    ```groovy
    def value = 13
    switch(value) {
        case 0..8: println("Child"); break;
        case 8..15: println("Teenager"); break; // Teenager
        case 16..17: println("Can Drive"); break;
        case 18..60: println("Can Vote"); break;
        default: println("Have Fun");
    }
    ```
1. 

