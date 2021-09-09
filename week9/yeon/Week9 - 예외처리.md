9주차 예외 처리



### Exception이란?

메서드 내에서 예외가 발생하면 예외 객체가 생성되고 메서드는 런타임 시스템에 예외 객체를 전달한다.  -> "예외를 던진다(throwing an exception)" 라고 표현한다.

예외 객체는 예외가 발생한 시점에서의 에러에 관한 정보, 타입, 프로그램의 상태를 포함한다. 

런타임 시스템은 콜 스택에서 예외를 처리할 수 있는 코드 블럭이 포함된 메서드를 찾는다. 이 코드 블럭을 exception handler 라고 한다. 에러가 발생한 메서드부터 찾기 시작한다. 그리고 콜 스택에서 메서드 호출 순서의 반대 순서로 탐색한다. 

만약, 적절한 exception handler를 찾지 못한다면 런타임 시스템과 프로그램이 종료된다. 

<img width="454" alt="스크린샷 2021-09-09 오후 10 17 09" src="https://user-images.githubusercontent.com/65011131/132711071-c1fdc553-f15c-4143-a183-06b1a41b3a7f.png">



</br>



### try, catch, finally

- try 블럭은 예외가 발생할 수 있는 코드 블럭이다.
- catch 블럭은 예외를 처리할 수 있는 코드 블럭이다. exception handler
- finally 블럭은 실행이 보장되는 코드 블럭이다. 자원을 close하기에 적합하다.



</br>



### try-catch 문 여러줄 catch 할 때 주의해야할 사항

순서가 중요하다. 

예외에는 계층구조가 있다. 범위가 작은 순으로 적어야한다. 

![스크린샷 2021-09-09 오후 5 24 46](https://user-images.githubusercontent.com/65011131/132711133-33f7a8c8-29ad-49db-99e0-ddec87a55612.png)

</br>




### Multicatch block

1.7부터 여러 catch block을 하나로 합칠 수 있다.

다른 예외인데 <u>처리 방법이 같다면</u> 하나로 합칠 수 있다. 

```java
try {
  	// do something
} catch (IllegalAccessError e) {
  	System.out.println("에러 발생");
} catch (IllegalStateException e) {
  	System.out.println("에러 발생");
}
```

위의 코드를 아래와 같이 변경 가능하다.

```java
try {
  	// do something
} catch (IllegalAccessError | IllegalStateException e) {
  	System.out.println("에러 발생");
}
```
</br>


**주의 사항**

catch문에서 예외들이 상속관계면 안된다. 

![스크린샷 2021-09-09 오후 5 34 40](https://user-images.githubusercontent.com/65011131/132711226-7a3851ca-05b1-4aac-92a8-1d0efbf91d9f.png)


</br>



### checked 예외와 unchecked 예외

- checked exception 
  - Exception 클래스의 서브 클래스들 중 RuntimeException을 제외한 모든 클래스는 checked exception이다. 
  - 컴파일 에러 발생하기 때문에 반드시 예외 처리를 해줘야한다. (try-catch 혹은 throws)
  - ex) IOException, SQLException
- unchecked exception
  - RuntimeException과 서브 클래스들
  - 런타임 시점에 예외가 발생된다.
  - ex) NullPointerException, IllegalArgumentException, IndexOutOfBoundException
</br>




RuntimeException은 해당 예외가 발생했을 때 코드상으로 해줄 수 있는 일이 없다면 발생시킨다.

checked exception은 코드로 컨트롤 해줄 수 있는 경우에 사용한다. 

</br>



### 사용자 정의 예외

사용자 정의 예외를 만들 때는 기존에 이미 예외가 존재하는지, 꼭 생성해야하는지를 파악하고 생성해야한다. 

JDK가 제공하고 있는 여러 예외와 비교했을 때 생성하려는 커스텀 예외가 어떤 장점을 제공하지 않는다면, 커스텀 예외 생성을 재고할 필요가 있다.

UnsupportedOperationException이나 IllegalArgumentException과 같은 표준 예외를 사용하는 편이 낫다. 


</br>


어떠한 예외가 발생했고 사용자 예외로 바꾸고 싶을 때는, 사용자 예외에 root cause를 전달할 수 있는 생성자를 만들자.

```java
public class Example {
    public static void main(String[] args) {

        try (MyStream myStream = new MyStream()) {

        } catch (Throwable e) {
            throw new CustomException("custom 예외 발생", e);
        }
    }
}
```

```java
public class CustomException extends RuntimeException {
  
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}

```

</br>



사용자 예외가 최초의 root cause라면 그냥 에러 메시지만 갖는 생성자만 만들어도 된다. 

</br>





### try-with-resources

try-with-resoources 구문을 사용하면 리소스를 자동으로 `close()` 해준다. 사용하는 리소스는 AutoCloseable 인터페이스를 구현하고 있는 구현체여야한다. 

AutoCloeseable 인터페이스는 `close()` 메서드를 갖고 있다.

예시 코드를 보자. 

```java
public class MyStream implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("close my stream");
    }
}
```

```java
public class Example {

    public static void main(String[] args) throws Exception {

        try (MyStream myStream = new MyStream()) {
            System.out.println("hello");
        }
    }
}
```
</br>


.class 가 디컴파일 된 try-with-resources 구문 코드는 다음과 같다. 


![스크린샷 2021-09-09 오후 11 11 15](https://user-images.githubusercontent.com/65011131/132711396-57a80977-932b-416e-bd38-cbd362fe84d5.png)
</br>




자바 8이상을 쓴다면 try-with-resources 사용을 권장한다. -> 자원을 finally에서 굳이 일일히 닫아줄 필요 없다. 

</br>




### 예외처리 비용

- 메모리에 stack trace를 다 갖고 있어야한다. 따라서 예외를 처리하는 행위 자체가 비용이 크다.
  - stack trace: 애플리케이션이 실행된 시점부터 현재 실행 위치까지의 메서드 호출 목록 

- 충분히 로직으로 해결할 수 있는 문제라면 예외를 던지는 것은 권장하지 않는다. 

</br>



### e.printStackTrace()

메서드가 실행되면 JVM 메모리 스택영역에 쌓인다. 예외가 발생하면 스택 영역에 쌓인 Stack Frame들을 pop하면서 출력한다. Stack Trace의 각 라인은 하나의 Stack Frame을 나타낸다. 


</br>




### throws 

계속해서 throws로 던지고 main에서도 throws를 하면 JVM으로 넘어가는데 그럼 어떻게 되는가?

해당 스레드는 예외를 던지고 종료된다. 담고 있던 stack trace를 출력하고 종료된다 



</br>

</br>





출처: 백기선님 유튜브 영상 9주차 예외처리

https://www.notion.so/3565a9689f714638af34125cbb8abbe8

https://leegicheol.github.io/whiteship-live-study/whiteship-live-study-09-exception-handling/

oracle java tutorials (https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html)
