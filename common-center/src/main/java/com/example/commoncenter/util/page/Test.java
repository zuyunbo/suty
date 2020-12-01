package com.example.commoncenter.util.page;

import java.util.ArrayList;
import java.util.List;

public class Test{

    @FunctionalInterface
    public interface PigeonCarouseleService<T> {

        List<T> getImgurl();

    }


    public static void main(String[] args) {

        List<String>  s= getSTring(() -> {
            return  new ArrayList<>();
        });
    }



    public static  <T> List<T> getSTring(PigeonCarouseleService<T> service){
       return service.getImgurl();
    }



}



