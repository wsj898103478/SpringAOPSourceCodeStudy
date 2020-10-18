package com.wsjkk.impl.interfaceprocessor;

import java.util.Arrays;

public interface StringProcessor extends Processor{
    @Override
    String process(Object input);  //[1]

    String S = "If she weighs the same as a duck, she's made of wood"; // [2]

    static void main(String[] args){              //[3]
        Applicator.apply(new Upcase(), S);
        Applicator.apply(new Downcase(), S);
        Applicator.apply(new Splitter(), S);
    }


}
class Upcase implements StringProcessor{
    @Override
    public String process(Object input) {
        return ((String) input).toUpperCase();
    }
}

class Downcase implements StringProcessor {
    @Override
    public String process(Object input) {
        return ((String) input).toLowerCase();
    }
}

class Splitter implements StringProcessor {
    @Override
    public String process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}