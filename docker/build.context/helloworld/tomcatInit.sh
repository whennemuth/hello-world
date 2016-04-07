#!/bin/bash
# This script will run catalina.sh, but only after it does some custom tasks first.
# Namely, copying war file from mounted directory to webapps dir so containter can start with last built war running.

# Change permission level so that ssh agent does not complain about the ssh key being "too open".
cp /usr/local/tomcat/webapp_mount/. /usr/local/tomcat/webapps/ -R

sh /usr/local/tomcat/bin/catalina.sh "run"