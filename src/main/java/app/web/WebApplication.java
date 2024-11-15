package app.web;

import java.net.URI;
import java.awt.Desktop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import app.ColorTerminal;

@SpringBootApplication
public class WebApplication {
	private ConfigurableApplicationContext context;

	public void start() {
		try {
			new Thread(() -> {
				this.context = SpringApplication.run(WebApplication.class);
			}).start();
			this.openWebApp();
		} catch (Exception e) {
			System.out.println(ColorTerminal.red() + "Ошибка запуска веб-сервера!" + ColorTerminal.end());
		}
	}

	private void openWebApp() {
		try {
			URI uri = new URI("http://localhost:8080");
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(uri);
			}
		} catch (Exception e) {
			System.out.println(ColorTerminal.red() + "Desktop не поддерживается на этой платформе!" + ColorTerminal.end());
		}
	}

	public void stop() {
		if (this.context != null) {
			context.close();
		}
	}
}
