## 학습 1.0) 스트림 (Stream) 

### I/0란?
-	자바의 정석 
-	Input 과 Output의 약자 == 입출력 
-	입출력이란? 컴퓨터 내부 또는 외부의 장치와 프로그램 간의 데이터를 주고 받는 것 

### 스트림(Strema)? 
- https://docs.oracle.com/javase/tutorial/essential/io/streams.html
-	자바의 정석 
#### 스트림은 연결통로 
-	입출력을 수행하려면 (어느 한 쪽에서 다른 쪽으로 데이터를 전달하려면) 두 대상을 연결하고 데이터를 전송할 수 있는 무언가가 필요한데, 이를 스트림으로 정의 
-	데이터를 운반하는데 필요한 연결통로 
![](https://images.velog.io/images/bongf/post/e7ebc7f7-b6a0-4bff-bf37-300d638a1e3b/image.png)
-	그림 출처 https://medium.com/@nilasini/java-nio-non-blocking-io-vs-io-1731caa910a2

#### docs에서는 스트림은 sequence of data라고 한다. 
-	자바의 정석에서는 통로라고 정의했다면 독스에서는 연속된 데이터라고 표현했다. 
> A stream is a sequence of data. A program uses an input stream to read data from a source, one item at a time: A program uses an output stream to write data to a destination, one item at time:
-	아래의 실사용 예제를 보면 어떤 관을 만들어주고 거기에 데이터를 read로 넣어주는 꼴을 하고 있어 우선은 자바의 정석 정의대로 연결 통로로 이해했다. 

```java
// 자바의 정석 예제 (15-1)
public class ByteArrayStreamPractice1 {
    public static void main(String[] args) {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;

        ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        int data = 0;
        while((data = input.read()) != -1)  {
            output.write(data);
        }

        outSrc = output.toByteArray();
        System.out.println(Arrays.toString(inSrc));
        System.out.println(Arrays.toString(outSrc));
    }
}
```

#### 스트림은 단방향 통신만 가능, 순차적 접근만 허용, 실제 데이터가 모두 전송되기 전까지 지연상태를 유지한다 
https://blog.naver.com/swoh1227/222237603565
-	입력과 출력을 둘 다 수행하려면 스트림 2개 필요. 입력스트림(Input Stream), 출력스트림(Output Stream) 
-	먼저 보낸 데이터를 먼저 받는 FIFO 구조 (큐)로 되어있다고 생각하면 된다. 
-	건너뛰는 것 없이 순차적인 접근만 된다. 
-	해당 스트림의 데이터가 처리가 끝날 때까지 지연상태(블로킹)이 된다. 다른 일을 할 수 없다. 이를 보완하기 위해 NIO가 나왔다. 


## 학습 2) InputStream과 OutputStream (Byte 스트림) 
#### 출처 
-	https://www.tutorialspoint.com/java/java_files_io.htm
-	자바의 정석 
-	https://docs.oracle.com/javase/tutorial/essential/io/bytestreams.htm
#### 내용
-	source로부터 데이터로 읽는데 사용되는 InputStream과 목적지에 데이터를 작성하는 OutputStream이 있다. 
-	InputStream과 OutputStream 클래스 중점으로 살펴보면 아래와 같다. 
-	스트림은 바이트 단위로 데이터를 전송한다. (입출력 단위가 1byte) 
-	[InputStream](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/InputStream.html) : 바이트를 input하는 스트림의 상위 추상 클래스 
-	[OutputStream](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/OutputStream.html) : 바이트를 output하는 스트림의 상위 추상 클래스 
-	바이트 스트림 클래스들은 InputStream과 OutoutStream의 자손이다. 
-	File, ByteArray, Piped(프로세스간통신), Audio + Input/Output Stream이 있는데 이는 InputStream, OutputStream의 자손이다. 
![](https://images.velog.io/images/bongf/post/68dbe2b8-f0d8-4599-827f-30901115ae19/image.png)
-	docs에서는 항상 close 해줄 것과 ByteStream은 lovw level I/O 라 더 나은 스트림을 쓸 것을 권고 하고 있다. 가장 기본일 때만 쓰는 것으로. 

## 학습 3) Byte와 Character 스트림
-	자바의 정석 
	-	자바에서는 char 형이 2byte다. 바이트 기반 스트림에는 따라서 어려움이 있다. 이를 보완하기 위해 문자 기반 스트림이 제공된다. 문자 데이터를 입출력 할때는 문자기반 스트림을 쓰면 된다. 
	-	모든 character 스트림 클래스들은 `Reader`와 `Writer`의 후손이다. 
	-	메서드를 비교해보면 바이트 기반 스트림은 byte[]를 사용하고 문자 기반 스트림은 char[]을 사용한다. 

-	https://docs.oracle.com/javase/tutorial/essential/io/charstreams.html
	-	Character Stream은 로컬의 character set에서(으로) (자바 플랫폼이 character를 저장할 때 사용하는) Unicode conventions 으로(부터) 자동 변환 한다고 한다. 
    -	docs에서 보여준 예시를 보면 바이트기반 스트림과 character 스트림이 둘 다 read, write 할 때 정수 기반의 변수를 사용하는데(int c), 바이트 기반 스트림은 마지막 8bit를 저장하고 character 기반 스트림은 마지막 16bit를 저장한다는 차이가 있다.  
```java 
    public class CopyCharacters {
      public static void main(String[] args) throws IOException {
      	FileReader inputStream = null;
      	FileWriter outputStream = null;
        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("characteroutput.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
```
#### 바이트 스트림을 사용하는 문자 기반 스트림
-	character 스트림은 종종 바이트 기반 스트림의 "wrappers"다. character 스트림은 물리적 I/O를 수행하기 위해 바이트 스트림을 사용한다. characters와 bytes 간의 변환을 다루면서. 예를 들어 FileReader은 FileInputStream을 사용하고 FileWriter은  FileOutputStream 을 사용한다. 
-	실제로 코드를 보면 아래와 같이 되어있다. 
![](https://images.velog.io/images/bongf/post/53014bcc-a255-405d-acb2-2e440849e388/image.png)

#### Line 기반 I/O
- character I/O는 한 character보다 큰 단위로 발생한다. 일반적인 단위는 line(한 줄)이다. 문장의 끝에는 line termiantor가 있다.  ("\r\n")("\r")("\n") 등. 
-	위의 예제를 라인 기반 I/O로 수정하면 아래와 같다. (  BufferedReader 와 PrintWriter 사용) 
```java
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class CopyLines {
    public static void main(String[] args) throws IOException {

        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("xanadu.txt"));
            outputStream = new PrintWriter(new FileWriter("characteroutput.txt"));

            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
```
## 학습 1.1) Buffer
https://docs.oracle.com/javase/tutorial/essential/io/buffers.html
-	지금까지는 주로 unbuffered I/O를 살펴봤다. 이 의미는 각각의 읽기, 쓰기 request가 OS에서 직접 처리된다는 말이다. 이것은 프로그램을 매우 비효율적으로 만든다. 각각의 request는 disk access, network activity 등의 상대적으로 비싼 다른 기능들을 요구하기 때문이다. 
-	이 오버헤드를 줄이기 위해서 자바 플랫폼은 buffered I/O 스트림을 구현한다. Buffered input streams은 `buffer`라고 불리는 메모리 영역에서 데이터를 읽는다. 
-	버퍼는 중간 저장소라고 생각하면 쉬운데 아래 그림이 괜찮아서 http://jcsites.juniata.edu/faculty/rhodes/os/ch11a.htm 에서 캡쳐했다. ![](https://images.velog.io/images/bongf/post/1621f10d-0e8b-45c3-af59-07a8c2097e90/image.png)
	-	이 중간 저장소가 가득 찼을 때 한 번에 전달한다
-	버퍼가 비어져있을 때만 native input API가 호출되고 버퍼가 full일 때만 output API가 호출된다. 
-	unbuffered stream을 buuffered stream으로 wrapping 하기 위해 사용하는 것 : `BufferedInputStream` `BufferedOutputStream` `BufferedReader` `BufferedWriter` 
	-	차이는 `BufferedInputStream` `BufferedOutputStream` 은 buffered된 바이트 스트림을 생성한다는 것, `BufferedReader` `BufferedWriter`는 buffered된 character 스트림을 생성한다는 것 
-	사용 예시는 아래와 같다. 
```java
inputStream = new BufferedReader(new FileReader("xanadu.txt"));
outputStream = new BufferedWriter(new FileWriter("characteroutput.txt"));
```
### Flushing Buffered Streams
-	버퍼가 가득 찰 때까지 기다리지 않고 특정 시점에 butffer를 write out할 때(버퍼에 있는 것 다 내보내기) 버퍼를 `flushing` 한다고 한다. 
-	몇몇 buffered ouput 클래스들을 `autoflush`를 제공한다.
	-	`autoflush`가 가능하면 특정 키 이벤트가 buffer를 flushed되게 할 수 있다. 
    -	위에서 봤던 `PrintWriter`는 `println`이나 `format`이 호출될 때마다 buffer가 flush한다. 
-	수동으로 flush 하려면 `flush()`메서드를 호출하면 된다. 어떤 putout 스트림에서도 사용 가능하지만 buffered된 스트림에서만 유효하다. 
	-	버퍼가 가득찼을 때만 출력소스에 출력을 해서 마지막 출력 부분이 출력소스에 쓰이지 못하고 버퍼에 남아 있을 때 
    -	그래서 보통 Buffered**Output**Stream에 close()나 flush()를 호출해서 마지막 버퍼에 남은 내용이 출력소스에 출력되도록 해야 한다. 

#### 보조스트림 
- 자바의 정석
- 이런 BufferedInputStream 등은 스트림의 기능을 보완하기 위한 보조 스트림이다. 실제 데이터를 주고 받는 스트림은 아니라 데이터를 입출력하는 기능은 없지만 스트림의 기능을 향상시키거나 새로운 기능을 추가할 수 있다. 
- 스트림을 먼저 생성한 후 보조 스트림을 생성하는 방식으로 사용한다. 
- 보조 스트림을 close() 하면 기반 스트림도 close() 된다. 

## 학습 4) 표준 스트림 (System.in, System.out, System.err)
- https://docs.oracle.com/javase/tutorial/essential/io/cl.html
-	커맨드 라인 환경에서 프로그램이 돌아가거나 interact하기 위해 자바 플랫폼은 두 가지 방싱르 제공한다. Standard Streams와 Console 
### Stndard Streams 
- 기본적으로 키보드에서 입력을 읽고 화면에 결과를 출력한다. 
-	세가지 표준 스트림을 제공한다. System.in;  System.out; System.err.
	-	System.in;(표준입력)  System.out;(표준출력) System.err.(표준에러출력)
	-	System.err.은 에러 text를 출력할 때만 사용한다. 
-	이들은 자바 어플리케이션의 실행과 동시에 사용될 수 있도록 자동생성 된다. 
-	표준 스트림은 바이트 스트림 
	-	System.out 과 System.err은 PrintStream 개체로 정의된다. 
    	-	PrintStream 은 기술적으로는 바이트 스트림이지만 character 스트림의 많은 기능을 모바앟기 위해 내부에 character 스트림을 활용한다. 
    -	반면에  System.in 은 character 스트림 특징이 없는 바이트 스트림. 이를 character 스트림으로 사용하려면 아래와 같이 하면 된다. 
    
```java 
    InputStreamReader cin = new InputStreamReader(System.in);
```
## 학습 5) 파일 읽고 쓰기
-	아래와 같이 코드를 작성하면 비어있던 fileout 파일에 test 파일 안에 있던 텍스트가 저장된다. 
```java
package bongf.week13.study;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilePractice {
    public static void main(String[] args) throws IOException {
        // file 경로로 fileInputStream,outputStream을 생성한다
        FileInputStream fileToIn = new FileInputStream("c:\\java2\\file_practice\\test.txt");
        FileOutputStream fileToOut = new FileOutputStream("c:\\java2\\file_practice\\fileout.txt", true);

        int data = 0;
        while ((data = fileToIn.read()) != -1) {
            fileToOut.write(data);
        }
        fileToIn.close();
        fileToOut.close();
    }
}


```
-	라인 단위로 읽고 쓰기 
```java
package bongf.week13.study;

import java.io.*;

public class FilePractice2 {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader( "c:\\java2\\file_practice\\test.txt");
        FileWriter fileWriter = new FileWriter("c:\\java2\\file_practice\\fileout.txt");

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String line = "";
        for (int i =1; (line = bufferedReader.readLine()) != null; i++) {
            System.out.println(line);
            bufferedWriter.write(line);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}

```

## 학습 1.2) 채널 (Channel) 기반의 I/O
http://tutorials.jenkov.com/java-nio/nio-vs-io.html

-	간단하게 NIO에 대해서 이해해 보고자 했다. 
-	기존 I/O가 느린 이유에 대해서 간단하게 이해했다. 
![](https://images.velog.io/images/bongf/post/40c12b99-78bd-41cd-ae00-4aa978b41309/image.png)
-	그림 출처 : https://ohjongsung.io/2019/09/07/java-nio-%ED%86%BA%EC%95%84%EB%B3%B4%EA%B8%B0
-	커널의 버퍼에 데이터가 쌓이면 프로세스 안의 버퍼에 다시 이를 복제할 때 오버헤드가 발생한다. 
	-	JVM 내부로 복제하는 것은 CPU 자원을 사용해야 하고, 또 복사된 내부 버퍼는 GC의 대상이며 이 복제 동안에 I/O 요청한 쓰레드는 블록킹 된다. 
### NIO (New I/O) 
-	자바 I/O API의 대안
-	기존 I/O는 버퍼를 지원해주지 않았기 때문에 보조스트림으로 버퍼를 사용했는데 NIO는 버퍼를 지원한다. 
#### 특징 1. Non-blokcking IO를 가능하게 한다. 
-	쓰레드가 채널한테 buffer로 데이터를 읽으라고 할 때. 채널이 버퍼로 데이터를 불러 읽는 동안 쓰레드는 다른 일을 할 수 있다. 데이터가 버퍼로 다 읽어지면 쓰레드는 다시 그 일을 처리할 수 있다.
-	NIO의 블로킹은 쓰레드를 인터럽트해서 블로킹을 빠져나올 수 있다. 
#### 특징 2. 채널과 버퍼 
-	표준 IO API에서는 바이트 스트림과 character 스트림을 사용했다. NIO에서는 채널과 버퍼를 사용한다. 
-	IO는 stream 지향이고 NIO buffer 지향이다. 
-	특정 버퍼를(ByteBuffer의 allacateDirect() 사용) 사용하면 커널 버퍼를 직접 접근할 수 있어서 위에서 얘기했던 문제를 해결할 수 있다. 
#### 특징 3. Selectors
-	NIO는 "selectors"라는 컨셉을 갖고 있다. selector는 event(connection opened, data arrived등)에 대해 여러 채널을 모니터 할 수 있는 개체. 따라서 단일 쓰레드가 여러 채널을 모니터 할 수 있다.
### 채널 
-	한 곳에서 다른 곳으로의 데이터 흐름의 수단으로 사용한다
-	채널에서 buffer로 데이터를 읽어들일 수도 있고 buffer로부터 데이터를 write 하는 것도 가능하다. 
	-	단방향만 지원했던 stream과 달리 양방향 지원이 가능하다. 
-	NIO 가 논블로킹 I/O를 지원하기 때문에 채널도 논블로킹 I/O가 가능하다. 
-	FileChannel 예시 : http://tutorials.jenkov.com/java-nio/file-channel.html
	-	FileChannel은 open하려면 InpuStream, OutputStream이나 RandomAccessFile로부터 얻어야 한다. 
```java 

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelPractice {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("c:\\java2\\file_practice\\test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("c:\\java2\\file_practice\\fileout.txt");

        ByteBuffer buffer = ByteBuffer.allocateDirect(20); // 우선 버퍼가 할당 되어야 한다. 그리고 나서 read()의 결과가 buffer에 써진다.
        FileChannel channelIn = fileInputStream.getChannel();
        FileChannel channelOut = fileOutputStream.getChannel();
        channelIn.read(buffer); // FileChannel로부터 데이터를 읽으려면 read() 

        buffer.flip(); //read모드였던 버퍼를 write모드로 flip 
        channelOut.write(buffer);
    }
}

```
-	flip에 대해서 더 궁금하다면 https://palpit.tistory.com/entry/Java-NIO-%EA%B8%B0%EB%B0%98-%EC%9E%85%EC%B6%9C%EB%A0%A5-%EB%B0%8F-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9-%EB%B2%84%ED%8D%BCBuffer 여기 아주 친절하게 설명되어 있다. 
	-	https://stackoverflow.com/questions/14792968/what-is-the-purpose-of-bytebuffers-flip-method-and-why-is-it-called-flip
