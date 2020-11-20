FROM registry.cn-hangzhou.aliyuncs.com/menglou/java:8
VOLUME /tmp
ADD register-center-1.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]