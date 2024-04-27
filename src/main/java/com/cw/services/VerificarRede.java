package com.cw.services;

import java.net.InetAddress;

public class VerificarRede {
    public Boolean redeConectada() {
        Boolean conectada = false;

        try {
            InetAddress address = InetAddress.getByName("google.com");
            conectada = address.isReachable (10000);
        } catch (Exception e) {}

        return conectada;
    }
}
