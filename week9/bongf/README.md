
## 학습 0) 예외가 발생하면 ? 
-	메서드가 예외를 발생시키면 runtime 시스템이 예외를 처리하려고 시도한다. 
-	예외가 발생한 메서드에서 시작해서 그를 호출한 메서드의 리스트 순서대로 예외를 처리할 수 있는지 찾는다. 
![](https://images.velog.io/images/bongf/post/80094440-f25e-4ad9-b806-113092268580/image.png)
-	그림 출처 : https://docs.oracle.com/javase/tutorial/essential/exceptions/definition.html
-	이 메서드 리스트가 콜스택이다. 
-	메소드가 호출된 역순으로 계속 거꾸로 가서 예외를 처리할 방법(Exception Handler)(ex. catch문) 찾는다. 
-	적절한 handler를 찾으면 runtime system은 이 예외를 handler에게 넘긴다. 
-	콜스택의 모든 메서드를 찾았는데 적절한 exception handler를 찾지 못하면 runtime system은 종료된다. (프로그램 종료) 

## 학습 1) 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
### try-catch-finally
#### try-catch-finally 

```java
try {
	//예외가 발생할 가능성이 있는 코드 
} catch (Exception1 e1) {
	// Exception1이 발생할 경우, 이를 처리하기 위한 문장 
} catch (Exception2 e2) {
	// Exception2이 발생할 경우, 이를 처리하기 위한 문장 
} catch (Exception3 e3) {
	// Exception3이 발생할 경우, 이를 처리하기 위한 문장 
} finally {
	// 예외 발생여부에 관계없이 항상 수행되어야 하는 문장 
}
```
-	try-catch-finally 순서로 작성하며, finally는 선택적으로 사용한다. 
	-	try문에서 예기치 않은 예외(catch문으로 잡지 않은)가 발생하더라도 finally 블럭 코드는 작동한다. 
-	if문과 달리 `{}` 생략 불가
-	try-catch문의 흐름 
	-	try 블럭 내에서 예외가 발생한 경우 
    	-	1. 발생한 예외와 일치하는 catch 블럭 있는지 확인 
        -	2. 일치하는 catch 블럭 찾으면, catch 블럭 내 문장을 수행하고 **전체 try-catch문을 빠져나간다** 
        -	일치하는 catch블럭 찾지 못하면 예외는 처리되지 못한다. 
     -	try 블럭 내에서 예외가 발생하지 않은 경우 
     	-	catch 블럭 거치치 않고 전체 try-catch 문 빠져 나간다. 
```java
// 일치하는 catch 블럭 찾으면, catch 블럭 내 문장을 수행하고 전체 try-catch문을 빠져나간다

public class TryCatch {
    public static void main(String[] args) {
       TryCatch tc = new TryCatch();
       tc.try_catch();
    }

    public void try_catch() {
        System.out.println(1);
        try {
            System.out.println(0/0);
            System.out.println(2); // 실행되지 않는다 
        } catch (ArithmeticException ae) {
            System.out.println(3);
        } finally {
          System.out.println(4);
        }
    }
}

// 결과 1 3 4 

```
#### 예외발생 후 catch 로 잡을 때 일어나는 일 
-	예외가 발생하면 발생한 예외에 해당하는 클래스의 인스턴스가 만들어진다. 
-	이 예외를 처리할 수 있는 catch 블럭을 찾는다. 
	-	catch 블럭의 괄호() 내에 선언된 참조변수의 종류와 생성된 예외클래스의 인스턴스에 instanceof 연산자 이용해서 검사한다. 
    -	`발생한예외인스턴스` `instanceof` `catch문에적힌예외클래스`
    -	따라서 동일한 예외가 아니어도 발생한 예외의 부모 클래스만 되도 예외가 잡히는 것 
    -	이 결과가 `true`가 나올 때까지 catch안의 () 순차적 확인 
-	그렇기 때문에 구체적인 예외를 상위에 작성한다. 더 위쪽의 예외가 아래 예외의 상위 클래스라면 아래 catch문은 절대로 실행될 일이 없기 때문 ![](https://images.velog.io/images/bongf/post/b4516f6a-7698-4161-bc53-56b2c9e376f0/image.png)
	-	아예 컴파일 되지 않는다. ![](https://images.velog.io/images/bongf/post/3dfd0568-760b-4ba6-80d7-4f39e8ade326/image.png)
#### 멀티 catch 블럭 
-	`|` 기호로 하나의 catch 블럭으로 여러 예외 처리 가능하다. 예외 처리 코드가 똑같다면 이렇게 써주면 된다. 
```java
try {
} catch (ExceptionA | ExceptionB e) {
}
```
-	위와 마찬가지로 부모-자식 관계의 예외를 발생 시키면 컴파일 에러가 난다. ![](https://images.velog.io/images/bongf/post/49e5a5a7-3bbb-4a01-a69a-9864f2d56a77/image.png) ![](https://images.velog.io/images/bongf/post/ddc15e95-b9b3-40c0-af96-ca613c88a79c/image.png)
#### try-catch-finally의 return
-	try문 안에 return 있을 경우 
	-	아래의 try-catch 문 바깥에 return 문이 없을 경우는 컴파일 에러 
```java 
 public static void main(String[] args) {
       TryCatchFinally tc = new TryCatchFinally();
        System.out.println(tc.tryReturn());
    }  // 결과 in try, in catch, 2
 public int tryReturn() {
        try {
            System.out.println("in try");
            int x = 10/0;
            return 1;
        } catch (ArithmeticException e) {
            System.out.println("in catch");
        }
        return 2;
    }

```
-	try&catch&finally 각각에 return 있을 때 -> finally return(case1) 
	-	finally 문은 정상 종료를 의미하기 때문에 try문에서 예외가 발생해도 정상처리 된다. (case2)  
```java
//case1 
    public int tryFinallyReturn() {
        try {
            System.out.println("in try");
            return 1;
        } catch (ArithmeticException e) {
            System.out.println("in catch");
            return 2;
        } finally {
            System.out.println("in finally");
            return 3;
        }
    } // 결과 in try, in finally, 3

    public int catchFinallyReturn() {
        try {
            System.out.println("in try");
            int x = 10/0;
            return 1;
        } catch (ArithmeticException e) {
            System.out.println("in catch");
            return 2;
        } finally {
            System.out.println("in finally");
            return 3;
        } // 결과 in try, in catch, in finally, 3
    }
```
```java
//case2
    /*
    public int notCatch() {
        int x = 10 / 0;
        return 1;
    }
    원래 이것을 실행하면 ArithmeticException 발생
     */
    public int notCatch() {
        try {
            int x = 10 / 0;
            return 1;
        }
        finally {
            return 42;
        }
    } //결과 42, 정상종료 
```

-	그렇다면 finally 문에는 return이 없고 try에만 return이 있을 때는? 
	-	try return이 실행된다. try의 return 값을 임시로 저장해두고 finally 종료 후 try의 return 수행 
    -	자세한 내용은 [여기](https://tomining.tistory.com/154)
```java
    public int onlyTryReturn() {
        try {
            System.out.println("in try");
            return 1;
        } catch (ArithmeticException e) {
            System.out.println("in catch");
        } finally {
            System.out.println("in finally");
        }
        return 2;
    } //결과 in try, in finally, 1
```
```java
//값을 미리 저장해두기 때문에 try의 n 값이 출력 
    public int tryFinallyReturnVariable() {
        int n = 0;
        try {
            System.out.println("in try");
            n = 1;
            return n;
        } catch (ArithmeticException e) {
            System.out.println("in catch");
            n = 2;
            return n;
        } finally {
            System.out.println("in finally");
            n = 3;
        }
    } //결과 in try, in finally, 1
```
```java
// 주의. 객체의 주소를 저장하기 때문에, finally 에서 객체에 변화를 준(아래 에서는 element를 바꿔줌) 것은 반영 된다.  
    public int[] tryFinallyReturnReference2() {
        int[] arr = {0,0,0};
        try {
            System.out.println("in try");
            arr = new int[]{1, 1, 1};
            return arr;
        } catch (ArithmeticException e) {
            System.out.println("in catch");
            arr[1] = 2;
            return arr;
        } finally {
            System.out.println("in finally");
            arr[2] = 3;
            arr = new int[]{3, 3, 3};
        } 
    } // 결과 in try, in finally, [1, 1, 3]

```
-	더 나아가서 :
	-	[catch 문안에 return이 권장되지 않는 이유 : 메모리 누수](https://lazydeveloper.net/1560573)
    -	[return문은 맨 마지막에 작성이 바람직](https://lazydeveloper.net/1560573)
### try-catch-resources
-	(프로그램이 끝나기 전에 반드시 close해야할) resource를 try구문에서 선언할 때 사용한다. 
	-	이 작업이 끝나면 resource를 close를 해줘야만 할 때 try-catch-resources를 사용하면 예외 발생 여부와 관계없이 close 해준다. 
    -	 java.lang.AutoCloseable 을 구현한 것만 resource로 사용할 수 있다. 
    -	원래 이전에는 finally 블록으로 리소스가 닫히게 해줬는데 이를 훨씬 간편하게 만들어 준 것 
    	-	finally를 이용하면 close를 하면서 발생할 수 있는 예외도 처리해줘야 했다. 
    -	readFirstLineFromFile 메서드 에서는 try블럭과 close(try-with-resource)둘 다에서 예외가 던져지면 close관련 예외가 억제되고 try 블럭 예외를 던진다 
    -	 readFirstLineFromFileWithFinallyBlock메서드에서 try블럭과 close(finally) 둘 다에서 예외가 던져지면 try 블럭 관련 예외가 억제되고 close 관련 예외가 던져진다. 
    -	이에 관련해서 [코드리뷰 받은 예](https://ryan-han.com/post/java/try_with_resources/)
    
```java
static String readFirstLineFromFile(String path) throws IOException {
    try (BufferedReader br =
                   new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
```
```java 
// java7 이전에 finally를 이용한 close 
static String readFirstLineFromFileWithFinallyBlock(String path) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(path));
    try {
        return br.readLine();
    } finally {
        br.close();
        /* 
        처리해주기 위해 
        finally {
        try { resource.close(); } catch(...) { 아무것도 안 함 }
        } 이런 식
        */
    }
    }
}
```
### throws 
-	예외가 발생한 메서드 내에서 해당 예외를 처리하지 않고 콜스택의 더 위의 메소드가 처리하게 할 때 
-	메서드에 예외를 선언한다. 
```java
void moethod() throws Exception1, Exception2 {

}
```
-	선언한 예외의 자손타입의 예외까지도 발생할 가능성을 포함 
-	예외를 처리하는 것이 아니라 자신을 호출한 메서드에게 처리를 떠넘기는 것 
-	Unchecked 예외는 내부에서 처리 안 해줘도 throws 로 선언 해주지 않아도 된다. 
	-	위의 학습0에서 어차피 상위 메서드로 넘겨지므로 
    -	아래 예 : 여기서 발생하는 Arithmetic exception은 unchecked exception 
```java
public class WithoutThrows {
    public static void main(String[] args) {
        System.out.println("main");
        try {
            two();
        } catch (ArithmeticException e) {
            System.out.println("handle");
        }
    }

    public static void two() {
        System.out.println("two");
        three(0);
    }

    public static void three(int n) {
        System.out.println("three" + "," + n);
        int x = 6 / n;
    } 
// 결과 : main, two, three,0, handle
```
-	계속해서 상위의 메서드에 예외가 떠넘겨지다 main 메서드까지 처리되지 못하면 프로그램 비정상 종료된다. 

### throw 
-	예외 던지기 
	-	예외를 발생시킬 때 사용한다. 
	-	Throwable 의 하위 클래스 object를 던지면 된다. 
```java
 public int calculate(int n) {

        if (n == 0) {
            throw new IllegalStateException();
        }
        return n * 3;
    }
```


#### exception-rethrowing, chained exception
-	예외 되던지기 (re-throwing) 
	-	예외를 처리한 후에 다시 동일한 예외를 발생시킨다 
    -	예외가 발생한 메서드와 그 메소드를 호출한 메서드 모두에서 해당 예외를 처리하게 하는 방법 
    -	try-catch 사용해서 예외를 처리해주는 동시에 메서드의 선언부에 발생할 예외를 throws에 지정해줘야 한다. 
```java
public class ReThrowing {
    public static void main(String[] args) {
        try {
            method();
        } catch (Exception e) {
            // 해당 예외 처리 2
        }
    }
    
    public static void method() throws Exception  {
        try {
            throw new Exception();
        } catch (Exception e) {
            // 해당 예외 처리 1
            throw e;
        }
    }
}
```
-	연결된 예외 (chained exception)
	-	한 예외가 다른 예외를 발생시킬 수 있다. 
    -	예외 a가 예외 b를 발생시켰다면 a를 b의 원인 예외(cause exception) 이라고 한다. 
    -	예외를 처리할 때, 발생 예외를 새로운 예외의 원인 예외로 등록해서 새로운 예외를 발생시키는 방식 
    	-	1) 여러 가지 예외를 하나의 큰 분류의 예외로 묶어서 다루기 위함(case1)
        	-	두 예외가 상속관계가 아니어도 묶을 수 있다. 
        -	2) checked 예외를 unchecked 예외로 바꿀 수 있도록 하기 위해서
        	-	`throw new RuntimeExcetion(catch문에 걸린 예외)' 이런식으로 중첩예외로 담아서 unchecked 예외로 변경 
	-	관련 메소드 
    	-	Throwable initCause(Throwable casue) 지정한 예외를 원인 예외로 등록
        -	Throwable getCause 원인 예외 반환 
```java 
//자바의 정석 예시 case1 
public class ChainedException {
    public static void main(String[] args) {
        try {
            method();
        } catch (InstallException ie) {
            ie.getCause();
        }
    }

    public static void method() throws InstallException {
        try {
            startInstall(); // SpaceException 발생
            copyFiles(): // MemoryException 발생
        } catch (SpaceException e) {
            InstallException ie = new InstallException("설치중예외발생");
            ie.initCause(e);
            throw ie;
        } catch (MemoryException me) {

        }

    }
}
```
-	[chained 예외 잘 설명되어 있는](https://wisdom-and-record.tistory.com/46)
## 학습 2) 자바가 제공하는 예외 계층 구조
![](https://images.velog.io/images/bongf/post/e576a6bf-30ef-44fa-aee0-26311933eac0/image.png)
-	그림 출처 : https://docs.oracle.com/javase/tutorial/essential/exceptions/throwing.html
-	모든 예외와 에러는 Throwable의 하위 클래스다. 
-	예외들은 모두 Excpetion 클래스를 상속한다. 
-	예외는 RuntimeException과 그가 아닌 것으로 나뉜다. 

## 학습 3) Exception과 Error의 차이는?
-	에러 : 프로그램 코드에 의해서 수습될 수 없는 심각한 오류 
	-	OutOfMemoryError, StackOverflowError 
-	예외 : 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류 
-	에러는 최대한 발생하지 않도록 해야 하고 예외는 일부러 발생시키기도 하고 예외를 처리하는 방법을 다양하게 둔다. 프로젝트 하면서 프로그래밍의 일부 같다는 생각이 든다. 일부러 예외를 발생시키고 그를 처리하면서 어떤 로직이 실행되게 한다는 점에서. 


## 학습 4) RuntimeException과 RE가 아닌 것의 차이는?
-	RuntimeException을 상속받는 Exception : Unchecked exception
	-	코드 상에서 처리하지 않아도 되는 예외 (해도 된다) 
    -	Runtime에 확인한다. 
-	그 외 : Checked Exception 
	-	예외처리가 강제되는 예외 
    -	처리되지 않으면 컴파일 되지 않는다. 

## 학습 5) 커스텀한 예외 만드는 방법
-	Exception, RuntimeException 등 예외를 상속 받아서 만들면 된다. 
	-	Exception 상속시 체크드 예외라 try-catch 해줘야 한다.
    -	아래에서 `super(message)`는 이 예외가 생성될 때 인자로 받는 메세지를 예외의 기본 메세지로 등록 (나중에 getMessage()로 받을)
```java
public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String message) {
        super(message);
    }
}
```
### Built-in Exceptions in Java with examples
-	자바라이브러리에서 가능한 buit-in 예외 
-	1. Arithmetic esception 
	-	산술연산에서 예외 발생시 
    -	0으로 나눌 때 
-	2. ArrayIndexOutOfBounds Exception
	-	array 잘못된 index에 접근했을 때 (size보다 크거나 음수인 인덱스로 접근했을 때) 
-	3. ClassNotFoundException   
	-	클래스에 대한 정의를 찾을 수 없을 때 
```java
class Bishal {

} class Geeks {

} class MyClass {
public static void main(String[] args)
	{
		Object o = class.forName(args[0]).newInstance();
		System.out.println("Class created for" + o.getClass().getName());
	}
}
```
-	4. FileNotFoundException 
	-	파일 접근 불가 일 때, 안 열릴 때 
```java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
class File_notFound_Demo {

	public static void main(String args[]) {
		try {
			File file = new File("E://file.txt");
			FileReader fr = new FileReader(file);
		} catch (FileNotFoundException e) {
		System.out.println("File does not exist");
		}
	}
}
```
-	5. IOException 
	-	입출력 작업이 실패, 중단될 때 
    
-	6. InterruptedException 
	-	thread가 waiting, sleeping, 작업중일 때 갑자기 interrupt 되었을 때 
-	7. NoSuchMethodException 
	-	찾을 수 없는 메소드에 접근할 때 
-	8. NullPointerException 
	-	객체 필요한 곳에서 null object 멤버를 참조할 때 
```java
class NullPointer_Demo {
public static void main(String args[])
	{
		try {
			String a = null; // null value
			System.out.println(a.charAt(0));
		}
		catch (NullPointerException e) {
			System.out.println("NullPointerException..");
		}
	}
}

```
-	9. NumberFormatException 
	-	메소드가 string을 numeric format으로 변경할 수 없을 때 
```java
class NumberFormat_Demo {
public static void main(String args[])
	{
		try {
			// "akki" is not a number
			int num = Integer.parseInt("akki");

			System.out.println(num);
		}
		catch (NumberFormatException e) {
			System.out.println("Number format exception");
		}
	}
}
```
-	10. StringIndexOutOfBoundsException 
	-	string의 index범위를 벗어난 탐색 
```java
class StringIndexOutOfBound_Demo {
public static void main(String args[])
	{
		try {
			String a = "This is like chipping "; // length is 22
			char c = a.charAt(24); // accessing 25th element
			System.out.println(c);
		}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("StringIndexOutOfBoundsException");
		}
	}
}

``` 
-	그 외 중요 예외 
-	1. ClassCastException
```java 
class Test {
public static void main(String[] args)
	{
		String s = new String("Geeks");
		Object o = (Object)s;
		Object o1 = new Object();
		String s1 = (String)o1;
	}
}

```
-	2. StackOverflowError
	-	 Stack 영역의 메모리가 지정된 범위를 넘어갈 때
    -	알고리즘 재귀로 풀 때 종종 발생 
```
// Java Program to illustrate
// StackOverflowError
class Test {
public static void main(String[] args)
	{
		m1();
	}
public static void m1()
	{
		m2();
	}
public static void m2()
	{
		m1();
	}
}

```

---
### 출처 
-	자바의 정석 
-	학습 1)
	-	try-catch-resources : https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
    -	throw : https://docs.oracle.com/javase/tutorial/essential/exceptions/throwing.html
-	학습 4) 
	-	https://docs.oracle.com/javase/specs/jls/se7/html/jls-11.html#jls-11.1.1
-	학습 5)
	-	Built-in Exceptions in Java with examples : https://www.geeksforgeeks.org/built-exceptions-java-examples/
