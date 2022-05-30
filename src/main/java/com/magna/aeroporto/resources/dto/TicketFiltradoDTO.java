package com.magna.aeroporto.resources.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.magna.aeroporto.entities.Client;
import com.magna.aeroporto.entities.Ticket;

public class TicketFiltradoDTO {

		private Long id;

		private Client client;
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
		private LocalDateTime dataCompra;
		
		public TicketFiltradoDTO() {
			
		}

		public TicketFiltradoDTO(Ticket ticket) {
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

		public LocalDateTime getDataCompra() {
			return dataCompra;
		}

		public void setDataCompra(LocalDateTime dataCompra) {
			this.dataCompra = dataCompra;
		}

}
