## 클래스 정의하는 방법

* 클래스란? 
  인스턴스를 생성하기 위한 틀(template)을 의미한다.

  객체를 만드는 데에만 사용이 되며,  필드와 메서드, 생성자, this 참조변수 네 가지로 나뉜다.

  > 1. 필드(field): 클래스 내에서 정의한 객체의 속성과 상태(state)
  > 2. 메서드(method): 클래스 내에서 정의한 객체의 행위(behavior)
  > 3. 생성자(constructor): 클래스로부터 만들어진 인스턴스 변수를 초기화하기 위한 메서드
  > 4. this 참조변수: constructor 의 매개변수명과 instance 변수명이 같을 경우 구별을 위한 참조변수.



* 정의하는 방법:

  ```java
  public class Animal {
    // 필드(field) : 모든 동물이 가지고 있는 속성
    private String name;
    private int age;
    
    // 생성자(constructor) : 객체를 생성 및 인스턴스 변수를 초기화
    // 해당 클래스와 이름이 동일 해야 한다.
    public Animal() { }
    
    public Animal(String name, int age) {
      // this 참조 변수. 매개변수 명과 instance의 변수 명이 같으므로 구분을 위해 사용.
      this.name = name;
      this.age = age;
    }
    
    // 메서드(method) : 모든 동물이 가능한 행위
    public void attack() {
    }
    
    public void eat() {
    }
    
    public void exercise() {
    }
  }
  ```

* 위와 같이 클래스 선언 시, 필드, 생성자, 메서드 정의가 포함될 수 있으며, 부모 클래스를 상속하는 지, 인터페이스를 구현하는 지 에 대한 여부를 선언 시 정의 할 수 있다.

  ```java
  public class Human extends Animal(class) implements Interface {}
  // Human 은 Animal의 서브 클래스며, Interface를 구현함을 의미한다.
  ```

* 메서드와 필드에서 사용한 것과 같이 클래스 또한 **접근제어자**를 앞에 추가함으로써 선언부가 복잡해질 수 있다.

  > 클래스에 사용할 수 있고, 주로 사용되는 접근 제어자:
  >
  > * public 
  > * private

* 클래스 선언 시 포함될 수 있는 요소

  1. public, private 등의 접근 제어자
  2. 첫 글자가 대문자인 클래스 명
  3. 상속받을 클래스의 경우, extends 뒤에 부모 클래스 이름을 붙인다.
     단 하나의 상위 클래스만 상속받을 수 있으니, 주의하자.
  4. implements 뒤에 인터페이스 목록이 올 수 있다.
     인터페이스의 경우 상속과는 달리 여러 개의 인터페이스를 구현할 수 있다.
  5. 중괄호({})로 클래스 본문을 선언.



## 객체 만드는 방법 (new 키워드 이해하기)

* 한 클래스를 가지고 인스턴스를 여러 개 만들 수 있는데, 이 때 사용되는것이 new 키워드이다.

* 생성자를 이용해 객체를 만들어 내는 코드:

  ```java
  Animal cat = new Animal();
  Animal tiger = new Animal("Amy", 3);
  Human adam = new Human("사육사", 50);
  ```

  * 새로운 객체를 만들기 위해 new 키워드를 붙여야 하며, 이 때 사용되는 것이 생성자이다.
    그리고 이러한 과정을 초기화 라고 한다.
  * 위의 예제는 다음과 같은 순서로 진행된다.
    1. 선언
       컴파일러에게 해당 타입의 객체를 사용할 것임을 알려준다.
    2. 인스턴스화
       new 키워드로 컴파일러에게 해당 타입의 인스턴스를 생성할 것임을 알려준다.
    3. 초기화
       new 키워드 다음에 생성자 호출이 진행되며 객체를 초기화 한다.

* 원시 타입은 stack영역에 데이터가 생성되고, 참조 타입은 힙 영역에 데이터 생성 후, stack영역에 있는 변수가 그 주소값을 가지고 있는데, 
  new 키워드 사용 시, 메모리(heap)영역에 값을 할당 하고, 이 주소 값을 매핑하는 해시코드를 변수에 저장하게 된다.
  참조 값을 사용자가 직접 만들 수 없고, 가상머신의 허락을 받아야 하는데 그 허락을 받게 해주는 것이 바로 new 키워드 인 것.

  ```java
  String a = "aaa";
  String b = new String("aaa");
  ```

  예시로 String을 사용해 보자.
  위의 문장은 서로 같아보이지만, `a==b` 와 같은 형식으로 값을 대조해 보았을 때 같은 값으로 나오지 않는다.
  new를 사용하지 않고 생성한 String값의 경우 String Pool안에 생성이 되고, 이를 참조하는 형식이기 때문에 
  직접적으로 Heap 안에 생성되는 b와는 별도의 값으로 판단 되는 것이다.
  ![img](https://t1.daumcdn.net/cfile/tistory/9941BE355D3054792F)



## 메소드 정의하는 방법

* 메서드란?
  * 객체 안에 있는 함수.
  * **특정 작업을 수행하는 일련의 문장들을 하나로 묶은 것.**
  * 작은 기능만 수행 해야 하며, 하나로 묶을 때엔 블럭{} 을 사용.
  * 메서드에 **넣을 값(입력)과 반환하는 결과(출력)만 알면 되며, 출력 과정은 몰라도 된다.** 이러한 특성으로 메서드를 내부가 보이지않는 '블랙박스(black box)'라고 부르기도 함.

* 메서드의 구성 방식:

  - 선언부(header, 머리)
  - 구현부(body, 몸통)
  - 메서드를 정의한다 == 선언부와 구현부를 작성하는 것.
    ![메서드](https://user-images.githubusercontent.com/69128652/90976506-b264ad00-e578-11ea-9352-45e8f5b8de1b.png)

* 메서드의 오버로딩(metod overloading):

  - 한 클래스 내에 같은 이름의 메서드를 여러 개 정의하는 것. '오버로딩(overloading)'이라고도 한다.

  - 오버로딩이 성립하는 조건:

    1. 메서드 이름이 같아야 한다.
    2. 매개변수의 개수 또는 타입이 달라야 한다.

    위의 조건이 성립하지 못하는 메서드는, *중복 정의로 간주되어 컴파일 시 에러가 발생한다.*

  - 오버로딩된 메서드들은 매개변수에 의해서 구별될 수 있음. **반환 타입은 오버로딩을 구현하는 데 영향을 주지 못함.**

  - 오버로딩의 대표적인 예: println메서드.

    > PrintStream클래스에 정의되어있는 10개의 오버로딩된 println메서드:
    >
    > > void println()
    > >
    > > void println(boolean x)
    > >
    > > void println(char x)
    > >
    > > void println(char[] x)
    > >
    > > void println(double x)
    > >
    > > void println(float x)
    > >
    > > void println(int x)
    > >
    > > void println(long x)
    > >
    > > void println(Object x)
    > >
    > > void println(String x)

* 메서드 오버라이딩

  * 클래스는 상속이 가능한데, 상위클래스의 함수를 재정의 하는것을 오버라이딩이라고 한다.
    오버라이딩이 가능 할 경우, 확장과 변경에 용이하다.

    ```java
    public class Human extends Animal {
       @Override
        public void eat() {
            System.out.println("떡볶이가 맛있다.");
        }
    }
    ```

    



## 생성자 정의하는 방법

* 생성자란?

  * 인스턴스가 생성될 때 호출되는 '인스턴스 초기화 메서드'
  * 초기화 작업에 주로 사용되고, 인스턴스 생성 시, 실행되어야하는 작업을 위해서도 사용.
  * 클래스 내에 선언 되며, 메서드와 구조가 유사하지만 리턴값이 없다.
    * 그렇다고 생성자 앞에 키워드 void를 사용하지는 않으며, 아무것도 적지 않음.
  * 생성자의 조건:
    1. 생성자의 이름은 클래스의 이름과 같아야한다.
    2. 생성자는 리턴값이 없다.

* 생성자의 종류:

  1. 기본 생성자 (default constructor)

     * 모든 클래스에는 반드시 하나 이상의 생성자가 정의 되어있어야 한다.

     * 컴파일 시, 소스파일(*.java)의 클래스에 생성자가 하나도 정의 되지 않은 경우, **컴파일러는 자동적으로 '기본생성자(default constructor)'를 추가하여 컴파일** 한다.

       > 클래스이름() {} // 기본 생성자
       >
       > poitn() {} // point 클래스의 기본 생성자

     * 특별히 인스턴스 초기화 작업이 요구되어지지 않는다면, 생성자를 정의 하지 않고 기본 생성자를 사용하는 것도 좋다.

     

  2. 매개변수가 있는 생성자

     - 생성자도 매개변수를 선언하여 호출 시 값을 넘겨받아, 인스턴스의 초기화 작업에 사용할 수 있다.

     - 인스턴스마다 각기 다른 값으로 초기화 되어야하는 경우가 많기 때문에, 매개변수를 사용한 초기화는 매우 유용하다.

     - 인스턴스를 생성할 때, 결정해야하는 두 가지 사항:

       1. 클래스 - 어떤 클래스의 인스턴스를 생성할 것인가?
       2. 생성자 - 선택한 클래스의 어떤 생성자로 인스턴스를 생성할 것인가?

       

## this 키워드 이해하기

- constructor 의 매개변수명과 instance 변수명이 같을 경우 구별을 위한 참조변수.

  ```java
  public class Animal {
    private String name;
    private int age;
    
    public Animal(String name, int age) {
      // this 참조 변수. 매개변수 명과 instance의 변수 명이 같으므로 구분을 위해 사용.
      this.name = name;
      this.age = age;
    }
  }
  ```

  * 현재 클래스의 인스턴스 변수를 생성자에서 넘겨받은 변수로 대입하겠다는 의미이다.

  * this() 메서드는 생성자 내부에서만 사용할 수 있으며, 같은 클래스의 다른 생성자를 호출할 때 사용한다.
    this() 메서드에 인수를 전달하면, 생성자 중 메서드 시그니처가 일치하는 다른 생성자를 찾아 호출해준다.

    ```java
    public class Animal {
      private String name;
      private int age;
      
      public Animal() {
        this("호랑이", 3); // 아래에 있는 매개변수 2개를 받는 생성자를 호출하게 된다.
      } 
      
      public Animal(String name, int age) {
        // this 참조 변수. 매개변수 명과 instance의 변수 명이 같으므로 구분을 위해 사용.
        this.name = name;
        this.age = age;
      }
    }
    ```

    