package com.giousa.tools.encode;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StjkEncodeUrl {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "https://h5.test.shantaijk.cn/web-slow-disease/#/blankPage?customType=dm";
        String encodeUrl = URLEncoder.encode(url, "utf-8");

        System.out.println("编码：");
        System.out.println("xcx://xcx/pages/bridge/webview/index?url="+encodeUrl);

        System.out.println(StringUtils.join("xcx://xcx/pages/bridge/webview/index?url=",URLEncoder.encode(url,"utf-8")));
        
//        String url2 = "xcx://xcx/pages/bridge/webview/index.html?url=https%3A%2F%2Fh5.dev.shantaijk.cn%2Fcommonh5%2Findex.html%23%2Finvitefamily%3Finterestsid%3D{sid}";
        String url2 = "https%3A%2F%2Fh5.shantaijk.cn%2Fcommonh5%2F%23%2Fgoods-detail%3FspuId%3D20211129000000000000000000277133%26skuId%3D20211129000000000000000000277134";
//        String url2 = "https%3A%2F%2Fh5.dev.shantaijk.cn%2Fcommonh5%2Findex.html%23%2Finvitefamily%3Finterestsid%3D{sid}";
        System.out.println("解码：");
        String decode = URLDecoder.decode(url2, "utf-8");
        System.out.println(decode);
    }

}
