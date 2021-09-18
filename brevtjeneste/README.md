## Bygg + tester
gradlew build

## Lag image av tjenesten
docker build --tag brevtjeneste .

## Kjør opp bygget image som container
docker run --publish 8082:8082 brevtjeneste
