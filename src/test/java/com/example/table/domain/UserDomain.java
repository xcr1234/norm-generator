package com.example.table.domain;
import norm.anno.Column;
import norm.anno.Id;
import norm.anno.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Table("user")
public class UserDomain{
    public static String COL_ID = "id";
    public static String COL_NAME = "name";

    @Id
    @Column("id")
    private Integer id;
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    @Column("name")
    private String name;
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


}