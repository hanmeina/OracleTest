package com.RBR.util;

public class SystemContext {
	private static ThreadLocal offset = new ThreadLocal();  
    private static ThreadLocal pageSize = new ThreadLocal();  
    public static int getOffset(){  
        Integer os = (Integer)offset.get();  
        if(os == null){  
            return 0;  
        }  
        return os;  
    }  
    public static void setOffset(int offsetvalue){  
        offset.set(offsetvalue);  
    }  
    public static void removeOffset(){  
        offset.remove();  
    }  
    public static int getPageSize(){  
        Integer ps = (Integer)pageSize.get();  
        if(ps == null){  
            return Integer.MAX_VALUE;  
        }  
        return ps;  
    }  
    public static void setPageSize(int pageSizevalue){  
        pageSize.set(pageSizevalue);  
    }  
    public static void removePageSize(){  
        pageSize.remove();  
    }  
}
