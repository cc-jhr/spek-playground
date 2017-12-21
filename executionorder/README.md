Spek execution order

Spek consists of different Scopes. One of them is ```Group``` scope. This scope is initiated by one of the following keywords: ```given```, ```describe``` or ```context```. You can also create nested context as seen in this exmaple.
The [official documentation](http://spekframework.org/docs/latest/#_scopes) does state a very important fact:
> Due to how Spek is structured, group scopes are eagerly evaluated during the discovery phase.

The spec file in in this example creates the following output.

```
SpekExecutionOrder - body Spec
SpekExecutionOrder -   given Group-A
SpekExecutionOrder -       context Context-1
SpekExecutionOrder -       context Context-2
SpekExecutionOrder -   given Group-B
SpekExecutionOrder -     beforeGroup Group-A
SpekExecutionOrder -         beforeGroup Context-1
SpekExecutionOrder - beforeEachTest Spec
SpekExecutionOrder -     beforeEachTest Group-A
SpekExecutionOrder -         beforeEachTest Context-1
SpekExecutionOrder -         it Context-1
SpekExecutionOrder -         afterEachTest Context-1
SpekExecutionOrder -     afterEachTest Group-A
SpekExecutionOrder - afterEachTest Spec
SpekExecutionOrder -         afterGroup Context-1
SpekExecutionOrder -         beforeGroup Context-2
SpekExecutionOrder - beforeEachTest Spec
SpekExecutionOrder -     beforeEachTest Group-A
SpekExecutionOrder -         beforeEachTest Context-2
SpekExecutionOrder -         it Context-2
SpekExecutionOrder -         afterEachTest Context-2
SpekExecutionOrder -     afterEachTest Group-A
SpekExecutionOrder - afterEachTest Spec
SpekExecutionOrder -         afterGroup Context-2
SpekExecutionOrder - beforeEachTest Spec
SpekExecutionOrder -     beforeEachTest Group-A
SpekExecutionOrder -       on Test-1
SpekExecutionOrder -         it Test-1
SpekExecutionOrder -     afterEachTest Group-A
SpekExecutionOrder - afterEachTest Spec
SpekExecutionOrder - beforeEachTest Spec
SpekExecutionOrder -     beforeEachTest Group-A
SpekExecutionOrder -       on Test-2
SpekExecutionOrder -         it Test-2
SpekExecutionOrder -     afterEachTest Group-A
SpekExecutionOrder - afterEachTest Spec
SpekExecutionOrder -     afterGroup Group-A
SpekExecutionOrder -     beforeGroup Group-B
SpekExecutionOrder - beforeEachTest Spec
SpekExecutionOrder -     beforeEachTest Group-B
SpekExecutionOrder -     on Test-3
SpekExecutionOrder -       it Test-3
SpekExecutionOrder -     afterEachTest Group-B
SpekExecutionOrder - afterEachTest Spec
SpekExecutionOrder - beforeEachTest Spec
SpekExecutionOrder -     beforeEachTest Group-B
SpekExecutionOrder -     on Test-4
SpekExecutionOrder -       it Test-4
SpekExecutionOrder -     afterEachTest Group-B
SpekExecutionOrder - afterEachTest Spec
SpekExecutionOrder -     afterGroup Group-B
```

It does give us a little hint, but it doesn't really give us a clear overview. But if we rearrange the output of the execution order, things are getting obvious. 

```
SpekExecutionOrder - body Spec


SpekExecutionOrder -   given Group-A
SpekExecutionOrder -       context Context-1
SpekExecutionOrder -       context Context-2
SpekExecutionOrder -   given Group-B


SpekExecutionOrder -   beforeGroup Group-A
SpekExecutionOrder -     beforeGroup Context-1
SpekExecutionOrder -       beforeEachTest Spec
SpekExecutionOrder -         beforeEachTest Group-A
SpekExecutionOrder -           beforeEachTest Context-1
SpekExecutionOrder -             it Context-1
SpekExecutionOrder -           afterEachTest Context-1
SpekExecutionOrder -         afterEachTest Group-A
SpekExecutionOrder -       afterEachTest Spec
SpekExecutionOrder -     afterGroup Context-1

SpekExecutionOrder -     beforeGroup Context-2
SpekExecutionOrder -       beforeEachTest Spec
SpekExecutionOrder -         beforeEachTest Group-A
SpekExecutionOrder -           beforeEachTest Context-2
SpekExecutionOrder -             it Context-2
SpekExecutionOrder -           afterEachTest Context-2
SpekExecutionOrder -         afterEachTest Group-A
SpekExecutionOrder -       afterEachTest Spec
SpekExecutionOrder -     afterGroup Context-2

SpekExecutionOrder -     beforeEachTest Spec
SpekExecutionOrder -       beforeEachTest Group-A
SpekExecutionOrder -         on Test-1
SpekExecutionOrder -           it Test-1
SpekExecutionOrder -       afterEachTest Group-A
SpekExecutionOrder -     afterEachTest Spec

SpekExecutionOrder -     beforeEachTest Spec
SpekExecutionOrder -       beforeEachTest Group-A
SpekExecutionOrder -         on Test-2
SpekExecutionOrder -           it Test-2
SpekExecutionOrder -       afterEachTest Group-A
SpekExecutionOrder -     afterEachTest Spec
SpekExecutionOrder -   afterGroup Group-A


SpekExecutionOrder -   beforeGroup Group-B
SpekExecutionOrder -     beforeEachTest Spec
SpekExecutionOrder -       beforeEachTest Group-B
SpekExecutionOrder -         on Test-3
SpekExecutionOrder -           it Test-3
SpekExecutionOrder -       afterEachTest Group-B
SpekExecutionOrder -     afterEachTest Spec

SpekExecutionOrder -     beforeEachTest Spec
SpekExecutionOrder -       beforeEachTest Group-B
SpekExecutionOrder -         on Test-4
SpekExecutionOrder -           it Test-4
SpekExecutionOrder -       afterEachTest Group-B
SpekExecutionOrder -     afterEachTest Spec
SpekExecutionOrder -   afterGroup Group-B
```

First off all the group scopes are executed one after another, as described in the documentation. This includes nested groups as well.
After that the tests are being executed. It starts with all ```beforeGroup``` blocks ere executing ```beforeEachTest``` definitions. Each in their structural order.