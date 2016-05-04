# if `docker run` first argument start with `--` the user is passing jenkins launcher arguments
if [[ $# -lt 1 ]] || [[ "$1" == "--"* ]]; then
  echo "Jenkins launcher arguments present: JENKINS_OPTS = $JENKINS_OPTS, JAVA_OPTS = $JAVA_OPTS"
  eval "su jenkins && exec java $JAVA_OPTS -jar /usr/share/jenkins/jenkins.war $JENKINS_OPTS \"\$@\" && su root"
fi

# As argument is not jenkins, assume user want to run his own process, for sample a `bash` shell to explore this image
exec "$@"
