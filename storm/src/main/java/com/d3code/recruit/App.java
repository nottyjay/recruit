package com.d3code.recruit;

import com.d3code.recruit.storm.StormServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StormServer server = new StormServer();
        server.run(args);
    }
}
