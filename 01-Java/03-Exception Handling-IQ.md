### 1) What is an exception in java? 
In java exception is an object. Exceptions are created when an abnormal situations are arised in our program. Exceptions can be created by JVM or by our application code. All Exception classes are defined in java.lang. In otherwords we can say Exception as run time error. 

### 2) State some situations where exceptions may arise in java? 
1 Accesing an element that does not exist in array. 
2 Invalid conversion of number to string and string to number. (NumberFormatException) 
3 Invalid casting of class (Class cast Exception) 
4 Trying to create object for interface or abstract class (Instantiation Exception) 

### 3) What is Exception handling in java? 
Exception handling is a mechanism what to do when some abnormal situation arises in program. When an exception is raised in program it leads to termination of program when it is not handled properly. The significance of exception handling comes here in order not to terminate a program abruptly and to continue with the rest of program normally. This can be done with help of Exception handling. 

### 4) What is an error in Java? 
Error is the subclass of Throwable class in java. When errors are caused by our program we call that as Exception, but some times exceptions are caused due to some environment issues such as running out of memory. In such cases we can’t handle the exceptions. Exceptions which cannot be recovered are called as errors in java. 
Ex : Out of memory issues. 

### 5) What are advantages of Exception handling in java? 
1) Separating normal code from exception handling code to avoid abnormal termination of program. 
2) Categorizing in to different types of Exceptions so that rather than handling all exceptions with Exception root class we can handle with specific exceptions. It is recommended to handle exceptions with specific Exception instead of handling with Exception root class. 
3) Call stack mechanism : If a method throws an exception and it is not handled immediately, then that exception is propagated or thrown to the caller of that method. This propogation continues till it finds an appropriate exception handler, if it finds handler it would be handled otherwise program terminates abruptly. 

### 6) In how many ways we can do exception handling in java? 
We can handle exceptions in either of the two ways : 
	1) By specifying try catch block where we can catch the exception. 
	2) Declaring a method with throws clause. 

### 7) List out five keywords related to Exception handling ? 
1) Try
2) Catch 
3) throw 
4) throws 
5) finally  

### 8) Explain try and catch keywords in java? 
In try block we define all exception causing code. In java try and catch forms a unit. A catch block catches the exception thrown by preceding try block. Catch block cannot catch an exception thrown by another try block. If there is no exception causing code in our program or exception is not raised in our code jvm ignores the try catch block. 
Syntax : 
```java
try {
	// code block
} Catch(Exception e) {
}
``` 

### 9) Can we have try block without catch block? 
Each try block requires atleast one catch block or finally block. A try block without catch or finally will result in compiler error. We can skip either of catch or finally block but not both. 

### 10) Can we have multiple catch block for a try block? 
In some cases our code may throw more than one exception. In such case we can specify two or more catch clauses, each catch handling different type of exception. When an exception is thrown jvm checks each catch statement in order and the first one which matches the type of exception is execution and remaining catch blocks are skipped. Try with multiple catch blocks is highly recommended in java. If try with multiple catch blocks are present the order of catch blocks is very important and the order should be from child to parent. 

### 11) Explain importance of finally block in java? 
Finally block is used for cleaning up of resources such as closing connections, sockets etc. if try block executes with no exceptions then finally is called after try block without executing catch block. If there is exception thrown in try block finally block executes immediately after catch block. If an exception is thrown, finally block will be executed even if the no catch block handles the exception. 

### 12) Can we have any code between try and catch blocks? 
We shouldn’t declare any code between try and catch block. Catch block should immediately start after try block. 
```java
try{ 
	//code 
} 
System.out.println(“one line of code”); // illegal 
catch(Exception e){ // 
} 
```

### 13) Can we have any code between try and finally blocks? 
We shouldn’t declare any code between try and finally block. finally block should immediately start after catch block.If there is no catch block it should immediately start after try block. 
```java
try{ 
	//code 
} 
System.out.println(“one line of code”); // illegal 
finally{ 
	// code 
}
``` 

### 14) Can we catch more than one exception in single catch block? 
From Java 7, we can catch more than one exception with single catch block. This type of handling reduces the code duplication. 
Note : When we catch more than one exception in single catch block , catch parameter is implicity final. We cannot assign any value to catch parameter. 
Ex : 
```java
catch(ArrayIndexOutOfBoundsException || ArithmeticException e) { 
	// 20 
}
```

In the above example e is final we cannot assign any value or modify e in catch statement. 

### 15) What are checked Exceptions? 
1) All the subclasses of Throwable class except error, Runtime Exception and its subclasses are checked exceptions. 
2) Checked exception should be thrown with keyword throws or should be provided try catch block, else the program would not compile. We do get compilation error. 
	Examples : 
	1) IOException, 
	2) SQlException, 
	3) FileNotFoundException, 
	4) InvocationTargetException, 
	5) CloneNotSupportedException 
	6) ClassNotFoundException 
	7) InstantiationException 


### 16) What are unchecked exceptions in java? 
All subclasses of RuntimeException are called unchecked exceptions. These are unchecked exceptions because compiler does not checks if a method handles or throws exceptions. Program compiles even if we do not catch the exception or throws the exception. If an exception occurs in the program,program terminates . It is difficult to handle these exceptions because there may be many places causing exceptions. 
Example : 
	1) Arithmetic Exception 
	2) ArrayIndexOutOfBoundsException 
	3) ClassCastException 
	4) IndexOutOfBoundException 
	5) NullPointerException 
	6) NumberFormatException 
	7) StringIndexOutOfBounds 
	8) UnsupportedOperationException 


### 17) Explain differences between checked and Unchecked exceptions in java?

| S.No | Unchecked Exception                                                          | Checked Exception                                                                          |
| ---- | ---------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| 1    | All the subclasses of RuntimeException are called unchecked exception.       | All subclasses of Throwable class except RuntimeException are called as checked exceptions |
| 2    | Unchecked exceptions need not be handled at compile time                     | Checked Exceptions need to be handled at compile time.                                     |
| 3    | These exceptions arise mostly due to coding mistakes in our program.         |                                                                                            |
| 4    | ArrayIndexOutOfBoundsException, ClassCastException, IndexOutOfBoundException | SqlException, FileNotFoundException, ClassNotFoundException                                |

### 18) What is default Exception handling in java? 
When JVM detects exception causing code, it constructs a new exception handling object by including the following information. 
	1) Name of Exception 
	2) Description about the Exception 
	3) Location of Exception. After creation of object by JVM it checks whether there is exception handling code or not. If there is exception handling code then exception handles and continues the program. If there is no exception handling code JVM give the responsibility of exception handling to default handler and terminates abruptly.
Default Exception handler displays description of exception, prints the stacktrace and location of exception and terminates the program. 
**Note:** The main disadvantage of this default exception handling is program terminates abruptly. 

### 19) Explain throw keyword in java? 
Generally JVM throws the exception and we handle the exceptions by using try catch block. But there are situations where we have to throw user-defined exceptions or runtime exceptions. In such case we use throw keyword to throw exception explicitly. 
Syntax : 
```java
throw throwableInstance;
```

Throwable instance must be of type throwable or any of its subclasses. 
After the throw statement execution stops and subsequent statements are not executed. Once exception object is thrown JVM checks is there any catch block to handle the exception. If not then the next catch statement till it finds the appropriate handler. If appropriate handler is not found, then default exception handler halts the program and prints the description and location of exception. 
In general we use throw keyword for throwing user-defined or customized exception. 

### 20) Can we write any code after throw statement? 
After throw statement jvm stop execution and subsequent statements are not executed. If we try to write any statement after throw we do get compile time error saying unreachable code. 

### 21) Explain importance of throws keyword in java? 
Throws statement is used at the end of method signature to indicate that an exception of a given type may be thrown from the method. 
The main purpose of throws keyword is to delegate responsibility of exception handling to the caller methods, in the case of checked exception. 
In the case of unchecked exceptions, it is not required to use throws keyword. 
We can use throws keyword only for throwable types otherwise compile time error saying incompatible types. 
An error is unchecked, it is not required to handle by try catch or by throws. 
Syntax : 
```java
Class Test { 
	Public static void main(String args[]) throws IE { 
	} 
}
``` 

**Note :** The method should throw only checked exceptions and subclasses of checked exceptions. It is not recommended to specify exception superclasses in the throws class when the actual exceptions thrown in the method are instances of their subclass. 

### 22) Explain the importance of finally over return statement? 
`finally` block is more important than return statement when both are present in a program. For example if there is any return statement present inside try or catch block, and finally block is also present first finally statement will be executed and then return statement will be considered. 

### 23) Explain a situation where finally block will not be executed? 
Finally block will not be executed whenever jvm shutdowns. If we use system.exit(0) in try statement finally block if present will not be executed. 

### 24) Can we use catch statement for checked exceptions? 
If there is no chance of raising an exception in our code then we can’t declare catch block for handling checked exceptions .This raises compile time error if we try to handle checked exceptions when there is no possibility of causing exception. 

### 25) What are user defined exceptions? 
To create customized error messages we use user-defined exceptions. We can create user defined exceptions as checked or unchecked exceptions. We can create user defined exceptions that extend Exception class or subclasses of checked exceptions so that user-defined exception becomes checked. User-defined exceptions can extend RuntimeException to create user-defined unchecked exceptions. Note : It is recommended to keep our customized exception class as unchecked, i.e we need to extend Runtime Exception class but not Exception class. 

### 26) Can we rethrow the same exception from catch handler?
Yes we can rethrow the same exception from our catch handler. If we want to rethrow checked exception from a catch block we need to declare that exception. 

### 27) Can we nested try statements in java? 
Yes try statements can be nested. We can declare try statements inside the block of another try statement. 

### 28) Explain the importance of throwable class and its methods? 
Throwable class is the root class for Exceptions. All exceptions are derived from this throwable class. The two main subclasses of Throwable are Exception and Error. The three methods defined in throwable class are : 
	1) **void printStackTrace()**: This prints the exception information in the following format : Name of the exception, description followed by stack trace. 
	2) **getMessage()**: This method prints only the description of Exception. 
	3) **toString()**: It prints the name and description of Exception. 


### 29) Explain when ClassNotFoundException will be raised? 
When JVM tries to load a class by its string name, and couldn’t able to find the class classNotFoundException will be thrown. An example for this exception is when class name is misspelled and when we try to load the class by string name hence class cannot be found which raises ClassNotFoundException. 


### 30) Explain when NoClassDefFoundError will be raised ? 
This error is thrown when JVM tries to load the class but no definition for that class is found NoClassDefFoundError will occur. The class may exist at compile time but unable to find at runtime. This might be due to misspelled classname at command line, or classpath is not specified properly , or the class file with byte code is no longer available.