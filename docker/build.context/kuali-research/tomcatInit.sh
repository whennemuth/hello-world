#!/bin/bash
# This script will run catalina.sh, but only after it does some custom tasks first.
# Namely, copying war file from mounted directory to webapps dir so container can start with last built war running.

# Change permission level so that ssh agent does not complain about the ssh key being "too open".
if [ -f /usr/local/tomcat/webapp_mount/kc.war ] ; then
   cp -f /usr/local/tomcat/webapp_mount/kc.war /usr/local/tomcat/webapps/kc.war
fi

sh /usr/local/tomcat/bin/catalina.sh "run"