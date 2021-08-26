7주차 패키지

- package 키워드
- import 키워드
- 클래스패스
- CLASSPATH 환경변수
- -classpath 옵션
- 접근지시자

</br>



### package 키워드

- 패키지란 클래스의 묶음이다. 서로 관련된 클래스들끼리 그룹 단위로 묶어서 클래스를 효율적으로 관리할 수 있다. 

- 클래스의 FQCN(Fully Qualified Class Name) 은 패키지명까지 포함한 것이다.
  - String 클래스의 실제 이름은 java.lang.String 

- 소스파일의 첫 번째 문장에 패키지 선언을 한다. 한 소스파일단 한번의 선언을 허용한다.

  ```java
  package linkedList;
  
  public class LinkedList {
    ...
  }
  ```

  

- 모든 클래스는 하나의 패키지에 속해야한다.

  - 패키지를 지정하지 않은 클래스는 자동으로 이름 없는 패키지(unnamed package, default package)에 속하다. 

- 패키지는 `.` 을 구분자로하여 계층구조로 구성한다.

- 패키지는 물리적으로 클래스 파일(.class)을 포함하는 디렉토리이다. 


</br>

### import 키워드

- import문으로 <u>사용하고자 하는 다른 패키지의 클래스를 명시</u>해주면 소스코드에 사용되는 클래스 이름에서 패키지명은 생략할 수 있다.
- java.lang 패키지의 클래스들은 import문 없이 사용 가능하다. 

```java
import java.util.Arrays;
import java.util.Collections;

public class Exam {
...
}
```

- 위 예시의 두개의 import문은 `import java.util.*` 이렇게 하나의 문장으로 쓸 수 있다.

</br>


**static import**

- static import문은 static 멤버를 호출할 때 쓰인다 .
- `Math.random();`을 사용할 때 statim import 문을 사용하면 `random();` 이라고 쓸 수 있다.
  - `import static java.Math.random;`



</br>


### class path 클래스 패스

- 클래스나 패키지를 찾기 위한 경로
  - JVM이 프로그램을 실행할 때 클래스 파일을 찾는 데 기준이 되는 경로
  - Java runtime(java 또는 jre)로 .class 파일에 있는 명령을 실행하려면 이 파일을 찾아야 한다.
    - 이때 .class 파일을 찾을 때 classpath에 지정된 경로를 사용한다. 

- class path는 .class가 포함된 디렉토리와 파일을 `:` 으로 구분한 목록이다. 

</br>


**class path를 지정하는 방법**

- 환경 변수 CLASSPATH를 사용한다
- java runtime에 -classpath 플래그를 사용한다. 

</br>


### CLASSPATH 환경변수

- 클래스 패스를 **CLASSPATH 환경 변수**를 통해 설정할 수 있다.

</br>


### -classpath 옵션

- java 명령어를 실행 시 `-classpath` 혹은 `-cp`옵션 으로 클래스패스를 지정할 수 있다. 

  `java -cp /Users/yeon/test Hello` 명령어를 실행하면 어느 경로에서나 Hello클래스에 있는 명령을 실행할 수 있다.

![스크린샷 2021-08-26 오후 7 46 10](https://user-images.githubusercontent.com/65011131/131013606-25890f19-5282-4e4f-a608-567214abd8ca.png)


</br>


### 접근 제어자 

- 접근 제어자가 사용될 수 있는 곳 - 클래스, 멤버변수, 메서드, 생성자
- 캡슐화
  - 접근 제어자를 사용하는 이유는 클래스 내부에 선언된 데이터를 보호하기 위해서이다. 



- private : 같은 클래스 내에서만 접근 가능
- default: 같은 패키지 내에서만 접근 가능

- protected: 같은 패키지 내에서, 다른 패키지내의 자손 클래스에서 접근 가능
- public: 접근 제한이 없음 





</br>


참고: https://effectivesquid.tistory.com/entry/%EC%9E%90%EB%B0%94-%ED%81%B4%EB%9E%98%EC%8A%A4%ED%8C%A8%EC%8A%A4classpath%EB%9E%80 (class path)

자바의 정석

