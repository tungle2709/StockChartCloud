package com.trading.app.repository;

import com.trading.app.model.Portfolio;
import com.trading.app.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByStock(Stock stock);
}
