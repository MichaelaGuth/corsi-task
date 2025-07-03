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


Run command `mvn clean javafx:run` from project folder

# Test sequences 
Default folder path:
- For Windows:	C:\Users\\{user's name}\Documents\Corsi Test\sequences
- For Linux: 	Home folder\Corsi Test\sequences

If folder _Corsi Test_ and/or its subfolders/files are missing, they will be automatically created.

## Create new test sequence
Test sequence file is type of JSON.
File content is only JSON array of arrays with numbers in range from 1 to 9 (Corsi task has 9 blocks)

Sample of custom test sequence file:

	[
		[1, 5],
		[3, 7],
		[4, 2, 9, 8],
		[8, 6, 9, 6]
	]


# Project layout


    ├─ .idea/          IntelliJ Idea project folder
	├─ .mvn/           Maven wrapper
	├─ src/main/       Application source code
	│  ├─ java/        Source code folder
	│  └─ resources/   Application resources
	│     ├─ fxml/     UI files
	│     ├─ json/     Predefined application json files 
	│     └─ png/      Application icons
    └─ windows_app.bat Batch file for creating .msi file

# License
Corsi task application is licensed under the GNU Affero General Public License v3.0. 
See [LICENSE](https://github.com/MichaelaGuth/corsi-task/blob/master/LICENSE) for details.
