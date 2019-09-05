package com.bitoex.bitopro.java.client;

import com.bitoex.bitopro.java.model.Currency;
import com.bitoex.bitopro.java.model.OrderBook;
import com.bitoex.bitopro.java.model.Pair;
import com.bitoex.bitopro.java.model.Ticker;
import com.bitoex.bitopro.java.model.Trade;

import java.io.IOException;
import java.util.List;



/**
 * Client for BitoPro Public API. Provides direct methods to access BitoPro
 * Public API. Default implementation can be created via {@link BitoProClientBuilder}.
 * 
 * All methods throw {@link com.bitoex.bitopro.java.exception.ApiException} when
 * server returns error. All parameter related errors will be thrown with
 * {@link com.bitoex.bitopro.java.exception.BadArgumentException}.
 * 
 * Pair is in terms of base and quote and combined in the form of @{code
 * ${base}_${quote}}.
 */
public interface BitoProPublicClient {

    /**
     * Get ticker for specific pair.
     * 
     * @param pair the pair to get ticker
     * @return ticker for the pair
     * @throws IOException when connection error occured while called the Rest API
     */
    Ticker getTicker(String pair) throws IOException;

    /**
     * Get tickers for all pairs.
     * 
     * @return ticker for the pair
     * @throws IOException when connection error occurred while called the Rest API
     */
    List<Ticker> getTickers() throws IOException;

    /**
     * Get order book for specific pair.
     * 
     * @param pair the pair to get order book
     * @param limit The limit for the response, possible values are 1, 5, 10, 20. Default is 5.
     * @param scale The scale for the response. Default is 0.
     * @return order book for the pair
     * @throws IOException when connection error occurred while called the Rest API
     */
    OrderBook getOrderBook(String pair, Integer limit, Integer scale) throws IOException;

    /**
     * Get recent trades for specific pair.
     * 
     * @param pair the pair to get trades
     * @return trades for the pair
     * @throws IOException when connection error occurred while called the Rest API
     */
    List<Trade> getTrades(String pair) throws IOException;

    /**
     * Get currency information
     * @return list of supported currencies and their state
     * @throws IOException
     */
    List<Currency> getCurrencies() throws IOException;

    /**
     * Get trading pair information
     * @return list of supported trading pairs and their state
     * @throws IOException
     */
    List<Pair> getTradingPairs() throws IOException;

    /**
     * Create convenient methods for specified pair. Save users from passing the
     * same pair parameters.
     * 
     * @param pair the pair to act on
     * @return a {@code BitoProPublicPairClient} for specified pair
     */
    default BitoProPublicPairClient getPairClient(String pair) {
        return new BitoProPublicPairClient(this, pair);
    }

    /**
     * A pair specific BitoProPublicClient.
     */
    class BitoProPublicPairClient {

        private final BitoProPublicClient client;
        private final String pair;

        BitoProPublicPairClient(BitoProPublicClient client, String pair) {
            this.client = client;
            this.pair = pair;
        }

        /**
         * Get ticker for specific pair.
         * 
         * @return ticker for the pair
         * @throws IOException when connection error occurred while called the Rest API
         * @see BitoProPublicClient#getTicker(String)
         */
        public Ticker getTicker() throws IOException {
            return client.getTicker(pair);
        }

        /**
         * Get order book for specific pair.
         * @param limit The limit for the response, possible values are 1, 5, 10, 20. Default is 5.
         * @param scale The scale for the response. Default is 0.
         * @return order book for the pair
         * @throws IOException when connection error occurred while called the Rest API
         * @see BitoProPublicClient#getOrderBook(String, Integer, Integer)
         */
        public OrderBook getOrderBook(Integer limit, Integer scale) throws IOException {
            return client.getOrderBook(pair, limit, scale);
        }

        /**
         * Get recent trades for specific pair.
         * 
         * @return trades for the pair
         * @throws IOException when connection error occurred while called the Rest API
         * @see BitoProPublicClient#getTrades(String)
         */
        public List<Trade> getTrades() throws IOException {
            return client.getTrades(pair);
        }


    }

}
