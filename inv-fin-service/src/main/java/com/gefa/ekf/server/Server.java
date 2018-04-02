package com.gefa.ekf.server;

import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Server class.
 *
 */
public class Server {

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static UndertowJaxrsServer startServer_working() {
		UndertowJaxrsServer server = new UndertowJaxrsServer().start();
		DeploymentInfo di = server.undertowDeployment(MyApp.class);
		di.setContextPath("/inv-fin-service");
		di.setDeploymentName("DI");
		server.deploy(di);
		return server;
	}

	public static UndertowJaxrsServer startServer() {
		System.setProperty("org.jboss.resteasy.port", "8086");
		UndertowJaxrsServer server = new UndertowJaxrsServer().start();

		ResteasyDeployment deployment = new ResteasyDeployment();
		deployment.setInjectorFactoryClass("org.jboss.resteasy.cdi.CdiInjectorFactory");
		deployment.setApplicationClass(MyApp.class.getName());
		DeploymentInfo di = server.undertowDeployment(deployment);
		di.setClassLoader(Server.class.getClassLoader());
		di.setResourceManager(new ClassPathResourceManager(Server.class.getClassLoader()));
		di.setContextPath("/inv-fin-service");
		di.setDeploymentName("DI");
		di.addListeners(Servlets.listener(org.jboss.weld.environment.servlet.Listener.class));
		server.deploy(di);
		return server;
	}

	/**
	 * Server method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		final UndertowJaxrsServer server = startServer();
		System.out.println(String.format("Resteasy app started \nHit enter to stop it..."));
		System.in.read();
		server.stop();
	}

	@ApplicationPath("/")
	public static class MyApp extends Application {

		@Override
		public Set<Class<?>> getClasses() {
			HashSet<Class<?>> classes = new HashSet<Class<?>>();

			classes.add(com.gefa.ekf.boundary.inbound.rest.v0_0_0.resources.AssetResourceImpl.class);
			classes.add(com.gefa.ekf.boundary.inbound.rest.v0_0_0.error.handlers.ExceptionHandler.class);
			classes.add(com.gefa.ekf.boundary.inbound.rest.v0_0_0.error.handlers.NoSuchAssetExceptionHandler.class);

			classes.add(com.gefa.ekf.boundary.inbound.rest.v0_0_1.resources.AssetResourceImpl.class);
			classes.add(com.gefa.ekf.boundary.inbound.rest.v0_0_1.error.handlers.ExceptionHandler.class);
			classes.add(com.gefa.ekf.boundary.inbound.rest.v0_0_1.error.handlers.NoSuchAssetExceptionHandler.class);

			return classes;
		}
	}

}
