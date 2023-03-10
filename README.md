# j2dts-generator
The TypeScript generator for [j2dts](https://github.com/sussyGaymer/j2dts).

## Usage
After you imported the project, you have a -`Transformer` class for *everything* you might need.
#### For example:
If you want to create a TS class, you can use the `ClassTransformer`, like so:
```java
// Every class needs a namespace!
NamespaceTransformer someNamespace = new NamespaceTransformer("com.sussygaymer.test");

ClassTransformer transformer = new ClassTransformer("SomeClass", someNamespace/*, someParentOrAbstractClass*/);

System.out.println(someNamespace.serialize());
```
The `serialize` method is present on every transformer class, so you can serialize any component at any time with ease!

The above example code outputs the following:
```
declare namespace com.sussygaymer.test {
    class SomeClass {
    }
}
```

You can then apply the same logic for interfaces and enums.

## Contributing
Any and all contributions are well-desired! If you have any issues or improvements, feel free to open an issue/pull request.
