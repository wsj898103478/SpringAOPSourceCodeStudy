package com.wsjkk.impl;

public class BandPass extends Filter{
    double lowCutOff,highCutOff;

    public BandPass(double lowCutOff,double highCutOff){
        this.lowCutOff = lowCutOff;
        this.highCutOff = highCutOff;
    }

    @Override
    public Waveform process(Waveform input) {
        return input;
    }
}
