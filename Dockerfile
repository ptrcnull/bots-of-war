FROM openjdk:13-alpine

COPY . /bow
WORKDIR /bow
ENTRYPOINT ["./entrypoint.sh"]