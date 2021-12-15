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

    public void setName(String zhichu_name){this.name = zhichu_name;}
    public void setMoney(String zhichu_money){this.money = zhichu_money;}
    public void setTime(String zhichu_time){this.time = zhichu_time;}
    public void setReason(String shouru_reason){this.reason = shouru_reason;}
}