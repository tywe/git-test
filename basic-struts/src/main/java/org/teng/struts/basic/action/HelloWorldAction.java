package org.teng.struts.basic.action;

import org.teng.struts.basic.model.MessageStore;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport {
	 private static final long serialVersionUID = 1L;
	 
	    private MessageStore messageStore;
	    private static int helloCount = 0;
	    private String userName;
	    
	    public String getUserName() {
	        return userName;
	    }
	     
	    public void setUserName(String userName) {
	        this.userName = userName;
	    }
	    
	    public int getHelloCount() {
	        return helloCount;
	    }
	     
	    public void setHelloCount(int helloCount) {
	        HelloWorldAction.helloCount = helloCount;
	    }
	    
	    public String execute() throws Exception {
	    	helloCount++; 
	        messageStore = new MessageStore() ;
	        if (userName != null) {
	             
	            messageStore.setMessage( messageStore.getMessage() + " " + userName);
	                     
	        }
	        return SUCCESS;
	    }
	 
	    public MessageStore getMessageStore() {
	        return messageStore;
	    }
	 
	    public void setMessageStore(MessageStore messageStore) {
	        this.messageStore = messageStore;
	    }
}
