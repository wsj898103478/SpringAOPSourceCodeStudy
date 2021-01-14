package com.wsjkk.adapter;

public class Adapter implements TriplePin{
    private DualPin dualPinDevice;

    //创建适配器的时候，需要把双插设备接入进来
    public Adapter(DualPin dualPinDevice){
        this.dualPinDevice = dualPinDevice;
    }
    //适配器实现的是目标接口
    @Override
    public void electrify(int l, int n, int e) {
       //实际调用了被适配设备的双插通电，地线e被丢弃了
        dualPinDevice.electrify(l,n);
    }
}
