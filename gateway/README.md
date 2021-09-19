## Bygg + tester
```Bash
gradlew build
```

## Lag image av tjenesten
Artifakt må være bygget før kjøring av denne
```Bash
docker build --tag gateway .
```

## Kjør opp bygget image som container
```Bash
docker run --publish 8080:8080 gateway
```

## Starte tjeneste utenfor docker
```Bash
gradlew bootRun --args='--spring.profiles.active=dev'
```