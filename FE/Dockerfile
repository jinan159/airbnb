FROM node:16.13.1

# 소스 이동
COPY . /app
WORKDIR /app

# PM2 설정
RUN \
    npm install \
    npm build

# 3000 포트 허용
EXPOSE 3000

ENTRYPOINT ["npm"]
CMD ["start"]