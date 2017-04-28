# sbt-tweeter
An sbt plugin that allows users to publish Twitter status updates from the sbt console.

Developed for the *sbt: Beyond the Build Tool* workshop given at [flatMap(Oslo) 2017](http://2017.flatmap.no).

## Table of Contents
* [Prerequisites](#prerequisites)
* [Project structure](#project-structure)
* [Evaluating the scripted-test giter8 template](#evaluating-the-scripted-test-giter8-template)
* [Testing](#testing)
* [Releasing artifacts](#releasing-artifacts)

## Prerequisites
1. [Install sbt](http://www.scala-sbt.org/download.html)

We will be utilizing the `sbt new` command, so please ensure that you are installing, or have installed, sbt version 0.13.13 or greater. At the time of this writing, the latest version of sbt is 0.13.15.

2. Evaluate the [giter8](http://www.foundweekends.org/giter8/) template [Tapad/sbt-tweeter.g8](https://github.com/Tapad/sbt-tweeter.g8)

Use the `sbt new` command (or `g8`, if preferred) to evaluate the [sbt-tweeter.g8](https://github.com/Tapad/sbt-tweeter.g8) template.

```
sbt new Tapad/sbt-tweeter.g8
```

While this is not strictly necessary for plugin development, it will reduce the amount of boilerplate required given a time constrained workshop.

3. Create a Twitter application and generate your access token(s)

You can do so at [https://apps.twitter.com](https://apps.twitter.com). It will require a valid Twitter account.

Although the steps to create an application are super simple, the guide available at [http://docs.inboundnow.com/guide/create-twitter-application](http://docs.inboundnow.com/guide/create-twitter-application) is extremely helpful and thorough if you should have any trouble.

Once you create your application and generate your access tokens, please ensure that your application and access token *Access Type* is set to **Read & Write**.

## Project structure
* [plugin](plugin)
* [library](library)

### plugin
The actual sbt plugin.

This plugin must only be built against Scala 2.10.x and sbt 0.13.x.

[Scripted](http://www.scala-sbt.org/0.13/docs/Testing-sbt-plugins.html#scripted+test+framework) is used to test its main functionality.

### library
The supporting service that is leveraged by the plugin.

This subproject will be cross-compiled and cross-published for all minor versions of Scala, and may be used outside of the plugin, if desired.

Unit tests (using [scalatest](http://www.scalatest.org/)) will be used to test its features and functionality.

## Evaluating the scripted-test giter8 template
From the root directory of your project issue:

```
sbt new file://plugin/src/main/g8/scripted-test.g8
```

You will be prompted to give a name to the new scripted test that will be generated.

Once template evaluation is complete, you should have a new, standalone sbt project available in the `plugins/src/main/sbt-test/sbt-tweeter` directory.

## Testing
Unit tests can be written against both the library and plugin subprojects, however, they are currently only written for the library subproject.

Issue `test` from the aggregate project to run all unit tests. Issue `+test` to run the unit tests for all versions of Scala.

```
$ sbt
> test
> +test
```

Scripted end-to-end tests exist in the [src/sbt-test](plugin/src/sbt-test) directories of the plugin subproject.

To run these tests, issue `scripted` from either the aggregate project or the plugin subproject:

```
$ sbt
> scripted
```

To selectively run a single scripted test suite, issue `scripted sbt-tweeter/<name of test project>`. e.g. `scripted sbt-tweeter/negative`.

Please note that `publishLocal` will be invoked for both the library and plugin subprojects when running `scripted`.

Scripted tests take longer to run than unit tests and will log myriad output to stdout if `scriptedBufferLog` is disabled. Also note that any output written to stderr during the execution of a scripted test will result in `ERROR` level log entries. These log entries will not effect the resulting status of the actual test.

## Releasing artifacts
`sbt-tweeter` uses [https://github.com/sbt/sbt-release](sbt-release). Simply invoke `release` from the root aggregate project to release all artifacts.
