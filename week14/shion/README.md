## Generics

### 제네릭스란?

- JDK 1.5에서 도입
- 컴파일 시 타입을 체크해주는 기능
- 객체의 타입 안정성을 높이고 형변환의 번거로움을 줄여줌

### 제네릭스 사용법

#### 클래스에 선언하는 제네릭스

```java
class Box {
    Object item;

    void setItem(Object item) { this.item = item; }
    Object getItem() { return item; }
}
```

- 이 `Box`클래스를 제네릭 클래스로 변경하면 다음과 같다.

```java
class Box<T> { // 클래스 옆에 <T>를 붙여준다.
    T item;

    void setItem(T item) { this.item = item; }
    T getItem() { return item; }
}
```

- `T`는 타입 변수 (type variable)의 첫글자며 반드시 `T` 일 필요는 없다.
- 기존에는 다양한 종류의 타입을 다루는 메서드의 매개변수나 리턴타입으로 `Object`타입의 참조변수를 많이 사용했고, 그로 인해 형변환이 불가피했지만 이젠 Object타입 대신 원하는 타입을 지정하기만 하면 된다.

```java
Box<String> b = new Box<String>();
b.setItem(new Object());   // 에러, String타입 이외의 타입은 지정불가
b.setItem("ABC");          // OK
String item = b.getItem(); // 형변환이 필요없음
```

```java
class Box {
    String item;

    void setItem(String item) { this.item = item; }
    String getItem() { return item; }
}
```

### 제네릭스 용어

```java
class Box<T> {}
```
- `Box<T>` : 제네릭 클래스, 'T의 Box' 또는 'T Box'
- `T` : 타입 변수 또는 타입 매개변수 (`T`는 타입문자)
- `Box` : 원시 타입


### 제네릭스의 제한

- 제네릭스는 인스턴스별로 다르게 동작하도록 하려고 만든 기능이기 때문에 아래와 같이 **인스턴스별로 다른 타입을 지정하는 것이 가능**하다.

```java
Box<Apple> appleBox = new Box<Apple>();
Box<Grape> appleBox = new Box<Grape>();
```

- 그러나 모든 객체에 대해 동일하게 동작해야하는 **static 멤버에 타입 변수 `T`를 사용할 수 없다.** (`T`는 인스턴스 변수로 간주)

- *모든 인스턴스에 공통으로 사용하는* `static`멤버에 *인스턴스별로 다르게 사용하는* 타입변수 사용 불가

```java
class Box<T> {
    static T item; // 에러
    static int compare (T t1, T t2) { // 에러
        // ... 
    }
}
```

- 배열 생성할 때 타입 변수 사용불가. 타입 변수로 배열 선언은 가능하다.

```java
class Box<T> {
    T[] itemArr; // OK

    T[] toArray() {
        T[] tmpArr = new T[itemArr.length]; // 제네릭 배열 생성불가
    }
}
```

- `new` 다음에 `T`가 오면 안된다. (객체 생성이나 배열을 생성할 때)
- `new` 연산자는 컴파일 시점에 뒤의 타입이 확정되어 있어야하는데 타입변수로 정의하면 `T`가 어떤 타입이 될지 전혀 알 수 없기 때문이다. 

### 제네릭 클래스의 객체 생성과 사용

- 참조변수와 생성자에 대입된 타입이 일치해야한다.

```java
Box<Apple> appleBox = new Box<Apple>(); // OK
Box<Apple> appleBox = new Box<Grape>(); // 에러
```

- 두 타입이 상속 관계라도 에러난다. 반드시 일치해야한다.

```java
Box<Apple> appleBox = new Box<Fruit>(); // 에러
```

- 제네릭 클래스의 타입이 상속관계에 있고, 대입된 타입이 일치하는 것은 괜찮다.

```java
List<Apple> appleList = new ArrayList<Apple>(); // OK
```

- JDK 1.7부터는 타입 추정이 가능해서 참조변수의 타입으로 Box가 Apple 타입의 객체만 저장한다는 것을 알 수 있기 때문에, 생성자에 반복해서 타입을 지정해주지 않아도 된다.

```java
Box<Apple> appleBox = new Box<Apple>();
Box<Apple> appleBox = new Box<>(); // OK, JDK 1.7부터 생략가능
```

### 제한된 제네릭스

- 타입 매개변수 `T`에 지정할 수 있는 타입 종류를 제한.
- 다음과 같이 제네릭 타입에 `extends`를 사용하면, 특정 타입의 자손들만 대입할 수 있게 제한할 수 있다.

```java
class FruitBox<T extends Fruit> {
    ArrayList<T> list = new ArrayList<>();
    // ...
}
```

- Fruit클래스의 자손들만 담을 수 있도록 제한한다.
- 클래스가 아닌 인터페이스를 구현해야 한다는 제약이 필요하다면, 이때도 `extends`를 사용한다. `implements` 사용 X
- 클래스와 인터페이스를 동시에 상속, 구현하고 있을 경우 `&`를 사용하여 연결한다.
```java
class FruitBox<T extends Fruit & Eatable> {}
```

### 와일드 카드

```java
class Juicer {
    static Juice MakeJuice(FruitBox<Fruit> box) {
        String temp = "";
        for (Fruit f : box.getList()) tmp += f + " "
        return new Juice(tmp);
    }
}
```

- `static` 메서드에는 타입 매개변수 `T`를 배개변수에 사용할 수 없으므로 특정타입을 지정해줘야 한다.

```java
System.out.println(Juicer.makeJuice(new FruitBox<Apple>())); // 에러, makeJuice의 매개변수는 반드시 FruitBox<Fruit> 이어야 한다.
```

- 이렇게 제네릭 타입을 `FruitBox<Fruit>`로 고정해 놓으면, `FruitBox<Apple>` 타입의 객체는 `makeJuice()`의 매개변수가 될 수 없으므로, 여러가지 타입의 매개변수를 갖는 `makeJuice()`를 만들 수 밖에 없다.

```java
static Juice MakeJuice(FruitBox<Fruit> box) {
    String temp = "";
    for (Fruit f : box.getList()) tmp += f + " "
    return new Juice(tmp);
}

static Juice MakeJuice(FruitBox<Apple> box) {
    String temp = "";
    for (Fruit f : box.getList()) tmp += f + " "
    return new Juice(tmp);
}
```

`그러나 위와 같이 오버로딩하면, 컴파일 에러가 발생한다.`

- 제네릭 타입이 다른 것만으로는 **오버로딩이 성립하지 않기 때문이다.**
- 제네릭 타입은 컴파일러가 컴파일할 때만 사용하고 제거해버리기 때문에, 위의 두 메서드는 오버로딩이 아니라 **메서드 중복 정의**이다.
- 이럴 때 사용하는 것이 **와일드 카드** `?`이다.
- `?` 만으로는 `Object`타입과 다를 바 없다.
```
<? extends T> : 와일드 카드의 상한 제한. T와 그 자손들만 가능
<? super T> : 와일드 카드의 하한 제한. T와 그 조상들만 가능
<?> : 제한 없음. 모든 타입이 가능. <? extends Object> 와 동일
```

- 제네릭 클래스와 달리 와일드 카드에는 `&`를 사용할 수 없다.

- 이제 와일드 카드를 사용해서 `makeJuice()`의 매개변수 타입을 `FruitBox<Fruit>` -> `FruitBox<? extends Fruit>` 으로 바꾸면, 매개변수로 `FruitBox<Fruit>` 뿐만 아니라, `FruitBox<Apple>`와 `FruitBox<Grape>`도 가능하게 된다.

### 제네릭 메서드

- 메서드의 선언부에 제네릭 타입이 선언된 메서드를 제네릭 메서드라고 한다.

```java
static <T> void sort(List<T> list, Comparator<? super T> c)
```

- 제네릭 클래스에 정의된 타입 매개변수와 제네릭 메서드에 정의된 타입 매개변수는 **전혀 별개의 것이다.**

```java
class FruitBox<T> { // 여기 T와
    // 여기 바로 아래 T는 다른 것이다.
    static <T> void sort(List<T> list, Comparator<? super T> c) {
        // ...
    }
}
```

- `static`멤버에는 타입 매개변수를 사용할 수 없지만, 메서드에 제네릭 타입을 선언하고 사용하는 것은 가능하다.

- 앞의 `makeJuice()`를 제네릭 메서드로 바꾸면 다음과 같다.
```java
static Juice makeJuice(FruitBox<? extends Fruit> box) {}
// | | |
// V V V
static <T extends Fruit> Juice makeJuice(FruitBox<T> box) {}
```

### 제네릭 타입의 제거

- 컴파일러는 제네릭 타입을 이용해서 소스파일을 체크하고, 필요한 곳에 형변환을 넣어준 후, 제네릭 타입을 제거한다.
- 즉, 컴파일된 파일(`*.class`)에는 제네릭 타입에 대한 정보가 없는 것이다.
- 이렇게 하는 주된 이유는 제네릭이 도입되기 이전의 소스 코드와의 호환성을 유지하기 위해서이다.

#### 기본 제거 과정

1. 제네릭 타입의 경계(bound)를 제거한다.
    - 제네릭 타입이 `<T extends Fruit>` 라면 `T`는 `Fruit`로 치환된다.
    - `<T>`인 경우는 `T`는 `Object`로 치환된다. 그리고 클래스 옆의 선언은 제거된다.

```java
class Box<T extends Fruit> {
    void add(T t) {
        // ...
    }
}

// | | |
// | | | 
// v v v

class Box {
    void add(Fruit t) {
        // ...
    }
}
```

2. 제네릭 타입을 제거한 후에 타입이 일치하지 않으면, 형변환을 추가한다.
    - `List`의 `get()`은 `Object`타입을 반환하므로 형변환이 필요하다.

```java
T get(int i) {
    return list.get(i);
}

// | | |
// | | |
// v v v

Fruit get(int i) {
    return (Fruit) list.get(i);
}
```
    - 와일드 카드가 포함되어 있는 경우, 다음과 같이 적절한 타입으로의 형변환이 추가된다.

```java
static Juice makeJuice(FruitBox<? extends Fruit> box) {
    String tmp = "";
    for (Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}

// | | |
// | | |
// v v v

static Juice makeJuice(FruitBox box) {
    String tmp = "";
    Iterator it = box.getList().iterator();
    while(it.hasNext()) {
        tmp += (Fruit) it.next() + " ";
    }
    return new Juice(tmp);
}
```
