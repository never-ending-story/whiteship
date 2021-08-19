## 학습 1) 자바 상속의 특징
### 개념 
- 한 클래스에서 파생된 클래스를 `subclass`라고 한다. ( == `derived class`, `extended class`, `child class` ) 
- 그 대상이 된 클래스를 `super class` ( == `base class`, `parent class`)라고 한다. 
-	`is -a` 관계가 성립한다. 
	-	하위클래스 `is a` 상위클래스 ) 
- 키워드 `extends`를 사용한다. 
``` java
class Vehicle {
	..
}

class Car extends Vehicle {
	.. 
}

```
- 장점 : 코드 중복 막고, 공통적인 코드 변경에 용이 
-	**단일상속** : Object을 제외하고 모든 클래스는 단 하나의 슈퍼 클래스가 있다. ( extends A, B 불가), ( c++은 다중상속 허용 ) 
	-	명시적으로 슈퍼클래스가 표시되어있지 않은 클래스는 Object의 하위 클래스다. (extends Object) 
    - implements를 활용해서 다중 상속을 흉내낼 수 있다. 자바의 정석 참고. 
 	

### 부모클래스의 모든 것을 가져온다? 
-	부모 클래스의 public, protected 멤버변수 모든 것을 가져온다. (패키지에 상관없이)
-	하위 클래스가 부모와 같은 패키지에 있다면 package-private 멤버변수도 다 가져올수도 있다. (replace도 가능) 
-	생성자는 멤버가 아니므로 상속되지 않는다. 
	-	`super()` 등을 통해 하위클래스에 생성자를 적용할 수 있다. (아래 `super()` 설명)
-	superclass의 private 멤버는 상속되지 않는다. 다만 superclass의 public, protected 메소드가 private 멤버에 접근하고 있다면 이것은 subclass에서도 사용할 수 있다. 
```
public class Practice {
    public static void main(String[] args) {
        Car c = new Car();
        c.printPrivateN();
    }
}

class Vehicle {
    private int n = 1;

    public int getPrivateN() {
        return this.n;
    }
}

class Car extends Vehicle {
    public void printPrivateN() {
        int n = super.getPrivateN();
        System.out.println(n); // 1출력
    }
}

```

- 오버라이딩 메소드는 조상 클래스의 메소드의 하위 타입을 반환할 수 있다. (아래 `covariant return type`)
## 학습 2) super 키워드
### 부모클래스의 멤버에 접근 할 때  
- 자식 클래스에서 부모 클래스를 가리킬 때 사용
- 만약 부모클래스의 메소드를 오버라이딩하고 있다면 `super` 키워드 통해서 부모클래스의 메소드에 접근 가능 하다 
```java
public class Superclass {

    public void printMethod() {
        System.out.println("Printed in Superclass.");
    }
}
```
```java
public class Subclass extends Superclass {

    // overrides printMethod in Superclass
    public void printMethod() {
        super.printMethod();
        System.out.println("Printed in Subclass");
    }
}
```
### 자식클래스의 생성자에서 사용할 때 
- 부모클래스의 생성자를 가리킬 때 사용 
- `super()` or `super(parameter list)`
```java
public MountainBike(int startHeight, 
                    int startCadence,
                    int startSpeed,
                    int startGear) {
    super(startCadence, startSpeed, startGear);
    seatHeight = startHeight;
}   
```
-	자식클래스에서 명시적으로 부모클래스의 생성자를 호출하지 않으면, 자바 컴파일러가 자동적으로 인자 없는 부모클래스의 생성자를 호출한다. ( `super()` )
	-	이 때, 만약 부모클래스에 인자 없는 생성자가 없으면 컴파일 에러가 날 것이다. 
    -	모든 클래스의 조상인 Object는 인자없는 생성자가 있기 때문에, 부모클래스가 Object라면 이런 문제는 일어나지 않는다. 
```java
class Vehicle2 {
    int num = 1;

    Vehicle2(int num) {
        this.num = num;
    }
}

class Car2 extends Vehicle2 {
    int num;
    String name;

    Car2(int num, String name) { //컴파일 에러. 
        this.num = num;
        this.name = name;
    }
}
```
-	위에서 Car2의 생성자에서 super에 대한 명시가 없기 때문에 자동적으로 `super()`를 호출한다. Vehicle2() 와 같은 인자 없는 생성자가 Vehicle에 없기 때문에 컴파일 에러가 발생한다. 

```java
class Vehicle2 {
    int num = 1;

    Vehicle2(int num) {
        this.num = num;
    }
}

class Car2 extends Vehicle2 {
    int num;
    String name;

    Car2(int num, String name) {
        super(num);
        this.name = name;
    }
}
```
-	존재하는 super()를 작성해 줘야 한다. 
```java
class Car2 extends Vehicle2 {
    int num;
    String name;

    Car2(int num, String name) {
        super(num);
        this.name = name;
    }
}
```


## 학습 3) 메소드 오버라이딩
- 상속받은 조상의 메소드를 오버라이딩 하는 것. 
- 주의. 오버로딩과 헷갈리지 말기
-	주의. hiding
	-	static 메서드 오버라이딩이 아니라 hiding
    	-	오버라이딩은 런타임에 객체를 호출하는데 static은 컴파일 때 올라가기 때문에
```java
public class Animal {
  public static void testClassMethod() {
    System.out.println("The static method in Animal");
  }

  public void testInstanceMethod() {
    System.out.println("The instance method in Animal");
  }
}

public class Cat extends Animal {
  public static void testClassMethod() {
    System.out.println("The static method in Cat");
  }

  @Override
  public void testInstanceMethod() {
    System.out.println("The instance method in Cat");
  }

  public static void main(String[] args) {
    Animal animal = new Cat(); 
    Animal.testClassMethod(); //The static method in Animal
    animal.testInstanceMethod(); //The instance method in Cat
  }
}

```
     
### 오버라이딩 조건 
1. 메소드의 선언부 ( `메소드의 시그니처` : 메소드 이름, 매개변수 타입과 수, 리턴타입) 이 같아야 한다. 
```java
class Vehicle3 {
    int oil = 100;

    public void run() {
        oil--;
    }
}

class Car3 extends Vehicle3 {
    public void run() {
        oil -= 2;
    }
}
```
2. 접근제어자는 부모 클래스의 접근제어자 보다 좁은 범위로 변경할 수 없다. 
```java
class Vehicle3 {
    int oil = 100;

    public void run() {
        oil--;
    }
}

class Car3 extends Vehicle3 {
    protected void run() { // 컴파일 에러 
        oil -= 2;
    }
}
```
3. 조상 클래스의 메소드보다 많은 수의 예외를 선언할 수 없다. 
```java
class Vehicle3 {
    int oil = 100;

    public void run() throws IndexOutOfBoundsException{
        oil--;
    }
}

class Car3 extends Vehicle3 {
    public void run() throws IndexOutOfBoundsException, IOException { // 컴파일 에러 
        oil -= 2;
    }
}
```
### covariant return type
- 오버라이딩 하면 그 오버라이딩한 조상클래의 메소드의 리턴타입의 하위 클래스를 리턴할 수 있다.
```java
class B extends A {

}

class C {
    
}

class Vehicle3 {
    int oil = 100;

    public void run() throws IndexOutOfBoundsException{
        oil--;
    }

    public A getObject() {
        return new A();
    }
}
```
``` java

class Car3 extends Vehicle3 {
    public void run() throws IndexOutOfBoundsException {
        oil -= 2;
    }
    
    public C getObject() { //컴파일 에러
        return new C();
    }
```
```java
class Car3 extends Vehicle3 {
    public void run() throws IndexOutOfBoundsException {
        oil -= 2;
    }

    public B getObject() { //이상무 
        return new B();
    }

}
```
### 인터페이스 메소드의 우선순위 
#### 규칙 1 : 인터페이스의 디폴트 메소드보다 인스턴스 메소드가 우선한다. (클래스우선)
```java
public class OverridingPriority {
    public static void main(String... args) {
        Pegasus myApp = new Pegasus();
        System.out.println(myApp.identifyMyself()); // "I am a horse."
    }
}

class Horse {
    public String identifyMyself() {
        return "I am a horse.";
    }
}
interface Flyer {
    default public String identifyMyself() {
        return "I am able to fly.";
    }
}
interface Mythical {
    default public String identifyMyself() {
        return "I am a mythical creature.";
    }
}

class Pegasus extends Horse implements Flyer, Mythical {
    
}
```

#### 규칙2. 다른 후보에 의해 오버라이딩된 메소드는 무시한다. (더 구체적인 메소드 우선)
한 예를 들면, 한 클래스가 여러 인터페이스를 implements 했고, 그 인터페이스 목록들에 같은 메소드가 있을 때 그 중 누군가가 힌 인터페이스를 오버라이딩을 했다면 그것을 따른다. (더 구체화된 메소드를 따른다) 
```java
public interface Animal {
    default public String identifyMyself() {
        return "I am an animal.";
    }
}
public interface EggLayer extends Animal {
    default public String identifyMyself() {
        return "I am able to lay eggs.";
    }
}
public interface FireBreather extends Animal { }

public class Dragon implements EggLayer, FireBreather {
    public static void main (String... args) {
        Dragon myApp = new Dragon();
        System.out.println(myApp.identifyMyself()); //I am able to lay eggs.
    }
}

```
지금 여기서 Droagon은 EggLayer와 FireBreather를 implements 했는데 이 두 후보 중에서 EggLayer가 해당 메소드를 오버라이딩 했으므로 FireBreather의 메소드는 무시된다.

아래와 같은 경우를 보면, 
```java
interface MyInterfaceA {
    default void print() {
        System.out.println("Print in MyInterfaceA");
    }
}

interface MyInterfaceB extends MyInterfaceA {
    default void print() {
        System.out.println("Print in MyInterfaceB");
    }
}
class MyClass1 implements MyInterfaceA {
}
class MyClass2 extends MyClass1 implements MyInterfaceA, MyInterfaceB {
}

public class OverridingPriority {
    public static void main(String... args) {
        MyClass1 myClass1 = new MyClass1();
        myClass1.print(); // Print in MyInterfaceA
        MyClass2 myClass2 = new MyClass2();
        myClass2.print(); // Print in MyInterfaceB
    }
}
```
myClass2.print() 할 때 어떤 메소드를 호출할지를 결정해야한다. 규칙1에 따라 클래스가 우선시 되서 MyClass1의 메소드가 가장 우선시 되야 할 것이다. 하지만 MyClass1는 해당 메소드는 구현하고 있지 않아 규칙1이 적용될 수 없다. 

규칙2에 따라 더 구체적인 디폴트 메소드가 우선시되므로 (MyInterfaceA 보다 MyInterfaceB가 더 구체적이라 이와 같은 결과가 나왔다. 


#### 두 개 이상의 독립적으로 정의된 독립된 디폴트 메서드가 충돌하거나(case1), 디폴트 메서드와 추상 메서드가 충돌되는 경우에는 컴파일 에러가 발생한다. (case2)
- (case1)
```
public interface OperateCar {
    // ...
    default public int startEngine(EncryptedKey key) {
        // Implementation
    }
}
public interface FlyCar {
    // ...
    default public int startEngine(EncryptedKey key) {
        // Implementation
    }
}
```
- (case2) 
```java
interface MyInterfaceA {
    default void print() {
        System.out.println("Print in MyInterfaceA");
    }
}

class MyClass2 implements MyInterfaceA {
}
```
위에서는 컴파일에러가 안난다. 디폴트 메서드가 있으니까 
```java 
interface MyInterfaceA {
    default void print() {
        System.out.println("Print in MyInterfaceA");
    }
}

abstract class MyClass1 {
    public abstract void print();
}

class MyClass2 extends MyClass1 implements MyInterfaceA {
} // 컴파일 에러 
```
하지만 위처럼 되면 MyInterfaceA에서 메소드를 구현해 줌에도 불구하고 컴파일 에러가 난다.  추상메소드와 디폴트 클래스가 충돌하기 때문. MyClass2에서 다시 오버라이딩 해줘야 한다. 

그런데 MyInterfaceA에 있는 것을 굳이 반복해서 쓸 필요가 없다. `super` 를 사용하면 된다. 
```java 
interface MyInterfaceA {
    default void print() {
        System.out.println("Print in MyInterfaceA");
    }
}

abstract class MyClass1 {
    public abstract void print();
}

class MyClass2 extends MyClass1 implements MyInterfaceA {
    public void print() {
        MyInterfaceA.super.print();  // Print in MyInterfaceA
    }
}
```
- super 키워드 사용법이 더 궁금하면 [여기](https://docs.oracle.com/javase/tutorial/java/IandI/override.html)


> 요약 
> - 규칙 1. 클래스가 항상 우선
> - 규칙 2. 더 구체적인 디폴트를 제공하는 인터페이스가 우선. 


## 학습 4) 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
-	오버라이딩된 메소드 호출할 때  어떤 메소드를 호출할지를 (컴파일 할 때가 아니라) **런타임** 때 결정하는 매커니즘 
```java
public class DynamicMethodDispatch {
    public static void main(String[] args) {
        Fruit fruit = new Fruit();
        Fruit peach = new Peach();

        fruit.eat(); //"과일은 맛있어"
        peach.eat(); //"복숭아는 맛있어"
    }
}

class Fruit {
    public void eat() {
        System.out.println("과일은 맛있어");
    }
}

class Peach extends Fruit {
    @Override
    public void eat() {
        System.out.println("복숭아는 맛있어");
    }
}
```
만약 컴파일 할 때 결정하면(참조타입에 의해 결정되므로) peach.eat() 도 "과일은 맛있어"가 되어야 한다.

런타임에 결정하면 JVM이 참조변수에 저장된 객체를 확인하므로 위와 같은 결과가 나온다. 


## 학습 5) 추상 클래스
-	`abstract`로 선언된 클래스 
-	추상 메소드가 있을 수도 있고 없을 수도 있다. 
	-	추상메소드는 구현부가 없는 메소드다 
```
abstract void moveTo(double deltaX, double deltaY);
```
-	추상메소드가 있는 클래스는 반드시 추상메소드어야만 한다. 

-	추상클래스의 인스턴스는 만들 수 없다. 
	-	상속해서 추상 메소드를 구현해야 한다. 
-	추상클래스는 하위클래스를 가질 수 있다. 


- 보통 abstract의 하위 클래스는 모든 추상 메소드를 구현한다. 그렇지 않으면 그 하위클래스는 `abstract`로 선언되어야 한다. 
-	추상 클래스의 특징을 생각할 때 추상클래스는 일반 클래스인데 추상메소드를 갖고 있는 것이라고 생각하면 편하다.
-	참고. 인터페이스의 메소드는 디폴트나 static으로 선언되지 않으면 전부 abstract이다. 그래서 따로 abstract라고 명시할 필요가 없다.  
 
## 학습 6) final 키워드
-	 값이 오직 한번만 할당될 수 있음을 나타낸다 (변경할 수 없다.
-	Final  클래스 : 서브클래스가 만들어질 수 없다. 
	-	자바 표준 라이브러리 클래스(ex java.lang.String)의 상당수는 final이다 
    -	https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.1.1.2
-	Final 메소드 : 오버라이딩 되거나 서브클래스에 의해 hide 될 수 없다. 
	-	https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.4.3.3
```java
public class Base{
    public       void m1() {...}
    public final void m2() {...}

    public static       void m3() {...}
    public static final void m4() {...}
}

public class Derived extends Base
{
    public void m1() {...}  // OK, overriding Base#m1()
    public void m2() {...}  // forbidden

    public static void m3() {...}  // OK, hiding Base#m3()
    public static void m4() {...}  // forbidden
}


```
- Final 변수 : 오직 한번만 초기화될 수 있다. 꼭 선언할 때 초기화될 필요는 없다. 

## 학습 7) Object 클래스
-	모든 클래스의 조상이다. 
	-	java는 단일상속이므로, 부모 클래스가 명시되있지 않으면 extends Object 
-	다양한 메서드를 가지고 있다. 
-	메서드1) clone() : 특정 클래스를 복제해서 인스턴스를 생성할 때 사용 
	-	사용방법
    	-	Cloneable을 implements 해서 해당 클래스가 복제 가능한 클래스라는 사실을 JVM에게 알려준다. 
		-	그리고 예외처리 해줘야 한다. `throws CloneNotSupportedException` 
        ```java
        public class Clone {
            public static void main(String[] args) {
                Student student = new Student("Cheol");
                student.toString(); // 가능, 접근제어자 public
                student.clone() ; // 불가능, 접근제어자 protected
            }
        }

        class Student implements Cloneable {
            String name;
            Student(String name) {
                this.name = name;
            }
        }
        ```
		-	접근제어자가 `protected` 이므로 Object와 다른 패키지에서 쓰고 싶다면 (일반적으로 이에 해당한다. 우리가 정의한 클래스에서 사용하고 싶다면)	clone 다시 써준다.  

```java
class Test {
	int x, y;
}

class Test2 implements Cloneable {
	int a;
	int b;
	Test c = new Test();
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}


public class Main {
	public static void main(String args[])
		throws CloneNotSupportedException
	{
		Test2 t1 = new Test2();
		t1.a = 10;
		t1.b = 20;
		t1.c.x = 30;
		t1.c.y = 40;

		Test2 t2 = (Test2)t1.clone();


		t2.a = 100;
		t2.c.x = 300;

		System.out.println(t1.a + " " + t1.b + " " + t1.c.x
						+ " " + t1.c.y); // 10 20 300 40
		System.out.println(t2.a + " " + t2.b + " " + t2.c.x
						+ " " + t2.c.y); // 100 20 300 40
	}
}
```
-	메서드2) equals() : 오버라이딩해서 동등성 정의 
-	메서드3) finalize() : 객체가 소멸될 때 호출되기로 약속된 메소드. 오버라이딩 해서 객체가 소멸될 때 일어나야 할 일을 작성 할 수 있다. 
	-	많은 자바의 전문가가 이 메소드의 사용을 지양한다. 사용될 일 거의 없다. 
-	메서드4) getClass() :  클래스 정보를 얻기 위해 사용한다. (getSimpleName(), getSuperclass(), getInterfaces())
	-	오버라이드 할 수 없다
    ```java
        void printClassName(Object obj) {
        System.out.println("The object's" + " class is " +
            obj.getClass().getSimpleName());
    }

    ```
-	메서드5) hashCode()
-	메서드6) toString() 
--- 
### 출처 
-	[자바의정석-ch7]()
-	학습 1) 자바 상속의 특징 
	- 개념 : 	https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html
    	-	이거 번역 잘해두신 : https://github.com/ByungJun25/study/tree/main/java/whiteship-study/6week#%EC%83%81%EC%86%8D%EC%9D%B4%EB%9E%80
    - superclass의 private 멤버에 접근 : https://stackoverflow.com/questions/19843021/access-a-private-variable-from-the-superclass-java/19843123
-	학습 2) super 키워드
	-	https://docs.oracle.com/javase/tutorial/java/IandI/super.html
-	학습 3) 메소드 오버라이딩 
	-	https://docs.oracle.com/javase/tutorial/java/IandI/override.html
    	-	hiding : https://ohgyun.com/242
	-	Covariant return type : https://en.wikipedia.org/wiki/Covariant_return_type
	-	인터페이스 메소드의 우선순위  https://javadevcentral.com/default-method-resolution-rules
-	학습 4) Dynamic Method Dispatch
	-	https://www.geeksforgeeks.org/dynamic-method-dispatch-runtime-polymorphism-java/
    -	https://www.tutorialspoint.com/Dynamic-method-dispatch-or-Runtime-polymorphism-in-Java
-	학습 5) 추상 클래스 
	-	https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
-	학습 6) final
	-	https://en.wikipedia.org/wiki/Final_(Java)
-	학습 7) Object 
	-	clone() https://www.geeksforgeeks.org/clone-method-in-java-2/, [생활코딩](https://www.youtube.com/watch?v=g7zFI2Mr_Xc)
    -	finalize() [생활코딩](https://www.youtube.com/watch?v=QdqUtyq7EJA&t=129s)
