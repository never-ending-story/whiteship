## 6주차 : 상속

### 1. 상속

- 자바에서 '***상속***'은 한 클래스에서 다른 클래스의 프로퍼티(변수와 메소드)를 얻기 위해 사용한다. (코드의 재사용)
- 두 클래스는 **부모**와 **자식** 관계를 맺게 된다.
- 자손은 **조상**의 모든 멤버를 상속받는다. (생성자, 초기화 블럭 제외)
- 그러므로 자손의 멤버는 조상보다 적을 수 없다. (같거나 많다.)
- 자손의 변경은 조상에 영향을 미치지 않는다.
- 자바는 단일 상속만 허용한다.

```java
class Parent {}

class Child extends Parent {
    // Parent 클래스를 확장(extend)해서 사용한다.
}
```

```java
class Parent1 {
    String name = "parent1";
}

class Parent2 {
    String name = "parent2";
}

class Child extends Parent1, Parent2 { // 에러, 조상은 하나만 허용한다.
    // super.name을 가져올 때 어떤 name을 가져올지 충돌이 발생
}
```

```java
class Vehicle {
    protected String brand = "Hyundai";
    public void honk() {
        System.out.println("빵 빵!");
    }
}

class Car extends Vehicle {
    private String model = "Sonata";

    public static void main(String[] args) {
        Car car = new Car();

        car.honk(); // 빵 빵!
    
        System.out.println(car.brand + " " + car.model) // Hyundai Sonata
    }
}
```

### 2. super 키워드

- 객체 자기 자신을 가리키는 참조변수, 인스턴스 메소드(생성자) 내에서만 존재 (static X)
- 조상의 멤버를 자신의 멤버와 구분할 때 사용

```java
class Parent {
    String name = "parent";

    public void whoAreYou() {
        System.out.println("I'm a parent");
    }
}

class Child extends Parent {
    String name = "child";

    public void whoAreYou() {
        System.out.println("I'm a child");
    }

    public void method() {
        System.out.println(name);        // child
        System.out.println(this.name);   // child
        System.out.println(super.name);  // parent
        whoAreYou();        // I'm a child
        this.whoAreYou();   // I'm a child
        super.whoAreYou();  // I'm a parent
    }
}
```
- 또는 조상의 생성자를 호출할 때 사용
- 생성자는 상속되지 않으므로 조상의 멤버는 조상의 생성자를 호출해서 초기화한다.
- **모든 생성자의 첫 줄에는 반드시 다른 생성자를 호출한다.**
- 그렇지 않으면 컴파일러가 생성자의 첫 줄에 `super();`를 삽입한다.

```java
class Child {
    String name = "";

    Child() {
        this("child"); // OK, this로 다른 생성자 호출
    }

    Child(String name) {
        // super(); // 조상(Object) 기본생성자가 컴파일러에 의해 삽입된다.
        this.name = name;
    }
}
```

```java
class Parent {
    String name = "";

    Parent(String name) { 
        // super(); // 조상(Object) 기본생성자가 컴파일러에 의해 삽입된다.
        this.name = name;
    }

}

class Child extends Parent { // Child가 Parent를 상속 했을 경우
    String name = "";

    Child() {
        this("child"); // OK, this로 다른 생성자 호출
    }

    Child(String name) {
        // super(); // Parent() 호출, but 해당 기본생성자가 Parent에 없으므로 에러
        this.name = name;
    }
}
```

### 3. 메소드 오버라이딩

- 상속받은 조상의 메소드를 자신에 맞게 변경하는 것

```java
class Parent {
    void show() {
        System.out.println("Parent's show()");
    }
}

class Child extends Parent {
    void show() {
        System.out.println("Child's show()");
    }
}

class Main {
    public static void main(String[] args) {
        // Parent 타입의 변수가 Parent 오브젝트를 참조하고 있으면
        // Parent's show가 호출된다.
        Parent obj1 = new Parent();
        obj1.show();

        // Parent 타입의 변수가 Child 오브젝트를 참조하고 있으면
        // Child's show가 호출된다.
        Parent obj2 = new Child();
        obj2.show();
    }
}
```
**Output :**
    
    Parent's show()
    Child's show()

- 오버라이딩 **하는** 메소드는 오버라이딩 **되는** 메소드보다 접근의 범위가 같거나 넓어야 한다.

```java
class Parent {
    // private 메소드는 오버라이딩 되지 않는다.
    private void m1() {
        System.out.println("parent m1");
    }

    protected void m2() {
        System.out.println("parent m2");
    }
}

class Child extends Parent {
    // 새로운 child의 m1 메소드
    private void m1() {
        System.out.println("child m1");
    }
    // 더 넓은 접근 범위로 오버라이딩
    // public > protected
    // private은 에러
    @Override
    public void m2() {
        System.out.println("child m2");
    }
}
```

```java
class Parent {
    // Child 클래스의 m1에 의해 숨겨진다.
    static void m1() {
        System.out.println("parent static m1()");
    }

    void m2() {
        System.out.println("parent instance m1()");
    }
}

class Child extends Parent {

    // 오버라이딩 X, Parent 클래스의 m1을 숨긴다.
    static void m1() {
        System.out.println("child static m1()");
    }

    @Override
    void m2() {
        System.out.println("child instance m2()");
    }

    public static void main(String[] args) {
        Parent obj1 = new Child();
        obj1.m1();
        obj1.m2();

        Child obj2 = new Child();
        obj2.m1();
        obj2.m2();
    }
}
```

**Output :**

    parent static m1()
    child instance m2()
    child static m1()
    child instance m2()

- static 메소드는 instance가 아닌 변수 type에 따라 호출된다. -> 메소드 호출이 컴파일타임에 정해진다.
- instance 메소드는 변수 type이 아닌 instance의 타입에 따라 호출된다. -> 메소드 호출이 런타임에 정해진다.
- 자식의 instance 메소드는 부모의 static 메소드를 오버라이딩 할 수 없고, 자식의 static 메소드는 부모의 static 메소드를 숨길 수 없다. 컴파일 에러가 발생한다.

```java
public class Parent {

    static void m1() {
        System.out.println("parent static m1()");
    }

    void m2() {
        System.out.println("parent instance m1()");
    }
}

class Child extends Parent {

    void m1() { // Parent의 static void m1()을 오버라이딩 할 수 없다. 컴파일 에러
        System.out.println("child instance m1()");
    }

    static void m2() { // Parent의 void m1()을 숨길 수 없다. 컴파일 에러
        System.out.println("child static m2()");
    }
}
```

### 4. 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)

- 다이나믹 메소드 디스패치는 오버라이딩 메소드 호출이 **컴파일 타임**이 아닌 ***런타임***에 결정되는 것이다.
    1. 오버라이딩 메소드를 호출할 때, 자바는 상위클래스의 메소드를 실행할지 하위클래스의 오버라이딩된 메소드를 실행할지를, 메소드를 호출하는 시점에 참조되고 있는 오브젝트(instance)의 타입에 따라 결정합니다. 그러므로 이런 결정은 런타임에 이루어집니다.
    2. `new Child();` 라면 Child 클래스의 메소드를, `new Parent();` 라면 Parent 클래스의 메소드를 실행합니다.

```java
class A {
    void m1() {
        System.out.println("A's m1 method");
    }
}

class B extends A {
    @Override
    void m1() {
        System.out.println("B's m1 method");
    }
}

class C extends A {
    @Override
    void m1() {
        System.out.println("C's m1 method");
    }
}

class Dispatch {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();

        A ref;

        ref = a; // A ref = new A();
        ref.m1();

        ref = b; // A ref = new B();
        ref.m1();

        ref = c; // A ref = new C();
        ref.m1();
    }
}
```

**Output :**
    
    A's m1 method
    B's m1 method
    C's m1 method

- 변수 ref의 타입이 무엇이든 간에 오버라이딩 메소드 호출은 오브젝트(instance)의 타입에 따라 결정된다.

### 5. 추상 클래스

- 추상메서드를 갖고 있는 클래스
- 인스턴스 생성 불가
- 상속을 통해서 추상메서드를 구현(오버라이딩)해야 인스턴스 생성 가능

```java
abstract class Player {
    abstract void play(int pos); // 추상메소드 (몸통 {}이 없는 미완성 메소드)
    abstract void stop(); // 추상메소드
}

Player p = new Player(); // 에러, 추상 클래스의 인스턴스 생성 불가

class AudioPlayer extends Player {
    @Override
    void play(int pos) { // 추상메서드를 구현

    }

    @Override
    void stop() { // 추상메서드를 구현

    }
}

AudioPlayer ap = new AudioPlayer(); // 인스턴스 생성 OK
```

### 6. final 키워드

```
final variable -> 상수를 생성한다.
final method -> 메소드 오버라이딩을 막는다.
final class -> 상속을 막는다.
```

#### 1. final variable
     - final로 선언된 변수는 변경이 불가능하다. (cannot be reinitialized)
     - 참조형 변수일 경우, 다른 오브젝트를 참조할 수 없다.
     - 배열이나 컬렉션을 참조할 경우, 그 안의 데이터는 추가하거나 삭제할 수 있다.
    - final 변수는 선언과 동시에 초기화되어야한다.
    - `=` 연산자를 통해 값을 할당하거나, 생성자 또는 초기화 블록(static변수는 static초기화블록)으로 초기화 해주어야 한다.

```java
class Main {
    final int a = 1;
    final int b;

    {
        b = 2;
    }

    // 또는 생성자를 통해 b에 값을 할당한다.
    /*
    Main() {
        b = 3;
    }
    */

    // 초기화 블록과 생성자를 통해 동시에 정의해서 같은 final 변수에 값을 할당하는 것은(여기서 b)
    // 먼저 시행된 초기화 블록에서 b = 2가 되고 생성자에서 b에 3을 재할당하는 행위이므로 에러가 발생한다.
}
```

```java
class Main {
    static final int a = 1;
    static final int b;

    static {
        b = 2;
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        final StringBuilder sb = new StringBuilder("Hello ");

        System.out.println(sb);

        // final 참조 변수 sb에 다른 참조 값을 재할당하는 것은 안되지만,
        // 참조하는 오브젝트(instance)의 내부 상태는 변경할 수 있다.
        sb.append("World");

        System.out.println(sb);
    }
}
```

**Output :**

    Hello
    Hello World

---

#### 2. final class

- 상속될 수 없는 마지막 클래스가 된다. (cannot be extended, inherited)
- immutable class를 만들 때 사용된다.
    - 우리가 아는 String, Wrapper class가 final class로 선언되어있다.

```java
final class A {

}

class B extends A { // 에러, final 클래스를 상속할 수 없다.

}
```

---

#### 3. final method

- 오버라이딩 될 수 없다. (cannot be overriden)

```java
class A {
    final void m1() {

    }
}

class B extends A {
    void m1() { // 에러, final 메소드는 오버라이딩할 수 없다.

    }
}
```

### 7. Object 클래스

`Object 클래스는 java.lang 패키지에 포함되어 있다. 모든 클래스는 직/간접적으로 Object클래스에 뿌리를 두고 있다. A 클래스가 아무런 클래스도 상속하고 있지 않다면 컴파일러는 Object클래스를 상속하도록 extends Object를 암묵적으로 추가한다.`

```java
class A {

}

// 위의 코드는 아래의 코드와 같다.

class A extends Object {

}
```

#### 1. toString()
- 한 오브젝트(instance)를 문자열 표현으로 나타낸다. (문자로 표현한다)
- 결과는 간결하게 정보를 잘 전달할 수 있어야 하며, 모든 하위 클래스에서 오버라이딩해서 사용하는게 권장된다.
- `'클래스이름' + '@' + '오브젝트 해쉬코드의 16진수표현'` 문자열을 반환한다. (오브젝트 주소값 아님 주의)

```java
public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
```

- 오브젝트의 참조값을 프린트하려고 할 때, 내부적으로 `toString()`가 호출된다.

```java
Student s = new Student();

// 아래의 두 명령의 결과는 같다.
System.out.println(s);
System.out.println(s.toString());
```

---

#### 2. hashCode()

`오브젝트마다 해쉬코드라는 고유값을 JVM이 생성한다. JVM은 해싱관련 자료구조 (HashTable, HashSet 등)에 오브젝트를 저장할 때 hashCode()를 사용한다. hashCode()를 오버라이딩할 때에는 오브젝트마다 유니크한 값을생성해야한다. equals()로 비교한 두 오브젝트가 같을 경우, 두 오브젝트의 hashCode()는 같은 값을 반환해야한다.`

#### 3. equals()

`두 객체가 같은 객체인지 확인하는 메소드`

```java
class Human {
    String name;

    Human(String name) {
        this.name = name;
    }
}

public class Test {
    public static void main(String[] args) {
        Human human1 = new Human("shion");
        Human human2 = new Human("shion");

        System.out.println(human1 == human2);      // false, 서로 다른 객체
        System.out.println(human1.equals(human2)); // false, 서로 다른 객체
    }
}
```

- `human1`과 `human2`는 서로 다른 객체지만, 두 객체는 논리적으로는 `shion` 이라는 같은 이름을 갖고 있기 때문에 두 객체를 같은 객체로 간주하고 싶다면 equals() 오버라이딩을 통해서 실현할 수 있다.

```java
class Human {
    String name;

    Human(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        Human _obj = (Human) obj;
        return this.name == _obj.name;
    }
}

public class Test {
    public static void main(String[] args) {
        Human human1 = new Human("shion");
        Human human2 = new Human("shion");

        System.out.println(human1 == human2);
        System.out.println(human1.equals(human2));
    }
}   
```

**Output :**

    false    // 실제로는 서로 다른 객체지만
    true     // 이름이 같다면 논리적으로 같은 객체 취급을 하고 싶었던 개발자가 equals()를 오버라이딩 했다.


- 하지만 이럴 경우, 같은 객체로 봐야하는지 다른 객체로 봐야하는지 혼동이 생길 수도 있으므로 hashCode()와 함께 오버라이딩하여 명확히 해주는 것이 권장된다.

---

#### 4. getClass()

`현재 오브젝트의 클래스 오브젝트를 반환한다. (Class라는 이름의 class)`

```java
public final class Class<T> implements ... {

}
```

- JVM에서 `.class`파일을 로딩 후, java.lang.Class 타입의 오브젝트를 Heap 메모리 영역에 생성한다.
- 이 오브젝트를 통해서 클래스의 정보를 가져올 수 있다.

---

#### 5. finalize() 

`한 오브젝트가 더이상 참조되지 않아 Garbage Collection의 대상이 되고, Garbage Collector에 의해 딱 한번 실행되는 메소드`

---

#### 6. clone()

`한 오브젝트와 똑같은 오브젝트를 복제한다. 한 오브젝트에 대해 작업을 할때, 원래의 오브젝트를 보존하고 clone() 메서드를 이용해서 새로운 오브젝트로 작업을 하면, 작업 이전의 값이 보존되므로 작업에 실패해서 원래의 상태로 되돌리거나 변경되기 전의 값을 참고하는데 도움이된다.`
- 오브젝트의 참조타입 변수의 경우, 참조값이 그대로 복사되므로 복제된 오브젝트의 작업이 원본 오브젝트에 영향을 미치게 된다.
- 이 경우, clone() 메서드를 오버라이딩해서 변수가 참조할 수 있는 새로운 오브젝트를 생성하고 기존의 내용을 복사해야한다.
