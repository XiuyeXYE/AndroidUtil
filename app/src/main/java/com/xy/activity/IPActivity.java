package com.xy.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.xiuye.sharp.X;
import com.xiuye.util.cls.XType;
import com.xy.util.DataUtil;
import com.xy.util.UIUtil;
import com.xy.util.bean.HttpAddress;
import com.xy.util.bean.HttpInterface;

import java.util.Map;

public class IPActivity extends AbstractBaseActivity {

    private static final int TYPE_GET_IP = 0;
    private static final int TYPE_LOOKUP_IP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);

        Map<String, Object> params = XType.map();

        this.clickBind(R.id.currentIPBtn, v -> {
            params.put("type", TYPE_GET_IP);
            this.requestAndHandler(params);
        });

        this.clickBind(R.id.lookUpIPBtn, v -> {
            params.put("type", TYPE_LOOKUP_IP);
            this.<EditText>byId(R.id.ipAddressTextEditor).E(t -> {
                params.put("ip", t.getText().toString());
                return t;
            });
            this.requestAndHandler(params);

        });


    }

    private void requestAndHandler(Map<String, Object> params) {

        X.of(DataUtil.createRxInterface(HttpAddress.SEARCH_IP_URL, HttpInterface.class))
                .E(sci -> {
                    return DataUtil.resultWrapIO(sci.lookupIP(params));
                }).E(o -> {
            o.subscribe(data -> {
                X.of(data).E(d -> {
                    this.<TextView>byId(R.id.ipDisplayView).E(displayView -> {

                        displayView.setText(d.toString());

                        return displayView;
                    });
                    return data;
                });
            }, e -> {
                UIUtil.log(getClass().getName(), "error: ", e);
            });
            return o;
        });

    }
}