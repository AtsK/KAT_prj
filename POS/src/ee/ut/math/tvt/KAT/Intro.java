package ee.ut.math.tvt.KAT;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Intro {
	private static final Logger logger = LogManager.getLogger(Intro.class.getName());
	public static void main(String[] args) {
		logger.error("I'm alive!");
		
		IntroUI ui = new IntroUI();
		ui.setVisible(true);
		
	}
}
