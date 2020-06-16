package com.github.ontio.ontid;

import com.alibaba.fastjson.JSON;
import com.github.ontio.crypto.Digest;

public class VerifiablePresentation {
    public String[] context;
    public String id;
    public String[] type;
    public VerifiableCredential[] verifiableCredential;
    public String holder; // holder may not use
    public Proof[] proof;

    public byte[] genNeedSignData() {
        String id = this.id;
        Proof[] proofs = this.proof;
        this.id = "";
        this.proof = null;
        String jsonStr = JSON.toJSONString(this);
        this.id = id;
        this.proof = proofs;
        return Digest.hash256(jsonStr.getBytes());
    }
}