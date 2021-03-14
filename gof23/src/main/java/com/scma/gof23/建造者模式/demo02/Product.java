package com.scma.gof23.建造者模式.demo02;

public class Product {
    private String BuilderA ="汉堡";
    private String BuilderB ="可乐";
    private String BuilderC ="薯条";
    private String BuilderD ="甜点";

    public String getBuilderA() {
        return BuilderA;
    }

    public void setBuilderA(String builderA) {
        BuilderA = builderA;
    }

    public String getBuilderB() {
        return BuilderB;
    }

    public void setBuilderB(String builderB) {
        BuilderB = builderB;
    }

    public String getBuilderC() {
        return BuilderC;
    }

    public void setBuilderC(String builderC) {
        BuilderC = builderC;
    }

    public String getBuilderD() {
        return BuilderD;
    }

    public void setBuilderD(String builderD) {
        BuilderD = builderD;
    }

    @Override
    public String toString() {
        return "Product{" +
                "BuilderA='" + BuilderA + '\'' +
                ", BuilderB='" + BuilderB + '\'' +
                ", BuilderC='" + BuilderC + '\'' +
                ", BuilderD='" + BuilderD + '\'' +
                '}';
    }
}
