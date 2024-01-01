package com.fxz.pojo;

public class NativeCipher {
    public native byte[] RC4Encrypt(byte[] data,byte[] key);
}

