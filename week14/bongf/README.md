## 학습1) 제네릭 사용법
### 제네릭이란? 
-	생활코딩 정의 
	-	클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법
    -	오라클의 설명과 비슷한 것 같다. 
    	-	제네릭은 클래스,인터페이스,메서드를 정의할 때 타입을 파라미터로 받게 해준다. 
    	> In a nutshell, generics enable types (classes and interfaces) to be parameters when defining classes, interfaces and methods.
-	간단하게 보자면 아래와 같다. 
	-	`Box<T>`는 지네릭 클래스이고, T의 Box, 또는 T Box라고 읽는다. 
    -	T는 `타입 변수` 또는 `타입 매개변수`라고 한다. 
    -	여기서 Box를 `원시타입`이라고 한다. 
```java
/**
 * 출처 : 독스 튜토리얼 : https://docs.oracle.com/javase/tutorial/java/generics/types.html
 * Generic version of the Box class.
 * @param <T> the type of the value being boxed
 */
public class Box<T> {
    // T stands for "Type"
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}
```
        
### 제네릭을 왜 사용할까? 
https://docs.oracle.com/javase/tutorial/java/generics/why.html
-	제네릭은 클래스,인터페이스,메서드를 정의할 때 **타입**을 파라미터로 받게 해준다. 
-	다른 종류의 input에 관해 같은 코드를 재사용할 수 있게 해준다. (타입을 변경해서) 

#### 제네릭을 사용하는 이점 
-	타입 안정성 
	-	1) 독스(튜토리얼)에서는 컴파일타임의 강한 타입 체크를 이유로 들고 있다. 타입을 명시해주기 때문에 다른 타입이 들어갈 경우 이에 대해서 컴파일 에러가 뜬다. 
    
![](https://images.velog.io/images/bongf/post/62b5dc06-e52f-4582-878a-007fa599e420/image.png)
-	cast의 제거 (형변환의 번거로움이 감소)
	-	제네릭이 없다면 아래와 같이 cast로 객체를 받아와야 하지만 
    ```java
    List list = new ArrayList();
	list.add("hello");
	String s = (String) list.get(0);
    ```
    -	제네릭으로 타입을 지정해줬기 때문에 바로 해당 객체를 cast 없이 해당 타입으로 받아올 수 있다. 
    ```java
   	List<String> list = new ArrayList<String>();
	list.add("hello");
	String s = list.get(0);   // no cast
    ```
-	generic 알고리즘을 구현하게 해준다. 
	-	여러 타입의 집합체에 적용되는 알고리즘을 짤 수 있게 한다. 
    
### 제네릭 사용 좀 더 자세히 
https://docs.oracle.com/javase/tutorial/java/generics/types.html
-	여러 타입을 담을 수 있는 Box라는 클래스를 만든다고 생각해보자. 제네릭 이전에는 아래와 같이 만들었다. 
```java
public class Box {
    private Object object;

    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}
```
![](https://images.velog.io/images/bongf/post/4fed09f0-acdb-4daa-b41c-213bc0cfcf93/image.png)
-	어떤 타입도 넣을 수 있지만 이를 꺼낼 때는 Object로 꺼내지기 때문에 과연 그 타입이 어떤 타입인지 확신하기 어렵다. 어디서는 Integer로 꺼내길 기대하고 Integer를 꺼냈지만 사실은 String이 들어가 있을 수도 있다. 그리고 이를 Object로 받아 형변환(Integer)를 만들어 줘야 원하는 타입으로 받을 수 있다. 
-	이를 제네릭으로 바꾸면 아래와 같다.
```java
/**
 * Generic version of the Box class.
 * @param <T> the type of the value being boxed
 */
public class Box<T> {
    // T stands for "Type"
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}
```
![](https://images.velog.io/images/bongf/post/8b34defe-da8b-4b91-95c2-296224839baa/image.png)
-	이 박스에 Box에 Integer라는 타입 매개변수를 전달했다면 해당 T 대신에 Integer가 전부 들어가게된다. 그렇기 때문에 retrun도 Integer로 하는 것 
-	T에는 primitive 타입이 아닌 것은 어떤 타입이든 올 수 있다. 

### Type Parameter Naming Conventions
-	반드시 타입 파라미터를 T 로만 작성해야 하는 것은 아니다. 아래와 같이 작성해도 코드를 잘 동작한다. 
![](https://images.velog.io/images/bongf/post/a662e4fa-0241-4a6a-ab5c-b26d347b093a/image.png)
-	그러나 컨벤션을 따르는 것이 좋다. 
-	관례적으로 Type Parmeter는 대문자이면서 한글자다. 
-	일반적으로 사용되는 타입 파라미터다 
```
E - Element (used extensively by the Java Collections Framework)
K - Key
N - Number
T - Type
V - Value
S,U,V etc. - 2nd, 3rd, 4th types
```

#### 용어 주의 : 타입 파라미터, 타입 인자 
선언할 때는 : 타입 파라미터 
실제 사용할 때는 타입 인자라고 한다. 

### Generic Type에 Primitive Type이 올 수 없다. 
https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html
-	아래 코드는 Primitive Type 이기 때문에 컴파일 에러가 난다. 
```java
Pair<int, char> p = new Pair<>(8, 'a');  // compile-time error
```
-	Wrapper 클래스로 바꿔 줘야 한다. 
```java

Pair<Integer, Character> p = new Pair<>(8, 'a');
```
-	8이랑 'a'를 넣을 수 있는 것은 Autoboxing 기능 덕분 

### Diamond 
-	Java SE7 부터 컴파일러가 문맥상에서 해당 타입을 추론, 결정할 수 있다면 해당 타입을 생략할 수 있다. <> 를 비공식적으로 다이아몬드라고 부른다. 
```
Box<Integer> integerBox = new Box<>();
```

### 다중 타입 파라미터 
-	제네릭 클래스는 다중 타입 파라미터를 가질 수 있다. 

![](https://images.velog.io/images/bongf/post/562bcf89-8fb4-4a8c-b46d-8b5f5fdbc1bd/image.png)
![](https://images.velog.io/images/bongf/post/e2468d36-788e-4f35-9b87-04e529af7a2b/image.png)
-	HashMap도 마찬가지 

## 학습2) 제네릭 주요 개념 (바운디드 타입, 와일드 카드)
### 바운디드 타입 
https://docs.oracle.com/javase/tutorial/java/generics/bounded.html
-	타입 인자로 사용될 타입을 제한하고 싶을 때. (ex Number 클래스와 그거의 하위 클래스만으로 제한하고 싶을 때 사용하는 것이 바운디드 타입 
-	위의 ex. 처럼 상위 경계를 제한하고 싶을 때 `extends` 키워드를 사용한다. 
	-	여기서 extends는 상속과 구현의 의미를 담고 있다. 
-	아래와 같이 `Box<T>` 클래스가 있다고 했을 때, 
-	 
![](https://images.velog.io/images/bongf/post/5a29f6a9-4388-4366-ab1b-b110766d3f17/image.png)    
-	inspect 메소드에 String이 인자로 들어가면 컴파일 에러가 발생한다. Number의 하위 타입이 아니기 때문에.
	
![](https://images.velog.io/images/bongf/post/10fb86a9-2a2a-40fb-b5ab-82840cf4649f/image.png)
-	bounded type은 bounds에 정의된 클래스의 메서드를 호출할 수 있게 한다. 
```java
public class NaturalNumber<T extends Integer> {

    private T n;

    public NaturalNumber(T n)  { this.n = n; }

    public boolean isEven() {
        return n.intValue() % 2 == 0;
    }

    // ...
}
```
-	.intValue()는 Integer에 정의된 메서드 
#### 여러개의 bounds 
-	복수의 타입 제한이 가능하다 
```
<T extends B1 & B2 & B3>
```
-	bounds 중에 하나가 클래스라면 가장 앞에 와야 한다. 
```java
Class A { /* ... */ }
interface B { /* ... */ }
interface C { /* ... */ }

class D <T extends A & B & C> { /* ... */ }
```
-	A가 먼저 오지 않으면 컴파일 에러. 

#### 주의
-	Number을 타입 인자로 전달했을 때를 보자. 그렇다면 해당 타입이 적용되는 부분에 Number의 하위 타입을 넣을 수 있다. 아래 그림에서 add 메서드를 보자

![](https://images.velog.io/images/bongf/post/0d2a564b-69fc-4a56-9cfb-d4f98b1fd501/image.png)
-	이것은 가능하다. Integer와 Double이 Number의 하위 타입이니까. 
-	그런데 아래와 같은 메소드가 있다고 가정해보자 
```java
public void boxTest(Box<Number> n) { /* ... */ }
```
-	인자로 어떤 타입이 들어갈 수 있을까?  `Box<Integer>` 또는 `Box<Double>`가 들어갈 수 있을까? 
	-	No.  `Box<Integer>` 과 `Box<Double>` 은 `Box<Number>`의 하위 타입이 아니기 때문이다 .
    
### 와일드카드
-	https://docs.oracle.com/javase/tutorial/java/generics/wildcards.html
-	와일드카드라고 불리는 `?`은 unknown type을 대표한다. 
	-	다양한 곳에 쓰일 수 있다. 파라미터, 필드, 지역변수, 때때로 return까지 
-	자바의 정석
	-	`extends`와 `super`로 상한과 하한을 제한할 수 있다. 

> `<? extends T>` 와일드 카드의 상한 제한. T와 그 자손들만 가능 
`<? super T>` 와일드카드의 하한 제한. T와 그 조상들만 가능 
`<?>` 제한 없음. 모든 타입 가능. `<? extends Object>`와 동일

-	여기도 마찬가지로 extends는 실제 extends와 implements를 포함하는 의미다. 
-	extends와 super을 동시에 사용하여 상한과 하한을 동시에 제한 할 수는 없다. 

#### 와일드카드와 하위 유형 지정 
https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html
-	위에서 <주의> 라고 쓰고 `List<Integer>` 와` List<Number>`은 아무런 관계가 아니라고 이야기했다. 
	-	`List<Integer>`는 ` List<Number>`의 하위 타입이 아니다. 
- 이 둘을 와일드 카드를 이용해서 관계를 만들어 줄 수 있다. 
```java
List<? extends Integer> intList = new ArrayList<>();
List<? extends Number>  numList = intList;  // 통과. List<? extends Integer> 는 List<? extends Number>의 서브 타입
```


## 학습3) 제네릭 메소드 만들기
https://docs.oracle.com/javase/tutorial/java/generics/methods.html  
-	자바의 정석
	-	메서드의 선언부에 제네릭 타입이 선언된 메서드를 제네릭 메서드라고 한다. 
```java
// 독스 튜토리얼 예시 
public class Util {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
               p1.getValue().equals(p2.getValue());
    }
}
```
-	위의 클래스를 사용하려면 아래와 같은 코드가 작성되어야 할 것이다. 
```java 
Pair<Integer, String> p1 = new Pair<>(1, "apple");
Pair<Integer, String> p2 = new Pair<>(2, "pear");
boolean same = Util.<Integer, String>compare(p1, p2);
```
-	컴파일러가 해당 타입을 추론할 수 있으므로 타입 생략도 가능하다. 
```java
Pair<Integer, String> p1 = new Pair<>(1, "apple");
Pair<Integer, String> p2 = new Pair<>(2, "pear");
boolean same = Util.compare(p1, p2);
```

-	pair의 인자 타입이 다르다면 컴파일 에러가 날 것이다. 

![](https://images.velog.io/images/bongf/post/00f5f442-225f-4698-8d14-3819b750a029/image.png)
-	자바의정석 : Collections.sort()가 제네릭 메서드다 
```java
static <T> void sort(List<T> list, Comparator<? super T> c) 
```
-	자바의 정석
	-	지네릭 클래스에 정의된 타입 매개변수와 지네릭 메서드에 정의된 타입 매개변수를 구분해야 한다. 
    	-	박스가 다음과 같이 있다고 했을 때 
    	-	![](https://images.velog.io/images/bongf/post/66fbf71a-f9ce-4d0b-b15e-386611238c32/image.png) 
    	-	다음과 같이 각각 Integer와 String 타입으로 각각 다른 타입이 들어갈 수 있다. 이 둘의 T는 다른 T다. 
    	-	![](https://images.velog.io/images/bongf/post/c7be5269-fd5e-4f11-ae94-9ec9db0e7725/image.png)
    -	(독스 튜토리얼) 타입 파라미터의 스코프는 선언된 메소드 안으로 제한된다. 
    -	지네릭 메서드는 지네릭 클래스가 아닌 클래스에도 정의될 수 있다. 
    -	![](https://images.velog.io/images/bongf/post/a8a59d1a-345d-4590-8f24-c2989fb57bd7/image.png) 
    
## 학습4) Erasure
### Raw Types
https://docs.oracle.com/javase/tutorial/java/generics/rawTypes.html
-	Box 클래스와 위와 같이 있었다고 했을 때 

![](https://images.velog.io/images/bongf/post/c5bc21ff-395a-4710-8259-4be331ed6533/image.png)
-	타입인자를 빠트린다면 raw type을 생성했다고 한다. 

![](https://images.velog.io/images/bongf/post/aba0edef-23a6-47ad-8b4f-1287245fb035/image.png)
-	실제로 써보면 위에 처럼 

![](https://images.velog.io/images/bongf/post/0d205f5c-2038-4eaf-b302-d1d14ab22096/image.png) 라는 경고가 뜬다. 
-	[출처 블로그](https://jyami.tistory.com/99)에 따르면 raw type은 아래 erausre 때문에 나오게 되었다고 한다. 
	-	제네릭을 사용할 수 없었던 이전 버전의 하위호환성을 위해 나왔다고 한다. 

### Erasure 
https://docs.oracle.com/javase/tutorial/java/generics/erasure.html
https://www.baeldung.com/java-type-erasure
-	제네릭스는 **컴파일 타임**에 좀 더 타이트한 타입 체크를 하고 제네릭 프로그램을 지원하기 위해 도입되었다. 
-	그리고 런타입에는 이 타입에 대한 정보를 버린다.
-	그래서 자바 컴파일러는 type erausre 을 적용한다. 
	-	1) 모든 타입 파라미터를 그들의 bounds로 바꾸거나 타입 파라미터의 경계가 없다면(unbounded) Object로 바꾼다.
    -	2) 타입 안정성을 위해 필요하다면 type cast(형변환)를 삽입한다. 
    -	3) 확장된 제네릭 타입의 다형성을 유지하기 위해 브릿지 메소드를 생성한다. 
-	Type erasure는 파라미터로 들어온 타입 때문에 새로운 클래스가 생성되는 것을 막기 위한 것이다. 따라서 제네릭스는 런타임 오버헤드를 발생시키지 않는다. 

### 규칙 1
- 아래 예시와 함께 보자 
```java
public class Node<T> {

    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() { return data; }
    // ...
}

```
-	이런 클래스가 있었을 때 unbounded 이기 때문에 Object로 바꾼다. 
```java
public class Node {

    private Object data;
    private Node next;

    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() { return data; }
    // ...
}
```
### 규칙 2 
- 만약에 bounded 되어있었다면 
```java
public class Node<T extends Comparable<T>> {

    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() { return data; }
    // ...
}

```
-	아래와 같이 첫번째? bound class로 바꾼다. 여기서는 Comparable 
```java
public class Node {

    private Comparable data;
    private Node next;

    public Node(Comparable data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Comparable getData() { return data; }
    // ...
}
```
-	같은 규칙이 제네릭 메소드에도 적용된다. 
### 규칙 3 
-	코드가 아래와 같이 있다고 했을 때 
```java
public class Node<T> {

    public T data;

    public Node(T data) { this.data = data; }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

public class MyNode extends Node<Integer> {
    public MyNode(Integer data) { super(data); }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
```
-	다음과 같은 코드를 보자 
```java
MyNode mn = new MyNode(5);
Node n = mn;            // A raw type - compiler throws an unchecked warning
n.setData("Hello");     // Causes a ClassCastException to be thrown.
Integer x = mn.data;    
```
-	원래 `Node<Integer>`로 받아야 하지만 raw type으로 받았을 때 타입을 체크할 수 없으니 컴파일은 안나고 노란 밑줄로 unchecked warning을 보여준다. 그렇게 setData에 String 타입을 넣어도 컴파일에는 문제가 없고 실행했을 때 ClassCastException이 발생한다. ![](https://images.velog.io/images/bongf/post/a0af3635-1898-4c14-b20d-ae1ba7913147/image.png)
-	이 코드는 type erasure 후에는 이렇게 되었을 것이다. 
```java
MyNode mn = new MyNode(5);
Node n = (MyNode)mn;         // A raw type - compiler throws an unchecked warning
n.setData("Hello");          // Causes a ClassCastException to be thrown.
Integer x = (String)mn.data;

```
-	왜 classCastException이 날까? 해당 예외가 날 수 있는 것은 Bridge Methods 덕분이다. 
-	erause 다음에 Node와 MyNode는 다음과 같이 변할 것이다. 
```java
public class Node {

    public Object data;

    public Node(Object data) { this.data = data; }

    public void setData(Object data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

public class MyNode extends Node {

    public MyNode(Integer data) { super(data); }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
```
-	erase 다음에 메소드 시그니쳐가 맞지 않아 `MyNode.setData()`가 `Node.setData()`를 오버라이드 하지 못한다. (들어오는 인자 타입이 다르므로) 
-	그렇게 되면 사실 위에서 classCastException이 발생하지 않아야 맞다. 실제로 저대로 코드를 작성해 보면 setData 부분이 아닌 받아오는 부분에서 컴파일 에러가 나는 것을 볼 수 있다. (returnType이 Object이니까) 그런데 우리는 classCastException이 발생했음을 확인했다. ![](https://images.velog.io/images/bongf/post/5ff9cf56-875c-43c4-b787-afb79bd77da1/image.png)![](https://images.velog.io/images/bongf/post/bae483aa-7118-4c82-b891-e82a8f619eb1/image.png) ![](https://images.velog.io/images/bongf/post/66a03900-fee4-40ea-8859-fee8ea88d305/image.png) 
-	그것은 erasure 다음에 제네릭 타입의 다형성을 보장하고 이 문제를 해결하기 위해 자바 컴파일러가 bridge 메소드를 생성해서 서브 타이핑(subtyping)이 보장 되도록 했기 때문.  
-	아래와 같은 브릿지 메소드를 만든다고 한다. 
```java 
class MyNode extends Node {

    // Bridge method generated by the compiler
    //
    public void setData(Object data) {
        setData((Integer) data);
    }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }

    // ...
}
```
-	그래서 String이 들어오면 Integer로 Cast될 수 없어 ClassCastException이 발생하는 것이다. 
