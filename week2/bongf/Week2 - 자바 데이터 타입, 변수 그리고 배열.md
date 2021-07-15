## 목표

-   자바의 프리미티브 타입, 변수 그리고 배열을 사용하는 방법을 익힙니다.

## 학습 2-1) 프리미티브 타입과 레퍼런스 타입
-	자바의 데이터 타입은 primitive 타입과 reference 타입이 있다. 
    - 프리미티브 타입을 기본형이라고도 한다. 
	- 레퍼런스 타입을 참조형이라고도 한다. 
    
-	primitive 타입은 실제 값을 저장하고 reference 타입은 (힙에)값이 저장되어 있는 주소를 값으로 갖는다. 레퍼런스 타입을 변수에 저장하면 해당 데이터가 저장된 메모리의 주소가 변수에 저장된다 (그래서 이를 참조변수라고 부른다)  
	-	레퍼런스타입의 데이터가 다른 레퍼런스타입에 할당되면 두 레퍼런스 타입의 변수는 같은 객체를 가리키게 된다. 프리미티브 타입의 값이 다른 프리미티브 타입에 할당되면 얘네는 각각 이 값들을 가리키게 된다. [출처](https://www.oreilly.com/library/view/java-8-pocket/9781491901083/ch04.html)
    -	[이에 관해서 쉽게 설명한 유튭](https://www.youtube.com/watch?v=LTnp79Ke8FI) 
    -	위 유튜브에서 ![](https://images.velog.io/images/bongf/post/58868969-32ba-4211-ab3e-96a83562511b/image.png) 이 그림을 보면 쉽게 이해간다. 
    	-	int a = 3 이고 int b = a 로 지정해도 스택의 영역에 a, b 가 각각 값을 갖는다. 
        -	int[] d = c; 로 한 부분에서는 reference 타입이므로 같은 오브젝트를 가리키다가 만약 d[1]의 값만 99로 바꿔도 c에도 똑같은 값이 적용된다. 같은 객체를 가리키고 있었으므로. 
        
        

-	기본값, null
	-	primitive 타입은 기본 값을 가지며 null 값이 올 수 없다. 
    	```
        int a = null; (불가능) 
        ```
		-	primitive 타입의 기본값은 아래 학습1에 작성 
    -	reference 타입은 null 값이 가능하다. 
    	```
        Integer a = null; (가능) 
        ```

-	코드 작성하면서 프리미티브 타입을 쓸까 참조타입을 쓸까 고민했던 적이 있었는데 프리미티브 타입은 제네릭 사용이 불가능 하다 (List<int> 불가.) null 값도 넣을 수 없기도 하고. 그런데 원시타입은 직접 값을 저장하기 때문에 성능이 빠르다고 한다. (접근속도와 메모리 양의 차이 https://siyoon210.tistory.com/139) 


## 학습 1) Primitive 타입 종류와 값의 범위 그리고 기본값

### Primitve 타입 종류
-   자바에는 8가지 프리미티브 타입이 있다.

-   직접 그림을 그리라는 백기선님의 말에 따라 적어본다

<table style="border-collapse: collapse; width: 100%;" border="1" width="640" data-ke-align="alignLeft"><tbody><tr><td width="128" height="37">&nbsp;</td><td width="128"><span><span>1byte</span></span></td><td width="128"><span><span>2byte</span></span></td><td width="128"><span><span>4byte</span></span></td><td width="128"><span><span>8byte</span></span></td></tr><tr><td width="128" height="39"><span><span>논리형</span></span></td><td width="128"><span><span>boolean</span></span></td><td width="128">&nbsp;</td><td width="128">&nbsp;</td><td width="128">&nbsp;</td></tr><tr><td width="128" height="39"><span><span>문자형</span></span></td><td width="128">&nbsp;</td><td width="128"><span><span>character</span></span></td><td width="128">&nbsp;</td><td width="128">&nbsp;</td></tr><tr><td width="128" height="39"><span><span>정수형</span></span></td><td width="128"><span><span>byte</span></span></td><td width="128"><span><span>short</span></span></td><td width="128"><span><span>Integer</span></span></td><td width="128"><span><span>long</span></span></td></tr><tr><td width="128" height="39"><span><span>실수형</span></span></td><td width="128">&nbsp;</td><td width="128">&nbsp;</td><td width="128"><span><span>Float</span></span></td><td width="128"><span><span>double</span></span></td></tr></tbody></table>

### 값의 범의, 기본값
-   boolean : true, false값만 갖는다 / 기본값 false 
-   char :  0부터 2의 16승 -1 이다. ( 0 ~ 65,536) / 기본값 
  	-	 2byt이고 문자이기 때문에 음의 값을 가질 필요가 없다.
    -	![](https://images.velog.io/images/bongf/post/d6b48ab3-aef0-468e-b9c6-db7a739109d9/image.png) ![](https://images.velog.io/images/bongf/post/ab89a0b0-bad3-40be-bdb7-d26cac8c2795/image.png)
    	-	char는 그러나보통 ''로 초기화한다. 

-	byte  : - 2의 7승 ~ (2의 7승 -1) ( - 128 ~ 127 ) / 기본값 0 
	-	1byte == 8bit 이므로 1byte의 값은 2의 8승의 범위를 가져야 한다.
  
-   short :  -2의 15승 ~ (2의 15승 -1) ( -32,768 ~ 32,767) / 기본값 0 
  
-   int : -2의 31승 ~ (2의 31승 -1) (대략 +_20억 정도이며 9자리 수라는 것을 기억하면 편하다) / 기본값 0 
  
-   long : -2의 63승 ~ ( 2의 63승 - 1) / 기본값 0L
  
-   (float와 double은 위와 같은 규칙(값의 크기에 따른 범위)을 따르지 않는다.

-	float (1.4 X 10^-45) ~ (3.4 X 10^38) 정도 / 기본값 0.0f

-	double (4,9 x 10A^-324) ~ (1.8 x 10^308) 정도 / 기본값 0.0

-   float, double 표현방식이
    -   부호 / 지수 / 가수 로 표현(부동 소수점 방식)  
    -   float나 doubles는 정확도가 떨어진다  
        : 그렇기 때문에 돈계산은 BigDecimal을 써주는 것이 좋다
 

## 학습 2-1) 레퍼런스 타입

-   레퍼런스 타입에는 annotation, array, class, enumeration, interface가 있다. 
	-	[출처] (https://www.oreilly.com/library/view/java-8-pocket/9781491901083/ch04.html) 
-   해당 데이터의 주소가 저장되기 때문에 write & read가 가능하다. 해당 실제 값이 있는 그 주소로 가서 컨트롤 할 수 있는 것이다.

## 학습 3) 리터럴

-   int x = 1에서 1이 리터럴이다. 1과 100처럼 그 자체로 값을 의미하는 것이다. 1이 1의 값을 갖는다는 건 고정되어 있고 변하지 않는다.

-  변하지 않는 데이터를 표현하기 위한 것이기 때문에 primitive type은 리터럴이 될 수 있다. 
  -   ex. boolen 타입의 true, flase 리터럴
  
-	프리미티브 타입이 아닌 리터럴도 있는데 string이 대표적인 예다. 
	-   ex. 문자 'a' '가'도 리터럴이며  문자열 "hello"도 리터럴이다.
    -   생성된 String의 실제 값은 힙 영역의 String constant pool에 저장된다. 여기에 저장된 String은 리터럴이라 같은 값이 다른 변수에 저장되면 새 String 데이터를 생성하지 않고 기존 String 데이터 주소를 참조한다.  
        -	String a = "hello"; String b = "hello" 라면 b는 a변수에 저장된 주소를 참조한다.  
        -	다시 a = "bye"라고 하면, "hello" 실제 값에 "bye"가 덮어 씌어지는 것이 아니라 "bye"라는 값을 새로 만들어서 그 주소를 a에 할당한다.  
        -	이 내용은 [https://velog.io/@jaden\_94/String-Class](https://velog.io/@jaden_94/String-Class)를 참고했다. 
    
## 학습 4) 변수 선언 및 초기화하는 방법

### 변수선언

-   변수 선언 : 값을 저장할 공간을 마련하기 위해서 변수 선언을 한다.
-   `변수타입 변수이름;` 으로 선언한다. 
-   변수의 초기화는 변수에 값을 처음 저장한다는 말이다
-   초기화 방법은 대입연산자 = 을 사용한다. int a = 1; 이런식이다.
-   지역변수를 제외하고 클래스변수와 인스턴스 변수는 기본값으로 초기화된다
  	-	![](https://images.velog.io/images/bongf/post/73ba8854-f91f-4413-bd6f-9ebc50041058/image.png) ![](https://images.velog.io/images/bongf/post/dfffaa93-a337-4162-a668-88fc69b4444a/image.png)
     -	지역변수는 반드시 따로 초기화를 해야 한다. 
        -  ![](https://images.velog.io/images/bongf/post/9dda16e6-3b6b-4c19-a255-d34c137c9b21/image.png)
        -   x, y에 컴파일 에러 
-   변수명 사용 규칙
    -   변수명은 숫자로 시작할 수 없다
    -   \_(underscore)와 $ 문자 이외에 특수문자 사용 불가
    -   자바의 키워드는 변수명으로 사용할 수 없다. (int, class, return 등 var는 가능, 키워드 아님)

## 학습 5) 변수의 스코프와 라이프타임
-   선언된 변수를 사용할 수 있는 범위를 변수의 스코프라고 한다
-   라이프 타임은 해당 변수가 살아 있는 기간이다
-	[아래 내용 출처] (https://www.learningjournal.guru/article/programming-in-java/scope-and-lifetime-of-a-variable/)
  
#### 1. 인스턴스 변수 
-	클래스 내부 & 모든 메소드 바깥에 선언된 변수 
-	스코프 : static 메소드를 제외한 클래스 전체 ![](https://images.velog.io/images/bongf/post/08dab906-8c16-48b3-96b2-35ce9b6050d4/image.png) ![](https://images.velog.io/images/bongf/post/0b15820f-aead-43e9-aa35-db4ac3f50019/image.png)
-	라이프타임 : 인스턴스가 생성될 때부터 ~ 이 인스턴스 객체가 메모리에 있을 때까지 

#### 2. 클래스 변수 
-	클래스 내부 & 모든 블럭 바깥에 & static 으로 선언된 변수 
-	스코프 : 클래스 내부 전체 ![](https://images.velog.io/images/bongf/post/3c870329-1872-4296-8500-d551db8c34df/image.png)
-	라이프타임 : 프로그램 종료시까지 또는 클래스가 메모리에 로드되어있는동안 
  
#### 3. 지역변수 
-	인스턴스나 클래스 변수가 아닌 모든 변수 
-	스코프 : 선언된 블럭 내에서 
-	라이프타임 : 선언된 블록에서 제어권이 떠날 때까지 (예를 들어 메소드 안에 선언된 지역변수라고 하면 그 메소드가 사용되기 시작하면서부터 사용 종료 될때까지로 이해했다) 
  


## 학습 6) 타입 변환, 캐스팅 그리고 타입 프로모션
-   출처 : [https://www.w3schools.com/java/java\_type\_casting.asp](https://www.w3schools.com/java/java_type_casting.asp), 자바의정석 기초편 [https://bong-f.tistory.com/85?category=995830](https://bong-f.tistory.com/85?category=995830)

-   타입변환 : 데이터 타입을 기존과 다른 타입으로 변환하는 것
-   캐스팅 : 표현 범위가 더 좁은 쪽으로의 자동 형변환(묵시적형변환)
-   타입 프로모션 : 표현 범위가 더 넓은 쪽으로의 수동 형변환(명시적형변환)
-   기존의 값을 최대한 보존할 수 있는 타입으로 자동 형변환 되는 원칙이 있다. (다른 데이터 타입간의 연산이나 대입이 있을 때)
	-   그래서 더 넓은 표현 범위를 갖는 쪽으로 자동 형변환이 일어난다
  	-	자동 형변환 : 서로 다른 타입 간의 대입이나 연산을 할 때, 자동적으로 표현범위가 큰 쪽으로 형변환 해준다. 예를 들어 float a = 1234; 이면 1234는 정수형이지만 표현 범위가 더 넓은 float로 자동 형변환 된다.
-   값 손실이 발생하는 (더 좁은 표혐 범위로 형변환, 예를 들어 1234.56f를 1234 인 정수로 형변환 할 때 0,56의 형변환) 데이터간의 연산일 경우 에러가 뜬다. 명시적 형변환을 해줘야 한다. 값손실을 어떻게 처리할지 모르기 때문
	-   수동 형변환 : (타입)피연산자 방법으로 형환 할 수 있다. (int)1.6f
-	데이터 표현 범위 
 ![](https://images.velog.io/images/bongf/post/d335b82e-7376-4a1b-925e-e264e20121e3/image.png)
   
    


## 학습 7) 1차 및 2차 배열 선언하기

-   출처 : 자바의 정석 기초편
-   배열이란 : 같은 타입의 여러 변수를 하나의 묶음으로 다루는 것이다.
-   배열은 순서가 있다. 0부터 시작 (index)
-   배열 선언 방법 1차) : 타입\[ \] 변수이름; ( 타입 변수이름\[ \] 도 가능하다 )
-   배열 선언 방법 2차) : 타입\[ \]\[ \] 변수이름;
-   배열의 생성 : 변수이름 = new 타입\[길이\];  
    선언과 생성을 같이 한다면 타입\[ \] 변수이름 = new 타입\[길이\]; 가 되겠다.
-   배열에 값 저장하기  
    1) 배열을 생성한 후 배열\[i\] = 값 이렇게 하나씩 넣어주는 방식 (for문 활용도 가능하다)  
    2) 배열의 생성과 값 저장을 한 번에) 타입\[ \]\[ \] 변수이름; = new 타입\[ \]{50, 60, 70, 80, 80}; (이 때 길이는 안 적어도 된다  
    3) new new 타입\[ \] 생략하기) 타입\[ \]\[ \] 변수이름; = {50, 60, 70, 80, 80}; (단 이렇게 값을 넣으려면 배열을 선언할 때 해줘야 한다. ( int\[\] temp = new int\[3\]; temp = { 1, 3, 5 }는 불가능하다 )

## 학습 8) 타입 추론, var

-   참고 : [https://m.blog.naver.com/PostView.nhn?blogId=2feelus&logNo=220655685560&proxyReferer=https:%2F%2Fwww.google.com%2F](https://m.blog.naver.com/PostView.nhn?blogId=2feelus&logNo=220655685560&proxyReferer=https:%2F%2Fwww.google.com%2F) [https://catch-me-java.tistory.com/19](https://catch-me-java.tistory.com/19)
  
-   타입 추론이란 데이터타입을 명시하지 않아도 컴파일러가 데이터 타입을 추론하여 처리하는 것이다. ![](https://images.velog.io/images/bongf/post/4437b6fb-4a9a-468a-ab3d-ac1f483675d7/image.png) ![](https://images.velog.io/images/bongf/post/baa23a57-6d2e-44d9-8d80-57a18b2b588a/image.png)
-   var는 키워드가 아니다. int는 키워드라 변수로 쓸 수 있지만 var은 변수로 사용가능하다
-   var은 초기화 없이 사용할 수 없으며 초기화시 null 값이 들어갈 수 없다.
-   var은 지역변수에서만 선언 가능하고 멤버 변수로 활용이 불가능이다.
