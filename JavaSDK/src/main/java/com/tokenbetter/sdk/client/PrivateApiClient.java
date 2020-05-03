package com.tokenbetter.sdk.client;

import com.tokenbetter.sdk.common.domain.ClientParameter;
import com.tokenbetter.sdk.common.enums.Content;
import com.tokenbetter.sdk.exchange.ExchangeApiFacade;
import com.tokenbetter.sdk.common.enums.SupportedLocaleEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * open api Client
 *
 * @author Cran
 * @date 2020/01/03
 */
public class PrivateApiClient {
    private final ApiClient apiClient;
    private final ClientParameter configuration;

    private PrivateApiClient(final Builder builder) {
        this.configuration = builder.configuration;
        Validate.notNull(this.configuration, "configuration is null");
        Validate.notNull(this.configuration.getApiKey(), "apiKey is null");
        Validate.notNull(this.configuration.getSecretKey(), "secretKey is null");
        Validate.notNull(this.configuration.getPassphrase(), "passphrase is null");

        this.configuration.setBaseUrl(StringUtils.defaultIfBlank(this.configuration.getBaseUrl(), Content.BASE_URL));
        this.configuration.setTimeout(ObjectUtils.defaultIfNull(this.configuration.getTimeout(), Content.TIME_OUT));
        this.configuration.setLocale(ObjectUtils.defaultIfNull(this.configuration.getLocale(), SupportedLocaleEnum.EN_US.getName()));

        this.apiClient = new ApiClient(this.configuration);
    }

    public static Builder builder() {
        return new Builder();
    }


    /**
     * 现货 REST API Endpoint
     *
     * @return PerpetualApiFacade
     */
    public ExchangeApiFacade exchange() {
        return new ExchangeApiFacade(this.apiClient);
    }

    public static class Builder {
        private ClientParameter configuration;

        public Builder configuration(final ClientParameter value) {
            this.configuration = value;
            return this;
        }

        public PrivateApiClient build() {
            return new PrivateApiClient(this);
        }
    }
}
