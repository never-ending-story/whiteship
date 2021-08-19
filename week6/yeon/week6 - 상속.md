Week6 - 상속

- 자바 상속의 특징
- super 키워드
- 메소드 오버라이딩
- 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
- 추상 클래스
- final 키워드
- Object 클래스


</br>



### 상속이란

- 기존의 클래스를 재사용하여 새로운 클래스를 작성하는 것

- 코드의 재사용성을 높이고 중복을 제거한다.

- 생성자와 초기화 블럭은 상속되지 않고 멤버만 상속된다. 

- 상속을 통해 자손 클래스는 부모클래스의 필드와 메서드를 재사용할 수 있다. 

</br>


**자바 상속의 특징**

- 다단계 상속이 가능하고, 단일 상속만 가능하다. 
- 모든 클래스는 Object 클래스의 자손 클래스이다. 

</br>


**다중 상속의 단점**

- 여러 클래스로부터 상속받을 수 있어서 클래스간의 관계가 복잡해질 수 있다.

- 서로 다른 클래스로부터 상속받은 멤버들의 이름이 같은 경우에는 이를 구별할 수 없다.

-> 이러한 이유들 때문에 자바는 단일 상속만을 허용한다. 

</br>


### super와 super()
</br>

**super**

- 객체 자신을 가르키는 참조변수
- 조상의 멤버와 자신의 멤버를 구별할 때 사용한다. 
- 조상클래스로부터 상속받은 멤버도 자손 클래스 자신의 멤버이므로 super 대신 this를 사용할 수 있다.
  - 조상클래스의 멤버와 자손 클래스의 멤버가 중복 정의되어 <u>서로 구별하는 경우에</u> super를 사용한다. 
- static 메서드에서 사용이 불가하고 인스턴스메서드에서만 사용할 수 있다. 



```java
public class Child extends Parent {
    int x = 20;

    int getX() {
        return x;
    }

    int getSuperX() {
        return super.x;
    }

    public static void main(String[] args) {
        Child c = new Child();
        System.out.println("c.x = " + c.getX());
        System.out.println("super.x = " + c.getSuperX());
    }

}

class Parent {
    int x = 10;
}

// 결과
c.x = 20
super.x = 10
```

위와 같이 변수 이름이 중복되어도 상속이 가능하고, 자손 클래스에 같은 이름의 변수를 정의하는 것도 가능하다 .

super를 사용해서 구별하면 된다. 


</br>

**super()**

- this()와 마찬가지로 super()는 생성자이다. 
- 상속시에 생성자와 초기화블럭은 상속되지 않는데 super()를 이용하면 조상 클래스의 생성자를 호출할 수 있다. 
- 자손클래스의 생성자에서는 조상클래스의 생성자가 호출되어야한다.
  - 자손클래스의 멤버가 조상클래스의 멤버를 사용할 수 있기 때문에 조상클래스의 멤버가 먼저 초기화되어야한다. 
- Object를 제외한 모든 클래스의 생성자 첫줄에 자신의 다른 생성자나 조상의 생성자를 호출해야만한다. 
  - 호출하지 않는다면 컴파일러가 자동으로 생성자의 첫줄에 super()를 추가한다. 



```java
public class Point {

    int x = 10;
    int y = 20;

    public Point(int x, int y) {
        // 컴파일러가 super()를 이곳에 추가한다. Object클래스의 기본 생성자가 호출됨
        this.x = x;
        this.y = y;
    }
}

class Point3D extends Point {

    int z = 30;

    public Point3D() {
        this(100, 200, 300);    // public Point3D(int x, int y, int z)를 호출한다. 
    }

    public Point3D(int x, int y, int z) {
        super(x, y);    // Point(int x, int y)를 호출한다. 
        this.z = z;
    }
}
```


</br>

### 메소드 오버라이딩

- 조상으로부터 상속받은 메서드의 내용을 변경하는 것

- 구현부만 변경 가능하고 선언부는 변경할 수 없다. 
  - 자손클래스에서 오버라이딩하는 메서드는 조상클래스의 메서드와 아래의 부분들이 같아야만한다.
    - 이름
    - 매개변수
    - 반환타입
- 접근제어자를 조상클래스의 메서드보다 좁은 범위로 변경할 수 없다.
- 조상클래스의 메서드보다 많은 수의 예외를 선언할 수 없다. 
- 인스턴스 메서드를 static으로 변경할 수 없고 그 반대도 마찬가지이다. 


</br>

### 다이나믹 메소드 디스패치

Method Dispatch란 어떤 메서드를 호출할지 결정하여 실제로 실행시키는 과정이다.

</br>


**Static Dispatch**

- 어떤 메서드가 실행될지 <u>컴파일 타임</u>에 결정된다. 
- private, static, final 메서드들이 static으로 결정된다.

- 컴파일 시 생성된 바이트코드에 이 정보가 그대로 남아 있다.


</br>

**Dynamic Dispatch**

- 상위 개념인 interface 나 추상클래스에 정의된 <u>추상메서드를 호출하는 경우</u>이다.
- 객체 지향 프로그래밍의 다형성을 지원하기 위한 매커니즘 
- 어떤 메서드가 사용될지 <u>런타임 시점</u>에 결정된다. 

```java
class Parent {
    void run() {
        System.out.println("A parent is running!");
    }
}

class Child1 extends Parent {
    @Override
    void run() {
        System.out.println("Child1 is running!");
    }
}

class Child2 extends Parent {
    @Override
    void run() {
        System.out.println("Child2 is running!");
    }
}

class Main {
    public static void main(String[] args) {
        Parent child1 = new Child1();
        Parent child2 = new Child2();

        child1.run();
        child2.run();
    }
}

```

위의 코드를 보면, child1와 child2은 런타임 시점에 어떤 메서드를 실행해야하는지가 결정된다. 


</br>


### 추상클래스

- 일반 클래스와 같이 멤버변수와 생성자를 갖고 있지만, <u>추상메서드를 갖고 있는 클래스</u>이다. 

- 추상클래스로는 인스턴스를 생성할 수 없다. 
- 주로 여러 클래스에서 공통적으로 사용되는 부분을 뽑아서 추상클래스로 만든다. 구체화된 코드보다 유연하고 변경에 유리하다. 

</br>


### final 키워드

- final은 변경될 수 없다는 의미를 갖고 있다.

- final 클래스
  - 확장될 수 없는 클래스로 final 클래스는 다른 클래스의 조상이 될 수 없다.
- final method
  - 오버라이딩이 불가능하다.
- final 변수
  - 값을 변경할 수 없는 상수가 된다.


</br>

### Object 클래스

- 모든 클래스의 최고 조상
- clone(), equals(), hashCode(), toString() 등 과 같은 메서드를 갖고 있고 모든 클래스에서 사용가능하다.

</br>

참고: 자바의 정석

https://hoooooooooooooop.tistory.com/entry/javahalle6

https://defacto-standard.tistory.com/413



