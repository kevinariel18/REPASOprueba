package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios3.entidades.Categoria;
import com.krakedev.inventarios3.entidades.Producto;
import com.krakedev.inventarios3.entidades.UnidadDeMedida;
import com.krakedev.inventarios3.exepciones.KrakeDevException;
import com.krakedev.inventarios3.utils.conexionBDD;


public class ProductosBDD {

	public ArrayList<Producto> buscar(String subcadena) throws KrakeDevException {

		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		ResultSet rs = null;
		String sql = "SELECT "
	            + "    prod.codigo AS codigo_producto, "
	            + "    prod.nombre AS nombre_producto, "
	            + "    udm.codigo_nombre AS codigo_unidad_medida, "
	            + "    udm.descripcion AS descripcion_udm, "
	            + "    cast(prod.precio_venta as decimal(6,2)), "
	            + "    prod.tiene_iva, "
	            + "    cast(prod.coste as decimal (6,2) ), "
	            + "    cat.codigo_cat AS codigo_categoria, "
	            + "    cat.nombre AS nombre_categoria, "
	            + "    prod.stock "
	            + "FROM "
	            + "    productos prod "
	            + "JOIN "
	            + "    unidades_medida udm ON prod.id_unidades_medida = udm.codigo_nombre "
	            + "JOIN "
	            + "    categorias cat ON prod.id_categorias = cat.codigo_cat "
	            + "WHERE upper(prod.nombre) like ?";


		try {
			con = conexionBDD.obtenerConexion();
			ps = con.prepareStatement(sql);
			ps.setString(1,  "%" +subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();
					
			while (rs.next()) {

				int codigoProducto = rs.getInt("codigo_producto");
				String nombreProducto = rs.getString("nombre_producto");
				String codigoUDM = rs.getString("codigo_unidad_medida");
				String descripcion_udm = rs.getString("descripcion_udm");
				BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
				boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				int codigoCategoria = rs.getInt("codigo_categoria");
				String nombreCategoria = rs.getString("nombre_categoria");
				int stock = rs.getInt("stock");

				UnidadDeMedida udm = new UnidadDeMedida();

				udm.setNombre(codigoUDM);
				udm.setDescripcion(descripcion_udm);

				Categoria categoria = new Categoria();

				categoria.setCodigo(codigoCategoria);
				categoria.setNombre(nombreCategoria);

				Producto producto = new Producto();

				producto.setCodigo(codigoProducto);
				producto.setNombre(nombreProducto);
				producto.setUnidadMedida(udm);
				producto.setPrecioVenta(precioVenta);
				producto.setTieneIva(tieneIva);
				producto.setCoste(coste);
				producto.setCategoria(categoria);
				producto.setStock(stock);

				

				productos.add(producto);

			}

		} catch (KrakeDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new KrakeDevException("Error al intentar conectarse a la base de datos" + e.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new KrakeDevException("error en la consulta sql, detalles " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new KrakeDevException("Error al intentar conectarse a la base de datos" + e.getMessage());
			}
		}

		return productos;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void insertar(Producto producto) throws KrakeDevException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    String sql = "INSERT INTO productos (codigo, nombre, id_unidades_medida, precio_venta, tiene_iva, coste, id_categorias, stock) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        
	        con = conexionBDD.obtenerConexion();
	        
	        
	        ps = con.prepareStatement(sql);
	        
	     
	        ps.setInt(1, producto.getCodigo());
	        ps.setString(2, producto.getNombre());
	        ps.setString(3, producto.getUnidadMedida().getNombre());
	        ps.setBigDecimal(4, producto.getPrecioVenta());
	        ps.setBoolean(5, producto.isTieneIva());
	        ps.setBigDecimal(6, producto.getCoste());
	        ps.setInt(7, producto.getCategoria().getCodigo());
	        ps.setInt(8, producto.getStock());
	        
	      
	        int filasAfectadas = ps.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Producto insertado correctamente.");
	        } else {
	            throw new KrakeDevException("No se pudo insertar el producto.");
	        }
	        
	    } catch (KrakeDevException e) {
	        e.printStackTrace();
	        throw new KrakeDevException("Error al intentar conectarse a la base de datos: " + e.getMessage());
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new KrakeDevException("Error en la consulta SQL: " + e.getMessage());
	        
	    } finally {
	     
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new KrakeDevException("Error al cerrar el PreparedStatement: " + e.getMessage());
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new KrakeDevException("Error al cerrar la conexión: " + e.getMessage());
	            }
	        }
	    }
	}


	public boolean actualizar(Producto producto) throws KrakeDevException {
	    // Verificar que los objetos no sean nulos
	    if (producto.getUnidadMedida() == null) {
	        throw new KrakeDevException("Unidad de medida no puede ser nula.");
	    }

	    if (producto.getCategoria() == null) {
	        throw new KrakeDevException("Categoría no puede ser nula.");
	    }

	    // Validar los datos del producto antes de la actualización
	    if (producto.getCodigo() <= 0) {
	        throw new KrakeDevException("El código del producto debe ser mayor que 0.");
	    }

	    if (producto.getStock() < 0) {
	        throw new KrakeDevException("El stock no puede ser negativo.");
	    }

	    if (producto.getPrecioVenta() == null || producto.getPrecioVenta().compareTo(BigDecimal.ZERO) <= 0) {
	        throw new KrakeDevException("El precio de venta debe ser mayor que 0.");
	    }

	    // Ahora aseguramos que utilizamos el codigo_nombre de la unidad de medida
	    String sql = "UPDATE productos SET nombre = ?, id_unidades_medida = ?, precio_venta = ?, tiene_iva = ?, coste = ?, id_categorias = ?, stock = ? WHERE codigo = ?";
	    Connection con = null;
	    PreparedStatement ps = null;
	    boolean actualizado = false;

	    try {
	        // Establecer la conexión a la base de datos
	        con = conexionBDD.obtenerConexion();
	        
	        // Preparar la consulta SQL
	        ps = con.prepareStatement(sql);

	        // Establecer los parámetros de la consulta
	        ps.setString(1, producto.getNombre());
	        // Usar el codigo_nombre de la unidad de medida (clave primaria)
	        ps.setString(2, producto.getUnidadMedida().getNombre()); // Usamos 'codigo_nombre' de la unidad de medida
	        ps.setBigDecimal(3, producto.getPrecioVenta());
	        ps.setBoolean(4, producto.isTieneIva());
	        ps.setBigDecimal(5, producto.getCoste());
	        ps.setInt(6, producto.getCategoria().getCodigo()); // Asegúrate de que 'producto.getCategoria()' no sea null
	        ps.setInt(7, producto.getStock());
	        ps.setInt(8, producto.getCodigo());

	        // Ejecutar la actualización
	        int filasActualizadas = ps.executeUpdate();

	        // Verificar si la actualización fue exitosa
	        actualizado = filasActualizadas > 0;

	    } catch (SQLException e) {
	        // Imprimir el error para depuración
	        e.printStackTrace();
	        
	        // Lanzar una excepción personalizada con el mensaje de error
	        throw new KrakeDevException("Error al actualizar el producto: " + e.getMessage(), e);
	    } finally {
	        // Cerrar recursos de conexión
	        conexionBDD.cerrar(ps);
	        conexionBDD.cerrar(con);
	    }

	    return actualizado;
	}




}


	
	
	
	   

	
	
	
	
	
	
	
	




	
	
	
	
	
	
	
	
	
	
	
	
	
	


