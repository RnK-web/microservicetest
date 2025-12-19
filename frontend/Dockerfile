FROM node:25-alpine3.22 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.29.4-alpine
WORKDIR /app
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html
