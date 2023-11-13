FROM maven:3.3-jdk-8 as builder
COPY . /usr/src/mymaven
VOLUME m2_data:/root/.m2/
WORKDIR /usr/src/mymaven
RUN mvn clean install -f /usr/src/mymaven && mkdir /usr/src/wars/
RUN find /usr/src/mymaven/ -iname '*.war' -exec cp {} /usr/src/wars/ \;


FROM tomcat:10.1.15-jre21-temurin-jammy
COPY --from=builder /usr/src/wars/* /usr/local/tomcat/webapps/
COPY tomcat-users.xml $CATALINA_HOME/conf/