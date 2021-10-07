## 학습 1) 애노테이션 정의하는 방법
### 애노테이션 등장 배경 
-	애노테이션 등장 배경 : 
	-	자바의 정석 
    -	소스코드에 대한 문서를 따로 관리하면 문서 업데이트가 잘 안되어 소스코드에 대한 문서를 소스코드에 작성했다. 
    	-	주석 `/**~**/` 에 소스코드에 대한 정보를 저장하고, 소스코드의 주석으로부터 HTML을 생성해내는 프로그램(javadoc.exe)을 만들어서 사용했다. 
        -	인텔리제이로 javadoc 만드는 법 https://parkadd.tistory.com/54

### 애노테이션이란? 
https://docs.oracle.com/javase/tutorial/java/annotations/
-	메타데이터의 한 형태인 `애노테이션`은 프로그램의 일부가 아닌 다른 프로그램에게 데이터를 제공한다. 
	-	자바의 정석 : 프로그램의 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨 것 
    -	메타데이터 : 데이터에 대한 데이터 
-	애노테이션은 그들이 주석을 다는 코드의 동작에 어떤 영향도 미치지 않는다. 
	-	자바의 정석 : 주석처럼 프로그래밍 언어에 영향을 미치지 않으면서도 다른 프로그램에게 유용한 정보 제공 가능 
    -	[백기선님 강의 정리 블로그](https://www.notion.so/37d183f38389426d9700453f00253532) : 애노테이션에 동적으로 실행되는 코드는 들어가지 않는다. 즉 런타임 중에 알아내야 하는 값은 못 들어간다. 동적으로 런타임 중에 바뀌어야 하는 것들은 애노테이션에 사용될 수 없다. 
    
-	애노노테이션 예시 :`@Test` ![](https://images.velog.io/images/bongf/post/00a86aeb-b3c9-471d-911d-31f50aa9eb6a/image.png)
	-	이 메서드를 테스트 해야 한다는 것을 테스트 프로그램에게 알리는 역할을 할 뿐 메서드가 포함된 프로그램 자체에는 영향을 미치지 않는다. 
    	-	@Test는 테스트 프로그램을 제외한 다른 프로그램에게는 아무런 의미가 없다. 

	-	해당 프로그램에 이미 정의된 종류와 형식으로 작성해야만 의미가 있다. 

### 애노테이션 용도 
https://docs.oracle.com/javase/tutorial/java/annotations/
-	애노테이션의 용도 
	-	컴파일러를 위한 정보 제공 
    	-	애노테이션은 컴파일러가 에러를 잡거나 경고를 억압하기 위해(경고를 안띄우기 위해) 사용될 수 있다. 
        -	자바의 정석 : JDK에서 제공하는 표준 애노테이션은 주로 컴파일러를 위한 것. 
    -	컴파일타임과 개발타임의 processing (처리) : 소프트웨어 툴은 애노테이션 정보를 이용해서 XML 파일 같은 코드를 생성할 수 있다. 
    -	런타임 프로세싱 
    	-	몇몇 애노테이션은 런타임에 검사될 수 있다. 
        
### 애노테이션 기본 
https://docs.oracle.com/javase/tutorial/java/annotations/basics.html
#### 애노테이션의 형태 
-	기본 : `@Entity`
-	`@`는 컴파일러에게 주석임을 나타낸다 
-	애노테이션은 특정 요소를 포함할 수 있다. (named, unnamed 다 가능), 그리고 그 요소들에 대한 값(values)를 포함한다. 
``` java
@Author(
   name = "Benjamin Franklin",
   date = "3/27/2003"
)
class MyClass { ... }
```
```java 
@SuppressWarnings(value = "unchecked")
void myMethod() { ... }
```
-	value가 하나라면 이름은 생략될 수 있다. 
```java
@SuppressWarnings("unchecked")
void myMethod() { ... }
```
-	(자바의정석) 요소의 타입이 배열(여러 개)일 때도 요소의 이름이 value이면 요소의 이름을 생략할 수 있다. 
```java
@inteface SuppressWarnings {
	String[] value();
}

@SuppressWarnings({"deprecation", "unchecked"})
void myMethod() { ... }
```
-	포함하는 요소가 없다면 괄호를 생략할 수 있다. 
```java
@Override
void mySuperMethod() { ... }
```
-	동일한 선언에 여러 개의 애노테이션 붙이는 것도 가능하다. 
```java
@Author(name = "Jane Doe")
@EBook
class MyClass { ... }
```
-	똑같은 애노테이션 타임이 여러 개 붙었다면 그것은 [repeating annotation](https://docs.oracle.com/javase/tutorial/java/annotations/repeating.html)이라고 한다(Java 8부터지원) 
##### 값이 여러 개일 때 
-	자바의정석
-	요소의 타입이 배열인 경우 괄호 {} 를 사용해서 여러 개의 값을 지정할 수 있다. 
```java
@interface TestInfo{
	String[] testTools();
}

@TestInfo(testTools={"JUnit", "AutoTester"})
@TestInfo(testTools="JUnit") //  값이 하나일때는 {} 생략 가능 
@TestInfo(testTools={}) // 값이 없을 때는 {} 생략 불가 
```
-	디폴트 값을 지정할 때도 {} 사용 
```java
@interface TestInfo{
	String[] testTools() default {"JUnit", "AutoTester"});
    String[] testTools2() default "JUnit"; //값이 하나일때는 {} 생략 가능 
}

@TestInfo // @TestInfo(testTools={"JUnit", "AutoTester"}, testTools2="JUnit") 과 동일 
@TestInfor(testTools={}) //  @TestInfo(testTools={"JUnit", "AutoTester"}, testTools2={}) 과 동일 
class NewClass {
}
```

-	애노테이션 타입은 java.lang 또는 java.lang.annotaion 패키지에 정의된 타입 중 한 가지가 될 수 있다. 
-	필요에 따라 annotation 타입을 정의하는 것도 가능하다. (위의 예에서 @Author(name = "Jane Doe") @EBook) 
#### 애노테이션 어디에 사용 될 수 있을까?
-	선언에 사용될 수 있다. 
	-	클래스, 필드, 메소드 또는 다른 프로그램 요소들의 선언에 사용될 수 있다. 
    -	선언에 사용될 경우 관례적으로 그 자체가 한 행을 갖는다. 

-	Java8부터 애노테이션은 타입을 사용할 때 적용될 수 있다. 
	-	클래스 인스턴스를 생성할 때 
    ```java
    new @Interned MyObject();
    ```
    -	타입 캐스트 
    ```java
      myString = (@NonNull String) str;
    ```
    -	구현 구문 
    ```java
     class UnmodifiableList<T> implements
            @Readonly List<@Readonly T> { ... }

    ```
    -	던져진 예외 선언에서 
    ```java
      void monitorTemperature() throws
        @Critical TemperatureException { ... }
    ```
### 애노테이션 정의하기
https://docs.oracle.com/javase/tutorial/java/annotations/declaring.html
-	자바의 정석 
```java
@interface 애노테이션이름 {
	타입 요소이름(); // 애노테이션의 요소를 선언한다. 
}
```


-	인터페이스 정의와 비슷하다. 키워드 `interface` 앞에 `@`(AT)이 붙는 것을 제외하고. 
-	애노테이션은 인터페이스의 한 형태다. 


-	만약 모든 클래스에 아래와 같은 정보를 제공해야 한다고 할 때 
```java
public class Generation3List extends Generation2List {

   // Author: John Doe
   // Date: 3/17/2002
   // Current revision: 6
   // Last modified: 4/12/2004
   // By: Jane Doe
   // Reviewers: Alice, Bill, Cindy

   // class code goes here

}
```
-	이를 애노테이션으로 바꿀 수 있는데, 어노테이션으로 제공하려면 우선 애노테이션 타입을 정의해야 한다. 
```java
@interface ClassPreamble {
   String author();
   String date();
   int currentRevision() default 1;
   String lastModified() default "N/A";
   String lastModifiedBy() default "N/A";
   // Note use of array
   String[] reviewers();
}
```
-	애노테이션의 body에 어노테이션 요소를 작성한다. 
	-	반환값이 있고, 매개변수는 없는 추상메서드의 형태
    -	디폴트 값을 포함할 수 있다. 위에서 default 1 
	-	자신이 아닌 다른 애노테이이션 타입을 포함할 수 있다. (위의 datetime) 
    -	enum도 포함될 수 있다. (String, enum, 어노테이션, Class만 허용) 
    ```java 자바의 정석 
    @interface ClassPreamble {
       DateTime testDate(); // (@Datetime) 
       TestTime testType(); // enum TestTime { FIRST, FINAL } 
    }
    ```
-	(자바의 정석) 잘못된 애노테이션 정의 
```
@interface TestInfo{
	int id = 10; // ok. 상수선언 가능 
    String testName(String name); // 에러. 매개변수 선언 불가 
    String wrong() throw Exception // 에러. 예외 선언 불가 
    ArrayList<T> list(); // 에러. 요소의 타입에 타입 매개변수 사용 불가 
}

```

#### 사용 
-	value를 채워서 사용한다. 
```java
@ClassPreamble (
   author = "John Doe",
   date = "3/17/2002",
   currentRevision = 6,
   lastModified = "4/12/2004",
   lastModifiedBy = "Jane Doe",
   // Note array notation
   reviewers = {"Alice", "Bob", "Cindy"}
)
public class Generation3List extends Generation2List {

// class code goes here

}
```
-	만약 이 만든 애노테이션의 정보를 Javadoc 생성 문서에 포함하고 싶다면 @Documented 애노테이션을 애노테이션 정의할 때 붙이면 된다
```java
// import this to use @Documented
import java.lang.annotation.*;

@Documented
@interface ClassPreamble {

   // Annotation element definitions
   
}
```

### 애노테이션의 조상은 Annotation
-	그러나 애노테이션은 상속 불가. `@interface TestAnno extends Annotation` 하면 에러 
![](https://images.velog.io/images/bongf/post/f9338763-ac84-4f87-80b6-83debc58e8e5/image.png)

## 메타 애노테이션
-	자바의 정석
-	애노테이션을 위한 애노테이션
-	애노테이션 위에 붙이는 애노테이션으로 애노테이션을 정의할 때 특정 속성을 지정하는데 사용된다. 
-	java.lang.annotation 패키지에 포함되어 있다. 

|애노테이션| 설명 |
|--|--|
|@Target|애노테이션이 적용 가능한 대상 지정하는데 사용 |
|@Documented|애노테이션 정보가 javadoc으로 작성된 문서에 포함되게 한다|
|@Inherited|애노테이션이 자손클래스에 상속되도록한다|
|@Retention|애노테이션이 유지되는 범위를 지정하는데 사용|
|@Repeatable|애노테이션을 반복해서 적용할 수 있게한다. (JDK 1.8)| 

#### @Inherited
-	애노테이션을 정의해두고
-	![](https://images.velog.io/images/bongf/post/fdbf2cf8-f3a3-457a-b168-09748d9ee8c3/image.png)
-	부모 클래스에 애노테이션을 붙인 후 자식 클래스를 만들어 자식 클래스의 애노테이션을 출력했을 때는 아무 것도 나오지 않았다. 
- ![](https://images.velog.io/images/bongf/post/7948720d-4d87-4dbb-886d-7e6749e35721/image.png)
- ![](https://images.velog.io/images/bongf/post/9f1c580b-1592-4caf-83d7-99221ab99d94/image.png)
- ![](https://images.velog.io/images/bongf/post/6d4bafe4-6b06-411a-94ce-4ceee3d8a76d/image.png)
-	 @Inherited을 붙이니 해당 애노테이션이 출력되었다. 
- ![](https://images.velog.io/images/bongf/post/163b26bf-2137-4015-af36-06caf05a486e/image.png)
- ![](https://images.velog.io/images/bongf/post/efa35c57-95f2-42af-9350-b73f0679bd6c/image.png)

## 학습 2) @retention
https://docs.oracle.com/javase/tutorial/java/annotations/predefined.html
-	애노테이션 유지(retention) 되는 기간 지정하는데 사용 
-	애노테이션 유지 정책 

|유지정책|의미|
|--|--|
|RetentionPolicy.SOURCE | 소스파일에만 존재. 클래스 파일에는 존재x. 컴파일러에게 무시된다|
|RetentionPolicy.CLASS| 컴파일러에 의해 컴파일 타임에 유지. 하지만 JVM에 의해서는 무시된다. (실행시 사용불가) (기본값) 
|RetentionPolicy.RUNTIME | JVM에 의해 유지. 런타임에 사용될 수 있다|
-	컴파일러가 사용하는 애노테이션은 유지 정책이 SOURCE 
-	유지정책이 RUNTIME이면 실행시에 리플렉션 reflection을 통해 클랫 파일에 저장된 애노테이션 정보를 읽어 처리할 수 있다. 
	-	reflection : 자바 클래스 파일이 바이트 코드로 컴파일 되어 static 영역에 위치. 이 영역 찾아 클래스 정보를 가져올 수 있다. -> 구체적 클래스 타입 몰라도 클래스의 메소드, 타입, 변수들을 접근하게 해주는 자바 api 
    	-	https://dublin-java.tistory.com/53

-	`@Controller`가 runtime 
![](https://images.velog.io/images/bongf/post/ec8b2112-20f9-424a-9193-0027c76f20c2/image.png)
-	@Inherited 의 예에서 RetentionPolicy.RUNTIME 을 붙이지 않으면 출력시(runtime) 아무 것도 출력되지 않는다. 

## 학습 3) @target
-	해당 에노테이션이 적용될 수 있는 자바의 요소들을 제한하는 애노테이션 


|대상타입|의미|
|--|--|
|ElementType.ANNOTATION_TYPE| 어노테이션 타입에 적용 가능|
|ElementType.CONSTRUCTOR| 생성자에 적용 가능|
|ElementType.FIELD| 필드나 속성에 적용 가능(멤버변수, enum 상수)|
|ElementType.LOCAL_VARIABLE | 지역변수에 적용 가능|
|ElementType.METHOD  | 메소드 레벨에 적용 가능|
|ElementType.PACKAGE | 패키지 선언에 적용 가능|
|ElementType.METHOD  | 메소드 레벨에 적용 가능|
|ElementType.PARAMETER  | 메소드의 매개변수에 적용 가능|
|ElementType.TYPE  | 클래스의 모든 요소에 적용 가능(클래스, 인터페이스, enum), 타입을 선언할 때|
|ElementType.TYPE_PARAMETER  | 타입 매개변수 가능|
|ElementType.TYPE_USE  | 타입이 사용되는 모든 곳, 해당 타입의 변수를 선언할 때|

-	static import를 쓰면 ElementType.FIELD 를 FIELD 로 간단하게 쓸 수 있다. 
```java
imprt static java.lang.annotation.ElementType.*;

@Target({FIELD, METHOD})
public @interface SuprressWarnings {
}
```

## 학습 4) @documented
-	애노테이션에 대한 정보가 javadoc으로 작성된 문서에 포함되도록한다. 자바에서 제공하는 기본 어노테이션 중에 @Override @SuppressWarnings 제외하고는 다 이 애노테이션이 붙어있다. 


## 학습 5) 애노테이션 프로세서
https://medium.com/@jason_kim/annotation-processing-101-%EB%B2%88%EC%97%AD-be333c7b913
-	애노테이션 프로세싱은 컴파일 시간에 어노테이션들을 스캐닝하고 프로세싱하는 javac 에 속한 빌드툴이다. 
-	애노테이션 프로세서는 자바 코드(또는 컴파일된 바이트 코드)를 인풋으로 받아서 아웃풋으로(보통 .java파일) 을 생성합니다. 이 말은 애노이션으로 당신이 java 코드를 생성시킬수 있다는 말이다. 생성된 자바 코드는 .java 파일에 있다. 
-	대표적인 예로 롬복이 있다. 
-	새롭게 파일을 생성하는 것이지 존재하는 코드를 바꾸는 것은 아니라는 점 주의. 
