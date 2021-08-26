# 패키지

## package 키워드

 #### 패키지란?

* 파일을 폴더에 모아 정리하듯, 자바 패키지 또한 비슷한 특징을 가지고 있는 자바 클래스 파일들을 모아 넣는 자바의 디렉토리다.

* 자바 소스 파일 내에 있는 패키지 클래스들을 사용하기 위해서는 import 선언과 함께 클래스들을 가지고 오는 것이 편리하다.
  packge나 import키워드는 class보다도 먼저 선언된다.

* 예로, UserController 와 MenuController라는 각각의 클래스파일이 있을 때, controller 라는 이름의 패키지로 분리한다면 좀 더 편리하게 사용할 수 있을 것이다.

  ```java
  package starve.controller;
  
  public class UserController {
    
  }
  ```

  

  ```java
  package starve.controller;
  
  public class MenuController {
    
  }
  ```

  두 클래스 모두 package라는 키워드를 사용하며, 위와 같은 패키지 지정 시 자바 파일이 담겨있는 디렉토리 내에 같은 구조로 파일들이 생성되어있을 것이다.
  `src/starve/controller/UserController.java`

  `src/starve/controller/MenuController.java`

  `bin/starve/controller/UserController.java`

  `bin/starve/controller/MenuController.java`



#### 패키지 사용 시 장점

* 클래스의 분류가 용이하다.
* 패키지가 다를 경우, 동일한 클래스 명을 사용할 수 있다.(딱히 권장되진않는다.)



#### 서브 패키지

* 위처럼 패키지가 지정 되어 있을 때, 그 밑에 하위 패키지를 계속해서 생성할 수 있는데, 이를 서브패키지라고 한다.

  > starve.controller.user패키지는 starve.controller패키지의 서브 패키지이다.



#### 자바 SE 8의 코어 패키지

- java.util
- java.io
- java.nio
- java.net
- java.security
- java.sql
- java.awt
- javax.swing
- java.beans
- java.text
- java.rmi
- java.time



## import 키워드

* 사용하고자 하는 패키지가 아닌 클래스에서 UserController를 사용하고자 할 때 패키지를 import 키워드를 통해 가지고 올 수 있다.

  ```java
  import starve.controller.UserController;
  ```

  만일 해당 패키지의 모든 클래스 파일을 가지고 오고 싶을 경우엔 `*` 기호를 사용할 수 있다.

  ```java
  import starve.controller.*;
  ```

  동일한 패키지에 있는 경우엔 따로 import를 하지 않아도 사용이 가능하다.

* java.lang 패키지는 import 문을 사용하지 않고도 사용이 가능하다.

* static 메소드를 지원하는 패키지 또한 `static import (경로)`를 사용하여 좀 더 쉬운 접근이 가능하다.
  (단, 해당 클래스에 동일한 이름의 메서드가 있다면, 그 메서드가 우선된다.)



## 클래스패스

* JVM이나 Java 컴파일러에 사용자 정의 클래스와 패키지의 위치를 지정해주는 파라미터.

* java runtime 실행 시, 컴파일 된 .class파일에 포함된 명령들을 실행할 때 .class파일을 찾는 것이 선행되어야한다.
  일반적인 경우, 즉 class파일이 존재하는 위치에서 실행할 경우에는 별도의 파일 경로를 입력해주지 않아도 되지만, class파일이 존재하지 않는 위치에서 class파일을 실행시키고자 한다면 이 경로를 입력해줘야 한다.

  바로 이 파일 경로가 클래스패스이다.

* 클래스 패스를 지정하는 방법

  * 환경 변수 CLASSPATH를 사용한다.
  * java runtime 시, -classpath 플래그를 사용한다.
    

## CLASSPATH 환경변수

* 세미콜론 또는 콜론으로 구분된 디렉토리 및 파일목록이며, classpath에 지정할 수 있는 유형은 디렉토리, zip파일, jar파일이 있다.

* 클래스패스는 `echo $CLASSPATH` 로 확인 할 수 있으며, 클래스 패스가 지정되어있지 않을 시 공백으로 출력된다.

  > `export CLASSPATH=.:/Users/starve/develop:/Users/starve/java:...:경로...`
  >
  > 경로의 맨 앞에 붙어있는 '.'의 의미는 현재 경로 또한 살펴보라는 의미이며, '.'이 붙어있지 않다면 자바 컴파일러는 명시된 경로만 탐색하게 된다.
  > 또, ':'로 경로를 구분하기 때문에 위의 클래스패스를 해석하자면, 
  >
  > * 현재 위치'.'
  > * /Users/starve/develop
  > * /Users/starve/java
  >
  > 위의 경로에 있는 클래스 파일을 찾아달라는 의미가 된다.

* 클래스패스를 지우려면 `unset CLASSPATH; export CLASSPATH` 을 입력하여 지울 수 있다.

* 클래스패스를 지정하려면, `CLASSPATH=/home/java/classes; export CLASSPATH` 를 입력하여 지정할 수 있다.

## -classpath 옵션

* 약어로 `-cp`가 사용된다.

* javac, java를 이용하여 실행 할 때 -classpath 옵션을 사용하게 된다.
  환경변수를 시스템에 설정하지 않고 클래스 패스를 지정하는 방식이다.
  (클래스패스가 등록되어도 위 옵션이 대체하게 된다.)

  

## 접근지시자

* 접근 제한자라고도 한다.
* 프로그램 개발에서 예상 외의 필드 참조나 메서드의 호출로 인한 버그가 발생할 수 있는데, 이를 방지하기 위해 클래스나 변수, 메서드의 사용 범위를 접근 제한자를 지정하여 제한하는 것이다.

| 접근 지정자    | 접근 범위                           | 동일 클래스 | 동일 패키지 | 다른 패키지의 자식 클래스 | 다른 패키지 |
| -------------- | ----------------------------------- | ----------- | ----------- | ------------------------- | ----------- |
| public         | 접근 제한 없음                      | O           | O           | O                         | O           |
| protected      | 동일 패키지와 상속 받은 클래스 내부 | O           | O           | O                         |             |
| default (기본) | 동일 패키지 내에서만                | O           | O           |                           |             |
| private        | 동일 클래스 내에서만                | O           |             |                           |             |

* 캡슐화를 하기 위해, 일반적으로 클래스 내의 필드들은 private으로 제작되며, 외부에서 간접적으로라도 값에 접근할 수 있도록 getter와 setter라는 것을 사용하게 된다.
  getter : `get변수이름()` 과 같은 방식으로 사용된다.

  setter : `set변수이름(변수)` 와 같은 방식으로 사용된다.

  하지만 setter는 객체지향에 어긋나는 경우가 많기 때문에 주로 getter만 사용되는 편이다.

