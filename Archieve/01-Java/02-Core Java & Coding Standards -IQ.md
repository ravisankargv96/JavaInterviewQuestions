### 1) Explain Java Coding Standards for classes or Java coding conventions for classes? 
Sun has created Java Coding standards or Java Coding Conventions . It is recommended highly to follow java coding standards. 
Classnames should start with uppercase letter (**TitleCase**). Classnames names should be nouns. 
If Class name is of multiple words then the first letter of inner word must be capital letter. Ex : Employee, EmployeeDetails, ArrayList, TreeSet, HashSet

### 2) Explain Java Coding standards for interfaces? 
Interface should start with uppercase letters (**TitleCase**). Names should be adjectives 
Example : Runnable, Serializable, Marker, Cloneable

### 3) Explain Java Coding standards for Methods?
Method names should start with small letters(**camelCase**). Method names are usually verbs. 
If method contains multiple words, every inner word should start with uppercase letter. 
Ex : toString() 
Method name must be combination of verb and noun 
Ex : getCarName(),getCarNumber()

### 4) Explain Java Coding Standards for variables ?
Variable names should start with small letters (**camelCase**). Variable names should be nouns 
Short meaningful names are recommended. If there are multiple words every inner word should start with Uppecase character. 
Ex : string,value,empName,empSalary


### 5) Explain Java Coding Standards for Constants? 
Constants in java are created using static and final keywords. 
Constants contains only uppercase letters. If constant name is combination of two words it should be separated by underscore (**SNAKE_CASE**). Constant names are usually nouns. 
Ex: MAX_VALUE, MIN_VALUE, MAX_PRIORITY, MIN_PRIORITY


### 6) Difference between overriding and overloading in java? 

| SNo. | Overriding                                                                                                    | Overloading                                                                                   |
| ---- | ------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| 1    | In overriding method names must be same                                                                       | In overloading method names must be same                                                      |
| 2    | Argument List must be same                                                                                    | Argument list must be different atleast order of arguments.                                   |
| 3    | Return type can be same or we can return covariant type. From 1.5 covariant types are allowed                 | Return type can be different in overloading.                                                  |
| 4    | We cant increase the level of checked exceptions. No restrictions for unchecked exceptions                    | In overloading different exceptions can be thrown.                                            |
| 5    | A method can only be overridden in subclass                                                                   | A method can be overloaded in same class or subclass                                          |
| 6    | Private,static and final variables cannot be overridden.                                                      | Private , static and final variables can be overloaded.                                       |
| 7    | In overriding which method is called is decided at runtime based on the type of object referenced at run time | In overloading which method to call is decided at compile time based on reference type.       |
| 8    | Overriding is also known as Runtime polymorphism, dynamic polymorphism or late binding                        | Overloading is also known as Compile time polymorphism, static polymorphism or early binding. |
   
### 6) What is `IS-A` relationship in java? 
`is-a` relationship is also known as inheritance. We can implement `is-a` relationship or inheritance in java using extends keyword. The advantage or inheritance or is a relationship is reusability of code instead of duplicating the code. 
Ex : Motor cycle is a vehicle 
Car is a vehicle Both car and motorcycle extends vehicle. 

### 7) What is `HAS-A` relationship in java? 

`Has a` relationship is also known as **composition** or **Aggregation**. As in inheritance we have `extends` keyword we don’t have any keyword to implement `Has a` relationship in java. The main advantage of `Has-A` relationship in java code reusability.
### 8) Difference between ‘IS-A’ and ‘HAS-A’ relationship in java?

| SNo. | `Is-A` relationship                                      | `Has-a` relationship                                                      |
| ---- | -------------------------------------------------------- | ------------------------------------------------------------------------- |
| 1    | Is a relationship also known as inheritance              | `Has-a` relationship is also known as **composition** or **Aggregation**. |
| 2    | For `Is-A` relationship we uses extends keyword          | For Has a relationship we use new keyword                                 |
| 3    | Ex : Car is a vehicle.                                   | Ex : Car has an engine. We cannot say Car is an engine                    |
| 4    | The main advantage of inheritance is reusability of code | The main advantage of has a relationship is reusability of code           |
 
### 9) Explain about instanceof operator in java? 
Instanceof operator is used to test the object is of which type. 
Syntax : `<reference expression>` instanceof `<destination type>` 
Instanceof returns true if reference expression is subtype of destination type. 
Instanceof returns false if reference expression is null. 

**Example:** 
```java
public classInstanceOfExample {
	public static voidmain(String[] args) { 
		Integer a = newInteger(5);
		if (a instanceof java.lang.Integer) { 
			System.out.println(true); 
		} else { 
			System.out.println(false);
		}
	}
 } 
```

Since a is integer object it returns true. 
There will be a compile time check whether reference expression is subtype of destination type. If it is not a subtype then compile time error will be shown as Incompatible types

### 10) What does null mean in java? 
When a reference variable doesn’t point to any value it is assigned null. 
Example : Employee employee; 
In the above example employee object is not instantiate so it is pointed no where 

### 11) Can we have multiple classes in single file ? 
Yes we can have multiple classes in single file but it people rarely do that and not recommended. We can have multiple classes in File but only one class can be made public. If we try to make two classes in File public we get following compilation error. 
**The public type must be defined in its own file** 

### 12) What all access modifiers are allowed for top class ? 
For top level class only two access modifiers are allowed. public and default. If a class is declared as public it is visible everywhere. 
If a class is declared default it is visible only in same package. 
If we try to give private and protected as access modifier to class we get the below compilation error. 
**Illegal Modifier for the class only public, abstract and final are permitted.**

### 13) What are packages in java? 
Package is a mechanism to group related classes, interfaces and enums in to a single module. 
Package can be declared using the following statement : 
Syntax : `package <package-name>` 
Coding Convention : package name should be declared in small letters. 
package statement defines the namespace. 
The main use of package is 
	1. To resolve naming conflicts 
	2. For visibility control : We can define classes and interfaces that are not accessible outside the class. 

### 14) Can we have more than one package statement in source file? 
We can’t have more than one package statement in source file. In any java program there can be atmost only 1 package statement. We will get compilation error if we have more than one package statement in source file. 

### 15) Can we define package statement after import statement in java? 
We can’t define package statement after import statement in java. package statement must be the first statement in source file. We can have comments before the package statement. 

### 16) What are identifiers in java? 
Identifiers are names in java program. Identifiers can be class name, method name or variable name. 
**Rules for defining identifiers in java:** 
	1. Identifiers must start with letter, Underscore or dollar($) sign. 
	2. Identifiers can’t start with numbers. 
	3. There is no limit on number of characters in identifier but not recommended to have more than 15 characters 
	4. Java identifiers are case sensitive. 
	5. First letter can only be alphabet, or underscore and dollar sign. From second letter we can have numbers. 
	6. We shouldn't use reserve words for identifiers in java. 

### 17) What are access modifiers in java? 
The important feature of encapsulation is access control. By preventing access control we can misuse of class, methods and members
A class, method or variable can be accessed is determined by the access modifier. There are three types of access modifiers in java. **public**, **private**, **protected**. If no access modifier is specified then it has a default access.


### 18) What is the difference between access specifiers and access modifiers in java? 
In C++ we have access specifiers as public, private, protected and default and access modifiers as static, final. But there is no such divison of access specifiers and access modifiers in java. In Java we have access modifiers and non access modifiers. 
Access Modifiers : public, private, protected, default 
Non Access Modifiers : abstract, final, stricfp. 

### 19) What access modifiers can be used for class? 
We can use only two access modifiers for class public and default. 

**public:** A class with public modifier can be visible 
	1) In the same class 
	2) In the same package subclass 
	3) In the same package nonsubclass 
	4) In the different package subclass 
	5) In the different package non subclass. 

**default:** A class with default modifier can be accesed 
	1) In the same class 
	2) In the same package subclass 
	3) In the same package nonsubclass 
	4) In the different package subclass 
	5) In the different package non subclass. 

### 20) Explain what access modifiers can be used for methods?
We can use all access modifiers public, private, protected and default for methods. 
**public :** When a method is declared as public it can be accessed 
	1) In the same class 
	2) In the same package subclass 
	3) In the same package nonsubclass 
	4) In the different package subclass 
	5) In the different package non subclass. 

**default :** When a method is declared as default, we can access that method in 
	1) In the same class 
	2) In the same package subclass 
	3) In the same package non subclass 
We cannot access default access method in 
	1) Different package subclass 
	2) Different package non subclass. 

**protected :** When a method is declared as protected it can be accessed 
	1) With in the same class 
	2) With in the same package subclass 
	3) With in the same package non subclass 
	4) With in different package subclass 
It cannot be accessed non subclass in different package. 

**private :** When a method is declared as private it can be accessed only in that class. 
It cannot be accessed in 
	1) Same package subclass 
	2) Same package non subclass 
	3) Different package subclass 
	4) Different package non subclass. 

### 21) Explain what access modifiers can be used for variables?
We can use all access modifiers public, private, protected and default for variables. 
**public :** When a variables is declared as public it can be accessed 
	1) In the same class 
	2) In the same package subclass 
	3) In the same package nonsubclass 
	4) In the different package subclass 
	5) In the different package non subclass. 

**default :** When a variables is declared as default, we can access that method in 
	1) In the same class 
	2) In the same package subclass 
	3) In the same package non subclass 
We cannot access default access variables in 
	1) Different package subclass 
	2) Different package non subclass. 

**protected :** When a variables is declared as protected it can be accessed 
	1) With in the same class 
	2) With in the same package subclass 
	3) With in the same package non subclass 
	4) With in different package subclass 
It cannot be accessed non subclass in different package. 

**private :** When a variables is declared as private it can be accessed only in that class. 
It cannot be accessed in 
	1) Same package subclass 
	2) Same package non subclass 
	3) Different package subclass 
	4) Different package non subclass. 

### 22) What is final access modifier in java? 
final access modifier can be used for class, method and variables. The main advantage of final access modifier is security no one can modify our classes, variables and methods. The main disadvantage of final access modifier is we cannot implement oops concepts in java. 
Ex : Inheritance, polymorphism. 
**`final` class :** A final class cannot be extended or subclassed. We are preventing inheritance by marking a class as final. But we can still access the methods of this class by composition. 
Ex: String class 
**`final` methods:** Method overriding is one of the important features in java. But there are situations where we may not want to use this feature. Then we declared method as final which will print overriding. To allow a method from being overridden we use final access modifier for methods. 
**`final` variables:** If a variable is declared as final, it behaves like a constant. We cannot modify the value of final variable. Any attempt to modify the final variable results in compilation error. The error is as follows **final variable cannot be assigned.** 

### 23) Explain about abstract classes in java? 
Sometimes we may come across a situation where we cannot provide implementation to all the methods in a class. We want to leave the implementation to a class that extends it. In such case we declare a class as abstract. To make a class abstract we use key word abstract. Any class that contains one or more abstract methods is declared as abstract. If we don’t declare class as abstract which contains abstract methods we get compile time error. We get the following error. 
“*The type `<class-name>` must be an abstract class to define abstract methods.*” 

Signature: 
```java
abstract class <class-name> {
}
``` 

For example if we take a vehicle class we cannot provide implementation to it because there may be two wheelers , four wheelers etc. At that moment we make vehicle class abstract. All the common features of vehicles are declared as abstract methods in vehicle class. Any class which extends vehicle will provide its method implementation. It’s the responsibility of subclass to provide implementation. 
The important features of abstract classes are : 
1) Abstract classes cannot be instantiated. 
2) An abstract classes contains abstract methods, concrete methods or both. 
3) Any class which extends abstract class must override all methods of abstract class. 
4) An abstract class can contain either 0 or more abstract methods. 
Though we cannot instantiate abstract classes we can create object references. Through superclass references we can point to subclass. 

### 24) Can we create constructor in abstract class? 
We can create constructor in abstract class, it doesn't give any compilation error. But when we cannot instantiate class there is no use in creating a constructor for abstract class. 

### 25) What are abstract methods in java?
An abstract method is the method which doesn't have any body. Abstract method is declared with keyword abstract and semicolon in place of method body. 
**Signature:** 
```java
public abstract void <method name>();
```
**Ex:**
```java
public abstract void getDetails();
```

It is the responsibility of subclass to provide implementation to abstract method defined in abstract class.


