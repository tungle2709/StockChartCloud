#!/bin/bash

# Quick EC2 Setup for StockChart Cloud
# Run this on a fresh Amazon Linux 2 EC2 instance

echo "Setting up EC2 for StockChart Cloud..."

# Update system
sudo yum update -y

# Install Git
sudo yum install -y git

# Download and run main deployment script
wget https://raw.githubusercontent.com/tungle2709/StockChartCloud/main/deploy.sh
chmod +x deploy.sh
./deploy.sh

echo "Setup complete! Check the application at your EC2 public IP address."
