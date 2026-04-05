Oops concepts interview questions

### Explain about procedural programming language or structured programming
language and its features?
In traditional programming language to solve a problem we use set of procedures. Once the procedures or
functions are determined next they concentrate on storing data.
Features :
2) In this top down approach is followed. First procedures were determined and then concentrate on
minute details.
3) Concentrate more on functions and procedure rather than data.
4) In traditional programming language procedures manipulate global data without knowing to other
procedures.
5) Very little concentration on minute details
The main drawback of traditional programming languages works well only for small problems. But not
suitable for larger problems.
Ex : C language, Pascal

### Explain about object oriented programming and its features?
Java replaced traditional programming language developed in 1970’s. In Object oriented programming
everything is made up of object. In this language bottom up approach is followed. Each
object communicates with other as opposed to traditional view.
Features :
7) In this bottom approach is followed. First concentrates on minute details like creating objects then
concentrates on implementation or solving the problem.
8) Concentrate more on data and give less importance for implementation.
9) Objects communicate with each other
The main advantage of object oriented programming language is works well for larger problems.

### List out benefits of object oriented programming language?
1) Easy maintenance
2) Code reusability
3) Code extendability
4) Reliable

### Differences between traditional programming language and object oriented programming
language?
Traditional Programming language Object Oriented Programming Language
A program is divided in to modules and procedures. A program is divided in to number of objects.
Implementation is done through procedures. Implementation is done through interfaces.
In traditional programming there is no
encapsulation all procedures access data.
In oops encapsulation is done by tightly coupling
data and behaviour together in class.
Suitable for small programs or problems Suitable for large programs and complex problems.

### Explain oops concepts in detail?
Object oriented programming should support these three features :
7) Inheritance
8) Encapsulation
36
9) Polymorphism

### Explain what is encapsulation?
Encapsulation is the process of wrapping of code and behaviour in a single unit called class and preventing
from misuse is called encapsulation. Encapsulation exposes only part of object which are safe to exposed
and remaining part of object is kept secured.
Encapsulation is supported through access control in java. There are four types of access control
specifiers(public,private, protected, default) in java which supports encapsulation.
For example tv manufacturers exposes only buttons not all the thousands of electronic components which
it is made up of.

### What is inheritance ?
Inheritance is one of the important feature of object oriented language. Inheriting is the process of
acquiring features of others. For example a child acquires the features of their parents.
In java inheritance is the process of inheriting member of existing classes by extending their functionality.
The original class is called base class, parent class or super class. The new class derived from parent is
called child class, sub class, and derived class.
We use extends keyword in java to extend a class in java. All java classes extend java.lang.Object since
object class is the super class for all classes in java.
When we create a new class by using inheritance ‘is-a’ relationship is formed.

### Explain importance of inheritance in java?
Reusability :The major advantage of inheritance is code reuse. We can avoid duplicating code by using
inheritance. We can place all common state and behaviour in that class , by extending that class we can
Extendability : We can add new functionality to our application without touching the existing code.
For example if we take Ms word we came across number of versions of msword such as word 2003,2007.
Everytime they won’t write new code they reuse the existing code and some more features.

### What is polymorphism in java?
Polymorphism is combination of two greek words which mean many forms. In polymorphism actual type
of object involved in method call determines which method to call rather type of reference variable.
14) What is covariant return ?
In java 1.4 and earlier one method can override super class method if both methods have same signature
and return types.
From Java 1.5 , a method can override other method if argument types match exactly though return
types are different.(Return type must be subtype of other method).
Example : Class A
{
A doSomeThing()
{
return new A();
}
}
Example : Class B
{
B doSomeThing()
{
return new B();
}
}
From java 1.5 return type for doSomeThing() in Class B is valid . We get compile time error in 1.4 and
earlier.