# http协议

## https协议

概念:https=http+ssl 是对http通信内容加密 是http的安全版本,主要使用TLS/SSL加密协议算法

作用: 1. 内容加密 建立一个信息安全通道,来保证数据传输的安全 2. 身份认证,确认网站的真实性 3. 数据完整性,防止内容被第三方冒充或篡改

## SSL协议

一种标准协议，用于加密浏览器和服务器之间的通信。它允许通过Internet安全轻松地传输账号密码、银行卡、手机号等私密信息。

SSL证书就是遵守SSL协议，由受信任的CA机构颁发的数字证书。

SSL/TLS协议

两种加密算法:

* 对称加密:通信双方使用**相同的密钥**进行加密。特点是加密速度快，但是缺点是需要保护好密钥，如果密钥泄露的话，那么加密就会被别人破解。常见的对称加密有AES，DES算法。
* 非对称加密:两个密钥：公钥(Public Key)和私钥(Private Key)

## https传输过程

![http传输过程](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy8yMDUwMjAzLTJhYTQyNTg5ZDY4YmMxODYucG5n?x-oss-process=image/format,png)

主要分为两个阶段

* 使用非对称加密 进行证书验证
* 使用对称加密进行数据传输

在证书验证阶段，使用非对称加密，需要公钥和私钥，假如浏览器的公钥泄漏了，我们还是能够确保随机数的安全，因为加密的数据只有用私钥才能解密。这样能最大程度确保随机数的安全。

在内容传输阶段，使用对称机密，可以大大提高加解密的效率。另一方面一对公私钥只能实现单向的加解密。只有服务端保存了私钥。如果使用非对称机密，相当于客户端必须有自己的私钥，这样设计的话，每个客户端都有自己的私钥，这很明显是不合理的，因为私钥是需要申请的。

## https不能杜绝中间人攻击

![中间人攻击](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy8yMDUwMjAzLTM1ZjE1YzFhNjllZTZiMDYucG5n?x-oss-process=image/format,png)




## 参考资料

- [https 真的安全吗，可以抓包吗，如何防止抓包吗](https://blog.csdn.net/gdutxiaoxu/article/details/107393249)