# 1. trinn: Hente Node og bygge klienten
FROM node:latest as nodeContainer
WORKDIR /app
COPY ["package.json", "yarn.lock", "./"]
RUN yarn install
COPY . .
RUN yarn run build

# 2. trinn: Kjør opp Nginx (lite image)
FROM nginx:alpine
COPY --from=nodeContainer /app/build/ /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
