package com.yihan.demo01;

public class Client {
    public static void main(String[] args) {
        // 房东要出租房子
        Host host = new Host();
        // 代理帮房东出组房子，但一般会多一些附属操作
        Proxy proxy = new Proxy(host);


        // 你不用面对房东，直接找代理即可
        proxy.rent();
    }
}
