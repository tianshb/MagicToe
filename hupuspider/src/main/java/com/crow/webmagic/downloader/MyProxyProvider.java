package com.crow.webmagic.downloader;

import com.crow.utils.IPCheckUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CrowHawk on 17/10/16.
 */
public class MyProxyProvider implements ProxyProvider {
    private final List<Proxy> proxies;
    private final AtomicInteger pointer;

    public MyProxyProvider(List<Proxy> proxies) {
        this(proxies, new AtomicInteger(-1));
    }

    private MyProxyProvider(List<Proxy> proxies, AtomicInteger pointer) {
        this.proxies = proxies;
        this.pointer = pointer;
    }

    public static MyProxyProvider from(List<Proxy> proxies) {
        ArrayList proxiesTemp = new ArrayList(proxies.size());
        int var1 = proxies.size();

        for(int var2 = 0; var2 < var1; ++var2) {
            Proxy proxy = proxies.get(var2);
            if(IPCheckUtil.checkValidIP(proxy.getHost(), proxy.getPort())) {
                proxiesTemp.add(proxy);
            }
        }

        return new MyProxyProvider(Collections.unmodifiableList(proxiesTemp));
    }

    public void returnProxy(Proxy proxy, Page page, Task task) {
    }

    public Proxy getProxy(Task task) {
        return this.proxies.get(this.incrForLoop());
    }

    private int incrForLoop() {
        int p = this.pointer.incrementAndGet();
        int size = this.proxies.size();
        if(p < size) {
            return p;
        } else {
            while(!this.pointer.compareAndSet(p, p % size)) {
                p = this.pointer.get();
            }

            return p % size;
        }
    }
}
