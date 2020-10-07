# SegmentDialogFX

Library for creating wizard-like dialogs in javaFX easily.

Provides also:
 * Internationalization
 * Easy styling through css

A demo is available in the [test](src/test) module.

<table>
<tr>
<td> 

![Screenshot](screenshots/screenshot-1.jpg) 

</td>

<td> 

![Screenshot](screenshots/screenshot-2.jpg) 

</td>
</tr>
</table>

**Used libraries**
 * [Jetbrains annotations](https://github.com/JetBrains/java-annotations)
 * [SLF4j](http://www.slf4j.org/)
 
**Used libraries by the test application**
 * [Logback](http://logback.qos.ch/)
 * [JMetro](https://github.com/JFXtras/jfxtras-styles)

### Installation
Maven:

```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.Dansoftowner</groupId>
    <artifactId>SegmentDialogFX</artifactId>
    <version>1.0</version>
</dependency>
```

Gradle:

```groovy
repositories {
		...
		maven { url 'https://jitpack.io' }
}
```

```groovy
dependencies {
	 implementation 'com.github.Dansoftowner:SegmentDialogFX:1.0'
}
```