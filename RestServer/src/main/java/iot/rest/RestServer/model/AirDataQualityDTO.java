package iot.rest.RestServer.model;


import java.sql.Time;
import java.util.Date;

public class AirDataQualityDTO {
    private Date date;
    private Time time;
    private float co_gt;
    private int pt08_s1_co;
    private int nmhc_gt;
    private float c6h6_gt;
    private int pt08_s2_nmhc;
    private int nox_gt;
    private int pt08_s3_nox;
    private int no2_gt;
    private int pt08_s4_no2;
    private int pt08_s5_o3;
    private float t;
    private float rh;
    private float ah;
    private int id;



    public float getCo_gt() {
        return co_gt;
    }

    public void setCo_gt(float co_gt) {
        this.co_gt = co_gt;
    }

    public int getPt08_s1_co() {
        return pt08_s1_co;
    }

    public void setPt08_s1_co(int pt08_s1_co) {
        this.pt08_s1_co = pt08_s1_co;
    }

    public int getNmhc_gt() {
        return nmhc_gt;
    }

    public void setNmhc_gt(int nmhc_gt) {
        this.nmhc_gt = nmhc_gt;
    }

    public float getC6h6_gt() {
        return c6h6_gt;
    }

    public void setC6h6_gt(float c6h6_gt) {
        this.c6h6_gt = c6h6_gt;
    }

    public int getPt08_s2_nmhc() {
        return pt08_s2_nmhc;
    }

    public void setPt08_s2_nmhc(int pt08_s2_nmhc) {
        this.pt08_s2_nmhc = pt08_s2_nmhc;
    }

    public int getNox_gt() {
        return nox_gt;
    }

    public void setNox_gt(int nox_gt) {
        this.nox_gt = nox_gt;
    }

    public int getPt08_s3_nox() {
        return pt08_s3_nox;
    }

    public void setPt08_s3_nox(int pt08_s3_nox) {
        this.pt08_s3_nox = pt08_s3_nox;
    }

    public int getNo2_gt() {
        return no2_gt;
    }

    public void setNo2_gt(int no2_gt) {
        this.no2_gt = no2_gt;
    }

    public int getPt08_s4_no2() {
        return pt08_s4_no2;
    }

    public void setPt08_s4_no2(int pt08_s4_no2) {
        this.pt08_s4_no2 = pt08_s4_no2;
    }

    public int getPt08_s5_o3() {
        return pt08_s5_o3;
    }

    public void setPt08_s5_o3(int pt08_s5_o3) {
        this.pt08_s5_o3 = pt08_s5_o3;
    }

    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public float getRh() {
        return rh;
    }

    public void setRh(float rh) {
        this.rh = rh;
    }

    public float getAh() {
        return ah;
    }

    public void setAh(float ah) {
        this.ah = ah;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
