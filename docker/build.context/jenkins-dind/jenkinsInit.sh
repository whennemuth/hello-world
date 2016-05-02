#!/bin/bash
# This script copies all ssh private keys from a docker mounted folder to an unmounted folder.
# Properties of these keys can only be changed in an unmounted folder.

# Adding the various hosts to the known_hosts file should have been done as a RUN instruction in the DockerFile.
# However test if this is true and fix the situation if it is not.
if [ -f /root/.ssh/ssh_hosts ] ; then
   if [ -! -f /root/.ssh/known_hosts ] || [ -z "$(cat /root/.ssh/known_hosts | grep github.com)" ] ; then 
      ssh-keyscan -t rsa -f /root/.ssh/ssh_hosts >> /root/.ssh/known_hosts
   fi
fi

if [ -f /var/jenkins_ssh/ssh_hosts ] ; then
   if [ ! -f /var/lib/jenkins_ssh_mount/known_hosts ] || [ -z "$(cat /var/lib/jenkins_ssh_mount/known_hosts | grep github.com)" ] ; then 
      ssh-keyscan -t rsa -f /var/lib/jenkins_ssh/ssh_hosts >> /var/lib/jenkins_ssh_mount/known_hosts
   fi
fi

# make .ssh dir (-p flag will avoid error if dir already exists).
# Then copy the mounted ssh content to the non-mounted ssh dir.
mkdir -p /var/lib/jenkins_home/.ssh
cp /var/lib/jenkins_ssh_mount/. /var/lib/jenkins_ssh/ -R
cp /var/lib/jenkins_ssh_mount/. /var/lib/jenkins_home/.ssh/ -R

# Change permission level so that ssh agent does not complain about the ssh key being "too open".
chgrp users /var/lib/jenkins_ssh -R
chmod 700 /var/lib/jenkins_ssh
chmod 600 /var/lib/jenkins_ssh/*
chmod 600 /var/lib/jenkins_home/.ssh/*

source jenkins.sh