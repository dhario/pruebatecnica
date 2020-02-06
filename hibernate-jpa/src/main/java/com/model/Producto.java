package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  //hace referencia a una entidad que va apersistirce en una tabla de una db
@Table(name="productos") //La clase producto va almacenarce en la tabla producto
public class Producto {
	@Id  											  //Identifica el atributo ID de la tabla a la que de va a mapear el objeto
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Indica que este campo en la bd sera autoincrementable
	private long Item;
	@Column					//Indica la correspondencia de campos en la bd
	private String Nombre;
	@Column
	private long Cantidad;
	@Column
	private long CodigoInventario;
	
	public long getItem() {
		return Item;
	}
	public void setItem(long item) {
		Item = item;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public long getCantidad() {
		return Cantidad;
	}
	public void setCantidad(long cantidad) {
		Cantidad = cantidad;
	}
	public long getCodigoInventario() {
		return CodigoInventario;
	}
	public void setCodigoInventario(long codigoInventario) {
		CodigoInventario = codigoInventario;
	}
}
