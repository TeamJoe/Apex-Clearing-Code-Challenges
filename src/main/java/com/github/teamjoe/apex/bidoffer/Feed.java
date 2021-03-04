package com.github.teamjoe.apex.bidoffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class Feed {
    private static String quoteStart = "Q";
    private static String quoteSplit = "\\|";

    private HashMap<String, HashMap<String, BidOffer>> market = new HashMap<>();

    public void start(String ip, int port) {
        System.out.println("Listening on: " + ip + ":" + port);
        try (var socket = new Socket(ip, port)) {
            var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (socket.isConnected()) {
                var line = input.readLine();
                if (line != null && line.startsWith(quoteStart)) {
                    try {
                        var part = line.split(quoteSplit);
                        var symbol = part[1];
                        var exchange = part[2];
                        var bid = Integer.valueOf(part[3]);
                        var offer = Integer.valueOf(part[4]);

                        var exchanges = market.computeIfAbsent(symbol, key -> new HashMap<>());
                        exchanges.put(exchange, new BidOffer(bid, offer));

                        // TODO: Since we know what is being removed,
                        //  we could save CPU cycles by see if that is current best offer
                        //  and if so, only recompute then.
                        BidOffer best = getBest(symbol);

                        System.out.println(symbol + " " + best.bid + "@" + best.offer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BidOffer getBest(String symbol) {
        // Highest bid, lowest offer
        var best = new BidOffer(Integer.MIN_VALUE, Integer.MAX_VALUE);

        market.get(symbol).forEach((key, value) -> {
            // TODO: Fix race condition that exists here if we make this highly parallel
            if (best.bid < value.bid) {
                best.bid = value.bid;
            }
            if (best.offer > value.offer) {
                best.offer = value.offer;
            }
        });

        return best;
    }
}
