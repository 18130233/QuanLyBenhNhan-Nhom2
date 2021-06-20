package com.Models;

public class ChiSo {
    int huyetAp;
    double nhietDo;
    int mach;
    int nhipTho;
    double spO2;

    public ChiSo(int huyetAp, double nhietDo, int mach, int nhipTho, double spO2) {
        this.huyetAp = huyetAp;
        this.nhietDo = nhietDo;
        this.mach = mach;
        this.nhipTho = nhipTho;
        this.spO2 = spO2;
    }
    public ChiSo(){

    }

    public int getHuyetAp() {
        return huyetAp;
    }

    public void setHuyetAp(int huyetAp) {
        this.huyetAp = huyetAp;
    }

    public double getNhietDo() {
        return nhietDo;
    }

    public void setNhietDo(double nhietDo) {
        this.nhietDo = nhietDo;
    }

    public int getMach() {
        return mach;
    }

    public void setMach(int mach) {
        this.mach = mach;
    }

    public int getNhipTho() {
        return nhipTho;
    }

    public void setNhipTho(int nhipTho) {
        this.nhipTho = nhipTho;
    }

    public double getSpO2() {
        return spO2;
    }

    public void setSpO2(double spO2) {
        this.spO2 = spO2;
    }
}
