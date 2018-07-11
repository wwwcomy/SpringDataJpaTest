package com.drools.test;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Main {
	static KieSession getSession() {
		KieServices ks = KieServices.Factory.get();

		KieContainer kc = ks.getKieClasspathContainer();

		return kc.newKieSession("simpleRuleKSession");
	}

	public static void main(String[] args) {
		KieSession ks = getSession();

		Person p1 = new Person("白展堂", 68);
		Person p2 = new Person("李大嘴", 32);
		Person p3 = new Person("佟湘玉", 18);
		Person p4 = new Person("郭芙蓉", 8);

		System.out.println("before p1 : " + p1);
		System.out.println("before p2 : " + p2);
		System.out.println("before p3 : " + p3);
		System.out.println("before p4 : " + p4);

		ks.insert(p1);
		ks.insert(p2);
		ks.insert(p3);
		ks.insert(p4);

		int count = ks.fireAllRules();
		System.out.println("总执行了" + count + "条规则");
		System.out.println("after p1 : " + p1);
		System.out.println("after p2 : " + p2);
		System.out.println("after p3 : " + p3);
		System.out.println("after p4 : " + p4);
		ks.dispose();
	}
}
