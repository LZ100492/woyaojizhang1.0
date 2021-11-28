package com.jnu.myhomework.classpack;

import com.jnu.myhomework.AddZhichuData;

import java.io.Serializable;

public class ZhichuData extends AddZhichuData implements Serializable {
    private int ZhichuImageId;
    private String time;
    private String name;
    private String money;
    private String reason;

    public ZhichuData(int ZhichuImageId, String time, String name, String money, String reason){
        this.ZhichuImageId = ZhichuImageId;
        this.time = time;
        this.name = name;
        this.money = money;
        this.reason = reason;
    }

    public int getZhichuImageId()
    {
        return ZhichuImageId;
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

    public void setName(String suili_name){this.name = suili_name;}
    public void setMoney(String suili_money){this.money = suili_money;}
    public void setTime(String suili_time){this.time = suili_time;}
    public void setReason(String shouli_reason){this.reason = shouli_reason;}
}