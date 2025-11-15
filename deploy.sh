#!/bin/bash

# StockChart Cloud EC2 Deployment Script
set -e

echo "Starting StockChart Cloud deployment on EC2..."

# Update system
sudo yum update -y

# Install Java 17
sudo yum install -y java-17-amazon-corretto-devel

# Install Node.js 20
curl -fsSL https://rpm.nodesource.com/setup_20.x | sudo bash -
sudo yum install -y nodejs

# Install Maven
sudo yum install -y maven

# Install PostgreSQL
sudo yum install -y postgresql15-server postgresql15
sudo postgresql-setup --initdb
sudo systemctl enable postgresql
sudo systemctl start postgresql

# Setup PostgreSQL database
sudo -u postgres psql -c "CREATE DATABASE trading_db;"
sudo -u postgres psql -c "CREATE USER trading_user WITH PASSWORD 'trading_pass';"
sudo -u postgres psql -c "GRANT ALL PRIVILEGES ON DATABASE trading_db TO trading_user;"

# Clone repository
cd /home/ec2-user
git clone https://github.com/tungle2709/StockChartCloud.git
cd StockChartCloud

# Build and run backend
cd backend
mvn clean package -DskipTests
nohup java -jar target/*.jar > backend.log 2>&1 &
echo $! > backend.pid

# Build and run frontend
cd ../frontend
npm install
npm run build
sudo yum install -y nginx
sudo systemctl enable nginx
sudo systemctl start nginx

# Configure nginx for frontend
sudo tee /etc/nginx/conf.d/stockchart.conf > /dev/null <<EOF
server {
    listen 80;
    server_name _;
    
    location / {
        root /home/ec2-user/StockChartCloud/frontend/dist/frontend;
        index index.html;
        try_files \$uri \$uri/ /index.html;
    }
    
    location /api/ {
        proxy_pass http://localhost:8080;
        proxy_set_header Host \$host;
        proxy_set_header X-Real-IP \$remote_addr;
    }
}
EOF

sudo nginx -t
sudo systemctl reload nginx

echo "Deployment completed!"
echo "Frontend: http://$(curl -s http://169.254.169.254/latest/meta-data/public-ipv4)"
echo "Backend logs: tail -f /home/ec2-user/StockChartCloud/backend.log"
