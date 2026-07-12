package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Service class for Stock operations.
 *
 * Hands on 2 (Section 2): Query Methods on stock table
 *
 * Methods:
 *   getFacebookStocksInSeptember2019() - FB stocks in Sep 2019
 *   getGoogleStocksAbove1250()         - GOOGL close > 1250
 *   getTop3HighestVolumeStocks()       - Top 3 by volume DESC
 *   getNetflixTop3LowestStocks()       - NFLX top 3 lowest close price
 */
@Service
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;

    // -------------------------------------------------------
    // Query 1: Facebook stocks in September 2019
    // -------------------------------------------------------

    /**
     * Returns all Facebook (FB) stock records for September 2019.
     * Uses Query Method: findByCodeAndDateBetween()
     */
    @Transactional
    public List<Stock> getFacebookStocksInSeptember2019() {
        LOGGER.debug("Start getFacebookStocksInSeptember2019");
        LocalDate startDate = LocalDate.of(2019, 9, 1);
        LocalDate endDate   = LocalDate.of(2019, 9, 30);
        List<Stock> stocks = stockRepository.findByCodeAndDateBetween("FB", startDate, endDate);
        LOGGER.debug("End getFacebookStocksInSeptember2019, count={}", stocks.size());
        return stocks;
    }

    // -------------------------------------------------------
    // Query 2: Google stocks where close > 1250
    // -------------------------------------------------------

    /**
     * Returns all Google (GOOGL) stocks where close price > 1250.
     * Uses Query Method: findByCodeAndCloseGreaterThan()
     */
    @Transactional
    public List<Stock> getGoogleStocksAbove1250() {
        LOGGER.debug("Start getGoogleStocksAbove1250");
        List<Stock> stocks = stockRepository
                .findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250"));
        LOGGER.debug("End getGoogleStocksAbove1250, count={}", stocks.size());
        return stocks;
    }

    // -------------------------------------------------------
    // Query 3: Top 3 dates with highest transaction volume
    // -------------------------------------------------------

    /**
     * Returns top 3 stock records with highest volume of transactions.
     * Uses Query Method: findTop3ByOrderByVolumeDesc()
     */
    @Transactional
    public List<Stock> getTop3HighestVolumeStocks() {
        LOGGER.debug("Start getTop3HighestVolumeStocks");
        List<Stock> stocks = stockRepository.findTop3ByOrderByVolumeDesc();
        LOGGER.debug("End getTop3HighestVolumeStocks, count={}", stocks.size());
        return stocks;
    }

    // -------------------------------------------------------
    // Query 4: Netflix top 3 lowest priced stocks
    // -------------------------------------------------------

    /**
     * Returns top 3 dates when Netflix (NFLX) stocks had lowest close price.
     * Uses Query Method: findTop3ByCodeOrderByCloseAsc()
     */
    @Transactional
    public List<Stock> getNetflixTop3LowestStocks() {
        LOGGER.debug("Start getNetflixTop3LowestStocks");
        List<Stock> stocks = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        LOGGER.debug("End getNetflixTop3LowestStocks, count={}", stocks.size());
        return stocks;
    }
}
