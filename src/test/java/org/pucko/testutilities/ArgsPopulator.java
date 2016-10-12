package org.pucko.testutilities;

import java.util.ArrayList;
import java.util.Collections;

public class ArgsPopulator {


    public ArrayList<String> populate(String[] input) {

        ArrayList<String> output = new ArrayList<>();
        Collections.addAll(output, input);

        return output;

    }


}
