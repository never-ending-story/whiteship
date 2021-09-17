## 멀티쓰레드 프로그래밍

**프로세스란?**

실행중인 하나의 프로그램. 지금 쓰고 있는 타이포라도 하나의 프로세스이고 우리가 OS에서 실행하는 모든 프로그램은 프로세스다. 프로세스는 운영체제에 의해 메모리를 할당받아 실행된다. 프로세스는 프로그램에 사용되는 자원(데이터, 메모리 등)과 <u>쓰레드</u>로 구성되어있다.

**쓰레드란?**

프로세스의 가장 작은 실행 단위, 프로세스에서 실제 작업을 수행하는 주체가 쓰레드다. 하나의 프로세스는 반드시 하나 이상의 쓰레드를 가진다. 자바 프로그램에서 '반드시 하나'가 되는 쓰레드는 바로 `public static void main(String[] args)` 메인! 쓰레드가 하나 이상인 프로세스를 멀티 쓰레드 프로세스라고 한다.

### Thread 클래스와 Runnable 인터페이스

자바에서 쓰레드를 구현하는데에는 두가지 방법이 있다. 하나는 `Runnable` 인터페이스를 구현하는 것이고 또 다른 하나는 `Thread` 클래스를 상속받는 방법이다.  `Thread` 클래스가 `Runnable` 인터페이스의 구현체이므로 인터페이스냐 클래스냐의 차이지 뭘 쓰든 기능은 똑같다. 둘 중 하나를 골라 해당 쓰레드가 실행될 때 수행할 작업을 run() 메서드에 구현한다. (처음에 나는 쓰레드의 개념이 잘 이해가지 않았는데 또다른 메인 메서드를 만든다고 생각했다.)

**Extend Thread Class**

```java
public class ThreadExample extends Thread {
  @Override
  public void run() {
    // 실행할 작업
  }
}
```

```java
public static void main(String[] args) {
  ThreadExample threadExample = new ThreadExample();
  threadExample.start();
}
```

구현된 run 메서드는 start 메서드를 이용해 실행시킨다. run 메서드를 호출하면 쓰레드의 기능은 하지 않고 정말 run 메서드 그대로의 작업을 수행해서 결국 한 쓰레드 안에서 실행된다. start 메서드는 새로운 호출 스택을 생성하고 생성된 호출 스택에서 run을 실행시켜준다.

**Implement Runnable Interface**

```java
public class RunnableExample implements Runnable {
  @Override
  public void run() {
    // 실행할 작업
  }
}
```

```java
public static void main(String[] args) {
  Runnable runnable = new RunnableExample;
  Thread runnableExample = new Thread(runnable);
  runnableExample.start();
}
```

인터페이스 `Runnable`의 변수로 구현체의 객체를 할당하고 새로운 쓰레드 객체를 해당 러너블을 이용해 만든다.

## **쓰레드의 상태**

- new: 생성되고 아직 스타트 전의 상태
- runnable: 실행중 또는 실행가능한 상태
- blocked: 동기화 블럭에 의해 일시정지된 상태
- Waiting, timed-waiting = 종료되지 않았지만 실행가능하지 않은 일시정지 상태. (suspend(), sleep(), wait(), join(), I/O block)
- Terminated: 작업종료
  - blocked/time waiting 풀리는 경우: time-out(sleep에서 시간 끝났을때), resume() (서스펜드를 재개), notify() goes with wait(), interrupt() force break sleep() before timeout

## **쓰레드의 우선순위**

스레드는 `setPriority()`라는 메서드로 우선순위를 지정해줄 수 있다. 매개변수로는 1부터 10까지의 정수가 들어가고 숫자가 높을수록 우선순위가 높지만 이 우선순위는 그렇게 큰 의미가 있지 않다고 한다. 왜냐면 코드대로 우선순위를 부여해주는 식이 아니라 **스레드가 실행되고 스케쥴링 하는 것은** **OS 마음**이기 때문이다.

이 부분은 공부를 좀 더 해봐야하는 건지 잘 모르겠다. 다 OS 마음대로면.. setPriority()의 존재의미는 무엇일까.. 더 깊이 파고들면 오늘 잠을 못 잘 것 같아서 오늘은 여기까지만 하고 나중에 다시 살펴봐야겠다.

## **Main 쓰레드**

스레드라는 개념 자체가 작업의 수행하는 단위이기 때문에 위의 예시들처럼 따로 스레드를 생성해주지 않아도 프로그램이 작동중에 있다면 우리는 이미 스레드를 하나 이상 가지고있다. 그게 메인스레드! 모든 스레드는 메인스레드로부터 생성된다. **프로그램은 실행중인 스레드가 하나도 없을 때 종료된다**. 그 말인 즉슨 메인스레드가 종료되어도 다른 스레드가 작동중이면 프로그램은 계속 실행될 것이다.

```
public class MainThread() {
  public static void main(String[] args) {
    ThreadEx1 t1 = new ThreadEx1();
    ThreadEx2 t2 = new ThreadEx2();

    t1.start();
    t2.start();
    System.out.println("메인 끝");
  }
}

class ThreadEx1 extends Thread {
  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("ex1");
    }
  }
}

class ThreadEx2 extends Thread {
  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("ex2");
    }
  }
}
```

여기서 메인은 t1과 t2의 `start()`를 호출하고 "메인 끝"을 출력하는 일을 맡았다. 새로운 호출스택 2개에서 `run()`이 실행되고 각각의 일을 하겠지만 메인의 일은 이미 전에 끝나서 제일 빨리 종료되는 스레드는 메인스레드이다. 만약 여기서 메인에서 wait()을 실행시키면 다른 스레드가 끝날 때까지 기다리고 메인은 제일 나중에 끝날 것이다.

## **동기화**

멀티스레드 프로세스에서 같은 자원을 공유하며 작업하기때문에 스레드끼리 서로의 작업에 영향을 끼친다. 한 스레드가 작업중이던 도중에 제어권이 다른 스레드로 넘어가 먼저 실행되던 스레드가 쓰던 데이터를 변경하면 결과물은 달라질 것이다. 이 때 필요한 게 스레드의 동기화이다. **스레드가 진행중인 작업을 다른 스레드가 간섭하지 못하도록 막는 것**을 동기화라고 한다.

```
public synchronized void example() {
  for (int i = 0; i < 10; i++) {
      System.out.println("ex1");
    }
}
```

`synchronized` 키워드가 붙은 메서드는 전체가 임계영역, critical section으로 설정되고 이 메서드는 lock을 얻어 작업을 수행하다가 메서드가 종료되면 lock을 반환한다. 해당 메서드가 실행될 동안은 제어권이 다른 스레드로 넘어가지 않는다.

## **데드락**

데드락은 한 자원을 여러 시스템이 사용하려고 할 때 발생한다. 두 개 이상의 스레드가 서로를 기다리며 block 상태에 머물러 있는 상황이다.

데드락은 네 가지 조건이 성립할 때 발생하고 이 조건들중 하나라도 성립하지 않게 만들면 데드락 상태를 풀 수 있다.

1. 상호배제(Mutual Exclusion): 자원은 한번에 한 프로세스만이 사용할 수 있어야 한다.
2. 점유대기(Hold and wait): 하나의 자원을 점유하고 있으면서 다른 프로세스에 할당되어 사용하고 있는 자원을 추가로 점유하기 위해 대기하는 프로세스가 있어야 한다.
3. 비선점(No Preemption): 다른 프로세스에 할당된 자원은 사용이 끝날 때까지 강제로 빼앗을 수 없어야 한다.
4. 순환대기(Circular Wait): 프로세스의 집합 {P0, P1, ,…Pn}에서 P0는 P1이 점유한 자원을 대기하고 P1은 P2가 점유한 자원을 대기하고 P2…Pn-1은 Pn이 점유한 자원을 대기하며 Pn은 P0가 점유한 자원을 요구해야 한다. (이거 그냥 서로가 서로를 기다리는 그런건가?)