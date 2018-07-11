package com.drools.test;

import java.util.Collection;

import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.runtime.StatefulKnowledgeSession;

@SuppressWarnings("deprecation")
public class MyMain {
	public static void main(String[] args) {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		// this will parse and compile in one step
		KieServices kieServices = KieServices.Factory.get();
		Resource resource = kieServices.getResources().newClassPathResource("first.drl");
		kbuilder.add(resource, ResourceType.DRL);
		// Check the builder for errors
		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors().toString());
			throw new RuntimeException("Unable to compile DRL file.");
		}
		// get the compiled packages (which are serializable)
		Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
		// add the packages to a knowledgebase (deploy the knowledge packages).
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(pkgs);
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		Person p1 = new Person("白展堂", 68);
		Person p2 = new Person("李大嘴", 32);
		Person p3 = new Person("佟湘玉", 18);
		Person p4 = new Person("郭芙蓉", 8);

		System.out.println("before p1 : " + p1);
		System.out.println("before p2 : " + p2);
		System.out.println("before p3 : " + p3);
		System.out.println("before p4 : " + p4);

		ksession.insert(p1);
		ksession.insert(p2);
		ksession.insert(p3);
		ksession.insert(p4);

		int count = ksession.fireAllRules();
		System.out.println("总执行了" + count + "条规则");
		System.out.println("after p1 : " + p1);
		System.out.println("after p2 : " + p2);
		System.out.println("after p3 : " + p3);
		System.out.println("after p4 : " + p4);
		// ((InternalAgenda) ksession.getAgenda()).activateRuleFlowGroup(ruleGroup);
		ksession.dispose();

	}
}
