package com.example.discoveryserver.banner;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class MyBanner implements Banner {

    private static final String BANNER =
            " ______      _____   __    __              ______     __    __    _____  \n" +
                    "(_  __ \\    / ___/   ) )  ( (             (   __ \\    ) )  ( (   (_   _) \n" +
                    "  ) ) \\ \\  ( (__    ( (    ) )  ________   ) (__) )  ( (    ) )    | |   \n" +
                    " ( (   ) )  ) __)    \\ \\  / /  (________) (    __/    ) )  ( (     | |   \n" +
                    "  ) )  ) ) ( (        \\ \\/ /               ) \\ \\  _  ( (    ) )    | |   \n" +
                    " / /__/ /   \\ \\___     \\  /               ( ( \\ \\_))  ) \\__/ (    _| |__ \n" +
                    "(______/     \\____\\     \\/                 )_) \\__/   \\______/   /_____( \n";

            @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        out.println(BANNER);
        out.println();
    }
}
