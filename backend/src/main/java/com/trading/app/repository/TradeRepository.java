package com.trading.app.repository;

import com.trading.app.model.Trade;
import com.trading.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findByUserOrderByCreatedAtDesc(User user);
    List<Trade> findTop10ByUserOrderByCreatedAtDesc(User user);
}
