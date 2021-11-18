package com.bootcamp.nttdata.examenServer.endPoint;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.nttdata.examenStarter.Almacen;
import com.bootcamp.nttdata.examenStarter.Producto;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
@Endpoint(id = "almacen")
@RestController
public class EndPointAlmacen {

	@Autowired
	private Almacen almacen;
	
	private Counter counter1;
	private Counter counter2;
	private Counter counter3;
	
	public EndPointAlmacen(MeterRegistry registry) {
		this.counter1 = Counter.builder("invocaciones.mostrarAlmacen").description("Contador de invocaciones").register(registry);
		this.counter2 = Counter.builder("invocaciones.insertarProducto").description("Contador de invocaciones").register(registry);
		this.counter3 = Counter.builder("invocaciones.eliminarProducto").description("Contador de invocaciones").register(registry);
	}
	
	@ReadOperation
	public List<Producto> mostrarAlmacen() {
		counter1.increment();
		if(!almacen.getAlmacen().isEmpty()) {
			return almacen.getAlmacen();
		}else {
			return null;
		}
	}
	
	@WriteOperation
	public void insertarProducto(@Selector Producto producto) {
		counter2.increment();
		almacen.insertarProducto(producto);
	}
	
	@DeleteOperation
	public void eliminarProducto(int id) {
		counter3.increment();
		almacen.eliminarProducto(id);
	}
	
}
