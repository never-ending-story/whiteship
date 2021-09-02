8주차 인터페이스

- 인터페이스 정의하는 방법
- 인터페이스 구현하는 방법
- 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
- 인터페이스 상속
- 인터페이스의 기본 메소드 (Default Method), 자바 8
- 인터페이스의 static 메소드, 자바 8
- 인터페이스의 private 메소드, 자바 9


<br>



### 인터페이스

- 인터페이스란 추상클래스보다 추상화 정도가 높은 일종의 추상 클래스이다.

- 추상메서드와 상수만을 갖고 인스턴스 변수를 가질 수 없다. 
- 모든 멤버 변수는 public static final이고 생략 가능하다.
- 모든 메서드는 public abstract 이며 생략 가능하다.
  - Java8 부터 static 메서드와 디폴트 메서드도 선언 가능하다. 

<br>


**인터페이스의 장점**

- 객체간의 연결, 대화, 소통을 돕는 중간 역할을 한다.

- 독립적인 프로그래밍이 가능하다. 
  - 선언과 구현을 분리시킬 수 있다.
  - 변경에 유리한 설계가 가능하다. 유연한 코드를 작성할 수 있다.
  - 강한 의존관계를 가진 클래스들 보다 <u>인터페이스를 통해 관계를 가진다면</u> 한 쪽에서 변경이 일어나도 다른 한쪽에 영향을 미치지 않는다. 

- 기본 틀을 인터페이스로 작성한다면 일관되고 정형화된 코드 작성이 가능하다.

  <br>


### 인터페이스의 정의

```java
interface 인터페이스명 {
  public static final NAME = "yeon";
  public abstract hello();
}
```

`NAME`이라는 상수의 public static final 이라는 제어자와 키워드는 생략 가능하다.

`hello()` 메서드의 public abstract 역시 생략 가능하다. 

인터페이스의 모든 멤버들은 public static final, 혹은 public abtract 이어야 한다는  조건 때문에 생략 가능한 것이다. 생략된 제어자는 컴파일 시에 컴파일러가 자동으로 추가해준다. 


<br>

### 인터페이스의 구현 

- 인터페이스는 implements 키워드를 사용하여 구현체를 만든다.
- 인터페이스에 정의되어 있는 메서드를 꼭 구현해야한다. 

```java
class Cat implements Animal {
	
  @Override
  public String getName() {
     return "고양이";
  }
  
  @Override
  public int getLegs() {
     return 4;
  }
}

interface Animal {
  
  String getName();
  int getLegs();
  
}
```

<br>


### 인터페이스 레퍼런스를 통해 구현체를 사용하는 법

위의 예제에서 Cat 인스턴스 변수의 타입은 조상 인터페이스인 Animal로 지정할 수 있다. 

```java
Animal cat = new Cat();
Animal bird = new Bird();
```

이렇게 했을 때 장점은 무엇일까?

아래의 예제와 같이 다형성의 장점을 활용할 수 있다. 

```java
public void printAnimal(Animal animal) {
  System.out.println(animal.getName());
  System.out.println(animal.getLegs());
}

Cat cat = new Cat();
Bird bird = new Bird();

printAnimal(cat);
printAnimal(bird);

// 실행 결과
고양이
4
새
2
```

`printAnimal` 메서드의 매개변수를 Cat과 Bird의 조상 인터페이스 타입으로 지정해주면 Animal의 어떤 구현체든지 `printAnimal` 의 인자로 넣어줄 수 있다.


<br>



### 인터페이스 상속

- 인터페이스는 인터페이스만 상속받을 수 있다.
- 클래스와 달리 다중 상속이 가능하다.
- 인터페이스가 인터페이스를 상속받는 경우에는 `extends` 키워드를 사용한다.

<br>


### 인터페이스의 기본 메서드

- 구현을 포함한 메서드로 `default` 키워드를 붙인다. 
- 접근제어자가 항상 public이고 생략 가능하다. 
- 구현체에서 선택적으로 재정의하여 사용할 수 있다. 
- 조상 클래스에 새로운 메서드를 추가한 것과 동일한 기능을 한다. 


<br>

**자바8 이전 default method가 등장하기 전에는 어떻게 했을까?**

```java
public class HelloJoinMember extends JoinMemberAdapter {

    @Override
    public void preJoin() {
        System.out.println("반갑습니다.");
    }
}

class JoinMemberAdapter implements JoinMember {

    @Override
    public void preJoin() {
    }

    @Override
    public void afterJoin() {
    }
}

interface JoinMember {

    void preJoin();

    void afterJoin();
}
```

위와 같이` JoinMemberAdapter`라는 클래스를 두고 구현체가 이를 상속받아서 ` JoinMember` 인터페이스의 필요한 메서드만을 선택적으로 오버라이딩 하는 방법을 사용했었다. 

=> <span style=color:green>자바 8 부터 **default method**를 이용하면 `JoinMemberAdpter`와 같은 클래스 없이도 구현체에서 필요한 메서드만 선택적으로 오버라이딩이 가능해졌다. </span>

```java
public class HelloJoinMember implements JoinMember {

    @Override
    public void preJoin() {
        System.out.println("반갑습니다.");
    }
}

interface JoinMember {

    default void preJoin() {}

    default void afterJoin() {}
}
```

<br>


**하위 호환성**

기본 메서드는 구현체에서 필수적으로 구현할 필요가 없어서 하위 호환이 가능하다.

만약 JoinMember 인터페이스에서 추가적인 기능을 기본 메서드로 구현하게 되면, 구현체인 HelloJoinMember 클래스에는 아무렇 영향이 없다. 

인터페이스에 새로운 메서드를 추가해야하는데 이미 구현된 구현체들에 영향이 가지 않게 하려면 default 메서드를 구현하면 된다. 하위 호황성이 유지되고 인터페이스를 보완할 수 있다. 

<br>


**만약 두개의 인터페이스를 상속받는데, 두개의 인터페이스의 default method 명이 같다면?**

![스크린샷 2021-09-02 오후 9 41 58](https://user-images.githubusercontent.com/65011131/131862679-50a11546-5036-4d99-8a92-ef44c04ad9f5.png)


위와 같이 컴파일 에러가 난다. 이름이 같은 메서드를 override 하면 된다. 

```java
public class HelloJoinMember implements JoinMember, JoinGroup {

    @Override
    public void preJoin() {
        JoinMember.super.preJoin();
    }

    @Override
    public void afterJoin() {
        JoinGroup.super.afterJoin();
    }

    public static void main(String[] args) {
        HelloJoinMember hello = new HelloJoinMember();
        hello.preJoin();
        hello.afterJoin();
    }
}

interface JoinMember {

    default void preJoin() {
        System.out.println("pre join member");
    }

    default void afterJoin() {
        System.out.println("after join member");
    }
}

interface JoinGroup {

    default void preJoin() {
        System.out.println("pre join group");
    }

    default void afterJoin() {
        System.out.println("after join group");
    }
}


// 실행 결과
pre join member
after join group
```

위와 같이 재정의할 때 어떤 조상의 메서드를 사용할건지 명시할 수 있다. 

<br>




### 인터페이스의 static 메서드

- Java8 부터 인터페이스에 static 메서드를 추가할 수 있다.
- 인스턴스 생성과 상관없이 인터페이스 타입으로 호출한다. 
- 구현부분이 존재하고 구현체에서 override가 불가능하다. -> static이니깐 
- static 메서드 역시 접근제어자는 항상 public이고 생략 가능하다. 

```java
public class Main {
    public static void main(String[] args) {
      
        PrintableAnimal.getDescription();
    }
}

interface PrintableAnimal extends Animal {
    static void getDescription() {
        System.out.println("출력 기능이 있는 동물 인터페이스");
    }
}

// 실행 결과
출력 기능이 있는 동물 메서드 
```
<br>



### 인터페이스의 private 메서드

- java8에서는 default method와 static method가 추가되었고,
- java9에서는 private method와 private static method가 추가되었다.
- 기존 java8에서는 default 메서드와 static 메서드가 단지 특정 기능을 처리하는 내부 method인 경우에, 이들을 외부에 공개되는 public으로 만들어야한다는 단점이 있었다.
  - java9부터는 private metho와 private static method 를 추가함으로서 코드의 중복을 피하고 인터페이스에 대한 <u>캡슐화</u>를 유지할 수 있게 되었다. 

```java
public class Main {
    public static void main(String[] args) {
        MyCar car = new MyCar();
        car.carMethod();
        car.defaultCarMethod();
    }
}

class MyCar implements Car {

    @Override
    public void carMethod() {
        System.out.println("car method by MyCar");
    }
}

interface Car {
    void carMethod();

    default void defaultCarMethod() {
        System.out.println("Default Car Method");

        privateCarMethod();
    }

    private void privateCarMethod() {
        System.out.println("private car method");
    }
}

// 결과
car method by MyCar
Default Car Method
private car method
```




<br>
<br>



참고: 자바의 정석

https://dev-coco.tistory.com/13

https://blog.baesangwoo.dev/posts/java-livestudy-8week/

백기선님 자바 스터디 유튜브 영상 



















