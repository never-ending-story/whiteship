## 학습 1) Thread 클래스와 Runnable 인터페이스
### 쓰레드란 
-	프로세스 : 실행중인 프로그램 
	-	프로그램을 실행하면 실행에 필요한 자원(메모리)를 할당받아 프로세스가 된다. 
-	쓰레드 : 프로세스의 자원을 이용해서 실제로 작업을 수행하는 것 
	-	프로그램에서 실행의 흐름을 추상화한 것 
	-	[위키 정의](https://en.wikipedia.org/wiki/Thread_(computing)) : 스케줄러에 의해 독립적으로 관리되는 프로그로밍된 명령의 가장 작은 실행 단위
    -	모든 프로세스에는 최소 1개 이상의 쓰레드가 존재한다. 

### 멀티쓰레드 
-	멀티쓰레드 프로세스 : 둘 이상의 쓰레드를 가진 프로세스 
-	하나의 프로세스 안에서 여러 쓰레드가 동시에 작업하는 것이 멀티쓰레드이다. 
-	실제로 동시에 돌아가는 쓰레드 수는 CPU 코어(core)의 수다. 
-	다만,  하나의 코어 하나의 프로세스 내에서는 짧은 시간 동안 코어가 작업을 이 쓰레드, 저 쓰레드 작업을 수행하며 동시에 일어나는 것처럼 보인다. 
-	쓰레드는 자원을 공유하기 때문에 사용하는 쓰레드의 전환이 일어날 때 context-switching에 시간이 걸린다. 그래서 싱글쓰레드보다 작업 시간이 더 길게 걸릴 수도 있다. 

### [Oracle](https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html) : Defining and Starting a Thread
-	쓰레드 정의와 실행 
-	쓰레드의 인스턴스를 만드는 어플리케이션은 반드시 해당 쓰레드에서 돌아갈 코드를 제공해야 한다. 2가지 방법이 있다. 
	-	1. Runnable 인터페이스 구현한 클래스
    -	2. Thread 클래스 상속한 클래스
    -	Thread 클래드 상속받으면 다른 클래스를 상속 받을 수 없어 Runnable 구현이 일반적이다.
    -	Runnable, Thead를 이용한 어떤 방법이든 코드를 제공할 클래스를 만들고, Thread.start 메서드로 쓰레드를 실행한다.  
   	
    
#### Runnable 인터페이스 구현 
-	Runnable 객체 제공하기 
-	Runnable 인터페이스는 `run`이라는 싱글 메소드를 정의한다. 
	-	'run' 메소드 : 쓰레드에서 실행될 코드를 포함
    - ![](https://images.velog.io/images/bongf/post/599b2124-b1ae-4442-a7b3-685c438cc16c/image.png)
```java
public class HelloRunnable implements Runnable {

    public void run() {
        System.out.println("Hello from a thread!");
    }
}
```
-	Runnable 인터페이스를 구현한 경우, Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 다음, 이 인스턴스를 Thread 클래스의 생성자의 매개변수로 제공해야 한다.  
```java
public class HelloRunnable implements Runnable {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new Thread(new HelloRunnable())).start();
    }

}
```
#### Thread 서브클래싱 
-	Thread 클래스는 이 자체로 Runnble을 구현한다. 이 Thread의 run 메소드는 아무 것도 하지 않는다. Thread를 구현한 서브클래싱하면서 run을 구현하고 이것이 실제 의미를 갖는 run
	-	아래 그림에서 Runnable을 구현했을 때는 run메소드를 override하라고 컴파일 에러가 뜨지만, Thread를 상속한 클래스에서는 해당 에러가 뜨지 않는다. 이 자체로 Runnable을 구현했기 때문
    
![](https://images.velog.io/images/bongf/post/1d0387c7-a508-4eed-a84d-30ec5c8a71a0/image.png)
-	Thread 클래스의 메소드 run() 
  - ![](https://images.velog.io/images/bongf/post/fe85d04a-854a-4154-863d-7f92a93559e5/image.png)
  - ![](https://images.velog.io/images/bongf/post/af99b0f8-d2aa-4376-9c87-0fb6d3a2ba1c/image.png)
```java
public class HelloThread extends Thread {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new HelloThread()).start();
    }

}
```
### start()와 run()
-	출처 : 자바의 정석 
-	start() 는 새로운 쓰레드가 작업을 실행하는데 필요한 호출스택(call stack)을 생성한 다음에 run()을 호출해서 생성된 호출 스택에 run()이 첫번째로 올라가게 한다.
	-	모든 쓰레드는 독립적인 작업 수행 위해 자신만의 호출 스택 필요하다. 
    -	새로운 쓰레드 생성, 실행할 때마다 새로운 호출 스택 생성되고 쓰레드 종료시 해당 호출스택 소멸
    -	![](https://images.velog.io/images/bongf/post/cc40f3b9-fc1a-413f-ab13-d76460f2a634/image.png)

    -	왼쪽의 start메서드가 오른쪽 쪽처럼 새로운 호출스택을 생성하고 새로 생성된 호출스택에서 run()메서드가 실행된다. 
        
-	start()를 호출했다고 해서 바로 해당 쓰레드를 실행하는 것은 아니다. 일단 실행대기 상태에 있다가 자신 차례가 되어야 실행. 
     -	그 차례는 OS 스케줄러가 정한다. 쓰레드의 실행순서는 OS 스케줄러가 작성한 스케줄에 의해 결정된다.  
-	한 번 실행이 종료된 쓰레드는 다시 실행할 수 없다. 하나의 쓰레드에 대해 	strat()는 한 번만 호출
  -	실행
	  - ![](https://images.velog.io/images/bongf/post/ed69a029-3424-4366-9fef-6f18cb5c5011/image.png)
  - 결과
    - ![](https://images.velog.io/images/bongf/post/07bf6eb9-4e45-4bc2-83f0-cc1ce1ec7662/image.png)
      - t가 terminated 상태로 바뀐 후에 다시 start를 시켰을 때, IllegalThreadStateException 이 난 것을 확인할 수 있다.
         

### 기타 
-	내가 쓰레드 만들기 전에, JVM 안에 현재 프로세스 안에 main 쓰레드 말고도 여러 쓰레드가 이미 실행되고 있다. 
```java
// 코드스쿼드 호눅스 코드 
public class ThreadAlreadyExist extends Thread {

    @Override
    public void run() {
        this.setName("newThread");
        System.out.printf("%s %d start%n", this.getName(), this.getId());
    }

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.printf("Main Thread : %s %d start%n", t.getName(), t.getId());
        Thread newThread = new ThreadAlreadyExist();
        newThread.start();
        System.out.println("Main Thread end");
    }
}
```
-	결과 ![](https://images.velog.io/images/bongf/post/f2458881-c0ab-4b53-9596-ab19185975b2/image.png)
	-	main 쓰레드의 번호가 1번인데 새롭게 만든 쓰레드는 14번이다. 그 사이에 여러 쓰레드가 있다는 것 알 수 있다. (ex. gc) 

## 학습 2) 쓰레드의 상태
-	기본  
	-	start() ➡ **상태 NEW** (생성) ➡  **상태 RUNNABLE** (실행대기) ➡ 실행 ➡ **상태 TERMINATED** (소멸)
   		-	![](https://images.velog.io/images/bongf/post/1a9b6302-cb64-4417-bbd0-5a407802ac87/image.png)
    -	실행 중 일시정지 된 상태 : **BLOCKED, WAITING, TIMED_WAITING**
    -	![](https://images.velog.io/images/bongf/post/f328af33-481c-4c6e-b6b4-eabd6e6d7ca3/image.png)
    
|상태|설명|
|--|--|
|NEW|쓰레드가 생성된 후 아직 start() 메서드가 호출되지 않은 상태, 실행상태로 갈 수 없음| 
|RUNNABLE| 실행 중 또는 실행가능 상태 (ready + running)|
|BLOCKED| 동기화 블럭에 의해서 일시 정지된 상태 (lock이 풀릴 때까지 기다리는 상태|
|WAITING| 쓰레드의 작업이 종료되지는 않았지만 실행가능하지 않은(unrunnable) 일시정지상태|
|TIMED_WAITING| 일시정지 시간이 지정된 경우|
|TERMINATED|쓰레드의 작업이 종료된 상태 

![](https://images.velog.io/images/bongf/post/96ce6408-c0f8-4520-8d4c-7ecb0bbbf1e2/image.png)
-	(1) 실행대기열은 큐(Queue)와 같은 구조로 먼저 실행 대기열에 들어간다 
-	(3) 주어진 실행시간이 다 되거나, yield()를 만나면 다시 실행대기상태가 되고 다음 차례의 쓰레드가 실행 상태가 된다 
-	(4) 실행 중에 suspend(), sleep(), wait(), join(), I/O block에 의해 일시정지가 될 수 있다. I/O 블럭은 입출력에서 발생하는 지연상태(사용자의 입력을 기다리는 경우, 사용자가 입력을 끝내면 다시 실행상태가 된다)
-	(5) 지정된 일시정지시간이 다 되거나(time-out), notify, resume(), interrupt()가 호출되면 다시 실행대기열로 

### Pausing Execution with Sleep
-	sleep으로 실행 일시중지 
-	`Thread.sleep`은 현재 쓰레드의 실행을 일정기간동안 중지시킨다. 
-	어플리케이션의 다른 쓰레드나 다른 어플리케이션이 프로세서의 시간을 사용할 수 있도록 하는 효율적인 방법
#### 시간지정 
-	밀리초 단위(millis, 1000분의 1초)와 나노초(nanos, 10억분의 1초) 단위의 sleep 메서드 존재 
```java
static void sleep(long millis)
static void sleep(long millis, int nanos)
```
-	ex. 0.0015초 멈추게 하려면 
```java
try {
	Thread.sleep(1, 500000);
} catch(InterruptedExecution e) {
}
```
-	이 sleep 시간은 정확하지 않을 수 있다.  
	-	OS에 의존적이기 때문에 
    -	또, sleep은 interrupts에 의해 종료될 수 있기 때문에
-	시간을 지정한 일시정지기 때문에 sleep하면 상태는 `TIMED_WAITING`
```java
public class ThreadDefinitionStart {
    public static void main(String[] args) {
        Runnable r = new HelloRunnable();
        Thread t = new Thread(r);
        t.start();
        Thread.sleep(1000); //main 메서드 1초쉬기. HelloRunnable의 sleep메서드 동작되도록 기다리기 
        System.out.println(t.getState()); // 출력값 TIMED_WAITING
    }
}

class HelloRunnable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello Runnable from a thread!");
    }
}
```
#### 현재 실행 중인 쓰레드에 대해서만 작동
-	sleep은 현재 실행 중인 쓰레드에 대해 작동한다. 
	-	그래서 인스턴스가(`t.sleep()`) 아닌 `Thread.sleep()`을 사용한다.
	-	아래 예에서 th1.sleep()을 하더라도 th1의 상태(자고 있는지)를 보장할 수가 없다. 그래서 실제 현재 쓰레드인 main 쓰레드가 sleep 된다. 그래서 아래 코드를 실행하면 t1의 "th1종료" 까지 다 출력되고 4초의 일시정지(메인메서드 정지)가 있다가 t2의 run()의 코드들이 출력된다. 
    
```java
public class WhichSleep {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        t1.start();
        try {
            t1.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        System.out.println("Main종료");
    }
}

class Thread1 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println("-");
        }
        System.out.println("th1종료");
    }
}

class Thread2 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println("|");
        }
        System.out.println("th2종료");
    }
}
```
#### `InterruptedException`
-	sleep 하는 현재 쓰레드를 다른 쓰레드가 interrupts하면 `InterruptedException` 이 예외를 던지기 때문에 try-catch문으로 감싸주거나 메서드에 `throws InterruptedException`해줘야 한다. 
	-	throw 안해줘서 컴파일 에러 생긴 예 ![](https://images.velog.io/images/bongf/post/0b215204-b10c-459b-a6fe-dfc4cb6492eb/image.png)
-	아래 예에서는 다른 쓰레드를 정의하지 않았기 때문에 InterruptedException 발생하지 않아 throws만 해준 것 
![](https://images.velog.io/images/bongf/post/80f3c46f-8577-4f67-ba8a-6a37094b9019/image.png)
-	interrupts 때문에 `InterruptedException` 이 던진 예외를 처리해준 코드 예 
```java
// 코드스쿼드 수업 호눅스 코드 
package bongf.week10.study;

public class SleepThread implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.err.println("왜깨우시죠");
        }
        System.out.println("Thread wake");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main start");
        Thread t = new Thread(new SleepThread());
        t.start();
        Thread.sleep(1000);
        t.interrupt(); // 이 코드 없다면 "왜깨우시죠" 는 출력 안된다 
        System.out.println("Main end");
    }
}

```
-	sleep() 을 호출할 때 try-catch로 계속 예외 처리해줘야 해서, 매번 이를 하기가 번거로워 try-catch를 포함하는 새로운 메서드를 만들기도 한다. 
```java
//자바의 정석 코드 
void delay(long millis) {
	try {
    	Thread.sleep(1000);
	} catch(InterruptedException e) {}
}
```

### [oracle](https://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html) : Interrupts
-	쓰레드가 현재 하고 있는 것을 멈추고 다른 작업을 해야 함을 나타낸다. interrupt에 대해 쓰레드가 어떻게 응답할지는 프로그래머가 결정하지만 보통 thread를 종료시키는 것이 일반적이다. 
-	interrupt()가 호출되면 쓰레드의 interrupted 상태를 변화시킨다. 
-	isInterrupted()는 쓰레드에 대해 interrupt()가 호출되었는지(interrupted 상태의 변화가 있는지) 알려준다. interrupt()가 호출되지 않았다면 false, 호출되었다면 true를 반환한다. 
	-	아래 코드는 interrupt() 후 isInterrupted() 상태가 true가 되면서 while문을 빠져 나오는 것 
```java
//코드스쿼드 수업 호눅스 코드 
public class SleepThread extends Thread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Thread t = new SleepThread();
        t.start();
        Thread.sleep(2000);
        t.interrupt();
        System.out.println("end");
    }

    public void run() {
        long count = 0;
        while(!isInterrupted()) {
            count++;
        }
        System.out.println("Thread wake : " + count);
    }
```
-	`interrupted()`는 interrupt()가 호출되었는지 확인하는 것은 `isInterrupted()`와 똑같지만 쓰레드의 interrupted 상태를 false로 초기화 시킨다는 점에서 차이가 있다. 
-	관례적으로 `InterruptedException`예외를 던져 종료되는 메서드는 interrupt 상태를 초기화시킨다(false). false되자마자 다른 쓰레드에 의해 다시 interrupt되어 true로 되는 것은 가능 

### Joins
-	한 쓰레드가 다른 쓰레드가 (지정된 시간동안) 작업을 수행하도록 멈춰서 기다릴 때 사용한다. 
	-	자신의 작업 중에 다른 쓰레드의 작업을 참여(join) 시키는 의미 
	-	시간을 지정하지 않으면 해당 쓰레드가 작업을 모두 마칠 때까지 기다리게 된다. 
    	-	시간의 정확성은 sleep과 마찬가지로 정확하지 않을 수 있다. 
    -	작업 중에 다른 쓰레드의 작업이 먼저 수행될 필요가 있을 때 사용 

```java
t.join() // 쓰레드 t가 종료될 때까지 기다린다. 
```
```java
void join()
void join(long millis)
void join(long millis, int nanos)
```
-	sleep처럼 interrupt()에 의해 대기상태에서 벗어날 수 있다. (InterruptedException 발생) 그래서 try-catch로 감싸야 한다. 
	-	sleep과 다른 점은 현재 쓰레드가 아닌 특정 쓰레드에 대해 동작해서 static메서드가 아니다. 
    
-	join해서 기다리는 동안 상태는 `WAITING` 
```java
package bongf.week10.study;

public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Thread3 t3 = new Thread3();
        t3.start();
        Thread.sleep(1000);
        System.out.println(t3.getState()); // WAITING 
    }
}

class Thread3 extends Thread {
    public void run() {
        System.out.println("t3 start");
        Thread4 t4 = new Thread4();
        t4.start();
        try {
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread3 ends");
    }
}

class Thread4 extends Thread {
    public void run() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread4 ends");
    }
}

```

### yield() 
-	자신에게 주어진 실행시간을 다른 쓰레드에게 양보하고(yield) 자신은 실행대기상태로 돌아간다

### ~~suspend()~~, ~~resume()~~, ~~stop()~~ 
-	suspend() : sleep처럼 쓰레드 멈추게 한다. resume()을 호출해야 다시 실행 대기 상태가 된다. 
-	stop()은 쓰레드를 종료시킨다. 
-	이 메서드들은 모두 deprecated. deadlock를 일으키기 쉽게 작성되었기 때문에 


## 학습 3) 쓰레드의 우선순위
-	우선순위(priority)라는 속성(멤버변수)를 가지고 있다. 
```java
void setPriority(int newPriority) // 우선순위 지정 
int getPriority() // 우선순위 반환
```
-	이 우선순위의 값에 따라 쓰레드가 얻는 실행 시간이 달라진다. 
	-	우선순위가 높은 쓰레드가 실행시간을 더 많이 얻을 수 있게 
-	우선순위 범위 1~10, 숫자가 높을수록 우선순위가 높다 (10이 최대 우선순위)
	-	Thread 클래스 안에 설정되어 있는 우선순위 ![](https://images.velog.io/images/bongf/post/f8e7ad34-c781-4641-b2f9-1d56da2faa59/image.png)
-	쓰레드를 생성한 쓰레드로부터 우선순위를 상송받는다.
	-	main 메서드를 수행하는 쓰레드는 우선순위가 5 ➡ 메인 메서드 내에서 생성하는 쓰레드의 우선순위는 자동으로 5 
-	OS 스케줄러에 종속적이기 때문에 희망사항을 적어주는 것 뿐 최종 결정은 OS 스케줄러가 한다. 

## 학습 4) Main 쓰레드
-	main 메서드의 작업을 수행하는 것도 쓰레드이며, 이를 main 쓰레드라고 한다. 
```java
public class MainThread {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println(t.getName()); // main
        System.out.println(t.getPriority()); // 5
    }
}
```
-	main메서드가 수행을 마쳤다 하더라도 다른 쓰레드가 아직 작업을 마치지 않은 상태라면 프로그램이 종료되지 않는다. (main이 종료되도 다른 일반 쓰레드(사용자 쓰레드)가 종료되지 않았다면 프로그램 죽지 않는다) 
-	실행 중인 사용자 쓰레드(일반쓰레드)가 하나도 없을 때 프로그램은 종료된다.

### 데몬 쓰레드 
-	쓰레드는 `사용자 쓰레드(user thread)` 와 `데몬 쓰레드(demon thread)` 두 종류가 있다. 
-	데몬 쓰레드는 다른 쓰레드를 돕는 보조 쓰레드이다. 일반쓰레드가 모두 종료되면 데몬 쓰레드는 강제 종료된다. 
-	데몬쓰레드의 예 : gc, 워드프로세서 자동저장, 화면 자동갱신
-	자바의 정석에 따르면 데몬쓰레드는 무한루프와 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족되면 작업을 수행하고 다시 대기하도록 작성한다. 
```java 
public class Daemon implements Runnable{
    @Override
    public void run() {
       while(true) {
           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {}
           if(  ) // 특정조건 {
       }
    }
}
```
-	데몬 쓰레드는 일반 쓰레드의 작성방법과 실행방법이 같다. 다만 쓰레드 생성 후, 실행 전 setDaemon(true)를 호출하면 된다. 
```java
//코드스쿼드 호눅스 수업 참고 
public class Daemon extends Thread{
    @Override
    public void run() {
       this.setName("Daemon");
       System.out.printf("%s starts%n", this.getName());
       int count = 0;
       while(true) {
           try {
               Thread.sleep(1000);
               count++;
               System.out.printf("Dameon %d초 진행 중%n", count);
           } catch (InterruptedException e) {}
       }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = Thread.currentThread();
        System.out.printf("%s starts%n", t.getName());
        Thread d = new Daemon();
        d.setDaemon(true);
        d.start();
        Thread.sleep(2000);
        System.out.printf("%s ends%n", t.getName());
    }
}
```
-	결과 : 데몬쓰레드는 메인이 죽자마자 죽는다 
  - ![](https://images.velog.io/images/bongf/post/9074bd4e-4aff-42d4-b16f-40ed50d5d40c/image.png)
-	데몬쓰레드가 생성한 쓰레드는 자동적으로 데몬 쓰레드다 
```java
public class Daemon extends Thread{
    @Override
    public void run() {

       this.setName("Daemon");
       System.out.printf("%s starts%n", this.getName());

       Thread childDaemon = new Daemon();
       childDaemon.setName("childDaemon");
       System.out.printf("제 이름은 %s 입니다 . 제가 데몬일까요? %s %n", childDaemon.getName(), childDaemon.isDaemon());

       int count = 0;
       while(true) {
           try {
               Thread.sleep(1000);
               count++;
               System.out.printf("%s %d초 진행 중%n", this.getName(), count);
           } catch (InterruptedException e) {}
       }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = Thread.currentThread();
        System.out.printf("%s starts%n", t.getName());
        Thread d = new Daemon();
        d.setDaemon(true);
        d.start();
        Thread.sleep(2000);
        System.out.printf("%s ends%n", t.getName());
    }
}
```
-	결과 : 
  -	![](https://images.velog.io/images/bongf/post/b6e41d6e-7734-4ea1-b79f-f0e0b3e46434/image.png)

## 학습 5) 동기화
### [oracle](https://docs.oracle.com/javase/tutorial/essential/concurrency/sync.html) : 동기화 

-	쓰레드끼리는 필드와 필드가 참조하는 객체 참조를 공유하며 커뮤니케이션한다. 
-	이것은 매우 효율적이지만 두 가지 에러가 발생할 수 있다. 
	-	thread interference (쓰레드 간섭)
    -	memory consistency error (메모리 일관성 오류) 
-	이 두 에러를 막는 것이 synchronization 동기화다. 

### [oracle] (https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html)  동기화 설명 : 내부 잠금 및 동기화 
- 동기화는 intrinsic lock 또는 monitor lock 이라고 알려진 내부 엔티티 중심으로 구축된다.  intrinsic lock은 1. 객체 상태에 배타적인 접근을 강제하고 2. 가시성에 필수적인  `happens-before` 관계를 구축한다. 
-	모든 객체는 연관된 내부 잠금이 있다. 
-	관례적으로 객체의 필드에 대한 배타적이고 일관된 접근이 필요한 쓰레드는 객체에 접근하기 전에 객체의 내부 잠금을 획득해야 한다. 그리고 작업이 끝나면 내부 자금을 반납해야 한다. 
-	intrinsic lock을 획득하고 반납하기까지 이를 intrinsic lock을 소유했다고 한다. 쓰레드가 intrinsic lock을 소유하는 이상 다른 쓰레드는 같은 lock을 얻을 수 없다. 
-	한 쓰레드가 내부 락을 반납하면 같은 락을 후에 얻는 어떤 액션에 대해  happens-before 관계가 맺어진다 
-	아래 자바의 정석 설명을 보면 더 쉽게 이해할 수 있다. 
-	자바의 정석 
	-	한 쓰레드가 특정 작업을 끝마치기 전까지 다른 쓰레드에 의해 방해받지 않도록 하기 위한 것 
    -	`임계영역(critical section)`과 `잠금(락, lick` 개념 도입 
    -	공유 데이터를 사용하는 코드 영역을 임계영역으로 지정해두고, 공유 데이터(객체)가 가지고 있는 lock를 획득한 하나의 쓰레드만 이 영역 내 코드를 수행할 수 있다. 
    -	그리고 해당 쓰레드가 임계 영역 내 코드를 다 수행하고 벗어나서 lock을 반납해야만 다른 쓰레드가 반납된 lock을 획득하여 임계 영역의 코드를 수행할 수 있게 된다. 
    -	한 쓰레드가 진행 중인 작업을 다른 쓰레드가 간섭하지 못하도록 막는 것을 `쓰레드 동기화(synchronization)`이라고 한다. 
    -	자바에서는 `synchronized블럭` 통해 쓰레드 동기화 지원했지만 JDK1.5부터는 java.util.concurrent.locks와 java.util.concurrent.atomic 패키지 통해 다양한 방식으로 지원 
-	그러나 동기화는 `thread contention`(쓰레드 경합)을 발생시킬 수 있다. 
	-	2개 이상의 쓰레드가 똑같은 자원에 동시에 접근하려고하는 것

### [oracle](https://docs.oracle.com/javase/tutorial/essential/concurrency/interfere.html) : 쓰레드 간섭 

```java 

class Counter {
    private int c = 0;

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public int value() {
        return c;
    }

}
```
-	Counter  객체가 여러 쓰레드에서 사용되면 쓰레드 간에 interference가 발생하고 예상되지 않는 결과가 나올 것 
-	Interference은 똑같은 데이터에 대해 두가지 연산이 각각 다른 쓰레드에서 실행될 때 발생한다. 
> 0. c 초깃값 = 0 
> 1. Thread A : c 정보 조회 (0)
> 2. Thread B : c 정보 조회 (0)
> 3. Thread A : 조회된 값을 증가시킨다 (1) 
> 4. Thread B : 조회된 값을 감소시킨다 (-1)
> 5. Thread A : 결과를 c에 저장 (1)
> 6. Thread B : 결과를 c에 저장 (-1)
-	Thread A 결과는 사라지고 B에 의해 덮어씌어진 값만 남는다. 
-	Thread Interfercne 버그는 찾고 고치기가 어렵다 

### [oracle](https://docs.oracle.com/javase/tutorial/essential/concurrency/memconsist.html) 메모리 일관성 오류
-	서로 다른 쓰레드가 동일한 데이터여야 하는 것에 일관성 없는 view를 가질 때 발생한다. 
-	프로그래머는 이에 대한 대응만 알고 있으면 된다 
```
int counter = 0;
counter++; // Thread A 
System.out.println(counter); // Thread B
```
-	counter 변수가 공유되고 A와 B 각각에서 각 코드가 실행된다고 할 때, Thread B에 "0"이 출력될 수도 있다. A의 counter 변경이 B에도 보일지 보장할 수 없기 때문에. 프로그래머는 이에 대해서 `happens-before` 관계를 맺어줘야 한다. 
-	`happens-before` 관계를 맺어주는 방법은 여러가지가 있는데 그 중 하나가 `synchronization`이다. 

### [oracle](https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html) :  synchronized를 이용한 동기화 
-	1. 동기화된 메서드 : 메서드 전체를 임계 영역으로 설정
	-	메서드가 호출된 시점부터 해당 메서드가 포함된 객체의 lock을 얻어 작업을 수행하다가 메서드가 종료되면 lock 반환
    -	메서드가 returns 할 때 lock이 반납되는데 잡히지 않는 예외로 인해 return 될 때도 lock은 반납된다. 
    -	[oracle2](https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html)
    	-	static synchronized 메서드가 호출되면 쓰레드는 Class에 대한 intrinsic lock(지금까지 말했던 lock을 oracle은 intrinsic lock 이라고 하는 것 같다)을 얻는다. 그래서 그 lock에 의해 클래스의 static fields가 제어된다. 이는 인스턴스에 대한 lock과 구분된다. 
```java 
public synchronized void calcSum() {

}
```
-	2. synchronized문 사용 : 메서드 내의 일부를 블럭 { } 으로 감싸고 블럭앞에 `synchronized (참조변수)`를 붙이는 것 
	-	참조변수는 락(lock)을 걸고자 하는 객체를 참조하는 것이어야 함 
	-	 이 블럭을 synchronized 블럭이라고 브른다. 
    -	이 블럭에 들어가면서 쓰레드는 지정된 객체의 lock을 얻고 여길 벗어나면 lock 반납 
	-	동기화된 메서드보다 복잡 
```java
public  void method() {
	 synchronized(객체의 참조변수) {
     }
}
```
### 1. [oracle](https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html)동기화된 메소드 

-	메소드를 동기화 하려면 `synchronized` 키워드를 메서드 선언부에 더하면 된다. 
```java 
public class SynchronizedCounter {
    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }
}
```
-	SynchronizedCounter의 인스턴스가 count라고 했을 때 동기화된 메서드는 두 가지 효과 
1. 한 쓰레드가 동기화된 메서드를 실행하면, 모든 다른 쓰레드는 첫번째 쓰레드가 그 객체와 관련된 코드를 다 실행할 때까지 기다려야 한다. 
2. 동기화된 메서드가 종료되면 같은 객체에 대한 동기화된 메서드를 후속 호출과 자동적으로 `happens-before`관계를 설정한다. 이는 객체 상태의 변경이 모든 쓰레드에게 보여지게 한다. 
-	생성자는 동기화될 수 없다. 객체를 생성한 쓰레드가 그것이 생성되는 동안 접근 권한을 가져야 하기 때문 
-	객체가 1개 보다 많은 쓰레드에 보인다면 그 객체에 대한 모든 읽기, 쓰기는 synchronized 메소드를 통해서 이루어 져야 한다. 
-	`final` fields에는 동기화 블럭 사용할 필요가 없다. final 이니까 



### 2. Synchronized Statements

- intrinsic lock을 제공할 객체를 구체적으로 명시해야 한다. 

```java 
public void addName(String name) {
    synchronized(this) {
        lastName = name;
        nameCount++;
    }
    nameList.add(name);
}
```
-	여기서 the addNam메소드는 lastName, nameCount의 변화에 동기화 되어야 한다. 
-	동시에 다른 객체의 메소드의 호출(nameList.add)을 피해야 한다. 동기화된 코드에서 다른 객체의 메소드를 호출하는 것은 [Liveness에서 소개된](https://docs.oracle.com/javase/tutorial/essential/concurrency/liveness.html) 문제를 발생시킬 수 있다. 
-	그런데 addName메소드 안에 nameList.add있잖아.  nameList.add는 동기화 되면 안된다. 이 때 이런 Synchronized Statements가 유용 
-	Synchronized statements 은 세분화된 동기화로 동시성을 향상시키는 데 유용하다.

### 3. 다른 방법 
-	Atomic 나 Lock을 쓴 방법들이 아래 블로그에 소개되어 있다. 
	-	https://sujl95.tistory.com/63
 

### wait() notify() notifyAll() 
-	동기화의 효율을 높이기 위해서 wait(), notify()가 있다. 특정 상황으로 인해 한 쓰레드가 계속 lock을 가지고 있다면 다른 작업이 진행되지 않는다. 
	-	이 때 wait()을 걸어주면 쓰레드는 lock을 반납하고 기다린다. 
    -	기다리던 쓰레드는 notify()가 호출되면 다시 작업을 진행할 수 있다. 
    -	다만 기다리던 쓰레드가 여러 개일 때 notify()로 어떤 쓰레드가 깨어날지 보장을 못한다. 
    -	wait()가 걸리면 waiting pool에서 대기하는데 여기에는 wait()걸린 쓰레드들이 있다. 
    -	notify() 걸리면 이들 중 하나가 나간다.
    -	한 객체의 waitingPool에 있는 쓰레드를 다 깨우려면 notifyAll() 
    -	다 깨어났다 하더라도 이 깨어난 애들 중에 다른 누가 lock을 선점해서 lock을 얻지 못하면 다시 기다리는 상태로 
    -	이 waitingPool은 객체마다 존재하므로 notifyAll()이 호출되면 기다리던 그 객체의 쓰레드만 깨우고 다른 객체의 기다리던 쓰레드는 그대로 기다림  
## 학습 6) 데드락
-	https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html
-	호눅스 수업 
-	2개 이상의 쓰레드가 서로를 기다리며 영원히 blocked 된 상태다.
-	Alphonese와 Gaston이 친구가 있다. 이 둘 사이에는 한 친구가 인사를 하면 다른 친구는 그 친구가 인사를 끝낼 때까지 인사를 해야 하는 룰이 있다고 했을 때, 데드락이 발생할 수 있는 상황이 있다. 둘 다 동시에 인사하는 것. 그러면 다른 친구가 인사 끝날 때까지 서로 기다리게 된다. 
```java
public class Deadlock {
    public static void main(String[] args) {
        Friend alphonse = new Friend("Alphonse");
        Friend gaston = new Friend("Gaston");
        new Thread(new Runnable() {
            @Override
            public void run() {
                alphonse.bow(gaston);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                gaston.bow(alphonse);
            }
        }).start();
    }

    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
        }
    }
}
```
-	결과 : 계속 돌아감 
  -	 ![](https://images.velog.io/images/bongf/post/c67be1be-766c-4732-bac4-2895f71f2886/image.png)

    
---
### 출처 
-	자바의 정석 
-	오라클 본문에 링크 걸어둠 
-	학습1) 
	-	멀티쓰레드 : https://yadon079.github.io/2021/java%20study%20halle/week-10
