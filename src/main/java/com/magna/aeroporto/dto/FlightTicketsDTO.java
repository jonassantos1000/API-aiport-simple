package com.magna.aeroporto.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.entities.Ticket;

public class FlightTicketsDTO {

		private Long id;

		private Client client;
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
		private Instant dataCompra;
		
		public FlightTicketsDTO() {
			
		}

		public FlightTicketsDTO(Ticket ticket) {
			super();
			this.client= (ticket.getClient());
			this.dataCompra=(ticket.getDataCompra());
			this.id=(ticket.getId());
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public Instant getDataCompra() {
			return dataCompra;
		}

		public void setDataCompra(Instant dataCompra) {
			this.dataCompra = dataCompra;
		}

}