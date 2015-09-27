FROM java:8
MAINTAINER Your Name <you@example.com>

ADD target/aristotl-0.0.1-SNAPSHOT-standalone.jar /srv/aristotl.jar

EXPOSE 8080

CMD ["java", "-cp", "/srv/aristotl.jar", "clojure.main", "-m", "aristotl"]
