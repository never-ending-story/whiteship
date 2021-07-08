#### JVM은 무엇이며 자바코드는 어떻게 실행하는 것인가

목차

- JVM이란? JVM 구성 요소와 역할
- 자바 파일의 컴파일과 실행하는 방법

- JIT compiler란?
- 바이트 코드란?
- JDK과 JRE의 차이

##### JVM이란?

Java Virtual Machine

Java 어플리케이션을 실행시키는 실행 엔진(run-time engine)

자바 코드에 있는 main메소드를 실행한다.

WORA: Write Once Run Anywhere

- 자바 코드를 하나의 시스템에서 개발하고, 어느 환경(Java-enabled 한)에서든 추가적인 변경없이 실행가능하다.

- <u>자바 어플리케이션</u>은 JVM하고만 상호작용해서 OS와 하드웨어에 독립적이다.
    - 다른 OS에서도 실행 가능
    - <u>JVM</u>은 <u>OS에 종속적</u>이여서 해당 OS에서 실행 가능한 JVM이 필요하다.



**자바 파일(.java)을 complie 하면?**

컴파일러가 .class 파일(바이트 코드를 포함한)을 생성한다.

여러 단계를 거치면서 .class 파일이 실행된다. (아래에 JVM architecture와 함께 여러 단계에 대해 설명)



<img width="535" alt="스크린샷 2021-07-08 오후 3 14 42" src="https://user-images.githubusercontent.com/65011131/124962482-a4692f00-e059-11eb-9a79-030b8c6a0b1e.png">


##### ClassLoader Subsystem

클래스 로더는 Loading -> Linking -> Initializtion 세가지 동작을 수행한다.

.class 파일을 실행하면 class loader가 .class 파일을 메모리에 로딩한다.

동적으로 클래스 로딩 가능 -> 모든 파일을 로딩하는 것이 아니라 필요할 때 로딩한다.

1. Loading

   클래스 로더가 .class 파일을 읽는 단계

   상응하는 이진 데이터(binary data)를 생성하고, JVM은 각각의 .class 파일의 정보를 method area(메소드 영역)에 저장한다. 다음은 JVM이 메소드 영역에 저장하는 .class 파일의 정보들이다.

    - 패키지명까지 포함된 클래스명과 *이것의 직속 부모 클래스(immediate parent class...?)*
    - .class 파일이 Class 인지, Interface인지, Enum인지에 대한 정보
    - 제어자(접근제어자를 포함한 static, final, abstract, synchronized와 같은 키워드가 해당), 변수, 메소드 등의 정보

Loading 과정 이후에 JVM은  Class들의 객체를 생성한다. (한 클래스당 하나의 객체만 생성한다.)

이 객체는 java.lang 패키지에 사전 정의된 Class 유형의 객체이다.

이 객체는 자바 코드 작성 시, 클래스나 메소드, 변수 등의 정보를 얻을 때도 사용된다. 아래에 이에 대한 예시 코드이다.

```java
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args)
	{
		Student s1 = new Student();

		// 이 프로그램을 실행하면 Loading 과정 이후에 JVM이 생성한 Student Class의 객체를 통해 s1의 클래스 정보를 얻어 올 수 있다.
		Class c1 = s1.getClass();

		System.out.println(c1.getName());

		// s1에 해당하는 클래스에 정의된 모든 메소드를 가져온다.
		Method m[] = c1.getDeclaredMethods();
		for (Method method : m)
			System.out.println(method.getName());

		// s1에 해당하는 클래스에 정의된 모든 변수를 가져온다.
		Field f[] = c1.getDeclaredFields();
		for (Field field : f)
			System.out.println(field.getName());
	}
}

class Student {
	private String name;
	private int roll_No;

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getRoll_no() { return roll_No; }
	public void setRoll_no(int roll_no)
	{
		this.roll_No = roll_no;
	}
}

```

```
Student
getName
setName
getRoll_no
setRoll_no
name
roll_No
```



2. Linking

   Linking 단계에서는 verification(코드 검증), preparation, resolution(optionally) 의 과정을 수행한다.

    - Verification

      .class 파일의 정확성, 그리고 유효한 컴파일러에 의해 생성되고 올바르게 format되었는지 검사한다.

      이 과정이 실패하면 java.lang.VerifyError가 발생한다.

      ByteCodeVerifier 에 의해 이 과정이 수행된다.

    - Preparation

      변수들이 메모리에 할당되고, 기본값으로 초기화된다.

    - Resolution

      symbolic references를 direct references(original references)로 변경하는 과정 수행



3. Initialization

   static 변수들이 정의될 때 대입된 값, 혹은 static block에서 대입된 값으로 배정받는다.



##### JVM Memory

1. Method area(메소드 영역)

   클래스명, 직속 부모 클래스명(immediate parent class name), 메소드와 변수 등의 class level 정보와 static 변수가 저장된다.

   JVM 하나 당 하나의 메소드 영역이 있다.

   공유되는 자원이다.

2. Heap area

   모든 객체들의 정보가 저장된다.

   힙 영역 역시, JVM 하나 당 하나의 힙 영역이 있다.

   이 또한 공유되는 자원이다.

3. Stack area

   모든 스레드마다, JVM은 하나의 run-time stack을 스택 영역에 생성한다.

   이 스택의 모든 블럭은 메소드의 호출을 저장하는 활성화 기록(스택) 프레임 이라고 불린다.

   모든 로컬 변수는 각각에 상응하는 프레임에 저장되고

   해당 스레드가 종료되면 JVM은 해당하는 run-time stack을 제거한다.

4. PC Registers (PC: Program Counter)

   스레드의 현재 실행중인 instruction(지시)를 가르키는 주소를 저장한다.

   각각의 스레드는 별도의 PC Resgister를 갖고 있다.

5. Native method stacks

   모든 스레드마다 별도의 native stack이 생성된다.

   네이티브 메소드 정보가 저장된다.



##### Execution Engine

Execution Engine은 .class 파일(바이트 코드)를 실행한다.

바이트 코드를 한줄 한줄 읽으면서 여러 메모리 영역에 있는 데이터와 정보를 이용하여 실행한다.

Execution Engine은 아래의 세가지 파트로 분류되어  있다.

1. Intertrepter

   바이트 코드를 한줄 한줄 기계어로 변역하고, 이를 실행한다.

   **문제점**: 하나의 메소드가 여러번 실행될 때마다 이 과정이 필요하다.

2. JIT

   Just-In-Time Compiler

   인터프리터의 효율을 높이기 위해 사용된다.

   모든 바이트 코드를 컴파일하고, 기계어로 바꾼다. 인터프리터가 반복된 메소드 호출을 마주하면, JIT이 바로 해당하는 부분의 기계어를 제공하여 Interpreter 가 수행하는 반복적인 기계어 번역 과정을 하지 않아도 되어서 효율성이 높아진다.

3. Garbage Collector

   참조되지 않는 객체를 제거한다.



##### Java Native Interface (JNI)

JNI는 원시 메소드 라이브러리와 상호작용한다.

Execution Engine이 요구하는 원시 라이브러리(C, C++)를 제공한다.

JVM이 C/C++ 라이브러리를 호출할 수 있도록 해준다.



**Native Method Libraries**

Execution Engine에 의해 요구되는 Native Libararies(C, C++)의 컬렉션



참고: https://www.geeksforgeeks.org/jvm-works-jvm-architecture/

https://dzone.com/articles/jvm-architecture-explained

[예전 내 글](https://velog.io/@yeon/TIL-2월-16일)





