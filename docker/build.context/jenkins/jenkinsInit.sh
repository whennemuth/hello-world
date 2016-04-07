#!/bin/bash
# This script copies all ssh private keys from a docker mounted folder to an unmounted folder.
# Properties of these keys can only be changed in an unmounted folder.

# Change permission level so that ssh agent does not complain about the ssh key being "too open".
cp /var/jenkins_ssh_mount/. /var/jenkins_ssh/ -R
chgrp users /var/jenkins_ssh -R
chmod 600 /var/jenkins_ssh -R

# Adding the various hosts to the known_hosts file should have been done as a RUN instruction in the DockerFile.
# However test if this is true and fix the situation if it is not.
if [ -f /root/.ssh/ssh_hosts ] ; then
   if [ -n "$(cat /root/.ssh/known_hosts | grep github.com)" ] ; then 
      ssh-keyscan -t rsa -f /root/.ssh/ssh_hosts >> /root/.ssh/known_hosts
   fi
fi

source jenkins.ssh