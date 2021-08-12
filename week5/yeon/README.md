5주차 - 클래스

- 클래스 정의하는 법
- 객체 만드는 법 (new 키워드)
- 메소드 정의하는 법
- 생성자 정의하는 법
- this 키워드

</br>


### 클래스 정의하는 법

클래스는 객체를 생성하기 위한 틀이다.

데이터와 함수의 결합


</br>

**클래스의 구조**

- 필드 (멤버 변수라고도 불림)
  - 인스턴스 변수
    - 인스턴스를 생성할 때 만들어진다.
    - 인스턴스마다 독립적인 값을 갖고, heap 영역에 할당된다.
  - 클래스 변수
    - static 키워드
    - 모든 인스턴스들이 공유하는 변수

- 메서드
  - 인스턴스 메서드
    - 인스턴스를 통해 호출할 수 있다.
  - 클래스 메서드
    - 정적 메서드, 인스턴스와 관계 없는 메서드를 정적 메서드로 정의한다.
- 생성자
  - 객체를 초기화 하는데 사용되는 코드 블록
  - 클래스 당 최소 하나 이상의 생성자가 존재한다.
  - 생성자를 정의하지 않으면 컴파일러가 기본 생성자를 추가한다.
- 초기화 블록
  - 클래스 변수 초기화 블록
  - 인스턴스 변수 초기화 블록



**초기화 블럭과 생성자 중 어느 것이 먼저 호출될까?**

```java
public class Init {

    private int number;

    {
        this.number = 10;
        System.out.println("init block");
    }

    public Init() {
        this.number = 100;
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        Init init = new Init();
        System.out.println(init.number);
    }
}


// 결과
init block
constructor
100
```

초기화 블럭이 먼저 실행된 이후에 생성자가 실행된다.


</br>

### 객체 만드는 법 (new 키워드)

```java
public class NewKeywordExam {

    String name = "yeon";

    public static void main(String[] args) {
        NewKeywordExam obj = new NewKeywordExam();
        System.out.println("obj.name = " + obj.name);
    }
}

// 결과
obj.name = yeon
```

new 키워드와 생성자를 사용하여 객체를 만들 수 있다.

</br>


### 메소드 정의하는 법

```java
접근제어자 반환타입 메서드명 (타입 변수명, 타입 변수명 ...) {	// 선언부	
  // 메소드 호출 시 수행될 코드 				 // 구현부 
}
```

</br>


### 생성자 정의하는 법

```java
class Card {
  int value;
  
  Card() {				// 매개변수가 없는 생성자
    this.value = 10;
  }
  
  Card(int. value) {			// 매개변수가 있는 생성자
    this.value = value;
  }
}
```

클래스에 정의된 생성자가 하나도 없을 경우에는 기본 생성자가 컴파일러에 의해 추가된다. 

</br>


### this 키워드

참조변수 this와 생성자 this() 
</br>

**참조변수 this**

- 인스턴스 자신을 가르키는 참조변수

- 인스턴스 메서드와 생성자에서 사용 가능

  - 클래스 메서드에서는 사용 불가능하다. this 키워드를 사용할 시점에 인스턴스가 생성되지 않았을 수도 있기 때문이다.

- 지연변수와 전역변수를 구별할 때 가장 많이 쓰인다.

  ```java
  class Car {
    String color;
    String gearType;
    int door;
    
    Car (String color, String gearType, int door) {
      this.color = color;
      this.gearType = gearType;
      this.door = door;
    }
  }
  ```

</br>


**this()**

생성자에서 다른 생성자의 호출이 가능한데 이 때 `클래스명()` 대신 `this()`를 사용한다. 

단, this()는 생성자 코드 내에서 첫번째 줄에서 호출해야한다. 

```java
class Car {
  String color;
  String gearType;
  int door;
  
  Car () {
    this("white", "auto", 4);
  }
  
  Car (String color, String gearType, int door) {
    this.color = color;
    this.gearType = gearType;
    this.door = door;
  }
}
```

</br>
</br>


출처: 자바의 정석

https://jeeneee.dev/java-live-study/week5-class/
