What is JDK, JRE and JVM ?

1. JVM(Java Virtual Machine) is the engine that actually runs the Java program. It reads the compiled bytecode and executes it on the your machine. The JVM is what makes Java work on any OS like Windows, Mac or LINUX - without changing the code.

2. JRE(JAVA Runtime Environment) is the package that contains the JVM plus the standard Java libraries the program needs to run. If you want to run a java program, the JRE is enough

3. JDK(Java Development Kit) is the full toolkit for developing Java Programs. It includes the JRE(and threfore the JVM),plus the compiler(javac) and other developer tools. As a developer, you always install the JDK.

JDK > JRE > JVM

What is Bytecode ?
When you write a Java code and compile it using javac, it does not get converted directly into a machine code. Instead, it gets compiled into an intermediate format called the byte code, stored in .class files.

Bytecode is not specific to any operating system or processor. It is a set of instructions designed for the JVM to understand. When you run our program the JVM reads the bytecode and translates it into the native machine code for the system you are on - at runtime.

This is the concept of compile once and run anywhere.

What Does "Write Once, Run Anywhere" Mean?
"Write Once, Run Anywhere" is Java's core promise. It means you write your Java code once, compile it into bytecode, and that same .class file can run on any device or operating system that has a JVM installed — without any changes.

For example, you can write and compile a Java program on a Windows laptop. That same compiled .class file can then be run on a Mac, a Linux server, or even a smart TV — as long as a JVM is available on that device. The JVM acts as a bridge between your bytecode and the underlying hardware, handling all the platform-specific differences for you.

This was a major breakthrough when Java was introduced, because before this, developers often had to rewrite or heavily adjust their code for each platform they wanted to support.
