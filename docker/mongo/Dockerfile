FROM mongo
COPY mongo-init.js /docker-entrypoint-initdb.d/
ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["mongod"]