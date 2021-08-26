## 패키지

### 1. package 키워드

- 패키지란, 클래스의 묶음이다. 
- 패키지에는 클래스, 인터페이스, ENUM, 어노테이션을 포함시킬 수 있다.
- 클래스의 실제 이름(FQCN : Full Qualified Class Name)은 패키지명을 포함한 것이다.
    - String 클래스 -> java.lang.String (FQCN)
- 클래스 파일들을 압축한 것이 jar파일(*.jar)이다.

### 2. import 키워드

- import문으로 사용하고자 하는 클래스의 패키지를 미리 명시해주면 소스코드에 사용되는 클래스이름에서 패키지 명은 생략할 수 있다.
- import문의 역할은 컴파일러에게 소스파일에 사용된 클래스의 패키지에 대한 정보를 제공하는 것.
- ' 패키지명.* '을 이용해서 지정된 패키지에 속하는 모든 클래스를 패키지명 없이 사용할 수 있다.

```java
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

// import java.util.*; 로 한번에 처리 가능하다.
```

- ' * '을 사용하면, 컴파일시 해당 패키지를 모두 검색하는 수고를 하지만, 실행 시 성능상의 차이는 없다.

---

**static import**

- static import 문을 사용하면 static 멤버를 호출할 때 클래스 이름을 생략할 수 있다.

```java
import static java.lang.Integer.*;   // Integer클래스의 모든 static메서드
import static java.lang.Math.random; // Math.random()만. 괄호 안붙임.
import static java.lang.System.out;  // System.out을 out만으로 참조 가능

System.out.println(Math.random()); -> out.println(random());
```

### 3. 클래스패스

- 컴파일러(javac.exe)나 JVM 등이 클래스의 위치를 찾기 위해 폴더를 검색하는데, 클래스패스를 통해서 특정 폴더를 검색하도록 지정할 수 있다. 

### 4. CLASSPATH 환경변수

- CLASSPATH 환경변수를 통해서 위에서 설명한 클래스패스 지정할 수 있다.
```
환경변수는 운영체제에 지정하는 변수로 자바 가상머신과 같은 애플리케이션들은 환경변수의 값을 참고해서 동작하게 된다. 
자바는 클래스 패스로 환경변수 CLASSPATH를 사용하는데 이 값을 지정하면 실행할 때마다 -classpath 옵션을 사용하지 않아도 되기 때문에 편리하다. 
하지만 운영체제를 변경하면 클래스 패스가 사라지기 때문에 이식성면에서 불리할 수 있다.
```

### 5. -classpath 옵션

- 자바를 실행할 때, 사용할 클래스를 JVM에게 알려주는 옵션이다.

```
~ java -classpath .:/Users/shion/desktop Hello 
```
- `.` : 현재 디렉토리 위치
- `:` : and (Windows는 `;`)
- `/Users/shion/desktop` : 클래스패스로 지정한 위치
- `Hello` : Hello.class 실행
- 클래스 패스를 지정해 주지 않으면 기본적으로 현재 디렉토리(.)가 클래스패스로 지정된다.
- 그러나 다른 클래스패스를 지정하게 되면 더 이상 현재 디렉토리가 자동적으로 클래스패스로 지정되지 않기 때문에 별도로 추가해주어야 한다.

### 6. 접근지시자

- 멤버 또는 클래스에 사용하며, 해당하는 멤버 또는 클래스를 외부에서 접근하지 못하도록 제한하는 역할을 한다.

```
 private    같은 클래스 내에서만 접근이 가능하다.
(default)   같은 패키지 내에서만 접근이 가능하다. (package-private)
 protected  같은 패키지 내에서, 그리고 다른 패키지의 자손클래스에서 접근이 가능하다.
 public     접근 제한이 없다.
```

- 메서드 하나를 변경해야 한다고 가정했을 때, 이 메서드의 접근 제어자가 public이라면, 메서드를 변경한 후에 오류가 없는지 테스트해야하는 범위가 넓다.
- 그러나 default라면 패키지 내부만 확인해 보면 되고, private이면 클래스 하나만 살펴보면 된다.
- 이처럼 접근 제어자 하나가 때로는 상당한 차이를 만들어낼 수 있으므로 접근제어자를 적절히 선택해서 접근 법위를 최소화하도록 노력하자.

---

**생성자의 접근 지시자**

- 생성자에 접근 지시자를 사용함으로써 인스턴스의 생성을 제한할 수 있다.

```java
class Singleton {
    
    private static Singleton s = new Singleton();

    private Singleton() { 
        // 외부에서 인스턴스를 생성할 수 없다.
    }

    // 인스턴스를 생성하지 않고도 호출할 수 있엉 하므로 static이어야 한다.
    public static Singleton getInstance() {
        return s;
    }
}
```

- 생성자가 private이면 자손 클래스에서 `super();`를 호출할 수 없기 때문에 조상클래스가 될 수 없다.
- 그래서 지난 시간에 배웠던 `final` 키워드를 클래스 앞에 추가하여 상속할 수 없는 클래스라는 것을 알리는 것이 좋다.
- 자바에서 제공하는 `Math`클래스를 살펴보자.

```java
public final class Math {

    /**
     * Don't let anyone instantiate this class.
     */
    private Math() {}

    // ...
}
```
