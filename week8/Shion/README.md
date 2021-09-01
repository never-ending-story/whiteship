## 8주차 : 패키지

### 1. 인터페이스 정의하는 방법

- 클래스처럼, 인터페이스는 변수와 메서드를 가질 수 있습니다.
- 하지만 인터페이스 내에 선언된 메서드는 기본적으로 ***추상메서드***입니다.(메서드 시그니처만 있고, 몸통은 없습니다..)
- 인터페이스는 클래스가 ***무엇***을 해야할지 구체적으로 지정합니다. (***어떻게*** 해야할지는 지정하지 않습니다.)
- 인터페이스는 클래스가 구현해야되는 메서드들을 지정합니다.
- 한 클래스가 어떤 인터페이스를 구현하고 인터페이스에 있는 모든 메서드들을 오버라이딩하지 않는다면, 이 클래스는 ***추상클래스***가 되어야합니다.

```java
interface <interface_name> {
    
    // 상수

    // 추상 메서드

}
```
- 인터페이스 내에 선언된 변수는 `public static final`이 생략된 상수입니다.
- 인터페이스 내에 선언된 메서드는 `public abstract`가 생략된 추상메서드입니다.

### 2. 인터페이스 구현하는 방법

- 인터페이스의 구현은 `implements`를 사용합니다.
- 인터페이스끼리 상속할 경우엔 `extends`를 사용합니다.

```java
interface InterfaceA extends InterfaceB {

}
```

```java
public interface Comparable<T> {

    public int compareTo(T o);
}

// 자바에서 제공하는 Comparable 인터페이스를 구현하였습니다.
public class Student implements Comparable<Student> { 

    // ...

    @Override
    public int compareT(T o) {
        // ...
    }
}
```
- 이제 Student가 담겨 있는 collection을 ***정렬할 수 있게*** 되었습니다.

### 3. 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

- 인터페이스는 추상클래스처럼 곧바로 인터페이스를 만들 수 없습니다.
- 인터페이스를 구현한 클래스의 인스턴스는 또한 인터페이스의 인스턴스이기도 합니다.

```java
class Student implements InterfaceA {

    public static void main(String[] args) {
        InterfaceA instance = new Student();
    }
}
```
- 변수 `instance`는 `Student`클래스의 인스턴스이기도 하지만, `InterfaceA`인터페이스의 인스턴스이기도 합니다.
- 우린 이 방법을 이용하여 `InterfaceA`인터페이스에 접근합니다.

### 4. 인터페이스 상속

- 인터페이스는 클래스에서 지원하지 않는 다중 상속을 지원합니다.
```java
interface InterfaceA extends InterfaceB, InterfaceC {
    // 가능합니다.
}

class Student implements Comparable<Student>, InterfaceB, InterfaceC {
    // 가능합니다.
}
```

```java
interface B {
    default void hello() {
        System.out.println("Hello B"); 
    }
}

interface C {
    default void hello() {
        System.out.println("Hello C"); 
    }
}

interface A extends B, C {
    // 다중 상속한 각각의 인터페이스(B, C)에 똑같은 시그니처의 default 메서드가 있다면
    // A에선 둘 중, 어느 hello()를 호출해야할지 모르니 오버라이딩해주어야 합니다.

    @Override
    default void hello() {
        System.out.println("Hello A");
    }
}
```

- 변수의 경우, static 상수만 정의할 수 있으므로 충돌하는 경우가 거의 없고, 충돌하더라도 클래스 이름으로 구분이 가능하다. 

### 5. 인터페이스의 기본 메소드 (Default Method), 자바 8

- 기존 인터페이스에 메소드를 추가해봅시다. 이제 인터페이스에 추상메서드가 하나 더 생겼으니 해당 인터페이스를 구현한 기존의 코드를 그대로 실행했다간 오류를 맛 볼 것입니다. 하지만 인터페이스에 새로운 메서드가 생겨도 오버라이딩 하지 않을 수 있다면 메서드 추가 작업이 부담스럽지 않을 것이고, 기존의 코드 또한 정상적으로 작동할 것입니다. 이런 개념에서 자바8부터 default메서드가 추가되었습니다.

- default 메서드는 반드시 오버라이딩 하지 않아도 됩니다. (필요할 때 오버라이딩해서 사용합시다.
)

```java
public interface HandlerInterceptor {
    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    default void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    default void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
```

- 우리가 프로젝트에서 인터셉터를 구현할 때 `preHandle()`, `postHandle()`, `afterCompletion()`을 모두 구현하지 않아도 되었던 건, 바로 이녀석들이 default method 였기 때문입니다.

### 6. 인터페이스의 static 메소드, 자바 8

- 자바 8에서 default 메서드와 함께 static 메서드도 추가되었습니다.
- static 메서드는 default 메서드처럼 완벽하게 몸통까지 구현되어있습니다.
- 하지만 static 메서드이기때문에, 인터페이스를 구현한 클래스에서 오버라이딩할 수 없습니다.
- 클래스에서처럼, static 메서드에서는 non-static 메서드에 접근할 수 없습니다.

```java
interface B {
    static void hello() {
        System.out.println("Hello B");
    }
}

class A extends B {
    // B 인터페이스에도 hello() 메서드가 있지만,
    // A, B 각각의 static 메서드 2개일 뿐, 오버라이딩이 아닙니다.
    static void hello() {
        System.out.println("Hello A");
    }
}
```

### 7. 인터페이스의 private 메소드, 자바 9

- 자바 7까지, 인터페이스는 단 2가지만 가질 수 있었습니다.
    - 상수 
    - abstract 메서드

- 자바 8에서는, 4가지를 가질 수 있습니다.
    - 상수
    - abstract 메서드
    - ***default 메서드***
    - ***static 메서드***

- 자바 9부터는, private 메서드가 추가되어 6가지를 가질 수 있습니다.
    - 상수
    - abstract 메서드
    - default 메서드
    - static 메서드
    - ***private default 메서드***
    - ***private static 메서드***

- `private`은 default 메서드와 static 메서드에만 사용할 수 있습니다.
- 인터페이스 안의 private 메서드는 클래스의 관점에서처럼 인터페이스 내에서 ***코드 재사용성***을 높여주고, ***해당 인터페이스를 구현한 클래스나 다른 인터페이스에서*** private 메서드에 ***접근할 수 없으므로*** 캡슐화의 이점이 있습니다.


