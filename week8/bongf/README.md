## 학습 1) 인터페이스 정의하는 방법
### 인터페이스란 
-	[일종의 계약과 같다.](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)
	-	코드의 group이 있다고 할 때 각각의 그룹은 다른 그룹의 코드가 어떻게 작성되었는지 몰라도 상호작용 하면서 코드를 작성할 줄 알아야 하는데 이 때 필요한 것이 interface 
    -	A그룹과 B그룹이 소통해야 한다면 B그룹 표준의 인터페이스를 만들 것. 그럼 B그룹은 A메소드의 어떤 메소드를 사용하면 되는지 알 수 있다. 
    -	윈도우가 어떻게 짜여 있는지 모르고 우리가 사용할 수 있는 것처럼 (UI) (겉껍질) 
-	(프로그래밍 관점) 추상 메서드의 집합 (자바의정석) 
	-	일종의 추상 클래스와 갖지만 추상 클래스와 달리 몸통을 갖춘 일반 메서드 또는 멤버 변수를 구성원으로 가질 수 없다. 
    -	오직 추상메서드와 상수만을 멤버로 가질 수 있다.
    	-	JDK 1.8부터 디폴트 메서드와 static메서드 가질 수 있다. 
    -	추상 클래스가 미완성 설계도라면 인터페이스는 밑그림만 있는 기본 설계도 

### 인터페이스 정의 
```java
inteface 인터페이스이름 {
  public static final 타입 상수이름 = 값;
  public abstract 메서드이름(매개변수목록); 
}
```
-	키워드 `interface`
-	모든 멤버변수는 public static final (상수)
	-	모두가 그렇게 때문에 생략 가능하다 
-	모든 메서드는 public 
	-	모두가 그렇기 때문에 생략 가능하다
    -	static, 디폴트 메서드만 메소드의 바디를 가질 수 있다. 나머지는 abstarct. 
```java 
interface 인터페이스이름 {
  public static final int one = 1; 
  final int two = 2; // public static final int two = 2;
  static int three = 3; // public static final int three = 3;
  int four = 4; // public static final int four = 4;
	
  public abstract String getSomething();
  String getOther(); //  public abstract String getOther();
}


```
-	인터페이스는 인스턴스화 될 수  없다. 
	-	인터페이스를 구현한 클래스의 인스턴스를 사용가능 

### 인터페이스 사용이유 
-	개발시간 단축
	-	인터페이스를 정해두고 해당 인터페이스를 사용해서 프로그램을 개발하는 동시에 인터페이스에 관한 구체적인 코드 개발 동시 가능 
-	표준화 가능 
-	서로 관계없는 클래스들에게 관계를 맺어 줄 수 있다. 
-	독립적인 프로그래밍이 가능 
	-	클래스 선언과 구현을 분리해서 실제 구현에 독립적인 프로그램 작성 가능 (인터페이스라는 막을 두고 간접관계 형성) 
## 학습 2) 인터페이스 구현하는 방법
-	`implements`를 사용하여 인터페이스에 정의된 추상메서드를 완성하면 인스턴스를 생성할 수 있다. 
	-	인터페이스의 메서드 일부만 구현하면 abstract으로 추상 클래스로 선언해야 한다. 
```java
public interface Car {
    void run();
    void stop();
}

public class BungBung implements Car{

    @Override
    public void run() {
        System.out.println("brrrrr");
    }

    @Override
    public void stop() {
        System.out.println("Kkik!");
    }
}

```
-	`implements` 뒤에는 여러 인터페이스가 올 수 있다. `class A implements B, C` 
	-	B, C는 인터페이스
-	컨벤션을 따르자면 상속 뒤에 구현이 온다. `class A extends B implements C` 
    
## 학습 3) 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

### 인터페이스 타입의 참조변수로 인터페이스 구현체 참조하기
```java
 Car car = new BungBung(); // class BungBung implements Car
```
-	인터페이스 타입의 참조변수로 이 인터페이스의 구현체를 참조할 수 있다. 
	-	이럴 때는 인터페이스에 명시된 메서드만 사용할 수 있다. 
	-	Car에는 없는 methodA가 BungBung에 작성되어있을 경우 위에서 Car 타입의 참조변수 car은 methodA를 사용할 수 없다. 
    
### 인터페이스 타입의 매개변수로 갖는 메서드
-	해당 메서드 호출시 해당 인터페이스의 구현체를 사용 
```java
public class BungBung implements Car{

    @Override
    public void run() {
        System.out.println("brrrrr");
    }

    @Override
    public void stop() {
        System.out.println("Kkik!");
    }

    public void greet(Car car) {
        System.out.println("hello");
    }
}
```
```java
public class InterfacePractice {
    public static void main(String[] args) {
        Car car = new BungBung();
        BungBung bung = new BungBung();
        bung.greet(car);
    }
}
```
### 리턴타입을 인터페이스로 갖는 메서드
-	해당 인터페이스의 구현체를 return 해야 한다. 
```java
public class BungBung implements Car{

    @Override
    public void run() {
        System.out.println("brrrrr");
    }

    @Override
    public void stop() {
        System.out.println("Kkik!");
    }
    
    public Car copy() {
        return new BungBung();
    }
}
```



## 학습 4) 인터페이스 상속
-	인터페이스는 인터페이스로부터만 상속 가능.
-	다중 상속 가능 
	-	인터페이스는 다른 인터페이스를 extends (상속) 할 수 있다. (클래스는 하나의 클래스만 extends 할 수 있는데 반해) 
```
public interface GroupedInterface extends Interface1, Interface2, Interface3 {

}
```
### 인터페이스 상속 예 
-	인터페이스를 이미 다른 클래스에서 구현하여 사용하고 있었을 때, 해당 인터페이스에 다른 메서드를 추가하고 싶을 때 
-	해당 인터페이스에 추상메서드를 또 추가할 경우 기존에 그 인터페이스를 구현하여 사용하던 클래스들을 모두 에러가 날 것이다. 추가된 메서드를 구현하지 않은 클래스가 되니까. 
-	그 때 사용할 수 있는 것이 상속 
```java 
public interface DoItPlus extends DoIt {

   boolean didItWork(int i, double x, String s);
   
}
```
-	이제 각각의 사용한 클래스에 대해서 원래의 DoIt 클래스를 구현해서 그대로 사용할지 DoItPlus를 구현해서 사용할지 선택해서 사용할 수 있다. 

## 학습 5) 인터페이스의 기본 메소드 (Default Method), 자바 8
-	위에서 (인터페이스 상속 예) 접했던 문제를 디폴트 메서드를 통해 해결할 수 있다. (static 메소드도 가능) 
	-	인터페이스가 있고, 이 인터페이스를 구현한 클래스들이 사용중인데, 인터페이스에 새로운 메소드를 추가하고 싶을 때 
```java 
public interface DoIt {

   void doSomething(int i, double x);
   int doSomethingElse(String s);
   default boolean didItWork(int i, double x, String s) {
       // Method body 
   }
   
}
```
-	인터페이스 DoIt을 구현한 클래스들에게는 어떤 코드 변화도 필요하지 않게 된다. 
### 디폴트 메소드 활용 
-	1.단순상속 2.override 3.abstract으로 재선언 
- 만약 특정 클래스에서 해당 메소드를 다르게 구현하고 싶다면 @Override 하면 된다. 
```java 
public interface Car {
    void run();
    void stop();
    default void refuel() {
        System.out.println("yummy");
    }
}
```
```java 
public class BungBung implements Car{

    @Override
    public void run() {
        System.out.println("brrrrr");
    }

    @Override
    public void stop() {
        System.out.println("Kkik!");
    }

    @Override
    public void refuel() {
        System.out.println("yummy yummy!");
    }
}
```
-	이 디폴트 메소드를 다시 abstarct으로 만들 수 있다. 
```java
public interface NewCar extends Car{
    @Override
    void refuel();
}
```
### 충돌문제 
-	디폴트 메서드를 쓰면 충돌문제가 날 수 있는데(ex 인터페이스의 default 메서드와 상속한 클래스의 메서드가 겹칠 때) 이 때의 우선순위는 week6 에 정리해 두었다. 
## 학습 6) 인터페이스의 static 메소드, 자바 8
-	static 메소드도 디폴트 메소드처럼 메소드 바디를 가질 수 있다. 
	-	각 객체보다는 클래스 관련된 메소드다. static이니까.
-	static 메소드이므로 인스턴스 생성 없이 바로 사용가능하다. 
```java
public interface Car {
    void run();
    void stop();
    default void refuel() {
        System.out.println("yummy");
    }
    static void introduce() {
        System.out.println("Car");
    }
}
```
```java
public class InterfacePractice {
    public static void main(String[] args) {
       Car.introduce(); //Car

    }
}
```

## 학습 7) 인터페이스의 private 메소드, 자바 9
-	인터페이스 내부에서만 사용될 수 있다. 
-	메소드 바디를 가져야 한다( abstract 불가) 
-	코드의 재사용성 
-	private static은 - non static, static 메소드에서 사용 가능, private non static은 non static 메소드에서 사용가능 
```java
public interface Temp {

    public abstract void mul(int a, int b);

    public default void add(int a, int b) {
        // private method inside default method
        sub(a, b);

        // static method inside other non-static method
        div(a, b);
        System.out.print("Answer by Default method = ");
        System.out.println(a + b);
    }

    public static void mod(int a, int b) {
        div(a, b); // static method inside other static method
        System.out.print("Answer by Static method = ");
        System.out.println(a % b);
    }

    private void sub(int a, int b) {
        System.out.print("Answer by Private method = ");
        System.out.println(a - b);
    }

    private static void div(int a, int b) {
        System.out.print("Answer by Private static method = ");
        System.out.println(a / b);
    }
}
```
--- 
## 출처 
-	https://docs.oracle.com/javase/tutorial/java/IandI/index.html
-	자바의 정석 ch7. 객체지향 프로그래밍2
-	학습 4) https://docs.oracle.com/javase/tutorial/java/IandI/nogrow.html
-	학습 6) 
	-	https://www.geeksforgeeks.org/private-methods-java-9-interfaces/
	-	https://howtodoinjava.com/java9/java9-private-interface-methods/
