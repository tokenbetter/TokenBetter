package com.tokenbetter.sdk.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientParameter {
    /**
     * 用户 api key，必填
     */
    private String apiKey;
    /**
     * 用户密钥，必填
     */
    private String secretKey;
    /**
     * 用户 passphrase，必填
     */
    private String passphrase;
    /**
     * 服务 url，非必填 默认
     */
    private String baseUrl;
    /**
     * 链接超时时间，非必填 默认 30s
     */
    private Long timeout;
    /**
     * 语言环境
     */
    private String locale;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
