## Annotation

- 주석(마크, 표시, 징표)
- 다이나믹하게 알아내야하는 코드는 못들어간다.(런타임에 알아내야하는 코드는 못들어간다.)
- 동적으로 런타임 중에 바뀌는 소스는 쓸 수 없다.
- 컴파일 타임(정적)에 정해져야한다.

```java
public class HelloController {

    private static String hello = "hello";

    @GetMapping(hello) // 에러 남. hello를 final로 변경해줘야 한다.
    public String hello() {
        return "hello";
    }
}
```

### 애너테이션이란?
- 주석처럼 프로그래밍 언어에 영향을 미치지 않으며, 유용한 정보블 제공한다.


### 애너테이션을 정의하는 방법

```java
@interface 애너테이션이름 {
    타입 요소이름(); // 애너테이션의 요소를 선언한다.
    // ...
}
```

- 요소 규칙

```
- 요소의 타입은 기본형, String, enum, 애너테이션, Class만 허용된다.
- ()안에 매개변수를 선언할 수 없다.
- 예외를 선언할 수 없다.
- 요소를 타입 매개변수로 정의할 수 없다.
```
```java
@interface AnnoTest {
    int id = 100; // OK, 상수선언 -> static final int id = 100; 이 된다.
    String major(int i, int j);      // NO, 매개변수를 선안할 수 없다.
    String minor() throws Exception; // NO, 예외를 선언할 수 없다.
    ArrayList<T> list();             // NO, 요소의 타입에 타입 매개변수(T) 사용할 수 없다.
}
```
```java
@interface Master {
    String name();
    int age();
    Course course();
    String bootCamp() default "Code Squad";
}

class TestAnnotation {
    // String bootCamp를 지정하지 않아서 default 값인 Code Squad가 들어간다.
    @Master(name = "정호영", age = 45, course = Course.BACKEND)
    public String honux;

    @Master(name = "윤지수", age = 45, course = Course.FRONTEND, bootCamp = "Wooahan Camp")
    public String crong;

    @Master(name = "김정", age = 45, course = Course.IOS, bootCamp = "Boost Camp")
    public String jk;
}

enum Course {
    BACKEND,
    FRONTEND,
    IOS
}
```
- 요소가 하나면서 이름이 `value`면 애너테이션을 사용할 때, 요소 이름 없이 값만 정의해줄 수 있다.
```java
@interface Hello {
    String value();
}

@Hello("안녕하세요")
class Korean {
    
}
```

- 애너테이션에 `@Retention(RetentionPolicy.RUNTIME)`를 붙여주고 Reflection을 이용해 애너테이션 정보를 가져올 수 있다.

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Class<TestAnnotation> cls = TestAnnotation.class;
        Master anno = cls.getField("honux").getAnnotation(Master.class);

        System.out.println("애너테이션 : " + anno);
        System.out.println("name : " + anno.name());
        System.out.println("age : " + anno.age());
        System.out.println("course : " + anno.course());
        System.out.println("bootCamp : " + anno.bootCamp());
    }
}

// ...

@Retention(RetentionPolicy.RUNTIME)
@interface Master {
    String name();
    int age();
    Course course();
    String bootCamp() default "Code Squad";
}
```
#### 결과 : 

```
애너테이션 : @Master(bootCamp="Code Squad", name="정호영", age=45, course=BACKEND)
name : 정호영
age : 45
course : BACKEND
bootCamp : Code Squad
```

### @Retention

- 이 애너테이션을 언제까지 유지할 것인가?
- `@Retention`이 없으면 기본 전략은 CLASS
- SOURCE -> CLASS -> RUNTIME
- SOURCE : 컴파일하면 해당 애너테이션정보가 없어짐 (정말 주석), 바이트 코드까지 남아있지 않아 (ex: @Override)
    - CLASS, RUNTIME에서는 쓰이지 않는다.
    - 소스코드를 읽는 사람에게만 보인다.
- CLASS : 애너테이션을 .class 파일까지 남겨놓겠다.
    - 바이트코드에 남아있다.
    - 클래스 로더가 클래스 정보를 메모리에 적재시킬 때 해당 애너테이션을 누락시킨다.
    - 바이트코드에서 클래스정보를 읽어올 수 있다 **(Reflection X)**
    
- RUNTIME : 
    - 런타임 중에 클래스 정보를 볼 수 있다.
    - Reflection이 가능해진다. (클래스 정보를 반추, 반사해서 보는 것)

### @Target
- 애너테이션을 어디에 적용할 수 있는지를 지정할 때 사용한다.
- `@Target({TYPE, FIELD, PARAMTER})` 배열 형태로 여러개 지정가능, `@Target`을 명시하지 않으면 어디에나 적용할 수 있다.
- 지정위치 목록
```java
public enum ElementType {
    /** Class, interface (including annotation type), or enum declaration */
    TYPE,

    /** Field declaration (includes enum constants) */
    FIELD,

    /** Method declaration */
    METHOD,

    /** Formal parameter declaration */
    PARAMETER,

    /** Constructor declaration */
    CONSTRUCTOR,

    /** Local variable declaration */
    LOCAL_VARIABLE,

    /** Annotation type declaration */
    ANNOTATION_TYPE,

    /** Package declaration */
    PACKAGE,

    /**
     * Type parameter declaration
     *
     * @since 1.8
     */
    TYPE_PARAMETER,

    /**
     * Use of a type
     *
     * @since 1.8
     */
    TYPE_USE,

    /**
     * Module declaration.
     *
     * @since 9
     */
    MODULE
}
```

### @Documented
- javadoc으로 작성한 문서에 애너테이션을 포함시키려면 `@Documented`를 붙인다.
- `@Documented`가 있다면,
  
```java
@Fruit
public class Apple {

}

@Documented
@interface Fruit {

}
````

![nohave](https://user-images.githubusercontent.com/46085281/136376902-7e7b8d1e-139f-4158-a88b-3c2de802931b.png)

- `@Documented`가 없다면,

```java
@Fruit
public class Apple {

}

// @Documented
@interface Fruit {

}
````

![have](https://user-images.githubusercontent.com/46085281/136376905-f379f599-7107-44c5-bffa-86d4fd0ebfb5.png)

- 내친김에 javadoc도 만들어보자

### javadoc

- javadoc은 Java코드에서 API문서를 HTML 형식으로 생성해주는 도구이다.

```java
/**
 * This is the Human class.
 *
 * @since 2021.10.7
 * @author Shion
 */
public class Human {

    String name = "";

    /**
     * @param fruit to eat
     * @return only seed...
     * @throws IllegalArgumentException if no fruit param
     */
    public String eat(String fruit) throws IllegalArgumentException {
        System.out.println("eat " + fruit + "...");
        return "only seed...";
    }
}
```
![스크린샷 2021-10-07 오후 9 40 00](https://user-images.githubusercontent.com/46085281/136385736-5642d1b6-470b-49dc-9f26-a23d85f24e55.png)

![스크린샷 2021-10-07 오후 9 46 07](https://user-images.githubusercontent.com/46085281/136386730-2cab4df3-2da6-4816-8fc6-b5ff155b401e.png)


### 생성방법

- 명령어 : `javadoc -d [경로] [소스파일] 입력하면 해당 경로에 API문서 생성`

<img width="884" alt="스크린샷 2021-10-07 오후 9 31 12" src="https://user-images.githubusercontent.com/46085281/136384765-9f94239f-138c-4afb-95dd-8ff7192818a2.png">

<img width="881" alt="스크린샷 2021-10-07 오후 9 31 34" src="https://user-images.githubusercontent.com/46085281/136384912-9d9f206f-f74c-417a-9dd9-f6c3d6840bc0.png">

<img width="918" alt="스크린샷 2021-10-07 오후 9 32 57" src="https://user-images.githubusercontent.com/46085281/136384969-3bddbecf-60cb-49f1-8d7f-499594439414.png">

### 인텔리J : 

![942DE243-2CD0-4FB2-8E2A-D124A7A30871_1_105_c](https://user-images.githubusercontent.com/46085281/136387383-7af7fb75-b4a4-4fea-9cda-5f25b7988d67.jpeg)

<img width="572" alt="스크린샷 2021-10-07 오후 9 53 04" src="https://user-images.githubusercontent.com/46085281/136387811-f4ad3daf-e99c-47f7-95f6-3a87d421e665.png">

### 애너테이션 프로세서

- 롬복이 애너테이션 프로세서를 사용하는 대표적인 예이다.
```java
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
public class Human {

    String name = "shion";
}
```
- 위의 소스코드를 컴파일하고 `.class`파일에서 바이트코드를 확인하여 다시 소스코드로 만들어보면,
```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Human {
    String name = "shion";

    public Human() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String toString() {
        return "Human(name=" + this.getName() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Human)) {
            return false;
        } else {
            Human other = (Human)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Human;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $name = this.getName();
        int result = result * 59 + ($name == null ? 43 : $name.hashCode());
        return result;
    }
}
```
- 애너테이션만 붙였는데 바이트코드를 조작해서 소스코드를 수정할 수 있다.
- 롬복은 일종의 해킹이지만 널리 사용되고 있다.
