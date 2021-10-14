13주차 I/O

- 스트림, 버퍼, 채널 기반 I/O
- InputStream, OutputStream
- Byte 스트림과 Character 스트림
- 표준 스트림(System.in, System.out, System.err)
- 파일 읽고 쓰기
</br>

### 스트림이란

Stream이란 데이터를 운반하는 통로이다. 

스트림은 단방향 통신만이 가능하다. → 하나의 스트림으로 입출력이 동시에 진행될 수 없다. 
</br>


### NIO(New Input/Output)

새로운 입출력

기존 IO를 보완하기 위해 추가된 패키지이다.

**IO vs NIO**

- IO는 입출력 방식이 스트림 방식이고 NIO는 채널 기반이다.
    - **채널**이란 스트림과 달리 양방향으로 입출력이 가능하다.  반면, 스트림은 입력 스트림과 출력 스트림이 필요하면 각각 생성해서 사용해야한다.
- IO는 버퍼를 제공해주는 보조 스트림인 BufferedInputStream과 BufferedOutputStream을 지원한다. 반면, NIO는 기본적으로 버퍼를 사용해서 입출력 하기 때문에 IO보나 성능이 높다.
    - 보조 스트림을 사용하면 한 바이트씩 입출력하는 것이 아니라 버퍼(바이트배열)을 이용해서 여러 바이트를 입출력한다. → 더 빠르다.
    - **버퍼**를 사용한 입출력은 OS 시스템 콜 횟수 자체가 줄어드는 것이기 때문에 성능이 더 좋다.
- NIO는 비동기 방식을 지원한다. (IO는 비동기 지원 안함)
</br>


### InputStream과 OutputStream

**InputStream** : 자원으로부터 데이터를 읽는 것(**read**)이고,

**OutputStream** : 목적지에 데이터를 쓰는 것(**write**)이다.

</br>


### Character stream과 Byte stream

**Character stream**

문자 스트림은 데이터를 문자별로(character by character)로 읽고 쓸 수 있게 해준다.

`FileReader` 와 `FileWriter` 가 문자 스트림에 해당된다. 

```java
import java.io.FileReader;
import java.io.IOException;

public class FileIoExam {

    public static void main(String[] args) {

        try(FileReader fileReader = new FileReader("temp/text.txt")) {
            int temp = fileReader.read();
            while (temp != -1) {
                System.out.println((char) temp);
                temp = fileReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
<img width="350" alt="스크린샷 2021-10-14 오후 3 44 43" src="https://user-images.githubusercontent.com/65011131/137373164-2c9923ab-66d8-4105-ab1d-8558bc60610b.png">

<img width="240" alt="스크린샷 2021-10-14 오후 3 43 17" src="https://user-images.githubusercontent.com/65011131/137373180-8d2ef47d-7559-4cc3-979f-763c9947649f.png">

text.txt 파일에서 문자별로 읽었다.

</br>


**Byte stream**

바이트 별로 작업을 수행한다.(byte by byte)

```java
import java.io.*;

public class FileIoExam {

    public static void main(String[] args) {

        try (FileInputStream inputStream = new FileInputStream("temp/sourcefile.txt");
             FileOutputStream outputStream = new FileOutputStream("temp/targetfile.txt")) {

            int temp;
            while ((temp = inputStream.read()) != -1) {
                outputStream.write((byte) temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
<img width="400" alt="스크린샷 2021-10-14 오후 4 02 53" src="https://user-images.githubusercontent.com/65011131/137373219-d79ee836-0cfa-4f50-bf9d-2acf7cd2ad6d.png">

</br>


**문자 스트림과 바이트 스트림은 언제 쓰는 것이 좋을까?**

바이트 스트림은 데이터 전송 단위가 1byte 이다. 데이터를 읽어서 binary file과 같은 미가공 데이터를 다룰 때 적합하다. 

자바에서는 char형의 데이터 크기가 2byte이기 때문에 바이트 스트림으로 처리하기에 어려움이 있어서 문자 스트림을 제공한다. 데이터가 문자인 경우에 사용하고 16bit 유니코드 문자를 주고받는다. 

</br>


**character stream vs byte stream**

character stream은 클래스 명에 Reader/Writer가 사용된다.

byte stream은 InputStream/OutputStream이 사용된다.

- 스트림은 사용 후 꼭 close 하는 이유는?
    - 자바와 같은 프로그래밍 언어를 이용해서 파일을 여는 행위는 OS에게 해당 파일에 접근을 요청하는 것이다. 이때 OS로부터 자원을 할당 받는 것이고 close는 자원을 해제하는 행위이다.
        
        → close를 해야 운영체제의 자원 고갈을 방지할 수 있다. 
        

- 일반적으로 BufferedReader/BufferedWriter 혹은 BufferedInputStream/BufferedOutputStream을 이용해서 효율을 높인다.
    - BufferedInputStream 사용 예제
        
        ```java
        import java.io.*;
        
        public class FileIoExam {
        
            public static void main(String[] args) {
        
                try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("temp/sourcefile.txt"))) {
        
                    int temp;
                    while ((temp = inputStream.read()) != -1) {
                        System.out.print((char) temp);
                    }
        
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ```
        
![스크린샷 2021-10-15 오전 2 48 43](https://user-images.githubusercontent.com/65011131/137373278-2a7bec35-8df6-4560-a700-18e95c72ba4a.png)
</br>


### 표준 입출력(System.in, System.out, System.err)

표준 입출력은 콘솔을 통한 데이터의 입출력을 의미한다.

자바에서는 표준 입출력을 위해 System.in, System.out, System.err 를 제공한다.

- System.in : 콘솔로부터 데이터를 입력받는데 사용
- System.out : 콘솔로부터 데이터를 출력하는데 사용
- System.err : 콘솔로 데이터를 출력하는데 사용

</br>
</br>


참고: [https://www.geeksforgeeks.org/character-stream-vs-byte-stream-java/](https://www.geeksforgeeks.org/character-stream-vs-byte-stream-java/)

[https://www.notion.so/I-O-af9b3036338c43a8bf9fa6a521cda242](https://www.notion.so/I-O-af9b3036338c43a8bf9fa6a521cda242)
