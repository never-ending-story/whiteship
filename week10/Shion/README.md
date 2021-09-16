## 10주차 : 쓰레드

### 1. Process vs Thread

#### Process
- 간단히 말해서 '**실행 중인 프로그램**'이다.
- 프로그램을 실행하면 OS로부터 시랭에 필요한 메모리를 할당받아 **프로세스**가 된다.
- 프로그램을 수행하는데 필요한 데이터와 메모리 등의 자원 그리고 쓰레드로 구성되어 있다.

#### Thread
- 프로세스의 자원을 이용해서 **실제로 작업을 수행하는 주체**.
- 모든 프로세스는 최소한 하나 이상의 쓰레드가 존재한다.
- 둘 이상의 쓰레드를 가진 프로세스를 '**멀티쓰레드 프로세스**'라고 한다.

### 2. Runnable vs Thread

- 쓰레드를 구현하는 방법은 `Thread`클래스를 상속받는 방법과 `Runnable`인터페이스를 구현하는 방법, 두가지가 있다.

```java
// Thread클래스를 상속
class ThreadSample extends Thread {
    @Override
    public void run() {
        System.out.println("Thread클래스를 상속");
    }
}

// Runnable인터페이스를 구현
class RunnableSample implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable인터페이스를 구현");
    }
}
```

#### 실행

```java
// Thread클래스를 상속받은 경우와 Runnable인터페이스를 구현한 경우의 방법이 다르다.
public static void main(String[] args) {
    Thread thread1 = new ThreadSample();
    thread1.start();
    
    Runnable runnable = new RunnableSample();
    Thread thread2 = new Thread(runnable);
    thread2.start();
}
```
#### 결과
```java
Thread클래스를 상속
Runnable인터페이스를 구현
```

- 쓰레드를 실행하는 `start()`메서드가 `Thread`클래스에 있기 때문에 `Thread`타입의 인스턴스(`thread2`)를 통해서 `Runnable`구현체를 실행해야한다.

```java
public class Thread implements Runnable {
    /* What will be run. */
    private Runnable target;

    public Thread(Runnable target) {
        // ...    
     }

    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    // ...
```
- `Thread`클래스 내부에 `Runnable`타입의 변수 `target`을 선언해 놓고 생성자를 통해서 주입한다.
- `start()`메서드 실행시 `Runnable`인터페이스에 정의된 `run()`메서드가 실행되는 것을 볼 수 있다.
  
```java
public static void main(String[] args) {
    // 익명 내부 클래스로 구현할 수도 있다.
    new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("Runnable인터페이스를 구현");
        }
    }).start();

    // 람다식으로도 구현할 수 있다.
    new Thread(() -> System.out.println("Runnable인터페이스를 구현")).start();
}
```

### 3. start() 와 run()

- `run()`을 호출하는 것은 **생성된 쓰레드를 실행시키는 것이 아니라 단순히 클래스에 선언된 메서드를 호출하는 것**이다.
  <img width="310" alt="스크린샷 2021-09-15 오후 2 28 42" src="https://user-images.githubusercontent.com/46085281/133375934-bb1b58e9-8eba-4e79-86c6-1745ca972b5b.png">

- `start()`는 새로운 쓰레드가 작업을 실행하는데 필요한 호출스택을 생성한 다음 `run()`을 호출해서 첫 번째로 올라가게 한다.
- **모든 쓰레드**는 독립적인 작업을 수행하기 위해 **자신만의 호출스택을 필요**로 한다.

![start()](https://user-images.githubusercontent.com/46085281/133377095-0d7b3238-ac5c-4201-bc45-405b9159f5e4.gif)

### 4. main쓰레드

- 위에서 보는바와 같이 `main()`메서드도 하나의 쓰레드 위에서 돌아가게 된다.
- Java는 JVM에서 돌아가게 되는데, 이 JVM이 하나의 프로세스이고, 이 프로세스는 최소한 하나의 쓰레드를 가진다. 
- 자바 프로그램을 시작하게 되면, 곧바로 하나의 쓰레드가 시작되고 이것이 우리 프로그램의 `main`쓰레드가 된다.
- `main`쓰레드에서 `main()`메소드를 찾아 호출해서 작업이 시작되는 것이다.

![main-thread-in-java](https://user-images.githubusercontent.com/46085281/133380890-3d12e6e3-a132-4f90-83dd-6393387bebeb.jpeg)

출처 : https://www.geeksforgeeks.org/main-thread-java/?ref=lbp

```java
public static void main(String[] args) {
    String threadName = Thread.currentThread().getName();
    System.out.println("쓰레드 이름 : " + threadName);
}
```
결과
```
쓰레드 이름 : main
```
### 5. 싱글쓰레드와 멀티쓰레드

![멀티쓰레드 001](https://user-images.githubusercontent.com/46085281/133550419-65bf0f29-cd23-4745-ad46-d4f78cd39a1b.jpeg)

- ***그림 2에서 두 쓰레드가 실행되는 순서와 시간은 OS스케줄러가 결정하기 때문에 우리가 알 수 없다.*** 그림은 이렇게 그렸지만 실제로는 다르게 돌아갑니다.
- 그림1은 하나의 쓰레드로 두개의 작업을 수행하는 경우(**싱글쓰레드**)이고, 그림2는 두 개의 쓰레드로 두개의 작업을 수행하는 경우(**멀티스레드**)이다.
- 두 경우의 작업 수행 시간은 비슷하다.
- 오히려 두 개의 쓰레드로 작업한 시간이 싱글쓰레드로 작업한 시간보다 더 걸리게 되는데, 그 이유는 **컨텍스트 스위칭** 때문이다.

#### Q. 그렇다면 멀티쓰레딩이 시간이 더 걸리는데 장점이 뭘까?
- #### A. 시간이 조금 더 걸리더라도 두 가지 일을 동시에 할 수 있다는 것이 장점이다.

![멀티쓰레드2 001](https://user-images.githubusercontent.com/46085281/133552733-0d3fa5ea-3ab6-40e7-a8eb-3374afc8da25.jpeg)

- 두 쓰레드가 서로 다른 자원을 사용하는 작업의 경우에는 싱글쓰레드보다 멀티쓰레드가 더 효율적이다.
- 만일 사용자로부터 입력받는 작업(A)과 화면에 출력하는 작업(B)을 하나의 쓰레드로 처리한다면 사용자가 입력을 마칠 때까지 아무 일도 하지 못하고 기다리기만 해야 한다.
- 그러나 두 개의 쓰레드로 처리한다면 사용자의 입력을 기다리는 동안 다른 쓰레드가 작업을 처리할 수 있기 때문에 보다 효율적인 작업이 가능하다.

### 6. 데몬 쓰레드
- 데몬 쓰레드는 다른 일반 쓰레드(데몬 쓰레드가 아닌 쓰레드)의 작업을 돕는 **보조적인 역할**을 하는 쓰레드다.
- **일반 쓰레드가 모두 종료되면 데몬 쓰레드는 강제적으로 자동 종료된다.**
- 데몬 쓰레드의 예로는 가비지 컬렉터, 워드프로세서의 자동저장, 화면자동갱신 등이 있다.
- 데몬 쓰레드는 무한루프와 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족되면 작업을 수행하고 다시 대기하도록 작성한다.
- 일반 쓰레드의 작성방법과 실행방법이 같으며 다만 쓰레드를 생성한 다음 실행하기 전에 `setDaemon(true)`를 호출하면 된다.
- 데몬 쓰레드가 생성한 쓰레드는 자동적으로 데몬 쓰레드가 된다.

```java
boolean isDaemon()          // 쓰레드가 데몬 쓰레드인지 확인한다.
void setDaemon(boolean on)  // 쓰레드를 데몬 쓰레드(true)로 또는 사용자 쓰레드(false)로 변경한다.
```

### 7. 쓰레드의 우선순위

- 쓰레드는 우선순위(priority)라는 속성(멤버변수)을 가지고 있는데, 이 우선순위의 값에 따라 쓰레드가 얻는 실행시간이 달라진다.
- 쓰레드마다 우선순위를 서로 다르게 지정하여 특정 쓰레드가 더 많은 작업시간을 갖도록 할 수 있다.

```java
public final int getPriority()                 // 쓰레드의 우선순위를 반환한다.
public final void setPriority(int newPriority) // 쓰레드의 우선순위를 지정한 값으로 변경한다.

public static final int MIN_PRIORITY = 1;  // 최소우선순위
public static final int NORM_PRIORITY = 5; // 보통우선순위
public static final int MAX_PRIORITY = 10; // 최대우선순위
```
- 쓰레드 우선순위의 범위는 1 ~ 10이며 숫자가 높을 수록 우선순위가 높다.
- 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받는다.
- `main()`메서드를 수행하는 `main`쓰레드는 우선순위가 5이므로, `main`메서드 내에서 생성하는 쓰레드의 우선순위는 자동으로 5가 된다.
- **쓰레드의 우선순위를 다르게 한다고 하더라도**, 쓰레드 작업할당은 OS의 스케쥴링 정책과 JVM의 구현에 따라 다르기 때문에 **우선순위에 따라 실행시간이나 실행기회가 보장된 것은 아니다**.
- 어느 정도 예측만 가능한 정도일 뿐, 실제 작업은 내가 설정한 우선순위와 다르게 진행될 수 있다.

### 8. 쓰레드의 상태

|상태|설명|
|-|-|
|NEW|쓰레드가 생성되고 아직 `start()`가 호출되지 않은 상태|
|RUNNALBE|실행 중 또는 실행 가능한 상태|
|BLOCKED|동기화블럭에 의해서 일시정지된 상태(lock이 풀릴 때까지 기다리는 상태)|
|WAITING, TIMED_WAITING|쓰레드의 작업이 종료되지는 않았지만 실행가능하지 않은(unrunnable) 일시정지 상태, TIMED_WAITING은 일시정지시간이 지정된 경우를 의미한다.|
|TERMINATED|쓰레드의 작업이 종료된 상태|

- 위 쓰레드 상태는 메서드를 통해 제어할 수 있다.
- 다음 그림은 쓰레드의 생성부터 소멸까지의 모든 과정을 보여준다.

![쓰레드의 상태 001](https://user-images.githubusercontent.com/46085281/133542341-a3d60d58-a8f4-4498-8bbb-cb8a28f0dd33.jpeg)

1. 
   - 쓰레드를 생성하고 `start()`를 호출하면 **바로 실행되는 것이 아니라 실행대기열에 저장**되어 자신의 차례가 될 때까지 기다려야 한다.

   - 실행대기열은 큐(Queue)와 같은 구조로 **먼저 실행대기열에 들어온 쓰레드가 먼저 실행**된다.
   
2. 
   - 실행대기상태에 있다가 **자신의 차례가 되면 실행상태**가 된다.
3. 
   - 주어진 실행시간이 다 되거나 `yield()`를 만나면 다시 실행대기상태가 되고 다음 차례의 쓰레드가 실행상태가 된다.
4. 
   - 실행 중에 `suspend()`, `sleep()`, `wait()`, `join()`, I/O block에 의해 일시정지상태가 될 수 있다.
   - I/O block은 입출력작업에서 발생하는 지연상태를 말한다. 사용자의 입력을 기다리는 경우를 예로 들 수 있는데, 이런 경우 일시정지 상태에 있다가 사용자가 입력을 마치면 다시 실행대기상태가 된다.
5. 
   - 지정된 일시정지 시간이 다 되거나 time-out, `notify()`, `resume()`, `interrupt()`가 호출 되면 일시정지상태를 벗어나 다시 실행대기열에 저장되어 자신의 차례를 기다리게 된다.
6. 
   - 실행을 모두 마치거나 `stop()`이 호출되면 쓰레드는 소멸된다.

`1 ~ 6 번호 순서로 쓰레드가 수행되는 것은 아니다.`

### 7. 동기화

- 멀티 쓰레드 프로세스에서는 다른 쓰레드의 작업에 영향을 미칠 수 있다.
- 진행중인 작업이 다른 쓰레드에게 간섭받지 않게 하려면 '**동기화**'가 필요하다.
  
```
쓰레드의 동기화 - 한 쓰레드가 진행중인 작업을 다른 쓰레드가 간섭하지 못하게 막는 것
```
- 동기화하려면 간섭받지 않아야 하는 문장들을 '**임계 영역**'으로 설정
- 임계영역은 락(lock)을 얻은 단 하나의 쓰레드만 출입가능 (객체 1개에 락 1개)

#### synchronized를 이용한 동기화
```java
// 1. 메서드 전체를 임계 영역으로 지정
public synchronized void calcSum() {
    //...
}

// 2. 특정한 영역을 임계 영역으로 지정
synchronized(객체의 참조변수) {
    // ...
}
```

- 이 블럭을 `synchronized`블럭 이라고 부르며, 이 블럭의 영역 안으로 들어가면서부터 쓰레드는 지정된 객체의 lock을 얻게 되고, 이 블럭을 벗어나면 lock을 반납한다. (자동)

```java
public class Main {
    public static void main(String[] args) {
        Runnable r = new RunnableSample();
        new Thread(r).start();
        new Thread(r).start();
    }
}

class Account {
    private int balance = 1_000;

    public int getBalance() {
        return this.balance;
    }

    public void withdraw(int money) {
        if (balance >= money) {
            try {
                Thread.sleep(1_000L);
            } catch (InterruptedException e) { }
            balance -= money;
        }
    }
}

class RunnableSample implements Runnable {
    // 작업에 필요한 Account객체 하나
    Account account = new Account();

    @Override
    public void run() {
        while(account.getBalance() > 0) {
            // 100, 200, 300 중 한 값을 임의로 선택해서 출금
            int money = (int) (Math.random() * 3 + 1) * 100;
            account.withdraw(money);
            System.out.println("balance : " + account.getBalance());
        }
    }
}
```
#### 결과
```
balance : 700
balance : 700
balance : 500
balance : 300
balance : -100
balance : 0
```
- 위의 예제는 하나의 `Account` 객체에 두 개의 쓰레드가 작업한 결과다.
- A 쓰레드가 `withdraw()`의 `if`문을 통과하여, 잔고 300에서 200을 출금하려는 찰나, B 쓰레드가 200을 먼저 출금해버려서 A는 잔고 100 [300(잔고) - 200(B의 출금)]에서 200을 출금하게 되고 잔고는 -100이 찍혀버렸다.
- 이처럼 한 쓰레드의 작업이 다른 쓰레드에 의해서 영향을 받는 일이 발생할 수 있기 때문에 **동기화**가 반드시 필요하다.

```java
public synchronized void withdraw(int money) {
        if (balance >= money) {
            try {
                Thread.sleep(1_000L);
            } catch (InterruptedException e) { }
            balance -= money;
        }
    }
```
- 이제 한 쓰레드에 의해서 먼저 `withdraw()`가 호출되면, 이 메서드가 종료되어 lock이 반납될 때까지 다른 쓰레드는 `withdraw()`를 호출하더라도 **대기상태**에 머물게 된다.
#### 결과
```
balance : 900
balance : 700
balance : 500
balance : 200
balance : 100
balance : 100
balance : 100
balance : 0
balance : 0
```
- 이때 `balance`의 접근제어자가 `private`가 아니면 아무리 동기화를 해도 외부에서 직접 접근하여 값을 변경할 수 있게 된다.
  
### 8. notify(), wait()

- `synchronized`로 동기화해서 공유 데이터를 보호하는 것 까지는 좋다.
- 하지만 특정 쓰레드가 객체의 락을 가진 상태로 오랜 시간을 보내지 않도록 하는 것도 중요하다.
- 만약 그렇다면 다른 쓰레드들은 모두 해당 객체의 락을 기다리느라 다른 작업들도 원활히 진행되지 않을 것이기 때문이다.
- 이러한 상황을 개선하기 위해 고안된 것이 바로 `wait()`와 `notify()`이다.
- 동기화된 임계영역의 코드를 수행하다가 작업을 더 이상 진행할 상황이 아니면, 일단 `wait()`을 호출하여 쓰레드가 락을 반납하고 기다리게 한다.
- 그러면 다른 쓰레드가 락을 얻어 해당 객체에 대한 작업을 수행할 수 있게 된다.
- 나중에 작업을 진행할 수 있는 상황이 되면 `notify()`를 호출해서, 작업을 중단했던 쓰레드가 다시 락을 얻어 작업을 진행할 수 있게 한다.

### 9. 데드락

- `synchronized`키워드는 클래스나 메서드를 **Thread-Safe**하게 만들기 위해 사용됩니다.
- 이것은 **곧 오직 하나의 쓰레드만**이 `synchronized` 메서드의 **락(lock)** 을 점유할 수 있고 사용할 수 있다는 것을 의미합니다.
- 다른 쓰레드들은 해당 메서드에 접근하려면, 이 점유된 **락(lock)** 이 풀리고 자신이 **락(lock)** 을 얻을 때까지 기다려야 합니다.
- ***데드락***이란, 각각의 쓰레드가 자원(클래스나 메서드)의 **락**을 점유한 상태로, 이미 또다른 스레드에게 점유된 자원의 **락**에 접근할 때 생기는 상태입니다.
- 두개의 쓰레드는 각각 상대방이 원하는 자원의 락을 본인이 소유하고 있고, 서로 본인이 점유한 자원의 락을 해체하지 않은 채, 상대방 자원의 락을 점유하려할 때 하염없이 기다리는 모습을 뜻합니다.

![데드락 001](https://user-images.githubusercontent.com/46085281/133548158-4478ac21-a9a2-4c18-a65e-17df4e98145b.jpeg)

