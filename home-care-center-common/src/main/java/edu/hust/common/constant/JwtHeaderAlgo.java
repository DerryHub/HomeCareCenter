package edu.hust.common.constant;

/**
 * 单点登录的加密算法枚举
 * @author chain
 * @date 2020/9/4
 **/
public enum JwtHeaderAlgo {

    /**
     * 不可逆加密算法的特征是加密过程中不需要使用密钥，输入明文后由系统直接经过加密算法处理成密文，
     * 这种加密后的数据是无法被解密的，只有重新输入明文，并再次经过同样不可逆的加密算法处理，
     * 得到相同的加密密文并被系统重新识别后，才能真正解密。
     */
    SM3("sm3","国密3加密算法,其算法不可逆，类似于MD5"),
    SM4("sm4","国密4加密算法，对称加密"),
    AES("aes","AES加密算法，对称加密");

    private String code;

    private String details;

    JwtHeaderAlgo(String code, String details) {
        this.code = code;
        this.details = details;
    }
}
