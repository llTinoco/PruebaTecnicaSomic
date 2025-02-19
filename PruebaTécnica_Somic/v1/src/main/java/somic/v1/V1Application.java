package somic.v1;
   
import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import somic.v1.panel.MainFrame;

@SpringBootApplication
public class V1Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(V1Application.class);
		app.setHeadless(false); // Desactiva el modo headless
		ConfigurableApplicationContext context = app.run(args);
		SwingUtilities.invokeLater(() -> {
			MainFrame mainFrame = context.getBean(MainFrame.class);
			mainFrame.setVisible(true);
		});
	}
}