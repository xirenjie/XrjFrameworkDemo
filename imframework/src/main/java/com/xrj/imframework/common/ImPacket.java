package com.xrj.imframework.common;

import com.xrj.imframework.bean.Command;

/**
 * Created by 袭人杰 on 2018/5/24.
 */
public class ImPacket {
    private static final long serialVersionUID = -4283971967100935982L;

    private byte version = 0;
    private byte mask = 0;

    public ImPacket(){}

    public ImPacket(Command command, byte[] body){
    }
    public ImPacket( byte[] body){
    }
    public byte getVersion() {
        return version;
    }
    public void setVersion(byte version) {
        this.version = version;
    }
    public byte getMask() {
        return mask;
    }
    public void setMask(byte mask) {
        this.mask = mask;
    }
}
