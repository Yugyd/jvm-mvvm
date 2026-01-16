#!/bin/bash

# Add signing properties SIGNING_KEY_ID, SIGNING_KEY, SIGNING_PASSWORD to environment variables
./gradlew :viewmodeldelegates-ui:publishToMavenLocal --console=plain
./gradlew :viewmodeldelegates-domain:publishToMavenLocal --console=plain
./gradlew :viewmodeldelegates-bom:publishToMavenLocal --console=plain
