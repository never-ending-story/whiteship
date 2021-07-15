##### 자바 프로그램의 컴파일과 실행

자바는 플랫폼에 독립적인 언어이기 때문에 컴파일이 한 단계만으로 수행되지 않는다.

대신에 두 단계의 실행을 포함한다. 첫째로, OS로 부터 독립적인 컴파일러를 거치고, 두번째로 JVM을 거쳐서 실행된다.



### Compilation(컴파일)

.java파일은 컴파일러를 거치면서, 바이트 코드로 변환된다.

각각 클래스의 내용은 .class 파일에 저장된다.

소스코드를 바이트 코드로 변환할 때, 컴파일러가 수행하는 단계는 아래와 같다.

- **Parse**

  .java 파일을 읽고 파생된 토큰 순서를 AST(Abstract Syntax Tree)-Nodes로 매핑한다.

    - Abstract Syntax Tree? 추상 구문 트리(소스 코드의 추상 구문 구조의 트리), 컴파일러에 널리 사용되는 자료 구조

   <img width="411" alt="스크린샷 2021-07-08 오후 5 47 00" src="https://user-images.githubusercontent.com/65011131/124962808-07f35c80-e05a-11eb-8ef3-c0983caae509.png">


- **Enter**

  symbol을 symbol table에 입력한다.

    - symbol? cannot find symbol 에러의 그 symbol 인 듯 하다.

    - symbol table

      컴파일러 혹은 인터프리터와 같은 언어 변환기에서 사용되는 key-value 형식의 데이터 구조

      아래 예시: C 언어 프로그램의 심볼 테이블

     <img width="411" alt="스크린샷 2021-07-08 오후 6 02 37" src="https://user-images.githubusercontent.com/65011131/124962835-0f1a6a80-e05a-11eb-96f3-3e32d2acb600.png">


- **Precess annotations**

  어노테이션들을 처리한다.

- **Attribute**

  Syntax tree(구문 트리)에 속성을 부여한다.

  이름 확인, 타입 검사, 상수의 인식과 평가(constant folding: the process of recognizing and evaluating constant expressions at compile time rather than computing them at runtime. 상수에 대한 검사라고 보면 될 듯)

- **Flow**

  이전 단계의 트리에 대한 데이터 흐름 분석을 수행한다.

  할당과 도달 가능성에 대한 검사를 포함한다.

- **Desugar**

  추상 구문 트리를 재 작성하고 syntactic sugar(자주 쓰이는 표현을 더 쉽게 읽고 쓸 수 있게 해주는 문법, 예) idx += 1)를 변역한다.

- **Generate**

  .class 파일을 생성한다.



### Execution (실행)

.class 파일은 하드웨어나 OS에 독립적이기 때문에 어느 시스템에서든 실행될 수 있다.

이를 실행하기 위해서는 main 메소드를 포함한 클래스 파일을 JVM에게 전달하고, 주요 단계를 거치게 된다.

주요 단계는 아래와 같다.

1. **Class Loader**

   main 메소드가 포함된 .class 파일은 JVM에 전달되면서 메모리에 로딩된다.

   모든 참조되는 클래스들도 클래스 로더를 통해 로딩된다.

2. **Bytecode Verifier** (코드 검증)

   바이트 코드는 클래스 로더에 의해 로딩된 후에, bytecode verifier에 의해 <u>검증이 필요</u>하다.

   bytecode verifier는 위험한 동작을 수행하지 않는지를 확인하는 역할을 한다.

   아래의 내용이 검증되어야 할 항목들이다.

    - 변수들이 사용되기 전에 초기화 된다.
    - 메소드 호출이 객체 참조의 타입과 일치한다.
    - private한 데이터와 메소드들의 접근에 대한 규칙이 위배되지 않는다.
    - 로컬 변수의 접근은 런타임 스택내에 속한다.
    - 런타임 스택이 overflow되지 않는다.

   위의 내용의 검증이 실패하면 bytecode verifier는 클래스가 로딩되는 것을 허락하지 않는다.

3. **JIT Compiler**

   Just-In-Time 컴파일러

   인터프리터의 효율을 높이기 위해 사용된다.

   모든 바이트 코드를 컴파일하고, 기계어로 바꾼다. 인터프리터가 반복된 메소드 호출을 마주하면, JIT이 바로 해당하는 부분의 기계어를 제공하여 Interpreter 가 수행하는 반복적인 기계어 번역 과정을 하지 않아도 되어서 효율성이 높아진다.




참고 : https://www.geeksforgeeks.org/compilation-execution-java-program/

https://ko.wikipedia.org/wiki/%EC%8B%AC%EB%B3%BC_%ED%85%8C%EC%9D%B4%EB%B8%94 (심볼 테이블)

https://en.wikipedia.org/wiki/Constant_folding (Constant folding)

