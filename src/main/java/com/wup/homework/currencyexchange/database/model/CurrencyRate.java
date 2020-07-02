package com.wup.homework.currencyexchange.database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currencyCode;
    private String currencyName;
    private Float buyingRate;
    private Float sellingRate;
    private Float centralRate;
}
