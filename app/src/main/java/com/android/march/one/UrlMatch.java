package com.android.march.one;

import java.util.HashMap;

public class UrlMatch {

    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";

    public static final String GITHUB_PREFIX = "github.com";
    public static final String GITHUB_CONTENT = "github";
    public static final int GITHUB_COLOR = 0xff000000;

    private static final String JCODECRAEER_PREFIX = "jcodecraeer.com";
    private static final String JCODECRAEER_CONTENT = "泡在网上的日子";
    private static final int JCODECRAEER_COLOR = 0xff95BA7A;

    private static final String CSDN_PREFIX = "csdn.net";
    private static final String CSDN_CONTENT = "CSDN";
    private static final int CSDN_COLOR = 0xffBE0000;

    private static final String OSCHINA_PREFIX = "oschina.net";
    private static final String OSCHINA_CONTENT = "开源中国";
    private static final int OSCHINA_COLOR = 0xff37AB4F;

    private static final String BOLE_PREFIX = "jobbole.com";
    private static final String BOLE_CONTENT = "伯乐";
    private static final int BOLE_COLOR = 0xff766964;

    private static final String WEIXIN_PREFIX = "weixin.qq.com";
    private static final String WEIXIN_CONTENT = "微信分享";
    private static final int WEIXIN_COLOR = 0xff70DC3A;

    private static final String JIANSHU_PREFIX = "jianshu.com";
    private static final String JIANSHU_CONTENT = "简书";
    private static final int JIANSHU_COLOR = 0xffE9816D;

    private static final String TUDOU_PREFIX = "tudou.com";
    private static final String TUDOU_CONTENT = "土豆";
    private static final int TUDOU_COLOR = 0xffFD6600;

    private static final String YOUKU_PREFIX = "youku.com";
    private static final String YOUKU_CONTENT = "优酷";
    private static final int YOUKU_COLOR = 0xff00A6E0;

    private static final String MIAOPAI_PREFIX = "miaopai.com";
    private static final String MIAOPAI_CONTENT = "秒拍";
    private static final int MIAOPAI_COLOR = 0xffFDD700;

    private static final String WEIBO_PREFIX = "weibo.com";
    private static final String WEIBO_CONTENT = "微博";
    private static final int WEIBO_COLOR = 0xffFDD700;

    private static final String M_WEIBO_PREFIX = "weibo.cn";
    private static final String M_WEIBO_CONTENT = "微博";
    private static final int M_WEIBO_COLOR = 0xffFDD700;

    private static final String V_SINA_PREFIX = "v.sina.cn";
    private static final String V_SINA_CONTENT = "新浪";
    private static final int V_SINA_COLOR = 0xffFDD700;

    private static final String SINA_PREFIX = "sina.com.cn";
    private static final String SINA_CONTENT = "新浪";
    private static final int SINA_COLOR = 0xff0075DE;

    private static final String TENCENT_PREFIX = "qq.com";
    private static final String TENCENT_CONTENT = "腾讯视频";
    private static final int TENCENT_COLOR = 0xff7ED800;

    private static final String STATIC_TENCENT_PREFIX = "video.qq.com";
    private static final String STATIC_TENCENT_CONTENT = "腾讯视频";
    private static final int STATIC_TENCENT_COLOR = 0xff7ED800;

    private static final String BILIBILI_PREFIX = "bilibili.com";
    private static final String BILIBILI_CONTENT = "哔哩哔哩";
    private static final int BILIBILI_COLOR = 0xffFFAEC9;

    private static final String ACFUN_PREFIX = "acfun.tv";
    private static final String ACFUN_CONTENT = "Acfun";
    private static final int ACFUN_COLOR = 0xff67BDCD;

    private static final String NETEASE_PREFIX = "163.com";
    private static final String NETEASE_CONTENT = "网易";
    private static final int NETEASE_COLOR = 0xffC02E34;

    private static final String VMOVIER_PREFIX = "vmovier.com";
    private static final String VMOVIER_CONTENT = "V电影";
    private static final int VMOVIER_COLOR = 0xff363636;

    private static final String SOUHU_PREFIX = "sohu.com";
    private static final String SOUHU_CONTENT = "搜狐";
    private static final int SOUHU_COLOR = 0xffDC1C1E;

    private static final String TV_SOUHU_PREFIX = "tv.sohu.com";
    private static final String TV_SOUHU_CONTENT = "搜狐";
    private static final int TV_SOUHU_COLOR = 0xffDC1C1E;

    private static final String FIVE_SIX_PREFIX = "56.com";
    private static final String FIVE_SIX_CONTENT = "56";
    private static final int FIVE_SIX_COLOR = 0xffE93430;

    private static final String LETV_PREFIX = "letv.com";
    private static final String LETV_CONTENT = "乐视";
    private static final int LETV_COLOR = 0xffDA0206;

    public static final String VIDEO_CONTENT = "其他";
    public static final int VIDEO_COLOR = 0xff301B91;

    public static final String OTHER_BLOG_CONTENT = "文章";
    public static final int OTHER_BLOG_COLOR = 0xff870D4F;

    public static final HashMap<String, Integer> URL_2_COLOR = new HashMap<>();
    public static final HashMap<String, String> URL_2_CONTENT = new HashMap<>();

    static {
        URL_2_COLOR.put(GITHUB_PREFIX, GITHUB_COLOR);
        URL_2_COLOR.put(JCODECRAEER_PREFIX, JCODECRAEER_COLOR);
        URL_2_COLOR.put(CSDN_PREFIX, CSDN_COLOR);
        URL_2_COLOR.put(OSCHINA_PREFIX, OSCHINA_COLOR);
        URL_2_COLOR.put(BOLE_PREFIX, BOLE_COLOR);
        URL_2_COLOR.put(WEIXIN_PREFIX, WEIXIN_COLOR);
        URL_2_COLOR.put(JIANSHU_PREFIX, JIANSHU_COLOR);
        URL_2_COLOR.put(TUDOU_PREFIX, TUDOU_COLOR);
        URL_2_COLOR.put(YOUKU_PREFIX, YOUKU_COLOR);
        URL_2_COLOR.put(MIAOPAI_PREFIX, MIAOPAI_COLOR);
        URL_2_COLOR.put(WEIBO_PREFIX, WEIBO_COLOR);
        URL_2_COLOR.put(M_WEIBO_PREFIX, M_WEIBO_COLOR);
        URL_2_COLOR.put(V_SINA_PREFIX, V_SINA_COLOR);
        URL_2_COLOR.put(TENCENT_PREFIX, TENCENT_COLOR);
        URL_2_COLOR.put(BILIBILI_PREFIX, BILIBILI_COLOR);
        URL_2_COLOR.put(STATIC_TENCENT_PREFIX, STATIC_TENCENT_COLOR);
        URL_2_COLOR.put(ACFUN_PREFIX, ACFUN_COLOR);
        URL_2_COLOR.put(NETEASE_PREFIX, NETEASE_COLOR);
        URL_2_COLOR.put(SINA_PREFIX, SINA_COLOR);
        URL_2_COLOR.put(VMOVIER_PREFIX, VMOVIER_COLOR);
        URL_2_COLOR.put(SOUHU_PREFIX, SOUHU_COLOR);
        URL_2_COLOR.put(FIVE_SIX_PREFIX, FIVE_SIX_COLOR);
        URL_2_COLOR.put(LETV_PREFIX, LETV_COLOR);
        URL_2_COLOR.put(TV_SOUHU_PREFIX, TV_SOUHU_COLOR);

        URL_2_CONTENT.put(GITHUB_PREFIX, GITHUB_CONTENT);
        URL_2_CONTENT.put(JCODECRAEER_PREFIX, JCODECRAEER_CONTENT);
        URL_2_CONTENT.put(CSDN_PREFIX, CSDN_CONTENT);
        URL_2_CONTENT.put(OSCHINA_PREFIX, OSCHINA_CONTENT);
        URL_2_CONTENT.put(BOLE_PREFIX, BOLE_CONTENT);
        URL_2_CONTENT.put(WEIXIN_PREFIX, WEIXIN_CONTENT);
        URL_2_CONTENT.put(JIANSHU_PREFIX, JIANSHU_CONTENT);
        URL_2_CONTENT.put(TUDOU_PREFIX, TUDOU_CONTENT);
        URL_2_CONTENT.put(YOUKU_PREFIX, YOUKU_CONTENT);
        URL_2_CONTENT.put(MIAOPAI_PREFIX, MIAOPAI_CONTENT);
        URL_2_CONTENT.put(WEIBO_PREFIX, WEIBO_CONTENT);
        URL_2_CONTENT.put(M_WEIBO_PREFIX, M_WEIBO_CONTENT);
        URL_2_CONTENT.put(V_SINA_PREFIX, V_SINA_CONTENT);
        URL_2_CONTENT.put(V_SINA_PREFIX, V_SINA_CONTENT);
        URL_2_CONTENT.put(TENCENT_PREFIX, TENCENT_CONTENT);
        URL_2_CONTENT.put(BILIBILI_PREFIX, BILIBILI_CONTENT);
        URL_2_CONTENT.put(STATIC_TENCENT_PREFIX, STATIC_TENCENT_CONTENT);
        URL_2_CONTENT.put(ACFUN_PREFIX, ACFUN_CONTENT);
        URL_2_CONTENT.put(NETEASE_PREFIX, NETEASE_CONTENT);
        URL_2_CONTENT.put(SINA_PREFIX, SINA_CONTENT);
        URL_2_CONTENT.put(VMOVIER_PREFIX, VMOVIER_CONTENT);
        URL_2_CONTENT.put(SOUHU_PREFIX, SOUHU_CONTENT);
        URL_2_CONTENT.put(FIVE_SIX_PREFIX, FIVE_SIX_CONTENT);
        URL_2_CONTENT.put(LETV_PREFIX, LETV_CONTENT);
        URL_2_CONTENT.put(TV_SOUHU_PREFIX, TV_SOUHU_CONTENT);
    }

    public static String processUrl(String url) {
        String temp;
        if (url.startsWith(HTTPS)) {
            temp = url.replace(HTTPS, "");
        } else {
            temp = url.replace(HTTP, "");
        }
        return temp.substring(temp.indexOf(".") + 1, temp.indexOf("/"));
    }
}