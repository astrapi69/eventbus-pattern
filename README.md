# Overview

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/eventbus-pattern/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/eventbus-pattern/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/eventbus-pattern/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/eventbus-pattern)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/eventbus-pattern.svg?style=flat)](https://github.com/astrapi69/eventbus-pattern/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/eventbus-pattern/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/eventbus-pattern)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/eventbus-pattern.svg)](http://www.javadoc.io/doc/io.github.astrapi69/eventbus-pattern)
[![MIT License](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
[![Hits Of Code](https://hitsofcode.com/github/astrapi69/eventbus-pattern?branch=develop)](https://hitsofcode.com/github/astrapi69/eventbus-pattern/view?branch=develop)

</div>

The EventBus design pattern is a messaging system that allows objects to communicate with each other without knowing
each other's details. It simplifies the process of event-driven programming by decoupling the event producers and
consumers. With the EventBus pattern, event producers can broadcast events to the bus, and multiple event listeners (
subscribers) can receive and handle those events asynchronously.

This repository provides a lightweight implementation of the EventBus design pattern in Java. The provided classes
include:

- **EventBus**: An interface that defines the contract for a simple event bus system.
- **BaseEventBus**: A basic implementation of the `EventBus` interface, providing centralized event dispatching and
  management.
- **GenericEventBus**: A utility class offering a final, thread-safe implementation of the EventBus, designed for
  managing and dispatching events using `EventSource` objects.

## Key Features

- **Loose Coupling**: Decouples event producers from event consumers, promoting modular and maintainable code.
- **Thread-Safety**: The provided `GenericEventBus` is thread-safe, making it suitable for concurrent applications.
- **Flexible Subscription Model**: Allows multiple subscribers to register for events of different types, and
  automatically handles event dispatching.

> Please support this project by simply putting a
>
Github <a class="github-button" href="https://github.com/astrapi69/eventbus-pattern" data-icon="octicon-star" aria-label="Star astrapi69/eventbus-pattern on GitHub">
> Star ⭐</a>
>
> Share this library with friends on Twitter and everywhere else you can
>
> If you love this project
> [![Donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

## Note

No animals were harmed in the making of this library.

## License

The source code comes under the liberal MIT License, making eventbus-pattern great for all types of applications.

## Import dependencies to your project

<details>
  <summary>gradle (click to expand)</summary>

## gradle dependency

Replace the variable ${latestVersion} with the current latest
version: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/eventbus-pattern/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/eventbus-pattern)

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of eventbus-pattern:

define version in file gradle.properties

```
eventbusPatternVersion=${latestVersion}
```

or in build.gradle ext area

```
    eventbusPatternVersion = "${latestVersion}"
```

then add the dependency to the dependencies area

```
    implementation("io.github.astrapi69:eventbus-pattern:$eventbusPatternVersion")
```

# with new libs.versions.toml file

If you use the new libs.versions.toml file for new automatic catalog versions update

```
[versions]
eventbus-pattern-version=${latestVersion}

[libraries]
eventbus-pattern = { module = "io.github.astrapi69:eventbus-pattern", version.ref = "eventbus-pattern-version" }
```

then add the dependency to the dependencies area

```
    implementation libs.eventbus.pattern
```

</details>

<details>
  <summary>Maven (click to expand)</summary>

## Maven dependency

Maven dependency is now on sonatype.
Check
out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~eventbus-pattern~~~)
for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of eventbus-pattern:

Then you can add the dependency to your dependencies:

    <properties>
        ...

```xml
        <!-- eventbus-pattern version -->
<eventbus-pattern.version>${latestVersion}</eventbus-pattern.version>
```

        ...
    </properties>
        ...
        <dependencies>
        ...

```xml
            <!-- eventbus-pattern DEPENDENCY -->
<dependency>
    <groupId>io.github.astrapi69</groupId>
    <artifactId>eventbus-pattern</artifactId>
    <version>${eventbus-pattern.version}</version>
</dependency>
```

        ...
        </dependencies>

</details>


<details>
  <summary>Snapshots (click to expand)</summary>

## 📸 Snapshots

[![Snapshot](https://img.shields.io/badge/dynamic/xml?url=https://oss.sonatype.org/service/local/repositories/snapshots/content/io/github/astrapi69/eventbus-pattern/maven-metadata.xml&label=snapshot&color=red&query=.//versioning/latest)](https://oss.sonatype.org/content/repositories/snapshots/io/github/astrapi69/eventbus-pattern/)

This section describes how to import snapshot versions into your project.
Add the following code snippet to your gradle file in the repositories section:

```
repositories {
   //...
```

```groovy
    maven {
    name "Sonatype Nexus Snapshots"
    url "https://oss.sonatype.org/content/repositories/snapshots"
    mavenContent {
        snapshotsOnly()
    }
}
```

```
}
```

</details>

## Usage

### 1. Define an Event Source

An event source is the object that generates events. In this pattern, any object can be an event source.

```java
public class MyEventSource {
    private String message;

    public MyEventSource(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
```

2. Create an Event Listener

Event listeners are objects that handle events generated by event sources.

```java
public class MyEventListener implements EventListener<EventObject<MyEventSource>> {
    @Override
    public void onEvent(EventObject<MyEventSource> event) {
        System.out.println("Received event: " + event.getSource().getMessage());
    }
}
```

3. Register the Listener and Post Events

You can now register the listener with the EventBus and post events.

```java
public class Main {
    public static void main(String[] args) {
        MyEventListener listener = new MyEventListener();

        // Register the listener to the EventBus
        GenericEventBus.register(listener, MyEventSource.class);

        // Create an event source and post it to the EventBus
        MyEventSource eventSource = new MyEventSource("Hello, EventBus!");
        GenericEventBus.post(eventSource);

        // Unregister the listener when it's no longer needed
        GenericEventBus.unregister(listener, MyEventSource.class);
    }
}
```

## Examples and Tests

To demonstrate the functionality of the EventBus classes, several examples and unit tests are provided in the test
source folder (src/test/java). These tests cover various scenarios, including registering and unregistering subscribers,
posting events, and verifying that subscribers receive the correct events.

# Example Tests

* ApplicationEventBusTest: Demonstrates how to register subscribers, post events, and ensure that subscribers receive
  the events. It also includes tests for unregistering subscribers and posting events with no subscribers.
* GenericEventBusTest: Shows how to use the GenericEventBus class for managing and dispatching events. The test verifies
  that the correct event listeners are notified when an event is posted.

These tests serve as both documentation and validation for the behavior of the EventBus classes, providing practical
examples of how to implement and use the EventBus pattern in your applications.

## Classes Overview

EventBus<S, E>

This interface defines the contract for an event bus system that allows objects to register as subscribers, unregister
from receiving events, and post events to all registered subscribers.
BaseEventBus

A basic implementation of the EventBus interface, it provides centralized management of event sources and listeners. The
BaseEventBus class allows you to register listeners for specific event types and dispatch events to them.
GenericEventBus

A final utility class that extends the functionality of the BaseEventBus by providing a thread-safe event bus mechanism.
This class maintains a registry of event sources keyed by strings or class types, allowing for efficient event
dispatching and management.

# Donations

This project is kept as an open source product and relies on contributions to remain being
developed. If you like this library, please consider a donation

over paypal:
<br>
<br>
<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=MJ7V43GU2H386" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif"
alt="PayPal this"
title="PayPal – The safer, easier way to pay online!"
style="border: none" />
</a>
<br>
<br>
or over bitcoin(BTC) with this address:

bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy.png"
alt="Donation Bitcoin Wallet" width="250"/>

or over FIO with this address:

FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop.png"
alt="Donation FIO Wallet" width="250"/>

or over Ethereum(ETH) with:

0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D.png"
alt="Donation Ethereum Wallet" width="250"/>

or over Ethereum Classic(ETC) with:

0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD.png"
alt="Donation Ethereum Classic Wallet" width="250"/>

or over Dogecoin(DOGE) with:

D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1.png"
alt="Donation Dogecoin Wallet" width="250"/>

or over Monero(XMR) with:

49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw.png"
alt="Donation Monero Wallet" width="250"/>

or over the donation buttons at the top.

## Semantic Versioning

The versions of eventbus-pattern are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit
the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

## What can i do to support this project

You can donate or contribute solve issues or pull request. Every support are welcome.

## Want to Help and improve it? ###

The source code for eventbus-pattern are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/eventbus-pattern/fork](https://github.com/astrapi69/eventbus-pattern/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/eventbus-pattern/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue if you have any suggestions or
improvements.

## Contacting the Developers

Do not hesitate to contact the eventbus-pattern developers with your questions, concerns, comments, bug reports, or
feature requests.

- Feature requests, questions and bug reports can be reported at
  the [issues page](https://github.com/astrapi69/eventbus-pattern/issues).

## Similar projects

## Credits
