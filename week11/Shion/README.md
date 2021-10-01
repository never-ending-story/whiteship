## Enum

```
우리는 일반적으로 상수값을 정의할 때 클래스 안에 final을 붙입니다.
```
```java
private final static int APPLE = 1;
private final static int PEACH = 2;
private final static int BANANA = 3;
```
```
현재는 과일 이름에 대한 상수만 정의되어 있었는데, 어플리케이션이 커지면서 필요에 의해서 기업에 대한 상수가 필요해졌습니다.
```
```java
private final static int GOOGLE = 1;
private final static int APPLE = 2;  // 컴파일 오류
private final static int ORACLE = 3;
```
```
이때 상수로 정의된 (과일)‘APPLE’에 값을 변경하려고 해서 컴파일 오류가 발생하게 됩니다.

이럴 때는 앞에 접두사를 붙여주는 것도 하나의 방법입니다.

과일 앞에는 FRUIT, 기업 앞에는 COMPANY를 붙여주면 이름이 충돌하는 문제를 방지할 수 있죠.
```
```java
public class Constant {
    private final static int FRUIT_APPLE = 1;
    private final static int FRUIT_PEACH = 2;
    private final static int FRUIT_BANANA = 3;

    private final static int COMPANY_GOOGLE = 1;
    private final static int COMPANY_APPLE = 2;
    private final static int COMPANY_ORACLE = 3;
}
```
```
그리고 각각의 상수들이 어떠한 취지에 속해 있는 상수인지를 코드상에서 쉽게 이해할 수 있는데도 도움이 됩니다. 

그런데 이렇게 하면 코드가 조금 지저분해지죠.

이럴때 interface를 쓰는 것도 하나의 방법입니다.
```
```java
interface FRUIT {
    int APPLE = 1, PEACH = 2, BANANA = 3;
}

interface COMPANY {
    int GOOGLE = 1, APPLE = 2, ORACLE = 3;
}
```
```
이렇게 interface를 썼을 때 좋은 점은 인터페이스를 통해서 상수를 사용하니까 그 복잡하고 길고 너저분했던 코드가 한결 간결해집니다.

왜냐하면 각각의 상수들은 ‘public static final’이라는 속성을 암시적으로 가지고 있기 때문에요.

또 하나의 장점은 우리가 아까는 FRUIT_APPLE 또는 COMPANY_GOOGLE 이런식으로 이 접두사를 붙여서 
각각의 변수들의 이름이 충돌할 가능성을 낮추는 작업을 했는데, 보시다시피 interface를 통해서 
그것이 과일인지, 기업인지에 따라서 각각의 상수들을 격리해놨기 때문에 이름이 충돌할 가능성이 없어진거죠.

그런데 누군가가 이런 코드를 시도해봤다고 생각해보죠.
```
```java
if (FRUIT.APPLE = COMPANY.APPLE) {
    System.out.println("과일 애플과 기업 애플은 같습니다.");
}
```
```
지금 과일 애플과 기업 애플이 같으냐라는 비교를 수행하고 있는데 이것은 말이 되지 않습니다.

과일 애플과 기업 애플은 비교조차 하면 안되는 대상이잖아요.

만약에 과일 애플과 기업 애플이 ‘1’이라고 하면 심지어 결과는 ‘같다’고 나옵니다.

우리는 이런 문제를 해결할 필요가 있는 것이고,

비교할 수 없는 상수의 그룹들, 과일이냐, 기업이냐하는 그룹들끼리는 서로 비교조차 할 수 없게 해야합니다.

왜냐하면 지금 이 상태로 우리가 컴파일하면 컴파일 에러가 발생하지 않기 때문에, 나중에 애플리케이션이 구동하는 과정에서 문제가 생길 것이고, 
그렇게 발생한 문제는 찾기가 어렵기 때문입니다.

문제를 찾아보면, 지금 이런 문제가 발생하는 것은,
좌항에 있는 값과 우항에 있는 값이 ‘int’ 정수라고 하는 같은 데이터 타입을 사용하기 때문에 그렇습니다.

만약에 좌항의 값과 우항의 값이 서로 다른 데이터 타입을 사용하고 있다면, 비교 자체가 불가능 해질 겁니다.

이게 무슨 말인지는 코드를 통해서 살펴보겠습니다.

위에 있는 interface를 class로 바꿔 보겠습니다.

APPLE, PEACH, BANANA가 이제는 interface 소속이 아니기 때문에 이제는 우리가 직접 final로 지정해줘야겠지요.
```
```java
class Fruit {
    public static final Fruit APPLE = new Fruit();
    public static final Fruit PEACH = new Fruit();
    public static final Fruit BANANA = new Fruit();
}

class Company {
    public static final Company GOOGLE = new Company();
    public static final Company APPLE = new Company();
    public static final Company ORACLE = new Company();
}
```
```
APPLE이라고 하는 상수에는 new Fruit()로 자기 자신의 인스턴스를 생성해서 상수의 값으로 지정합니다.

그 다음에 데이터타입으로는 Fruit로 지정해줍니다. 자기 자신의 데이터 타입이죠. 

나머지 상수들도 똑같이 각각의 인스턴스를 넣는데요, 보시는 것처럼 각각의 인스턴스라고 하면 
APPLE, PEACH, BANANA 이 세가지의 값이 모두 다른 데이터란 뜻입니다.

왜냐하면 각각 다른 인스턴스이기 때문입니다.

하지만 이 세가지 값은 Fruit라고 하는 같은 데이터 타입을 갖습니다.

*** 데이터타입은 같지만 각각이 가지고 있는 값은 서로 다른 인스턴스를 갖고 있다는 거죠.

Company도 마찬가지입니다.
```
```java
if (Fruit.APPLE == Company.APPLE) // 컴파일 에러
```
```
이제 이 코드를 보면 아까와는 달리 컴파일 에러가 발생합니다.

Fruit의 상수인 APPLE에 담겨 있는 값과 Company의 상수인 APPLE에 담겨있는 값이 
하나는 Fruit 타입이고, 하나는 Company 타입이기 때문에 이 두개의 값은 서로 비교가 불가능하다는 의미에서 밑줄이 쳐지고 컴파일 오류가 발생하는 것이죠.

즉 런타임에서 발생할 에러, 실행하는 과정에서 발생할, 의미가 혼동되어 발생할 수 있는 문제에서 미리 컴파일로 가져왔다는 것입니다.

그러면 자기 자신이 어떤 작업을 하는 과정에서 컴파일러가 문제를 검출해줄 수 있는 장점이 생긴 것입니다.
```
```java
Fruit type = Fruit.APPLE;
switch(type){ // 컴파일 에러
    case Fruit.APPLE:
        System.out.println("57 kcal");
        break;
    case Fruit.PEACH:
        System.out.println("34 kcal");
        break;
    case Fruit.BANANA:
        System.out.println("93 kcal");
        break;
}
```
```
그럼에도 불구하고 아직도 에러를 발생시킬 수 있는 이유는 switch문 때문입니다.

switch문 조건에 들어가는 데이터타입이 제한적입니다.

byte, short, char, int, * enum *, String, Character, Byte, Short, Integer

이런 데이터타입만을 switch문의 조건으로 사용할 수 있어요.

그런데 우리가 사용하려는 데이터 타입은 Fruit와 Company 입니다.

그렇기 때문에 이 switch 문에서 사용할 수 없다는 것이고, 그 얘기는 우리가 어렵게 만든 클래스의 데이터 타입을 통해서 만드는 상수가 switch 문에서 사용할 수 없다는 것이죠.

물론 if 문에서는 사용할 수 있지만, 이건 상당히 아쉬운 점이에요.

왜냐하면 상수를 사용할 때 switch문이 보기도 좋고 가독성이 좋기 때문에 switch문을 많이 사용하는데 이걸 사용할 수 없다는 것은 문제점이죠.

이 문제를 해결할 수 있는 방법 중 하나가 바로 enum입니다.

enum : 열거형( enumerated type , enumeration ) 

서로 연관된 상수들의 집합, 즉 바뀌지 않는 값들의 집합이라고 할 수 있습니다.

우리가 지금까지 살펴본 것에서는 Fruit와 Company라고 하는 그 그룹들이 일종의 열거인 것이고, 바로 그 열거라고 하는 특성을 문법적으로 지원한 형태가 바로 enum인 것입니다.
```
```java
class Fruit {
    public static final Fruit APPLE = new Fruit();
    public static final Fruit PEACH = new Fruit();
    public static final Fruit BANANA = new Fruit();
}
```
```
문법적으로 지원하는데 더 복잡해지면 안되겠죠.

그렇기 때문에 이렇게 복잡하게 작성하던 것을 간략하게 만든 것을 enum이라고 합니다.

enum에 Fruit라는 것은 class입니다.
```
```java
enum Fruit {
    APPLE, PEACH, BANANA
}
```
```
그런데 enum이라는 이름을 붙인 것은 작은 코드만으로 긴 코드의 효과를 낼 수 있도록 문법적인 지원을 했기 때문에 
저 클래스가 enum클래스라는 것을 분명히 하기 위해서 enum 이라는 키워드를 사용하는 것입니다.

그래서 enum Fruit를 컴파일 해보면 Fruit.class라는 파일이 만들어집니다.

APPLE, PEACH, BANANA는 public static final Fruit APPLE = new Fruit(); 이렇게 길게 써주는 것과 같은 의미다라고 생각하면 됩니다.

이제 위에서 작성된 switch문의 조건(type)에 더이상 에러가 발생하지 않습니다.

정리를 해보면 enum을 통해서 우리가 얻을 수 있는 효과는,

첫번째, 코드가 매우 단순해집니다.

그리고 인스턴스의 생성과 상속을 방지합니다.

그리고 키워드로 enum을 사용하기 때문에 enum을 사용하는 Fruit라고 하는 이것이 
열거를 위한 코드라는 것을 분명하게 표명할 수 있다는 장점이 생깁니다.

앞서서 enum은 클래스라고 말씀드렸었죠, 그렇기 때문에 생성자를 가질 수 있습니다.
```
```java
enum Fruit {
    APPLE, PEACH, BANANA

    Fruit() {
        System.out.println("Call Constructor");
    }
}
```
```
그래서 이렇게 생성자를 만들고 실행시켜보면 결과는 아래와 같습니다.

결과 : 
Call Constructor
Call Constructor
Call Constructor
```
```java
class Fruit {
    public static final Fruit APPLE = new Fruit();
    public static final Fruit PEACH = new Fruit();
    public static final Fruit BANANA = new Fruit();
}

enum Fruit {
    APPLE, PEACH, BANANA
}
```
```
우리가 이렇게 상수들을 열거한 것이 위와 동일하다는 것을 증명하고 있습니다.

왜냐하면 우리가 이렇게 상수를 하나하나 만들때 마다 new Fruit()라는 인스턴스화하는 과정이 필요하죠.

인스턴스화를 하게되면 그 인스턴스의 생성자가 호출이 되는데, 우리가 생성자를 정의했기 때문에 APPLE, PEACH, BANANA 이것들이 호출될 때마다 
이 상수들의 값들이 세팅되기 위해 Fruit가 인스턴스화 되면서, 그 인스턴스를 만들기 위한 생성자가 호출이 되는겁니다.

그래서 상수가 3개라고 하면 Call Constructor가 3번 호출되는 것이죠.

만약에 4개라고 한다면 4번 호출됩니다
(static 멤버이기 때문에 enum Fruit가 참조될 때 초기 한번만)
```
```java
Fruit() {
    System.out.println("Call Constructor " + this);
}
```
```
이 뒤에 this를 하게 되면 생성자를 대표할 수 있는 어떤 정보를 자바가 우리에게 주죠.

그 정보가 APPLE, PEACH, BANANA가 되는겁니다.

결과 : 
Call Constructor APPLE
Call Constructor PEACH
Call Constructor BANANA


그러면 생성자를 가졌다면 Fruit는 내부적으로 데이터, 즉 필드값을 가질 수 있습니다.

public String color; 를 정의해서 각각의 과일에 대한 색깔을 준다고 하면 이렇게 하면 됩니다.
```
```java
public String color;

Fruit (String color) {
    System.out.println("Call Constructor " + this);
    this.color = color;
}

```
```
그러면 이 생성자가 호출될 때마다 여기 있는 color 값이 세팅이 되서 
this.color의 값을 갖도록 하려면이 생성자가 호출될 때 (String) color 값을 부여해야 됩니다.
```
```java
APPLE("red"), PEACH("pink"), BANANA("yellow");
```
```java
생성자를 호출할 때 매개변수를 위한 인자를 전달해서 이 red라는 값이 color로 들어가게 됩니다.

이 APPLE 이라는 상수에 저장되는 Fruit의 인스턴스에 인스턴스 변수인 color의 값이 red가 되는 겁니다. 

그러면 이것을 어떻게 쓰는지 한번 보겠습니다.

각각의 과일들을 가져오는 이부분에다가 이렇게 써주는 것이죠.
```
```java
Fruit type = Fruit.APPLE;
switch(type){
    case Fruit.APPLE:
        System.out.println("57 kcal, color : " + Fruit.APPLE.color);
        break;
    case Fruit.PEACH:
        System.out.println("34 kcal, color : " + Fruit.PEACH.color);
        break;
    case Fruit.BANANA:
        System.out.println("93 kcal, color : " + Fruit.BANANA.color);
        break;
}
```
```
이렇게 해서 실행을 해보면 보시는 것처럼 color는 red라는 것이 출력되었구요, 
이것은 Fruit.APPLE.color 라는 부분이 red로 출력된 결과인 것이죠.

결과 : 
Call Constructor APPLE
Call Constructor PEACH
Call Constructor BANANA
57 kcal, color red


즉, 단순히 어떤 상수로서의 기능만 하는 것이 아니라 그 상수가 어떤 값을 갖게하는 등 더 많은 일을 할 수 있다는 것이 enum형의 장점이고, 
더 많은 일을 할 수 있지만 더 적은 코드를 사용한다는 것도 enum코드의 장점이라고 할 수 있습니다.

그럼 여기 변수가 생긴 것처럼 메소드도 생길 수 있습니다.
```
```java
private String color;

public String getColor() {
    return this.color;
}
```
```
color 변수의 접근제한자를 private으로 수정하고 getter 메서드를 생성해봤는데요, 

이렇게 한 다음에 여기있는 color는 getColor()로 바꿔주고 실행을 해보면 그 결과는 같습니다.
```
```java
Fruit type = Fruit.APPLE;
switch(type){
    case Fruit.APPLE:
        System.out.println("57 kcal, color : " + Fruit.APPLE.getColor());
        break;
    case Fruit.PEACH:
        System.out.println("34 kcal, color : " + Fruit.PEACH.getColor());
        break;
    case Fruit.BANANA:
        System.out.println("93 kcal, color : " + Fruit.BANANA.getColor());
        break;
}
```
```
지금 제가 설명드린 것은 enum안에 필드와 메서드가 다 들어갈 수 있는 온전한 형태의 클래스다 라는 것을 보여드리려고 한겁니다.

자 enum이 열거형이잖아요? 

우리말로 열거라고 하는 것은 어떠한 정보를 열거할 수 있다. 뭐 이런 뜻이겠죠.

그래서 enum과 enum이전에 살펴봤었던 방법(class Fruit부분)과 아주 큰 차이점 중 하나는, 
클래스로 우리가 상수를 정의하게 되면 클래스가 가지고 있는 각각의 멤버, APPLE, PEACH, BANANA라고 하는 
각각의 멤버를 우리가 마치 배열처럼 열거할 수 없다는 단점이 있습니다.

그 얘기는 뭐냐, class Fruit가 어떠한 상수를 가지고 있는지를 코드를 작성하는 사람이 알 수 있어야 한다는 것이죠.

열거형이 class방식과 다른 점 중 하나는 그 열거형 안에 어떠한 데이터가 있는지 몰라도 
마치 배열처럼 그 안에 들어있는 열거형이 가지고 있는 데이터를 하나씩 꺼내서 처리할 수 있는 기능을 제공한다는 겁니다.

그때 사용하는 메소드가 바로 values()라고 하는 메소드를 사용하게 되는데요.
```
```java
for (Fruit f : Fruit.values()) {
    System.out.println(f);
}
```
```
이렇게 for문을 만들어 봤습니다.

이렇게 하면 values()가 호출되면서 이 Fruit가 가지고 있는 데이터들 APPLE, PEACH, BANANA라고 하는 데이터들을 하나씩 하나씩 꺼내서 ‘f’에 담는거죠.

그러면 실행 결과는 아래와 같습니다.

결과 :
APPLE
PEACH
BANANA


열거형을 사용하게 되면, 단순히 어떤 배열로 비유하자면, 상수 데이터를 갖고 있는 배열을 만드는 것 뿐만 아니라 
마치 배열처럼 이렇게 상수들을 하나하나 꺼내서 처리할 수 있는 기능도 제공한다는 것이 열거형이 가지고 있는 중요한 특징이라고 할 수 있겠습니다.

열거형의 특성을 정리해볼까요.

첫번째, 연관된 값들 정확하게는 상수들을 저장을 하죠. 값들이 변경되지 않도록 보장합니다.

그리고 열거형은 그것 자체가 클래스이기 때문에 열거형 내부에 생성자, 필드, 메소드들이 있어서 
단순히 상수가 아니라 클래스가 할 수 있는 일은 열거형도 할 수가 있다는 장점이 있습니다. 

그리고 아까 보셨던 것처럼 열거형이 갖고 있는 상수들을 values()라고 하는 메서드를 통해서 상수들을 담은 배열을 가지고 올 수 있기 때문에, 
그 배열을 이용해서 그 저장되어 있는 상수들을 하나하나 꺼내서 처리하는 것도 할 수 있다는 것이죠.
```

### EnumSet
- enum을 가지고 Set자료구조를 만들 수 있다.
```java
public class Main {

    public static void main(String[] args) {
        System.out.println("allOf : " + EnumSet.allOf(Fruit.class));
        System.out.println("noneOf : " + EnumSet.noneOf(Fruit.class));
        System.out.println("of1 : " + EnumSet.of(Fruit.APPLE));
        System.out.println("of2 : " + EnumSet.of(Fruit.APPLE, Fruit.MELON));
        System.out.println("range : " + EnumSet.range(Fruit.APPLE, Fruit.BANANA));
        System.out.println("complementOf : " + EnumSet.complementOf(EnumSet.of(Fruit.APPLE, Fruit.PEACH)));
    }
}

enum Fruit {
    APPLE, PEACH, BANANA, MELON, STRAWBERRY;
}
```
결과 :
```
allOf : [APPLE, PEACH, BANANA, MELON, STRAWBERRY]
noneOf : []
of1 : [APPLE]
of2 : [APPLE, MELON]
range : [APPLE, PEACH, BANANA] 
complementOf : [BANANA, MELON, STRAWBERRY]
```
- allOf : enum의 모든 상수를 추가한다.
- noneOf : 아무것도 추가하지 않는다.
- of : 매개변수로 주어진 상수만 추가한다.
- range : 선택한 범위의 상수를 추가한다.
- complementOf : enum의 전체 상수 중 매개변수로 주어진 상수를 제외하고 추가한다.

### ordinal()
```java
/**
 * Returns the ordinal of this enumeration constant (its position
 * in its enum declaration, where the initial constant is assigned
 * an ordinal of zero).
 *
 * Most programmers will have no use for this method.  It is
 * designed for use by sophisticated enum-based data structures, such
 * as {@link java.util.EnumSet} and {@link java.util.EnumMap}.
 *
 * @return the ordinal of this enumeration constant
 */
public final int ordinal() {
    return ordinal;
}
```
- enum에 정의된 상수의 순서를 반환한다.
- `ordinal()`은 EnumSet이나 EnumMap 같은 내부 자료구조용이기 때문에 우리는 `ordinal()`이 반환하는 순서에 기반한 코드를 작성하지 않는 것이 좋다.

### Spring에서...
```java
@Entity
public class User {

    @Id
    private Long id;

    private SocialLogin oauthResource;

    // ...
}

public enum SocialLogin {
    GITHUB,
    GOOGLE,
    KAKAO,
    NAVER,
}
```
- `@Entity`안에 `enum`타입 있으면 DB에 기본적으로 ordinal `int` 타입이 들어간다.
- 그래서 `enum`타입에 정의된 상수 순서가 바뀌면 큰일난다.
```java 
@Enumerated(EnumType.STRING)
private SocialLogin oauthResource;
```
- `@Enumerated(EnumType.STRING)`를 써주면 상수명이 들어간다.
