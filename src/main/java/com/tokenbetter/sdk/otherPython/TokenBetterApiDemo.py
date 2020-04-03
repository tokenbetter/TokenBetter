import time
import hmac
import base64
import hashlib
import urllib.parse

import requests

TOKENBETTER = 'https://***.***.com'


class Tokenbetter:
    def __init__(self, config={}):
        self.apiKey = None
        self.secret = None
        self.symbol = None
        self.passphrase = None
        for key, value in config.items():
            setattr(self, key, value)

        self.headers = {
            'ACCESS-KEY': self.apiKey,
            'ACCESS-PASSPHRASE': self.passphrase,
            "Content-type": "application/json",
            'Cookie': 'locale=web',
            'x-locale': 'web'
        }

    def load_markets(self):
        url = TOKENBETTER + '/v1/common/symbols'
        response = self.http_get_request(url)
        return response

    def fetch_ticker(self, symbol):
        url = '/openapi/exchange/public/%s/ticker' % symbol.replace('/', '_')
        response = self.http_get_request(url)
        parsed = {
            'bid': float(response['buy']),
            'ask': float(response['sell']),
            'last': float(response['last'])
        }
        return parsed

    def fetch_order_book(self, symbol, limit=10):
        url = '/openapi/exchange/public/%s/orderBook' % symbol.replace('/', '_')
        response = self.http_get_request(url)
        parsed = {
            'asks': [[float(ask[0]), float(ask[1])] for ask in response['asks']],
            'bids': [[float(bid[0]), float(bid[1])] for bid in response['bids']],
            'symbol': symbol
        }
        return parsed

    def fetch_ohlcv(self, symbol, timeframe='1min', start_time=None, end_time=None):
        url = '/openapi/exchange/public/%s/candles' % symbol.replace('/', '_')
        params = {
            'interval': timeframe
        }
        if start_time is not None:
            params.update({'start_time': start_time})
        if end_time is not None:
            params.update({'end_time': end_time})
        params_str = '?'+urllib.parse.urlencode(params)
        response = self.api_get_request(url, params_str, params)
        parsed = {'data': []}
        for x in response:
            parsed['data'].append({
                "time": x[0],
                "open": float(x[3]),
                "high": float(x[2]),
                "low": float(x[1]),
                "close": float(x[4]),
                "volume": float(x[5])
            })
        parsed['data'].reverse()
        return parsed

    def fetch_balance(self):
        url = '/openapi/exchange/assets'
        res = self.api_get_request(url)
        parsed = dict()
        for code in res:
            parsed[code['symbol']] = {'used':float(code['hold']), 'free': float(code['available']), 'total': float(code['hold'])+float(code['available'])}
        return parsed

    def create_order(self, symbol, side, amount, price, is_limit='limit'):
        url = '/openapi/exchange/%s/orders' % symbol.replace('/', '_')
        # params = {
        #     'side': side,
        #     'systemOrderType': is_limit,
        #     # 'volume': str(amount),
        #     'volume': amount,
        #     'price': price,
        #     # 'price': str(price),
        #     'source': 'web'
        # }
        params_str = '{"side":"%s","price":%f,"volume":%f,"systemOrderType":"limit","source":"web"}' % (side, price, amount)
        res = self.api_post_request(url, params_str)
        return {'id': res}

    def create_limit_buy_order(self, symbol, amount, price):
        return self.create_order(symbol, 'buy', amount, price)

    def create_limit_sell_order(self, symbol, amount, price):
        return self.create_order(symbol, 'sell', amount, price)

    def cancel_order(self, order_id=None, symbol=None):
        symbol = symbol.replace('/', '_')
        url = '/openapi/exchange/%s/orders/%s' % (symbol, order_id)
        params_str = '{"code":"%s","orderId":"%s"}' % (symbol, order_id)
        res = self.api_delete_request(url, params_str)
        return res

    def fetch_order(self, order_id, symbol=None, offset=0, limit=20):
        pass

    def fetch_orders(self, symbol, limit=1000):
        url = '/openapi/exchange/orders'

        params = {
            'pairCode': symbol.replace('/', '_'),
            'page': 1,
            'pageSize': limit
        }
        params_str = '?' + urllib.parse.urlencode(params)

        response = self.api_get_request(url, params_str, params)
        pends = []
        for res in response:
            parsed = {
                "id": res["id"],
                "status": res['status'],
                "symbol": symbol,
                "side": res['side'],
                "type": 1 if res['side'] == 'buy' else 2,
                "price": float(res["entrustPrice"]),
                "amount": float(res["amount"]),
                "filled": float(res["dealAmount"]),
                "remaining": float(res["amount"]) - float(res["dealAmount"]),
                'addtime': res['createOn'],
                'average': res['averagePrice']
            }
            pends.append(parsed)
        return pends

    def http_get_request(self, url, headers=None, params=None, *args, **kwargs):
        url = TOKENBETTER + url
        if headers is not None:
            headers.update(self.headers)
        else:
            headers = self.headers
        res = requests.get(url, headers=headers, params=params)
        return res.json()

    def http_delete_request(self, url, headers={}, params_str='', *args, **kwargs):
        url = TOKENBETTER + url
        headers.update(self.headers)
        res = requests.delete(url, data=params_str, headers=headers)
        return res

    def http_post_request(self, url, headers={}, params_str='', *args, **kwargs):
        url = TOKENBETTER + url

        headers.update(self.headers)
        res = requests.post(url, data=params_str, headers=headers)
        return res.json()

    def api_get_request(self, url, params_str='', params=None):
        now_time = str(int(time.time() * 1000))
        to_sign = now_time + 'GET' + url + params_str
        headers = {
            'ACCESS-TIMESTAMP': now_time,
            'ACCESS-SIGN': self.sign(to_sign)
        }
        return self.http_get_request(url, headers, params)

    def api_post_request(self, url, params_str=''):
        now_time = str(int(time.time()*1000))
        to_sign = now_time+ 'POST'+ url + params_str
        headers = {
            'ACCESS-TIMESTAMP': now_time,
            'ACCESS-SIGN': self.sign(to_sign)
        }
        return self.http_post_request(url, headers, params_str)

    def api_delete_request(self, url, params_str='', params=None):
        now_time = str(int(time.time() * 1000))
        to_sign = now_time + 'DELETE' + url + params_str
        headers = {
            'ACCESS-TIMESTAMP': now_time,
            'ACCESS-SIGN': self.sign(to_sign)
        }
        return self.http_delete_request(url, headers, params_str)

    def sign(self, string_to_sign):
        encode_params = string_to_sign.encode(encoding='utf-8')
        digest = hmac.new(self.secret.encode(encoding='utf-8'), encode_params, digestmod=hashlib.sha256).digest()
        signature = base64.b64encode(digest)
        signature = signature.decode()
        return signature


if __name__ == '__main__':

    tokenbetter = Tokenbetter({
        'apiKey': 'xxx',
        'secret': 'xxx',
        'passphrase': 'xxx'
    })
    symbol = 'BTC/USDT'

    book = tokenbetter.fetch_order_book(symbol)
    print(book['bids'])
    print(book['asks'])

    # klines = tokenbetter.fetch_ohlcv(symbol)
    # for i in klines['data']:
    #     print(i)

    # ticker = tokenbetter.fetch_ticker(symbol)
    # print(ticker)
    # print(ticker['last'])

    # bal = tokenbetter.fetch_balance()
    # for k, v in bal.items():
    #     print(k, v)

    # order = tokenbetter.create_limit_buy_order(symbol, 10, 1000)
    # print(order)

    # pending_orders = tokenbetter.fetch_orders(symbol)
    #
    # pending_orders.sort(key=lambda x: x['price'])
    # buy_pends = list(filter(lambda x: x['type'] == 1, pending_orders))
    # buy_pends.reverse()
    # sell_pends = list(filter(lambda x: x['type'] == 2, pending_orders))
    # print(len(sell_pends))
    # for index, order in enumerate(sell_pends):
    #     print(index, order)
    #
    #     print(index, order)
    # print(len(buy_pends))
