package ee.ut.math.tvt.KAT;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.apache.log4j.Logger;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

public class IntroUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(Intro.class);

	public IntroUI() {
		setTitle("Introduction");
		// size & location
		int width = 600;
		int height = 600;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());

		} catch (UnsupportedLookAndFeelException e1) {
			log.warn(e1.getMessage());
		}

		drawWidgets();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	private void drawWidgets() {

		ReadPropertiesFile appProps = new ReadPropertiesFile();
		Properties appProperties = appProps.readProps("application.properties");
		Properties versionProperties = appProps.readProps("version.properties");

		BufferedImage logoimage;
		try {
			logoimage = ImageIO.read(new File(appProperties
					.getProperty("team.logo")));
			JLabel logo = new JLabel(new ImageIcon(logoimage));
			add(logo);
			logo.setHorizontalAlignment(JLabel.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel name = new JLabel("Team name: "
				+ appProperties.getProperty("team.name"));
		JLabel leader = new JLabel("Team leader: "
				+ appProperties.getProperty("team.leader"));
		JLabel email = new JLabel("Team leader email: "
				+ appProperties.getProperty("team.leader.email"));
		JLabel members = new JLabel("Team members: "
				+ appProperties.getProperty("team.members"));
		JLabel version = new JLabel("Build version: "
				+ versionProperties.getProperty("build.number"));

		name.setHorizontalAlignment(JLabel.CENTER);
		leader.setHorizontalAlignment(JLabel.CENTER);
		email.setHorizontalAlignment(JLabel.CENTER);
		members.setHorizontalAlignment(JLabel.CENTER);
		version.setHorizontalAlignment(JLabel.CENTER);

		setLayout(new GridLayout(6, 1));

		add(name);
		add(leader);
		add(email);
		add(members);
		add(version);

	}
}
