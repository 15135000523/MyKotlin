package com.example.mykotlin.ui.youku.main;

import java.util.List;

public class OrderDetailsBean {


    /**
     * qxdbh : 工单编号:2020092002202348
     * gwgdbh : 国网工单编号:2020092002202348
     * yhbh : 户号:0100343706
     * hm : 户名:
     * bxr : 联系人:连英成
     * lxdh : 联系人电话:17835128363
     * gzdz : 故障地址:山西省太原市古交市桃园街道千佛路居委会大川西路57#
     * gzms : 受理内容:【一户无电】【继电器状态查询无字段】客户报修[居民]一户无电，[室内、表下总开关未跳闸、电表显示看不到]， 初步判断为非内部故障，请尽快核实处理。【短信推广】【话术推广】
     * ssds : 所属区县:国网太原供电公司
     * sstgname : 所属台区:
     * xlname : 线路名称:
     * sfmg : 敏感客户:否
     * zykh : 重要客户:
     * ljdh : 累计拨打95598电话:
     * sfnc : 城农网标志:农电
     * khbh : 客户编号:0100343706
     * khbiaoh :
     * tgname : 台区名称:
     * kvmc : 0.4kV线路名称:dfgdfg
     * kvxlmc : 10kV线路名称:
     * gdsmc : 供电所名称:
     * sfwb : 抢修队伍是否外包:
     * qxdwmc : 抢修队伍名称:
     * sbmc : 故障设备名称:sdfsdfsdf
     * spjl : 视频记录:
     * khjlxm : 姓名:
     * khjldh : 电话:
     * jdsj : 接单时间:2020-09-20 16:03:49
     * ddxcsj : 到达时间:2020-09-20 17:30:18
     * xcfl : 现场分类:特殊边远山区
     * sfcs : 是否超时:
     * cssm : 超时说明:145165161
     * clxx : 车辆信息:
     * xckcsj : 勘查时间:
     * sfgz : 0.4kV及以下非电网故障:是
     * sfkvmc : 0.4kV线路名称:dfgdfg
     * gzmc : 故障名称:已知停电_卡表预付费不足_客户原因_客户设备问题
     * gzxx : 故障现象:
     * gzlb : 故障类别:dfgdg
     * tdfw : 停电范围:
     * sfjj : 紧急程度:是
     * yjxfsj : 预计修复时间:2020-09-21 23:44:42
     * gzyj : 一级分类:
     * gzej : 二级分类:
     * gzsj : 三级分类:
     * gzmsyy : 故障描述:已知停电_卡表预付费不足_客户原因_客户设备问题
     * gzsbcq : 故障设备产权:供电企业资产
     * gzyydl : 故障原因大类:客户原因
     * gzyyxl : 故障原因小类:客户设备问题
     * gznr : 工作内容:dfgdfg
     * xfwcsj : 记录修复时间:2020-09-21 23:44:48
     * xfnr : 修复内容:
     * khyj : 客户意见:
     * tdxz : 停电性质:故障停电
     * dydj : 电压等级:交流10kV
     * gzbw : 故障部位:fgdfg
     * tuidyy : 退单原因:
     * jdr :
     * path : ["https://s2.gjdwllgdgs.com:30001/pwkshApp/../tdyy/2020092002202348/2020092002202348_1600698296206311.jpeg"]
     * gzppath : ["https://s2.gjdwllgdgs.com:30001/pwkshApp/../gzgd/2020092002202348/2020092002202348_1600698292791441.jpeg"]
     * spqb : 市配抢班:
     * xpqb : 县配抢班:
     * sycgzz : 市远程工作站:
     * qxryxx : 抢修人员:121515471-(工单数量:5)
     * bzfzr : 班组负责人:
     * bzfzrdh : 班组负责人电话:
     * mac : 处理手机MAC:
     */

    private String qxdbh;
    private String gwgdbh;
    private String yhbh;
    private String hm;
    private String bxr;
    private String lxdh;
    private String gzdz;
    private String gzms;
    private String ssds;
    private String sstgname;
    private String xlname;
    private String sfmg;
    private String zykh;
    private String ljdh;
    private String sfnc;
    private String khbh;
    private String khbiaoh;
    private String tgname;
    private String kvmc;
    private String kvxlmc;
    private String gdsmc;
    private String sfwb;
    private String qxdwmc;
    private String sbmc;
    private String spjl;
    private String khjlxm;
    private String khjldh;
    private String jdsj;
    private String ddxcsj;
    private String xcfl;
    private String sfcs;
    private String cssm;
    private String clxx;
    private String xckcsj;
    private String sfgz;
    private String sfkvmc;
    private String gzmc;
    private String gzxx;
    private String gzlb;
    private String tdfw;
    private String sfjj;
    private String yjxfsj;
    private String gzyj;
    private String gzej;
    private String gzsj;
    private String gzmsyy;
    private String gzsbcq;
    private String gzyydl;
    private String gzyyxl;
    private String gznr;
    private String xfwcsj;
    private String xfnr;
    private String khyj;
    private String tdxz;
    private String dydj;
    private String gzbw;
    private String tuidyy;
    private String jdr;
    private String spqb;
    private String xpqb;
    private String sycgzz;
    private String qxryxx;
    private String bzfzr;
    private String bzfzrdh;
    private String mac;
    private List<String> path;
    private List<String> gzppath;

    private String subString(String str){
        String[] strs = str.split(":",2);
        return strs.length==2?strs[1]:"";
    }
    public String getQxdbh() {
        return subString(qxdbh);
    }

    public void setQxdbh(String qxdbh) {
        this.qxdbh = qxdbh;
    }

    public String getGwgdbh() {
        return subString(gwgdbh);
    }

    public void setGwgdbh(String gwgdbh) {
        this.gwgdbh = gwgdbh;
    }

    public String getYhbh() {
        return subString(yhbh);
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getHm() {
        return subString(hm);
    }

    public void setHm(String hm) {
        this.hm = hm;
    }

    public String getBxr() {
        return subString(bxr);
    }

    public void setBxr(String bxr) {
        this.bxr = bxr;
    }

    public String getLxdh() {
        return subString(lxdh);
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getGzdz() {
        return subString(gzdz);
    }

    public void setGzdz(String gzdz) {
        this.gzdz = gzdz;
    }

    public String getGzms() {
        return subString(gzms);
    }

    public void setGzms(String gzms) {
        this.gzms = gzms;
    }

    public String getSsds() {
        return subString(ssds);
    }

    public void setSsds(String ssds) {
        this.ssds = ssds;
    }

    public String getSstgname() {
        return subString(sstgname);
    }

    public void setSstgname(String sstgname) {
        this.sstgname = sstgname;
    }

    public String getXlname() {
        return subString(xlname);
    }

    public void setXlname(String xlname) {
        this.xlname = xlname;
    }

    public String getSfmg() {
        return subString(sfmg);
    }

    public void setSfmg(String sfmg) {
        this.sfmg = sfmg;
    }

    public String getZykh() {
        return subString(zykh);
    }

    public void setZykh(String zykh) {
        this.zykh = zykh;
    }

    public String getLjdh() {
        return subString(ljdh);
    }

    public void setLjdh(String ljdh) {
        this.ljdh = ljdh;
    }

    public String getSfnc() {
        return subString(sfnc);
    }

    public void setSfnc(String sfnc) {
        this.sfnc = sfnc;
    }

    public String getKhbh() {
        return subString(khbh);
    }

    public void setKhbh(String khbh) {
        this.khbh = khbh;
    }

    public String getKhbiaoh() {
        return subString(khbiaoh);
    }

    public void setKhbiaoh(String khbiaoh) {
        this.khbiaoh = khbiaoh;
    }

    public String getTgname() {
        return subString(tgname);
    }

    public void setTgname(String tgname) {
        this.tgname = tgname;
    }

    public String getKvmc() {
        return subString(kvmc);
    }

    public void setKvmc(String kvmc) {
        this.kvmc = kvmc;
    }

    public String getKvxlmc() {
        return subString(kvxlmc);
    }

    public void setKvxlmc(String kvxlmc) {
        this.kvxlmc = kvxlmc;
    }

    public String getGdsmc() {
        return subString(gdsmc);
    }

    public void setGdsmc(String gdsmc) {
        this.gdsmc = gdsmc;
    }

    public String getSfwb() {
        return subString(sfwb);
    }

    public void setSfwb(String sfwb) {
        this.sfwb = sfwb;
    }

    public String getQxdwmc() {
        return subString(qxdwmc);
    }

    public void setQxdwmc(String qxdwmc) {
        this.qxdwmc = qxdwmc;
    }

    public String getSbmc() {
        return subString(sbmc);
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public String getSpjl() {
        return subString(spjl);
    }

    public void setSpjl(String spjl) {
        this.spjl = spjl;
    }

    public String getKhjlxm() {
        return subString(khjlxm);
    }

    public void setKhjlxm(String khjlxm) {
        this.khjlxm = khjlxm;
    }

    public String getKhjldh() {
        return subString(khjldh);
    }

    public void setKhjldh(String khjldh) {
        this.khjldh = khjldh;
    }

    public String getJdsj() {
        return subString(jdsj);
    }

    public void setJdsj(String jdsj) {
        this.jdsj = jdsj;
    }

    public String getDdxcsj() {
        return subString(ddxcsj);
    }

    public void setDdxcsj(String ddxcsj) {
        this.ddxcsj = ddxcsj;
    }

    public String getXcfl() {
        return subString(xcfl);
    }

    public void setXcfl(String xcfl) {
        this.xcfl = xcfl;
    }

    public String getSfcs() {
        return subString(sfcs);
    }

    public void setSfcs(String sfcs) {
        this.sfcs = sfcs;
    }

    public String getCssm() {
        return subString(cssm);
    }

    public void setCssm(String cssm) {
        this.cssm = cssm;
    }

    public String getClxx() {
        return subString(clxx);
    }

    public void setClxx(String clxx) {
        this.clxx = clxx;
    }

    public String getXckcsj() {
        return subString(xckcsj);
    }

    public void setXckcsj(String xckcsj) {
        this.xckcsj = xckcsj;
    }

    public String getSfgz() {
        return subString(sfgz);
    }

    public void setSfgz(String sfgz) {
        this.sfgz = sfgz;
    }

    public String getSfkvmc() {
        return subString(sfkvmc);
    }

    public void setSfkvmc(String sfkvmc) {
        this.sfkvmc = sfkvmc;
    }

    public String getGzmc() {
        return subString(gzmc);
    }

    public void setGzmc(String gzmc) {
        this.gzmc = gzmc;
    }

    public String getGzxx() {
        return subString(gzxx);
    }

    public void setGzxx(String gzxx) {
        this.gzxx = gzxx;
    }

    public String getGzlb() {
        return subString(gzlb);
    }

    public void setGzlb(String gzlb) {
        this.gzlb = gzlb;
    }

    public String getTdfw() {
        return subString(tdfw);
    }

    public void setTdfw(String tdfw) {
        this.tdfw = tdfw;
    }

    public String getSfjj() {
        return subString(sfjj);
    }

    public void setSfjj(String sfjj) {
        this.sfjj = sfjj;
    }

    public String getYjxfsj() {
        return subString(yjxfsj);
    }

    public void setYjxfsj(String yjxfsj) {
        this.yjxfsj = yjxfsj;
    }

    public String getGzyj() {
        return subString(gzyj);
    }

    public void setGzyj(String gzyj) {
        this.gzyj = gzyj;
    }

    public String getGzej() {
        return subString(gzej);
    }

    public void setGzej(String gzej) {
        this.gzej = gzej;
    }

    public String getGzsj() {
        return subString(gzsj);
    }

    public void setGzsj(String gzsj) {
        this.gzsj = gzsj;
    }

    public String getGzmsyy() {
        return subString(gzmsyy);
    }

    public void setGzmsyy(String gzmsyy) {
        this.gzmsyy = gzmsyy;
    }

    public String getGzsbcq() {
        return subString(gzsbcq);
    }

    public void setGzsbcq(String gzsbcq) {
        this.gzsbcq = gzsbcq;
    }

    public String getGzyydl() {
        return subString(gzyydl);
    }

    public void setGzyydl(String gzyydl) {
        this.gzyydl = gzyydl;
    }

    public String getGzyyxl() {
        return subString(gzyyxl);
    }

    public void setGzyyxl(String gzyyxl) {
        this.gzyyxl = gzyyxl;
    }

    public String getGznr() {
        return subString(gznr);
    }

    public void setGznr(String gznr) {
        this.gznr = gznr;
    }

    public String getXfwcsj() {
        return subString(xfwcsj);
    }

    public void setXfwcsj(String xfwcsj) {
        this.xfwcsj = xfwcsj;
    }

    public String getXfnr() {
        return subString(xfnr);
    }

    public void setXfnr(String xfnr) {
        this.xfnr = xfnr;
    }

    public String getKhyj() {
        return subString(khyj);
    }

    public void setKhyj(String khyj) {
        this.khyj = khyj;
    }

    public String getTdxz() {
        return subString(tdxz);
    }

    public void setTdxz(String tdxz) {
        this.tdxz = tdxz;
    }

    public String getDydj() {
        return subString(dydj);
    }

    public void setDydj(String dydj) {
        this.dydj = dydj;
    }

    public String getGzbw() {
        return subString(gzbw);
    }

    public void setGzbw(String gzbw) {
        this.gzbw = gzbw;
    }

    public String getTuidyy() {
        return subString(tuidyy);
    }

    public void setTuidyy(String tuidyy) {
        this.tuidyy = tuidyy;
    }

    public String getJdr() {
        return subString(jdr);
    }

    public void setJdr(String jdr) {
        this.jdr = jdr;
    }

    public String getSpqb() {
        return subString(spqb);
    }

    public void setSpqb(String spqb) {
        this.spqb = spqb;
    }

    public String getXpqb() {
        return subString(xpqb);
    }

    public void setXpqb(String xpqb) {
        this.xpqb = xpqb;
    }

    public String getSycgzz() {
        return subString(sycgzz);
    }

    public void setSycgzz(String sycgzz) {
        this.sycgzz = sycgzz;
    }

    public String getQxryxx() {
        return subString(qxryxx);
    }

    public void setQxryxx(String qxryxx) {
        this.qxryxx = qxryxx;
    }

    public String getBzfzr() {
        return subString(bzfzr);
    }

    public void setBzfzr(String bzfzr) {
        this.bzfzr = bzfzr;
    }

    public String getBzfzrdh() {
        return subString(bzfzrdh);
    }

    public void setBzfzrdh(String bzfzrdh) {
        this.bzfzrdh = bzfzrdh;
    }

    public String getMac() {
        return subString(mac);
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<String> getGzppath() {
        return gzppath;
    }

    public void setGzppath(List<String> gzppath) {
        this.gzppath = gzppath;
    }
}
