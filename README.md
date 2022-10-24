<a name="readme-top"></a>

<div align="center">
<h3 align="center">Aleksandr Laidov Mobile</h3>
</div>

<h3>Prerequisites:</h3>

* check `java -version` has java **1.8**
* check following software installed and configured on environment selected for execution:
    * Maven
    * Android studio
    * Appium server

<h3>(Local) Execution:</h3>

* start Android studio
* choose testable device:
    * connect physical android device
    * start virtual device with _Device Manager_ tool of Android studio
* open cmd
* navigate to project folder
* execute one of commands:
    * for web tests: `mvn clean test -P web`
    * for EPAM application: `mvn clean test -P native`

<h3>(Local) After tests:</h3>

* open cmd
* execute: `adb devices` to see connected/virtual devices
* in case virtual device is used
    * execute `adb emu kill`

<h3>(Cloud) Execution:</h3>

* provide Api Key to the file: `src/main/resources/secrets/apiKey.properties`
    * with attribute 'apyKey': `apyKey=key`
* install application under test on device in cloud with accordance to iOS/Android environment.
* P.S. application will be removed from device automatically after end of session.