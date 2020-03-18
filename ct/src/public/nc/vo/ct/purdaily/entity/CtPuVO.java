package nc.vo.ct.purdaily.entity;

import nc.vo.ct.entity.CtAbstractVO;
import nc.vo.ct.enumeration.CtEntity;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.pubapp.res.NCModule;

/**
 * <b> �ڴ˴���Ҫ��������Ĺ��� </b>
 * <p>
 * �ɹ���ͬ����VO
 * </p>
 * ��������:2010-03-18 19:14:49
 * 
 * @author
 * @version lizhengb
 */

public class CtPuVO extends CtAbstractVO {
  /** �Ƿ��������� */
  public static final String BBRACKETORDER = "bbracketorder";

  /** Э�鹩�� */
  public static final String BPROTSUPPLY = "bprotsupply";

  /** ���� */
  public static final String BPUBLISH = "bpublish";

  /** �Ƿ���ʾ���°� */
  public static final String BSHOWLATEST = "bshowlatest";

  /** ��ԴЭͬ��ͬ */
  public static final String BSRCECMCT = "bsrcecmct";

  // ��Ӧ��
  public static final String CVENDORID = "cvendorid";

  /** �۸����� */
  public static final String IPRICETYPE = "ipricetype";

  /** ��Ӧ״̬ */
  public static final String IRESPSTATUS = "irespstatus";

  /** ���״̬ */
  public static final String MODIFYSTATUS = "modifystatus";

  // �ɹ���ͬid
  public static final String PK_CT_PU = "pk_ct_pu";

  /** ������ */
  public static final String PK_PUBPSN = "pk_pubpsn";

  /** �ɹ���˾ */
  public static final String PK_PURCORP = "pk_purcorp";

  /** ��Ӧ�� */
  public static final String PK_RESPPSN = "pk_resppsn";

  /** ����ʱ�� */
  public static final String TPUBTIME = "tpubtime";

  /** ��Ӧʱ�� */
  public static final String TRESPTIME = "tresptime";

  /** �ܾ�ԭ�� */
  public static final String VREASON = "vreason";

  // modify by liangchen1 �ۻ���ͬ�����Ч�Լ���������������

  private static final long serialVersionUID = -6057905148775268085L;

  /**
   * ����Ĭ�Ϸ�ʽ����������. ��������:2010-03-18 19:14:49
   */
  public CtPuVO() {
    super();
  }

  /** �������� getter ���� */
  public UFBoolean getBbracketOrder() {
    return (UFBoolean) this.getAttributeValue(CtPuVO.BBRACKETORDER);
  }

  /** Э�鹩�� getter ���� */
  public UFBoolean getBprotsupply() {
    return (UFBoolean) this.getAttributeValue(CtPuVO.BPROTSUPPLY);
  }

  /** ���� getter ���� */
  public UFBoolean getBpublish() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BPUBLISH);
  }

  @Override
  public UFBoolean getBsc() {
    return (UFBoolean) this.getAttributeValue(CtAbstractVO.BSC);
  }

  /** �Ƿ���ʾ���°� getter ���� */
  public UFBoolean getBshowLatest() {
    return (UFBoolean) this.getAttributeValue(CtPuVO.BSHOWLATEST);
  }

  /** ��ԴЭͬ��ͬ getter ���� */
  public UFBoolean getBsrcecmct() {
    return (UFBoolean) this.getAttributeValue(CtPuVO.BSRCECMCT);
  }

  public String getCvendorid() {
    return (String) this.getAttributeValue(CtPuVO.CVENDORID);
  }

  /** �۸����� getter ���� */
  public Integer getIpricetype() {
    return (Integer) this.getAttributeValue(CtPuVO.IPRICETYPE);
  }

  /** ��Ӧ״̬ getter ���� */
  public Integer getIrespstatus() {
    return (Integer) this.getAttributeValue(CtPuVO.IRESPSTATUS);
  }

  /**
   * �õ�Ԫ���ݵ�ʵ�����ԣ����С�ct.ct_pu��, ct��ʾ����������ռ䣬�����ڳ�������˵�� ����������ռ���Ǻ�ͬ����ct��ct_pu
   * ��ʾ��ʵ������ݿ������
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(
            NCModule.CT.getName().toLowerCase() + "." + CtEntity.ct_pu.name());
    return meta;
  }

  // modify by liangchen1 �ۻ���ͬ�����Ч�Լ���������������
  /** ���״̬ getter ���� */
  public Integer getModifyStatus() {
    return (Integer) this.getAttributeValue(CtPuVO.MODIFYSTATUS);
  }

  public String getPk_ct_pu() {
    return (String) this.getAttributeValue(CtPuVO.PK_CT_PU);
  }

  /** ������ getter ���� */
  public String getPk_pubpsn() {
    return (String) this.getAttributeValue(CtPuVO.PK_PUBPSN);
  }

  /** �ɹ���˾ getter ���� */
  public String getPk_purcorp() {
    return (String) this.getAttributeValue(CtPuVO.PK_PURCORP);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_resppsn() {
    return (String) this.getAttributeValue(CtPuVO.PK_RESPPSN);
  }

  /** �������� getter ���� */
  public UFDateTime getTpubtime() {
    return (UFDateTime) this.getAttributeValue(CtPuVO.TPUBTIME);
  }

  /** ��Ӧ���� getter ���� */
  public UFDateTime getTresptime() {
    return (UFDateTime) this.getAttributeValue(CtPuVO.TRESPTIME);
  }

  /** �ܾ�/������� getter ���� */
  public String getVreason() {
    return (String) this.getAttributeValue(CtPuVO.VREASON);
  }

  /** �������� Setter ���� */
  public void setBbracketOrder(UFBoolean bbracketorder) {
    this.setAttributeValue(CtPuVO.BBRACKETORDER, bbracketorder);
  }

  /** Э�鹩�� setter ���� */
  public void setBprotsupply(UFBoolean bprotsupply) {
    this.setAttributeValue(CtPuVO.BPROTSUPPLY, bprotsupply);
  }

  /** ���� setter ���� */
  public void setBpublish(UFBoolean bpublish) {
    this.setAttributeValue(OrderHeaderVO.BPUBLISH, bpublish);
  }

  @Override
  public void setBsc(UFBoolean bsc) {
    this.setAttributeValue(CtAbstractVO.BSC, bsc);
  }

  /** �Ƿ���ʾ���°� setter ���� */
  public void setBshowLatest(UFBoolean bshowlatest) {
    this.setAttributeValue(CtPuVO.BSHOWLATEST, bshowlatest);
  }

  /** ��ԴЭͬ��ͬ setter ���� */
  public void setBsrcecmct(UFBoolean bsrcecmct) {
    this.setAttributeValue(CtPuVO.BSRCECMCT, bsrcecmct);
  }

  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(CtPuVO.CVENDORID, cvendorid);
  }

  /** �۸����� setter ���� */
  public void setIpricetype(Integer ipricetype) {
    this.setAttributeValue(CtPuVO.IPRICETYPE, ipricetype);
  }

  /** ��Ӧ״̬ setter ���� */
  public void setIrespstatus(Integer irespstatus) {
    this.setAttributeValue(CtPuVO.IRESPSTATUS, irespstatus);
  }

  /** ���״̬ setter ���� */
  public void setModifyStatus(Integer modifystatus) {
    this.setAttributeValue(CtPuVO.MODIFYSTATUS, modifystatus);
  }

  public void setPk_ct_pu(String pk_ct_pu) {
    this.setAttributeValue(CtPuVO.PK_CT_PU, pk_ct_pu);
  }

  /** ������ setter ���� */
  public void setPk_pubpsn(String pk_pubpsn) {
    this.setAttributeValue(CtPuVO.PK_PUBPSN, pk_pubpsn);
  }

  /** �ɹ���˾ setter ���� */
  public void setPk_purcorp(String pk_purcorp) {
    this.setAttributeValue(CtPuVO.PK_PURCORP, pk_purcorp);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_resppsn(String pk_resppsn) {
    this.setAttributeValue(CtPuVO.PK_RESPPSN, pk_resppsn);
  }

  /** �������� setter ���� */
  public void setTpubtime(UFDateTime tpubtime) {
    this.setAttributeValue(CtPuVO.TPUBTIME, tpubtime);
  }

  /** ��Ӧ���� setter ���� */
  public void setTresptime(UFDateTime tresptime) {
    this.setAttributeValue(CtPuVO.TRESPTIME, tresptime);
  }

  /** �ܾ�/������� setter ���� */
  public void setVreason(String vreason) {
    this.setAttributeValue(CtPuVO.VREASON, vreason);
  }

}
