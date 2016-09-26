package org.pucko.testutilities;

import java.util.ArrayList;

/**
 * Created by Tobias on 15/09/16.
 */
public class ArgsPopulator {


    public ArrayList<String> populate(String[] input) {

        ArrayList<String> output = new ArrayList<>();
        for (String s : input) {
            output.add(s);
        }

        return output;

    }


}
