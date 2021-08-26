### package 키워드

자바에서 패키지는 폴더의 개념처럼 여러 타입(클래스, 인터페이스, enums and annotations)들을 분리할 때 사용된다. 패키지는 이 타입들의 집합이라고도 볼 수 있다.

`package` 키워드는 패키지를 선언할 떄 사용되고 꼭 하나만 있어야 한다. 상, 하위 패키지는 `.`으로 구분되어 관리할 수 있지만 한번에 여러 패키지에 해당할 수 없다. 패키지 선언은 소스의 제일 첫 줄에 위치해야 한다. 이렇게 선언된 패키지와 타입명을 합치면 하나의 클래스 명을 표현할 수 있다. 이를 FQCN, Fully Qualified Class Name이라고 한다.

```java
package java.lang;

public final class String {...} // String의 FQCN은 java.lang.String
```

패키지 명에는 소문자를 사용하고 예약어의 사용을 금하는 컨벤션이 있다.

### import 키워드

다른 패키지에 속한 클래스를 쓰고 싶을 때 `import`를 이용해 '가져온다'. 특정 클래스 하나만 가져오고 싶다면 `import` 키워드 이후에 FQCN을 써주면 되지만 특정 패키지 안에 있는 모든 클래스를 불러오고 싶을 때에는 `import package.*;`로 가져올 수 있다. static한 변수나 메서드를 사용할 때에는 `import static` 를 사용할 수 있고 클래스 명을 생략할 수 있게 된다.

자바에서 제일 기본적으로 쓰이는 패키지가 `java.lang`이다. 이 패키지는 따로 import하지 않아도 되는데 이는 제일 많이 쓰이는 패키지이므로 자바에서 알아서 불러오기 때문이다.

### 클래스패스

클래스패스는 JVM이 프로그램을 실행할 때 class 파일의 경로를 찾기 위해 쓰인다. java 파일들은 루트 패키지에 `com.example.graphics.Rectangle` 의 형식으로 위치하면 해당 java 파일은 ~/../com/example/graphics/Rectangle.java에 저장된다. 컴파일된 후에 생성된 클래스 파일들은 자바 파일들과 마찬가지로 com 이후에는 동일한 디렉토리에 저장되지만 클래스 파일이 저장되는 곳은 com 이전의 디렉토리를 클래스패스라고 한다.

```
<path_one>\sources\com\example\graphics\Rectangle.java

<path_two>\classes\com\example\graphics\Rectangle.class 
// <path_two>\classes에 해당하는 부분이 클래스패스
```

### CLASSPATH 환경변수

JVM이 시작될 때 클래스 로더는 환경변수를 호출해 여기에 설정되어있는 디렉토리를 불러오고 여기에 있는 클래스들을 먼저 로드한다. 맥에서 클래스패스는 아래와 같이 지정할 수 있다.

1. ~/.bash_profile 이 있는 지 확인
2. vi ~/.bash_profile
3. `Export PATH=${PATH}:$JAVA_HOME/bin` 이건 이미 있는 옵션이고 콜론 이후의 값을 바꿔주면 된다.

### -classpath 옵션

자바를 `java`나 `javac`등의 명령어로 실행시켜줄 때 `-classpath`나 `-cp` 옵션을 사용하면 해당 실행 시 클래스패스를 따로 지정해 줄 수 있다. 해당 옵션은 클래스패스 환경변수보다 높은 우선순위를 가지게 된다.

```
java -classpath /users/sally/workspace Rectangle.class
```

### 접근지시자

접근 제어자는 다른 클래스로 하여금 해당 클래스/필드가 어디까지 접근이 가능한지를 알려준다.

- public: 어느곳에서나 사용 가능
- protected: 상속받은 클래스에 사용 가능하게 만드는 게 목적이지만 같은 패키지 내에서도 사용 가능하다.
- default: 같은 패키지 안에서만 사용할 수 있다. 패키지 프라이빗이라고도 한다. 이름 따라 따로 default라고 명시해주지 않아도 된다(생략 가능)
- private: 클래스 내부에서만 사용 가능

### Reference

- https://docs.oracle.com/javase/tutorial/java/package/managingfiles.html
- https://kils-log-of-develop.tistory.com/430#%ED%81%B4%EB%9E%98%EC%8A%A4%ED%8C%A8%EC%8A%A4