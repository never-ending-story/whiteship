## 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)

####  예외처리(exception handling)란?

- 정의: 프로그램 실행 시 발생할 수 있는 예기치 못한 예외의 발생에 대비한 코드를 작성하는 것.
- 목적: 프로그램의 갑작스런 비정상 종료를 막고, 정상적인 실행상태를 유지할 수 있도록 하는 것.

------

- 발생한 예외를 처리하지 못하면, 프로그램은 비정상적으로 종료 되며 처리되지 못한 예외(uncaught exception)는 JVM의 '예외처리기(UncaughtExceptionHandler)'가 받아서 원인을 화면에 출력한다.



#### try-catch문

- 예외를 처리하기 위해 **try-catch문**을 사용.

  ```java
  try{
    //예외가 발생할 가능성이 있는 문장들을 넣는다.
  } catch (Exception1 e1) {
    //Exception1이 발생했을 경우, 이를 처리하기 위한 문장을 적는다.
  } catch (Exception2 e2) {
    //Exception2가 발생했을 경우, 이를 처리하기 위한 문장을 적는다.
  } catch (ExceptionN eN) {
    //ExceptionN이 발생했을 경우, 이를 처리하기 위한 문장을 적는다.
  }
  ```

  - 하나의 try블럭 다음에는 여러 종류의 예외를 처리할 수 있도록,하나 이상의 catch블럭이 올 수 있다.
  - 이 중 발생한 예외의 종류와 일치하는 단 한개의 catch블럭만 수행되며, 일치하는 catch블럭이 없으면 처리되지 않는다.
  - catch블럭 내에 포함된 문장이 하나뿐이어도 괄호{}를 생략할 수 없다.

*** try-catch문에서의 흐름**

- try-catch문에서, 예외가 발생한 경우와 그렇지 않았을 때 프로그램 작동의 흐름이 달라진다.
  - try블럭 내에서 예외가 발생한 경우.
    1. 발생한 예외와 일치하는 catch블럭이 있는지 확인한다.
    2. 일치하는 catch블럭을 찾게 되면, 그 catch블럭 내의 문장들을 수행하고 전체 try-catch문을 빠져나가서 그 다음 문장을 계속해서 수행하며, 발생한 예외와 일치하는 catch블럭을 찾지 못하면 예외는 처리되지 않는다.
  - try블럭 내에서 예외가 발생하지 않은 경우.
    1. catch블럭을 거치지 않고 전체 try-catch문을 빠져나가서 이후의 작업을 수행한다.



#### catch 블럭

- catch블럭은 괄호()와 블럭{} 두 부분으로 나눠져 있는데, 괄호() 내에는 처리하고자 하는 예외와 같은 타입의 참조변수를 하나 선언해야한다.
- 예외가 발생 시, 발생한 예외에 해당하는 클래스의 인스턴스가 만들어지며 예외가 발생한 문장이 try블럭에 포함되어 있다면, 이 예외를 처리할 수 있는 catch블럭이 있는지 찾게된다.
  * 검사 결과가 true인 catch블럭을 만날 때 까지 검사는 계속된다.
- 모든 예외 클래스는 Exception클래스의 자손이므로, catch블럭의 괄호()에 Exception클래스 타입의 참조변수를 선언해 놓으면, 어떤 종류의 예외가 발생하더라도 이 catch블럭에 의해서 처리된다.
- try-catch문의 마지막에 Exception타입의 참조변수를 선언한 catch블럭을 사용 시, 어떤 종류의 예외가 발생하더라도 이 catch 블럭에 의해 처리되도록 할 수 있다.

* JDK1.7부터 여러 catch블럭을 '|' 기호를 이용해서, 하나의 catch블럭으로 합칠 수 있게 되었음. 이를 '멀티 catch블럭'이라 한다.

  - 중복된 코드를 줄일 수 있음.

  - '|'기호로 연결할 수 있는 예외 클래스의 개수는 제한이 없다.

    ```java
    try {
    
    ...
    
    }catch (ExceptionA | Exception B e) {
    
     e. printStackTrace();
    
    }
    ```

  - 다만 멀티 catch문은 하나의 catch 블럭으로 여러 예외를 처리하는 것 이기 때문에, 어떤 예외가 발생한 것인지 정확히 알 수 없다는 단점이 있다.



#### throw문

- 키워드 throw를 사용해, 프로그래머가 고의로 예외를 발생시킬 수 있다.

- 예외를 발생시키는 방법:

  1. 연산자 new를 이용해서 발생시키려는 예외 클래스의 객체를 만든다. `Exception e = new Exception("고의로 발생시켰음");`
  2. 키워드 throw를 이용해서 예외를 발생시킨다. throw e;

- 예제:

  ```java
  public class Ex8_6 {
  	public static void main(String args[]) {
  		try {
  			Exception e = new Exception("고의로 발생시켰음.");
  			throw e; // 예외를 발생시킴.
  			// throw new Exception("고의로 발생시켰음"); ==> 위의 두 줄을 한 줄로 줄여 쓴 것.
  		} catch (Exception e) {
  			System.out.println("에러메시지:"+e.getMessage());
  			e.printStackTrace();
  		}
  		System.out.println("프로그램이 정상 종료되었음.");
  	}
  }
  ```



#### throws

- 선언 방법:

  > void method() throws Exception, Exception2, ... ExceptionN { // 메서드의 내용 }
  >
  > - 예외를 발생하는 키워드 throw와 예외를 메서드에 선언할 때 쓰이는 throws를 잘 구분할 것.

- 아래의 코드와 같이 모든 예외의 최고조상인 Exception클래스를 메서드에 선언하면, 이 메서드는 모든 종류의 예외가 발생할 가능성이 있다는 뜻.

  ```java
  void method() throws Exception {
  
  // 메서드 내용
  
  }
  ```

- 예외 선언 시, 이 예외 뿐만 아니라 자손타입의 예외까지도 발생할 수 있다는 점을 주의 해야한다. 
  오버라이딩 할 때 처럼 단순히 선언된 예외의 개수가 아니라 상속관계까지 고려해야한다.

- 예외를 선언함으로써 메서드 선언부를 보았을 때, 이 메서드를 사용하기 위해 어떠한 예외들이 처리 되어져야하는지 쉽게 알 수 있다.

- 예외가 발생한 메서드에서 예외처리를 하지 않고 **자신을 호출한 메서드에게 예외를 넘겨줄 수는 있지만,** **이 것으로 예외가 처리된 것은 아니고 예외를 단순히 전달만 하는것.** 그렇기 때문에 **어느 한 곳에서는 반드시 try-catch문으로 예외처리를 해주어야한다**.



#### finally 블럭

- 예외의 발생여부에 상관없이 실행되어야할 코드를 포함시킬 목적으로 사용된다.

- try-catch문의 끝에 선택적으로 덧붙여 사용할 수 있으며, try-catch-finally순서로 구성 된다.

  ```java
  try {
    //예외가 발생할 가능성이 있는 문장들을 넣는다.
  } catch (Exception1 e1) {
    //예외처리를 위한 문장을 적는다.
  } finally {
    //예외의 발생여부에 관계없이 항상 수행되어야하는 문장들을 넣는다.
    //finally블럭은 try-catch문의 맨 마지막에 위치해야함.
  }
  ```

  - 예외가 발생한 경우, 'try ➡️ catch ➡️ finally'의 순으로 실행되며,  예외가 발생하지 않은 경우 'try ➡️ finally'의 순으로 실행 된다.

  

## 자바가 제공하는 예외 계층 구조

- 자바에서는 실행 시 발생할 수 있는 오류(Exception과 Error)를 클래스로 정의 한다.
- Excption과 Error클래스도 Object클래스의 자손들이다.

[![image](https://user-images.githubusercontent.com/69128652/93057473-f1ce7700-f6a8-11ea-9bfc-8a0571767c9a.png)](https://user-images.githubusercontent.com/69128652/93057473-f1ce7700-f6a8-11ea-9bfc-8a0571767c9a.png)

- Exception클래스와 RuntimeException클래스 중심의 상속계층도

   

  ![image](https://user-images.githubusercontent.com/69128652/93057770-673a4780-f6a9-11ea-9c63-1b34979324fd.png)

  - 예외클래스들은 다음과 같이 두 그룹으로 나눠 질 수 있다.
    1. Exception클래스와 그 자손들(윗부분)
    2. RuntimeException클래스와 그 자손들(아랫부분)

## Exception과 Error의 차이는?

### 프로그램 오류란?

- 프로그램 실행 중 어떤 원인에 의해서 오작동을 하거나 비정상적으로 종료되는 경우, 이러한 결과를 초래하는 원인.
- 발생 시점에 따라 '컴파일 에러(compile-time error)'와 '런타임 에러(runtime error)', 이외에 '논리적 에러(logical error)'로 나눌 수 있다.
  1. 컴파일 에러:컴파일 시 발생하는 에러
     - 컴파일러가 소스코드(*.java)에 대해 오타나 잘못된 구문,자료형 체크 등 검사를 진행하여 오류가 있는지 알려준다.
  2. 런타임 에러:실행 시 발생하는 에러
     - 실행 시 발생할 수 있는 프로그램 오류를 '에러(error)'와 '예외(exception)' 두가지로 구분.
       1. 에러 (error): 프로그램 코드에 의해서 수습될 수 없는 심각한 오류 ex) 메모리 부족(OutOfMemoryError), 스택오버플로우(StackOverflowError)
       2. 예외(exception): 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류
  3. **논리적 에러:** 실행은 되지만, 의도와 다르게 동작하는 것

## RuntimeException과 RE가 아닌 것의 차이는?

* `RuntimeException`과 `Error`클래스들이 UncheckedException이고, 그밖의 모든 `Throwable`클래스의 자손들이 CheckedException이다.
* CheckedException : 컴파일 시점에 처리되어야하는 예외들이다. 따라서 반드시 예외처리를 해줘야한다.
* UncheckedException : 컴파일 시점에 처리되지 않는 예외들이다.

## 커스텀한 예외 만드는 방법

- 기존의 정의된 예외 클래스 외에 필요에 따라 프로그래머가 새로운 예외 클래스를 정의하여 사용할 수 있다.

- 보통 Exception클래스 또는 RuntimeException클래스로부터 상속받는 클래스를 만들지만, 필요에 따라서 알맞은 예외 클래스를 선택할 수 있다.

  ```java
  class MyException extends Exception {
    MyException(String msg) { // 문자열을 매개변수로 받는 생성자
      super(msg);             // 조상인 Exception클래스의 생성자를 호출한다.
    }
  }
  ```

  - Exception클래스로부터 상속받아서 MyException클래스를 만들었다. 필요하다면 멤버변수나 메서드를 추가할 수 있다.

  - Exception클래스는 생성 시에 String값을 받아서 메시지로 저장할 수 있다.

    (사용자정의 예외 클래스에 메세지를 저장할 수 있으려면, 위처럼 String을 매개변수로 받는 생성자를 추가해야한다.)

- 다만 정말 특별한 경우가 아니라면, 자바에서 제공되는 예외 클래스들이 많으니 그 것을 이용하는것이 가장 좋다!

  낯선 예외를 마주쳤을 때의 가독성 측면이나 해당 예외를 파악하는 작업이 낭비되는 비용이 될 수도 있고 일일히 예외를 커스텀 하다보면 지나치게 많은 예외들을 만들 수도 있다.
  따라서 아래와 같은 조건에 부합할 경우에 커스텀 예외를 만들자.

  * 자바에서 지원하지 않는 예외가 필요한지
  * 다른 예외들과 작성한 예외를 구별 할 수 있을 때, 다른 사용자에게 유용한지
  * 커스텀하려는 관련 예외가 두 개 이상 발생하는지
  * 커스텀 예외를 사용할 때, 사용자가 해당 예외에 액세스 할 가능성이 있는지
  * 현재 패키지가 스스로 관리가 되고, 독립적인 구조인지

