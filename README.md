<!-- 
    If viewing raw file in GitPod/GitHub, for easier reading, right-click on the file on Explorer, and select 'Open Preview' 
/-->

[![Built using ALFA](https://alfa-lang.io/_images/built-using-ALFA.png)](https://alfa-lang.io)

# ALFA Sample Project

This Repository contains an example ALFA project showcasing some ALFA data modelling features - [entity](https://alfa-lang.io/lang/constructs/entity.html), [trait](https://alfa-lang.io/lang/constructs/trait.html), [service](https://alfa-lang.io/lang/constructs/service.html), [library](https://alfa-lang.io/lang/expressions/library.html), [testcase](https://alfa-lang.io/labs/langtesting.html) definitions. 

It demonstrates features of that can be used to clearly capture data models, APIs, validations, calculation rules and testcases. For more information see [www.schemarise.com/products](http://www.schemarise.com/products) or the ALFA [Language Guide](https://alfa-lang.io/lang/guide.html). Alongside the model, 
there are few sample Java and Python projects showing how the models can be used in various usecases and technologies.


This project can be used directly from the browser using github/gitpod integration with ALFA toolset. You can login to gitpod using your github/bitbucket/gitlab account.


> **Click the button below to open the project in your browser.**
> 
> <a href="https://gitpod.io/#https://github.com/Schemarise/alfa-sample-project" target="_blank"><img src="https://gitpod.io/button/open-in-gitpod.svg"/></a>
> 
> Or
>
> Open this project directly in **Github Codespaces**.
>
> **Tip:** Have a look at the *Quick Tour* below first, then right-click on the Gitpod button and open in new tab/window or in GitHub Codespaces.


### Want to see complex example models visualized in ALFA?

See [https://schemarise.github.io/alfa-sample-models-doc](https://schemarise.github.io/alfa-sample-models-doc/)


# Quick Tour

## ALFA Model files

* Once opened in gitpod, on the left Explorer Panel, click on the `model` directory and browse the `*.alfa` files.
* The model files are actively validated. For example try changing `string` to `stringx` or `Person` to `Personx`.
* Navigate around the model by pressing Control (or Command on Mac) and clicking a user-defined-type, such as `Person`.

<img src="resources/ide.png" alt="ALFA IDE" width="800"/>

## Model live-documentation

* Right-click on any ALFA file and select `Open ALFA Studio` which will open a new Browser with web-based ALFA Studio Console.
* Click on `View Documentation`.
* You can navigate around the documentation UML diagram or links. Documentation from model is incorporated into the HTML page.
* Changes to the ALFA files are reflected in HTML when the browser page is refreshed.
* **View a live ALFA Documentation site** [https://alfa-demo.github.io/position-model-docs/index.html](https://alfa-demo.github.io/position-model-docs/index.html)

<img src="resources/ide-docs.png" alt="ALFA DOCS" width="800"/>

* Transform definition rendered as part of ALFA Documentation showing source to target field paths.

<img src="resources/transform.png" alt="ALFA DOCS" width="800"/>

## Data Quality Report produced from DQ Validation
[Sample DQ Report](https://alfa-lang.io/samples/DQ-Report.html)

## Model versions upgrade changes report
[Sample Change Report](https://alfa-lang.io/samples/rel/report-v2-v3.html)


## Code Examples

The `projects` directory includes examples in Java, Scala and Python. Typically model files may reside in a project of their own 
which will then be used as a dependency from Java or Python projects. However for the purpose of having fully self-contained example project,
these are conbined in sub-directories within this project.

1. [Java/Scala samples](projects/java/README.md) - 4 sample projects showing ALFA being used in different usecases.
2. [Python samples](projects/python/README.md) - Coming soon

For any questions, please email `info@schemarise.com`.
