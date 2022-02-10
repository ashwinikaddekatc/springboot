package com.realnet.comm.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Did;
    private String Dname;
    private String Dhead;
    private long Dcontact;
    private int No_ofEmp;
}
