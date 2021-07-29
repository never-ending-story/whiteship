## 목표
-   자바가 제공하는 다양한 연산자 학습하기
## 1) 연산 
### 용어 
- `연산자` : 연산을 수행하는 기호 
- `피연산자` : 연산의 대상 

## 2) 산술연산자
### 산술연산자의 종류 
-	사칙연산과 나머지(모듈)연산

-   `+` `-` `*` `/` `%` 
### 산술변환

-   연산 전에 피연산자 타입 일치를 위해 자동 형변환 되는 것
-   (1) 보다 범위가 큰 타입으로 일치된다 
```
System.out.println(4/2); // 2 
System.out.println(4/2f); // 2.0 ((2f)실수라는 큰 타입으로 일치)
```
-	(2) 연산 결과가 int보다 작은 범위의 타입이면 int 형식으로 변환된 다음에 연산이 들어간다 
	 -	JVM 이 기본적으로 32비트 단위로 계산 (int는 4바이트)
     -	`byte a + byte b` 는 `int a + int b` 로 변환되어 결과가  `int c`가 나온다  
```
  byte a = 3;
  byte b = 4;
  byte c = a + b; //컴파일에러. a+b의 결과는 int
```
     
### 산술연산시 주의
-	1) 오버플로우
    -	연산 후 산출 값이 산출 타입으로 충분히 표현가능한지 확인해야 한다.
-	2)	`/`, `%` 연산자는 0으로 나눌수 없다. 
	-	ArithmeticException 발생 
    -	실수형을 0으로 나누거나 정수를 0.0(실수) 으로 나눌경우 Infinity, NaN(Not a Number) 결과값을 가진다 
    -	```System.out.println(3%0.0); // 결과값 NaN``` 
    -	```System.out.println(3/0.0); // 결과값 Infinity```

## 3) 비트연산자
###  비트 연산?
> 한 개 혹은 두개의 이진수에 대해 비트 단위로 적용되는 연산 [출처 : 위키백과](https://ko.wikipedia.org/wiki/%EB%B9%84%ED%8A%B8_%EC%97%B0%EC%82%B0)
### 비트연산자 종류1
-   `NOT(~)` : 반대로 바꿔준다. 0을 1로. 1을 0으로.
-   `0R(|)`: 두 값의 각 자릿수 비교해서 하나라도 1이 있으면 1. 그 외는 0.
-   `XOR(^)` : 두 값의 각 자릿수 비교해서 같으면 0, 다르면1  
-   `AND(&)` : 두 값의 각 자릿수 모두 1일 때만 1

|x|y| x&#124;y | x&y | x^y |
|---|---|---|---|---|
|1|1|1|1|0|
|1|0|1|0|1|
|0|1|1|0|1|
|0|0|0|0|0|

### 비트연산자 종류2 : 시프트연산 
-	비트를 오른쪽 ``>>`` 또는 왼쪽 `<<` 으로 이동시켜 해당 숫자를 곱하고 나누는 결과를 만들어 내는 연산  
-   `x << n` : x를 n비트 만큼 좌(<<)로 이동하고 저장범위 벗어난 값은 버려지며 오른쪽 빈 값은 0으로 채워준다
	-	x에 2의 n승으로 곱해준 것과 같다 
-   `x >> n` : x를 n비트 만큼 우(>>)로 이동하라는 의미, 맨 앞의 수(빈자리)는 0 또는 1 (원래의 부호로)
    -   x에 2의 n승 만큼 나눠준 것과 같다 (좌일경우) (우일경우 : 2의 -n승)
```
System.out.println( Integer.toBinaryString(8 >> 0));
//결과값 1000
System.out.println( Integer.toBinaryString(8 >> 2));
//결과값 10
```
-	`x >>> n` : x를 n비트만큼 우(>>)로 하라는 의미에서 `>>`과 같으나 최상위비트는 항상 0이다. (양수만) 
```
 System.out.println( Integer.toBinaryString(-153 >> 2)); 
 //결과값 11111111111111111111111111011001
 System.out.println( Integer.toBinaryString(-153 >>> 2));
 //결과값 111111111111111111111111011001
```
-   어떤 메모리의 값을 2의 배수만큼 곱하거나 나눌 때 산술연산자보다 이렇게 처리하는 것이 더 빠르다. 하지만 가독성이 떨어진다. 


## 4) 관계 연산자

### 관계 연산?

-   두 피연산자를 비교하는 연산, 연산 결과는 오직 `true` or `false`
-   이항 연산자이므로 타입이 다를 경우 큰 쪽으로 자동 형변환
-   주로 조건문, 반복문에 사용된다

### 관계 연산자 종류 
-   대소 비교 연산자 `<` `>` `<=` `>=`  
    - 참조형에서는 비교 불가 
-   등가 비교 연산자 `==` `!=`  
    - 만약 String끼리 비교하고자 하면 String은 클래스로 각자 다른 객체가 문자열 마다 생성되므로, 각기다른 주소값을 가진다. 따라서 주소값을 비교하게 되므로 String의 동등성 비교할 때는 equals()를 써야 한다.

## 5) 논리 연산자

### 논리연산?

-   참 또는 거짓의 값을 가지는 조건을 and나 or로 연결하여 하나의 식으로 표현, 연산

### 논리연산자 종류

-   `&&` : AND 두 피연산자 모두 true일 때 true
-   `||` : OR 두 피연산자 중 한 쪽이 true면 true
-   `&&`가 `||`보다 우선순위가 높다

### 논리부정연산자 !

-   true를 false로 false를 true로

### Short Circuit Evaluation : &&과 &, ||과 |

-   `&&`과 `||`은 Short Circuit Evaluation이고 `&`과 `|`는 아니다
-   Short Circuit Evaluation은 첫번째 연산자의 값만 보고도 결과가 나올 때 두번째 피연산자는 고려하지 않는다.
	-   `&&` 일 때 `false &&` 면 두 번째 피연산자 보지 않고 무조건 `false`
	-   `||` 일 때 `true ||` 면 두 번째 피연산자 보지 않고 무조건 `true`
-   `&`과 `|`는 무조건 두 피연산자 다 확인 

## 6) instanceOf

-   참조변수가 참조하고 있는 인스턴스의 타입을 알아보기 위한 연산자
-	객체가 클래스의 인스턴스인지, 하위 클래스의 인스턴스인지, 특정 인터페이스를 구현하는 클래스의 인스턴스인지 확인할 때 사용할 수 있다. 
-	주로 조건문에 활용된다. 
-   `A instance of B` : A값의 타입이 B로 형변환 할 수 있다면 `true`  
	-   `instanceOf` 가 참이라도 조상타입의 참조변수로 자식 객체를 참조할 수 있기 때문에 실제 타입은 다를 수 있다.  

## 7) assignment(=) operator : 대입연산자

-   우변의 값을 좌변의 피연산자에 할당
-   연산자들 중 가장 낮은 우선순위, 식에서 맨 나중에 수행
-   방향이 오른쪽에서 왼쪽 (`x=y=1`이면 `y=1`먼저)
-   왼쪽 피연산자를 `lvalue(leftvalue)` 오른쪽피연산자를 `rvalue(rightvalue)`라고 함  
-   `lvalue` 에는 변수처럼 값을 변경할 수 있는 것만 가능, 리터럴이나 상수는 불가능

## 8) 화살표(->) 연산자
- JAVA 8 이후부터 도입되는 람다 표현식에 사용
-	`(Parameters) -> { Body }` 이런식

## 9) 3항 연산자

-   `조건식 ? 식1 : 식2`
-   조건문이 `true`라면 식1 반환, `false` 라면 식2 반환.
-   조건문에 `()`를 치기도 하고 생략하기도 한다.
-   `if문`으로 바꿔 쓸 수 있다.
-   식1과 식2의 피연산자의 타입이 다른 경우 이항연산자에서처럼 산술변환이 발생한다.  
	-	`a < 0.5 ? 1 : 0.5` 이라면 1과 0.5 중 1만 int이므로 1.0으로 산술변환된다.

## 10) 연산자 우선순위
-   대부분 상식선에서 이해 가능하다 
-   `단항` - `산술` - `비교` - `논리` - `삼항` - `대입`
-	아래 표에서 상위에 있는 것이 연산 순위가 높다 
	-	동일선상에 있는 것은 동일한 우선순위 
-	헷갈린다면 `()` 를 쳐주는 것이 좋다 

|종류|연산자|
|---|---|
|후위표기식|expr++ expr--|
|단항연산자|++expr --expr +expr -expr ~ !|
|곱셈, 나눗셈|	* / %|
|덧셈, 뺄셈| + -| 
|비트(시프트)|<<, >>,  >>>|
|관계비교|<, >, <=, >=, instanceof|
|동등비교|	==,  !=|
|비트 AND|	&|
|비트 XOR|^|
|비트 OR|	&#124;|
|논리 AND|&&|
|논리 OR|	&#124; &#124;|
|삼항연산자|?:|
|대입연산자|	=, +=, -=, \*=, /=, %=, &=, ^=,  &#124;=, <<=, >>=, >>>= |

## 11) Java 13. switch 연산자

### switch란?

````
switch (조건식) {
    case 값1: 
    	.. break; 
    case 값2: 
    	... break; 
    ...
    default:
}
````
-   if문은 결과가 참, 거짓 밖에 없기 때문에 경우의 수가 많아질수록 복잡해진다. 
	-	```if x = a; if x = b;``` 
    -	따라서 결과 값을 지정해서 비교할 수 있는 switch문을 사용 ```case a; case b;```
    -	이에 관한 예시 https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html
-   조건식을 계산하고 결과값에 따라 case문으로 이동, break만나면 switch문 빠져 나가고 case에 일치하는 값이 없는 결과값이 나오면 default문으로 간다. default는 else와 같아서 break 필요 없다.
-   만일 break문을 생략하면 조건문을 빠져나가지 못하기때문에 빠트리지 않도록 주의해야 한다. 

### JAVA 12부터? (몇부터인지 정확히 알 수 없으나) 
-   람다식을 사용할 수 있다. `case a -> {}`
	-	break 생략 가능 
-	case에 복수의 값을 설정할 수 있다. `case 1, 2:` 

### JAVA 13부터
https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html
-   `break` 대신에 `yield` 로 case문 안에서 return이 가능하다
```
case a -> {
	yield 8; 
}
