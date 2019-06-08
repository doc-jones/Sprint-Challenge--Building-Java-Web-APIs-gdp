package com.lambdaschool.gdp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

public class GDP{
    private static final Logger logger = LoggerFactory.getLogger(GDP.class);
    private static final AtomicLong counter = new AtomicLong();
    private long id;
    private String name;
    private int gdp;

    public GDP() {
    }

    public GDP(String name, String gdp) {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.gdp = Integer.parseInt(gdp);

//        logger.info("We created GDP value");
//        logger.debug("Yes we created a GDP listing with id " + this.id);
    }

    public long getId()
    {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGdp() {
        return gdp;
    }

    public void setGdp(int gdp) {
        this.gdp = gdp;
    }

    @Override
    public String toString() {
        return "GDP{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gdp='" + gdp + '\'' +
                '}';
    }
}
