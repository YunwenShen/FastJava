package com.cucci.common.reactor;

import reactor.core.publisher.Flux;

/**
 * The Demo For Project Reactor
 *
 * @author shenyw
 **/
public class ReactorDemo {

    public static void main(String[] args) {
        Flux.just("xiaomi", "apple", "huawei")
                .filter(phone -> phone.length() == 6)
                .map(phone -> phone.concat(" china brand"))
                .subscribe(System.out::println);
    }
}
