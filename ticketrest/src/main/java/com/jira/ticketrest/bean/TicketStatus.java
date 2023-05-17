package com.jira.ticketrest.bean;

import com.jira.ticketrest.exception.IllegalTicketStatusChangeException;

public enum TicketStatus {

	CREATED {
		@Override
		public TicketStatus cancelTicket(TicketStatus status) {
			if(!status.equals(null))return TicketStatus.CANCELLED;throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+TicketStatus.CREATED+" to "+status);
		}
		@Override
		public String toString() {
			return "CREATED";
		}

		@Override
		public TicketStatus getNextStatus(String status) {
			if(status.equalsIgnoreCase("OPEN"))
				return TicketStatus.OPEN;
			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+TicketStatus.CREATED+" to "+status);
		}
	},
	OPEN {
		@Override
		public String toString() {
			return "OPEN";
		}
		@Override
		public TicketStatus getNextStatus(String status) {
			if(status.equalsIgnoreCase("IN_PROGRESS"))
				return TicketStatus.IN_PROGRESS;
			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+TicketStatus.OPEN+" to "+status);
		}
		@Override
		public TicketStatus cancelTicket(TicketStatus status) {
			if(!status.equals(null))return TicketStatus.CANCELLED;
			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+TicketStatus.IN_PROGRESS+" to "+status);
		}
	},
	IN_PROGRESS {

		@Override
		public TicketStatus cancelTicket(TicketStatus status) {
			if(!status.equals(null))return TicketStatus.CANCELLED;
			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+TicketStatus.IN_PROGRESS+" to "+status);
		}
		@Override
		public String toString() {
			return "IN_PROGRESS";
		}
		@Override
		public TicketStatus getNextStatus(String status) {
			if(status.equalsIgnoreCase("DONE"))
				return TicketStatus.DONE;
			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+TicketStatus.IN_PROGRESS+" to "+status);
		}
	},
	DONE {
		@Override
		public TicketStatus cancelTicket(TicketStatus status) {
			if(!status.equals(null))
				return TicketStatus.CANCELLED;
			return null;
		}
		@Override
		public String toString() {
			return "DONE";
		}
		@Override
		public TicketStatus getNextStatus(String status) {
			if(status.equalsIgnoreCase("IN_REVIEW"))
				return TicketStatus.IN_REVIEW;
			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+TicketStatus.DONE+" to "+status);
		}
	},
	IN_REVIEW {

		@Override
		public TicketStatus cancelTicket(TicketStatus status) {
			if(!status.equals(null))
				return TicketStatus.CANCELLED;
			return null;
		}
		@Override
		public String toString() {
			return "IN_REVIEW";
		}
		@Override
		public TicketStatus getNextStatus(String status) {
			if(status.equalsIgnoreCase("APPROVED"))
				return TicketStatus.APPROVED;
			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+TicketStatus.IN_REVIEW+" to "+status);
		}
	},
	APPROVED {

		@Override
		public TicketStatus cancelTicket(TicketStatus status) {	// APPROVED TICKET CANNOT BE CANCELLED
			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+ TicketStatus.APPROVED+" to "+status);
		}
		@Override
		public String toString() {
			return "APPROVED";
		}
		@Override
		public TicketStatus getNextStatus(String status) {
			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+ TicketStatus.APPROVED+" to "+status);
		}
	},
	CANCELLED {

		@Override
		public TicketStatus cancelTicket(TicketStatus status) {
			if(!status.equals(null))
				return TicketStatus.CANCELLED;

			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+ TicketStatus.CANCELLED+" to "+status);
		}
		@Override
		public String toString() {
			return "CANCELLED";
		}
		@Override
		public  TicketStatus getNextStatus(String status) {
			throw new IllegalTicketStatusChangeException("Ticket status cannot be directly changed from "+ TicketStatus.CANCELLED+" to "+status);
		}

	};

	public abstract TicketStatus cancelTicket(TicketStatus status);
	
	public abstract TicketStatus getNextStatus(String status);

}
