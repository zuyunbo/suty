package com.example.commoncenter.util.page;

public class FuncApi {

  @FunctionalInterface
  public  interface FuncV {
     void apply();
  }
  @FunctionalInterface
  public static interface FuncArgV<P> {
    public void apply(P p);
  }

  @FunctionalInterface
  public static interface FuncR<R> {
    public R apply();
  }
}
