package com.example.BookingMovieTickets.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "totalMoney")
    private double totalMoney;

    @Column(name = "tradingCode")
    private String tradingCode;

    @Column(name = "createTime")
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    @JsonBackReference
    private User userBill;
    @Column(name = "customerId")
    private int customerId;

    private String name;

    @Column(name = "updateTime")
    private LocalDateTime updateTime;

    @ManyToOne
    @JoinColumn(name = "promotionId", insertable = false, updatable = false)
    @JsonBackReference
    private Promotion promotionfromBill;
    @Column(name = "promotionId")
    private int promotionId;

    @Column(name = "isActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "billStatusId", insertable = false, updatable = false)
    @JsonBackReference
    private BillStatus billStatusfromBill;
    @Column(name = "billStatusId")
    private int billStatusId;

    @OneToMany(mappedBy = "billfromBillFood")
    @JsonManagedReference
    private Set<BillFood> billFoods;

    @OneToMany(mappedBy = "billfromBillTicket")
    @JsonManagedReference
    private Set<BillTicket> billTickets;
}
