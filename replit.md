# Real-Time Financial Trading Application

## Project Overview
This is a real-time financial trading application built with Spring Boot backend and Angular frontend, designed based on AWS cloud architecture concepts for future deployment.

## Technology Stack

### Backend
- **Spring Boot 3.2.0** - Java-based REST API framework
- **Spring Security** - JWT authentication
- **Spring Data JPA** - Database ORM
- **PostgreSQL** - Relational database
- **Maven** - Dependency management

### Frontend
- **Angular 17** - TypeScript-based SPA framework
- **Chart.js** - Real-time stock price visualization
- **RxJS** - Reactive programming for real-time updates

## Features
1. **User Authentication** - JWT-based login/signup system
2. **Real-Time Stock Prices** - Simulated live stock price updates every 5 seconds
3. **Interactive Stock Charts** - Live charts showing price movements on the main dashboard
4. **Trading Operations** - Buy and sell stocks with real-time portfolio updates
5. **Portfolio Management** - View holdings, profit/loss, and account balance
6. **Transaction History** - Track all trading activities

## Project Structure

```
├── backend/                    # Spring Boot Backend
│   ├── src/main/java/com/trading/app/
│   │   ├── controller/        # REST API controllers
│   │   ├── service/           # Business logic
│   │   ├── model/             # JPA entities
│   │   ├── repository/        # Data access layer
│   │   ├── security/          # JWT security configuration
│   │   ├── config/            # Application configuration
│   │   └── dto/               # Data transfer objects
│   └── pom.xml               # Maven dependencies
│
├── frontend/                  # Angular Frontend
│   ├── src/app/
│   │   ├── components/       # UI components
│   │   │   ├── login/        # Login page
│   │   │   └── dashboard/    # Main trading dashboard with charts
│   │   ├── services/         # API services
│   │   └── models/           # TypeScript interfaces
│   └── angular.json          # Angular configuration
```

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/signup` - User registration

### Stocks
- `GET /api/stocks/all` - Get all available stocks
- `GET /api/stocks/{symbol}` - Get specific stock details
- `GET /api/stocks/{symbol}/price` - Get current stock price

### Trading
- `POST /api/trades/execute` - Execute buy/sell trade
- `GET /api/trades/history` - Get trade history
- `GET /api/trades/recent` - Get recent trades

### Portfolio
- `GET /api/portfolio` - Get user's portfolio holdings
- `GET /api/portfolio/summary` - Get portfolio summary with P&L

## Running the Application

### Backend (Port 8080)
```bash
cd backend
mvn spring-boot:run
```

### Frontend (Port 5000)
```bash
cd frontend
npm start
```

## Default Test Account
- Username: `trader` (create via signup)
- Initial Balance: $100,000

## Cloud Architecture Concepts
This application is designed to be containerized and deployed to AWS with:
- **Docker** - Container packaging
- **ECS/EKS** - Container orchestration
- **RDS** - Managed PostgreSQL database
- **ALB** - Load balancing
- **Cognito** - User authentication (can replace JWT)
- **CloudWatch** - Monitoring and logging
- **Auto Scaling** - Dynamic scaling based on demand

## Recent Changes
- Implemented Spring Boot REST API with full CRUD operations
- Created Angular dashboard with real-time stock charts
- Integrated Chart.js for interactive price visualization
- Added JWT-based authentication and authorization
- Set up PostgreSQL database with JPA entities
- Implemented simulated real-time stock price updates
