FROM nginx
LABEL authors="cbq1024"

COPY ./docs/.vitepress/dist ./usr/share/nginx/html/sb3-study
COPY config/nginx.conf ./etc/nginx/conf.conf
COPY config/conf.d ./etc/nginx/conf.d

EXPOSE 80
