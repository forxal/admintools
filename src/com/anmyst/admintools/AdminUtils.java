package com.anmyst.admintools;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.*;

import org.w3c.dom.*;

/**
 * Admin Tools utility functions
 */
public class AdminUtils {
	
	/**
	 * Load xml document that contains commands 
	 * @param fname - xml file name
	 * @return xml DOM 
	 */
	public Document loadDocument(String fname) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    //factory.setValidating(true);
	    //factory.setIgnoringElementContentWhitespace(true);
	    Document doc = null;	    
	    try {
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        File file = new File(fname);
	        doc = builder.parse(file);	      
	    } 
	    catch (Exception e){
	    	e.printStackTrace();
	    	return null;
	    }	    
		return doc;
	}
	
	/**
	 * Read nodes from Document, add nodes to JTree 
	 * @param nl - list of nodes from XML Document 
	 * @param tn - JTree node
	 */
	private void processNode(NodeList nl, DefaultMutableTreeNode tn) {
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if ((n.getNodeType() == Node.ELEMENT_NODE) && (n.hasAttributes())){
				NamedNodeMap nodemap = n.getAttributes();
				String nname = n.getNodeName();
				String ncmd = "cmd";
				boolean f = false;
				
				if (nname.equalsIgnoreCase("Folder"))
					f = true;
				
				for (int j = 0; j < nodemap.getLength(); j++) {
					Node attr = nodemap.item(j);
					if  (attr.getNodeName().equalsIgnoreCase("item_name")) 
						nname = attr.getNodeValue();
					
					if  (attr.getNodeName().equalsIgnoreCase("item_command")) 
						ncmd = attr.getNodeValue();
					
					if  (attr.getNodeName().equalsIgnoreCase("folder_name")) 
						nname = attr.getNodeValue();
				}
				
				CommandTreeNode tnode = new CommandTreeNode(nname, ncmd, f);
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(tnode);
				tn.add(node);
				
				if (n.hasChildNodes()) {
					processNode(n.getChildNodes(), node);
				}
			}							
		}
	}
	
	/**
	 * Fill JTree from XML Document 
	 * @param m - JTree model
	 * @param doc - XML Document
	 * @return
	 */
	public int fillTree(DefaultTreeModel m, Document doc) {	
		try {
			
			if (doc.hasChildNodes()) {
				processNode(doc.getChildNodes(), (DefaultMutableTreeNode)m.getRoot());
				//DefaultMutableTreeNode root = (DefaultMutableTreeNode)m.getRoot();
				//DefaultMutableTreeNode node = new DefaultMutableTreeNode("Child");
				//root.add(node);
			}
				
		}
		catch (Exception e) {
			return 1;
		}
		return 0;
	}

}
