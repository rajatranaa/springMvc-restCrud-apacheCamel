package com.bookStore.restCrud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BOOK")
public class Book implements Serializable  {

	private static final long serialVersionUID = -460917901673850042L;	
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID",nullable = false)
   private Long id;
   
   @Column(name = "TITLE")
   private String title;
   
   @Column(name = "AUTHOR")
   private String author;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }
  
   public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
