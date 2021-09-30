## enum 정의하는 방법

- 정의 방법: 괄호{} 안에 상수의 이름을 나열한다.
  주로 고정된 상수들의 집합을 표현할 때 사용되며, 선언 시 상수를 담고있으므로 대문자로 작성해야한다.

  ```java
  enum 열거형이름 {상수명1, 상수명2, ...}
  
  enum Direction {EAST, SOUTH, WEST, NORTH}
  ```

- 사용 방법: '열거형이름.상수명'을 작성하여 사용하면 된다. static변수를 참조하는 것과 동일하다.

  ```java
  class Unit{
    int x, y; 		 //유닛의 위치
    Direction dir; //열거형을 인스턴스 변수로 선언
    
    void init(){
      dir = Direction.EASR;	// 유닛의 방향을 EAST로 초기화
    }
  }
  ```

- 열거형 상수간의 비교:

  - '=='를 사용하며, equals()를 사용하지 않아도 비교가 된다.(빠른 성능 제공)

  - '<','>' 와 같은 비교연산자는 사용 할 수 없다.

  - comparTo()는 다른 곳에서 사용할 때와 같이, 두 비교 대상이 같으면 0, 왼쪽이 크면 양수, 오른쪽이 크면 음수를 반환한다.

    ```java
    if(dir == Direction.EAST){
      x++;
    } else if (dir > Direction.WEST){ // 에러. 열거형 상수에 비교연산자 사용 불가
      ...
    } else if (dir.compareTo(Direction.WEST) > 0){ // compareTo()는 가능
      ...
    }
    ```

## enum이 제공하는 메소드 (values()와 valueOf())

- 컴파일러가 모든 열거형에 자동적으로 추가해주는 메소드가 두 개 있다.

  - `static E[] values()`

    - 열거형 Direction에 정의된 모든 상수를 출력하는 데 사용된다.

    - 선언된 순서대로 열거형 내의 모든 값을 포함한 배열을 반환하는 메소드로, for-each 구문과 함께 사용해 열거형의 값을 반복한다.

      ```java
      Direction[] dArr = Direction.values();
      
      for(Direction d : dArr) // for(Direction d : Direction.values())
        System.out.printf("%s = %d%n", d.name(), d.ordinal());
      ```

  - `static E valueOf(String name)`

    - 열거형 상수의 이름으로 문자열 상수에 대한 참조를 얻을 수 있게 해준다.

    - 존재하지 않는 상수를 입력한 경우, IllegalArgumentException이 발생한다.

      ```java
      Direction d = Direction.valueOf("WEST");
      
      System.out.println(d); // WEST
      System.out.println(Direction.WEST == Direction.valueOf("WEST")); // true
      ```

## java.lang.Enum

* 모든 열거형의 조상은 java.lang.Enum 이며, java내에서 다중 상속을 지원하지 않기 때문에 열거형 클래스는 다른 클래스를 상속할 수 없다.

* Enum클래스는 추상클래스이기 때문에, Enum클래스의 객체를 만들 수 없다.

* Enum은 다음과 같은 메소드를 제공한다.

  | 메서드                                 | 설명                                                         |
  | -------------------------------------- | ------------------------------------------------------------ |
  | Class getDeclaringClass()              | 열거형의 Class객체를 반환한다.                               |
  | String name()                          | 열거형 상수의 이름을 문자열로 반환한다.                      |
  | int ordinal()                          | 열거형 상수가 정의된 순서를 정수로 반환한다.(0부터 시작)<br />코드 내에서 순서 변경 시, ordinal의 값도 변경되기 때문에 사용시 주의가 필요하다. |
  | T valueOf(Class enumType, String name) | 지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다.    |
  | String toString()                      | 열거형 상수의 문자열 표현을 반환한다. 이는 재정의 할 수 있다. |
  | final boolean equals(Object obj)       | 매개변수로 전달된 객체가 열거형 상수와 같다면 `true`를 반환하고, 그렇지 않다면 `false`를 반환한다. |
  | final int hashCode()                   | 이 열거형 상수에 대한 해시 코드를 반환한다.                  |
  | final int compareTo(E obj)             | 열거형의 순서를 비교한다. 순서가 낮은 경우 음수, 같은 경우 0, 높은 경우 양수를 반환한다. |
  | final Class <E> getDeclaringClass()    | 열거형 상수의 타입에 해당하는 `Class` 객체를 반환한다.       |
  | final Object clone()                   | 열거형이 복제되지 않도록 보장하며, 단일 상태임을 보장해준다. 사용시 `CloneNotSupportedException`이 발생한다. 열거형 상수 생성을 위해 컴파일러 내부적으로 사용된다. |
  | final void finalize()                  | enum 클래스가 `finalize` 메서드를 가질 수 없음을 보장한다.   |

## EnumSet

* enum과 함께 동작하는 Set을 의미한다. Set 인터페이스를 구현하고, AbstractSet을 상속한다.

* 추상클래스이므로, 인스턴스 생성을 위한 정적 팩토리 메서드가 정의 되어있다.

* 제작 시 유의사항:

  * 열거형 값만 저장할 수 있기 때문에, 모든 값은 같은 열거형에 속해야한다.
  * null을 추가할 수 없으며, 추가할 경우 NullPointerException이 발생한다.
  * EnumSet은 thread-safe하지 않으므로, 동기화가 필요할 경우 외부에서 동기화 처리 작업을 진행해야한다.
  * 열거형에 정의된 순서에 따라 요소들이 저장된다.
  * 복사본에 fail-safe iterator(장애 발생 시 작업을 중단하지 않음)을 사용하기 때문에, 컬렉션이 수정되어도 ConcurrentModificationException이 발생하지 않는다.

* 사용 시 장점:

  * 모든 메서드들이 산술비트 연산을 사용해 구현되어있으므로, 일반적인 연산이 빠르게 진행된다.
  * 데이터가 예상 가능한 순서로 저장되어있고, 각 계산을 진행할 때 하나의 비트만이 필요해 속도가 더빠르다.
  * HashSet처럼 데이터를 저장할 버킷을 찾는 데 hashcode를 계산할 필요가 없다.
  * 내부가 비트벡터로 이뤄져 있어 더 작은 메모리를 사용한다.

* 사용방법

  * 다음과 같은 Color 열거형이 있을 경우, allOf()로 EnumSet을 만들 수 있다.

    ```java
    public enum Color{
      RED, YELLOW, GREEN, BLUE, WHITE, BLACK
    }
    
    public class Canvas {
      public static void main(Strig[] args) {
        EnumSet<Color> set = EnumSet.allOf(Color.class);
    		set.forEach(System.out::println);
      }
    }
    ```

    > 결과: 선언한 Enum의 순서대로 출력된다.

  * nonOf() 사용 시, 빈 Color컬렉션을 갖는 EnumSet을 만든다.

    ```java
    EnumSet<Color> set = EnumSet.noneOf(Color.class);
    ```

  * of() 사용 시, 들어갈 요소를 직접 입력하여 구성할 수 있다.

    ```java
    EnumSet<Color> set = EnumSet.of(Color.YELLOW, Color.BLUE);
    // YELLOW, BLUE만 포함한 Set
    ```

  * complementOf() 사용 시, 원하는 요소를 제거하여 구성할 수 있다.

    ```java
    EnumSet<Color> set = EnumSet.complementOf(EnumSet.of(Color.BLACK,Color.BLUE));
    // RED, YELLOW, GREEN, WHITE만 포함한 Set
    ```

  * copyOf() 사용 시, 다른 EnumSet의 모든 요소를 복사하여 만들 수 있다.

    ```java
    EnumSet.copyOf(EnumSet.of(Color.BLACK, Color.WHITE));
    ```

* 기타 메서드

  | 메소드           | 설명                                      |
  | ---------------- | ----------------------------------------- |
  | add(Enum e)      | EnumSet에 요소 추가                       |
  | contains(Enum e) | EnumSet에 특정 요소가 포함되어있는지 확인 |
  | remove(Enum e)   | EnumSet에서 특정 요소 제거                |

  