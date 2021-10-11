package com.yihan.demo01;

public class Proxy implements Rent{
    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        seeHouse();
       host.rent();
       fare();
       contract();
    }

    //看房子
    public void seeHouse(){
        System.out.println("看房子");
    }
    //收中介费
    public void fare(){
        System.out.println("收中介费");
    }
    //签合同
    public void contract(){
        System.out.println("签合同");
    }
}
