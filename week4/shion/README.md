## 제어문

- 코드의 실행 흐름은 위에서 아래로 한 문장씩 순차적으로 진행되지만, 때로는 조건에 따라 문장을 건너뛰고, 때로는 같은 문장을 반복해서 수행해야할 때가 있다.
- 이처럼 프로그램의 흐름(flow)을 바꾸는 역할을 하는 문장들을 '제어문(control statement)'이라고 한다.

### 1. 선택문(조건문)

---

- 조건문은 조건에 따라 다른 문장이 수행되도록 한다.
- 조건식과 문장을 포함하는 블럭{ }으로 구성되어있다.

**1.1 if문**

---

- 만일(if) 조건식이 참(true)이면 괄호{ } 안의 문장들을 수행한다.

```java
if (조건식) {
    // 조건식이 참(true)일 때 수행될 문장들을 적는다.
}

// example
int score = 80;

if (score > 60) {
    System.out.println("합격입니다.");
}

// 결과
score > 60
-> 80 > 60
-> true 
조건식이 참(true)이므로 괄호{ } 안의 문장이 실행된다.
```

**조건식**

- if문에 사용되는 조건식은 일반적으로 비교연산자와 논리연산자로 구성된다.
- 등가비교 연산자 '==' 대신 대입 연산자 '='를 사용하지 않도록 주의한다.

```java
if (x = 0) { ... } // x에 0이 저장되고, 결과는 0된다.
-> if (0) { ... }  // 결과가 true 또는 false가 아니므로 에러가 발생한다.
```

**블럭 { }**

- 괄호 { }를 이용해서 여러 문장을 하나의 단위로 묶을 수 있다.

```java
if (score > 60) 
{ // 블럭 시작
    System.out.println("합격입니다.");
<--> // 탭(tap)에 의한 들여쓰기를 해서 블럭 안에 속한 문장이라는 것을 알기 쉽게 해주는 것이 좋다.
} // 블럭 끝
```

- 블럭 안에는 보통 여러 문장을 넣지만, 한 문장만 넣거나 아무런 문장도 넣지 않을 수 있다.
- 문장이 하나뿐 일 때는 괄호 { }를 생략할 수 있다.

```java
if (score > 60) 
    System.out.println("합격입니다.");

//또는

if (score > 60) System.out.println("합격입니다.");
```

- 가능하면 괄호 { }를 생략하지 않고 사용하는 것이 가독성에 좋다.

```java
if (score > 60)
    System.out.println("합격입니다.");  // 문장1. if문에 속한 문장
    System.out.println("축하드립니다."); // 문장2. if문에 속한 문장이 아님 
```

**1.2 if-else문**

---

- if문에 'else'블럭이 더 추가되었다.
- 'else'의 뜻은 '그 밖의 다른'이므로 조건식의 결과가 참이 아닐 때, 즉 거짓일 때 else 블럭의 문장을 수행한다.
- 조건식의 결과에 따라 이 두개의 블럭{ } 중 어느 한 블럭{ }의 내용이 수행되고 전체 if문을 벗어나게 된다.

```java
if (조건식) {
    // 조건식이 참(true)일 때 수행될 문장들을 적는다.
} else {
    // 조건식이 거짓(false)일 때 수행될 문장들을 적는다.
}
```

- if-else문 역시 블럭 내의 문장이 하나뿐인 경우 아래와 같이 괄호{ }를 생략할 수 있다.

```java
if (input == 0)
    System.out.println("입력하신 숫자는 0입니다.");
else
    System.out.println("입력하신 숫자는 0이 아닙니다.");
```

**1.3 if-else if문**

---

- 한 문장에 여러개의 조건식을 쓸 수 있다.
- 첫 번째 조건식부터 순서대로 평가해서 결과가 참인 조건식을 만나면, 해당 블럭{ }만 수행하고 'if-else if'문 전체를 벗어난다.
- 만일 결과가 참인 조건식이 하나도 없으면, 마지막에 있는 else블럭의 문장들이 수행된다.
- else 블럭은 생략이 가능하며, 생략되었을 때 if-else if문의 어떤 블럭도 수행되지 않을 수 있다.

```java
if (조건식1) {
    // 조건식1의 연산결과가 참일 때 수행될 문장들을 적는다.
} else if (조건식2) {
    // 조건식2의 연산결과가 참일 때 수행될 문장들을 적는다.
} else if (조건식3) {    // 여러 개의 else if를 사용할 수 있다.       
    // 조건식3의 연산결과가 참일 때 수행될 문장들을 적는다.
} else {    // 마지막에는 보통 else블럭으로 끝나며, else블럭은 생략가능하다.
    // 위의 어느 조건식도 만족하지 않을 때 수행될 문장들을 적는다.
}
```

**1.4 중첩 if문**

---

- if문의 블럭 내에 또 다른 if문을 포함시키는 것이 가능한데 이것을 중첩 if문이라고 부르며 중첩의 횟수에는 거의 제한이 없다.

```java
if (조건식1) {
    // 조건식1의 연산결과가 true일 때 수행될 문장들을 적는다.
    if (조건식2) {
        // 조건식1과 조건식2가 모두 true일 때 수행될 문장들
    } else {
        // 조건식1이 true이고, 조건식2가 false일 때 수행되는 문장들
    }
} else {
    // 조건식1이 false일 때 수행되는 문장들
}
```

- 중첩 if문에서는 바깥쪽의 if문과 안쪽의 if문이 서로 엉켜서 if문과 else블럭의 관계가 의도한 바와 다르게 형성될 수도 있기 때문에 괄호 { }의 생략에 더욱 조심해야 한다.

```java
if (num >=0)
    if (num != 0)
        sign = '+';
else
    sign = '-';

// 언뜻 보기에 else블럭이 바깥쪽의 if문에 속한 것처럼 보이지만, 
// 괄호가 생략되었을 때 else블럭은 가까운 if문에 속한 것으로 간주되기 때문에 
// 실제로는 안쪽 if문의 else블럭이 되어버린다.

if (num >= 0) {
    if (num != 0) {
        sign = '+';
    } else {
        sign = '-';
    }
}

// 이제 else블럭은 어떤 경우에도 수행될 수 없다.
```

**1.5 switch문**

---

- switch문은 단 하나의 조건식으로 많은 경우의 수를 처리할 수 있다.
- switch문은 조건식을 먼저 계산한 다음, 그 결과와 일치하는 cas문으로 이동한다.
- 이동한 case문 아래에 있는 문장들을 수행하며, break문을 만나면 전체 switch문을 빠져나가게 된다.

```java
switch (조건식) {
    case 값1 : 
        // 조건식의 결과가 값1과 같을 경우 수행될 문장들
        // ...
        break;
		case 값2 :
        // 조건식의 결과가 값2와 같은 경우 수행될 문장들
        // ...
        break; // switch문을 벗어난다.
    // ...
    default :
        // 조건식의 결과와 일치하는 case문이 없을 때 수행될 문장들
        // ...
}
```

- switch문의 조건식 결과는 정수 또는 문자열(jdk 1.7부터)이어야한다. (jdk 1.5부터  enum 추가)
- case문의 값은 정수 상수만 가능하며, 중복되지 않아야한다.(jdk 1.7부터 문자열, jdk 1.5부터 enum 추가)

### 2. 반복문

---

- 어떤 작업이 반복적으로 수행되도록할 때 사용된다.
- 주어진 조건을 만족하는 동안 주어진 문장들을 반복적으로 수행한다.

**2.1 for문**

---

```java
for (초기화; 조건식; 증감식) {
    // 조건식이 참일때 수행될 문장들을 적는다.
}

(1) 초기화 -> (2) 조건식 -> (3) 수행될 문장 -> (4) 증감식 -> (2) 다시 조건식으로..
// 조건식이 참인 동안 계속 반복된다.
```

**초기화**

- 반복문에 사용될 변수를 초기화하는 부분이며 처음 한번만 수행된다.
- 보통 변수 하나로 for문을 제어하지만, 둘 이상의 변수가 필요할 때는 콤마','를 구분자로 변수를 초기화 하면 된다.(단, 두 변수의 타입은 같아야 한다.)

```java
for (int i = 0; i <= 10; i++) { ... } // 변수 i의 값을 1로 초기화 한다.
for (int i = 1, j = 0; i <= 10; i++) { ... } // int타입의 변수 i와 j를 선언하고 초기화
```

**조건식**

- 조건식의 값이 참(true)이면 반복을 계속하고, 거짓(false)이면 반복을 중단하고 for문을 벗어난다.

```java
for (int i = 0; i <= 10; i++) { ... } // ' i <= 10 '가 참인 동안 블럭 { } 안의 문장들을 반복
```

**증감식**

- 반복문을 제어하는 변수의 값을 증가 또는 감소시키는 식이다.
- 매 반복마다 변수의 값이 증감식에 의해서 점진적으로 변하다가 결국 조건식이 거짓이 되어 for문을 벗어나게 된다.
- 증감식도 쉼표','를 이용해서 두 문장 이상을 하나로 연결해서 쓸 수 있다.

```java
for (int i = 1; j = 10; i <= 10; i++, j--) { ... } // i는 1부터 10까지 1씩 증가하고
																									 // j는 10부터 1까지 1씩 감소한다.
```

- 지금까지 살펴본 이 세가지 요소는 필요하지 않으면 생략할 수 있으며, 모두 생략하는 것도 가능하다.

```java
for(;;) { ... } // 초기화, 조건식, 증감식 모두 생략. 조건식이 생략된 경우, 참(true)로 간주되어 무한 반복문이 된다.
```

**중첩 for문**

- for문 안에 또 다른 for문을 포함시키는 것도 가능하다.

**향상된 for문**

- JDK 1.5부터 추가되었다.

```java
for (타입 변수명 : 배열 또는 컬렉션) {
		// 반복할 문장
}

int[] arr = {10, 20, 30, 40, 50};

for (int tmp : arr) { // 위 배열의 모든 요소를 출력할 수 있다.
	System.out.println(tmp);
}

```

**2.2 while문**

---

- 조건식이 '참(true)인 동안', 즉 조건식이 거짓이 될 때까지 블럭 { } 내의 문장을 반복한다.

```java
while (조건식) {
    // 조건식의 연산결과가 참(true)인 동안, 반복될 문장들을 적는다.
}
```

**for문과 while문의 비교**

```java
// 초기화, 조건식, 증감식
for (int i = 1; i <= 10; i++) {
    System.out.println(i);
}

int i = 1; // 초기화
while (i <= 10) { // 조건식
    System.out.println(i);
    i++; // 증감식
}

// 위의 두 코드는 완전히 동일하다.
```

**2.3 do-while문**

---

- 블럭 { }을 먼저 수행한 후에 조건식을 평가한다.
- while문은 조건식의 결과에 따라 블럭 { }이 한 번도 수행되지 않을 수 있지만, do-while문은 최소한 한번은 수행될 것을 보장한다.

```java
do {
    // 조건식의 연산결과가 참일 때 수행될 문장들을 적는다.
} while (조건식); // 끝에 ';'을 잊지 않도록 주의
```

**2.4 break문**

---

- break문은 자신이 포함된 가장 가까운 반복문을 벗어난다.

```java
int sum = 0;
int i = 0;

while (true) {
    if (sum > 100)
        break;
    i++;
    sum += i;
}

// 숫자를 1부터 계속 더해 나가서 몇까지 더하면 합이 100을 넘는지 알아낸다.
```

**2.5 continue문**

---

- 반복이 진행되는 도중에 continue문을 만나면 반복문의 끝으로 이동하여 다음 반복으로 넘어간다.
- for문의 경우 증감식으로 이동하며, while문과 do-while문의 경우 조건식으로 이동한다.
- continue문은 반복문 전체를 벗어나지 않고 다음 반복을 계속 수행한다는 점이 break문과 다르다.

```java
for (int i = 0; i <= 10; i++) {
    if (i % 3 == 0) 
        continue;  // 조건식이 참이되어 continue문이 수행되면 블럭의 끝으로 이동한다.
    System.out.println(i);
}

// 3의 배수는 제외되고 출력된다.
```

**2.6 이름 붙은 반복문**

---

- break문은 근접한 단 하나의 반복문만 벗어날 수 있기 때문에, 여러 개의 반복문이 중첩된 경우에는 break문으로 중첩 반복문을 완전히 벗어날 수 없다.
- 중첩 반복문 앞에 이름을 붙이고 break문과 continue문에 이름을 지정해 줌으로써 하나 이상의 반복문을 벗어나거나 반복을 건너 뛸 수 있다.

```java
Loop1 : for (int i = 2; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (j == 5) {
                    break Loop1; // 전체 반복문을 빠져나간다.
                } 
                System.out.println(i + "*" + j + "=" + i * j);	
            }
            System.out.println();
        }

// 결과
2*1=2
2*2=4
2*3=6
2*4=8

// 만일 반복문의 이름이 지정되지 않은 break문이었담녀 2단부터 9단까지 모두 네 줄씩 출력되었을 것이다.
```