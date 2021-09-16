
### 멀티스레딩(Multithreading)이란?

하나의 애플리케이션 내에서 멀티 스레드를 수행하는 것이다.

**다중 스레드 애플리케이션 (Multithreaded application)** 은 여러 CPU가 동시에 코드의 다른 부분을 수행하는 애플리케이션을 말한다. 

</br>



### Process와 Thread

**Process**

실행 중인 프로그램이라고 볼 수 있다.

 OS에 의해 메모리 공간을 할당 받아 실행 중인 프로그램이다. 프로세스는 데이터와 메모리와 같은 자원 그리고 스레드로 구성되어있다.

</br>



**Thread**

프로세스 내에서 작업을 수행하는 주체, 가장 작은 실행 단위

모든 프로세스는 하나 이상의 스레드가 존재한다.
</br>


----

### 스레드의 상태

- NEW : 스레드가 생성되고 아직 `start()` 가 호출되지 않은 상태
- RUNNABLE : 실행 중, 혹은 실행 가능한 상태
- BLOCKED : 동기화 블럭에 의해서 일시정지된 상태 (lock이 풀릴 때 까지 기다리는 상태)
- WAITING, TIME_WAITING : 스레드의 작업이 종료되지는 않았지만 실행 가능하지 않은 일시정지 상태, TIME_WAITING은 일시정지 시간이 지정된 경우 
- TERMINATED : 스레드의 작업이 종료된 상태 
</br>


-----

### 스레드의 우선순위

Thread클래스는 우선순위(priority) 멤버변수를 갖고 있다. 이 값을 서로 다르게 지정해서 스레드의 우선순위를 지정할 수 있다. 

`getPriority()`와 `setPriority()`를 이용해서 스레드의 우선순위를 얻거나 변경할 수 있다. 

우선순위가 가질 수 있는 범위는 1~10이고 숫자가 높을수록 우선순위가 높다. 우선순위는 상대적인 값이다. 

</br>



----

### 스레드를 생성하는 방법 

1. Thread 클래스를 부모 클래스로 갖는 클래스를 생성한다.

   ```java
   public class MyThread extends Thread{
   
       @Override
       public void run() {
           System.out.println("Thread is running created by extending Thread class");
       }
   
       public static void main(String[] args) {
           MyThread myThread = new MyThread();
           myThread.start();
       }
   }
   ```

   Thread 클래스는 Runnable의 구현체이다. 그러나 `run()`메서드에서 아무런 동작을 하지 않기때문에 재정의 해줘야한다. 

   
</br>


2. Runnable 인터페이스를 구현한다.

   ```java
   public class ThreadUsingInterface implements Runnable {
       @Override
       public void run() {
           System.out.println("Thread is running created Using Runnable interface");
       }
   
       public static void main(String[] args) {
           ThreadUsingInterface obj = new ThreadUsingInterface();
           Thread myThread = new Thread(obj);
           myThread.start();
       }
   }
   ```

   Runnable 객체가 Thread 생성자의 인자로 전달된다. 

</br>



두 가지 방법 모두  `start()`메서드의 호출로 스레드를 시작한다. 이 메서드는 `run()` 메서드를 찾아 실행을 시작한다. 

**어떤 방법을 사용해야 할까?**

- 스레드 클래스가 다른 클래스를 상속받아야 하는 경우에는 Runnable 인터페이스를 구현하고, 그렇지 않는 경우에는 
  Thread 클래스를 상속받는 것이 편하다.  Java는 단일 상속만 지원하기 때문에.
- Thread 클래스도 Runnable의 구현체이다. 

</br>



**start()와 run()의 차이점**
스레드를 구현할 때는 `run()`메서드를 구현한다. 그런데 스레드를 시작할 때는 `start()` 를 호출한다. 

`run()` 을 호출하는 것은 스레드를 실행시키는 것이 아니라 단순이 메서드를 호출하는 것이다. 반면, `start()`를 호출하면 <span style=color:green>**새로운 스레드가 작업을 실행하는데 필요한 새로운 호출 스택(call stack)을 생성한 이후에 `run()` 을 호출**</span>한다. 즉, 새로 생성된 호출 스택에 `run()`이 첫 번째로 올라간다. `run()`메서드의 수행이 종료된 스레드는 생성된 호출 스택이 소멸된다. 

</br>



**메인 스레드**

main 메서드의 작업을 수행하는 스레드를 메인 스레드라고 한다. 

</br>



----

### Thread 클래스 

각각의 스레드는 어떤 스레드가 먼저 실행되야 하는지 결정하는 스레드 스케줄러에 의해 우선순위를 갖는다. 

Java는 스레드의 동작을 관리하기 위해 Thread 클래스를 제공한다. 

</br>



**Thread 생성자**

| 생성자                                                       | 수행되는 행동                                                |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Thread()                                                     | 새로운 Thread 객체 할당                                      |
| Thread(Runnable target)                                      | 새로운 Thread 객체 할당                                      |
| Thread(Runnable target, String name)                         | 새로운 Thread 객체 할당                                      |
| Thread(String name)                                          | 새로운 Thread 객체 할당                                      |
| Thread(ThreadGroup group, Runnable target)                   | 새로운 Thread 객체 할당                                      |
| Thread(ThreadGroup group, Runnable target, String name)      | 실행할 객체 target이 지정되고, 지정된 이름을 가지고, 스레드 그룹에 새 Thread 객체를 할당한다. |
| Thread(ThreadGroup group, Runnable target, String name, long stackSize) | 실행할 객체 target이 지정되고, 지정된 이름을 가지고, 스레드 그룹에 새 Thread 객체를 할당하고 지정한 크기의 stack 크기를 갖는다. |
| Thread(ThreadGroup group, String name)                       | 새로운 Thread 객체 할당                                      |

</br>



### Thread 클래스의 메서드

**sleep**

일정 시간동안 실행을 일시 중지 시키는 것

- `sleep(long millis)`
- `sleep(long millis, int nanos)`

매개변수의 시간은 OS에서 제공하는 기능에 따라 제한되기 때문에 정확하다고 보장할 수 없다.

sleep은 interrupt에의해 종료될 수 없다. 따라서 sleep이 정확히 지정된 시간동안 스레드를 일시 중단 한다고 보장할 수 없다.

```java
public class SleepMessages {
    public static void main(String[] args) throws InterruptedException {
        String[] importantInfo = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for (int i = 0; i < importantInfo.length; i++) {
            Thread.sleep(4000);
            System.out.println(importantInfo[i]);
        }
    }
}
```

`importantInfo`배열의 요소가 4초에 하나씩 출력된다. 

sleep이 활성화 된 상태에서 다른 스레드가 현재 스레드를 interrupt 하면 InterruptedException이 발생한다. 


</br>


**interrupt**

interrupt는 스레드가 현재 진행 중인 작업을 멈추고 다른 작업을 수행하도록 할 때 사용된다. 

한 스레드가 InterruptedException을 던지는 메서드(sleep과 같은)를 자주 호출한다면 catch 문에서 예외를 처리하고 그저 메서드를 종료(return)함으로서 interrupt를 처리할 수 있다. 

```java
String[] importantInfo = {
  "Mares eat oats",
  "Does eat oats",
  "Little lambs eat ivy",
  "A kid will eat ivy too"
};

for (int i = 0; i < importantInfo.length; i++) {
  try {
    Thread.sleep(4000);
  } catch (InterruptedException e) {
    return;
  }
  System.out.println(importantInfo[i]);
}
```

`sleep()` 과 같이 InterruptedException을 던지는 여러 메서드들은 interrupt가 발생하면 현재 작업을 즉시 중단하도록 설계되었다. 

</br>



**interrupt 예시**

```java
public static void main(String[] args) {
    Thread thread = new Thread(() -> {
      String[] importantInfo = {
        "Mares eat oats",
        "Does eat oats",
        "Little lambs eat ivy",
        "A kid will eat ivy too"
      };

      for (int i = 0; i < importantInfo.length; i++) {
        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          System.out.println("This thread has been interrupted!!!");
          return;
        }
        System.out.println(importantInfo[i]);
      }
    });

    thread.start();
    thread.interrupt();
}


// 실행 결과
This thread has been interrupted!!!

```

</br>


**join**

- `join()`
  - 스레드의 작업이 끝날 때까지 기다린다. 
- `join(long millis)`
- `join(long millis, int nanos)`

하나의 스레드가 다른 스레드가 종료될 때까지 기다릴때 사용한다. 

만약 t 라는 스레드가 수행 중인데 `t.join()`을 현재 스레드는 t라는 스레드가 작업을 완료할때까지 기다린다. 

또한 기다리는 시간을 지정할 수도 있다. 이 경우에는 정해진 시간동안만 기다린다. 

`sleep` 과 마찬가지로 join도 inttrupt 되면 InterruptedException이 발생된다. 

```java
public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(() -> {
      String[] importantInfo = {
        "Mares eat oats",
        "Does eat oats",
        "Little lambs eat ivy",
        "A kid will eat ivy too"
      };

      for (int i = 0; i < importantInfo.length; i++) {

        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          return;
        }
        System.out.println(importantInfo[i]);
      }
    });

    thread.start();
    thread.join();

    System.out.println("End!!!!");
}

// 실행 결과
Mares eat oats
Does eat oats
Little lambs eat ivy
A kid will eat ivy too
End!!!!
```

메인 스레드가 `thread`의 작업이 수행될 때까지 기다린 후에 `End!!!!` 가 출력된다.

</br>


-----

### 동기화 Synchronization

다음 예제를 보자.

```java
public class Run {
    public static void main(String[] args) {
        Counter counter = new Counter();
        PlusThread plusThread = new PlusThread(counter);
        MinusThread minusThread = new MinusThread(counter);

        plusThread.start();
        minusThread.start();
        try {
            plusThread.join();
            minusThread.join();
            System.out.println("Final value is " + counter.getAmount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class PlusThread extends Thread {
    private Counter counter;

    public PlusThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.plus(1);
        }
    }
}


class MinusThread extends Thread {
    private Counter counter;

    public MinusThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.minus(1);
        }
    }
}

class Counter {
    private int amount;

    public void plus(int value) {
        amount += value;
    }

    public void minus(int value) {
        amount -= value;
    }

    public int getAmount() {
        return amount;
    }
}


// 실행 결과 
Final value is -1288
```

실행 결과는 `0`이어야 할거 같지만 아니다. 심지어 수행할 때마다 다른 결과가 나온다. 

위의 예제는 서로 다른 스레드가 같은 프로세스 내의 자원을 공유해서 서로의 작업에 영향을 주어서 발생한 문제다.


</br>

이러한 문제를 방지하기 위해 한 스레드가 다른 스레드에 의해 방해받지 않도록 해야한다. 임계 영역(critical section)과 lock이 이를 위해 도입되었다.

공유 자원 접근 순서에 따라 실행 결과가 달라지는 영역을 임계 영역이라고 한다. 

같은 자원에 접근하는 코드를 임계 영역으로 지정해두고, 공유 데이터(객체)가 갖고 있는 lock을 획득한 하나의 스레드만 이 영역의 코드를 수행할 수 있도록 한다. 그리고 임계 영역의 코드 수행을 마치고 lock을 반납해야지만, 다른 스레드가 반납된 lock을 획득하여 임계 영역의 코드를 수행할 수 있도록 한다. 

한 스레드가 작업 중일 때 다른 스레드가 간섭하지 못하도록 막는 것을 **동기화** 라고 한다. 

</br>


### synchronized 키워드

synchronized 키워드는 임계 영역을 설정하는데 사용된다. 

메서드 전체를 임계 영역으로 지정하는 방법과 특정 구문을 임계 영역으로 지정하는 방법이 있다. 

</br>


**Synchronized methods** 

메서드 전체를 임계 영역으로 지정하는 방법이다. 

메서드에 synchronized 키워드를 추가해보자.

```java
class Counter {
    private int amount;

    public synchronized void plus(int value) {
        amount += value;
    }

    public synchronized void minus(int value) {
        amount -= value;
    }

    public int getAmount() {
        return amount;
    }
}
```

이 부분만 수정해서 위의 예제를 실행시키면 결과가 `0` 이 나온다. 

<img width="466" alt="스크린샷 2021-09-16 오후 4 40 29" src="https://user-images.githubusercontent.com/65011131/133657336-115448d2-e6bc-4173-98e6-d50e12960775.png">



</br>




**Synchronized Statements**

이 방법은 코드 일부분을 블럭{}으로 감싸고 블럭 앞에 `synchronized(참조변수)` 를 붙이는 것이다. 

여기서 참조변수는 락을 걸고자 하는 객체를 참조하는 것이어야 한다. 

이 블럭의 영역을 들어가면서 스레드는 지정된 객체의 lock을 얻는다. 

</br>



Synchronized 메서드 보다는 Synchronized 구문으로 필요한 부분만 임계 영역으로 지정하는 것이 더 효율적이다. 

</br>



### volatile

멀티 코어 프로세서에는 코어마다 별도의 캐시를 갖고있다.

코어는 메모리에서 읽어온 값을 캐시에 저장하고 캐시에서 값을 읽어서 작업한다. 같은 값을 다시 읽을 때는 캐시를 먼저 확인하고 값이 없을 때만 메모리에서 읽어온다. 캐시에 저장된 값이 메모리에서 변경된 값으로 갱신되지 않는 상황이 발생할 수 있다. 

volatile 키워드를 붙이면 코어가 변수 값을 캐시가 아닌 메모리에서 읽어온다. 

변수의 값을 write할 때도 메모리에 작성한다. 

final 변수는 Thread-safe하기 때문에 volatile을 붙일 필요가 없다. 따라서 volatile은 final 변수에 붙일 수 없다. 

</br>



---

### 데드락(Deadlock)

Deadlock(교착상태) 란, 둘 이상의 스레드가 lock을 획득하기 위해 기다릴 때, lock을 갖고 있는 스레드들도 똑같이 다른 lock을 기다리면서 서로 block 상태에 놓이는 것이다.

다수의 스레드가 같은 lock을 동시에, 다른 명령에 의해 획득하려고 할 때 발생할 수 있다.

</br>

</br>





https://vallista.kr/2019/12/28/%EB%8F%99%EC%8B%9C%EC%84%B1%EA%B3%BC-%EB%B3%91%EB%A0%AC%EC%84%B1-Concurrency-Parallelism

https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html

https://sujl95.tistory.com/63

https://parkadd.tistory.com/48

자바의 정석 
