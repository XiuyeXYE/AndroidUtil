package com.xy.network;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xiuye.sharp.X;
import com.xy.bean.BaseBean;
import com.xy.util.DataUtil;
import com.xy.util.UIUtil;

import java.util.Map;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 作者：Abeing
 * <p>
 * 时间： 2019/4/5 14:34
 */
public class TicketApi {
    private WBack wBack;
    protected Map<String, Object> pathMap;
    SwipeRefreshLayout swipeRefreshLayout;

    public void request() {
        X.of(DataUtil.createRxInterface("http://dnjy.jinou.biz/", ScanGunCodeInterface.class))
                .E(sci -> {
                    return DataUtil.resultWrapIO(sci.ticket(pathMap));
                })
                .E(d -> {
                    d.subscribe(data -> {
                        if (swipeRefreshLayout != null)
                            swipeRefreshLayout.setRefreshing(false);
                        wBack.fun(data);
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            if (swipeRefreshLayout != null)
                                swipeRefreshLayout.setRefreshing(false);
                            UIUtil.log(getClass().getName(), "accept: ", throwable);
                        }
                    });
                    return X.DEFAULT_OBJECT;
                });

    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public void setPathMap(Map<String, Object> pathMap) {
        this.pathMap = pathMap;
    }

    public void setwBack(WBack wBack) {
        this.wBack = wBack;
    }

    public interface WBack {
        void fun(BaseBean data);
    }
}
