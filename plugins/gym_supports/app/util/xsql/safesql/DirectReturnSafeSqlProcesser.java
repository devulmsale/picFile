package util.xsql.safesql;

import util.xsql.SafeSqlProcesser;

/**
 * 直接返回,不做任何处理
 * @author badqiu
 *
 */
public class DirectReturnSafeSqlProcesser implements SafeSqlProcesser {
	
	public static SafeSqlProcesser INSTANCE = new DirectReturnSafeSqlProcesser();
	
	public String process(String value) {
		return value;
	}

}
