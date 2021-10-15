## **입출력이란?**

프로그램상으로 들어오는 값을 input data, 밖으로 나가는 값을 output data라고 한다. 입출력은 이처럼 프로그램을 기준으로 데이터가 안으로 들어오고, 또 밖으로 나가는 것을 뜻한다.

자바에서는 입출력을 위해 [java.io](http://java.io) 패키지를 제공한다. 여기서 크게 바이트단위의 입출력과 문자단위의 입출력으로 나눌 수 있다. 바이트 단위의 입출력은 InputStream/OutputStream 추상클래스를 상속받고 문자단위의 입출력은 Reader/Writer 추상클래스를 상속받는다.

## **스트림 (Stream) 기반의 I/O**

자바에서 데이터를 전달할 때 두 대상을 연결하고 데이터를 전송할 수 있는, 데이터를 운반하는데 사용하는 연결통로 스트림이라고 정의한다. 스트림은 문자 그대로 한 방향으로 흘러간다는 뜻을 내포하고 있어 단방향 통신만 가능하다. 그래서 입력스트림은 입력만, 출력스트림은 출력만 가능하다.

### **InputStream, OutputStream**

InputStream과 OutputStream은 **바이트 기반 스트림의 최상위 클래스**로 **바이트 단위로 데이터를 전송**하는데 사용된다. 미디어, 문자 등 모든 종류의 데이터를 주고받을 수 있고 이 두 클래스를 상속받는 클래스가 실제로 어떤 데이터를 주고받을 지 정의해준다.

- 각각의 추상클래스를 열어보면 `int read()` 또는 `void write(int b)`라는 추상메서드가 있다. 입출력대상에 따라 읽고 쓰는 방법이 다르기때문에 알아서 잘 구현하라고 추상메서드로 정의되어 있다.

- 위의 추상메서드 외에 모든 메서드들은 정의가 되어있다. 하지만 거기에서도 위의 추상메서드를 호출하기때문에 구현이 꼭 필요하다.

- InputStream을 예로 `int read()`는 스트림으로부터 한 바이트를 읽고 그 읽은 바이트를 int타입으로 리턴한다. 더 이상 읽을 값이 없으면 -1을 리턴한다.

  `int read(byte[] b)`는 b의 length만큼 바이트를 읽어 바이트배열 b에 저장하고 읽은 바이트 수를 리턴한다.

  ```
  public static void main(String[] args) {
    InputStream is = new FileInputStream("test.txt"); // "hello world"가 저장되어있는 test.txt
  
    int bytes;
    byte[] readbytes = new byte[6];
    String data;
  
    while (true) {
      bytes = is.read();
      if (bytes == -1) break;
      System.print.out((char)bytes); // hello world 출력
    }
  
    while ((bytes = is.read(readbytes)) != -1) {
      data += new String(readbytes, 0, bytes);
    }
    System.println.out(data); // hello world 출력
  }
  ```

- 모든 종류의 데이터를 받을 수 있다고는 하나 문자 데이터의 경우 바이트스트림보다는 문자스트림을 이용하는 것이 훨씬 효율적이라고 한다.

### **Reader, Writer**

Reader과 Writer는 문자 기반 스트림의 최상위 클래스로 문자 단위로 데이터를 전송한다. 바이트 기반 스트림과 사용법은 비슷해서 패스..하려고 했는데 reader로 코드짜보니까 위의 InputStream과는 다르게 한글이 잘 읽힌다.

### **버퍼 (Buffer) 기반의 I/O**

### **보조 스트림**

다른 스트림과 연결이 되어 편리한 기능(문자 변환, 입출력 성능 향상, 객체 입출력 등)을 제공해주는 스트림이다. 생성 시 연결할 스트림을 매개변수로 넣어주면 보조 스트림이 연결된다.

### **Buffer**

위 설명의 스트림은 OS레벨에 바로 요청된다. 근데 이 요청에서 비용이 많이 들어서 그냥 쓰면 성능이 떨어진다. 버퍼를 이용해서 데이터를 모아뒀다가 한번에 보낸다면? 메모리 버퍼는 프로그램과 데이터를 주고받는데 속도가 빠르므로 버퍼를 이용하면 성능이 향상된다.

프로그램에서 데이터를 내보낼 때 버퍼에 고속으로 전송하고 버퍼에 데이터가 어느정도 차면 한꺼번에 목적지로 보내서 출력 횟수를 줄일 수 있다.

## **채널 기반의 I/O**

### **NIO**

기존 IO의 단점을 개선하기 위해 java 4부터 추가된 패키지이다.

- 채널 방식: 양방향으로 입출력이 가능해서 입력을 위한 스트림, 출력을 위한 스트림 이렇게 따로 생성하지 않고 채널은 하나 만들면 그 하나로 입력과 출력을 같이 할 수 있다.
- 버퍼: 위에서 설명했던 것과 같이 스트림은 버퍼를 이용하지 않아서 보조 스트림을 이용해야 하지만 NIO는 버퍼방식을 기본으로 해서 이미 성능이 짱짱
  - 블로킹: 블로킹과 넌블로킹 특징 모두 가진다. 스트림의 write()는 데이터가 출력되기 전까지 thread가 블로킹 되지만

## **표준 스트림 ([System.in](http://System.in), System.out, System.err)**

System 클래스는 java.lang 패키지에 포함되어 있다. In은 일반적으로 콘솔 프로그램의 키보드 입력에 연결되는 inputStream, out은 OutputStream이다. Cli에서만 전달되기 때문에 자주 사용되지 않는다.

## Reference

- https://www.notion.so/I-O-af9b3036338c43a8bf9fa6a521cda242
- 이것이 자바다 챕터 18 강의 on Youtube