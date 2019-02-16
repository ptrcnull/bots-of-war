Running:
```bash
./entrypoint.sh
```

Docker:
```bash
docker build -t botsofwar .
docker run -v "$HOME/.m2":/root/.m2 -p 8080:8080 botsofwar
```