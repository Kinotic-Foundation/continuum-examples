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
1. Install Docker Desktop.
2. Install SdkMan, which makes installing/managing Java and Gradle easy.  Also can manage multiple installed versions.
   1. `curl -s "https://get.sdkman.io" | bash`
3. Install Java 21
   1. `sdk install java 21`
4. Build the Docker images (Get a cup of :coffee: this could take a bit the first time, depending on your internet connection.)
   1. `./gradlew bootBuildImage`
5. Run Docker Compose.
   1. `docker-compose up -d` (You should see the application listed under containers in docker desktop)
6. You can now view the application by going [here](http://localhost:9090/) in a browser. (This may not be available for at least a minute while the backend services finish starting.)
   1. [http://localhost:9090/](http://localhost:9090/)
 