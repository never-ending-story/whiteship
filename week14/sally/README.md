### 제네릭 사용법

- 타입의 객체를 다루는 메서드나, 컬렉션 클래스에 컴파일시 타입체크를 해준다. 컴파일시 타입체크 하므로 타입 안정성을 높이고 형변환의 번거로움이 줄어든다 -> `1List<Integer>` 이면 컴파일할때 담긴 객체들이 다 인티저 객체인지 확인

- 지네릭 없이 리스트만 선언한다면 안에 담긴 객체가 어떤 객체인지 모름. -> 꺼내올때 형변환 해줘야함

  ```
  ArrayList list = new ArrayList();
  list.add(new Tv());
  Tv t = (Tv) list.get(0); <- type cast is inevitable without generics
  ```

- 클래스를 정의할 때 지네릭을 쓰는 예시

  ```
  public class Box<T> {
  	private T t;
  	
  	public void set(T t) { this.t = t; }
  	public T get() { return t; }
  }
  ```

  여기서 T는 type을 의미한다

- 네이밍 컨벤션

  | Letter      | stands for         |
  | ----------- | ------------------ |
  | E           | element            |
  | K           | Key                |
  | N           | Number             |
  | T           | Type               |
  | V           | Value              |
  | S, U, V etc | 2nd, 3rd, 4th type |

- Raw type

  `Box<T>` 에서 타입 지정 없이 `Box`만 만들 경우 Box는 `Box<T>`의 원시 타입이라고 함

- 제약
  - Static 멤버는 인스턴스화 되는 것이 아니므로 T 사용 불가
  - 지네릭타입의 배열 생성 불가 -> `new` 키워드는 컴파일 시점에 T가 무슨 타입인지 정확히 알아야 하는데 지네릭에서는 컴파일때 알 수 없으므로 사용 불가
  - instanceOf도 new와 마찬가지

### 바운디드 타입

`FruitBox<T>`로 클래스를 정의한다면 T에 들어오는 타입에는 제약이 없다. 이 때 `FruitBox<T extends <class>>`로 정의하면 `<class>`를 상속받은 타입만 T로 지정할 수 있게 된다. 인터페이스는 implements 키워드를 사용한다.

바운드는 &을 이용해서 다중으로 제한을 둘 수 있다. 클래스가 먼저, 인터페이스가 나중에 선언되어야 한다.

```
public class FruitBox<T> {
	private T fruit;
	
	public FruitBox(T fruit) {
		this.fruit = fruit;
	}
}

public class FruitBox2<T extends Fruit> {
	private T fruit;
	
	public FruitBox2(T fruit) {
		this.fruit = fruit;
	}
}

public static void main(String[] args) {
	FruitBox<Toy> toy = new FruitBox(new Toy()); // OK
	FruitBox2<Toy> toy = new FruitBox2(new Toy()); // compile error
}
```

위 예시에서 Toy 클래스가 Fruit을 상속받지 않는다고 가정할 때 바운드를 주지 않은 FruitBox는 Toy 타입으로 생성이 가능하지만 `T extends Fruit`이 붙은 FruitBox2는 생성이 불가능하다.

### 와일드카드

- 제네릭에 다형성을 적용한 방법, 알 수 없는 유형을 참조하는데 사용된다. 물음표 `?`로 표시된다.

  ```
  public static void objectTest(List<Object> list) { //.. }
  
  public static void wildCardTest(List<?> list) { //.. }
  
  public static void main(String[] args) {
  	objectTest(new List<Integer>()); // complie error
  	wildCardTest(new List<Integer>()); // OK
  }
  ```

  위와 같이 `List<Object>`가 파라미터인 경우에는 `List<Object>`만 올 수 있지만 와일드카드를 쓴다면 해당 타입에 어떤 타입이 와도 상관없다.

- 와일드카드는 `<? extends T>`, `<? super T>`, `<?>` 이렇게 세가지 방법으로 쓸 수 있다. extends 키워드가 붙으면 상한제한(T와 그 자손들), super 키워드가 붙으면 하한제한(T와 그 조상들)을 둘 수 있다. 

### 지네릭 메서드

- 지네릭 타입이 선언된 메서드를 지네릭 메서드라고 한다. 지네릭 타입은 리턴타입의 앞에 위치하고 선언된 지네릭 타입은 메서드 매개변수나 리턴타입에 오는 해당 메서드에만 적용되는 지네릭 타입을 정의해준다. 

  ```
  public class Box<T> {
  	private T thing;
  	
  	public static <A> Box<A> from(A thing) {
  		return new Box<>(thing);
  	}
  }
  ```

  원래 `<A>` 가 아니라 `<T>` 로 표기되어야 하지만 Box 클래스의 제네릭 타입과 쓰임이 다르므로 이해를 돕기 위해 `<A>`로 표기했다.

  from 메서드에 표기된 타입 `<A>`는 파라미터의 타입 A와 리턴타입 내의 <A>를 정의하고 있다.

### Erasure

타입 소거는 컴파일 타임에 타입체크를 하고 맞지 않는 것을 잡아내 컴파일 에러를 발생시키거나 제네릭 경계를 제거하고 필요한 곳에 형변환을 시켜주는 역할을 한다.

```
public class Box<T> {
	private T t;
	
	public T get() {
		return t;
	}
}

public static void main(String[] args) {
	Box<Fruit> fruitBox = new Box<>(new Fruit());
	Fruit fruit = fruitBox.get();
}

// 런타임
public class Box {
	private Object t;
	
	public Object get() {
		return t;
	}
}

public static void main(String[] args) {
	Box<Object> fruitBox = new Box(new Fruit());
	Fruit fruit = (Fruit) fruitBox.get();
}
```

먼저 모든 T, ?는 `Object`로 변환되고 바운디드 타입의 경우 바운드되는 클래스로 변환된다. 그리고 해당 객체를 꺼내올 때 타입을 맞춰주는 형변환이 일어난다.