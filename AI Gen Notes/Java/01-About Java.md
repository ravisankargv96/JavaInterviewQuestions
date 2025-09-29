### 1. what are static blocks and static initializers in Java ? 
Static blocks or static initializers are used to initialize static fields in java. we declare static blocks when we want to initialize static fields in our class. Static blocks gets executed exactly once when the class is loaded . Static blocks are executed even before the constructors are executed. 

```java
```
### 2. How to call one constructor from the other constructor ? 
With in the same class if we want to call one constructor from other we use this() method. Based on the number of parameters we pass appropriate this() method is called. 
Restrictions for using this method : 
	1) this must be the first statement in the constructor 
	2) we cannot use two this() methods in the constructor 

```java
```
### 3. What is method overriding in java? 
If we have methods with same signature (same name, same signature, same return type) in super class and subclass then we say  subclass method is overridden by superclass. 
**When to use overriding in java** 
If we want same method with different behavior in superclass and subclass then we go for overriding. When we call overridden method with subclass reference subclass method is called hiding the superclass method.

```java

```

### 4. What is super keyword in java ? 
Variables and methods of super class can be overridden in subclass . In case of overriding, a subclass object call its own variables and methods. Subclass cannot access the variables and methods of superclass because the overridden variables or methods hides the methods and variables of super class. 
But still java provides a way to access super class members even if its members are overridden. Super is used to access superclass variables, methods, constructors. 
Super can be used in two forms : 
	1) First form is for calling super class constructor. 
	2) Second one is to call super class variables, methods. 
Super if present must be the first statement.

```java

```
### 5. Difference between method overloading and method overriding in java ?

| SNo | Method Overloading                                                 | Method Overriding                                                                       |
| --- | ------------------------------------------------------------------ | --------------------------------------------------------------------------------------- |
| 1   | Method Overloading occurs with in the same class                   | Method Overriding occurs between two classes superclass and subclass                    |
| 2   | Since it involves with only one class inheritance is not involved. | Since method overriding occurs between superclass and subclass inheritance is involved. |
| 3   | In overloading return type need not be the same                    | In overriding return type must be same.                                                 |
| 4   | Parameters must be different when we do overloading                | Parameters must be same.                                                                |
| 5   | Static polymorphism can be acheived using method overloading       | Dynamic polymorphism can be acheived using method overriding.                           |
| 6   | In overloading one method can’t hide the another                   | In overriding subclass method hides that of the superclass method.                      |
```java

```
 
### 6. Difference between abstract class and interface ? 

|     | Interface                                                   | Abstract Class                                                                 |
| --- | ----------------------------------------------------------- | ------------------------------------------------------------------------------ |
| 1   | Interface contains only abstract methods                    | Abstract class can contain abstract methods, concrete methods or both          |
| 2   | Access Specifiers for methods in interface must be public   | Except private we can have any access specifier for methods in abstract class. |
| 3   | Variables defined must be public , static , final           | Except private variables can have any access specifiers                        |
| 4   | Multiple Inheritance in java is implemented using interface | We cannot achieve multiple inheritance using abstract class.                   |
| 5   | To implement an interface we use implements keyword         | To implement an interface we use implements keyword                            |
```java

```
### 7. Why java is platform independent? 
The most unique feature of java is platform independent. In any programming language source code is compiled in to executable code. This cannot be run across all platforms. When javac compiles a java program it generates an executable file called .class file. 
class file contains byte codes. Byte codes are interpreted only by JVM’s . Since these JVM’s are made available across all platforms by Sun Microsystems, we can execute this byte code in any platform. Byte code generated in windows environment can also be executed in linux environment. This makes java platform independent. 

### 8. What is method overloading in java ? 
A class having two or more methods with same name but with different arguments then we say that those methods are overloaded. Static polymorphism is achieved in java using method overloading. 
Method overloading is used when we want the methods to perform similar tasks but with different inputs or values. When an overloaded method is invoked java first checks the method name, and the number of arguments ,type of arguments; based on this compiler executes this method. 
Compiler decides which method to call at compile time. By using overloading static polymorphism or static binding can be achieved in java. 
Note : Return type is not part of method signature. we may have methods with different return types but return type alone is not sufficient to call a method in java.

```java
```

### 9. What is difference between C++ and Java ? 

|     | Java                                     | C++                               |
| --- | ---------------------------------------- | --------------------------------- |
| 1   | Java is platform independent             | C++ is platform dependent.        |
| 2   | There are no pointers in java            | There are pointers in C++.        |
| 3   | There is no operator overloading in java | C ++ has operator overloading.    |
| 4   | There is garbage collection in java      | There is no garbage collection    |
| 5   | Supports multithreading                  | Doesn't support multithreading    |
| 6   | There are no templates in java           | There are templates in c++        |
| 7   | There are no global variables in java    | There are global variables in c++ |
```java
// garbage collection

// support multithreading
```
### 10. What is JIT compiler? 
JIT compiler stands for Just in time compiler. JIT compiler compiles byte code in to executable code . JIT a part of JVM .JIT cannot convert complete java program in to executable code it converts as and when it is needed during execution. 
```java
// display mermaid.js flow diagram
```

### 11. What is bytecode in java? 
When a javac compiler compiler compiles a class it generates .class file. This .class file contains set of instructions called byte code. Byte code is a machine independent language and contains set of instructions which are to be executed only by JVM. JVM can understand this byte codes

```java
// display mermaid.js flow diagram.
```
### 12. Difference between this() and super() in java ? 
this() is used to access one constructor from another with in the same class while super() is used to access superclass constructor. Either this() or super() exists it must be the first statement in the constructor.

```java
// eg. of using this() keyword

// eg. of using super() keyword
```
### 13. What is a class ? 
Classes are fundamental or basic unit in Object Oriented Programming .A class is kind of blueprint or template for objects. 
Class defines variables, methods. A class tells what type of objects we are creating. 
For example take Department class tells us we can create department type objects. We can create any number of department objects. 

All programming constructs in java reside in class. When JVM starts running it first looks for the class when we compile. Every Java application must have atleast one class and one main method. 
Class starts with class keyword. A class definition must be saved in class file that has same as class name. 
File name must end with .java extension. 

```java
// file: FirstClass.java
public class FirstClass {
	public static void main(String[] args) {
		System.out.println(“My First class”); 
	} 
}
```

If we see the above class when we compile JVM loads the FirstClass and generates a .class file (`FirstClass.class`). When we run the program we are running the class and then executes the main method.  
### 14. What is an object ? 
An Object is instance of class. A class defines type of object. Each object belongs to some class. Every object contains state and behavior. State is determined by value of attributes and behavior is called method. Objects are also called as an instance. 
To instantiate the class we declare with the class type.

```java
public class FirstClass { 
	public static voidmain(String[] args) { 
		FirstClass f = new FirstClass(); 
		System.out.println(“My First class”); 
	}
}
```

To instantiate the FirstClass we use this statement 
```java
FirstClass f = new FirstClass();
```

f is used to refer FirstClass object. 

### 15. What is method in java ? 
It contains the executable body that can be applied to the specific object of the class. 
Method includes method name, parameters or arguments and return type and a body of executable code. 

```java
returnType methodName(Argument List){
}
```

```java
public float add(int a, int b, int c) //eg: method
```

methods can have multiple arguments. Separate with commas when we have multiple arguments. 
### 16. What is encapsulation ? 

The process of wrapping or putting up of data in to a single unit class and keeps data safe from misuse is called encapsulation .

Through encapsulation we can hide and protect the data stored in java objects. Java supports encapsulation through access control. There are four access control modifiers in java `public`, `private`, `protected` and `default` level. 

For example take a car class , In car we have many parts which is not required for driver to know what all it consists inside. He is required to know only about how to start and stop the car. So we can expose what all are required and hide the rest by using encapsulation. 
### 17. Why main() method is public, static and void in java ?
**public :** `public` is an access specifier which can be used outside the class. When main method is declared public it means it can be used outside class. 
**static :** To call a method we require object. Sometimes it may be required to call a method without the help of object. Then we declare that method as static. JVM calls the main() method without creating object by declaring keyword static. 
**void :** void return type is used when a method doesn't return any value. main() method doesn't return any value, so main() is declared as void. 

Signature : 

```java
public static void main(String[] args) {}
``` 

### 18. Explain about main() method in java ? 
Main() method is starting point of execution for all java applications. public static void main(String[] args) {} String args[] are array of string objects we need to pass from command line arguments. Every Java application must have atleast one main method. 

### 19. What is constructor in java ? 
A constructor is a special method used to initialize objects in java. we use constructors to initialize all variables in the class when an object is created. As and when an object is created it is initialized automatically with the help of constructor in java. We have two types of constructors Default Constructor Parameterized Constructor Signature : public classname() { } Signature : 
```java
public classname(parameters list) { }
```

### 20. What is difference between length and length() method in java ? 
**length():** In String class we have length() method which is used to return the number of characters in string. 

```java
String str = “Hello World”; //eg.
System.out.println(str.length()); 
Str.length() // will return 11 characters including space. 
```

**length :** we have length instance variable in arrays which will return the number of values or objects in array. For example :

```java
String days[]={” Sun”,”Mon”,”wed”,”thu”,”fri”,”sat”};
```

Will return 6 since the number of values in days array is 6. 
### 21) What is ASCII Code? 
ASCII stands for American Standard code for Information Interchange. ASCII character range is 0 to 255. We can’t add more characters to the ASCII Character set. ASCII character set supports only English. That 13 is the reason, if we see C language we can write c language only in English we can’t write in other languages because it uses ASCII code. 

### 22) What is Unicode ? 
Unicode is a character set developed by Unicode Consortium. To support all languages in the world Java supports Unicode values. Unicode characters were represented by 16 bits and its character range is 0- 65,535. 
Java uses ASCII code for all input elements except for `Strings`, `identifiers`, and `comments`. If we want to use telugu we can use telugu characters for identifiers. We can enter comments in telugu. 

### 23) Difference between Character Constant and String Constant in java ? 
Character constant is enclosed in single quotes. String constants are enclosed in double quotes. Character constants are single digit or character. String Constants are collection of characters. 

```java
’2’, ‘A’ // eg. Character constant 
“Hello World” // eg. String constant
```

### 24) What are constants and how to create constants in java? 
Constants are fixed values whose values cannot be changed during the execution of program. We create constants in java using `final` keyword. 

```java
final int NUMBER =10; //eg: 
final String STR = ”java-interview –questions” 
```

### 25) Difference between `>>` and `>>>` operators in java? 
`>>` is a right shift operator shifts all of the bits in a value to the right to a specified number of times. 
```java
int a =15; 
a = a >> 3; // a = 15/(2^3)
```
The above line of code moves 15 three characters right. 

`>>>` is an unsigned shift operator used to shift right. The places which were vacated by shift are filled with zeroes. 

```java
int a =15; 
a = a >>> 3; // check in a java compiler
```

 
  
 
 
 
