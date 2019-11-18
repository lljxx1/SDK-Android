package com.meishu.sdk;

public interface DelegateChain extends AdDelegate{
    void setNext(DelegateChain next);

    DelegateChain getNext();

}
