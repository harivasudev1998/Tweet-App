# FROM tomcat:8
# EXPOSE 8080
# CMD ["catalina.sh", "run"]
FROm  node:10
COPY . .
# install node modules and build assets
RUN npm i && npm run build

CMD ["npm", "start"]

# nginx state for serving content
FROM nginx:1.9.1 

ENTRYPOINT ["nginx", "-g", "daemon off;"]