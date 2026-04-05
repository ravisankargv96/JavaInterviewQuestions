
Interview questions on Nested classses and inner classes
### 1) What are nested classes in java?
Class declared with in another class is defined as nested class.
There are two types of nested classes in java.
2) Static nested class
3) Non static nested class
A static nested class has static keyword declared before class definition.

### 2) What are inner classes or non static nested classes in java?
Nested classes without any static keyword declaration in class definition are defined as non static nested
classes. Generally non static nested classes are referred as inner classes.
There are three types of inner classes in java :
2) Local inner class
3) Member inner class
4) Anonymous inner class

### Why to use nested classes in java?
(or)
### What is the purpose of nested class in java?

1) Grouping of related classes
Classes which are not reusable can be defined as inner class instead of creating inner class.
For example : We have a submit button upon click of submit button we need to execute some code. This
code is related only to that class and cannot be reused for other class . Instead of creating a new class we
can create inner class
2) To increase encapsulation :
Inner class can access private members of outer class.so by creating getter and setter methods for
private variables , outside world can access these variables. But by creating inner class private variables
can be accesed only by inner class.
3) Code readable and maintainable :
Rather than creating a new class we can create inner class so that it is easy to maintain.
30
4) Hiding implementation :
Inner class helps us to hide implementation of class.

### Explain about static nested classes in java?
When a static class is defined inside a enclosing class we define that as nested class. Static nested classes
are not inner classes. Static nested classes can be instantiated without instance of outer class.
A static nested doesnot have access to instance variables and non static methods of outer class.

### How to instantiate static nested classes in java?
We can access static members and static methods of outer class without creating any instance of outer
class.
Syntax for instantiating Static nested class :
OuterclassName.StaticNestedClassName ref=new OuterclassName.StaticNestedClassName();

### Explain about method local inner classes or local inner classes in java?
Nested classes defined inside a method are local inner classes. We can create objects of local inner class
only inside method where class is defined. A local inner classes exist only when method is invoked and
goes out of scope when method returns.

### Explain about features of local inner class?
1) Local inner class does not have any access specifier.
2) We cannot use access modifiers static for local inner class. But we can use abstract and final for local
inner class.
3) We cannot declare static members inside local inner classes.
4) We can create objects of local inner class only inside method where class is defined.
5) Method local inner classes can only access final variables declared inside a method.
6) Method local inner classes can be defined inside loops(for,while) and blocks such as if etc.

### Explain about anonymous inner classes in java?
Inner class defined without any class name is called anonymous inner class. Inner class is declared and
instantiated using new keyword.The main purpose of anonymous inner classes in java are to provide
interface implementation. We use anonymous classes when we need only one instance for a class. We can
use all members of enclosing class and final local variables.
When we compile anonymous inner classes compiler creates two files
8) EnclosingName.class
9) EnclsoingName$1.class

### Explain restrictions for using anonymous inner classes?
1) An anonymous inner class cannot have any constructor because there is no name for class.
2) An anonymous inner class cannot define static methods, fields or classes.
3) We cannot define an interface anonymously.
4) Anonymous inner class can be instantiated only once.

### Is this valid in java ? can we instantiate interface in java?
Runnable r = new Runnable() {
@Override
public void run() {
}
};
Runnable is an interface.If we see the above code it looks like we are instantiating Runnable interface. But
we are not instantiating interface we are instantiating anonymous inner class which is implementation of
Runnable interface.

### Explain about member inner classes?
Non static class defined with in enclosing class are called member inner class. A member inner class is
defined at member level of class. A member inner class can access the members of outer class including
private members.
Features of member inner classes :
7) A member inner class can be declared abstract or final.
8) A member inner class can extend class or implement interface.
9) An inner class cannot declare static fields or methods.
10) A member inner class can be declared with public, private, protected or default access.
31

### How to instantiate member inner class?
OuterClassName.InnerclassName inner=new OuterClassReference.new InnerClassName();
We cannot instantiate inner class without outer class reference

### How to do encapsulation in Java?
Make instance variables private.
Define getter and setter methods to access instance variables .

### What are reference variables in java?
Variables which are used to access objects in java are called reference variables.
Ex : Employee emp=new Employee();
In the above example emp is reference variable.
Reference variable can be of only one type.
A reference variable can point to any number of objects. But if a reference variable is declared final it
can’t point to other objects.
A reference variable can be declared either to a class type or interface type. If a reference variable is
declared with interface type it points to the class that implements the interface.

### Will the compiler creates a default constructor if I have a parameterized constructor in
the class?
No compiler won’t create default constructor if there is parameterized constructor in the class. For
example if I have a class with no constructors, then compiler will create default constructor.
For Example :
```java
public classCar {}
```

In the above Car class there are no constructors so compiler creates a default constructor.

```java
public classCar {
	Car(String name) {
	}
}
```
In this example compiler won’t create any default constructor because already there is one constructor in
the Car class.

### Can we have a method name same as class name in java?
Yes we can have method name same as class name it won’t throw any compilation error but it shows a
warning message that method name is same as class name.

### Can we override constructors in java?
Only methods can be overridden in java. Constructors can’t be inherited in java. So there is no point of
verriding constructors in java.

### Can Static methods access instance variables in java?
No.Instance variables can’t be accessed in static methods. When we try to access instance variable in
static method we get compilation error. The error is as follows:
Cannot make a static reference to the non static field name

### How do we access static members in java?
Instance variables and instance methods can be accessed using reference variable . But to access static
variables or static methods we use Class name in java.

### Can we override static methods in java?
Static methods can’t be overridden. If we have a static method in superclass and subclass with same
signature then we don’t say that as overriding. We call that as

### Difference between object and reference?
Reference and object are both different. Objects are instances of class that resides in heap memory.
Objects does’nt have any name so to access objects we use references. There is no alternative way to
access objects except through references.
32
Object cannot be assigned to other object and object cannot be passed as an argument to a method.
Reference is a variable which is used to access contents of an object. A reference can be assigned to other
reference ,passed to a method.

### 160 ) Objects or references which of them gets garbage collected?
Objects get garbage collected not its references.

### How many times finalize method will be invoked ? who invokes finalize() method in java?
Finalize () method will be called only once on object. Before the object gets garbage collected garbage
collector will call finalize() method to free the resources. Finalize() method will be called only when object
is eligible for garbage collection.

### Can we able to pass objects as an arguments in java?
Only references can be passed to a method not an object. We cannot pass the objects to a method. The
largest amount of data that can passed as parameters are long or double.

### Explain wrapper classes in java?
Converting primitives to objects can be done with the help of wrapper classes. Prior to java 1.5 we use
Wrapper classes to convert primitives to objects. From java 1.5 we have a new feature autoboxing which
is used to convert automatically primitives to objects but in wrapper classes programmer has to take care
of converting primitives to objects.
Wrapper classes are immutable in java. Once a value is assigned to it we cannot change the value.

### Explain different types of wrapper classes in java?
For every primitive in java we have corresponding wrapper class. Here are list of wrapper classes
available in java.
Primtive Wrapper Class
boolean Boolean
int Integer
float Float
char Character
byte Byte
long Long
short Short

### Explain about transient variables in java?
To save the state of an object to persistent state we use serialization. If we want a field or variable in the
object not to be saved, then we declare that variable or field as transient.
Example : public Class Car implements serializable
{
transient int carnumber;
}

### Can we serialize static variables in java?
Static variables cannot be serialized in java.

### What is type conversion in java?
Assigning a value of one type to variable of other type is called type conversion.
Example : int a =10;
long b=a;
There are two types of conversion in java:
17) Widening conversion
18) Narrowing conversion

### Explain about Automatic type conversion in java?
Java automatic type conversion is done if the following conditions are met :
20) When two types are compatible
Ex : int, float
int can be assigned directly to float variable.
21) Destination type is larger than source type.
Ex : int, long
33
Int can be assigned directly to long .Automatic type conversion takes place if int is assigned to long
because long is larger datatype than int.
Widening Conversion comes under Automatic type conversion.

### Explain about narrowing conversion in java?
When destination type is smaller than source type we use narrowing conversion mechanism in java.
Narrowing conversion has to be done manually if destination type is smaller than source type. To do
narrowing conversion we use cast. Cast is nothing but explicit type conversion.
Example : long a;
byte b;
b=(byte)a;
Note : casting to be done only on valid types otherwise classcastexception will be thrown.

### Explain the importance of import keyword in java?
Import keyword is used to import single class or package in to our source file.import statement is
declared after package decalaration. We use wild character (*) to import package.
Note : After compilation the compiled code does not contain import statement it will be replaced with fully
qualified class names

### Explain naming conventions for packages ?
Sun defined standard naming conventions for packages.
25) Package names should be in small letters.
26) Package name starts with reverse company domain name (excluding www) followed by department
and project name and then the name of package.
Example : com.google.sales.employees

### What is classpath ?
The path where our .class files are saved is referred as classpath.JVM searches for .class files by using the
class path specified. Class path is specified by using CLASSPATH environment variable. CLASSPATH
environment variable can contain more than one value. CLASSPATH variable containing more than one
value is separated by semicolon.
Example to set class path from command prompt :
set CLASSPATH= C:Program FilesJavajdk1.6.0_25bin;.;
only parent directories need to be added to classpath.Java compiler will look for appropriate packages and
classes.

### What is jar ?
Jar stands for java archive file. Jars are created by using Jar.exe tool. Jar files contains .class files, other
resources used in our application and manifest file.Manifest file contains class name with main method.jar
contains compressed .class files. Jvm finds these .class files without uncompressing this jar.

### What is the scope or life time of instance variables ?
When object is instantiated using new operator variables get allocated in the memory.instance variables
remain in memory till the instance gets garbage collected

### Explain the scope or life time of class variables or static variables?
Static variables do not belong to instances of the class. We can access static fields even before
instantiating the class. Static variable remain in memory till the life time of application.

### Explain scope or life time of local variables in java?
Local variables are variables which are defined inside a method. When the method is created local
variables gets created in stack memory and this variable gets deleted from memory once the method
execution is done.

### Explain about static imports in java?
From Java 5.0 we can import static variables in to source file. Importing static member to source file is
referred as static import. The advantage of static import is we can access static variables without class or
interface name.
Syntax : import static packagename.classname.staticvariablename;
Ex : import static com.abc.Employee.eno;
To import all static variables from a class in to our source file we use *.
import static com.abc.Employee.*
34

### Can we define static methods inside interface?
We can’t declare static methods inside interface. Only instance methods are permitted in interfaces.only
public and abstract modifiers are permitted for interface methods. If we try to declare static methods
inside interface we get compilation error saying
“Illegal modifier for the interface method Classname.methodName(); only public & abstract are
permitted”.

### Define interface in java?
Interface is collection of abstract methods and constants. An interface is also defined as pure or 100
percent abstract class.Interfaces are implicitly abstract whether we define abstract access modifier or not.
A class implementing interface overrides all the abstract methods defined in interface. Implements
keyword is used to implement interface.

### What is the purpose of interface?
Interface is a contract . Interface acts like a communication between two objects. When we are defining
interface we are defining a contract what our class should do but not how it does. An interface does’nt
define what a method does. The power of interface lies when different classes that are unrelated can
implement interface. Interfaces are designed to support dynamic method resolution at run time.

### Explain features of interfaces in java?
1) All the methods defined in interfaces are implicitly abstract even though abstract modifier is not
declared.
2) All the methods in interface are public whether they are declared as public or not.
3) variables declared inside interface are by default public, static and final.
4) Interfaces cannot be instantiated.
5) we cannot declare static methods inside interface.
6) ‘ implements’ keyword is used to implement interface.
7) Unlike class, interface can extend any number of interfaces.
8) We can define a class inside interface and the class acts like inner class to interface.
9) An interface can extend a class and implement an interface
10) Multiple inheritance in java is achieved through interfaces.

### Explain enumeration in java?
Enumeration is a new feature from Java 5.0. Enumeration is set of named constants . We use enum
keyword to declare enumeration. The values defined in enumeration are enum constants.Each enum
constant declared inside a enum class is by default public , static and final.
Example :
package javaexamples;
public enum Days {
SUN,MON,TUE,WED,THU,FRI,SAT;
}
SUN,MON,TUE,WED,THU,FRI,SAT are enum constants.

### Explain restrictions on using enum?
1) Enums cannot extend any other class or enum.
2) We cannot instantiate an enum.
3) We can declare fields and methods in enum class. But these fields and methods should follow the
enum constants otherwise we get compilation error.

### Explain about field hiding in java?
If superclass and subclass have same fields subclass cannot override superclass fields. In this case
subclass fields hides the super class fields. If we want to use super class variables in subclass we use
super keyword to access super class variables.

### Explain about Varargs in java?
Beginning with Java 5 has a new feature Varargs which allows methods to have variable number of
arguments. It simplifies creation of methods when there are more number of arguments. Earlier to java 5
Varargs are handled by creating method with array of arguments.
Ex : public static void main(String[] args)
A variable length argument is specified using ellispses with type in signature. main method with var args
is written as follows:
public static void main(String … args)
If no arguments are passes we get array with size 0.There is no need for null check if no arguments are
passed.
35

### Explain where variables are created in memory?
When we declare variables variables are created in stack. So when the variable is out of scope those
variables get garbage collected.

### Can we use Switch statement with Strings?
Prior to Java 7 we can use only int values and enum constants in Switch .Starting with Java 7 we can use
strings in Switch statement. If we use strings in switch statement prior to Java 7 we will get compile time
error “only int and enum constants are permitted”.

### In java how do we copy objects?
In Java we cannot copy two objects but by assigning one reference to other we can copy objects. For
example if we have a reference r1 that point to object .so when we declare r2=r1, we are assigning
reference r1 to r2 so now r2 points to the same object where r1 points. Any changes done by one
reference on an object will reflect to other.