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

/**
 * 自定义的ProxyProvider
 * 添加了代理有效性校验
 */

public class CrowProxyProvider implements ProxyProvider{
    private final List<Proxy> proxies;
    private final AtomicInteger pointer;

    public CrowProxyProvider(List<Proxy> proxies) {
        this(proxies, new AtomicInteger(-1));
    }

    private CrowProxyProvider(List<Proxy> proxies, AtomicInteger pointer) {
        this.proxies = proxies;
        this.pointer = pointer;
    }

    public static CrowProxyProvider from(Proxy... proxies) {
        ArrayList proxiesTemp = new ArrayList(proxies.length);
        Proxy[] var2 = proxies;
        int var3 = proxies.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Proxy proxy = var2[var4];
            if(IPCheckUtil.checkValidIP(proxy.getHost(), proxy.getPort())) {
                proxiesTemp.add(proxy);
            }
        }

        proxiesTemp.trimToSize();
        return new CrowProxyProvider(Collections.unmodifiableList(proxiesTemp));
    }

    public void returnProxy(Proxy proxy, Page page, Task task) {
    }

    public Proxy getProxy(Task task) {
        return (Proxy)this.proxies.get(this.incrForLoop());
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
