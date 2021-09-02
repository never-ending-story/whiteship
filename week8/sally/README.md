### 인터페이스 정의하는 방법

인터페이스는 간단히 말해서 구현체를 전혀 가지지 않는 추상클래스라고 할 수 있다. 자바에서 다중상속을 지원하지 않아 대체 방안으로 나온 게 인터페이스인데 그에 맞게 인터페이스는 한번에 여러 인터페이스를 받을 수 있다.

추상클래스는 확장을 중점적으로, 인터페이스는 구현을 중점적으로 본다. 추상 클래스는 부모 클래스가 자식 클래스 '속해'있다. 동물-독수리, 탈 것-비행기의 관계를 예로 들 수 있다. 인터페이스는 기능을 부여한다고 볼 수 있다. 앞서 들었던 예시에서 독수리와 비행기는 전혀 다른 카테고리에 속해있지만 날 수 있다는 기능은 공통적으로 가지고 있다. 이럴 때 인터페이스를 쓴다.

```java
    abstract class Animal {
        int legs;
        abstract void move();
    }

    abstract class Transportation {
        int wheels;
        abstract void move();
    }

    interface Flyable {
        int wings = 2;
        void fly();
    }

    class Eagle extends Animal implements Flyable {
        @Override
        void move() {
            System.out.println("eagle's moving");
        }

        @Override
        public void fly() {
            System.out.println("Flying 50feet high");
        }
    }

    class Airplane extends Transportation implements Flyable {

        @Override
        void move() {
            System.out.println("Airplane's moving");
        }

        @Override
        public void fly() {
            System.out.println("Flying 60,000feet high");
        }
    }
```

인터페이스를 정의할 때에는 앞에 `interface` 키워드를 붙여 주고 클래스를 정의할 때와 같이 하면 된다. 다만 구현은 하지 않으며 필드들은 모두 초기화 해주어야 한다. 모든 필드는 `public static final`이고 생략 가능하다. 모든 메서드는 `public abstract`이고 생략 가능하다. 생략 가능한 키워드들은 IDE에서도 생략하기를 권장한다.

### 인터페이스 구현하는 방법

인터페이스 안에 구현된 메서드가 아무것도 없으므로 인터페이스를 구현하는 구현체 클래스는 인터페이스에 선언된 메서드를 모두 구현해야 한다. 만약 구현하지 않을 시 메서드 앞에 `abstract` 키워드를 붙여줘야 하고 그 클래스는 추상클래스가 된다.

인터페이스는 '구현하다'라는 뜻의 `implements` 키워드를 사용해 특정 클래스가 인터페이스를 가져와 '구현할 것'임을 알려준다. 클래스는 `extends` 키워드를 쓰는 것에 있어 위에서 했던 추상클래스, 인터페이스의 비교도 더 쉽게 이해할 수 있다.

여러 인터페이스를 구현할 때에는 인터페이스 간에 콤마를 사용해 구분짓는다. 인터페이스를 구현하는 동시에 클래스를 상속받는 것도 가능하다.

```java
class ExampleClass extends AnotherExample implements InterfaceExample, InterfaceExample2 {
  @Override
  ...
}
```



### 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

인터페이스 레퍼런스를 통해 구현체를 사용한다는 것은 인터페이스로 타입을 선언한 후 해당 인터페이스의 구현체가 되는 클래스의 객체를 참조하겠다는 뜻이다. 이렇게 되면 인터페이스에 선언된 필드와 메서드만 사용할 수 있게 된다.

```java
interface Flyable {
    int wings = 2;

    void fly();
}

class Eagle implements Flyable {
    int eyes = 2;

    public int getEyes() {
        return this.eyes;
    }

    @Override
    public void fly() {
        System.out.println("Flying");
    }
}

public static void main(String[] args) {
    Flyable eagle = new Eagle();
    eagle.fly();
    // eagle.getEyes(); 컴파일 에러
}
```

메서드의 리턴타입을 인터페이스로 지정하면 해당 인터페이스의 구현체를 반환할 수 있다.

### 인터페이스 상속

인터페이스끼리의 상속은 `extends` 키워드를 쓰며 클래스와는 다르게 인터페이스끼리는 다중상속이 가능하다. 자식 인터페이스는 부모 인터페이스가 가진 필드와 메서드를 모두 가지고 거기에 더 추가할 수 있게 된다. 부모 인터페이스를 상속받은 자식 인터페이스가 있고 그 자식 인터페이스의 구현체가 되는 클래스는 부모, 자식 인터페이스(다중 상속이 있다면 그것도 같이)의 메서드를 모두 구현해야 한다. 구현체는 구현한 모든 인터페이스의 인스턴스화가 가능하다.

```java
    interface Ex1 {
        void ex1();
    }

    interface Ex2 {
        void ex2();
    }
    
    interface Ex3 extends Ex1, Ex2{
        void ex3();
    }
    
    class 구현체 implements Ex3 {

        @Override
        public void ex1() {
            System.out.println("ex1");
        }

        @Override
        public void ex2() {
            System.out.println("ex2");
        }

        @Override
        public void ex3() {
            System.out.println("ex3");
        }
    }
```

### 인터페이스의 기본 메소드 (Default Method), 자바 8

구현체에서 어떤 메서드를 구현할 때 같은 동작을 할 때 

인터페이스에 새로운 메서드를 추가해야하는 상황이 생겼다고 가정할 때, 그 인터페이스를 구현하던 클래스는 새롭게 추가된 메서드를 구현해야하고 이 과정에서 오류가 날 확률이 크다. 또 구현체에서 반복적으로 같은 동작을 구현할 때에도 마찬가지다. 이에 방안책으로 기본 메서드라는 기능이 자바 8에서부터 생겼다.

기본 메서드는 `default` 키워드를 가지며 추상메서드가 아니고 그래서 메서드 정의할 때 몸통 구현이 필수고 그래서 인터페이스를 구현할 때 이 `default method` 는 따로 변경해주지 않아도 된다. 오버라이드 하려면 할 수 있다.

### 인터페이스의 static 메소드, 자바 8

자바 8부터 인터페이스 메서드에서 static 키워드의 사용이 가능해졌다. 기본 메서드와 같이 구현이 필요하지만 static 키워드가 붙어 오버라이딩은 불가능하다.

### 인터페이스의 private 메소드, 자바 9

자바 9에서부터 지원하는 `private` 키워드의 사용은 인터페이스 내부에서만 사용 가능하다. 기본 메소드나 스태틱 메소드를 구현할 때 로직이나 중복되는 코드를 분리하기 위해 생긴 것으로 알고있다.