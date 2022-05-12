# Cool Commerce
This is an example E-commerce application using the Continuum Framework. All the projects are contained within this mono repo. The individual projects follow a micorservice design. Below is a description of each project and its responsibilty within the stack.

### Projects
* ecommerce-frontend
  * Provides the UI
* ecommerce-gateway
  * Provides Continuum Gateway
* ecommerce-main
  * Provides the main store functionality
* ecommerce-payment
  * Provides payment processing services


### Basic Setup
* Install Docker Desktop.
* Install SdkMan, which makes installing/managing Java and Gradle easy.  Also can manage multiple installed versions.
  * `curl -s "https://get.sdkman.io" | bash`
* Install Java 11
  * `sdk install java 11.0.15.9.1-amzn`
* Build Docker images (Get a cup of :coffee: this will take 10 minutes or so..)
  * `./gradlew bootBuildImage`
* Run Docker Compose.
 * `docker-compose up -d`
 * You should be able to see this in Docker Desktop Dashboard after fully booting.
* You can now view the application by going here in a browser. (This may not be available for at least a minute while the backend services finish starting.)
  * [http://localhost:9090/](http://localhost:9090/)
