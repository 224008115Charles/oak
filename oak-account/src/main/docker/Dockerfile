FROM java:8

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    OAK_SLEEP=0 \
    JAVA_OPTS=""

ADD oak-account.jar /app.jar

EXPOSE 8081 5701/udp
CMD echo "The application will start in ${OAK_SLEEP}s..." && \
    sleep ${OAK_SLEEP} && \
    java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
