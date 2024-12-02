package com.krakedev.inventarios3.entidades;



import java.util.ArrayList;
import java.util.Date;

public class PedidoNuevo {

    private int codigo;
    private Proveedor proveedor;
    private Date fecha;
    private EstadoPedido estadoPedido;
    private ArrayList<DetallePedido> detalles;

    // Constructor vacío: inicializa 'detalles' con una lista vacía
    public PedidoNuevo() {
        this.detalles = new ArrayList<>();  // Inicializa la lista vacía para evitar NullPointerException
    }

    // Constructor con parámetros
    public PedidoNuevo(int codigo, Proveedor proveedor, Date fecha, EstadoPedido estadoPedido) {
        this.codigo = codigo;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.estadoPedido = estadoPedido;
        this.detalles = new ArrayList<>();  // Inicializa la lista vacía para evitar NullPointerException
    }

    // Constructor con todos los parámetros, controla el caso en que 'detalles' sea null
    public PedidoNuevo(int codigo, Proveedor proveedor, Date fecha, EstadoPedido estadoPedido, ArrayList<DetallePedido> detalles) {
        this.codigo = codigo;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.estadoPedido = estadoPedido;
        // Si 'detalles' es null, asignamos una lista vacía para evitar NullPointerException
        this.detalles = (detalles != null) ? detalles : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "PedidoNuevo [codigo=" + codigo + ", proveedor=" + proveedor + ", fecha=" + fecha + ", estadoPedido="
                + estadoPedido + "]";
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}
