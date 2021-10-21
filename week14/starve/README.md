## 제네릭 사용법

### 제네릭이란?

- **다양한 타입**의 객체들을 다루는 **메서드**나, **컬렉션 클래스**에 컴파일 시 타입 체크(compile - time type check) 를 해주는 기능.

  * 객체의 타입 안전성을 높이고 형변환의 번거로움이 줄어든다.
  * 이미 어떤 객체를 저장할 것인지 지정을 해두었기 때문에, 저장된 객체를 꺼낼 때 형변환 할 필요가 없어 편리하다는 장점이 있다.

  * ex) ArrayList를 생성할 때, 저장할 객체의 타입을 지정하여, 다른 객체를 저장하려 시도할 경우 에러가 발생하는 것이 제네릭의 대표적인 예이다.

  ```java
  // 제네릭스 적용 코드
  ArrayList<Tv> tvList = new ArrayList<Tv>();
  tvList.add(new Tv());
  Tv t = tvList.get(0); // 형변환 불필요
  ```

  - 장점:

    1. 타입 안전성을 제공.

       > 의도하지않은 타입의 객체 저장을막고, 꺼내올 때 원래의 타입과 다른 타입으로 형변환 되어 발생할 수 있는 오류가 감축된다.

    2. 타입 체크와 형변환을 생략할 수 있으므로, 코드가 간결해짐.

### 제네릭 사용하기

#### 선언 예시

```java
class Box<T> {
  private T t;
  
  public void set(T t) {
    this.t = t;
  }
  public T get() {
    return t;
  }
}
```

* T를 타입 변수라고 한다.

* 제네릭 용어

  - `class Box<T> {}`

    - `Box<T>` : 지네릭 클래스. 'T의 Box' 또는 'T Box'라고 읽는다.

    - `T` : 타입 변수 또는 타입 매개변수. (T는 타입 문자)

      > 메서드의 매개변수와 유사한 면이 있기 때문에 이러하게 부른다.
      >
      > - 때문에, 아래와 같이 타입 매개변수에 타입을 지정하는 것을 **'지네릭 타입 호출'** 이라고 하고,
      > - 지정된 타입 'String'과 같은 것을 **'매개변수화된 타입(parameterized type)'** 이라고 한다. 이는 너무 길기 때문에, '대입된 타입'이라고도 부른다.
      >
      > [![스크린샷 2020-12-29 오후 6 27 14](https://user-images.githubusercontent.com/69128652/103273787-7ac4b080-4a03-11eb-88eb-6e72709301c7.png)](https://user-images.githubusercontent.com/69128652/103273787-7ac4b080-4a03-11eb-88eb-6e72709301c7.png)
      >
      > - 컴파일이 된 후에는`Box<String>` , `Box<Integer>` 이었던 것이 '원시타입'인 Box로 바뀌어, 지네릭 타입이 제거된다.

    - `Box` : 원시 타입(raw type

#### 제네릭 제약 조건

* Box 객체 생성 시 객체별로 다른 타입을 지정하는 것은 적절하지만, 모든 객체에 동일하게 동작해야하는 static멤버에 타입변수 T를 사용하는 것은 적절하지 않다.

  > T는 인스턴스 변수로 간주되기 때문에, static 멤버는 인스턴스 변수를 참조할 수 없다.

  ```java
  class Box<T>{
    static T item; // 에러
    static int compare(T t1, T t2){...} // 에러
    ...
  }
  ```

* static 멤버는 타입 변수에 대입된 타입의 종류에 관계없이 동일한 것이어야 한다.

  > 'Box.item'과 'Box.item'이 서로 다른것이면 안된다.

* 지네릭 타입의 배열을 생성하는 것이 불가능 하다. 정확히는 지네릭 배열 타입의 **참조변수를 선언**하는 것은 **가능**하지만, `new T[10]`과 같이 **배열을 생성하는 것은 안된다.**

  ```java
  class Box<T>{
   T[] itemArr; // OK. T타입의 배열을 위한 참조변수
    ...
   T[] toArray(){
      T[] tmpArr = new T[itemArr.length]; // 에러. 지네릭 배열 생성 불가.
      ...
      return tmpArr;
    }
    		...
  }
  ```

  > 배열을 생성하지 못하는 이유는 연산자 new 때문이다. 컴파일 시점에 타입 T가 무엇인지 정확히 알아야 하는데, 컴파일 시점에서 T가 어떤 타입이 될지는 모르기 때문.
  >
  > instanceof연산자도 new연산자와 같은 이유로 T를 피연산자로 사용할 수 없다.

## 제네릭 주요 개념 (바운디드 타입, 와일드 카드)

### 바운디드 타입

* 제네릭 클래스 생성 시, 타입 인자로 받고싶은 타입을 특정 타입으로 제한하고 싶을 때 사용하면 된다.

* 선언 방법

  ```java
  class FruitBox <T extends Fruit> { // implements 도 가능.
  // 과일 클래스만 담을 수 있다.
  }
  ```

* 바운디드 타입으로 여러 타입을 지정할 수 있는데, 이 때 조건문의 and를 사용한 것 처럼 &기호 표시 후 다음 타입을 지정해주면된다.
  대신 입력 될 타입의 데이터는 선언 시 나열된 모든 타입의 하위 타입이어야 하며, 만일 클래스와 인터페이스를 같이 선언 할 경우 클래스를 먼저 지정해줘야한다.

  ```java
  class Box <T entends Fruit & Vegetable> {
  }
  ```

* 제네릭 클래스 뿐만아니라, 제네릭 메서드에서도 사용이 가능하다.

  

### 와일드 카드

- 제네릭 클래스 생성 시 참조변수에 지정된 제네릭 타입과 생성자에 지정된 제네릭 타입은 일치해야하는데, 제네릭 타입에 다형성을 적용하고 싶다면 '와일드 카드'를 사용하면 된다.

- 와일드 카드는 &기호를 사용할 수 없다.

  - 와일드 카드는 기호 **'?'를 사용**하며, **'extends'**와 **'super'**로 상한(upper bound)과 하한(lower bound)을 제한 할 수 있다.

    | 종류          | 설명                                                    |
    | ------------- | ------------------------------------------------------- |
    | <? extends T> | 와일드 카드의 상한제한. T와 그 자손들만 가능            |
    | <? super T>   | 와일드 카드의 하한제한. T와 그 조상들만 가능            |
    | <?>           | 제한 없음. 모든 타입이 가능. <? extends Object>와 동일. |

- 와일드 카드 이용 시, 다음과 같이 하나의 참조변수로 다른 제네릭타입이 지정된 객체를 다룰 수 있음. (Tv와 Audio가 Product의 자손이라고 가정.)

  ```java
  // 제네릭 타입이 <? extends Product> 이면, Product와 Product의 모든 자손이 OK
  ArrayList<? extends Product> list = new ArrayList<Tv>(); // OK
  ArrayList<? extends Product> list = new ArrayList<Audio>(); // OK
  ```

- 메서드의 매개 변수에 적용 하면,

  ```java
  static Juice makeJuice(FruitBox<? extends Fruit> box){
    String tmp = "";
    for(Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
  }
  ```

  다음과 같이 제네릭 타입이 다른 객체를 매개 변수로 지정할 수 있다.

  ```java
  System.out.println(Juicer.makeJuice(new FruitBox<Fruit>())); // OK
  System.out.println(Juicer.makeJuice(new FruitBox<Apple>();)); // OK
  ```

  

## 제네릭 메소드 만들기

- 메서드의 선언부에 제네릭 타입이 선언된 메서드를 **제네릭 메서드**라 한다.

- Collcetions.sort()가 제네릭 메서드 이며, 제네릭 타입의 선언위치는 **반환타입 바로 앞**이다.

  ```java
  static <T> void sort(List<T> list, Comparator<? super T> c)
  ```

  - 여기에 있는 의 경우, 클래스에 정의된 타입 매개변수와 같아보이지만, 별개의 것이다. 같은 타입 문자 T를 사용해도 같은 것이 아니라는 것에 주의 하자.

- 제네릭클래스가 아닌 클래스에도 제네릭 메서드를 정의 할 수 있다.

  ```java
  class FruitBox<T> {
    ...
    static <T> void sort(List<T> list, Comparator<? super T> c)
  }
  ```

  - 위의 경우, FruitBox의 T와 sort 내의 List는 문자만 같고 서로 다른 것.
  - 앞서 static멤버에는 타입 매개변수를 사용 할수 없다 하였지만, 위 처럼 메서드에 제네릭 타입을 선언하고 사용하는 것은 가능하다.

- 메서드에 선언된 제네릭 타입 == 지역변수를 선언한것 이라고 생각하면 이해하기가 쉽다.

  이 타입 매개변수는 메서드 내에서만 **지역적으로 사용**될 것이므로, 메서드가 static이어도 괜찮은것이다.



````java
class Juicer {
    static Juice makeJuice(FruitBox2<? extends Fruit2> box){
        String tmp = "";

        for(Fruit2 f : box.getList())
            tmp += f + " ";
        return new Juice(tmp);
    }
}
// 지네릭스로 바꿀경우:

class Juicer {
      static <T extends Fruit> Juice makeJuice(FruitBox2<T> box){
        String tmp = "";
  
        for(Fruit2 f : box.getList())
              tmp += f + " ";
        return new Juice(tmp);
      }
````



## Erasure

- 지네릭이 도입 되기 이전의 소스코드와 호환성 유지를 위해,

  1. 컴파일러는 지네릭 타입을 이용해 소스파일을 체크
  2. 필요한 곳에 형변환을 넣음.
  3. 지네릭 타입을 제거함.

  위와 같은 과정을 거쳐, 컴파일이 된 이후의 파일(.class)에는 지네릭 타입에 대한 정보를 없도록 만든다.

- 제거 과정은 복잡하기 때문에, 기본적인 과정만 알아보자.

  1. **지네릭 타입의 경계(bound)를 제거한다.**

     - 지네릭 타입이 라면, T는 Fruit로 치환됨. 의 경우 T는 Object로 치환됨.

     - 이후 지네릭 타입 선언 제거.

       ```java
       class Box<T extends Fruit> {
         void add(T t){
           ...
         }
       }
       ```

       ⬇️

       ```java
       class Box {
         void add(Fruit t){
           ...
         }
       }
       ```

  2. **지네릭 타입을 제거 후, 타입이 일치하지 않다면 형변환 추가.**

     - List의 get()은 Object타입을 반환하므로 형변환 필요.

       ```java
       T get(int i){
         return list.get(i);
       }
       ```

       ⬇️

       ```java
       Fruit get(int i){
         return (Fruit)list.get(i);
       }
       ```

     - 와일드 카드 포함 시, 적절한 타입으로의 형변환이 추가됨.

       ```java
        static Juice makeJuice(FruitBox2<? extends Fruit2> box){
               String tmp = "";
       
               for(Fruit2 f : box.getList())
                   tmp += f + " ";
               return new Juice(tmp);
           }
       ```

       ⬇️

       ```java
        static Juice makeJuice(FruitBox2 box){
               String tmp = "";
       				Iterator it = box.getList().iterator();
               while(it.hasNext()){
                   tmp += (Fruit)it.next() + " ";
               }
               return new Juice(tmp);
           }
       ```