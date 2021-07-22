### 1. 산술 연산자

```java
class Main {
    public static void main(String[] args) {
        int a = 12;
        int b = 5;

        System.out.println("a + b = " + (a + b));  // a + b = 17
        System.out.println("a - b = " + (a - b));  // a - b = 7
        System.out.println("a * b = " + (a * b));  // a * b = 60
        System.out.println("a / b = " + (a / b));  // a / b = 2
        System.out.println("a % b = " + (a % b));  // a % b = 2
    }
}
```

- `/` 나누기 연산자
	int 끼리 나누면 int 반환하고, 두 수 중에 하나라도 float이 있으면 float 반환한다.
```java
9 / 2 = 4
9.0f / 2 = 4.5f
9 / 2.0f = 4.5f
9.0f / 2.0f = 4.5f
```

### 2. 비트 연산자
- 비트 논리 연산자(Bitwise Operators)
<img width="684" alt="bit_false_true_operator" src="https://user-images.githubusercontent.com/46085281/126601760-84d442a7-9dbd-45d8-9305-07da0616e97b.png">

```java
3 & 7 -> 0011 & 0111 -> 0011 -> 3
3 | 7 -> 0011 | 0111 -> 0111 -> 7
3 ^ 7 -> 0011 ^ 0111 -> 0100 -> 4

// 비트 논리 연산자를 활용하여 중복되지 않는 수 구하기
// 5 ^ 0 == 5 -> 0과 배타적 논리 합은 자기자신.
// 101 (5)
// 000 (0)
// 101 (5^0)
// 5 ^ 5 == 0 -> 자기자신과 배타적 논리합은 0.
// 101 (5)
// 101 (5)
// 000 (5^5)
// 2개씩 있는 숫자는 배타적 논리합으로 모두 0이 되고, 0과 마지막 남은 숫자의 배타적 논리합은 마지막 남은 숫자가 됨
int[] numbers = {5, 3, 4, 2, 1, 4, 5, 3, 2};
int answer = 0;
for (int number : numbers) {
    answer ^= number;
}
// answer == 1
```

- 쉬프트 연산자(Shift Operators)
<img width="696" alt="bit_shift_operator" src="https://user-images.githubusercontent.com/46085281/126601836-dda534f0-c809-40de-8ff8-3babd55bd46e.png">

```java
3 << 2 -> 0011 -> 1100 -> 12
3 >> 2 -> 0011 -> 0000 -> 0
11 >> 2 -> 1011 -> 0010 -> 15
3 >>> 2 -> 0011 -> 0000 -> 0

// 쉬프트 연산자를 활용하여 중간값 구하기
500 >>> 1 == 250
```

### 3. 관계 연산자
- 일반적으로 값의 유효성을 검사하는데 사용합니다.

|Operator|Description|Example|
|-|-|-|
|`==`|Is Equal To|`3 == 5` returns `false`|
|`!=`|Not Equal To|`3 != 5` returns `true`|
|`>`|Greater Than|`3 > 5` returns `false`|
|`<`|Less Than|`3 < 5` returns `true`|
|`>=`|Greator Than or Equal To|`3 >= 5` returns `false`|
|`<=`|Less Than or Equal To|`3 <= 5` returns `true`|

```java
class Main {
    public static void main(String[] args) {
        int a = 7;
        int b = 11;

        System.out.println(a == b);  // false
        System.out.println(a != b);  // true
        System.out.println(a > b);  // false
        System.out.println(a < b);  // true
        System.out.println(a >= b);  // false
        System.out.println(a <= b);  // true
    }
}
```

### 4. 논리 연산자

|Operator|Example|Meaning|
|-|-|-|
|`&&` (AND)|expression1 `&&` expression2|expression1과 expression2 모두 `true`일 때 `true`|
|`\|\|` (OR)|expression1`\|\|`expression2|expression1와 expression2중 하나라도 `true`일 때 `true`|
|`!` (NOT)|`!`expression|expression이 `false`일 때 `true`, 그 반대도 마찬가지|

```java
class Main {
    public static void main(String[] args) {
        System.out.println((5 > 3) && (8 > 5));  // true  (true && true)
        System.out.println((5 > 3) && (8 < 5));  // false (true && false)

        System.out.println((5 < 3) || (8 > 5));  // true  (false || true)
        System.out.println((5 > 3) || (8 < 5));  // true  (true || false)
        System.out.println((5 < 3) || (8 < 5));  // false (false || false)

        System.out.println(!(5 == 3));  // true (!false)
        System.out.println(!(5 > 3));  // false (!true)
     }
}
```

### 5. instanceof
- 참조변수가 참조하고 있는 인스턴스의 실제 타입을 알아보기 위한 연산자.
- 어떤 타입에 대한 instanceof 연산의 결과가 true라는 것은 검사한 타입으로 형변환이 가능함을 뜻한다.
- 형변환 전에 반드시 instanceof로 확인해야 한다.

```java
class Car extends Vehicle {}

Car car = new Car();
System.out.println(car instanceof Vehicle); // true;
System.out.println(car instanceof Object);  // true;
System.out.println(car instanceof Car);     // true;

```
### 6. assignment(=) operator
- 대입 연산자는 변수와 같은 저장공간에 값 또는 수식의 연산결과를 저장하는데 사용한다.
- 오른쪽 피연산자의 값을 왼쪽 피연산자에 저장한다.
- 대입 연산자는 연산자들 중에서 가장 낮은 우선순위를 가지고 있기 때문에 제일 나중에 수행된다.
- 연산 진행 방향은 오른쪽에서 왼쪽이다.

```java
x = y = 3;
1. y = 3;
2. x = y;
```

- 대입 연산자의 왼쪽 피연산자를 'lvalue(left value)'라 하고, 오른쪽 피연산자를 'rvalue(right value)'라고 한다.

    x (lvalue) = 3 (rvalue)

- rvalue는 변수뿐만 아니라 식이나 상수 등이 모두 가능하다.
- lvalue는 반드시 변수처럼 값을 변경할 수 있는 것이어야 한다.
- 그래서 리터럴이나 상수같이 값을 저장할 수 없는 것들은 lvalue가 될 수 없다.

```java
int i = 0;
3 = i + 3; // 에러. lvalue가 값을 저장할 수 있는 공간이 아니다.
i + 3 = i; // 에러. lvalue의 연산결과는 리터럴(i + 3 -> 0 + 3 -> 3)

final int MAX = 3; // 변수 앞에 키워드 final을 붙이면 상수가 된다.
MAX = 10; // 에러. 상수(MAX)에 새로운 값을 저장할 수 없다.
```

복합 대입 연산자 op=

- 대입 연산자는 다른 연산자(op)와 결합하여 'op='와 같은 방식으로 사용될 수 있다.

```java
i = i + 3 
-> i += 3 // 결합된 두 연산자는 반드시 공백없이 붙여 써야 한다.

i += 3; -> i = i + 3;
i -= 3; -> i = i - 3;
i *= 3; -> i = i * 3;
i /= 3; -> i = i / 3;
i %= 3; -> i = i % 3;
i *= 10 + j -> i = i * (10 + j);
```

### 7. 화살표(->) 연산자

- 람다식에서 사용, 매개변수와 실행문을 구분한다.
- (매개변수, ...) -> { 실행문 ... }
- (매개변수, ...)는 오른쪽 { }를 실행하기 위해 필요한 값을 제공하는 역할을 한다.

```java
int max(int a, int b) {          
	return a > b ? a : b;       ->   (a, b) -> a > b ? a: b
}
```

### 8. 3항 연산자

`조건식 ? 식1 : 식2`

- 첫 번째 피연산자인 조건식의 평가결과에 따라 다른 결과를 반환한다.
- 조건식의 평가결과가 true이면 식1이, flase이면 식2가 연산결과가 된다.

```java
result = x > y ? x : y;
result = 5 > 3 ? 5 : 3;
result = true ? 5 : 3;
result = 5;
```

### 9. 연산자의 우선순위

![연산자 우선순위](https://user-images.githubusercontent.com/46085281/126601881-1be1bc0c-71ca-4e12-9db0-7e4e332d657a.png)

### 10. (optional) Java 13. switch 연산자

1. Multiple values in a case
- case 하나당 하나의 값만 할당할 수 있었지만 이제 그 제한이 사라졌다.

```java

// 기존 Single value cases
switch (errorCode) {
    case 400:
    case 404:
    case 405:
        System.out.println("Something is wrong with the request!");
        break;
    case 500:
    case 501:
    case 505:
        System.out.println("Something is wrong with the server!");
        break;
}

// Java 13 Multiple values in a case
switch (errorCode) {
    case 400, 404, 405:
        System.out.println("Something is wrong with the request!");
        break;
    case 500, 501, 505:
        System.out.println("Something is wrong with the server!");
        break;
}
```

2. Switch expression

- 명령문(statement)였던 switch문에 더해 switch식(expression)이 가능해졌다.

```java
String message = switch (errorCode) {
    case 404:
        yield "Not found!";
    case 500:
        yield "Internal server error!";
    default:
        throw new IllegalArgumentException("Unexpected value: " + errorCode);
};
```

- 위의 코드처럼 switch 문의 결과 값을 사용할 수 있다.
- switch expression에서는 yield 키워드 사용(return문과 비슷하며, 뒤에 break를 사용할 필요 없음)

3. Null Pointer Exception Safety

- switch statement와 다르게 switch expression은 모든 가능한 입력에 대해서 case문을 만들어 줘야한다.

    그렇지 않으면 compile error 발생

```java
String message = switch (errorCode) {
    case 404:
        yield "Not found!";
    case 500:
        yield "Internal server error!";
    // default:
    //    throw new IllegalArgumentException("Unexpected value: " + errorCode);
};

결과 : Error:(11, 26) java: the switch expression does not cover all possible
```

4. Switch with arrows

- 이 문법은 switch statement, switch expression에서 모두 사용 가능하다.
- '→' 사용시 fall-through를 하지 않기 때문에 break를 사용할 필요가 없다.

    (fall-through : break를 만날때까지 다음 case문을 실행하는 행위)

```java
switch (errorCode) {
      case 404 -> System.out.println("Not found!");
      case 500 -> System.out.println("Internal server error!");
}
```

- 하나의 스위치 문에서 case : , case → 중 하나만 써야한다.

```java
// Invalid: both 'case:' and 'case ->' in the same switch
switch (errorCode) {
    case 404 -> System.out.println("Not found!");
    case 500: System.out.println("Internal server error!");
}
```

5. Scope

- 하나의 switch문은 단일? 범위였다.

```java
switch (errorCode) {
    case 404:
        System.out.println();
        String message = "Not found!";
        break;
    case 500:
        // Cannot declare 'message', it already exists
        String message = "Internal server error!";
        break;
}
```

- 각 case를 별개의 범위로 사용하려면 { }를 사용해야 한다.

```java
switch (errorCode) {
    case 404: {
        // This variable exists just in this {} block
        String message = "Not found!";
        break;
    }
    case 500: {
        // This is ok, {} block has a separate scope
        String message = "Internal server error!";
        break;
    }
}
```

- '→' 를 사용하면 그 오른쪽은 별개의 범위를 가지기 때문에 충돌의 위험이 사라진다.
