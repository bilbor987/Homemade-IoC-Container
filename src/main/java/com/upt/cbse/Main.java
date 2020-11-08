package com.upt.cbse;

import com.upt.cbse.container.IocContainer;
import com.upt.cbse.container.exceptions.NoSuchBeanException;
import com.upt.cbse.test.dealership.Dealer;
import com.upt.cbse.test.movie.MovieLister;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World\n");

        IocContainer container = new IocContainer();

        try {
            container.initializeAndBuild("CarDealershipBean.xml", "MovieFinderBean.xml");
            Dealer skodaDealer = (Dealer) container.getBean("Skoda Dealer");
            System.out.println(skodaDealer);
            MovieLister ml = (MovieLister) container.getBean("lister");
            System.out.println(ml);
        } catch (ClassNotFoundException | NoSuchFieldException | InstantiationException | IllegalAccessException | NoSuchBeanException e) {
            e.printStackTrace();
        }
    }
}
