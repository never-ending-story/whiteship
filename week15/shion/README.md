## 람다식


## 함수형 인터페이스
- 인터페이스에 추상메서드가 오직 하나만 있는 것

```java
@FunctionalInterface // 추상메서드가 하나일 경우 해당 애너테이션을 붙일 수 있다 (자바 제공)
interface RunSomething {

    void do(); // public abstract 생략된 추상메서드 1개

    static void run() {} // static or default 메서드는 상관 없다.

}
```

- 이렇게 정의한 함수형 인터페이스는 구현체를 만들어서 사용해야 한다.

```java
// 자바 8 이전, 익명 내부 클래스를 만들어서 사용한다.
public static void main(String[] args) {
    RunSomething runSomething = new RunSomething() {
        @Override
        public void doIt() {
            System.out.println("run something...");
        }
    };

    runSomething.doIt();
}
```

### 결과 : 

```
run something...
```

- 자바 8부터는 람다 표현식이 추가되었다. 아래 코드는 위의 코드와 내부적으로 동일하다.

```java
public class Main {
    public static void main(String[] args) {
        RunSomething runSomething = () -> System.out.println("run something...");
        runSomething.doIt();
    }
}
```

### 결과 : 

```
run something...
```

- 람다 표현식을 쓰면 다른 언어에서의 함수처럼 보이지만, 자바는 객체지향 언어이기 때문에 특수한 형태의 오브젝트라고 볼 수 있다. (변수에 저장하고, 메서드의 파라미터로 전달하거나 실행 결과로 반환할 수 있다 (일급 객체))

### 자바에서의 함수형 프로그래밍

- 자바8부터 함수형 프로그래밍을 위한 초석으로 **함수형 인터페이스**와 **람다 표현식**이 제공된다.
- 위 2가지 개념(함수형 인터페이스, 람다 표현식)은 굳이 함수형 프로그래밍을 하지 않더라도 문법으로 지원하기 때문에 써도 된다.
- 하지만 함수형 프로그래밍을 하기 위해선 다음을 주의해서 사용해야한다.
    1. 함수를 `First-class object`(일급 객체)로 사용할 수 있다.
        - 보통 함수에 매개변수로 넘기기, 수정하기, 변수에 대입하기와 같은 연산을 지원할 때 일급 객체라고 한다.
    2. 고차함수 (Higher-Order function)
        - 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수도 있다.
        - 일급 객체가 된다면 당연히 고차함수도 가능하다.
    3. 순수 함수 (Pure function)
        - 입력 받은 값이 동일한 경우 결과값이 같아야한다.
        - 함수 밖에 있는 값을 변경하지 않는다.

```java
@FunctionalInterface
interface RunSomething {

    int doIt(int number);

}

public class Main {
    public static void main(String[] args) {
        RunSomething runSomething = (number) -> number + 10;

        // 같은 입력 값일 경우 결과 값이 같다.
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));

        System.out.println(runSomething.doIt(2));
        System.out.println(runSomething.doIt(2));
        System.out.println(runSomething.doIt(2));
    }
}
```

### 결과 : 
```
11
11
11
12
12
12
```

- 같은 값을 넣었을 때 결과값이 같지 않을 여지, 가능성이 생기는 경우 (Not Pure)는 아래와 같다.
1. 함수 안에서 함수 밖에 있는 값을 참조해서 사용하는 경우 (`baseNumber`) - ***상태값을 가지고 있다.***
   
```java
public class Main {
    public static void main(String[] args) {
        RunSomething runSomething = new RunSomething() {
            int baseNumber = 10;

            @Override
            public int doIt(int number) {
                return number + baseNumber;
            }
        };
    }
}
```

2. 외부 값을 변경하는 경우

```java
// 문법적인 지원
public class Main {
    public static void main(String[] args) {
        int baseNumber = 10;

        RunSomething runSomething = new RunSomething() {
            @Override
            public int doIt(int number) {
                baseNumber++; // doIt에서 baseNumber를 final이라고 가정하고 사용하기 때문에 컴파일 에러가 발생한다.
                return number + baseNumber;
            }
        };
    }
}
```

```java
// 하지만 가능한 경우도 있다.
public class Main {
    public static void main(String[] args) {

        RunSomething runSomething = new RunSomething() {
            int baseNumber = 10;

            @Override
            public int doIt(int number) {
                baseNumber++;
                return number + baseNumber;
            }

        };
    }
}
```


- 함수형 프로그래밍을 하려면 위의 주의사항을 지켜야 한다.

### 자바에서 제공하는 함수형 인터페이스

- `java.lang.function`패키지에 정의되어 있는 함수형 인터페이스를 살펴보자.
  1. Function<T, R>
  2. BiFunction<T, U, R>
  3. Consumer<T>
  4. Supplier<T>
  5. Predicate<T>
  6. UnaryOperator<T>
  7. BinaryOperator<T>

### 1. Function<T, R>
- `T`타입을 받아서 `R`타입을 리턴하는 함수형 인터페이스 
- 두 개의 타입이 다를 수 있기 때문에 `T`, `R`로 정의되어 있다. 
- 우리는 `R apply(T t)` 추상메서드를 구현하면 된다.

```java
// 어떤 숫자를 받아서 10을 더해주는 클래스
class Plus10 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}

public class Main {
    public static void main(String[] args) {
        Plus10 plus10 = new Plus10();
        // 1을 입력하면 10을 더해서 11이 출력된다.
        System.out.println(plus10.apply(1));
    }
}
```

### 결과 :
```
11
```

- 람다 표현식으로 바로 사용할 수도 있다.

```java
public class Main {
    public static void main(String[] args) {
        Function<Integer, Integer> plus10 = i -> i + 10;
        System.out.println(plus10.apply(1));
    }
}
```

- 함수를 조합할 수 있는 메서드를 `default`메서드로 제공한다.
  1. `andThen`
  2. `compose`

```java
public class Main {
    public static void main(String[] args) {
        Function<Integer, Integer> plus10 = i -> i + 10;
        Function<Integer, Integer> multiply2 = i -> i * 2;
        // 10을 더하는 plus10 함수와 2를 곱하는 multiply2 함수를 조합할 수 있다.
        System.out.println(plus10.compose(multiply2).apply(2));
        System.out.println(plus10.andThen(multiply2).apply(2));
    }
}
```

### 결과 : 
```
14
24
```
- `plus10.compose(multiply2).apply(2);`
  - 2를 먼저 곱한 후(`multiply2`) `compose`해서 결과 값에 10을 더한다.(`plus10`)
- `plus10.andThen(multiply2).apply(2);`
  - 10을 먼저 더한 후(`plus10`) 그리고나서(`andThen`) 2를 곱한다.(`multiply2`)

### 2. BiFunction<T, U, R>

- `T`, `U` 두개의 타입을 받아서 `R`타입으로 반환한다.

### 3. Consumer<T>

- `T`타입을 받아서 아무것도(`void`) 반환하지 않는다. (Consume만 한다.)

### 4. Supplier<T>

- 받는게 없고 `T`타입을 반환만 한다. (supply만 한다.)

### 5. Predicate<T>

- `T`타입 인자를 받아서 `true` or `false`를 반환한다. (`boolean` 반환)
- 조합이 가능하다.
  1. and `&&`
  2. or `||`
  3. negate `!`

### 6. UnaryOperator<T>

- `Function` 함수형 인터페이스의 특수한 케이스다.
- `Function<T, R>`의 입력값 타입 `T` 와 반환값 타입 `R`이 같은 경우, `UnaryOperator<T>`로 통일하여 사용할 수 있다.

### 7. BinaryOperator<T>

- `BiFunction<T, U, R>`의 특수한 형태
- `T`,`R`,`U` 세개의 형태가 같을 때, `BinaryOperator<T>`를 사용한다.

```
그외 함수형 인터페이스는 보통 이름으로 추측 가능하다.
```

### 람다 표현식

- 파라미터값이 없을 경우 `()`
- 하나일 경우 `one` or `(one)`
- `{}`안에 한 문장만 있을 경우 `{}` 생략 가능, `return`도 생략 가능하다.

```java
Supplier<Integer> get10 = () -> {
    return 10;  
};

Supplier<Integer> get10 = () -> 10;
```

- 파라미터값이 두개인 경우

```java
BinaryOperator<Integer> sum = (a, b) -> a + b;

// a, b의 타입을 적어주어도 되지만 제네닉으로 정의한 타입이 있어서 컴파일러가 추론하기 때문에 생략 가능하다.
BinaryOperator<Integer> sum = (Integer a, Integer b) -> a + b;
```

### Variable Capture

```java
public class Main {
    public static void main(String[] args) {
        final int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };
    }
}
```
- 로컬클래스 또는 익명클래스에서는 외부 `final`변수를 참조할 수 있다. (자바 8 이전)
- 자바 8 이후 외부 변수(`baseNumber`)가 사실상 `final`인 경우 생략 가능하다.
- 사실상 `final`이라는 말은 해당 변수가 `final`키워드는 없지만 어디서도 그 값이 변경되지 않을 경우를 말한다. (`effectively final`)

```java
public class Main {
    public static void main(String[] args) {
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };

        // baseNumber++;
        // 위 주석을 해제하여 baseNumber 값을 변경하게 되면 
        // 내부 클래스와 익명 클래스에서 참조하고 있는 baseNumber에서 컴파일 에러가 발생한다.
        // Error Message : 
        // Variable 'baseNumber' is accessed from within inner class, 
        // needs to be final or effectively final
    }
}
```

- 내부 클래스와 익명 클래스는 ***쉐도잉***을 한다.

```java
public class Main {
    public static void main(String[] args) {
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 20; 
                System.out.println(baseNumber); 
                // main메서드에 정의된 baseNumber가 로컬 클래스에 새로 정의된 baseNumber로 가려진다. (쉐도잉)

            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber);
                // main메서드에 정의된 baseNumber가 accept의 인자 baseNumber로 가려진다. (쉐도잉)
            }
        };
    }
}
```

- 람다도 마찬가지로 `final`또는 `effectively final`로 정의된 외부 변수만 참조할 수 있다.

```java
public class Main {
    public static void main(String[] args) {
        int baseNumber = 10; // effectively final

        // 람다
        IntConsumer printInt = () -> System.out.println(i + baseNumber);
    }
}
```

- 람다는 ***쉐도잉***이 일어나지 않는다.

```java
public class Main {
    public static void main(String[] args) {
        int baseNumber = 10; // effectively final

        // 람다는 람다를 감싸고 scope(main method)와 같은 scope이다.
        // 그래서 람다 안에서 baseNumber를 새로 정의할 수 없다.
        // Error Message : Variable 'baseNumber' is already defined in the scope
        IntConsumer printInt = Integer baseNumber -> System.out.println(baseNumber);
    }
}
```

### 메서드 레퍼런스

- 람다 표현식을 구현할 때 쓸 수 있는 방법이다.
- 기존에 이미 있는 메서드를 참조하여 함수형 인터페이스의 구현체로 사용하는 것이다. 

|메서드 종류|메서드 참조하는 방법|
|-|-|
| 스태틱 메소드 참조 | 타입::스태틱 메소드 | 
특정 객체의 인스턴스 메소드 참조 | 객체 레퍼런스::인스턴스 메소드 |
| 임의 객체의 인스턴스 메소드 참조  |타입::인스턴스 메소드|
| 생성자 참조 | 타입::new |

```java
class Greeting {

    private String name;

    public Greeting() {

    }

    public Greeting(String name) {
        this.name = name;
    }

    public String hello(String name) {
        return "hello " + name;
    }

    public static String bye(String name) {
        return "bye " + name;
    }
}

public class Main {
    public static void main(String[] args) {
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello; // 인스턴스 메서드 레퍼런스
        UnaryOperator<String> bye = Greeting::bye; // static 메서드 레퍼런스

        Supplier<Greeting> newGreeting = Greeting::new; // 인자가 없는 생성자 레퍼런스
        Greeting g = newGreeting.get();

        Function<String, Greeting> shionGreeting = Greeting::new; // 문자열을 받는 생성자 레퍼런스
        Greeting shion = shionGreeting.apply("shion");
    }
}
```

#### 임의 객체의 인스턴스 메서드 참조 
- 특정 타입이긴 한데 그 타입의 불특정 다수를 참조할 때, 여기선 `names`의 `"ccc", "bbb", "aaa"`
- `Arrays.sort()`에서 파라미터를 두개 받는 것이 있다.

```java
public class Main {
    public static void main(String[] args) {
        String[] names = {"ccc", "bbb", "aaa"};
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
    }
}
```
- 위의 코드에서 `Comparator`가 자바 8부터 함수형 인터페이스로 바껴서 람다식으로 바꿀 수 있다.

```java
Arrays.sort(names, (o1, o2) -> 0);
```

- 람다식으로 표현할 수 있다는 것은 메서드 레퍼런스를 사용할 수 있다는 뜻이다.
- 즉, 어떤 문자열 `o1`이 다른 문자열 `o2`와 비교를 해서 `int`값을 넘겨주는 메서드를 참조할 수 있다.

```java
public int compareToIgnoreCase(String str) {
        return CASE_INSENSITIVE_ORDER.compare(this, str);
    }
```

```java
public class Main {
    public static void main(String[] args) {
        String[] names = {"ccc", "bbb", "aaa"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
    }
}
```
### 결과 : 
[aaa, bbb, ccc]

- Q. 메소드가 두개 이상인 인터페이스에 함수형 표현식인 람다 표현식을 어떻게 대응 시킬 수 있을까요?
  
참고 : https://www.inflearn.com/course/the-java-java8/dashboard
        System.out.println(Arrays.toString(names));
