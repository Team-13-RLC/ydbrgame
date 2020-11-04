@echo off

rem Check if cache directory exists, if it doesn't, make one
IF EXIST .\.jrecache\linux (
    ECHO "Linux cache folder exests"
) ELSE (
    mkdir .\.jrecache\linux && echo linux cache folder created
)

IF EXIST .\.jrecache\windows (
    ECHO "Linux cache folder exests"
) ELSE (
    mkdir .\.jrecache\windows && echo windows cache folder created
)

@rem Run the gradle build
ECHO "Starting the gradle build"
call gradlew.bat desktop:dist

rem Remove the old executable if it exists
IF EXIST .\desktop\build\exe (
    ECHO "Removing the old executables"
    rmdir /S /Q .\desktop\build\exe
)

rem Package the jar into an executable
rem    --platform - what platform is the executable for
rem    --jdk - URL of the JDK to be used
rem    --useZgcIfSupportedOs - if a platform supports Z garbage collector, use it
rem    --executable - name of the executable
rem    --classpath - location of the jar(s) to be packaged
rem    --mainclass - name of the main class
rem    --resources - location of the resources used by the program
rem    --output - output directory (one cleared in the prevous comand
rem    --cachejre - folder in which to cache the jre (created by te forst command in this script)


ECHO "Packaging For Linux"
call java -jar packr-all.jar --platform linux64 --jdk https://github.com/AdoptOpenJDK/openjdk15-binaries/releases/download/jdk-15.0.1%2B9/OpenJDK15U-jdk_x64_linux_hotspot_15.0.1_9.tar.gz --useZgcIfSupportedOs --executable game --classpath .\desktop\build\libs\desktop-1.0.jar --mainclass com.team13.game.desktop.DesktopLauncher --resources .\core\assets --output .\desktop\build\exe\linux --cachejre .jrecache\linux

ECHO "Packaging For Windows"
call java -jar packr-all.jar --platform windows64 --jdk https://github.com/AdoptOpenJDK/openjdk15-binaries/releases/download/jdk-15.0.1%2B9/OpenJDK15U-jdk_x64_windows_hotspot_15.0.1_9.zip --useZgcIfSupportedOs --executable game --classpath .\desktop\build\libs\desktop-1.0.jar --mainclass com.team13.game.desktop.DesktopLauncher --resources .\core\assets --output .\desktop\build\exe\windows --cachejre .jrecache\windows
