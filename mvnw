#!/usr/bin/env bash
# ----------------------------------------------------------------------------
# Maven Wrapper
# ----------------------------------------------------------------------------

MAVEN_PROJECTBASEDIR=$(cd "$(dirname "$0")" && pwd)

exec java -classpath "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar" \
    org.apache.maven.wrapper.MavenWrapperMain "$@"
