package com.RBR.util;

import org.drools.runtime.StatefulKnowledgeSession;

public class DroolsUtils {
	private static ThreadLocal<StatefulKnowledgeSession> threadLocal = 
			new ThreadLocal<StatefulKnowledgeSession>();

	public static ThreadLocal<StatefulKnowledgeSession> getThreadLocal() {
		return threadLocal;
	}
	
}
