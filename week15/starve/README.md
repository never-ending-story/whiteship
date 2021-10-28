## 람다식 사용법

####  람다식(Lambda Expression)

- 메서드를 하나의 '식(expression)'으로 표현한 것.
- 함수를 간략하고 명확하게 표현할 수 있게 해준다.
- 메서드의 이름과 반환값이 없어지기 때문에, 람다식을 '익명 함수(anonymous function)'라고도 한다.

```java
int[] arr = new int[5];
Arrays.setAll(arr, (i) -> (int)(Math.random()*5)+1);

//아래의 메서드와 동일한 작업을 한다.
int method(int i) {
  return (int)(Math.random()*5)+1;
}
```

- 람다식은 새로은 클래스를 만들고, 메서드를 만드는 일련의 과정을 거치지 않고 람다식 자체로 메서드의 역할을 대신 할 수 있는 메리트를 가진다.
- 메서드의 매개변수로 전달되는 것은 물론, 메서드의 결과로 반환될 수 있어 메서드를 변수처럼 다루는 것이 가능해졌다.
- 보통 runnable 혹은 stream사용 시 자주 사용된다.



### 람다식 작성하기

- 메서드에서 이름과 반환타입을 제거한 뒤, 매개변수 선언부와 몸통 사이에 '->'를 추가하면 된다.

  ```java
  // 원본
  int max(int a, int b) {
    return a>b? a:b;
  }
  
  // 람다식
  (int a, int b) -> { return a > b ? a : b; }
  
  // 반환 값이 있는 메서드의 경우, return문 대신 '식(expression)'으로대신할 수 있다.
  // '식'이므로 세미콜론(;)을 붙이지 않아도 되며, 계산결과가 자동 반환된다.
  (int a, int b) -> a > b ? a : b
    
  // 매개변수의 타입을 추론 가능 할 경우, 생략 가능하다.
  // (대부분 가능. 반환타입이 없는 이유도 추론이 가능하기 때문)
  (a , b) -> a > b? a : b
    
  // 매개변수가 하나 뿐인 경우, 괄호 생략 가능.
  // 단, 매개변수의 타입이 있을 경우 괄호 생략 불가능.
  (a) -> a * a     // OK
  (int a) -> a * a //에러
    
  // 대괄호 안의 문장이 하나일 때는 대괄호 생략 가능.
  // 문장 끝에 세미콜론을 안붙이는 것을 유의 하자.
  (String name, int i) -> {
    System.out.println(name + "=" + i);
  }
  
  (String name, int i) -> System.out.println(name + "=" + i);
  
  // 단, 대괄호 내의 문장이 return문 일 경우, 생략 불가능
  (int a, int b) -> { return a > b ? a : b; } // OK
  (int a, int b) ->  return a > b ? a : b // 에러
  ```

- 람다식의 예

  - 메서드1:

    ```java
    (int a, int b) -> { return a > b ? a : b; }
    ```

    람다식1:

    ```java
    (int a, int b) -> a > b ? a : b
      
    (a , b) -> a > b? a : b
    ```

  - 메서드2:

    ```java
    void printVar(String name, int i){
      System.out.println(name + "=" + i);
    }
    ```

    람다식2:

    ```java
    (String name, int i) -> {System.out.println(name + "=" + i);}
    
    (name, i) -> {System.out.println(name + "=" + i);}
    
    (name, i) -> System.out.println(name + "=" + i)
    ```

  - 메서드3:

    ```java
    int square(int x){
      return x * x;
    }
    ```

    람다식3:

    ```java
    (int x) -> x * x 
    (x) -> x * x
    x -> x * x 
    ```

  - 메서드4:

    ```java
    int roll(){
      return (int)(Math.random()*6);
    }
    ```

    람다식4:

    ```java
    () -> { return (int)(Math.random()*6); }
    () -> (int)(Math.random()*6);
    ```

  - 메서드5:

    ```java
    int sumArr(int[] arr){
      int sum = 0;
      for(int i : arr)
        sum += i;
      return sum;
    }
    ```

    람다식5:

    ```java
    (int[] arr) -> {
      int sum = 0;
      for(int i : arr)
        sum += i;
      return sum;
    }
    ```

## 함수형 인터페이스

- 위에서 거론된 Runnable이 대표적 함수형 인터페이스이며, 함수형 인터페이스는 하나의 메소드만 있는 것을 의미한다.

- **인터페이스를 구현한 익명 객체를 람다식으로 대체할 수 있다.** 이 것이 되는 이유는, 람다식 또한 실제로 보면 익명 객체이고, 인터페이스를 구현한 익명 객체의 메서드와 람다식의 매개 변수의 타입, 개수 그리고 반환값이 일치하다면 문제가 없기 때문이다.

- 하나의 메서드가 선언된 인터페이스를 정의하여 람다식을 다루는 것은, 자바의 기존 규칙들을 어기지 않으면서도 자연스럽기 때문에 인터페이스를 통해 람다식을 다루기로 결정 되었다.

  - 그리고 이렇게 람다식을 다루기 위한 인터페이스를 **'함수형 인터페이스(Functional Interface)'**라고 한다.
  - 단, 함수형 인터페이스엔 오직 하나의 추상 메서드만 정의되어야 한다는 제약이 있다. 그래야 람다식과 인터페이스의 메서드가 1:1로 연결될 수 있기 때문이다.

- @FunctionalInterface 어노테이션을 사용한다.

  ```java
  @FunctionalInterface
  interface MyFunction {
      void foo(); // 추상 메서드
  }
  
  void bar(MyFunction f) { // 메서드 호출 시, 람다식을 참조하는 참조변수를 매개변수로 지정해야함.
      f.foo();
  }
  
  bar(() -> System.out.println("Hello"));
  ```



#### 미리 정의 해 둔 function 패키지

* 일반적으로 자주 사용되는 형식의 메서드를 함수형 인터페이스로 정의 해 놓은 것.
* 되도록 기존에 있는 메소드를 사용하는 것이 좋다.
* 매개변수가 두 개인 함수형 인터페이스의 경우, 이름 앞에 'Bi'가 붙는다.
* Runnable을 제외하고 java.util.function 패키지 안에 있다.

| 인터페이스          | 메소드            | 매개변수 | 리턴 값 | 설명                                                  |
| ------------------- | ----------------- | -------- | ------- | ----------------------------------------------------- |
| Runnable            | void run()        | 0        | void    | 매개변수가 없고 리턴값도 없음                         |
| Supplier            | T get()           | 0        | T       | 매개변수만 없고 리턴값은 있음                         |
| Consumer            | void accept(T t)  | 1        | void    | 매개변수만 있고 리턴값은 없음                         |
| Function<T, R>      | R apply(T t)      | 1        | R       | 하나의 매개변수만 받아서 결과 반환. 일반 메소드       |
| Predicate           | boolean test(T t) | 1        | boolean | 하나의 매개변수를 받아서 boolean 결과 값 반환. 조건식 |
| BiFunction<T, U, R> | R apply(T t, U u) | 2        | R       | 두 개의 매개변수를 받아, 하나의 결과 반환.            |

## Variable Capture

* 특정 상황에서 람다 함수에 선언된 변수에 접근 할 수 있는데, 변수에 접근 할 수 있는 유형은 다음과 같다.
  * 지역변수
  * 인스턴스 변수
  * 클래스 변수(static 변수)

#### 지역 변수

* 함수 본문의 외부에 선언된 지역 변수의 값에 접근할 수 있다.

* 단, 변경되지 않는 것이 확인 된 변수만 접근해 람다에서 값을 조작할 수 있는 것이다.(final)

* 컴파일러가 final임을 알수있도록 키워드를 입력해주는 것이 필요하다.

  ```java
  int i = 5;
  Supplier<String> x = () -> {
      i = i * i; //error. i는 final로 지정되어있어야한다.
      return "hello " + i;
  };
  ```

  

#### 인스턴스 변수

* 인스턴스 변수가 바뀌면 람다식이 참조하는 변수도 바뀐다.
* 익명 클래스는 자체적인 인스턴스 변수를 가지고 있는 것에 비해, 자체적인 변수가 없어 참조 변수가 변경되면 값이 변경되는 것이다.

```java
public class Ex {
    private String name = "starve";
    
    public void attach(MyEvent myEvent) {
        myEvent.listen(e -> {
            System.out.println(this.name);
        })
    }
}
```



#### 클래스 변수

* 클래스 변수(static)에도 접근 할 수 있으나, 이는 람다가 아니어도 접근 할 수 있기 때문에 추가적인 설명은 하지않는다. 



## 메소드, 생성자 레퍼런스

#### 메소드 레퍼런스

* 람다식이 하나의 메서드만 호출하는 경우엔, 메서드 참조(method reference)라는 방법으로 람다식을 간략히 할 수 있다.

  ```java
  Function<String, Integer> f = (String s) -> Integer.parseInt(s);
  Function<String, Integer> f2 = Integer::parseInt; // 변경한 것.
  // 참조변수::메서드 이름 혹은 클래스이름::메서드 이름 형식
  ```

* 위와 같이 람다식을 정의 할 경우, 컴파일러는 생략된 부분을 우변의 parseInt 메서드의 선언부로 부터나 좌변의 Funtion인터페이스에 지정된 지네릭 타입으로 알 수 있다.

* 메서드 레퍼런스가 가능 한 경우

  * static 메서드 참조 - 클래스명::메소드명
  * 인스턴스메서드 참조 - 클래스명::메소드명
  * 특정 객체의 인스턴스메서드 참조 - 참조변수명::메서드명



#### 생성자 레퍼런스

* 생성자를 호출하는 람다식도 메서드 참조로 변환시킬 수 있다.

* 매개변수가 있는 생성자의 경우, 매개변수의 개수에 따라 알맞은 함수형 인터페이스를 사용하면 된다.
  필요에 따라 함수형 인터페이스를 새로 정의해야할 수 도 있다.

  ```java
  Supplier<MyClass> s = () -> new MyClass(); // 람다식
  Supplier<MyClass> s2 = MyClass::new;	  // 메서드 참조
  
  BiFunction<Integer, String, MyClass> bf = (i, s) -> new MyClass(i, s);
  BiFunction<Integer, String, MyClass> bf2 = MyClass::new; // 메서드 참조
  
  //배열 생성
  Function<Integer, int[]> f = x -> new int[x];
  Function<Integer, int[]> f2 = int[]::new;
  ```

* 메서드 참조는 static변수를 사용하는 것 처럼 람다식을 다룰수 있게 되어, 코드를 간략히 할 수 있는 장점이 있다.