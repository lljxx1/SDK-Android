package com.meishu.sdk.domain;

public class ClickIdResponse {

    private Integer ret;
    private ClickIdInfo data;

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public ClickIdInfo getData() {
        return data;
    }

    public void setData(ClickIdInfo data) {
        this.data = data;
    }

    public class ClickIdInfo{
        private String dstlink;
        private String clickid;

        public String getDstlink() {
            return dstlink;
        }

        public void setDstlink(String dstlink) {
            this.dstlink = dstlink;
        }

        public String getClickid() {
            return clickid;
        }

        public void setClickid(String clickid) {
            this.clickid = clickid;
        }
    }
}
