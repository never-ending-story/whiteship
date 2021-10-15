## I/O

```
입출력은 컴퓨터 내부 또는 외부 장치와 프로그램 간의 데이터를 주고 받는 것을 말한다.
```

### 스트림(Stream)

```
스트림이란 데이터를 운반하는데 사용되는 연결통로이다.
```
- 단방향 통신만 가능하기 때문에 입력과 출력을 동시에 수행하려면 입력을 위한 입력스트림과 출력을 위한 출력스트림 모두 필요하다.
- 먼저 보낸 데이터를 먼저 받게 되어 있으며 중간에 건너뜀 없이 연속적으로 데이터를 주고 받는다. (FIFO)

![Java-Input-Output-Stream](https://user-images.githubusercontent.com/46085281/137257854-0f404cee-1b3e-4179-832e-9fb895a72b06.jpeg)

### 버퍼 (Buffer)

- 버퍼 (Buffer)란 데이터를 전송하는 상호 간의 장치에서 고속의 장치와 저속의 장치 간의 속도 차이로 인해 저속의 장치가 작업을 추리하는 동안, 고속의 장치가 기다려야하는 현상을 줄여주는 기술이며 데이터를 임시 저장하는 공간을 의미한다. 그리고 임시저장장치로 불리운다.


### 채널 (Channel)

- 채널은 입력과 출력을 동시에 할 수 있다고 한다.

---

### Byte와 Character 스트림

```
스트림은 읽고 쓰는 단위에 따라서 바이트(Byte)스트림(1 byte씩 읽는다)과 문자(Character)스트림(2 bytes씩 읽는다)으로 나타낸다.
```

- **ByteStream** : 데이터를 바이트 단위로 처리한다. (ex: XXXStream)
- **CharacterStream** : 데이터를 문자 단위로 처리한다. (ex: XXXReader, XXXWriter)

![Java-stream-classification-filetype2](https://user-images.githubusercontent.com/46085281/137256283-340781db-5982-464f-a701-b72e38498c4e.png)

출처 : https://www.geeksforgeeks.org/java-io-input-output-in-java-with-examples/

---
### InputStream과 OutputStream

```
모든 바이트기반 스트림의 조상이다.
```

- **InputStream** : 배열, 파일, 기타 주변장치로부터 읽어들여야하는 데이터에 사용된다.

![Java-Input-Stream](https://user-images.githubusercontent.com/46085281/137256147-cb3c4f03-b07b-4be2-8360-dc407521641c.png)

출처 : https://www.geeksforgeeks.org/java-io-input-output-in-java-with-examples/

- **OutputStream** : 배열, 파일, 기타 주변장치에 출력해야되는 데이터에 사용된다.

![Java-Output-Stream](https://user-images.githubusercontent.com/46085281/137256168-0c69ed09-a13e-4e6e-93da-210ed060f24d.png)

출처 : https://www.geeksforgeeks.org/java-io-input-output-in-java-with-examples/

- 자바에서는 java.io패키지를 통해서 많은 종류의 입출력 관련 클래스들을 제공하고 있으며, 입출력을 처리할 수 있는 표준화된 방법을 제공함으로써 입출력의 대상이 달라져도 동일한 방법으로 입출력이 가능하기 때문에 프로그래밍을 하기에 편리하다.
---
### 표준 스트림 (System.in, System.out, System.err)

![Java-Basic-input-output1](https://user-images.githubusercontent.com/46085281/137254228-304e9b09-b699-482c-a3c3-fd0dc9d7110a.png)
출처 : https://www.geeksforgeeks.org/java-io-input-output-in-java-with-examples/

- **System.in** : 키보드나 다른 표준 입력 장치로 문자를 입력받는데 사용되는 표준 입력 스트림
- **System.out** : 모니터와 같은 출력 장치에 프로그램의 결과를 만들어 보여주는 표준 출력 스트림
- **System.err** : 모니터나 표준 출력 장치에 에러데이터를 출력하는데 사용되는 표준 에러 스트림

### 파일 읽고 쓰기

- **키보드 -> 파일**

```java
import java.io.*;
// 키보드 --> 파일 
class C {
    InputStream is = System.in;
    FileOutputStream os;
    C(){
	try{
	    os = new FileOutputStream("/Users/shion/desktop/C.txt"); 
	}catch(FileNotFoundException fe){}
    }
    void m(){
	byte[] b = new byte[8];
	try{
	    while(true){
		int total = is.read(b);
		os.write(b, 0, total); 
		os.flush();
	    }
	}catch(IOException ie){
	}finally{
	    try{
		is.close();
		os.close();
	    }catch(IOException ie){}
	}
    }
    public static void main(String[] args) {
	C c = new C();
	c.m();
    }
}
```

- 실행해보면

![스크린샷 2021-10-14 오후 5 39 47](https://user-images.githubusercontent.com/46085281/137282556-6d74a1fc-f822-4103-a941-6002618669fb.png)

- 키보드로 입력한 문장이 `C.txt` 파일에 써지는 것을 볼 수 있다.

<img width="829" alt="스크린샷 2021-10-14 오후 5 40 27" src="https://user-images.githubusercontent.com/46085281/137282633-5dafe7dc-4cd0-4937-a9be-9a0b26a12b2b.png">

- **파일 -> 파일/모니터 1**

```java
import java.io.*;
//파일 -> 모니터/파일
class D {
    FileInputStream is;
    OutputStream os = System.out; //모니터
    FileOutputStream fos; //파일 
    D(){
	try{
	    is = new FileInputStream("/Users/shion/desktop/C.txt"); // C.txt 파일을 읽는다.
	    fos = new FileOutputStream("/Users/shion/desktop/D.txt"); // D.txt 파일에 출력하기 위한 OutputStream
	}catch(FileNotFoundException fe){}
    }
    void m(){
	byte[] b = new byte[8];
	int total = 0;
	try{
	    while((total = is.read(b)) != -1){
		fos.write(b, 0, total); 
		os.write(b, 0, total);
		fos.flush();
		os.flush();
	    }
	}catch(IOException ie){
	}finally{
	    try{
		is.close();
		os.close();
		fos.close();
	    }catch(IOException ie){}
	}
    }
    public static void main(String[] args) {
	D d = new D();
	d.m();
    }
}
```
- 실행 -`C.txt` 파일의 내용이 콘솔창에 출력되었음

![스크린샷 2021-10-14 오후 5 47 05](https://user-images.githubusercontent.com/46085281/137283591-1670a4bd-7e55-486e-8df4-97069d511ae2.png)

- `C.txt` 파일의 내용이 `D.txt`파일에 복사 되었음

<img width="829" alt="스크린샷 2021-10-14 오후 5 46 45" src="https://user-images.githubusercontent.com/46085281/137283561-ae37a86c-6344-485e-9f6c-c97db8c67640.png">

- **파일 -> 파일/모니터 2**

```java
import java.io.*;
//파일 -> 모니터/파일
class DD {
    FileInputStream is;
    OutputStream os = System.out; //모니터
    FileOutputStream fos; //파일
    String fname = "IOCopy.jpg";
    DD(){
	try{
	    is = new FileInputStream("/Users/shion/desktop/IO.jpg"); //바탕화면에 있는 IO.jpg라는 파일을 읽어서
	    fos = new FileOutputStream("/Users/shion/desktop/" + fname); //바탕화면에 IOCopy.jpg 이름으로 복사할 것임
	}catch(FileNotFoundException fe){}
    }
    void m(){
	byte[] b = new byte[8];
	int total = 0;
	try{
	    while((total = is.read(b)) != -1){
		fos.write(b, 0, total); 
		//os.write(b, 0, total);
		fos.flush();
		//os.flush();
	    }
	    System.out.println("파일을 복사했습니다("+fname+")");
	}catch(IOException ie){
	}finally{
	    try{
		is.close();
		os.close();
		fos.close();
	    }catch(IOException ie){}
        }
    }
    public static void main(String[] args) {
	DD d = new DD();
	d.m();
    }
}
```
- 이 파일을 복사할 것임

<img width="434" alt="스크린샷 2021-10-14 오후 5 56 40" src="https://user-images.githubusercontent.com/46085281/137285205-59dcea66-ebe3-4b6c-90a4-b3380bcdbb8a.png">

- 실행

![스크린샷 2021-10-14 오후 5 56 08](https://user-images.githubusercontent.com/46085281/137285194-8f0ae9b2-92f0-4201-aed4-5f39873d2f56.png)

- 결과

<img width="434" alt="스크린샷 2021-10-14 오후 5 56 51" src="https://user-images.githubusercontent.com/46085281/137285222-3bc3de4c-a340-43df-9ef0-686fdfde1d65.png">

- **파일 -> 파일**
  
```java
import java.io.*;

//파일 -> 파일 
class A2 {
    String fName1 = "A2.java";
    String fName2 = "A2_copy.txt";
    FileInputStream fis; //Node 
    FileOutputStream fos; //Node 
    BufferedInputStream bis; //Filter 
    BufferedOutputStream bos; //Filter 
    A2(){
	try{
	    fis = new FileInputStream("/Users/shion/Desktop/" + fName1); // A2.java(현재파일) 파일내용을 읽어서
      	    fos = new FileOutputStream("/Users/shion/Desktop/" + fName2); // A2_copy.txt 파일에 출력해 넣을 것임
	    bis = new BufferedInputStream(fis, 1024); // 버퍼를 사용, 사이즈는 1024바이트
            bos = new BufferedOutputStream(fos, 1024);
	}catch(FileNotFoundException fe){}
    }
    void copy(){
	int i = 0;
	long t = 0;
	byte bs[] = new byte[256];
	try{
	    while((i = bis.read(bs)) != -1){
		bos.write(bs, 0, i);
		t += i;
	    }
	    bos.flush();
	    System.out.println("복사성공!! ("+fName2+" "+t+"bytes)");
	}catch(IOException ie){
	}finally{
	    try{
		bis.close();
		bos.close();
		fis.close();
		fos.close();
	    }catch(IOException ie){}
	}
    }
    public static void main(String[] args) {
	A2 a = new A2();
	a.copy();
    }
}
```

- 실행

![스크린샷 2021-10-14 오후 6 17 51](https://user-images.githubusercontent.com/46085281/137288849-18328b28-297e-431b-867f-d3fdc9494a4e.png)
<img width="826" alt="스크린샷 2021-10-14 오후 6 18 21" src="https://user-images.githubusercontent.com/46085281/137288863-216ddfa6-ded4-414f-bc08-d459b91b9182.png">
