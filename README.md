# NibbleNow - CSC678 Project

Nikolaos Komninos,
Katarina Liedbeck,
Christopher Sciortino

### Instructions

##### Start The Backend 

###### Option 1: Download the latest release

1. Download the latest release from the Releases tab
2. `cd` into `backend`, and run the JAR with `java -cp nibblenow-1.0-SNAPSHOT.jar com.nibblenow.app.App`

###### Option 2: Build the JAR with Maven

1. Clone the repository
2. `cd` into `backend`
2. Build the JAR with `mvn package`
3. Run the JAR with `java -cp target/nibblenow-1.0-SNAPSHOT.jar com.nibblenow.app.App`

##### Start The Frontend

###### Option 1: Download the latest release

1. Download the release from the Releases tab
2. Open `login.html` with live server in VSCode or any other method that serves HTML files

###### Option 2: Clone the Repository

1. Clone the repository
2. Open the `frontend` folder and open `login.html` with live server in VSCode or any other method that serves HTML files

### Documentation

For Javadoc documentation, see `docs/apidocs/allpackages-index.html`, which is also included in the latest release

The main methods that are being ran in the backend are located in `backend/src/main/java/com/nibblenow/app/services/`

Their corresponding test classes are located in `backend/src/test/java/com/nibblenow/app/`
