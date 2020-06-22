package hd.vo.pub.tools;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class PuPubVO {
	
	/**
	 * 将对象 转换为字符串， 并且去空格
	 */
	public static String getString_TrimZeroLenAsNull(Object value) {
		  if ( value==null || value.toString().trim().length()==0 ) {
		    return  null ;
		  }
		  return  value.toString().trim() ;
		}

	public static UFDate getUFDate(Object value) {
		  return getUFDate(value, true);
		}
	
	public static UFDate getUFDate(Object value, boolean isParse) {
		  if ( value == null || value.toString().trim().equals("") ){
		    return  null ;
		  }else if(value  instanceof  UFDate){
		    return  (UFDate)value ;
		  }else{
		    return  new UFDate(value.toString().trim(),isParse);
		  }
	}
	
	public static UFDouble getUFDouble_NullAsZero(Object  value) {
	  if ( value == null || value.toString().trim().equals("") ){
	    return  UFDouble.ZERO_DBL ;
	  }else if(value  instanceof  UFDouble){
	    return  (UFDouble)value ;
	  }else if(value  instanceof  BigDecimal){
	    return  new UFDouble((BigDecimal)value) ;
	  }else{
	    return  new UFDouble(value.toString().trim());
	  }
	}

	public static UFDouble getUFDouble_ValueAsValue(double  dValue) {
	  if ( dValue==0 ){
	    return  UFDouble.ZERO_DBL ;
	  }else{
	    return  new UFDouble(dValue) ;
	  }
	}

	public static UFDouble getUFDouble_ValueAsValue(Object  value) {
	  if ( value == null || value.toString().trim().equals("") ){
	    return  null ;
	  }else if(value  instanceof  UFDouble){
	    return  (UFDouble)value ;
	  }else if(value  instanceof  BigDecimal){
	    return  new UFDouble((BigDecimal)value) ;
	  }else{
	    return  new UFDouble(value.toString().trim());
	  }

	}
	
	public static UFDouble getUFDouble_ZeroAsNull(double  dValue) {
	  if ( dValue==0 ){
	    return  null ;
	  }else{
	    return  new UFDouble(dValue) ;
	  }
	}
	
	public static UFDouble getUFDouble_ZeroAsNull(Object  value) {
	  UFDouble  dValue = getUFDouble_NullAsZero(value) ;
	  if ( dValue.compareTo(UFDouble.ZERO_DBL)==0 ) {
	    return  null ;
	  }
	  return  dValue ;
	}
	
	public static final Integer ZERO_INTEGER = new Integer(0);
	public static final Integer	ONE_INTEGER = new Integer(1);
	
	public static Integer getInteger_NullAs(Object value,Integer iDefaultValue) {
		  if ( value == null || value.toString().trim().equals("") ){
		    if (iDefaultValue.equals(PuPubVO.ZERO_INTEGER)) {
		      return PuPubVO.ZERO_INTEGER ;
		    }else if (iDefaultValue.equals(PuPubVO.ONE_INTEGER)) {
		      return PuPubVO.ONE_INTEGER ;
		    }else{
		      return iDefaultValue;
		    }
		  }else if(value instanceof Integer){
		    return (Integer)value ;
		  }else{
			  String valueNew = value.toString().trim();
			  if (valueNew.indexOf(".") >= 0) {
				  String[] valueNewSplit = valueNew.split("\\.");
				  if (valueNewSplit!=null && valueNewSplit.length > 0 ) {
					  valueNew = valueNewSplit[0];
				  }
			  }
		    return new Integer(valueNew);
		  }
		}
	
	public static UFBoolean getUFBoolean_NullAs(Object value, UFBoolean bDefaultValue)
    {
        if(value == null || value.toString().trim().equals(""))
            return bDefaultValue;
        if(value instanceof UFBoolean)
            return (UFBoolean)value;
        else
            return new UFBoolean(value.toString().trim());
    }
	
	/**
	 * 将 ArrayList 转成sql的in
	 * 返回格式：  ('null','value1','value2')
	 * 进行去重
	 */
	public static String getSqlInByList(ArrayList<String> list) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		for (String str : list) {
			map.put(str, str);
		}
		
		StringBuffer result = new StringBuffer(" ('null'");
		for (String str : map.keySet()) {
			result.append(",'").append(str).append("'");
		}
		result.append(") ");
		return result.toString();
	}
	
	/**
	 *  根据 开始、结束日期，返回 这期间内，所有日期的 ArrayList<String>
	 */
	public static ArrayList<String> getEveryDateList(String beginDateStr, String endDateStr) {
		ArrayList<String> result = new ArrayList();
		
		UFDate beginDate = PuPubVO.getUFDate(beginDateStr);
		UFDate endDate = PuPubVO.getUFDate(endDateStr);
		
		Integer days = endDate.getDaysAfter(beginDate);	// 开始结束 日期之间，相差的天数
		
		result.add(beginDateStr);
		
		if (days >= 1) {
			for (int i = 1; i <= days; i++) {
				UFDate date = beginDate.getDateAfter(i);
				String strDate = date.toString().substring(0, 10);
				result.add(strDate);
			}
		}
		
		return result;
	}
	
	/**
	 * 字符长度
	 */
	public static int getStringLength(String str,String encoding) throws UnsupportedEncodingException{
		if(isNullOrEmpty(str)) return 0;
		else return str.getBytes(encoding).length;
	}
	/**
	* 判断字段是否为空
	* @return true 为空， false 不为空
	*/
	public static boolean isNullOrEmpty(String str){
	    return null == str || "".equals(str);
	}
	
}
