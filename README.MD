# Corsi task
Corsi block-tapping task is a psychological test used to assess visuo-spatial working memory. In this project I aimed to improve and optimize the efficiency of psychological testing by digitizing the Corsi test and making it available to the scientific community as free software. 
# Install from package
Installer for MS Windows: [Corsi_Task_1.0.msi]()

## Build installer for Windows from source code
1. Meet all prerequisites from section [Run from Source](#prerequisites).
2. In file _windows_app.bat_ find line `set JRE="add path to JDK folder"` and set proper path to JDK folder in your system. (e.g. `set JRE=C:\Users\JohnDoe\Documents\OpenJDK`) 
3. In command line from project directory run command `mvn install`
4. MSI package should be created inside _target_ folder

# Run from source
## Prerequisites
- [Java OpenJDK version 18.0.2](https://jdk.java.net/archive/)
- [Apache Maven](https://maven.apache.org/)
## From Command-line
Run command `mvn clean javafx:run` from project folder

# License
Corsi task application is licensed under the GNU Affero General Public License v3.0. 
See (LICENSE)[https://github.com/MichaelaGuth/corsi-task/blob/master/LICENSE] for details.
