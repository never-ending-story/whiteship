## I/O란?

* Input과 Output의 약자로, 입력과 출력을 의미하며 컴퓨터에서의 I/O는 컴퓨터 내부나 외부의 장치와 프로그램간의 데이터를 주고받는 것이다.

* 컴퓨터로 예를 들어보자면, 키보드나 마우스로 입력하는 것이 Input, 모니터로 입력한 내용을 보여주거나 선택한 프로그램을 띄워주는 것이 Output이다.

* 자바로는 Scanner와 같이 화면상에서 입력받고, System.out.println()로 출력하는 것이다.

  

## 스트림 (Stream) / 버퍼 (Buffer) / 채널 (Channel) 기반의 I/O

### 스트림

* 데이터를 연결하고 데이터가 들어온 순서대로 운반되는 단방향의 통로다.
* 입력을 위한 InputStream과 출력을 위한 OutputStream이 있으며, 이는 아래에 자세히 설명 된다.
* 스트림은 queue와 같은 FIFO(First In First Out)구조로, 먼저 보낸 데이터를 먼저 받고 연속적으로 데이터를 주고받는다.
* 동기적, blocking 방식으로 동작하며 입출력을 요청 할 경우, 다시 입출력을 진행할 때 까지 무한정 대기상태에 빠진다.
* 자바 내의 기본 I/O시스템들은 Stream을 기반으로 사용되며, 위에 설명한 대로 입력이 될 때 까지 기다리기 때문에 사용을 끝낸 후 반드시 닫아줘야한다.
  닫아주지 않을 경우, 메모리 누수가 발생할 수 있기 때문에 예외처리를 하는것이 중요하다.

### 버퍼

* 임시로 데이터를 담아둘 수 있는 큐.
* 바이트 단위의 데이터가 입력될 때, 스트림은 매회 즉시 전송을 하는 것 때문에 디스크 접근이나 네트워크 접근의 관점에서 오버헤드가 발생할 수 있기 때문에 비효율적인 것에 비해, 중간에서 입력을 모아 한번에 출력함으로써 I/O의 성능을 향상시키는 역할을 한다. 데이터가 많을수록 이 체감이 더 잘 된다.
* 자바에서는 BufferedInputStream, BufferedOutputStream을 지원하며, 조금 더 자주 사용하는 것으로는 StringBuffer가 있다.

### 채널

* 기존에 존재하던 스트림은 blocking, Non-Buffer의 특징으로 입출력 속도가 느릴 수 밖에 없었는데, 이를 보완하기 위해 나타났다.
* New Input Output의 줄임말인 NIO의 기본 입출력 방식이다.
* 데이터를 연결하고, 데이터를 연결하는 양방향의 통로이다.
* 스트림과 유사해보이지만, 동작 방식이 다르다. 양방향 통신을 지원하기 때문에 Input/Output을 구분하지 않아 InputStream과 OutputStream을 별개로 생성할 필요가 없다.
* Buffer를 통해서 read와 write를 할 수 있는 버퍼 방식을 사용하며, blocking, Non-blocking방식도 지원한다. (Non-blocking으로 과도한 스레드 생성을 피하고, 스레드 재사용 가능)
* 다만 입출력 처리가 오래걸리는 경우, Non-blocking방식은 오히려 좋은 효율을 내지 못하고, 즉시 처리하는 IO보다 버퍼를 무조건 사용해야하는 복잡함때문에 불특정 다수의 클라이언트를 연결하거나, 하나의 입출력이 오래걸리지 않는 작업에 사용하는 것이 좋다. 



#### NIO vs IO

| 구분                       | IO                                            | NIO                                                 |
| -------------------------- | --------------------------------------------- | --------------------------------------------------- |
| 입출력 방식                | 스트림                                        | 채널                                                |
| 버퍼 방식                  | Non-Buffer                                    | Buffer                                              |
| 비동기 방식 지원           | X                                             | O                                                   |
| Blocking/Non-Blocking 방식 | Blocking Only                                 | Both                                                |
| 사용 케이스                | 연결 클라이언트가 적고, IO 가 큰 경우(대용량) | 연결 클라이언트가 많고, IO 처리가 작은 경우(저용량) |



## InputStream과 OutputStream

* InputStream과 OutputStream은 모든 바이트 기반 스트림의 조상이다.(최상위 추상 클래스)

  * InputStream: 데이터 입력

  * 버퍼, 파일, 네트워크 단에서 입력되는 데이터를 읽어오는 기능을 수행한다.

    | 메서드 명                            | 설명                                                         |
    | ------------------------------------ | ------------------------------------------------------------ |
    | int available()                      | 스트림으로부터 읽어 올 수 있는 데이터 크기를 반환            |
    | void close()                         | 스트림을 닫음으로써 사용하고 있던 자원을 반환                |
    | void mark(int readlimit)             | 현재 위치를 표시한다. reset()에 의해서 표시해 놓은 위치로 다시 돌아갈 수 있다.<br />readlimit은 되돌아 갈 수 있는 byte의 수다. |
    | boolean markSupported()              | mark()와 reset()을 지원하는지 알려준다. mark()와 reset()를 지원하는것은 선택적이기때문에,<br />지원 여부를 먼저 확인해야한다. |
    | abstract int read()                  | 1 byte를 읽어온다. 더 이상 읽어 올 데이터가 없으면 -1을 반환<br />추상메서드라서 상황에 맞게 구현해야한다. |
    | int read(byte[] b)                   | 배열 b의 크기만큼 읽어서 배열을 채우고, 읽어 온 데이터의 수를 반환.<br />반환 값은 항상 배열의 크기보다 작거나 같다. |
    | int read(byte[] b, int off, int len) | 최대 len개의 byte를 읽어, 배열 b의 지정된 위치(off) 부터 저장한다.<br />실제로 읽어올 수 있는 데이터가 len보다 적을 수 있음. |
    | void reset()                         | 스트림에서의 위치를 마지막으로 mark()가 호출되었던 위치로 되돌린다. |
    | long skip(long n)                    | 스트림에서 주어진 길이(n)만큼 건너뛴다.                      |

    

  * OutputStream: 데이터 출력

  * 버퍼, 파일, 네트워크 단에서 입력되는 데이터를 내보내는 기능을 수행한다.

    | 메서드 명                              | 설명                                                         |
    | -------------------------------------- | ------------------------------------------------------------ |
    | void close()                           | 입력소스를 닫음으로써 사용하고 있던 자원을 반환한다.         |
    | void flush()                           | 스트림의 버퍼에 있는 모든 내용을 출력소스에 쓴다             |
    | abstract void write(int b)             | 주어진 값을 출력소스에 쓴다                                  |
    | void write(byte[] b)                   | 주어진 배열 b에 저장된 모든 내용을 출력소스에 쓴다           |
    | void write(byte[] b, int off, int len) | 주어진 배열 b에 저장된 내용 중에서 off번째부터 len개 만큼만 읽어서 출력소스에 쓴다. |

    

* 바이트 시퀀스를 읽거나 쓰기위한 기본 기능을 정의해놓은 추상 클래스이며, 대상에 따라 알맞게 사용자가 구현할 수 있다.

* 자바 내에서 제공하는 스트림 클래스들은 다형성을 이용해 스트림을 계층화 할 수 있으며, 더 큰 데이터 유형 처리와 같이 더 높은 수준의 기능을 제공할 수 있다.

## Byte와 Character 스트림

### Byte Stream

* binary 데이터를 입출력하는 스트림으로, 데이터를 1바이트 단위로 처리한다.

* 이미지, 동영상 등을 송수신 할 때 주로 사용된다.

  | 입력 스트림          | 출력 스트림           | 입출력 대상의 종류          |
  | -------------------- | --------------------- | --------------------------- |
  | FileInputStream      | FileOutputStream      | 파일                        |
  | ByteArrayInputStream | ByteArrayOutputStream | 메모리(Byte 배열)           |
  | PipedInputStream     | PipedOutputStream     | 프로세스(프로세스간의 통신) |
  | AudioInputStream     | AudioOutputStream     | 오디오 장치                 |

  

### Character Stream

* text 데이터를 입출력하는 스트림으로, 데이터를 2바이트 단위로 처리한다.

* 일반적인 텍스트, JSON, HTML 등의 데이터를 송수신 할 때 주로 사용된다.

  | 바이트 기반 스트림                                           | 문자 기반 스트림                     |
  | ------------------------------------------------------------ | ------------------------------------ |
  | FileInputStream<br />FileOutputStream                        | FileReader<br />FileWriter           |
  | ByteArrayInputStream<br />ByteArrayOutputStream              | CharArrayReader<br />CharArrayWriter |
  | PipedInputStream<br />PipedOutputStream                      | PipedReader<br />PipedWriter         |
  | StringBufferInputStream(deperecated)<br />StringBufferOutputStream(deperecated) | StringReader<br />StringWriter       |



### 보조 스트림

* 스트림의 기능을 보완하기 위한 보조스트림이 제공되며, 이는 실제 데이터를 주고받는 스트림이 아니지만 기능을 향상시키거나 새로운 기능을 추가할 수 있다.
  종류는 다음과 같다.

  | 입력                  | 출력                 | 설명                                                         |
  | --------------------- | -------------------- | ------------------------------------------------------------ |
  | FilterInputStream     | FilterOutputStream   | 필터를 이용한 입출력 처리}<br />FilterReader, FilterWriter로 문자기반도 존재 |
  | BufferedInputStream   | BufferedOutputStream | 버퍼를 이용한 입출력 성능 향상<br />BufferedReader, BufferedWriter로 문자기반도 존재 |
  | DataInputStream       | DataOutputStream     | int, float와 같은 기본형 단위(primitive type)로 데이터를 처리 |
  | SequenceInputStream   |                      | 두 개의 스트림을 하나로 연결                                 |
  | LineNumberInputStream |                      | 읽어온 데이터의 라인 번호를 카운트(JDK 11 부터 LineNumberReader로 대체) |
  | ObjectInputStream     | ObjectOutputStream   | 데이터를 객체단위로 읽고 쓰는데 사용.<br />주로 파일을 이용하며 객체 직렬화와 관련있음 |
  |                       | PrintStream          | 버퍼를 이용하여 추가적인 print관련 기능<br />(print, printf, println 메서드)<br />PrintWriter로 문자기반도 존재 |
  | PushbackInputStream   |                      | 버퍼를 이용해서 읽어온 데이터를 다시 되돌리는 기능(unread)<br />PushbackReader로 문자기반도 존재 |

  

## 표준 스트림 (System.in, System.out, System.err)

* 콘솔(console, dos)을 통한 데이터 입력과 콘솔로의 데이터 출력을 의미한다.

* 자바 내에서는 System.in, System.out, System.err 세가지의 입출력 스트림을 지원한다.

  ```java
  public final class System {
    public static final InputStream in;
    public static final PrintStream out;
    public static final PrintStream err;
    ....
  }
  ```

  자바 어플리케이션 실행 시 별도의 설정 없이 바로 사용 가능한 스트림이기 때문에, 손쉽게 사용할 수 있다.
  System 클래스의 static 메서드로, 버퍼를 이용하는 BufferedInputStream, BufferedOutputStream의 인스턴스를 사용한다.

  * System.in : 콘솔로부터 데이터를 입력 받는다. CLI에서만 전달되기 때문에 자주 사용되지 않는다. 해당 기능이 작동하는 애플리케이션이 아닌, 다른 애플리케이션의 키보드 입력은 읽을 수 없다.
  * System.out : 콘솔로 데이터를 출력한다. CLI에서 많이 사용되며, 디버그를 위한 로그를 출력할 때 자주 사용된다.
  * System.err : 콘솔로 데이터를 출력한다. PrintStream이지만, 일반적으로 오류를 출력하는 데에 사용된다.

* setIn(), setOut(), setErr() 메서드의 매개변수에 PrintStream이나 InputStream 입력 시, 콘솔 이외에 txt파일 같이 다른 입출력 대상으로 변경할 수 있다. 

## 파일 읽고 쓰기

* FileReader, FileWriter나 FileInputStream, FileOutputStream으로 텍스트 데이터를 읽거나, 파일에 쓰는것이 가능하다.
  실제 프로그래밍에서 많이 사용되는 스트림은 FileInputStream, FileOutputStream이지만, 텍스트 파일을 다루는 경우 문자 기반의 FileReader, FileWriter를 사용하는 것이 더 좋다. FileInputStream, FileOutputStream의 경우, 한글이 깨져서 출력되는 경우가 있다.
  대신, FileInputStream, FileOutputStream의 경우, 바이너리 파일일 때 사용하는 것이 좋다.

  * FileReader, FileWriter

    ```java
    BufferedReader br = new BufferedReader(new FileReader("a.txt"));
    BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"));
    String s;
    while ((s = br.readLine()) != null) {
      bw.write(s + "\n");
    }
    ```

    

  * FileInputStream, FileOutputStream

    ```java
    BufferedInputStream is = new BufferedInputStream(new FileInputStream("a.jpg"));
    BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream("b.jpg"));
    byte[] buffer = new byte[16384];
    while (is.read(buffer) != -1) {
      os.write(buffer);
    }
    ```

    

* 입출력 효율을 위해 Buffered 계열의 보조 스트림을 함께 사용하는 것이 좋다