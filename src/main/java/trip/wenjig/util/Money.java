package trip.wenjig.util;

import java.math.BigDecimal;

public class Money {
	
	private static final double def_d = 0.0d;

	public Money() {
		
	}

	private void log() {
		
	}

	/**
	 * 提供精确加法显示
	 * @param money
	 * @param addMoney
	 * @return Now hold money(double)
	 */
	public static double disAddMoney(String money,String addMoney) {
		BigDecimal b1 = new BigDecimal(money);
		BigDecimal b2 = new BigDecimal(addMoney);
		BigDecimal b3 = b1.add(b2);
		if (b3 != null) {
			return b3.doubleValue();
		}
		return def_d;
	}

	/**
	 * 提供数据库操作的加法对象
	 * @param money
	 * @param addMoney
	 * @return
	 */
	public static BigDecimal addMoney(String money,String addMoney) {
		BigDecimal b1 = new BigDecimal(money);
		BigDecimal b2 = new BigDecimal(addMoney);
		BigDecimal b3 = b1.add(b2);
	    return b3;
	}
	
	/**
	 * 提供精确减法显示
	 * @param money
	 * @param subMoney
	 * @return
	 */
	public static double disSubMoney(String money,String subMoney) {
		BigDecimal b1 = new BigDecimal(money);
		BigDecimal b2 = new BigDecimal(subMoney);
		BigDecimal b3 = b1.subtract(b2);
		if (b3 != null) {
			return b3.doubleValue();
		}
		return def_d;
	}
	
	/**
	 * 提供数据库操作的减法对象
	 * @param money
	 * @param subMoney
	 * @return
	 */
	public static BigDecimal subMoney(String money,String subMoney) {
		BigDecimal b1 = new BigDecimal(money);
		BigDecimal b2 = new BigDecimal(subMoney);
		BigDecimal b3 = b1.subtract(b2);
	    return b3;
	}
	
	/**
	 * 提供精确乘法显示
	 * @param money
	 * @param mulMoney
	 * @return
	 */
	public static double disMulMoney(String money,String mulMoney) {
		BigDecimal b1 = new BigDecimal(money);
		BigDecimal b2 = new BigDecimal(mulMoney);
		BigDecimal b3 = b1.multiply(b2);
		if (b3 != null) {
			return b3.doubleValue();
		}
		return def_d;
	}
	
	/**
	 * 提供数据库操作的乘法对象
	 * @param money
	 * @param mulMoney
	 * @return
	 */
	public static BigDecimal mulMoney(String money,String mulMoney) {
		BigDecimal b1 = new BigDecimal(money);
		BigDecimal b2 = new BigDecimal(mulMoney);
		BigDecimal b3 = b1.multiply(b2);
	    return b3;
	}
	
}
