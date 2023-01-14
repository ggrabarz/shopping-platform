FROM mongo
COPY mongo-init.js /docker-entrypoint-initdb.d/
# set default environment variables
ENV MONGO_INITDB_ROOT_USERNAME=root
ENV MONGO_INITDB_ROOT_PASSWORD=root
ENV MONGO_INITDB_DATABASE=shopping-platform
ENV MONGO_INITDB_USERNAME=inpost
ENV MONGO_INITDB_PASSWORD=inpost
ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["mongod"]