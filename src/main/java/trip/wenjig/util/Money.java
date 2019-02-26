package trip.wenjig.util;

import java.math.BigDecimal;

public class Money {

	public static BigDecimal addition(String nativeMoney, String addMoney) {
		BigDecimal bigDecimal1 = new BigDecimal(nativeMoney);
		BigDecimal bigDecimal2 = new BigDecimal(addMoney);
		return bigDecimal1.add(bigDecimal2);
	}

	public static BigDecimal subtraction(String nativeMoney, String subMoney) {
		BigDecimal bigDecimal1 = new BigDecimal(nativeMoney);
		BigDecimal bigDecimal2 = new BigDecimal(subMoney);
		return bigDecimal1.subtract(bigDecimal2);
	}

	public static BigDecimal multiplication(String nativeMoney, String mulMoney) {
		BigDecimal bigDecimal1 = new BigDecimal(nativeMoney);
		BigDecimal bigDecimal2 = new BigDecimal(mulMoney);
		return bigDecimal1.multiply(bigDecimal2);
	}

	public static BigDecimal division(String nativeMoney, String divMoney) {
		BigDecimal bigDecimal1 = new BigDecimal(nativeMoney);
		BigDecimal bigDecimal2 = new BigDecimal(divMoney);
		return bigDecimal1.divide(bigDecimal2);
	}
}
