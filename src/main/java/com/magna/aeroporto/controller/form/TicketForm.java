package com.magna.aeroporto.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TicketForm {
	
		private Long id;
 
		@NotNull
		private Long idFlight;
		
		@NotNull
		private Long idClient;
		
		@NotNull
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
		private LocalDateTime dataCompra;
		
		public TicketForm() {
			
		}

		public TicketForm(Long id, Long idFlight, Long idClient, LocalDateTime dataCompra) {
			this.id = id;
			this.idFlight = idFlight;
			this.idClient = idClient;
			this.dataCompra = dataCompra;
		}

		public Long getId() {
			return id;
		}

		@JsonIgnore
		public void setId(Long id) {
			this.id = id;
		}

		public Long getIdFlight() {
			return idFlight;
		}

		public void setIdFlight(Long idFlight) {
			this.idFlight = idFlight;
		}

		public Long getIdClient() {
			return idClient;
		}

		public void setIdClient(Long idClient) {
			this.idClient = idClient;
		}

		public LocalDateTime getDataCompra() {
			return dataCompra;
		}

		public void setDataCompra(LocalDateTime dataCompra) {
			this.dataCompra = dataCompra;
		}
		
}
