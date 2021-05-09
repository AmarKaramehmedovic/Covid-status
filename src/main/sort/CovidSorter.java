package main.sort;

import main.model.Zupanija;

import java.util.Comparator;

public class CovidSorter implements Comparator<Zupanija> {

    @Override
    public int compare(Zupanija z1, Zupanija z2){
        Double post1 = z1.getBrojZarazenih().doubleValue()
                / z1.getBrojStanovnika().doubleValue();

        Double post2 = z2.getBrojZarazenih().doubleValue()
                / z2.getBrojStanovnika().doubleValue();

        if(post1 > post2)
            return 1;
        else if(post1 < post2)
            return -1;
        else
            return 0;
    }

}
