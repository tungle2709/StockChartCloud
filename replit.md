# Real-Time Financial Trading Application

## Project Overview
This is a real-time financial trading application built with Spring Boot backend and Angular frontend. It operates as a **demo platform without authentication**, featuring portfolio tracking and trade execution with a default $100,000 balance. The user will handle Docker containerization and AWS deployment separately.

## Technology Stack

### Backend
- **Spring Boot 3.2.0** - Java-based REST API framework
- **Spring Security** - CORS configuration only (no authentication)
- **Spring Data JPA** - Database ORM
- **PostgreSQL** - Relational database
- **Maven** - Dependency management

### Frontend
- **Angular 17** - TypeScript-based SPA framework
- **Chart.js** - Real-time stock price visualization
- **RxJS** - Reactive programming for real-time updates
- **Vite** - Development server configured for Replit hosting

## Features
1. **No Authentication** - Direct access to trading dashboard
2. **Real-Time Stock Prices** - Simulated live stock price updates every 5 seconds
3. **Interactive Stock Charts** - Live charts showing price movements on the main dashboard
4. **Trading Operations** - Buy and sell stocks with real-time portfolio updates
5. **Portfolio Management** - View holdings, profit/loss, and account balance
6. **Transaction History** - Track all trading activities
7. **Default Balance** - $100,000 starting cash for demo trading

## Project Structure

```
├── backend/                    # Spring Boot Backend
│   ├── src/main/java/com/trading/app/
│   │   ├── controller/        # REST API controllers
│   │   ├── service/           # Business logic
│   │   ├── model/             # JPA entities
│   │   ├── repository/        # Data access layer
│   │   ├── config/            # CORS and application configuration
│   │   └── dto/               # Data transfer objects
│   └── pom.xml               # Maven dependencies
│
├── frontend/                  # Angular Frontend
│   ├── src/app/
│   │   ├── components/       # UI components
│   │   │   └── dashboard/    # Main trading dashboard with charts
│   │   ├── services/         # API services
│   │   └── models/           # TypeScript interfaces
│   ├── angular.json          # Angular configuration
│   └── vite.config.js        # Vite dev server config (allowedHosts: true)
```

## API Endpoints

### Stocks
- `GET /api/stocks/all` - Get all available stocks
- `GET /api/stocks/{symbol}` - Get specific stock details
- `GET /api/stocks/{symbol}/price` - Get current stock price

### Trading
- `POST /api/trades/execute` - Execute buy/sell trade
- `GET /api/trades/history` - Get trade history
- `GET /api/trades/recent` - Get recent trades

### Portfolio
- `GET /api/portfolio` - Get portfolio holdings
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

## Configuration Notes
- **Angular Development Server**: Configured with `allowedHosts: true` in vite.config.js for Replit hosting environment
- **CORS**: Backend allows requests from localhost, 127.0.0.1, and Replit domains
- **Stock Price Simulation**: Backend service updates stock prices every 5 seconds
- **Default Demo Balance**: $100,000 cash available for trading

## Cloud Architecture Concepts
This application is designed to be containerized and deployed to AWS with:
- **Docker** - Container packaging (user handles separately)
- **ECS/EKS** - Container orchestration
- **RDS** - Managed PostgreSQL database
- **ALB** - Load balancing
- **CloudWatch** - Monitoring and logging
- **Auto Scaling** - Dynamic scaling based on demand

## Recent Changes (November 2, 2025)
- **Removed all authentication features** including JWT, login components, and user management
- Simplified Spring Security configuration to CORS-only (permits all requests)
- Updated Portfolio and Trade entities to work without user references
- Set default cash balance of $100,000 for demo trading
- Fixed Vite host configuration with `allowedHosts: true` for Replit environment
- Successfully cleaned backend build artifacts and verified application works without authentication
- Removed auth-related DTOs (SignupRequest, LoginRequest, JwtResponse)
- Updated frontend services to remove authorization headers
- Simplified dashboard to remove login/logout UI elements

## Design Decisions
- **No Authentication**: Application operates as a demo with $100,000 default balance
- **Port Configuration**: Angular on port 5000, Spring Boot on port 8080 with CORS enabled
- **Constraints**: Cannot use Docker, MySQL, or AWS services on Replit - user will handle deployment
- **Stock Data**: Uses mock/simulated real-time updates via scheduled backend service
- **Simplified Schema**: Portfolio and trade features work without user accounts
