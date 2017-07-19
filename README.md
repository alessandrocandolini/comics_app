[![codecov](https://codecov.io/gh/alessandrocandolini/comics_app/branch/master/graph/badge.svg)](https://codecov.io/gh/alessandrocandolini/comics_app)
# comics_app


Sample app for loading list of comics consuming marven apis

## Architecture

3-layer clean architecture with MVP pattern for presentation layer. 

Clean is a standard layered architecture with a specific dependency rule that puts business rules (the logic) at the core of the architecture. View and data layers are "plugins" for the business layer.   
In comparison, traditional layered architectures often are more datacentric, starting from data (network, database) and proceeding towards view layer.
The basic idea of clean architecture is an old one (see for example Jacobson's usecase driven approach to object oriented software engineering), it has been recently popularise/revamped by Robert (uncle bob) Martin. 
 

### Domain layer: business rules

The behaviour/logic of the app is implemented here.
It's a pure java package, no android component is used here. 
It is the first layer that has been implemented (before view and data, below). 
It has been developed using TDD and it's mostly completed covered by unit tests (and few integration tests and one speed test for illustration purpose)

Specific behaviour is achieved by implementing usecases and chain them when appropriate. 

### View layer

Here I follow MVP pattern with passive view.  
THe pattern is slightly modified to accomodate the android requirements; in particular, the presenter exposes methods to attach/detach a view. This is because the view can become "invalid" during screen rotation/configuration changes. 

View is defined with as a pure java interface with methods to show/hide information. 
Presenters are pure java code and unit tested. They take care of the logic of the view and how to update information on the view in response of usecases. 
Show/hide methods uses plain java types (strings mainly).
Presenter keeps a reference to the view (while the view is attached) and can trigger its methods in response to usecases. 


Actual implementation of the view is done in the activities and that is the only part of the code that contains android methods.

Few espresso tests are provided but not attempt has been made to do TDD on the views. 

### Data layer 

The last layer implemented. Pure java layer, it contains network requests and, in principle, cache (not implemented yet). We follow repository pattern (in preparation for cache). Repository interface is defined in the business logic, but the actual implementation is responsibility of the data layer. 


## Pipeline

We use nevercode.io for CI/CD. Automatic builds are triggered and unit and instrumentation testing are executed every time you commit code on github. The results of the tests are shown also on github

*JaCoCo unit-test coverage report* is enabled and reports are sent to codecov.io. 

To run lint code scanning tool, run from app folder
```bash
gradle lint
```


## Third party dependencies

In addition to Android support libraries, here is a list of the main libraries used by the project:

1. [Dagger 2](https://github.com/google/dagger) - fully static, compile-time dependency injection framework for Java and Android, extensively used for DI in app and tests. 
1. [RxJava 2](https://github.com/ReactiveX/RxJava) - Reactive Extensions for the JVM. Beautiful library that brings a flavour of functional programming in the java worlds. It provides a nice abstraction on top of async/sync behaviour and it comes with a default implementation of lots of useful map/reduce/filter operators for reactive stream manipulations (pushed base streams). here mainly used for data streams and reactive usecases, *not* UI bindings. 
1. [Retrofit2](https://square.github.io/retrofit/) - type-safe HTTP API client for Java and Android 
1. [Butterknife](http://jakewharton.github.io/butterknife/) - Annotation processor for reducing amount of boilerplate of inflating and binding android views
1. [Gson](https://github.com/google/gson) - Json serialization/deserialization library
1. [OkHttp](http://square.github.io/okhttp/) - An HTTP & HTTP/2 client for Android and Java applications
1. [Parceler](https://github.com/johncarl81/parceler) - for automatic generation of parcellables

In addition, the following libreries are used for tests: 

1. [Junit4](http://junit.org/junit4/) - Simple framework to run JVM unit tests
1. [Mockito](http://site.mockito.org/) - mocking framework for unit tests in Java and Android (since version 2)
1. [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/) - for android UI automation tests
1. [Wiremock](https://wiremock.org) - for mocking, stubbing, proxying network requests in unit and integration tests

## Additional comments

Sometimes we provided two implementations of the same usecase in the business logic, for illustration purposes. One of the two implementations follows a more imperative approach (wrapping imperative for loops inside Rx) while the other is reactive , using the usual map-filter-reduce patterns directly on the rx streams. 

## TODO

Lot of things!! 
Improve test coverage, refactoring and code cleanup of latest additions to the code (for example the list presenter is doing part of the business logic). 

