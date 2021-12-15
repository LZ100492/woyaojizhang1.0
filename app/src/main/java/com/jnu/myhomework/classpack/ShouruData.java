package com.jnu.myhomework.classpack;

import java.io.Serializable;

public class ShouruData implements Serializable {
    private int ShouruImageId;
    private String time;
    private String name;
    private String money;
    private String reason;

    public ShouruData(int ShouliImageId, String time, String name, String money, String reason){
        this.ShouruImageId = ShouliImageId;
        this.time = time;
        this.name = name;
        this.money = money;
        this.reason = reason;
    }

    public int getShouruImageId()
    {
        return ShouruImageId;
    }

    public String getTime()
    {
        return time;
    }

    public String getName()
    {
        return name;
    }

    public String getMoney()
    {
        return money;
    }

    public String getReason(){return reason;}

    public void setName(String shouru_name){this.name = shouru_name;}
    public void setMoney(String shouru_money){this.money = shouru_money;}
    public void setTime(String shouru_time){this.time = shouru_time;}
    public void setReason(String shouru_reason){this.reason = shouru_reason;}
}
