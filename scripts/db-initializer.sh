#!/bin/bash

set -e

echo "PostgreSQL is ready. Creating databases..."

for cur_db in "client_db" "credit_db"; do
  psql -U "$POSTGRES_USER" -c "CREATE DATABASE ${cur_db};" || echo "Database '${cur_db}' already exists"
done
