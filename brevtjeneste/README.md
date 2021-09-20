# Brevtjeneste

## Bygg + tester
```Bash
gradlew build
```

## Lag image av tjenesten
Artifakt må være bygget før kjøring av denne
```Bash
docker build --tag brevtjeneste .
```

## Kjør opp bygget image som container
```Bash
docker run --publish 8082:8082 brevtjeneste
```

## Starte tjeneste uten for docker
```Bash
gradlew bootRun
```