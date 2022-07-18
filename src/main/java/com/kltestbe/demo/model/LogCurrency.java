package com.kltestbe.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "log_currency")
public class LogCurrency implements Serializable{
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(name = "search", nullable = false, length = 500)
	private String search;

    @Column(name = "full_name", nullable = false,length = 500)
	private String fullName;

    @Column(name = "population", nullable = false, length = 500)
	private int population;

	@Column(name = "priod", nullable = false, length = 500)
	private String priod;

	@Column(name = "currency", nullable = false, length = 500)
	private String currency;

	@Column(name = "rate_idr", nullable = false)
	private double rateIDR;

    @CreationTimestamp
	@Column(name = "created", nullable = true)
	private LocalDateTime createdAt;
}
