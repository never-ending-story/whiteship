## 학습 1) package 키워드
### package란 
-	패키지란 관련된 클래스와 인터페이스를 묶어둔 namespace다. 
	-	파일들(클래스, 인터페이스) 담고 있는 폴더라고 생각하면 된다. 
-	패키지 사용 이유 
	-	동일 패키지 내 클래스, 인터페이스들이 관련되어있다는 것을 쉽게 파악할 수 있게 해준다.
    -	(패키지 내 접근만 가능하게 하는 등) 접근 제한을 할 수 있다. 
    -	namespace의 기능을 해서 다른 패키지의 클래스와 이름 충돌을 막아준다. 
    	-	클래스의 실제 이름(full name)은 패키지명을 포함한 것이다(unnamed package의 경우는 클래스 이름만)
        
> • The fully qualified name of a top level class or top level interface that is declared
in an unnamed package is the simple name of the class or interface.
• The fully qualified name of a top level class or top level interface that is declared
in a named package consists of the fully qualified name of the package, followed
by ".", followed by the simple name of the class or interface
    
-	패키지 예	
	-	java의 기본 클래스들은 java.lang에 있고 
	-	읽기, 쓰기(input, oupt)과 관련된 클래스는 java.io에 있다. 
	-	새로 만든 클래스를 package에 넣을 수 있다. 
### package 생성  
-	패키지를 생성하려면 
	-	패키지의 이름을 정하고 
    -	소스파일의 **맨 위에** 이름과 함께 package 키워드를 사용한다. 
```java
pacakge 패키지명; // pacakage com.honux.practiceapp.forlecture
```
-	하나의 소스 파일 내 단 한번만 선언 가능 
-	하나의 소스 파일 내에 여러개의 타입(클래스, 인터페이스)를 두려면 오직 하나의 타입만 public이어야 하고 그것은 소스파일 이름과 같아야 한다. 
	-	non-public 타입도 같은 파일 내에 따로 둘 수 있지만 (권장되지x, 해당 타입이 작고 public type에 밀접하게 연관되어 있을 경우에만) 패키지 밖에서 접근가능한 것은 오직 public. 
- 패키지 키워드를 사용하지 않는다면 unnamed package에 속하는 것이다. unnamed package는 오직 작고 임시적인 어플리케이션에서, develop 초기 단게에서만 사용하고 패키지 지정을 해줘야한다. 

### package 네이밍 
-	동일 클래스 이름이라도 다른 패키지에 속한다면, 동일 클래스명 - 다른 클래스 존재가 가능하다. 
	-	클래스의 full name은 패키지명을 포함하므로 
#### Naming Conventions
- 네이밍 컨벤션 
-	소문자로 작성해라 
	-	클래스, 인터페이스의 이름과 충돌을 막기 위해 
-	기업들은 그들의 도메인 이름의 역순으로 패키지 이름을 시작한다.
	-	도메인주소 : example.com
    -	패키지 이름 : mypackage
    -	패키지명 : com.example.mypackage 
-	도메인 이름이 유효하지 않은 패키지 이름인 경우 
	-	패키지 이름이 숫자로 시작하거나 java 키워드를 포함하는 경우 등에는 underscore을 사용하는 것이 좋다.
    	-	hi-name.exmple.org -> org.example.hi_name
        -	example.int -> int_.example
        -	123name.example.com -> com.example._123name
        
-	자바 언어 자체 안의 패키지는 java. 나 javax.로 시작한다. 
    `com.example.mypackage` 패키지 이름 :  
## 학습 2) import 키워드
- 같은 패키지내에서는 해당 클래스, 인터페이스의 이름으로 사용할 수 있다. 
- 다른 패키지의 public 패키지 멤버를 사용하려면 3가지 방법 중 하나 
-	1. fully qualified name으로 member 참조하기 
	-	패키지 이름을 포함하는 fully qualified name을 사용해야 한다. 
    -	ex. graphics의 패키지 외부에서 graphics패키지의 Rectangle을 사용하고 싶을 때 
    -	```  graphics.Rectangle myRect = new graphics.Rectangle();```
    -	이는 자주 사용되지 않는다. (반복적 호출 해야 되고, 가독성이 떨어진다)  
-	1번 방법이 자주 사용되지 않아 `import` 키워드를 사용한다. (2번 3번) 
### 패키지 멤버 하나씩 import 
-	2. package memeber import
	-	외부 패키지의 특정 멤버를 import하고자 할 때 
    -	import 구문을 소스파일 시작 부분(package statement 보다는 뒤에)에 작성한다. 
    -	해당 패키지의 여러 멤버를 import 할 때는 패키지 전체를 import 하는 것이 좋다 
```java
import graphics.Rectangle;
import graphics.Circle;
...

Rectangle myRectangle = new Rectangle();
```
### 패키지 전체 import 
-	3. 패키지 전체를 import 
	-	`*` 를 사용한다. ```import graphics.*;```
    -	클래스 내에 public nested class가 있을 때 이 nested 클래스를 가져올 때 import를 사용할 수 있다. 
    	-	![](https://images.velog.io/images/bongf/post/34e84e47-4bc2-4f83-80a6-4e7abd7e6c3f/image.png)
        -	위의 클래스의 내부 클래스를 import 하고 싶으면, `import bongf.week7.toimport.ClassForImport.*;` 이렇게 가져올 수 있다. 
        -	![](https://images.velog.io/images/bongf/post/d3b58d91-3969-439f-a69a-e6ce6cfd8bfb/image.png)
        -	그러나 `import bongf.week7.toimport.ClassForImport.*;` 로 ClassForImport 클래스 자체는 가져오지 못한다. 또 import 해줘야 한다. 
        -	![](https://images.velog.io/images/bongf/post/cc57685a-7528-4199-9df9-3b89f03170ac/image.png) ![](https://images.velog.io/images/bongf/post/dfe59b4a-0f57-4728-a119-a96b33cd4423/image.png)
        -	해당 패키지 전체를 import 하면 해당 클래스는 가져오나 public nested class는 가져오지 못한다. 
        ![](https://images.velog.io/images/bongf/post/c9fb7a3f-99bb-41a6-b145-dcdf9a177f72/image.png)
	-	편의를 위해 Java 컴파일러는 (1)  java.lang (2) 현재 패키지 이 두가지를 자동으로 import 한다. 
### Static Import 
-	상수(static final 필드)나 static 메소드를 자주 사용해서 클래스를 계속해서 호출하고 싶지 않을 때 사용 (static import 하면 클래스 이름 없이 사용 가능) 
-	java.lang.Math에 PI 상수와 cos라는 static 메소드가 있다. 
```java
public static final double PI 
    = 3.141592653589793;
public static double cos(double a)
{
    ...
}
```
-	다른 패키지에서 이를 호출할 때 static import를 사용하면 Math라는 클래스 이름을 호출하지 않아도 사용 가능 
```java
import static java.lang.Math.*;

double r = cos(PI * theta);
```
-	static import를 너무 많이 사용하면 코드 가독성이 떨어지거나 유지보수가 어려워질 수 있다. (어떤 클래스에서 왔는지 몰라서) 적절히 사용하자. 

## 학습 3) 클래스패스
-	JVM 또는 Java 컴파일러가 사용자가 정의한 클래스와 패키지의 위치를 특정하기 위한 파라미터 
-	command-line이나 환경변수로 클래스 패스를 지정해야 한다.  
	-	command-line으로 호출한 예(윈도우) 
```
set CLASSPATH=D:\myprogram
java org.mypackage.HelloWorld
```
## 학습 4) CLASSPATH 환경변수
-	클래스의 경로를 CLASSPATH에 설정하는 방법.
-	루트디렉토리를 클래스패스에 포함시켜야 실행시 모든 클래스 찾을 수 있다. 
-	윈도우 : 제어판 -시스템-고급시스템설정-환경변수-새로만들기에서 변수이름을 'CLASSPATH'를 입력하고 변수값에는 해당 루트의 경로 입력(자세한 사항 자바의 정석)
	-	';'를 구분자로 여러 경로를 클래스패스에 지정 가능
-	클래스 패스를 지정해주지 않으면 기본적으로 현재 디렉토리(.)가 클래스패스로 지정 
-	클래스 패스를 따로 지정해준다면 현재 디렉토리가 자동적으로 클래스 패스로 지정되지 않아 별도로 추가 해야 함 

## 학습 5) -classpath 옵션
-	실행할 때 -classpath 옵션(-cp)으로 클래스 패스 지정 가능 
```
java -classpath C:\java\MyClasses\myclasses.jar utility.myapp.Cool
```
-	여러 개 일 때 
```java
java -classpath C:\java\MyClasses;C:\java\OtherClasses ...
```
## 학습 6) 접근지시자
| 접근제어자 |	클래스내부 | 패키지내부 | 패키지 외부는 자손 클래스만 | 전체 |
|---|---|---|---|---|
| public | o | o | o | o |
| protected | o | o | o | x |
| default (package private) | o | o | x | x |
| private | o | x | x | x |

- 다른 클래스에서 클래스 또는 멤버에 접근할 때 제어 하는 역할 
-	접근 제어자가 없다면 default 


---
### 출처 
-	학습 1 & 2 
	-	자바의 정석 
	-	https://docs.oracle.com/javase/tutorial/java/concepts/package.html
    -	https://docs.oracle.com/javase/tutorial/java/package/index.html
    -	클래스의 이름은 패키지 명을 포함한다. 
    	-	https://docs.oracle.com/javase/specs/jls/se8/jls8.pdf 191/788
        -	자바의 정석 
-	학습 3 
	-	https://en.wikipedia.org/wiki/Classpath
-	학습 4
	-	자바의 정석 
-	학습 5 
	-	https://docs.oracle.com/javase/7/docs/technotes/tools/windows/classpath.html
-	학습 6
	-	자바의 정석 
    -	https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html
