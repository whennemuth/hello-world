docker run \
   -ti \
   -v /sys/fs/cgroup:/sys/fs/cgroup:ro \
   -p 8080:8080 \
   -p 80:80 \
   --restart unless-stopped \
   --name kccontainer2 \
   --privileged \
   --security-opt seccomp=unconfined \
   bu-ist/centos7-java-tomcat
   

#bu-ist/centos7-java-tomcatdocker run \
#   -d \
#   -u root \
#   -p 8080:8080 \
#   -p 80:8080 \
#   -p 443:8080 \
#   -p 2521:2521 \
#   --restart unless-stopped \
#   --name kccontainer2 \
#   -v /var/log/kuali:/usr/share/tomcat/log \
#   -v /opt/kuali/config:/root/kuali \
#   bu-ist/kuali-research:1603.49
