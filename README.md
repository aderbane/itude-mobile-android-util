# Android Util

Library containing utility Classes that are useful when developing for Android.

## [Changelog](https://github.com/ItudeMobile/itude-mobile-android-util/wiki/Changelog)
Current version: 0.2.0.1

## Build
#### Maven

First add the [ItudeMobile repository](https://github.com/ItudeMobile/maven-repository) to your pom.xml

```xml
<repository>
	<id>itudemobile-github-repository</id>
	<name>ItudeMobile Github repository</name>
	<url>http://mobbl.org/maven-repository/releases</url>
</repository>
```

Now add Android Util Library

```xml
<dependency>
	<groupId>com.itude.mobile.android.util</groupId>
	<artifactId>android-util-lib</artifactId>
	<version>${util.lib.version}</version>
	<type>apklib</type>
</dependency>
```
to your pom.xml.

## Contribute

If you find a bug or have a new feature you want to add, just create a pull request and submit it to us. You can also [file an issue](https://github.com/ItudeMobile/itude-mobile-android-util/issues/new).

Please note, if you have a pull request, make sure to use the [develop branch](https://github.com/ItudeMobile/itude-mobile-android-util/tree/develop) as your base.

#### Formatting

For contributors using Eclipse there's a [formatter](http://mobbl.org/downloads/code-format.xml) available.

## License
The code in this project is licensed under the Apache Software License 2.0, per the terms of the included LICENSE file.
