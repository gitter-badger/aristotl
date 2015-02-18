FROM java:8
MAINTAINER Your Name <you@example.com>

ADD target/micro-0.0.1-SNAPSHOT-standalone.jar /srv/micro.jar

EXPOSE 8080

CMD ["java", "-cp", "/srv/micro.jar", "clojure.main", "-m", "micro"]
