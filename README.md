# The Cookbook

A cookbook for usage in a private network

## Installation

**Requirements:**
* JDK 11 for building
* JRE 11 for executing (only if JDK 11 is not installed)

Build the package by executing the Gradle-target `jar`, either by executing in your IDE of choice or using the console and running `gradlew jar`.  
The JAR-package can then be found in `build\libs`. Run it using `java -jar <JARNAME>.jar`

#### Caution
This application does not have any authentication. Access and editing is not subject to any restrictions.  
Therefore you should only set up this application in a network, where you trust all users to not mess with your recipes or restrict the access using additional software.

## Acknowledgements

Special thanks goes to [@ajlkn](https://twitter.com/ajlkn), who created the design called 'Editorial' and made it available, together with a whole lot more awesome designs, on his website [HTML5 UP!](https://html5up.net/)
