#基于哪个镜像
FROM java:8
#将本地文件挂载到当前容器
VOLUME /temp
#复制文件到容器
ADD /target/springcloud-eureka-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
#声明暴露的接口
EXPOSE 8761

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]