package com.tokenbetter.sdk.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * API Signature Utils
 *
 * @author Cran
 * @date 2020/01/03
 */
public class SignatureUtils {
    public static String generate(final String timestamp, String method, final String requestPath,
                                  String queryString, String body, final String secretKey) throws UnsupportedEncodingException {
        method = method.toUpperCase();
        body = StringUtils.defaultIfBlank(body, StringUtils.EMPTY);
        queryString = StringUtils.isBlank(queryString) ? "" : "?" + queryString;
        final String preHash = timestamp + method + requestPath + queryString + body;
        return Base64.encodeBase64String(new HmacUtils(HmacAlgorithms.HMAC_SHA_256,
                secretKey.getBytes("UTF-8")).hmac(preHash.getBytes("UTF-8")));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(generate("1582192437148","POST","/openapi/exchange/BTC_USDT/orders",null,"{\"volume\":\"0.1\",\"side\":\"sell\",\"systemOrderType\":\"limit\",\"pairCode\":\"BTC_USDT\",\"price\":\"9400\"}","61a8be7128293040b55d3248f462299628658abce41f0ee9b98c54b919"));
    }
}
