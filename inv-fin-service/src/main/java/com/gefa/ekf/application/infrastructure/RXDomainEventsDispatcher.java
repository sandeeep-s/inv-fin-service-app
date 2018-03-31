package com.gefa.ekf.application.infrastructure;

import com.gefa.ekf.application.domain.events.DomainEvent;
import com.gefa.ekf.application.domain.events.handlers.DomainEventHandler;
import com.gefa.ekf.boundary.outbound.async.fit.AssetCreatedEventHandler;
import com.gefa.ekf.boundary.outbound.rest.asset.AssetCreatedEventRESTHandler;
import io.reactivex.subjects.PublishSubject;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Iterator;

@Singleton
public class RXDomainEventsDispatcher {



//	@Inject
//	@RXPublisherSubject
	private PublishSubject<DomainEvent> publishSubject;

//	@Produces
//	@RXPublisherSubject
	public PublishSubject<DomainEvent> createPublishEubject(){
		return PublishSubject.create();
	}

	@Inject
	public RXDomainEventsDispatcher(@Any Instance<DomainEventHandler> handlerList) {
		publishSubject = PublishSubject.create();
		Iterator<DomainEventHandler> handlerIterator = handlerList.iterator();
		while (handlerIterator.hasNext()) {
			DomainEventHandler handler = handlerIterator.next();
			System.out.println("RXDomainEventsDispatcher handler.getClass()="+handler.getClass());
			System.out.println("RXDomainEventsDispatcher AssetCreatedEventHandler.class="+AssetCreatedEventHandler.class);
			if (handler instanceof AssetCreatedEventHandler || handler instanceof AssetCreatedEventRESTHandler) {
				publishSubject.subscribe(handler::handle);
			}
		}
	}

	public void dispatch(DomainEvent domainEvent) {
		publishSubject.onNext(domainEvent);
	}
}
