11주차: Enum
</br>


### Enum

관련된 상수를 묶어놓은 것

- Enum간의 비교는`equls` 대신에 `==`을 쓸 수 있다.

- 모든 enum은 추상 클래스 **Enum의 자손**이다. 

  - 바이트코드를 보면 Enum클래스를 extends하고 있다.

  <img width="714" alt="스크린샷 2021-09-30 오후 11 59 16" src="https://user-images.githubusercontent.com/65011131/135487822-412ae4a4-71ff-4660-b955-8b4d39b3f74a.png">


- enum은 Enum 클래스 이외에 다른 클래스 상속이 불가능하다. 

- enum의 생성자는 묵시적으로 private 이다.

- enum을 사용하면 **type safety**가 보장된다.

  - 여기서 **type safe** 하다는 것은 enum에 정의한 상수 이외에 다른 값을 명시할 수 없다는 뜻이다. 아래 예시를 보자.

    ```java
    public class Main {
        public static void main(String[] args) {
            System.out.println("east");
        }
    }
    ```

    방향을 반환하는 위의 코드는 type safe 하지 않다. 가령, 오타가 날 수도 있고 방향에 해당하지 않는 값을 넣을 수도 있다.

    ```java
    public class Main {
        public static void main(String[] args) {
            System.out.println(Direction.EAST.getValue());
        }
    }
    
    enum Direction {
        EAST("east"), SOUTH("south"), WEST("west"), NORTH("north");
    
        private String value;
    
        Direction(String value) {
            this.value = value;
        }
    
        public String getValue() {
            return value;
        }
    }
    ```

    위와 같이 enum을 사용하면 오타가 발생하면 컴파일러가 알려주고 정해진 4개의 값 중에서만 사용할 수 있다. 

    type-safety가 보장된다. 


</br>

### Enum이 제공하는 메서드

**`values()` 메서드**

모든 상수를 배열에 담아 반환한다.

```java
public class Main {
    public static void main(String[] args) {
        Direction[] directions = Direction.values();
        Arrays.stream(directions).forEach(System.out::println);
    }
}

enum Direction {
    EAST, SOUTH, WEST, NORTH
}
```

```
EAST
SOUTH
WEST
NORTH
```


</br>

**`getDeclaringClass()`**

enum의 Class 객체를 반환한다. 

```java
System.out.println(Direction.EAST.getDeclaringClass());
```

```
class yeon.exam.Direction
```

</br>


**`name()`**

enum 상수명을 문자열로 반환한다.

```java
 System.out.println(Direction.EAST.name());
```

```
EAST
```

</br>


**`valueOf(String name)`**

enum에서 name과 같은 enum 상수를 반환한다.

```java
Direction direction = Direction.valueOf("WEST");
System.out.println(direction.name());
```

```
WEST
```

</br>


**`ordinal()`**

상수가 정의된 순서를 반환한다. 새로운 상수를 추가하거나 삭제하는 등의 변경이 일어나면 순서가 바뀔 수 있으므로 ordinal()을 이용해서 소스코드를 작성하는 것은 위험하다. 

JPA에서 `@Enumerated`라는 어노테이션을 사용할 때 데이터베이스에 enum의 순서가 저장된다. 따라서 `@Enumerated(EnumType.STRING)`으로 설정해야 enum의 이름이 저장된다. 

</br>


### EnumSet

EnumSet은 enum을 위해 만들어진 특별한 Set 인터페이스 구현체이다. 

주요 특징은 다음과 같다.

- AbstractSet을 extends하고 Set 인터페이스를 구현했다.
- HashSet보다 빠르다.
- 모든 요소는 enum 타입이어야 한다. 
- 요소로 null을 허용하지 않고 null을 넣으면 NPE가 발생한다.
- fail-safe 하다.

`EnumSet.allOf(Class<E> elementType)`으로 enum에 있는 모든 값을 가진 Set을 생성할 수 있다.

```java
public class Main {
    public static void main(String[] args) {
        EnumSet<Direction> directions = EnumSet.allOf(Direction.class);
        directions.forEach(System.out::println);
    }
}

enum Direction {
    EAST, SOUTH, WEST, NORTH
} 
```

```
EAST
SOUTH
WEST
NORTH
```

</br>


이외에 `noneOf(Class<E> elementType)`, `of(E e)`, `of(E fist, E...rest)`, `range(E from, E to)`등의 메서드를 이용해서 EnumSet을 생성할 수 있다.


</br>
</br>



참고: 자바의 정석

백기선님 유튜브 자바 라이브 스터디 11주차 Enum

https://www.geeksforgeeks.org/enumset-class-java/

