package info.xiaohei.www.mr.weiboad;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Copyright © 2016 xiaohei, All Rights Reserved.
 * Email : chubbyjiang@gmail.com
 * Host : xiaohei.info
 * Created : 16/4/8 14:36
 */
public class WeiboAd {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, URISyntaxException {
        info.xiaohei.www.mr.weiboad.CalcTFAndN.run();
        info.xiaohei.www.mr.weiboad.CalcDF.run();
        info.xiaohei.www.mr.weiboad.CalcTotal.run();
    }
}
