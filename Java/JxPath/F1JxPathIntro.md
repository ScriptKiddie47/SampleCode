# JxPath

1. Doesn't seem to work with Java Records.
1. XPath the first element of a collection has index 1, not 0. 

### Dependency 

1. Maven Central

    ```ps
    implementation group: 'commons-jxpath', name: 'commons-jxpath', version: '1.4.0'
    ```
### Usage

1. Great Starter : https://commons.apache.org/proper/commons-jxpath/apidocs/index.html
1. Lets look at a bunch of common features.
    - JavaBean Property Access
    - Lenient Mode
    - Nested Bean Property Access
    - Collection Subscripts (JXPath can extract elements from arrays and collections)
    - Retrieving Multiple Results (JXPath can retrieve multiple objects from a graph. Note that the method called in this case is not getValue, but iterate)

```
```