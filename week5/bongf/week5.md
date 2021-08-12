## 학습
### 학습 1) 클래스 정의하는 방법
```
class 클래스 이름 {
}
```
```
class 클래스 이름 extends SUPERCLASS implements INTERFACE{
}
```
-	클래스 정의할 때 가지는 속성들 
	-	1) 접근제어자 
    -	2) 클래스이름
    -	3) (없을 수도 있다) superclass : 부모 클래스 
    -	4) (없을 수도 있다) interface 
    -	5)	클래스의 바디 { } 안 
        -	생성자 
        -	멤버변수(필드) 
        -	메소드 

### 학습 2) 객체 만드는 방법 (new 키워드 이해하기)
-	클래스의 객체가 만들어진 것을 인스턴스를 만들었다고 한다. 
	-	변수만 선언한 것은 인스턴스를 생성한 것이 아니다. 
    ```
    public class Practice {
    	public static void main(String[] args) throws ClassNotFoundException {
        	MadeClass mc;
    	}
	}
    ```
    - 이렇게 되면 변수만 선언한 것. 
    -	인스턴스를 생성하기 위해서는 `new`를 사용해야 한다 
    	-	`new`뒤에는 클래스의 생성자 메서드가 온다 
    ```
    MadeClass mc = new MadeClass(); 
    ```
    -	`new`는 객체를 생성하고(힙에) 객체 주소를 반환한다. 
    -	참조변수에는 이 객체 주소가 저장된다. 
    -	이 참조변수를 가지고 객체에 접근할 수 있다.
    -	참조변수와 연결이 끊긴 객체는 gc가 정리한다. 
    -	생성한 객체를 변수에 반드시 저장할 필요는 없다. 속성 값을 바로 도출할 수 있다. 
    ```
    int height = new Rectangle().height;
    ```
### 학습 3) 메소드 정의하는 방법
```
public double calculateAnswer(double wingSpan, int numberOfEngines,
                              double length, double grossTons) {
    //do the calculation here
}
```
#### 구성요소 
-	1) 접근제어자
-	2) return 타입 
-	3) 메소드이름 
-	4) 파라미터 리스트(`()` 안에)
-	5) (예외던질경우) 던질 예외 리스트 
-	6) 메소드 바디 (`{}` 안에)
-	필수요소는 메서드의 리턴타입, 이름, `{}`, `()` (부호 그 자체)
#### 메소드 오버로딩 
-	한 클래스 내에 동일한 명의 메소드가 있을 수 있다. 
```
public class DataArtist {
    ...
    public void draw(String s) {
        ...
    }
    public void draw(int i) {
        ...
    }
    public void draw(double f) {
        ...
    }
    public void draw(int i, double f) {
        ...
    }
}
```
-	parmeter 리스트가 달라야 한다. 

### 학습 4) 생성자 정의하는 방법
-	생성자 : 인스턴스가 생성될 때마다 호출되는 인스턴스 초기화 메서드 
	-	생성자 이름은 클래스의 이름과 같다 
    -	생성자는 반환값이 없다 
    -	생성자는 인스턴스를 초기화 할 때 필요한 작업들을 정리해 주는 것이고 위에서 했든 인스턴스를 만드는 것은 `new` 키워드 
-	클래스 Bicycle의 생성자 
```
public Bicycle(int startCadence, int startSpeed, int startGear) {
    gear = startGear;
    cadence = startCadence;
    speed = startSpeed;
}
```
-	해당 생성자의 사용 예시 
```
Bicycle myBike = new Bicycle(30, 0, 8);
```
-	클래스에 생성자를 작성하지 않을 수도 있다. 
	-	컴파일러가 자동으로 생성. 인자가 없는 default 생성자 생성
    -	이 기본 생성자는 superclass의 인자가 없는 생성자를 호출한다. 
    
### 학습 5) this 키워드 이해하기
-	현재 객체 내에서 메소드나 생성자에서 해당 객체를 호출하기 위해 사용한다. 
-	static에서는 사용할 수 없다. 
-	필드 호출할 때 
```
public class Point {
    public int x = 0;
    public int y = 0;
        
    //constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```
-	동일 클래스 내에 다른 생성자를 호출할 때
```
public class Rectangle {
    private int x, y;
    private int width, height;
        
    public Rectangle() {
        this(0, 0, 1, 1);
    }
    public Rectangle(int width, int height) {
        this(0, 0, width, height);
    }
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    ...
}
```
## 과제 

### 1. 이진트리 
-	이진트리란 ? 각 노드가 최대 2개의 자식 노드를 가진 구조 
![](https://images.velog.io/images/bongf/post/37b43304-fbf7-4709-b6bc-d1290deb22de/image.png)
	-	[이미지출처](https://ko.wikipedia.org/wiki/%EC%9D%B4%EC%A7%84_%ED%8A%B8%EB%A6%AC)
### 2. 탐색 알고리즘 BFS, DFS
-	![](https://images.velog.io/images/bongf/post/14079b63-ac08-4168-9477-4cd468f9b520/image.png)
	-	[그림출처](https://www.quora.com/When-you-start-a-DFS-search-from-the-root-of-a-tree-do-you-still-need-to-keep-track-of-visited-nodes-like-you-would-in-a-graph)
-	BFS(Breadth-first Search) 너비우선탐색 
	-	루트 노드에서 시작해서 현재 depth에 있는 노드를 다 탐색한 후 다음 depth로 탐색을 시작한다. 
-	DFS(Depth-first Search) 깊이우선탐색 
	-	루트노드에서 시작해서 각 자손의 제일 아래에 있는 자식노들 까지 다 탐색한후 다음 형제 노드로 가는 방법 
    -	루트노드 방문을 언제 하느냐에 따라서 Pre-order(전위), In-order(중위), Post-order(후위)가 있다. 
    	-	Pre-order(전위) : 루트 -> 왼쪽 자식 노드를 루트로 하는 서브트리를 재귀로 Pre-order -> 오른쪽 자식 노드를 루트로 하는 서브트리를 재귀로 Pre-order
        -	In-order(중위) : 왼쪽 자식 노드 루트로 하는 서브트리를 재귀로 In-order -> 루트 ->  오른쪽 자식 노드 루트로 하는 서브트리를 재귀로 In-order
    	-	Post-order(후위) : 왼쪽 자식 노드 루트로 하는 서브트리를 재귀로 Post-order -> 오른쪽 자식 노드 루트로 하는 서브트리를 재귀로 Post-order -> 루트 
        -	![](https://images.velog.io/images/bongf/post/8d4722b2-4332-4c15-822d-3084e27cfd0c/image.png) 
        -	출처 : 위키

### 3. 구현 코드 
```
public class BinaryTree {
    private List<Integer> dfsVisited = new ArrayList<>();

    public BinaryTree() {
    }

    public List<Integer> bfs(Node root) {
        List<Integer> visited = new ArrayList<>();
        Queue<Node> toCheckNodes = new LinkedList<>();
        toCheckNodes.offer(root);

        while (!toCheckNodes.isEmpty()) {
            Node node = toCheckNodes.poll();
            visited.add(node.getValue());

            if (node.getLeft() != null) {
                toCheckNodes.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                toCheckNodes.offer(node.getRight());
            }
        }
        return visited;
    }

    public List<Integer> dfs(Node root) {
        if (root.getLeft() != null) {
            dfs(root.getLeft());
        }

        dfsVisited.add(root.getValue());

        if (root.getRight() != null) {
            dfs(root.getRight());
        }
        return dfsVisited;
    }
}
```
```
public class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
```
### 4. 테스트 코드 
```
class BinaryTreeTest {
    BinaryTree binaryTree = new BinaryTree();
    Node root;

    /*
     *                0
     *              /  \
     *             1    2
     *            /\    /\
     *           3  4  5  6
     */
    @BeforeEach
    void set() {
        root = new Node(0);
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        root.setLeft(one);
        root.setRight(two);
        one.setLeft(three);
        one.setRight(four);
        two.setLeft(five);
        two.setRight(six);
    }

    @Test
    void dfs() {
        assertThat(binaryTree.bfs(root).toString()).isEqualTo("[0, 1, 2, 3, 4, 5, 6]");
    }

    @Test
    void bfs() {
        assertThat(binaryTree.dfs(root).toString()).isEqualTo("[3, 1, 4, 0, 5, 2, 6]");
    }
}
```



--- 
### 출처
#### 학습
-	학습1) 
	-	https://docs.oracle.com/javase/tutorial/java/javaOO/classdecl.html
	-	https://www.geeksforgeeks.org/classes-objects-java/
-	학습2) 
	-	https://docs.oracle.com/javase/tutorial/java/javaOO/objectcreation.html
-	학습3) 
	-	https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html
-	학습4) 
	-	https://docs.oracle.com/javase/tutorial/java/javaOO/constructors.html
-	 학습5) 
	-	https://docs.oracle.com/javase/tutorial/java/javaOO/thiskey.html
	-	https://docs.oracle.com/javase/tutorial/java/javaOO/thiskey.html#:~:text=Within%20an%20instance%20method%20or,a%20constructor%20by%20using%20this%20.
#### 과제
-	1) 이진트리 
	-	https://ratsgo.github.io/data%20structure&algorithm/2017/10/21/tree/
-	2) 탐색 알고리즘 
	-	https://en.wikipedia.org/wiki/Tree_traversal











