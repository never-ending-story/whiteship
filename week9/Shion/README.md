### 1. 예외 처리
```
에러 : 메모리 부족이나, 스택오버플로우와 같이 일단 발생하면 복구할 수 없는 심각한 오류.
예외 : 발생되더라도 프로그램 코드에 의해 수습될 수 있는 다소 미약한 오류
```
- 프로그램 실행도중에 발생하는 에러는 어쩔 수 없지만, 예외는 프로그래머가 처리해주어야 한다.

- ***예외처리*** : `프로그램 실행 시 발생할 수 있는 예외에 대비한 코드를 작성하여, 프로그램의 비정상 종료를 막고, 정상적인 실행상태를 유지하는 것`
  #### A) ***try-catch***
    
    ```java
    try {
        // 예외가 발생할 가능성이 있는 문장들을 넣는다.
    } catch (Exception1 e1) {
        // Exception1이 발생했을 경우, 이를 처리하기 위한 문장들을 적는다. 
    } catch (Exception2 e2) {
        // Exception2이 발생했을 경우, 이를 처리하기 위한 문장들을 적는다.
    } catch (Exception3 e3) {
        // Exception3이 발생했을 경우, 이를 처리하기 위한 문장들을 적는다.
    }
    ```
    - **try블럭 내에서 예외가 발생한 경우**, 발생한 에외와 일치하는 catch블럭이 있는지 ***instanceof 연산자***를 사용하여 확인한다. 
    - 일치하는 catch블럭을 찾게 되면, 그 catch블럭 내의 문장들을 수행하고 전체 try-catch문을 빠져나가서 그 다음 문장을 계속해서 수행한다.
    - ***instanceof 연산자***를 사용하기 때문에 조상클래스도 catch할 수 있다.
    - 만약 일치하는 catch블럭을 찾지 못하면, 예외는 처리되지 못하한다.
    - **try블럭 내에서 예외가 발생하지 않은 경우**, catch블럭을 거치지 않고 전체 try-catch문을 빠져나가서 수행을 계속한다.
  
    - ***멀티 catch***
      - JDK1.7부터 `|`기호를 이용해서 여러 예외를 하나의 catch블럭으로 합칠 수 있게 되었다.
      ```java
      try {

      } catch (Exception1 | Exception2 e) {

      }
      ```

      ```java
        try {
          
        } catch (Exception | NumberFormatException e) {
            // 두 예외가 서로 상속 관계에 있다면, 조상 클래스에만 catch되므로 컴파일 에러가 발생한다.
        }
      ```
  #### B) ***throw***
    ```java
    throw new Exception();
    ```
    `프로그래머가 예외객체를 생성해서 고의로 예외를 발생시킬 수 있다.`
  
  #### C) ***throws***
  ```java
  void method() throws Exception1, Exception2, ... ExceptionN {

  }
  ```
  - throws를 사용하여 메소드에 예외를 선언하게 되면 이 메소드를 호출한 곳에서 해당 예외를 처리해주어야한다.
  - throws를 사용함으로써 이 메소드를 사용할 때, 어떤 예외를 처리해주어야 하는지 알 수 있다.
  ```java
  void method() throws Exception {
      // 이 메소드는 모든 종류의 에외가 발생할 가능성이 있다는 뜻이다.
  }
  ```
  - 만약 위의 메소드를 오버라이딩하여 throws를 사용하려면 Exception이나 그 자손 예외를 적어줘야한다.
  
  ```java
  @Override
  void method() throws Throwable {
      // Exception의 부모인 Throwable을 throw 했을 때, 에러가 발생한다.
  }
  ```
  - throws를 이용해서 예외를 본인이 직접 처리하지 않고, 자신을 호출한 곳으로 떠넘길 수 있는데, 이렇게 계속 떠넘기다 main메소드마저 예외를 처리하지 않으면, main메소드가 종료되어 프로그램 전체가 종료된다.
  
  #### D) ***finally***
    - `finally`블럭은 예외발생여부에 상관없이 실행되어야할 코드를 포함시킬 목적으로 사용된다.
    ```java
    try {
        // 예외가 발생할 가능성이 있는 문장들을 넣는다.
    } catch (Exception e) {
        // 예외처리를 위한 문장을 적는다.
    } finally {
        // 예외의 발생여부에 관계없이 항상 수행되어야하는 문장들을 넣는다.
        // finally블럭은 try-catch 문의 맨 마지막에 위치해야한다.
    }
    ```
    - `try`문에 `return`이 있더라도 `finally`문은 수행되고 `return`된다.
    - `try`문에 `System.exit(0);`이 있으면 프로그램이 종료되어 `finally`문은 수행되지 않는다.

### 2. 예외 계층 구조
```java
          Object
             |
         Throwable
       /          \
  Exception      Error
  /   |    \    /    \
```
- 모든 예외의 최고 조상은 Exception클래스다.
```java
Exception
    IOException
    ClassNotFoundException
    ...
    RuntimeException
        ArithmeticException
        ClassCastException
        NullPointerException
        ...
        IndexOutOfBoundsException
```
- ***RuntimeException클래스들***은 주로 프로그래머의 실수에 의해서 발생될 수 있는 예외들이다.
- 예를 들면, 배열의 범위를 벗어난다던가(ArrayIndexOutOfBoundsException), 값이 null인 참조변수에 접근하려 했다던가(NullPointerException), 클래스간의 형변환을 잘못했다던가(ClassCastException), 정수를 0으로 나누려고(ArithmeticException)하는 경우에 발생한다.
- ***Exception클래스들***은 주로 외부의 영향으로 발생할 수 있는 것들로서, 프로그램의 사용자들의 동작에 의해서 발생하는 경우가 많다. 
- 예를 들면, 존재하지 않는 파일의 이름을 입력했다던가(FileNotFoundException), 실수로 클래스의 이름을 잘못 적었다던가(ClassNotFoundException), 또는 입력한 데이터 형식이 잘못된(DataFormatException) 경우에 발생한다.

### 3. checked, unchecked
- checked : 컴파일 시점에 처리되어야하는 예외들이다. 따라서 반드시 예외처리를 해줘야한다.
- unchecked : 컴파일 시점에 처리되지 않는 예외들이다.
- `RuntimeException`과 `Error`클래스들이 unchecked이고, 그밖의 모든 `Throwable`클래스의 자손들이 checked이다.

```java
class Main {
   public static void main(String args[]) {
      int x = 10 / 0;
  }
}
```
- 위 코드는 컴파일은 가능하지만, 실행시 ArithmeticException(unchecked)이 발생할 것이다.

### 4. 예외 커스터마이징
- 기존에 정의된 예외 클래스 외에 필요에 따라 프로그래머가 새로운 예외 클래스를 정의하여 사용할 수 있다.
```java
class MyException extends Exception {
    MyException(String msg) {
        super(msg);
    }
}
```
- 필요하다면, 멤버변수나 메서드를 추가할 수 있다.
```java
class MyException extends Exception {

    private final int ERR_CODE;

    MyException(String msg, int errCode) {
        super(msg);
        ERR_CODE = errCode;
    }

    public int getErrCode() {
      return ERR_CODE;
    }
}
```
`자바에서 제공하는 이미 좋은 예외클래스들이 많으니 그것들을 사용하자.`

