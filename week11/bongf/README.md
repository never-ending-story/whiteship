## 학습 1) enum 정의하는 방법
### enum 
-	미리 정의된 상수 집합, 특별한 데이터 타입
-	해당 변수는 미리 정의한 집합 중 하나의 값이어야 한다. 
	-	ex. (NORT, SOUTH, EAST, WEST) 로 정의했다면 이들 중 하나여야만 한다. (요일도 대표적인 예) 
-	상수기 때문에 대문자로 표기한다. 

### 정의하기 
-	`enum` 키워드 사용 
-	끝에는 `;` 붙이기 
```java
public enum Day {
	SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
}
```
-	고정된 상수 집합을 표현할 때는 enum 사용 
	-	ex. 태양계 행성들, 메뉴의 선택지 등 

### 사용하기 
```java
enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
}

public class EnumTest {
    Day day;

    public EnumTest(Day day) {
        this.day = day;
    }

    public void tellItLikeItIs() {
        switch (day) {
            case MONDAY:
                System.out.println("월요일이네..");
                break;
            case WEDNESDAY:;
                System.out.println("수요일에 쉬면 좋아");
                break;
            case SATURDAY:
                System.out.println("토요일이닷!");
                break;
            case SUNDAY:
                System.out.println("내일이 월요일이네..");
                break;
            default:
                System.out.println("평일이다");
                break;
        }
    }

    public static void main(String[] args) {
        EnumTest firstDay = new EnumTest(Day.MONDAY);
        firstDay.tellItLikeItIs();
        EnumTest thirdDay = new EnumTest(Day.WEDNESDAY);
        thirdDay.tellItLikeItIs();
        EnumTest fifthDay = new EnumTest(Day.FRIDAY);
        fifthDay.tellItLikeItIs();
        EnumTest sixthDay = new EnumTest(Day.SATURDAY);
        sixthDay.tellItLikeItIs();
        EnumTest seventhDay = new EnumTest(Day.SUNDAY);
        seventhDay.tellItLikeItIs();
    }
}
```
```
결과 

월요일이네..
수요일에 쉬면 좋아
평일이다
토요일이닷!
내일이 월요일이네..
```
-	`enum`의 선언은 `enum type`이라고 불리는 클래스를 정의한다. 
-	enum 클래스의 바디는 메소드나 다른 필드를 포함할 수 있다. 
	-	enum의 선언이 반드시 먼저 와야한다. (아래 컴파일 에러)
    ![](https://images.velog.io/images/bongf/post/8851ae32-2aa0-495c-ba1d-21cc46e43cb3/image.png)

- 모든 enum 들은 java.lang.Enum을 상속하기 때문에 enum은 다른 클래스를 상속하지 못한다. 
- enum의 생성자의 접근제어자는 package-private 또는 private 여야만 한다. enum body에 정의될 때 자동적으로 해당 상수가 만들어지기 때문에 따로 생성자를 호출할 필요가 없다. 
- enum은 '==' 로 비교 가능하다. (compareTo 사용가능) 
```java

enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
}


public class EnumTest {
    Day day;

    public EnumTest(Day day) {
        this.day = day;
    }

    public static void main(String[] args) {
        EnumTest fifthDay = new EnumTest(Day.FRIDAY);
        EnumTest seventhDay = new EnumTest(Day.SUNDAY);

        System.out.println(fifthDay.day == seventhDay.day);
        System.out.println(fifthDay.day.compareTo(seventhDay.day));
    }
}
```
## 학습 2) enum이 제공하는 메소드 (values()와 valueOf())
-	enum 생성시에 컴파일러는 몇 가지의 메소드를 자동으로 추가한다.

### `values()` 메소드 
-	선언된 순서로 enum 값들을 모두 담은 배열을 return하는 static 메소드
-	for-each 문에서 enum 값들을 iterate하는데 사용된다. 
    
```java
enum Day {
    SUNDAY("일"), MONDAY("월"), TUESDAY("화"), WEDNESDAY("수"),
    THURSDAY("목"), FRIDAY("금"), SATURDAY("토");

    private final String Korean;

    Day(String korean) {
        Korean = korean;
    }

    public String korean() {
        return Korean;
    }
}

public class EnumTest {

    public static void main(String[] args) {
        
        for( Day d : Day.values()) {
            System.out.println(d.korean());
        }
    }
}
```
### `valueOf(String name)` 
-	출처 : 자바의 정석 
-	이름(String)으로 enum 참조를 얻을 수 있게 해준다. 

```java
enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
}

public class EnumTest {
    Day day;

    public EnumTest(Day day) {
        this.day = day;
    }

    public static void main(String[] args) {
        Day day = Day.valueOf("MONDAY");
        System.out.println(day); //MONDAY
        System.out.println(Day.valueOf("MONDAY")==Day.MONDAY); // true
    }
}    
```
## 학습 3) java.lang.Enum
-	모든 열거형의 조상
-	모든 enum 들은 java.lang.Enum을 상속하기 때문에 enum은 다른 클래스를 상속하지 못한다. 
- java.lang.Enum이 제공하는 메서드 
- 아래 출처 : 자바의 정석 


|메서드|설명|
|--|--|
| `Class<E> getDeclaringClass()` | 열거형의 클래스 객체를 반환한다 |
|String name() | 열거형 상수의 이름을 문자열로 반환한다.|
|int ordinal() | 열거형 상수가 정의된 순서를 반환한다.(0부터 시작)|
|`T valueOf(Class<T> enumType, String name)`| 지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다|

```java
  
enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
}
  
public class EnumTest {
    Day day;

    public EnumTest(Day day) {
        this.day = day;
    }

  

    public static void main(String[] args) {
        Day day = Day.valueOf("MONDAY");
        System.out.println(day); //MONDAY

        System.out.println(day.getDeclaringClass()); // class bongf.week11.study.Day
        String name = day.name();
        System.out.println(name);  // MONDAY
        System.out.println(day.ordinal()); // 1
        Day day3 = Day.WEDNESDAY;
        System.out.println(day3.ordinal()); // 3
    }
}
```
-	ordinal() 사용하지 말자
	-	순서가 바뀌면 ordinal 값은 변경된다.
    -	[effective java에도 사용하지 말자고 나와있다고 한다.](https://www.notion.so/11-Enum-ccbba2bf2b7746ed8a12d2dee09aa833) 
    -	https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html

![](https://images.velog.io/images/bongf/post/a1e98a16-f9e2-4945-ab16-b06e5c28565b/image.png)


## 학습 4 )EnumSet
- [출처 : Baeldung](https://www.baeldung.com/java-enumset)
-	[출처:oracle](https://docs.oracle.com/javase/10/docs/api/java/util/EnumSet.html)
### EnumSet
- enum 값들만 포함할 수 있다. 그리고 모든 value 들은 같은 enum 안에 포함되어야 한다. 
- null을 추가할 수 었다. `NullPointerException` 발생한다.
-	thread-safe 하지 않다. 필요하다면 외부에서 동기화 해줘야 한다. 
	-	[`Collections.synchronizedSet(java.util.Set<T>) method`](https://docs.oracle.com/javase/10/docs/api/java/util/Collections.html#synchronizedSet(java.util.Set)) 로 가능 
    -	` Set<MyEnum> s = Collections.synchronizedSet(EnumSet.noneOf(MyEnum.class));`
- enum에 저장된 순서로 저장된다. 
-	fail-safe iterator를 사용한다. 
	-	collection이 수정되어도 ConcurrentModificationException이 발생하지 않는다. 
    -	fail-safe란 말은 [Fail Fast and Fail Safe Iterators in Java](https://www.geeksforgeeks.org/fail-fast-fail-safe-iterators-java/) 를 보면 편의상 쓴 말 같다. 
    -	어쨌든 iterator로 돌면서 중간에 set의 구조가 변경되어도(데이터 추가 삭제 등) ConcurrentModificationException 을 발생시키지 않는다는 말로 이해했다. 

### EnumSet 사용 
-	'내부적으로 bit vectors 표현되어있어 압축적이고 효율적이다. 이 클래스의 공간, 시간 성능은 매우 우수하다.' 라고 oracle에 적혀 있다. 
-	성능이 매우 우수하기 때문에 사용하는 것으로 이해했다. 

### EnumSet 생성 
-	해당 enum클래스의 모든 요소를 포함하여 EnumSet 만들기  (아래 Day.class 는 enum 클래스) 
```java 
private EnumSet<Day> workedDays = EnumSet.allOf(Day.class);
```
-	비어있는 EnumSet 만들기 
```java 
EnumSet.noneOf(Color.class);
```
-	enum의 부분 집합 (subset)으로 만들기 `of` 메소드 활용. 요소의 갯수에 따라 5개의 오버로딩 메소드가 있다. 그리고 가변인자(갯수에 상관없이 사용할 수 있는 메소드가 있다) 이 가변인자를 사용하는 메소드는 느리므로 요소 갯수가 1~5개라면 오버로딩된 메소드를 사용하자 

![](https://images.velog.io/images/bongf/post/456d46b0-afc4-4a6c-8fb1-bed77d5e86dd/image.png)
![](https://images.velog.io/images/bongf/post/8f27bf06-2d71-429d-8ab8-6227447aafe9/image.png)
`public static <E extends Enum<E>> EnumSet<E> of​(E e1,
                                                E e2,
                                                E e3,
                                                E e4,
                                                E e5)`

`@SafeVarargs
public static <E extends Enum<E>> EnumSet<E> of​(E first,
                                                E... rest)`
-	`range()` enum에 정의된 순서대로 범위를 지정하여 생성하는 방법 
`public static <E extends Enum<E>> EnumSet<E> range​(E from,
                                                   E to)`



### 코드 
```java

enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
}


public class WorkingDay {
    private EnumSet<Day> workedDays;

    public WorkingDay() {
        this.workedDays = EnumSet.noneOf(Day.class);
    }

    public EnumSet<Day> getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(EnumSet<Day> days) {
        this.workedDays = days;
    }

    public boolean didWorkOn(Day day) {
        return workedDays.contains(day);
    }

    public void add(Day day) {
        workedDays.add(day);
    }

    public void remove(Day day) {
        workedDays.remove(day);
    }
}

```
-	테스트코드 
```java
class WorkingDayTest {
    private static WorkingDay workingDay;

    @BeforeEach
    void set() {
        workingDay = new WorkingDay();
    }

    @Test
    @DisplayName("처음 생성시에 EnumSet.noneOf()로 비어있는 set을 만들어야 한다")
    void startWithEmptySet() {
        assertThat(workingDay.getWorkedDays().size()).isEqualTo(0);
        System.out.println(workingDay.getWorkedDays());// []
    }

    @Test
    @DisplayName("Set에 있는 add, remove, contains가 EnumSet에서도 동작해야 한다.")
    void workWithSetMethod() {
        workingDay.add(Day.FRIDAY);
        workingDay.add(Day.WEDNESDAY);
        workingDay.add(Day.MONDAY);
        assertThat(workingDay.didWorkOn(Day.SUNDAY)).isFalse();

        workingDay.remove(Day.FRIDAY);
        assertThat(workingDay.getWorkedDays().size()).isEqualTo(2);
        workingDay.getWorkedDays().forEach(System.out::println); // MONDAY, WEDNESDAY (enum에 정의된 순서대로 출력)
    }
}

```
-	구체적인 사용법이 궁금했는데 [블로그](https://d2fault.github.io/2021/07/06/20210706-java-bitmask-and-enumset/) 를 참고했다. 

## [우형 기술블로그의 Enum 활용기](https://techblog.woowahan.com/2527/) 
-	다른 언어와 달리 Enum은 클래스이기 때문에 장점을 많이 갖는다. 
	-	1. 데이터의 연관관계 표현 `MON("월", 1)`
	-	2. 상태와 행위를 한 곳에서 관리 
-	JPA 사용시 `@Enumerated(EnumType.String)` 붙이면 테이블에 저장시 Enum의 name이 저장된다. 
-	활용방안 
	-	유형 블로그에서는 enum을 활용한 예시를 차후 학습을 목적으로 아래 간단하게 작성해두었다. 
	-	결제종류인 `CASH`, `CARD`, `ETC` 가 있고 
    -	각각 종류에 대해 구체적인 결제수단 `CASH(토스, 현장결제, 계좌이체)`가 있다고 했을 때 
    ```java
    enum PayGrup {
    	CASH(Arrays.asList("토스", "현장결제", "계좌이체) ..
    }
    ```
    -	이렇게 묶어준 후에 "토스" 라는 방식의 결제 수단이 들어왔을 때 enum을 스트림으로 순회하면서 해당 enum의 요소가 들어온 결제수단을 찾는 방식을 활용할 수 있다. 
    -	이러한 토스, 현장결제, 계좌이체 등등 또한 enum으로 만들 수 있다. 
    
## 출처 
-	https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
-	https://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#jls-8.9
