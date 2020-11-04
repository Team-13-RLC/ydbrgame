#!/bin/sh

# Check if cache directory exists, if it doesn't, make one
if [ -d ./.jrecache/linux ]; then
    echo "Linux cache folder exests"
else
    echo "Making the cache folder for linux"
    mkdir -p .jrecache/linux
fi

if [ -d ./.jrecache/windows ]; then
    echo "Winodws cache folder exests"
else
    echo "Making the cache folder for windows"
    mkdir -p .jrecache/windows
fi

# Run the gradle build with a specific jdk
#echo "Starting the gradle build"
#./gradlew -Dorg.gradle.java.home=/usr/lib/jvm/java-15-adoptopenjdk desktop:dist

# Remove the old executable if it exists
if [ -d ./desktop/build/exe ]; then
    echo "Removing the old executables"
    rm -rf ./desktop/build/exe/*
fi

# Package the jar into an executable
#    --platform - what platform is the executable for
#    --jdk - URL of the JDK to be used
#    --useZgcIfSupportedOs - if a platform supports Z garbage collector, use it
#    --executable - name of the executable
#    --classpath - location of the jar(s) to be packaged
#    --mainclass - name of the main class
#    --resources - location of the resources used by the program
#    --output - output directory (one cleared in the prevous comand
#    --cachejre - folder in which to cache the jre (created by te forst command in this script)


echo "Packaging For Linux"
java -jar packr-all.jar \
     --platform linux64 \
     --jdk https://github.com/AdoptOpenJDK/openjdk15-binaries/releases/download/jdk-15.0.1%2B9/OpenJDK15U-jdk_x64_linux_hotspot_15.0.1_9.tar.gz \
     --useZgcIfSupportedOs \
     --executable game \
     --classpath ./desktop/build/libs/desktop-1.0.jar \
     --mainclass com.team13.game.desktop.DesktopLauncher \
     --resources ./core/assets \
     --output ./desktop/build/exe/linux \
     --cachejre .jrecache/linux


echo "Packaging For Windows"
java -jar packr-all.jar \
     --platform windows64 \
     --jdk https://github.com/AdoptOpenJDK/openjdk15-binaries/releases/download/jdk-15.0.1%2B9/OpenJDK15U-jdk_x64_windows_hotspot_15.0.1_9.zip \
     --useZgcIfSupportedOs \
     --executable game \
     --classpath ./desktop/build/libs/desktop-1.0.jar \
     --mainclass com.team13.game.desktop.DesktopLauncher \
     --resources ./core/assets \
     --output ./desktop/build/exe/windows \
     --cachejre .jrecache/windows

