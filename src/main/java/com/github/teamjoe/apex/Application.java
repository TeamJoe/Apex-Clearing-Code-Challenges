package com.github.teamjoe.apex;

import com.github.teamjoe.apex.bidoffer.Feed;

import java.io.IOException;

public class Application {
    public static void main(String[] s) {
        new Feed().start("199.83.14.77", 7777);
    }
}
