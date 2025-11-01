package com.trading.app.repository;

import com.trading.app.model.Portfolio;
import com.trading.app.model.Stock;
import com.trading.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByUser(User user);
    Optional<Portfolio> findByUserAndStock(User user, Stock stock);
}
