package com.ycr.jse.strategy;

import java.util.List;

public abstract class FireStrategy<B,T> {

    public abstract List<B> fire(T t);
}
