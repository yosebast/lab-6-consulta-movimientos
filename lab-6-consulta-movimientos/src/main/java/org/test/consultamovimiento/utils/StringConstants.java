package org.test.consultamovimiento.utils;

public class StringConstants {

	public static final String TRUE_STRING = "true";
	public static final String FALSE_STRING = "false";
	
	public static final String EMPTY_STRING = "";
	public static final String SPACE = " ";

	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String DASH = "-";
	public static final String DOT = ".";
	public static final String SLASH = "/";
	public static final String UNDERSCORE = "_";
	public static final String VERTICAL_BAR = "|";
	public static final String STAR_KEY = "*";
	public static final String ROUND_BRACKETS = ")";
	
	
	public static final String NOT_APPLICABLE = "N/A";
	public static final String ALL_PARTITIONS = "ALL_PARTITIONS";
	public static final String UNION_ALL = "UNION ALL";
	public static final String SELECT = "SELECT";
	public static final String FROM = "FROM";
	public static final String USUARIO_TABLA_PARTITIONS = "TER_WRITE.TB_HIST_MOVIMIENTOS PARTITION(TB_HIST_MOVIMIENTOS_";
	
	public static final String MSISDN = "msisdn";	

	private StringConstants() {
		throw new IllegalAccessError("Constants class");
	}
}
