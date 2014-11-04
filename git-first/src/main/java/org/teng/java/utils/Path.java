package org.teng.java.utils;

/**
 * this class is provide to convert a path name to the actual path.
 * 1. application mode:
 * 	  no conversion;
 * 
 * 2. servlet context :
 *    map the path to the absolute path.    
 * 	  example:
 * 
 *    "etc/conf/domain.conf"
 *      -> 
 *    "$TOMCAT_BASE/webapps/$CONTEXT_BASE/WEB-INF/etc/conf/domain.conf"    
 *    
 *    note that the orignal path should be put under path "WEB-INF"
 *    
 * end;
 */

public class Path {
	
	private static final String SERVLET_CONTEXT = "/WEB-INF/classes";
	
	private static final boolean inServletContext;
	private static final String path_prefix;
	static {
		String binpath = Path.class.getResource("/").toString();
		int p = binpath.lastIndexOf(SERVLET_CONTEXT);
		if( p > 0){				
			path_prefix = binpath.substring(5, p+9); 	// remove the heading "file:"
			inServletContext = true;
		} else {			
			path_prefix = null;
			inServletContext = false;
		}
	}
	
	public static String path(String path) {
		
		if((path_prefix == null) || path.startsWith(path_prefix)) {
			return path;
		} else {
			return path_prefix + path;
		}
	}
	
	public static String getContextPath() {
		return path_prefix;		// "tomcat/webapps/xxx/WEB-INF/"
	}
	
	public static boolean isInServletContext() {
		return inServletContext;
	}
}
