package hd.vo.pub.tools;

import java.sql.Types;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IColumnMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

import nc.pub.smart.metadata.Field;

/**
 * �������ݼӹ�����
 * 
 * @since 6.0
 * @version 2014��12��23��15:11:00
 * @author 
 */
public class ReportDataUtil {	
  public ReportDataUtil() {
    // �����಻��Ҫ���췽��
  }

  /**
   * ����UFDouble���ͱ����ֶ�
   * 
   * @param key
   * @return
   */
  public static Field createDoubleField(String key) {
    Field field = new Field();
    field.setDbColumnType(Types.DECIMAL);
    field.setFldname(key);
    field.setPrecision(28);
    field.setScale(8);
    return field;
  }

  /**
   * ����Ԫ���ݾ������ʹ�����Ӧ���͵ı����ֶ�
   * 
   * @param viewmeta
   * @param key
   * @return
   */
  public static Field createFiled(IDataViewMeta viewmeta, String key) {
    IAttributeMeta attrmeta = viewmeta.getAttribute(key);
    IColumnMeta colmeta = attrmeta.getColumn();
    Field field = new Field();
    field.setDbColumnType(colmeta.getSqlType());
    field.setFldname(colmeta.getName());
    field.setPrecision(colmeta.getLength());
    field.setScale(colmeta.getPrecision());
    return field;
  }

  /**
   * ����Integer���ͱ����ֶ�
   * 
   * @param key
   * @return
   */
  public static Field createIntegerFiled(String key) {
    Field field = new Field();
    field.setDbColumnType(Types.INTEGER);
    field.setFldname(key);
    return field;
  }

  /**
   * ����varchar���ͱ����ֶ�
   * 
   * @param key
   * @return
   */
  public static Field createStringFiled(String key) {
    Field field = new Field();
    field.setDbColumnType(Types.VARCHAR);
    field.setFldname(key);
    return field;
  }
}
