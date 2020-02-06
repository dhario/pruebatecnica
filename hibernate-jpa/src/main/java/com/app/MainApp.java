package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.model.Producto;

public class MainApp {

	public static void main(String[] args) {
		
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		Producto producto;

		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager(); //El objeto entity trae de JPAUtil el objeto factory que contiene la coneccion a la base de datos
		while (opcion!=5) {
			System.out.println("1. Crear Producto");
			System.out.println("2. Buscar Producto");
			System.out.println("3. Eliminar Producto");
			System.out.println("4. Salir");
			System.out.println("Elija una opción:");

			opcion = scanner.nextInt();
			switch (opcion) {
			
			case 1: //Insertamos datos en la BD.
				System.out.println("Digite el nombre del producto:");
				producto = new Producto();  //Creamos un obj de tipo produto de la clase producto
				producto.setNombre(null);
				scanner.nextLine();
				producto.setNombre(scanner.nextLine());
										//SETeo del nombre y la cantidad
				System.out.println("Digite la cantidad del producto:");
				producto.setCantidad(scanner.nextLong());
				System.out.println(producto);
				
				//Se utilizan 3 sentecias.
				entity.getTransaction().begin(); //JDBC comenzamos la tranzaccion
				entity.persist(producto); //Almacenamos el objeto producto en la base de datos por medio de hibernate
				entity.getTransaction().commit(); //finalizamos la tranzaccion y todo lo que se realizo haga un commit en la BD.
				
				System.out.println("Cantidad registrada..");
				System.out.println();
				
				System.out.println("Digite el codigo del producto:");
				producto.setCodigoInventario(scanner.nextLong());
				System.out.println(producto);
				entity.getTransaction().begin();
				entity.persist(producto);
				entity.getTransaction().commit();
				System.out.println("Codigo registrado..");
				System.out.println();
				break;

			case 2: //Buscamos elementos en la BD.
				System.out.println("Digite el Item del producto a buscar:");
				
				producto = new Producto(); //Creamos la variable
				producto = entity.find(Producto.class, scanner.nextLong()); //Ocupamos el obj entity con el metodo find de hibernate
											//Le pasamos dos parametros al metodo find, el 1er parametro hace refencia a la clase donde esta instanciado el obj	
				if (producto != null) {     // le pasamos el Item y se encarga de buscar en la DB y ponerlo en el obj producto
					System.out.println(producto);
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Producto no encontrado... Lista de productos completa");
					//En esta lista se pondran todos los obj de tipo producto
					List<Producto> listaProductos= new ArrayList<Producto>();
					//Creamos un obj de tipo query con el metodo create.query al cual se le crea una sentencia sql que hace referencia a la clase producto.
					Query query=entity.createQuery("SELECT p FROM Producto p");
					//El Obj query regresa de nuevo a la lista de productos
					listaProductos=query.getResultList();
					for (Producto p : listaProductos) {
						System.out.println(p);
					}
					
					System.out.println();
				}
				break;
				
			case 3:
				System.out.println("Digite el Item del producto a eliminar:");
				producto = new Producto();
				//Buscamos el producto que se va eliminar con el metodo find.	
				producto = entity.find(Producto.class, scanner.nextLong());
				if (producto != null) {
					System.out.println(producto);
					//Se crean las 3 sentencias base con el metodo remove
					entity.getTransaction().begin();
					entity.remove(producto);
					entity.getTransaction().commit();
					
					System.out.println("Producto eliminado...");
				} else {
					System.out.println("Producto no encontrado...");
				}
				break;
				
			case 4:entity.close();JPAUtil.shutdown();
			break;

			default:
				System.out.println("Opción no válida\n");
				break;
			}
		}

	}

}
