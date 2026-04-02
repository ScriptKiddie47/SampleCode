# Equality, Relational & Conditional Operators

## Short Circuit Evaluation vs Full Evaluation

1. `& | ^` - Full 
1. `&&` , `||` - Short
1. Short circuit evaluation enables you to not evaluate the right side of the `AND` and `OR` expressions, when the overall result can be predicted from the left side.

## Ternary Operator

1. When the boolean expression yields `true`, the value after the `?` is assigned. When `false` the value after the : is assigned.
1. Used to simplyfy conditional assignment logic.

## Switch

1. Applicable for Types: `byte,short,int,char,String,enum,Object`
1. Execution flow continues until it reaches the end of the switch or encounters an optional `break` statement.

## Switch Expression

1. Used to evaluate a value
1. Single line expression does not require explicit use of a `yield` statement.
1. A block requires a `yield` statement to return a value.
1. An overall statement must be terminated with a `;` symbol.
1. Must have `default` clause

    ```java
    char status = 'N';
    double cost = 10;
    double price = switch (status) {
        case 'S', 'N' -> cost += 1;
        case 'D' -> {
            double discount = cost * 0.2;
            cost -= discount;
            yield cost;
        }
        default -> 3;
    };
    ```
1. `yield` is a special keyword. If a variable called `yield` exists elsewhere, that code won't break.
1. When using `->` no fall through will happen so no need to use `break`

