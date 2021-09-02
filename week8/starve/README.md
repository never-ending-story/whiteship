## 인터페이스 정의하는 방법

#### 인터페이스란?

- 일종의 추상 클래스이며, 추상클래스처럼 추상메서드를 갖지만 추상클래스보다 추상화 정도가 높다. 

- 추상클래스와 달리 몸통을 갖춘 일반 메서드 또는 멤버변수를 구성원으로 가질 수 없다.

- 추상메서드와 상수만을 멤버로 가질 수 있으며, 그 외 의 다른 어떠한 요소도 허용하지 않음.

- 다른 클래스를 작성하는 데 도움을 줄 목적으로 작성 됨.

- 인터페이스 작성 방법:

  ```java
  interface 인터페이스이름 {
    public static final 타입 상수이름 = 값;
    public abstract 메서드이름(매개변수목록);
  }
  ```

  - 인터페이스 멤버 제약 사항:
    1. 모든 멤버변수는 `public static final` 이어야 하며, 이를 생략할 수 있다.
    2. 모든 메서드는 `public abstract`이어야 하며, 이를 생략할 수 있다. ( 단, static 메서드와 디폴트 메서드는 예외.)생략 된 제어자는 자동적으로 컴파일러가 추가해준다.



## 인터페이스 구현하는 방법

- 인터페이스 자체로는 인스터스 생성 불가능. 인터페이스도 자신에 정의된 추상메서드의 몸통을 만들어주는 클래스를 작성해야한다.

- 방법은 자신을 상속받는 클래스를 정의하는 것과 다르지 않음. 다만, 클래스와 같이 확장한다는 의미(extends)가 아닌, 구현한다는 의미의 'implements' 키워드를 사용

  ```java
  class 클래스이름 implements 인터페이스이름 {
    // 인터페이스에 정의된 추상메서드를 모두 구현해야한다.
  }
  
  class Fighter implements Fightable {
    @Override
    public void move(int x,int y) { /* 내용생략 */}
    
    @Override
    public void attack(String skill) {/* 내용생략 */}
  }
  ```

- 만일 구현하는 인터페이스의 메서드 중 일부만 구현한다면, abstract를 붙여 추상클래스로 선언해야함.

  ```java
  abstract class Fighter implements Fightable {
    public void move(int x,int y) { /* 내용생략 */}
  }
  ```

- 상속과 구현을 동시에 할 수도있다.

  ```java
  class Fighter extends Unit implements Fightable {
    public void move(int x,int y) { /* 내용생략 */}
    public void attack(String skill) {/* 내용생략 */}
  }
  ```

- 익명 구현 객체로도 사용할 수 있다.
  주로 일회성의 객체를 위해서 생성이 되는 경우이다.

  ```java
  class main {
    public static void main(String[] args) {
      Fightable fightable = new Fightable() {
        @Override
   			public void move(int x,int y) {/* 내용생략 */}
    
    		@Override
    		public void attack(String skill) {/* 내용생략 */}
      };
      
    }
  }
  ```

  

## 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

* 클래스의 다형성 처럼, 인터페이스도 다형성이 적용된다.

* 부모 인터페이스, 자식 인터페이스를 구현한 클래스는 부모나 자식으로 부터 레퍼런스를 받을 수 있다.

  * 상속 개념과 같이 지정된 상속 타입에서 정의된 상수나 메소드를 전부 사용 할 수 있다.
    * 부모의 경우: 부모 인터페이스에 정의된 상수, 메소드 사용 가능
    * 자식의 경우: 부모 인터페이스 및 자식 인터페이스에 정의된 상수, 메소드 사용가능

  ```java
  public class FighteGame {
    // 래퍼런스로 사용하는 방법
    Fightable fightable = new Fighter();
    fightable.attack("fight"); // @Overide처리 된 메서드만 사용 가능
    
    // 인스턴스로 사용하는 방법
    Fighter fighter = new Fighter();
  	fighter.attack("fight"); // @Overide처리 된 메서드 및 Fighter에 지정된 다른 메서드도 사용 가능.
  }
  ```

  * 캐스팅을 `((Fighter) fightable)`과 같이 진행한다면, Fighter에 있는 메서드에도 접근이 가능하다.

## 인터페이스 상속

- 인터페이스는 인터페이스로부터만 상속받을 수 있으며, 클래스와는 달리 다중상속, 즉 여러개의 인터페이스로부터 상속을 받는 것이 가능하다.

  ```java
  interface Movable {
    /* 지정된 위치(x, y)로 이동하는 기능의 메서드 */
    void move(int x,int y);
  }
  
  interface Attackable {
    /* skil로 공격하는 기능의 메서드 */
    void attack(String skill);
  }
  
  interface Fightable extends Movable,Attackable { }
    
  ```

  - 자손 인터페이스는 조상 인터페이스에 정의된 멤버를 모두 상속받는다.

## 인터페이스의 기본 메소드 (Default Method), 자바 8

* 기본적으로 인터페이스는 메소드의 선언만을 지원했는데, 자바 8부터는 default 접근제어자를 이용해 메소드를 정의 해 놓을 수 있는 Default Method를 지원한다.

* 하위 호환성을 지원하기 위해 이러한 메소드가 생성되었으며, 
  상위 인터페이스를 구현하고 있는 여러 클래스가 있는 경우, 도중에 인터페이스에 대한 기능 확장 혹은 보완할 때 하위 구현체 전부에 오버라이딩 에러가 나타나기 때문에 이를 해결하기 위함이다.

* Default Method의 경우 이미 구현이 되어있는 상태이기 때문에 오버라이딩을 하지 않아도 사용이 가능하며, 오버라이딩을 통해 재정의 또한 가능하다. 

  ```java
  public interface Fightable {
    void move(int x,int y);
    void attack(String skill);
    
    default void defense() {
      System.out.println("공격을 방어합니다");
    }
  }
  ```

  

## 인터페이스의 static 메소드, 자바 8

* Default Method와 마찬가지로 인터페이스 내에 기능을 정의하여 사용이 가능하며, 자바 8부터 사용이 가능하다.
  다만 차이점이 있다면, 인터페이스를 구현한 구현체 내에서 오버라이딩이 불가능하다.

* static method의 경우, 일반적으로 호출되는 클래스명.메소드명 방식이 아닌, 인터페이스명.메소드명 방식으로 호출해야한다. 
  다른 구현체가 있어도 인터페이스명으로 호출해야한다.

  ```java
  public interface Fightable {
    void move(int x,int y);
    void attack(String skill);
    
    default void defense() {
      System.out.println("공격을 방어합니다");
    }
    
    static void rest() {
      System.out.println("휴식합니다.");
    }
  }
  
  
  public class FighteGame {
    // 인스턴스로 사용하는 방법
    Fighter fighter = new Fighter();
  	fighter.defense(); //Ok
    
    Fightable.rest(); // Ok
    fighter.rest(); // 에러
  }
  ```

  

## 인터페이스의 private 메소드, 자바 9

* 외부 구현체에서 필요한 것이 아닌, 내부에서 작동되기를 원할 때 사용이 가능한 private method가 자바 9부터 사용 가능해졌다.

* 코드의 중복을 피하고, 내부 작동 메소드에 대한 캡슐화를 유지할 수 있게 되었다.

  ```java
  public interface Fightable {
    void move(int x,int y);
    void attack(String skill);
    
    default void defense() {
      System.out.println("공격을 방어합니다");
    }
    
    static void rest() {
      System.out.println("휴식합니다.");
    }
    
    default void status() {
      int level = basicLevel();
      int skillLevel = basicSkillLevel();
    }
    
    private int basicLevel() {
      return 10;
    }
    
    private int basicSkillLevel() {
      return 50;
    }
  }
  ```

  