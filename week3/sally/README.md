### **연산자**

> Operators are special symbols that perform specific operations on one, two, or three operands, and then return a result.

연산자는 경우에 따라 하나, 둘, 혹은 세개의 피연산자를 이용해 연산을 하고 결과를 가져오는 기호를 뜻한다.

### **산술 연산자**

산술 연산자는 사칙연산과 모듈을 다루는 연산자이다.

산술 연산에서 두 피연산자의 타입이 다를 경우 컴파일러가 자동으로 변환을 수행해준다. 이를 산술 변환이라고 한다.

```
int a = 1, b;
long c = 2, d;

b = a + c; // 컴파일 에러
d = a + c; // int타입의 a가 long타입의 c와 더해질 때 자동으로 long 타입으로 형변환이 이뤄지기 때문에 long 타입의 변수에만 들어갈 수 있다.
```

```
byte a = 1;
byte b = 3;

byte c = a + b; // 컴파일 에러!!
int d = a + b; // int타입보다 작은 byte나 short의 경우 사이즈가 작으므로 산술 연산 도중 오버플로우가 생길 가능성이 높아 이를 방지하기 위해 정수형의 기본형인 int로 변환이 된다.
byte e = (byte) a + b; // 타입을 고정시키고 싶다면 이것도 방법이다.
```

### **비트 연산자**

자바는 정수형에 비트 연산을 할 수 있는 비트 연산자를 제공한다.

![https://user-images.githubusercontent.com/59776016/126673999-445f93cb-aa64-46b5-9cd4-2f4b7576d9fe.png](https://user-images.githubusercontent.com/59776016/126673999-445f93cb-aa64-46b5-9cd4-2f4b7576d9fe.png)

### **관계 연산자**

두 피연산자를 비교하고 결과에 따라 boolean 값을 반환한다.

![https://user-images.githubusercontent.com/59776016/126674000-efbc9b91-a9fe-4a5f-a2e1-5b322254296c.png](https://user-images.githubusercontent.com/59776016/126674000-efbc9b91-a9fe-4a5f-a2e1-5b322254296c.png)

### **논리 연산자**

Boolean 값의 두 피연산자 사이에서 AND나 OR를 따지는 연산자이다.

![https://user-images.githubusercontent.com/59776016/126674005-e882970d-d276-4248-aa48-ed180f6faf8a.png](https://user-images.githubusercontent.com/59776016/126674005-e882970d-d276-4248-aa48-ed180f6faf8a.png)

||연산자는 조건식에 들어가는 경우 왼쪽의 피연산자가 참일 경우 뒤의 피연산자는 따지지 않지만 | 이렇게 하나만 쓰는 경우에는 두 피연산자를 모두 본다.

### **instanceof**

`instanceof` 연산자는 객체를 특정 클래스(데이터 타입)과 비교하여 해당 클래스의 객체인지를 알려준다. 직접적으로 비교하는 클래스의 객체가 아닐지라도 서브 클래스나 특정 인터페이스를 받는 경우에도 사용할 수 있다.

```
class InstanceofDemo {
    public static void main(String[] args) {

        Parent parent = new Parent();
        Parent child = new Child();

        System.out.println("parent instanceof Parent: "
            + (parent instanceof Parent));
        System.out.println("parent instanceof Child: "
            + (parent instanceof Child));
        System.out.println("parent instanceof MyInterface: "
            + (parent instanceof MyInterface));
        System.out.println("child instanceof Parent: "
            + (child instanceof Parent));
        System.out.println("child instanceof Child: "
            + (child instanceof Child));
        System.out.println("child instanceof MyInterface: "
            + (child instanceof MyInterface));
    }
}

class Parent {}
class Child extends Parent implements MyInterface {}
interface MyInterface {}

//output
obj1 instanceof Parent: true
obj1 instanceof Child: false
obj1 instanceof MyInterface: false
obj2 instanceof Parent: true
obj2 instanceof Child: true
obj2 instanceof MyInterface: true
```

### **assignment(=) operator**

변수에 값을 지정해줄 때 사용된다. 다른 연산자와는 다르게 오른쪽에서 왼쪽으로 움직이고 다른 여러 연산자와 결합해 사용할 수 있다.

![https://user-images.githubusercontent.com/59776016/126674198-ae2cdd12-950b-44b3-86ab-9a527ebe36e2.png](https://user-images.githubusercontent.com/59776016/126674198-ae2cdd12-950b-44b3-86ab-9a527ebe36e2.png)

### **화살표(->) 연산자**

자바 8부터 람다 표현식을 사용할 수 있게 되고 화살표 연산자가 생긴다. 화살표 연산자에 담긴 의미는 매개변수를 이용하여 중괄호를 실행한다는 뜻이 있다.

```
Runnable r = () -> System.out.print("Run!");
```

### **3항 연산자**

유일하게 피연산자가 3개인 연산자로, 한 종류만 있어서 이 자체를 3항 연산자라고 부른다. 조건 연산자라고도 불린다.

```
variable a = (expression) ? value if true : value if false
```

```
int a = 10;
int b = (a == 1) ? 20 : 30;
System.out.println("Value b is: " + b);

int c = (a == 10) ? 20 : 30;
System.out.println("Value c is: " + c);

//output
Value b is: 30
Value c is: 20
```

### **연산자 우선 순위**

아래 표의 순서대로 연산자의 우선순위가 결정된다. 우선순위대로 연산이 이루어지고 몇몇의 연산자는 우선순위가 같은데 이 때는 왼쪽부터 오른쪽으로, 연산이 흘러가는 방향대로 연산자를 수행한다.

![https://user-images.githubusercontent.com/59776016/126673922-c87a9646-e416-42de-b15d-e088aee0185e.png](https://user-images.githubusercontent.com/59776016/126673922-c87a9646-e416-42de-b15d-e088aee0185e.png)

### **(optional) Java 13. switch 연산자**

- ~~Java 13 이전의 스위치문과 다르게 break을 생략하고 yield를 사용할 수 있게 되었다.~~ -> 스위치 문이 아니라 switch operator가 생긴 것

```
// 기존의 스위치
switch (v) {
  case "1":
    result = 1;
    break;
  case "2":
    result = 2;
    break;
  case "3":
  case "4":
  case "5":
    result = 3;
    break;
}

// 12의 스위치
switch (v) {
  case "1" -> 1;
  case "2" -> 2;
  case "3", "4", "5" -> 3;
}

// 13의 스위치
switch (v) {
  case "1":
    yield 1;
  case "2":
    yield 2;
  case "3", "4", "5":
    yield 3;
}
```

### **Reference**

[https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html)
