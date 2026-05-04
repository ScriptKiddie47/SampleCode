# IO

1. Read information from various `sources` - Input Direction
1. Write information to various `destinations` - Output Direction
1. IO classes are located in `java.io` and `java.nio` packages.
    1. Abstract classes define general text and binary data read and write abilities.
    1. Concrete classes descent from these parents to provide different types of IO Stream handlers
        1. Connect to different sources and destinations(A)
        1. Transform stream content(B)
        1. Perform content buffering(C)
        1. Provide convenience methods(D)

## Matrix

1. Binary Read
    1. `InputStream`
        1. `FileInputStream` (A)
        1. `ObjectInputStream` (D)
        1. `PipedInputStream` (A)
1. Binary Write
    1. `OutputStream`
        1. `FileOutputStream` (A)
        1. `ObjectOutputStream` (D)
        1. `PipedOutputStream` (A)

1. Character Read
    1. `Reader`
        1. `BufferedReader` (C)
        1. `InputStreamReader` (B)
            1. `FileReader` (A)
        1. `PipedReader` (A)
        1. `URLReader` (A)
1. Character Write
    1. `Writer`
        1. `BufferedWrite` (C)
        1. `OutputStreamWriter` (B)
            1. `FileWriter` (A)
        1. `PipedWriter` (A)
        1. `PrintWriter` (A)

## Reading and Writing Binary Data

1. InputStream
    1. `int read(byte[] buffer,int offset, int length)` -> Read Binary data from the stream
    1. `void mark(int position)` -  Mark position in the stream
    1. `boolean markSupported()` -  Check if mark is supported
    1. `long transferTo(Outstream out)` - Transfer all data from input to output
    1. `int available()` - Check amount of data available
    1. `void skip(long length)` - Skip
    1. `void reset()` - reset stream
    1. `void close` - close the stream
1. Output Stream
    1. `void write(byte[] buffer,int offset, int length)` - Write Binary Data to the stream
    1. `void flush()` - Flush the stream
    1. `void close()` - close the stream



## Basic Binary Data Reading and Writing

1. Method `read` populates the `buffer` with portions of binary data and returns an int `length` indicator
1. On intermediate reads , this indicator is equal to the buffer length.
1. On the read before last, it is equal to much data reamins in the stream.
1. On the last read, it equals -1 which indicates the end of the stream.

```java
try(InputStream inpS = new FileInputStream("data.txt");
    OutputStream outS = new FileOutputStream("dataCp.txt")){
    byte[] buffer = new byte[1024];
    int length = 0;
    while((length = inpS.read(buffer)) != -1){ // When read returns -1. It means read has nothing to read.
        System.out.println(length); // 1024,1024,1024,299 -> It ready at max 1024 bytes at a time.
        outS.write(buffer,0,length);
    }
} catch (FileNotFoundException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Read and Writing Character Data

1. Reader
    1. `int read(char[] buffer,int offset,int length)` -> Read Charater data from the stream
    1. `boolean ready()` -> Check if stream is ready
    1. `void mark(int position)` -> Mark position in the stream
    1. `boolean markSupported()` -> Mark position in the stream
    1. `long transferTo(Writer out)` -> Transfer all data from input to output.
    1. `void close()` -> Close the stream
1. Writer
    1. `void write(char[] buffer,int offset,int length)` -> Write character data to the stream
    1. `void flush()` -> Flush the stream
    1. `void close()` -> Close the stream


## Basic Charracter Data Reading and Writing.

1. Method `read` populates the `buffer` with portions of binary data and returns an int `length` indicator
1. On intermediate reads , this indicator is equal to the buffer length.
1. On the read before last, it is equal to much data reamins in the stream.
1. On the last read, it equals -1 which indicates the end of the stream.
1. Need to know character encoding - `Charset.defaultCharset() - UTF-8`

```java
try (Reader inReader = new FileReader(("data.txt"), Charset.defaultCharset());
        Writer oWriter = new FileWriter("dataCp.txt", Charset.defaultCharset())) {
    char[] buffer = new char[1024];
    int length = 0;
    while ((length = inReader.read(buffer)) != -1) {
        oWriter.write(buffer, 0, length);
    }
} catch (Exception e) {
    // TODO: handle exception
} 
```

# Connecting Streams

1. Streams can be connected to each other to apply features.
1. Connecting streams apply transform, filter, and buffer data capabilities.
1. Connect stream to a chain until you get the required and conveninent way of handling content.
1. `BufferedReader` provides `readLine` method is connected to 
    1. `InputStreamReader` coverts `bytes` to `chars` is connected to 
    1. `FileInputStream` reads `bytes` from a `file`.
1. Same is there for output

```java
try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt")));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("dataCp.txt")))) {
    String line = null;
    while ((line = in.readLine()) != null) {
        System.out.println(line);
        out.println(line);
    }
} catch (Exception e) {
    // TODO: handle exception
}
```

## Standard Input and Output

1. Class `System` provides references to standard input,output and error output.
    1. `System.in` references InputStream
    1. `System.out` references PrintStream
    1. `System.err` references PrintStream
1. `java.util.Scanner` provides conveninet way of parsing input
1. Java doesn't assume we run things on desktop computers. It has integrations for embedded system. So it doesn't assume a console will be available.

```java
Scanner s = new Scanner(System.in);
String text = null;
System.out.println("To quite type:exit");
System.out.println("Type Value and press enter:");
while(!(text = s.nextLine()).equals("exit")){
    System.out.println("Echo:   " + text);
}
s.close();
```

## Using Console

1. Class `java.io.Console` provides access to system console with operations such as 
    1. `readLine()` - Read user input
    1. `readPassword()` - read user input suppressed
    1. `reader()` - retireves the Reader object associated with Console.
    1. `writer()` - retireves the PrintWriter object associated with Console.


```java
Console c = System.console();
if (c == null) {
    System.out.println("Console is not supported");
    return;
}
System.out.println("Console is Supported");
PrintWriter out = c.writer();
String text;
while ((text = c.readLine("Type value and press enter: ")) != null) {
    if (text.equals("exit"))
        break;
    out.println("Echo: " + text);
    out.flush();
}
```

1. The output from out.println(...) is going through PrintWriter which is buffered — but you're only calling out.flush() at the very end, after the loop.

# Serialization

1. Serialization Process of writing objects from memory into a stream.
1. Deserialization is a process of reading objects from the stream
1. Data is serialized in a binary form.
1. Use Cases : Swapping objects to run out of memory, Network calls, pass parameters
1. Not suitable for long term data storage. ( Objects in specific )

## Serializable Object Graph

1. `java.io.Serializable` interface is used to indicate permission to serialize instance of a class.
1. You can serialize all primitives as well as many other classes such as Strings,numbers,dates & collectons.
1. Serialization inclues the entire object graph except `transient` variables
    1. `transient` - Indicates that a field should only exist in memory and is not going to written to any ObjectOutputStream.

```java
public class SingleScript {
    public static void main(String[] args) {
        Product tea = new Product("Tea", 1.99,10);
        System.out.println(tea);  
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("swap"))) {
            out.writeObject(tea); // Product [pName=Tea, price=1.99, quantity=10]
        } catch (Exception e) {
            e.printStackTrace();
        }
        tea = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("swap"))) {
            tea = (Product)in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(tea); // Product [pName=Tea, price=1.99, quantity=0]  
    }
}
// record Product(String pName,double price,int quantity){}
class Product implements Serializable{
    private String pName;
    private double price;
    private transient int quantity;
    public Product(String pName, double price, int quantity) {
        this.pName = pName;
        this.price = price;
        this.quantity = quantity;
    }
    public String getpName() {
        return pName;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    @Override
    public String toString() {
        return "Product [pName=" + pName + ", price=" + price + ", quantity=" + quantity + "]";
    }   
}
```

1. If you don't iplement Serializable we get error : `java.io.NotSerializableException: Product`
1. Also note `quantity`

## Serialization of Sensitive Information

1. Serialization can write data outside of secure envrionment of your program
1. Consider protecting infromation by generating secure object hash or using encryption.

## Customize Serialization Process

1. Serialization-Deserialization Process can be customized. 

## Serial Version 

1. A modified class definition is produced when
    1. Source code is changed.
    1. Class is recomplied with a different version of JDK.
1. Mismatch of the class definition could result in unpredictable program behaviour
1. `ObjectInputStream` checks the `serialVersionUID` indicator to ensure that the class definition you are using is the same as the class definitoon used by Java Run time at the time when the object was serialized and throws and `InvalidClassExpection` in case of a mismatch.

```java
private static final long serialVersionUID = 1L;
```

## File System

1. `java.nio.file` contains classes that handle filesystem interactions. `java.io.file` is the legacy API class.
1. Class `Path` represents files and folders
1. Class `Files` provides operations that handle path objects
1. Class `FileSystem` describes available filesystems and their properties

```java
FileSystem fs = FileSystems.getDefault();
fs.getFileStores().forEach(fst -> System.out.println(fst.type() + " : " + fst.name())); // sysfs,proc..
fs.getRootDirectories().forEach(rD -> System.out.println(rD)); // '/'
System.out.println(fs.getSeparator()); // '/'
```

## Constructing File System Paths

1. The `Path` represents files and folders as immutable objects
1. Path objects may represent an absolute or a relative path.
1. `Absolute Path` Starts from the file system root : `/`
1. `Relative Path` starts from current folder.
1. `Relative Path` references `.` for the current folder
1. `Relative Path` references `..` for the parent folder

```java
Path file = Path.of("/home/syndicate/Documents/CodeSource/GitHub/SampleCode/Java/Java SE 21/data.txt");
Path fileName = file.getFileName(); // data.txt
Path docsFolder = file.getParent(); // /home/syndicate/Documents/CodeSource/GitHub/SampleCode/Java/Java SE 21
Path currentFolder = Path.of("."); // .
```

## Navigate File System

1. Class `File` provides operations that handle path objects
1. Class `Files` provide operations to navigate the file system
    1. List Folder Contet
    1. Walk down filesystem path
1. Symbolic links represents `shortcuts` to other paths
1. Class Files can create and read symbolic links
1. `file.getNameCount()` and `file.getName(i)` can be used to get the nested folder path we must walk.
1. Folder Dir

```ls
$ tree

├── ritam_folder_1
│   ├── ritam_file_1.pe
│   └── ritam_file_2.txt
├── ritam_folder_2
│   └── r_file_1.txt -> ritam_folder_1/ritam_file_1.txt
```

1. Code

```java
public class SingleScript {
    public static void main(String[] args) {
        try {
            Path folder = Path.of("ritam_folder_1");
            Path file1 = Path.of("ritam_folder_1/ritam_file_1.txt");
            for (int i = 0; i < file1.getNameCount(); i++) {
                Path temp = file1.getName(i);
                System.out.println(temp); // ritam_folder_1 -> ritam_file_1.txt
            }
            Path file2 = Path.of("./ritam_folder_2/r_file_1.txt"); // r_file_1.txt doesn't exists
            Files.createSymbolicLink(file2, file1); // This is symbolic link. - r_file_1.txt doesn't exists
            Path file3 = Files.readSymbolicLink(file2);
            System.out.println(file3.toString()); // ritam_folder_1/ritam_file_1.txt
            Files.list(folder).forEach(t -> System.out.println(t)); // ritam_folder_1/ritam_file_1.txt , ritam_folder_1/ritam_file_2.pe
            Files.walk(folder).map(t -> t.toString())
            .filter(t -> t.endsWith("pe"))
            .forEach(t -> System.out.println(t)); // ritam_folder_1/ritam_file_1.pe
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

1. If we try to open the file using our file explorer - We see `link broken` error.
1. If you run the symbolic link code again. The program will break.


## Access HTTP Resources

1. Classes in `java.net.http` provides HTTP client functionalities.


```java
public class SingleScript {
    public static void main(String[] args) {
        Path path = Path.of("index.html");
        URI uri = URI.create("https://openjdk.org/");
        HttpRequest httpRequest = HttpRequest.newBuilder(uri).GET().build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<Path> res = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofFile(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }       
    }
}
```

1. Create a File populated with HTML data fetched from `https://openjdk.org/`