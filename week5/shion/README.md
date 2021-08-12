## 클래스

### 1. 클래스

```java
public class Person {

}

class Animal {

}

class Car {

}

// 파일 이름 : Person.java
// 하나의 자바 코드(*.java)에 여러개의 class를 정의할 경우, 
// public class가 없거나, 파일 이름과 public class의 이름이 같아야한다.

class Person {

}

class Animal {

}

class Car {

}
```

```java
// 클래스는 내부에 변수, 초기화 블록, 메서드, 생성자, 클래스를 가질 수 있다.
public class Person {
    static String planet; // 클래스 변수, 모든 인스턴스가 같은 값을 공유한다.

    String name; // 인스턴스 변수
    int age;     // 인스턴스 변수

    static { // 클래스 초기화 블록, JVM이 클래스를 읽어들일 때 클래스 변수를 초기화한다.
        planet = "earth";
    }

    { // 인스턴스 초기화 블록, 객체 생성 시 인스턴스 변수를 초기화한다.
        name = "unknown";
        age = 1;
    }

    public Person() { // 기본 생성자
        
    }

    public Person(String name, int age) { // 생성자
        this.name = name;
        this.age = age;
    }

    public static String whereAreYouFrom() { // 클래스 메소드
        return planet;
    }

    public String whatYourName() { // 인스턴스 메소드
        return this.name;
    }

    public int howOldAreYou() { // 인스턴스 메소드
        return this.int;
    }

    class Experience { // 클래스
        int year;
    }
}
```
`* 자바소스코드를 실행하면 위의 클래스 정보는 JVM 메모리의 METHOD 영역에 저장됩니다.`

`Q. 생성자와 인스턴스 초기화 블록이 동시에 정의된다면 무슨 일이 일어날까?`

### 2. 객체
` new 키워드로 클래스에 정의된 생성자를 호출하면 해당 클래스 타입의 객체를 반환합니다.`

```java
class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class Main {
    public static void main(String[] args) {
        Person shion = new Person("시온", 30);
    }
}
```
`이름이 시온이고 나이가 30인 Person객체가 JVM 메모리 Heap 영역의 어떤 주소에 저장됩니다.`
`변수 shion에는 위의 Person객체가 저장된 Heap의 주소가 저장됩니다.`
`이제 Person객체는 변수 shion을 통해서 접근할 수 있습니다.`

### 3. 메소드
```java
public class Person {
    public void sayHello() {
        System.out.println("Hello!");
    }
}
```
`메소드를 호출하면 JVM 메모리의 METHOD 영역에 저장되어 있는, 메소드가 정의되어 있는 클래스정보에서 메소드를 찾아서 실행합니다. `

### 4. 생성자
`생성자는 반환타입이 없고, 클래스이름과 같은 이름을 가져야합니다..`
```java
public class Person {
    String name;
    int age;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }
}
```
`생성자는 따로 정의하지 않는다면 JVM이 컴파일 시 기본 생성자를 정의해줍니다.`
`그래서 클래스는 기본적으로 생성자 하나씩을 꼭 가지고 있습니다.`
`위의 Person 클래스처럼 여러개의 생성자를 오버로딩으로 정의할 수 있습니다.`

### 5. this
`this 키워드는 현재 객체 자기 자신을 가리킵니다.`
`생성자 내부 첫번째 라인에서 다른 생성자를 호출할 때에도 사용됩니다.`
```java
public class Person {
    String name;
    int age;

    // 주로 지역변수와 멤버변수를 구분하기 위해 사용됩니다.
    public Person(String name, int age) {
        // name과 age는 지역변수이므로 멤버변수에 저장되지 않고 생성자호출의 종료와 함께 사라집니다.
        // name = name; 
        // age = age;

        // this 키워드를 붙여주면 Person 클래스에 정의된 멤버변수 name을 가리켜서 비로소 생성자의 매개변수로 받은 name을 멤버변수 name에 저장할 수 있게 됩니다.
        this.name = name;
        this.age = age;
    }

    public Person() {
        // 위에 정의된 다른 생성자를 호출합니다.
        // new Person("시온", 30); 과 같습니다.
        this("시온", 30); 
    }

    public String whatYourName() {
        return this.name;
    }

    public int howOldAreYou() {
        return this.age;
    }
}

```

## Binary Tree

```java
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
}

public class BinaryTree {
    public static void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();

        System.out.print("BFS 순회결과 > ");
        
        queue.add(node);

        while(!queue.isEmpty()) {
            Node nodeData = queue.poll();
            System.out.print(nodeData.data + " ");

            if (nodeData.left != null) {
                queue.add(nodeData.left);
            }

            if (nodeData.right != null) {
                queue.add(nodeData.right);
            }
        }
    }

    public static void dfs(Node node) {
        if(node.left != null) {
            dfs(node.left);
        }

        System.out.print(node.data + " ");

        if (node.right != null) {
            dfs(node.right);
        }
    }

    /**
     *       7       level 1
     *     /  \
     *    6    5     level 2
     *   / \  / \
     *  4  3 2   1   level 3
     *
     *  BFS : 7 6 5 4 3 2 1
     *  DFS : 4 6 3 7 2 5 1
     */
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node7.left = node6;
        node7.right = node5;
        node6.left = node4;
        node6.right = node3;
        node5.left = node2;
        node5.right = node1;

        bfs(node7);
        System.out.println();
        System.out.print("DFS 순회결과 > ");
        dfs(node7);
    }
}

/**
 *  결과
 *  BFS 순회결과 > 7 6 5 4 3 2 1 
 *  DFS 순회결과 > 4 6 3 7 2 5 1   
 */
```
