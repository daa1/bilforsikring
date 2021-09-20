# Fagsystem

## Bygg + tester
```Bash
gradlew build
```

## Lag image av tjenesten
Artifakt må være bygget før kjøring av denne
```Bash
docker build --tag fagsystem .
```

## Kjør opp bygget image som container
```Bash
docker run --publish 8081:8081 fagsystem
```

## Starte tjeneste utenfor docker
```Bash
gradlew bootRun
```