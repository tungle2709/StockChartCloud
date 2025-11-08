# 📈 StockChart Cloud - Real-Time Financial Trading Platform

<div align="center">

![Trading Platform](https://img.shields.io/badge/Trading-Platform-success?style=for-the-badge&logo=chart.js&logoColor=white)
![Real Time](https://img.shields.io/badge/Real--Time-Updates-blue?style=for-the-badge&logo=socket.io&logoColor=white)
![Demo Mode](https://img.shields.io/badge/Demo-$100K%20Balance-gold?style=for-the-badge&logo=dollar-sign&logoColor=white)

</div>

## 🚀 Overview

A modern, real-time financial trading application featuring live stock price updates, interactive charts, and portfolio management. Built as a demo platform with a $100,000 starting balance for seamless trading simulation.

## ✨ Key Features

- 🔄 **Real-Time Stock Prices** - Live updates every 5 seconds
- 📊 **Interactive Charts** - Dynamic price visualization with Chart.js
- 💰 **Portfolio Management** - Track holdings, P&L, and balance
- 📈 **Trading Operations** - Execute buy/sell orders instantly
- 📋 **Transaction History** - Complete trading activity log
- 🎯 **No Authentication** - Direct access for demo purposes

## 🛠️ Tech Stack

### 🎨 Frontend
<div align="center">

| Technology | Version | Purpose |
|------------|---------|---------|
| ![Angular](https://img.shields.io/badge/Angular-17.3.0-red?style=flat-square&logo=angular) | 17.3.0 | SPA Framework |
| ![TypeScript](https://img.shields.io/badge/TypeScript-5.4.2-blue?style=flat-square&logo=typescript) | 5.4.2 | Type Safety |
| ![Chart.js](https://img.shields.io/badge/Chart.js-4.5.1-orange?style=flat-square&logo=chart.js) | 4.5.1 | Data Visualization |
| ![RxJS](https://img.shields.io/badge/RxJS-7.8.0-purple?style=flat-square&logo=reactivex) | 7.8.0 | Reactive Programming |

</div>

### ⚙️ Backend
<div align="center">

| Technology | Version | Purpose |
|------------|---------|---------|
| ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green?style=flat-square&logo=spring) | 3.2.0 | REST API Framework |
| ![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk) | 17 | Programming Language |
| ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql) | 16 | Database |
| ![Maven](https://img.shields.io/badge/Maven-3.x-red?style=flat-square&logo=apache-maven) | 3.x | Build Tool |

</div>

### 🔧 Additional Technologies
<div align="center">

![Spring Security](https://img.shields.io/badge/Spring%20Security-CORS%20Only-lightgreen?style=flat-square&logo=spring-security)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-ORM-green?style=flat-square&logo=spring)
![Lombok](https://img.shields.io/badge/Lombok-Code%20Generation-red?style=flat-square&logo=lombok)

</div>

## 🏗️ Architecture

```mermaid
graph TB
    A[Angular Frontend<br/>Port 5000] --> B[Spring Boot API<br/>Port 8080]
    B --> C[PostgreSQL Database<br/>Port 5432]
    B --> D[Stock Price Service<br/>Real-time Updates]
    
    subgraph "Frontend Components"
        E[Dashboard Component]
        F[Trading Component]
        G[Portfolio Component]
        H[Chart Component]
    end
    
    subgraph "Backend Services"
        I[Stock Service]
        J[Trading Service]
        K[Portfolio Service]
        L[Price Update Service]
    end
    
    A --> E
    A --> F
    A --> G
    A --> H
    
    B --> I
    B --> J
    B --> K
    B --> L
```

## 📁 Project Structure

```
StockChartCloud/
├── 🎨 frontend/                 # Angular Application
│   ├── src/app/
│   │   ├── components/         # UI Components
│   │   │   └── dashboard/      # Trading Dashboard
│   │   ├── services/           # API Services
│   │   └── models/             # TypeScript Interfaces
│   ├── angular.json            # Angular Configuration
│   └── package.json            # Dependencies
│
├── ⚙️ backend/                  # Spring Boot API
│   ├── src/main/java/com/trading/app/
│   │   ├── controller/         # REST Controllers
│   │   ├── service/            # Business Logic
│   │   ├── model/              # JPA Entities
│   │   ├── repository/         # Data Access
│   │   └── config/             # Configuration
│   └── pom.xml                 # Maven Dependencies
│
└── 📚 README.md                # This file
```

## 🚀 Quick Start

### Prerequisites
- ☕ Java 17+
- 📦 Node.js 20+
- 🐘 PostgreSQL 16+
- 🔧 Maven 3.x

### 🏃‍♂️ Running the Application

#### 1️⃣ Start Backend
```bash
cd backend
mvn spring-boot:run
```
🌐 Backend runs on: `http://localhost:8080`

#### 2️⃣ Start Frontend
```bash
cd frontend
npm install
npm start
```
🌐 Frontend runs on: `http://localhost:5000`

## 🔌 API Endpoints

### 📈 Stock Operations
```http
GET    /api/stocks/all              # Get all stocks
GET    /api/stocks/{symbol}         # Get stock details
GET    /api/stocks/{symbol}/price   # Get current price
```

### 💱 Trading Operations
```http
POST   /api/trades/execute          # Execute trade
GET    /api/trades/history          # Trade history
GET    /api/trades/recent           # Recent trades
```

### 💼 Portfolio Management
```http
GET    /api/portfolio               # Portfolio holdings
GET    /api/portfolio/summary       # Portfolio summary
```

## 🎯 Demo Features

<div align="center">

| Feature | Description | Status |
|---------|-------------|--------|
| 💰 Starting Balance | $100,000 demo cash | ✅ Active |
| 🔄 Real-time Updates | 5-second price refresh | ✅ Active |
| 📊 Live Charts | Interactive price charts | ✅ Active |
| 🛡️ Authentication | Disabled for demo | ❌ Disabled |
| 📱 Responsive Design | Mobile-friendly UI | ✅ Active |

</div>

## ☁️ Cloud Deployment Ready

This application is designed for containerization and AWS deployment:

<div align="center">

![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=flat-square&logo=docker)
![AWS ECS](https://img.shields.io/badge/AWS%20ECS-Compatible-orange?style=flat-square&logo=amazon-aws)
![AWS RDS](https://img.shields.io/badge/AWS%20RDS-PostgreSQL-blue?style=flat-square&logo=amazon-aws)

</div>

### 🏗️ Deployment Architecture
- 🐳 **Docker** - Container packaging
- 🚀 **AWS ECS/EKS** - Container orchestration
- 🗄️ **AWS RDS** - Managed PostgreSQL
- ⚖️ **AWS ALB** - Load balancing
- 📊 **CloudWatch** - Monitoring

## 🔧 Configuration

### CORS Settings
```java
// Backend allows requests from:
- localhost:5000 (Angular dev server)
- 127.0.0.1:5000
- Replit domains (if needed)
```

### Database Configuration
```properties
# PostgreSQL connection
spring.datasource.url=jdbc:postgresql://localhost:5432/trading_db
spring.jpa.hibernate.ddl-auto=update
```

## 📊 Performance Metrics

<div align="center">

| Metric | Value | Description |
|--------|-------|-------------|
| 🔄 Update Frequency | 5 seconds | Stock price refresh rate |
| 💾 Default Balance | $100,000 | Demo trading capital |
| 🌐 API Response | <100ms | Average response time |
| 📱 Mobile Support | 100% | Responsive design coverage |

</div>

## 🤝 Contributing

1. 🍴 Fork the repository
2. 🌿 Create feature branch (`git checkout -b feature/amazing-feature`)
3. 💾 Commit changes (`git commit -m 'Add amazing feature'`)
4. 📤 Push to branch (`git push origin feature/amazing-feature`)
5. 🔄 Open Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- 📊 **Chart.js** - Beautiful chart visualizations
- 🌱 **Spring Boot** - Robust backend framework
- 🅰️ **Angular** - Powerful frontend framework
- 🐘 **PostgreSQL** - Reliable database system

---

<div align="center">

**Built with ❤️ for financial trading simulation**

![GitHub stars](https://img.shields.io/github/stars/yourusername/StockChartCloud?style=social)
![GitHub forks](https://img.shields.io/github/forks/yourusername/StockChartCloud?style=social)

</div>
