FROM java:8
EXPOSE 9020
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
ADD demo-0.0.1-SNAPSHOT.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "/app.jar"]