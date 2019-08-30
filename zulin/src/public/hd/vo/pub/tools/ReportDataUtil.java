package hd.vo.pub.tools;

import java.sql.Types;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IColumnMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

import nc.pub.smart.metadata.Field;

/**
 * 报表数据加工工具
 * 
 * @since 6.0
 * @version 2014年12月23日15:11:00
 * @author 
 */
public class ReportDataUtil {	
  public ReportDataUtil() {
    // 工具类不需要构造方法
  }

  /**
   * 创建UFDouble类型报表字段
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
   * 根据元数据具体类型创建对应类型的报表字段
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
   * 创建Integer类型报表字段
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
   * 创建varchar类型报表字段
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
