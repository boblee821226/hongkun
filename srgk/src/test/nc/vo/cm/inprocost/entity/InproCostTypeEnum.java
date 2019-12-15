package nc.vo.cm.inprocost.entity;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class InproCostTypeEnum extends MDEnum {
    /**
     * 在产成本
     */
    public static final InproCostTypeEnum CM_InproCost = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(0));

    /**
     * 期初在产余额
     */
    public static final InproCostTypeEnum CM_Begin = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(1));

    /**
     * 在产调整单
     */
    public static final InproCostTypeEnum CM_Adjust = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(2));

    /**
     * 在产转出
     */
    public static final InproCostTypeEnum CM_TurnOut = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(3));

    /**
     * 在产成本(成本对象不为空)---出库异常结存
     */
    public static final InproCostTypeEnum CM_YCJC = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(4));

    /**
     * 在产成本(成本对象为空)---出库异常结存(留存)
     */
    public static final InproCostTypeEnum CM_YCJC_Remain = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(5));

    public InproCostTypeEnum(IEnumValue enumValue) {
        super(enumValue);
    }
}
