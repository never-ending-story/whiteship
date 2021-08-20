# 학습할 것 (필수)

- 자바 상속의 특징
- super 키워드
- 메소드 오버라이딩
- 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
- 추상 클래스
- final 키워드
- Object 클래스

### 자바 상속의 특징

상속이란 어떤 클래스가 다른 클래스를 확장 시키는 것이다. 이 때 상속받는 클래스를 서브 클래스(혹은 derived class, extended class or child class)라고 하고 상속 당하는(?) 클래스를 수퍼 클래스(혹은 base class or parent class) 라고 부른다. 아래는 상속의 간단한 예시다.

```java
class Super {
  String name;
  
  Super(String name) {
    this.name = name;
  }
}

class Sub extends Super {
  int age;
  
  Sub(String name, int age) {
    super(name);
    this.age = age;
  }
}
```

상속에서 서브 클래스는 자신의 필드나 메서드와 더불어 수퍼 클래스의 public, protected(수퍼와 서브가 같은 패키지에 있다면 package-private까지) 접근제어자에 한해 멤버(필드와 메서드)를 포함하여 가진다. (생성자는 멤버가 아니므로 상속되지 않는다) 이러한 상속의 특징은 수퍼 클래스를 재사용할 수 있다는 장점을 가진다. 만약 두 클래스에 중복되는 필드나 메서드가 있다면 하나의 수퍼 클래스를 추출해 두 클래스에서 상속받으면 코드가 훨씬 간결해진다.

자바 상속의 특징은 다중상속을 허용하지 않는다. 한 클래스는 하나의 클래스만 상속받을 수 있다는 것이다. 상속은 여러 겹으로 이루어 질 수 있지만(A를 상속받은 B를 C가 상속받을 수 있음), 한번에 여러 클래스를 상속받지는 못한다. (-> 인터페이스를 이용한다면 somewhat 가능하지만 이건 8주차에)

### 메소드 오버라이딩

- 인스턴스 메서드
  - 서브 클래스에서 수퍼 클래스의 특정 인스턴스 메서드의 리턴타입, 메서드명, 매개변수 타입, 매개변수 개수, 매개변수 명를 똑같이 맞추고 내용을 다르게 한다면 오버라이딩
  - 오버라이딩은 수퍼 클래스의 메서드와 비슷(행동 - 메서드명, 매개변수, 리턴타입)하지만 하는 일이 조금은 다른 메서들르 생성하고 싶을 때 사용한다
  - 오버라이딩할 때에는 `@Override` 어노테이션이 필수
  - `@Override` 어노테이션이 붙었지만 수퍼클래스에 해당 메서드가 존재하지 않을 때 에러가 발생한다.
- 스태틱 메서드
  - 인스턴스 메서드와 마찬가지로 수퍼 클래스의 특정 스태틱 메서드의 시그니처와 리턴타입, 메서드명이 같을 시 수퍼 클래스의 스태틱 메서드를 '숨긴'다.
  - 스태틱 메서드는 클래스 별로 실행시킬 수 있으므로 어디서 불러오느냐에 따라 오버라이드 시 실행결과가 달라진다.

### super 키워드

`super` 키워드를 사용하면 수퍼 클래스의 필드와 메서드에 접근할 수 있다.

- 수퍼 클래스의 필드에 접근할 때

- 서브 클래스에서 수퍼 클래스의 메서드를 오버라이드 했을 때, 서브에서 오버라이드 된 메서드가 아닌 수퍼의 '원래' 메서드를 사용하고 싶을 때
- 수퍼 클래스의 생성자를 불러오고 싶을 때
  - 서브 클래스의 생성자에서는 수퍼 클래스의 생성자가 필수로 들어가야 한다. 하지만 수퍼 클래스에 매개변수가 없는 기본 생성자가 있는 경우는 제외한다.

```java
class Super {
  String name;
  
  Super() {
    this.name = "name";
  }
  
  Super(String name) {
    this.name = name;
  }
}

class Sub {
  int age;

  Sub(int age) {
    this.age = age;
  }
  
  Sub(String name, int age) {
    super(name);
    this.age = age;
  }
}
```

### 메소드 디스패치

자바는 런타임 시 객체를 생성하고 컴파일 시에는 생성할 객체에 대한 정보만 가지고 있는다.

- **스태틱 메소드 디스패치**

  - 컴파일 시점에서 특정 메서드를 호출할 것을 명확하게 알고있는 경우를 말한다. 

    ```java
    class Dog {
      void test() {
        System.out.println("bark");
      }
    }
    
    class Cat {
      void test() {
        System.out.println("meow");
      }
    }
    
    public static void main(String[] args) {
      Dog dog = new Dog();
      Cat cat = new Cat();
      
      dog.test(); // "bark"
      cat.test(); // "meow"
    }
    ```

    위 예시에서 `dog`과 `cat`은 모두 구현 클래스(수퍼 클래스가 아닌 클래스)이므로 아래 test 메서드를 호출하는 부분에서 할당된 인스턴스를 보지 않아도 어떤 메서드를 호출해야 할 지 명확하게 안다. 

- **다이나믹 메소드 디스패치**

  - 런타임 시점에서 어떤 메서드가 호출될 지 알게되는 경우를 말한다.

    ```java
    class Animal {
      void test() {
        System.out.println("animal");
      }
    }
    
    class Cat extends Animal{
      @Override
      void test() {
        System.out.println("meow");
      }
    }
    
    public static void main(String[] args) {
      Animal pet = new Cat();
      
      pet.test(); // "meow"
    }
    ```

    위의 예시에서 pet은 선언될 때 Animal 타입의 변수로 선언되었지만 실제로 할당되는 객체가 Cat의 인스턴스일 수 있기 때문에 컴파일 시점에서 `pet.test()`는 "animal"을 뱉어낼 지 "meow"를 뱉어낼 지 모른다.

### 추상 클래스

추상 클래스란 `abstract` 키워드를 가지고 추상 메서드를 가질 수 있는(가지지 않아도 되는) 클래스를 뜻한다. 추상 메서드를 하나라도 가지고 있다면 추상 클래스가 된다. 추상 클래스는 <u>인스턴스화 될 수는 없지만 상속될 수는 있다</u>.

추상 메서드란 `abstract` 키워드를 가지고 구현되지 않은 메서드를 의미한다. 메서드 자체는 선언부만 있고 구현되는 블럭을 가지지 않는다.

```java
public abstract thisIsAbstractMethod(String param);
```

추상 메서드의 구현은 추상 메서드가 속한 추상 클래스를 상속받는 서브 클래스에서 이루어진다. 만약 그 서브 클래스에서도 구현되지 않으면 해당 서브 클래스도 (추상 메서드를 가지므로) 추상 클래스가 된다.

추상 클래스와 인터페이스는 모두 인스턴스화 될 수 없고 구현체를 가지지 않은 메서드를 포함한다는 점에서 많이 닮아있다. 추상 클래스와 인터페이스 중 어떤 걸 선택해야할 지는 다음을 고려해보면 된다.

- 추상클래스
  - `static`, `final` 키워드가 붙지 않은 필드를 만들고 싶을 때
  - 추상 클래스와 이를 상속받을 서브 클래스가 서로 밀접한 연관이 있거나 서브 클래스가 공통된 메서드나 필드를 많이 가질 때
  - 접근 제어자를 `public`에만 한정되어 구현하고 싶지 않을 때
- 인터페이스
  - 인터페이스와 이를 구현할 클래스가 크게 연관이 없을 때
  - 다중 상속을 받고 싶을 때

### final 키워드

`final` 키워드가 붙은 메서드는 서브 클래스에서 오버라이드 할 수 없다. 이 경우는 보통 특정 메서드의 구현체가 바뀌면 안될 때쓰인다.

`final` 키워드가 붙은 클래스는 상속이 될 수 없다. 

`final` 키워드가 붙은 변수는 재할당 될 수 없다.

### Object 클래스

자바에서 생성되는 모든 클래스는 `Object` 클래스를 상속받는다. 자바에서는 단일 상속만을 지원하므로 다른 클래스를 상속받았다고 하더라도 타고 타고 올라가보면 `Object` 클래스가 나온다.

Object 클래스에는 필드가 없고 메서드로만 구성되어 있고 Object의 모든 함수들은 어떤 클래스에서도 호출이 가능하다.

| 메소드                             | 설명                                                    |
| ---------------------------------- | ------------------------------------------------------- |
| boolean equals(Object obj)         | 두 객체가 같은 지 비교한다.(같으면 true, 틀리면 false)  |
| String toString()                  | 객체의 문자열을 반환한다.                               |
| protected Object clone()           | 객체를 복사한다.                                        |
| protected void finalize()          | 가비지 컬렉션 직전에 객체의 리소스를 정리할때 호출한다. |
| Class getClass()                   | 객체의 클레스형을 반환한다.                             |
| int hashCode()                     | 객체의 코드값을 반환한다.                               |
| void notify()                      | wait된 스레드 실행을 재개할 때 호출한다.                |
| void notifyAll()                   | wait된 모든 스레드 실행을 재개할 때 호출한다.           |
| void wait()                        | 스레드를 일시적으로 중지할 때 호출한다.                 |
| void wait(long timeout)            | 주어진 시간만큼 스레드를 일시적으로 중지할 때 호출한다. |
| void wait(long timeout, int nanos) | 주어진 시간만큼 스레드를 일시적으로 중지할 때 호출한다. |

