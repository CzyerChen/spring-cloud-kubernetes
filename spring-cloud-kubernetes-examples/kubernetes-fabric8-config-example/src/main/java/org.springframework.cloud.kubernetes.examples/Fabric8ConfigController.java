/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.kubernetes.examples;

import jakarta.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.kubernetes.examples.config.ConfigMapProperties;
import org.springframework.cloud.kubernetes.examples.config.SecretsProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Fabric8ConfigController {

	@Value("${greeting.message}")
	private String greetingMessage;

	@Value("${farewell.message}")
	private String farewellMessage;

	@Resource
	private ConfigMapProperties configProperties;

	@Resource
	private SecretsProperties secretProperties;

	/**
	 * Return a message whether this instance is a leader or not.
	 * @return info
	 */
	@GetMapping("meet")
	public String meet() {
		return String.format("Hello, '%s', Goodbye, '%s'", greetingMessage, farewellMessage);
	}

	@GetMapping
	public String configs() {
		return String.format("baseFilePath: '%s', maxFileCount: '%s'", configProperties.getBaseFilePath(),
				configProperties.getMaxFileCount()) + ";"
				+ String.format("username: '%s', password: '%s'", secretProperties.getUsername(),
						secretProperties.getPassword());
	}

	@GetMapping("secrets")
	public String secrets() {
		return String.format("username: '%s', password: '%s'", secretProperties.getUsername(),
				secretProperties.getPassword());
	}

}
