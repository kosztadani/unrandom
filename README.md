# Instrumentation of java.util.Random

This is mostly intended as a demonstration of the powers of the
Java instrumentation API.

Don't expect this to be particularly useful.

This is a Java agent that instruments `java.util.Random` such that calls to its
no-arg constructor will be replaced with a call to its one-arg constructor, with
0 passed as an argument.

## Build

To create a JAR file containing the agent and all its dependencies, invoke:

```bash
./gradlew shadowJar
```

## Example usage

### Test class

Create and compile this test class: `Hello.java`:
```java
import java.util.Random;
public class Hello {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
    }
}
```

```bash
javac Hello.java
```

### Test without agent
```bash
java Hello
```

This way, two random integers will be printed to standard output.

### Test with agent
```bash
java -javaagent:build/libs/unrandom-0.1-SNAPSHOT-all.jar Hello
```

This way, you will always get the same two integers:
```text
-1155484576
-723955400
```