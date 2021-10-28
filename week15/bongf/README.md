## 학습 1) 람다식 사용법
### 람다식 
https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
-	익명 클래스가 메서드 한 개만 갖고 있고 그 메서드조차 너무 심플할 때 이 메서드를 그 메서드 자체로 다른 메서드의 인자로 넘겨주고자 할 때 람다식을 사용한다. 그러니까 람다식은 단일 메소드를 가진 클래스를 더 압축적으로 표현하게 해준다. 
-	자바의 정석에 따르면 람다식은 메서드를 하나의 식으로 표현한 것. 메서드를 람다식으로 표현하면 메서드의 이름과 반환 값이 없어져 익명 함수(anonymous function)이라고 표현한기도 한다. 
```java 
//자바의 정석 코드 
int[] arr = new int[5];
Arrays.setAll(arr, (i) -> (int) (Math.random() * 5) +1);

int methond(int i) {
	return (int) (Math.random() * 5) +1);
}
```
-	자바의 정석에 보면 람다는 익명 함수라기 보다는 사실 익명 클래스의 객체라고 한다. (위에 독스에서도 (익명 클래스의 메서드가 단순할 때 람다를 쓴다는 말에서) 익명 클래스를 강조했다고 느꼈다)
```java
(int a, int b) -> a > b ? a : b

// 사실 아래 익명 클래스 객체와 같다. 
new Object() {
	int max(int a, int b) {
    	return a > b ? a : b; 
    }
}
```
-	https://www.baeldung.com/java-8-functional-interfaces 자바 8부터 람다로 엄청난 변화를 가져왔다. 람다로 메소드에 제공하거나 람다를 return 할 수 있게 되면서 익명 함수를 일급 객체로 사용할 수 있게 된 것에서 의미가 크다. 어떻게 이를 메서드에 제공하고 return 하는지는 아래에서 알아보도록 하자. 

### 람다식 사용법 
- 자바의 정석 
-	메서드에서 이름과 반환 타입을 제거하고 매개변수 선언부와 몸통{} 사이에 `->`를 추가하면 된다. 
```java
int max(int a, int b) {
	//수행 내용 
}

(int a, int b) -> {
	//수행 내용
}
```
-	반환값이 있는 메서드라면 return 문 대신 식 자체가 메서드가 된다. 
	-	식의 연산 결과가 자동으로 반환 값이 된다. 문장이 아니라 식이기 때문에 `;`를 붙이지 않는다. 
    -	return을 붙이는 형태도 가능 하다. 
```java
int add(int a, int b) {
	return a + b; 
}

(int a, int b) -> a + b

(int a, int b) -> {
	return a+b; 
}
```
-	람다식에 선언된 매개변수 추론이 가능한 경우는 생략할 수 있다. (대부분의 경우에 생략가능) 
```java
//생략 전 
(int a, int b) -> a > b ? a : b
//생략 후 
(a, b) -> a > b ? a : b 
```
-	선언된 매개변수가 하나라면 괄호() 를 생략 할 수 있다. 
```java
//생략 전 
(a) -> a * 2
//생략 후 
a -> a * 2
```
- 매개변수에 선언된 타입이 있으면 생략 불가 
```java
//() 생략 불가 
(int a) -> a * 2 
```
- `{ }` 안에 문장이 한 개라면 생각 가능, return문일 경우 생략 불가 

```java
(int a, int b) -> {a+b;}

//생략 후 ( ; 를 붙이지 않기!) 
(int a, int b) -> a + b

(int a, int b) -> {return a+b;}

//에러. 불가한 코드. 
(int a, int b) -> return a+b; 

```


## 학습 2) 함수형 인터페이스
### 함수형 인터페이스가 왜 필요한지? 
위에서 람다식은 사실 익명 클래스의 객체라고 했다. 이 친구를 받아줄 때 타입과 변수로 받아줘야 할 것이다. 이 때 필요한 것이 함수형 인터페이스 
https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#approach6
```java
interface CheckPerson {
    boolean test(Person p);
}
```
-	이 인터페이스는 너무 간단해서 따로 정의하기가 애매하다.  그래서 JDK는 몇몇 **표준 함수형 인터페이스**들을 제공하고 있다. 
```java
interface Predicate<T> {
    boolean test(T t);
}
```
-	실제로 `Predicate<T>` 를 호출하면 아래와 같이 오버라이딩 하라고 자동 완성된다 ![](https://images.velog.io/images/bongf/post/f6670fc5-869c-407c-819b-1d4ace15cad1/image.png)  ![](https://images.velog.io/images/bongf/post/33738af5-e6fb-49d1-a2fb-59ece5e783f9/image.png)
-	저기서 `new Predicate<Integer>` 한 부분이 인터페이스를 구현한 익명 클래스의 객체를 생성한 것이다. 
-	이 익명 객체를 이제 변수로 받았으니 쉽게 호출 할 수 있다. 
![](https://images.velog.io/images/bongf/post/24278126-74f9-42a4-98fd-9946d97f49bf/image.png)
-	위의 함수형 인터페이스를 구현하는 것을 람다식으로도 할 수 있다. 람다식도 사실 익명 객체이기 때문 ![](https://images.velog.io/images/bongf/post/e1114812-13ea-4afd-9243-120dd4ce5efc/image.png) 
-	이렇게 되면 람다식을 다룰 참조변수가 만들어 진 것이다. 이런 인터페이스는 함수형 인터페이스이다. 

### 함수형 인터페이스란 
https://www.baeldung.com/java-8-functional-interfaces
- 함수형 인터페이스란 단 하나의 추상 메서드를 가진 인터페이스이다. 
-	위에서 본 것처럼 함수형 인터페이스 구현은 람다를 통해 가능하다.
-	어노테이션 ` @FunctionalInterface`을 사용한다. 
	-	https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.6.4.9 에 따르면 이는 함수형 인터페이스를 나타내기 위해 사용한다. 부적절한 메소드 선언을 잡아낸다. 그러나 반드시 모든 함수형 인터페이스에 이 어노테이션을 붙여야 하는 것은 아니라고 한다. 
    -	실제로 어노테이션을 안붙여도 잘 작동한다 ![](https://images.velog.io/images/bongf/post/78e9d1ec-2619-4ce0-bd5e-fbf63212246a/image.png)
-	(자바의정석) 함수형 인터페이스의 메서드와 람다식의 매개변수 개수와 반환 타입이 일치해야 한다. 

### 함수형 인터페이스 : 메서드를 통해 람다식을 주고 받을 수 있다.
#### 파라미터로 전달 
-	이런식으로 함수형 인터페이스를 파라미터로 전달받는 메서드가 있다고 했을 때 ![](https://images.velog.io/images/bongf/post/20edcd4e-20d5-42dd-ba6b-5c0beeb19594/image.png) 이런식으로 람다식을 참조하는 참조변수를 매개변수로 지정해야 한다. 
-	![](https://images.velog.io/images/bongf/post/2e98a66e-ed3e-4616-b9a5-881d3e90ac3a/image.png) 
-	아니면 람다식을 인자로 넣는 직접 넣는 것도 가능하다. 
-	![](https://images.velog.io/images/bongf/post/24bcd6a0-b472-4070-90b6-33d1618dfdfe/image.png)
#### return으로 받기 
-	반환 타입으로 함수형 인터페이스를 선언하면 구현해야 하는 메서드를 람다로 직접 return 할 수 있다. 
![](https://images.velog.io/images/bongf/post/53a4a826-689c-4cda-a31b-d2be0f681b6b/image.png)
-	이렇게 메서드를 변수처럼 주고 받는 것이 가능하고, 익명 함수를 일급 객체로 사용할 수 있게 된 것에서 의미가 크다고 한다. 실제로는 메서드가 아니라 익명 객체를 주고 받는 것이지만 코드가 훨씬 간결해진 장점이 있다. 


## 학습 3) Variable Capture
-	람다식 내부에서 람다식 외부에 선언된 변수에 접근 할 수 있는 것 
-	static 변수, 인스턴스 변수, 지역 변수에 접근가능하다. 
-	람다 안에서 사용되는 지역변수, 파라미터로 넘어오는 변수는 final 이어야 한다. 
	-	좀 더 엄밀히 얘기하면 지역변수는 fianl, effectively final 이어야 한다. https://watrv41.gitbook.io/devbook/java/java-live-study/15_week 에 따르면 effectively final은 람다식 내부에서 외부 지역변수를 참조하였을 때 지역변수를 재할당을 하지 않아야 하는 것이라고 한다. 
    -	그렇기 때문에 static 변수, 인스턴스 변수는 람다식 내에서 변경이 가능하나 지역변수는 변경하지 못한다. 
    -	다음은 static 변수를 변경한 예 ![](https://images.velog.io/images/bongf/post/85c0ef6f-b32c-4d8a-9b36-244553573219/image.png)
    -	https://www.notion.so/758e363f9fb04872a604999f8af6a1ae 를 참고하여 왜 final이어야 하는지 좀 더 자세히 설명을 적으면 이는 인스턴스 변수나 static 변수는 힙 영역에 위치해 모든 쓰레드가 공유하기 때문에 변경이 가능한 것 
    	-	그러나 지역변수는 스택영역에 있다. 메소드 내부에서 새로운 객체를 생성했을 때(힙영역) 이 객체가 메서드 내부의 지역변수나 매개변수를 사용할 경우 스택 영역에 있던 이들은 없어졌을 수도 있다. 그래서 컴파일 시점에 메소드 매개변수나 지역변수를 메서드 내부에서 생성된 객체가 사용할 경우 이 값들을 복사해서 사용하는데, final이거나  effectively final인 값만 복사하여 사용. 이 것이 `variable capture`이다. final 이고 final 처럼 사용해야 되기 때문에 위에서 말한 것처럼 람다식 내부에서 지역변수를 변경하지 못한다. 
    
```java
public class VariableCapture {
    private static int x = 2;
    private int y = 1;
    public static void main(String[] args) {
        AInterface add = i -> i + x; // static 변수 접근 가능

        int a = 1;
        for (int i = 0; i < 5; i++) {
            a++;
        }
        int[] test = new int[]{1, 2, 3};
//        Arrays.stream(test).map(x -> x + a).getAsInt(); //에러. final이 아니다. 

    }

    public void accessInstanceVariable() {
        AInterface subtract = i -> i - y; // 인스턴스 변수 접근 가능
    }

    public static void accessLocalVariable() {
        int z = 1;
        AInterface divde = i -> i / z; // 로컬변수 접근 가능
        AInterface notFianl = (int x) -> {
//            z = z*z; //에러
            return  z;
        };
    }
}

@FunctionalInterface
interface AInterface {
    int operate(int x);
}
```
-	위의 스트림 사용한 람다의 에러 화면. 스트림 쓸 때 종종 마주했다. ![](https://images.velog.io/images/bongf/post/3b445ce5-6a4c-40b6-a57a-ac1f0bdcf89f/image.png) 이제 왜 그런지 이해했다. 
-	아래는 이런 Variable Capture 을 잘 정리해 둔 것 같아 docs tutorial 에서 가져 왔다. 그리고 위에 출처로 표기한 블로그들에서 이해한 바를 덧붙였다. 
```java
package bongf.week15.study;

import java.util.function.Consumer;

public class LambdaScopeTest {

    public int x = 0;

    class FirstLevel {

        public int x = 1;

        void methodInFirstLevel(int x) {

            int z = 2;

            //  여기서 y대신 x나, z로 바꾸면 에러 난다.
            //  "Lambda expression's parameter x cannot redeclare another local variable defined in an enclosing scope"
            //   외부함수의 범위(enclosing scope)에서 정의된 지역 변수 x를 재 정의 할 수 없다.
            //	람다는 람다를 감싸고 있는 메소드와 같은 스코프이기 때문에
            //	이미 z나 x를 정의한 것으로 인식한다.
            //	만약 이를 익명 클래스로 작성하면 에러가 발생하지 않는다.(아래 testShadowing) 익명 클래스는 쉐도잉 이슈가 있지만 람다는 없다.
            //	자세한 내용은 https://www.notion.so/758e363f9fb04872a604999f8af6a1ae
            Consumer<Integer> myConsumer = (y) ->
            {
                // The following statement causes the compiler to generate
                // the error "Local variable z defined in an enclosing scope
                // must be final or effectively final"
                //
                // z = 99;

                System.out.println("x = " + x); // 23
                System.out.println("y = " + y); // 23 매개변수로 넣어준
                System.out.println("z = " + z); // 2
                System.out.println("this.x = " + this.x); // 1
                System.out.println("LambdaScopeTest.this.x = " +
                        LambdaScopeTest.this.x); //0
            };

            myConsumer.accept(x);

            // 익명 클래스로 작성하면 'z'라고 써도 문제가 없다.
            Consumer<Integer> testShadowing = new Consumer<Integer>() {
                @Override
                public void accept(Integer z) {
                    System.out.println(z);
                }
            };

        }
    }

    public static void main(String... args) {
        LambdaScopeTest st = new LambdaScopeTest();
        LambdaScopeTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}


```

## 학습 4) 메소드, 생성자 레퍼런스
-	출처 : 자바의 정석 
### 메소드 참조 Method Reference 
-	람다식이 하나의 메서드만 호출하는 경우 메서드 레퍼런스를 이용할 수 있다. 
-	`클래스이름::메서드이름` 혹은`참조변수::메서드이름`으로 바꿀 수 있다.
```java
(x) -> ClassName.method(x) 
(obj, x) -> obj.method(x)
(x) -> obj.method(x) 

//메서드 참조 후 
ClassName::method //클래스의 static 메서드 참조 
ClassName::method //인스턴스 메서드 참조 
obj::method //특정 객체의 인스턴스 메서드 참조 
```
-	간편해서 스트림에서 종종 썼다. ![](https://images.velog.io/images/bongf/post/2793b7ac-c8ae-4448-aaf7-6105d4b0b96d/image.png)

### 생성자 참조 
-	생성자를 호출하는 람다식도 메서드 참조로 변환할 수 있다. 
```
() -> new AClass();
() -> AClass::new

```
