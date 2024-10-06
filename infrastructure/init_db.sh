#!/bin/bash
set -e

echo "Running init_db.sh to create warehouse_db..."

# Create second database 'warehouse_db'
mysql -u root -p${MYSQL_ROOT_PASSWORD} << EOF
CREATE DATABASE IF NOT EXISTS warehouse_db;
GRANT ALL PRIVILEGES ON warehouse_db.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF

echo "Finished running init_db.sh"
