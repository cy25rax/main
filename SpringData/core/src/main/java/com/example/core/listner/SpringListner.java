package com.example.core.listner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpringListner implements ApplicationListener<ListnerMessage> {
	
	@Override
	public void onApplicationEvent(ListnerMessage event) {
		log.info(event.getMessage());
	}
}
