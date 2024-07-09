FROM nginx
LABEL authors="cbq2024"

COPY ./docs/.vitepress/dist ./usr/share/nginx/html/docs-template
COPY config/nginx.conf ./etc/nginx/conf.conf
COPY config/conf.d ./etc/nginx/conf.d

EXPOSE 5173
