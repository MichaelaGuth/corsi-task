@echo off
set JRE="add path to JRE folder"
set MAIN_JAR=%1
set INSTALLER_TYPE=msi

echo Remove old directories
IF EXIST target\java-runtime rmdir /s /q .\target\java-runtime
IF EXIST target\installer rmdir /s /q target\installer

echo Copy libs into installer\input\libs directory
xcopy /s /q target\dependency\* target\installer\input\libs\
copy target\%MAIN_JAR% target\installer\input\libs\

echo Detecting required modules

"%JRE%\bin\jdeps" ^
-q ^
--multi-release 18 ^
--ignore-missing-deps ^
--class-path "target\installer\input\libs\*" ^
--print-module-deps target\classes\cz\pvsps\corsitask\Main.class > dependencies.txt

set /p detected_modules=<dependencies.txt

echo Detected modules: %detected_modules%

set manual_modules=javafx.controls,javafx.fxml,com.fasterxml.jackson.databind,com.fasterxml.jackson.datatype.jsr310,com.fasterxml.jackson.core,com.fasterxml.jackson.annotation

echo Manual modules: %manual_modules%

echo Creating java runtime image
"%JRE%\bin\jlink" ^
--strip-native-commands ^
--no-header-files ^
--no-man-pages ^
--compress=2 ^
--strip-debug ^
--module-path target\installer\input\libs;%JRE%\jmods ^
--add-modules %detected_modules%,%manual_modules% ^
--output target\java-runtime
echo Java runtime image created

echo Creating installer
"%JRE%\bin\jpackage" ^
--type %INSTALLER_TYPE% ^
--dest target\installer ^
--input target\installer\input\libs ^
--name "Corsi Test" ^
--main-class cz.pvsps.corsitask.Main ^
--main-jar %MAIN_JAR% ^
--runtime-image target\java-runtime ^
--icon src\main\resources\png\icon.ico ^
--app-version %APP_VERSION% ^
--vendor "Michaela Guthova" ^
--win-dir-chooser
echo Installer created

IF EXIST dependencies.txt del dependencies.txt