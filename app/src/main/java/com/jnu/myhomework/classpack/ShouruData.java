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

    public void setName(String shouli_name){this.name = shouli_name;}
    public void setMoney(String shouli_money){this.money = shouli_money;}
    public void setTime(String shouli_time){this.time = shouli_time;}
    public void setReason(String shouli_reason){this.reason = shouli_reason;}
}
