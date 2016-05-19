package com.tpg.onewatcher.message;

public class SmsEntity {
	
	/*insert into sms 
	 * set enteredBy_notify = '$enteredBy_notify', 
	 * additional_notify_address = '$additional_notify_address', 
	 * system = \"csweb\", 
	 * priority = '$priority', 
	 * destination_addr = '$phonenum', 
	 * registered_delivery = 1, 
	 * short_message = '$message', 
	 * status = \"Open-New-Outgoing\", 
	 * entered = NOW(), 
	 * updated = NOW(), 
	 * enteredBy = '$enteredBy'*/
	String enteredByNotify;
	String additionalNotifyAddress;
	String system;
	String priority;
	String destinationAddr;
	String registeredDelivery;
	String shortMessage;
	String status;
	String enteredBy;
	public String getEnteredByNotify() {
		return enteredByNotify;
	}
	public String getAdditionalNotifyAddress() {
		return additionalNotifyAddress;
	}
	public String getSystem() {
		return system;
	}
	public String getPriority() {
		return priority;
	}
	public String getDestinationAddr() {
		return destinationAddr;
	}
	public String getRegisteredDelivery() {
		return registeredDelivery;
	}
	public String getShortMessage() {
		return shortMessage;
	}
	public String getStatus() {
		return status;
	}
	public String getEnteredBy() {
		return enteredBy;
	}
	public void setEnteredByNotify(String enteredByNotify) {
		this.enteredByNotify = enteredByNotify;
	}
	public void setAdditionalNotifyAddress(String additionalNotifyAddress) {
		this.additionalNotifyAddress = additionalNotifyAddress;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public void setDestinationAddr(String destinationAddr) {
		this.destinationAddr = destinationAddr;
	}
	public void setRegisteredDelivery(String registeredDelivery) {
		this.registeredDelivery = registeredDelivery;
	}
	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}
	@Override
	public String toString() {
		return "SmsEntity [enteredByNotify=" + enteredByNotify + ", additionalNotifyAddress=" + additionalNotifyAddress
				+ ", system=" + system + ", priority=" + priority + ", destinationAddr=" + destinationAddr
				+ ", registeredDelivery=" + registeredDelivery + ", shortMessage=" + shortMessage + ", status=" + status
				+ ", enteredBy=" + enteredBy + "]";
	}
}
