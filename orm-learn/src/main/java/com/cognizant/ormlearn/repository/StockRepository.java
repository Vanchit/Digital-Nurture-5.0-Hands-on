package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Stock entity.
 *
 * Hands on 2 (Section 2): Query Methods on stock table
 *
 * Spring Data JPA generates all SQL queries automatically
 * from the method name using the Query Methods feature.
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    /**
     * Query 1: Get all stock details of Facebook in September 2019.
     * Finds stocks where code = 'FB' AND date is between startDate and endDate.
     *
     * Usage:
     *   findByCodeAndDateBetween("FB",
     *       LocalDate.of(2019, 9, 1),
     *       LocalDate.of(2019, 9, 30))
     */
    List<Stock> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    /**
     * Query 2: Get all Google stocks where close price was greater than 1250.
     * Finds stocks where code = 'GOOGL' AND close > threshold.
     *
     * Usage:
     *   findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250"))
     */
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal close);

    /**
     * Query 3: Find top 3 dates with highest volume of transactions.
     * Orders by volume descending and picks the top 3.
     *
     * Usage:
     *   findTop3ByOrderByVolumeDesc()
     */
    List<Stock> findTop3ByOrderByVolumeDesc();

    /**
     * Query 4: Identify 3 dates when Netflix stocks were the lowest.
     * Orders by close price ascending (lowest price = cheapest) for NFLX.
     *
     * Usage:
     *   findTop3ByCodeOrderByCloseAsc("NFLX")
     */
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
