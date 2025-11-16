### What is serialization in java?
Serialization is the process of converting an object in to bytes, so that it can be transmitted over the
network,or stored in a flat file and can be recreated later. Serialized object is an object represented as
sequence of bytes that includes objects data, object type, and the types of data stored in the object.

### What is the main purpose of serialization in java?
The main uses of serialization are :
3) Persistence:
We can write data to a file or database and can be used later by deserializing it.
4) Communication :
To pass an object over network by making remote procedure call.
5) Copying :
We can create duplicates of original object by using byte array.
6) To distribute objects across different JVMs.

### What are alternatives to java serialization?
XML based data transfer
JSON based data transfer.
XML based data transfer : We can use JIBX or JAXB where we can marshall our object’s data to xml and
transfer data and then unmarshall and convert to object.
JSON based transfer : We can use json to transfer data.

### Explain about serializable interface in java?
To implement serialization in java there is an interface defined in java.io package called serializable
interface. Java.io.Serializable interface is an marker interface which doesnot contain any any methods. A
class implements Serializable lets the JVM know that the instances of the class can be serialized.
Syntax:
public interface Serializable {
}

### How to make object serializable in java?
1) Our class must implement serializable interface.If our object contains other objects those class must
also implement serializable interface.
2) We use ObjectOutputStream which extends OutputStream used to write objects to a stream.
3) We use ObjectInputStream which extends InputStream used to read objects from stream

### What is serial version UID and its importance in java?
Serial version unique identifier is a 64 bit long value .This 64 bit long value is a hash code of the class
name,super interfaces and member. Suid is a unique id no two classes will have same suid. Whenever an
object is serialized suid value will also serialize with it.
When an object is read using ObjectInputStream, the suid is also read. If the loaded class suid does not
match with suid read from object stream, readObject throws an InvalidClassException.
45

### What happens if we don’t define serial version UID ?
If we don’t define serial version UID JVM will create one suid for us. But it is recommended to have suid
rather than JVM creating because at run time JVM has to compute the hashcode of all the properties of
class. This process makes serialization low. We can’t serialize static fields one exception to this is suid
where suid gets serialized along with the object.
Ex :private static final long serialVersionUID = -5885568094444284875L;

### Can we serialize static variables in java?
We can’t serialize static variables in java. The reason being static variable are class variables that belongs
to a class not to object, but serialization mechanism saves only the object state not the class state.

### When we serialize an object does the serialization mechanism saves its references too?
When we serialize an object even the object it refers must implement serializable then the reference
objects also get serialized. If we don’t make reference objects serializable then we get
NotSerializableException.

### If we don’t want some of the fields not to serialize How to do that?
If we don’t want to serialize some fields during serialization we declare those variables as transient.
During deserialization transient variables are initialized with default values for primitives and null for object references.