### 목표
자바가 제공하는 제어문을 학습하세요.

### 학습 1. 제어문 (Control Flow Statement) 
- 제어문이란 코드의 실행 흐름을 제어하는 구문이다.  
-	소스 파일의 문장들을 원래 위에서부터 아래로 실행된다. 제어문은 조건문, 반복문, 분기문으로 이 흐름을 파괴한다. 
	-	프로그램이 어떤 조건 하에서 특정 블록의 코드를 실행하도록 만든다. 

|분류| |
|:--:|:--:
|선택문(조건문)(the decision-making statements) | if-then, if-then-else, switch
|반복문(the looping statements)| for, while, do-while
|분기문(the branching statements) | break, continue, return 

### 학습 2-1. 선택문 - if-then 
```
if ( 조건식 ) {
	//statement(s)
}
```
- 조건식은 boolean, Boolean 이어야 한다. 
- 조건식이 `true`일 때 `{ }` 안의 statement가 실행한다. statement 실행 후 `if-then` 구문 빠져 나온다. 
- 조건식이 `false`일 때 `if-then` 구문 빠져나온다. 
- statement가 딱 한 개일 때 `{ }`는 생략할 수 있다. 

### 학습 2-2. 선택문 - if-then-else  
```	
if ( 조건식 ) {
	//statement(s)1 
} else {
	//statement(s)2 
}
```
- 조건식이 `true`면 statement1 실행. statement1 실행 후 `if-then-else` 구문 빠져나온다.
- 조건식이 `false`면 statement2 실행. statement2 실행 후 `if-then-else` 구문 빠져나온다. 

### 학습 2-3. 선택문 - switch 
```
switch (조건식) {
    case 값1: 
    	.. break; 
    case 값2: 
    	... break; 
    ...
    default:
    	... break;
}
```

-	조건식의 결과에 해당하는 `case`문으로 이동해서 해당 statement 실행. `break`문을 만나면 `switch`문 전체를 빠져 나온다. 
	- `break`를 쓰지 않아도 되는 향상된 `case`문은 전 주 과제 참고 

- `default`는 `case`문에 해당하는 값 외에 모든 값 (`else`같은)

- 조건식에는 `char`, `byte`, `short`, `int`, `Character`, `Byte`, `Short`, `Integer`, `String`, `enum`이 가능하다.

- 마지막 분기의 `break`는 필요하지 않다. (작성 안 해도 잘 작동된다) 그래도 쓰는 것을 추천한다. 코드 수정이 쉽고 에러가 적을 수 있다. 

-	**언제사용?** 처리해야하는 경우의 수가 많다면 `if`보다 `switch`를 사용한다. `switch`문은 가능할 수 있는 실행 경로를 여러 개 가질 수 있기 때문. 
	-	만약 `switch`문은 한 번만 조건식을 확인해주면 되지만 `if문`을 여러 개 쓸 경우 매 번 조건식을 확인하게 된다. ```if(x=a).. if(x=b)...```

- **주의** `switch`문의 조건절의 값이 `null`이면 `NullPointException` 발생 가능성 있으므로 돌리기 전에 이를 체크하도록 하자 

- 향상된 `switch`문은 지난 시간에 했던 것 참고 

### 학습 3-1. 반복문 - while문
#### while문 
```
while ( 조건식) {
	statement(s)
}
```
- 조건식이 참이면 statement 실행 
-	계속 조건식 체크해서 조건식 거짓이면 while문 빠져나온다.
	-	계속 체크 한다는 말은 매 iteration의 `{ }`이 한 번 끝난 후 체크한다는 말 
```
 public static void breakWhile() {
        int a = 0;
        while( a <= 3) {
            a += 4;
            System.out.println(a); // 결과값 4
        }
    }

    public static void breakWhile2() {
        int a = 0;
        while( a <= 3) {
            a += 4;
            a -= 1;
            System.out.println(a); // 결과값 3, 6 
        }
    }
```
#### do-while문 
```
do {
     statement(s)
} while (조건식);
```
- `do`안에 statments를 먼저 실행하고 조건식을 체크한다. 따라서 `do` 안의 statements는 무조건 적어도 한 번은 실행된다.  
```
 public static void doWhile(){
        int count = 1;
        do {
            System.out.println("Count is: " + count);
            count++;
        } while (count < 1);
    }
// 결과값 Count is: 1
```

### 학습 3-2. 반복문 - for문
-	`기본 for문`과 `for each문(향상된 for문)`이 있다. 

#### 기본 for문 
```
 for( 초기화, 조건식, 증감식) {
 	statement(s)
 }
```
- 값의 범위에서 하나씩 돌며 반복한다.(iterate) 
- 조건식이 `false`가 되면 루프가 중지된다. 
- 매 iteration 후에 값이 증감식에 따라 변화된다. 
- 초기화, 조건식, 증감식은 선택사항이다. 무한루프도 만들 수 있다. (아래) 
```
for ( ; ; ) {
    
}
```
- 루프 중간에 조건식이 `false`가 되면 `for문`을 빠져나온다. 단, `while문`과 마찬가지로 `for문` 안의 한 번의 iteration안의 모든 statement가 끝나고 조건식을 확인한다. 
```
	public static void breakFor() {
        for (int i = 0; i <= 2; i++) {
            i += 3;
            System.out.println(i); //결과값 3 
        }
    }

    public static void breakFor2() {
        for (int i = 0; i <= 2; i++) {
            i += 3;
            i -= 3;
            System.out.println(i); //결과값 0, 1, 2
        }
    }
```


#### for each문(향상된 for문)
- `for문`보다 가독성이 좋다. 
```
for ( 타입 var : 식) {
	statement(s)
}
```
- 식에는 iterable 가능한 것들이 온다. (collections, array) 

### 학습 4-1. 분기문 - break
- break 는 label 있는 것과 없는 것이 있다. 
- label 없는 것은 가장 안쪽의 `switch`, `for`, `while`, `do-while을` 빠져나오게 한다. 
- label 있는 것은 해당 label이 붙은 statement를 빠져나오게 한다. 
```
  public static void labeledForStatement() {
        int x = 0;
        int y = 0;
        loop1: for (int outLoop=0; outLoop<=10; outLoop++){
            x = outLoop;
            for (int inLoop=0; inLoop<=5; inLoop++){
                y = inLoop;
                if(inLoop==3) {
                    break loop1;
                }
            }
        }
        System.out.println(x+","+y); //결과값 0,3
    }
```
### 학습 4-2. 분기문 - continue
- `continue`는 `for`,`while`,`do-while`의 iteration을 skip하게 해준다. (`for문`에서 `continue`가 중간에 있다고 하면 `continue` 다음의 statement를 실행 안하고 다음 iteration 부터 시작) 
- 출처에서 가져온 예 
```
 public static void main(String[] args) {

        String searchMe = "peter piper picked a " + "peck of pickled peppers";
        int max = searchMe.length();
        int numPs = 0;

        for (int i = 0; i < max; i++) {
            // interested only in p's
            if (searchMe.charAt(i) != 'p')
                continue;

            // process p's
            numPs++;
        }
        System.out.println("Found " + numPs + " p's in the string.");
    }
```
- `break`처럼 label이 있는 것과 없는 것이 있다. 
### 학습 4-1. 분기문 - return 
- 이미 아는 내용이라 따로 정리하지 않음 



---

### 출처 
-	https://lucas.codesquad.kr/masters-2021/course/%EB%A7%88%EC%8A%A4%ED%84%B0%EC%A6%88-%EB%B0%B1%EC%97%94%EB%93%9C-Java-%ED%81%B4%EB%9E%98%EC%8A%A4-2021/Java-%EA%B0%95%EC%9D%98-%EB%AA%A8%EC%9D%8C/unit-test

#### 학습1. 제어문 
- https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html
- https://www.youtube.com/watch?v=Wv-E61YtzNI&t=2s
#### 학습2. 선택문  
- if문 : https://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html#jls-14.9
https://docs.oracle.com/javase/tutorial/java/nutsandbolts/if.html
- switch문 : https://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html#jls-14.11
(성능비교) https://aahc.tistory.com/6
(성능비교) https://www.notion.so/04-c95ef49a7923425d84f403b5ba583bf4
(성능비교) https://thinkpro.tistory.com/132
#### 학습3. 반복문  
- while문 :
https://docs.oracle.com/javase/tutorial/java/nutsandbolts/while.html
https://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html#jls-14.12
- for문 :
https://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html#jls-14.14
https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html
#### 학습4. 분기문 
https://docs.oracle.com/javase/tutorial/java/nutsandbolts/branch.html
- break : 
https://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html#jls-14.15
- continue : 

