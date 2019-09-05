package com.bitoex.bitopro.java.client;

import static com.bitoex.bitopro.java.util.BitoProUtils.validatePair;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.bitoex.bitopro.java.model.*;
import com.bitoex.bitopro.java.util.BitoProUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Default implementation for {@link BitoProPublicClient} using HttpClient.
 */
public class DefaultBitoProPublicClient extends AbstractBitoProClient implements BitoProPublicClient {

    public DefaultBitoProPublicClient(CloseableHttpClient client) {
        super(client);
    }

    public DefaultBitoProPublicClient() {
        this(BitoProUtils.createDefaultClient());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ticker getTicker(String pair) throws IOException {

        validatePair(pair);
        String url = getUrl("tickers/" + pair);
        HttpGet get = createGet(url);

        try (CloseableHttpResponse resp = client.execute(get)) {

            checkStatus(resp.getStatusLine(), resp.getEntity());
            ResponseWrapper<Ticker> wrap = om.readValue(resp.getEntity().getContent(),
                    new TypeReference<ResponseWrapper<Ticker>>() {});
            return wrap.getData();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Ticker> getTickers() throws IOException {

        String url = getUrl("tickers");
        HttpGet get = createGet(url);

        try (CloseableHttpResponse resp = client.execute(get)) {

            checkStatus(resp.getStatusLine(), resp.getEntity());
            ResponseWrapper<List<Ticker>> wrap = om.readValue(resp.getEntity().getContent(),
                    new TypeReference<ResponseWrapper<List<Ticker>>>() {});
            return wrap.getData();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderBook getOrderBook(String pair, Integer limit, Integer scale) throws IOException {

        if(limit == null) {
            limit = Integer.valueOf(5);
        }
        if(scale == null) {
            scale = NumberUtils.INTEGER_ZERO;
        }
        validatePair(pair);
        try {
            URIBuilder builder = new URIBuilder(getUrl("order-book/" + pair))
                .addParameter("limit", limit.toString())
                .addParameter("scale", scale.toString());
            HttpGet get = createGet(builder.build());

            try (CloseableHttpResponse resp = client.execute(get)) {

                checkStatus(resp.getStatusLine(), resp.getEntity());
                return om.readValue(resp.getEntity().getContent(), OrderBook.class);
            }
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Trade> getTrades(String pair) throws IOException {

        validatePair(pair);
        String url = getUrl("trades/" + pair);
        HttpGet get = createGet(url);

        try (CloseableHttpResponse resp = client.execute(get)) {

            checkStatus(resp.getStatusLine(), resp.getEntity());
            ResponseWrapper<List<Trade>> wrap = om.readValue(resp.getEntity().getContent(),
                    new TypeReference<ResponseWrapper<List<Trade>>>() {});
            return wrap.getData();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Currency> getCurrencies() throws IOException {

        String url = getUrl("provisioning/currencies");
        HttpGet get = createGet(url);

        try (CloseableHttpResponse resp = client.execute(get)) {

            checkStatus(resp.getStatusLine(), resp.getEntity());
            ResponseWrapper<List<Currency>> wrap = om.readValue(resp.getEntity().getContent(),
                    new TypeReference<ResponseWrapper<List<Currency>>>() {});
            return wrap.getData();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair> getTradingPairs() throws IOException {
        String url = getUrl("provisioning/trading-pairs");
        HttpGet get = createGet(url);

        try (CloseableHttpResponse resp = client.execute(get)) {

            checkStatus(resp.getStatusLine(), resp.getEntity());
            ResponseWrapper<List<Pair>> wrap = om.readValue(resp.getEntity().getContent(),
                    new TypeReference<ResponseWrapper<List<Pair>>>() {});
            return wrap.getData();
        }
    }

    private HttpGet createGet(String url) {
        HttpGet get = new HttpGet(url);
        get.addHeader(CLIENT_HEADER);
        return get;
    }

    private HttpGet createGet(URI url) {
        HttpGet get = new HttpGet(url);
        get.addHeader(CLIENT_HEADER);
        return get;
    }
}
