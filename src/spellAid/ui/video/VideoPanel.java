package spellAid.ui.video;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import spellAid.util.string.URLString;

/**
 * This class creates and shows a video using JavaFX media player.
 * 
 * @author Luke Tudor
 *
 */
public class VideoPanel extends BorderPane {
	
	private static final String BUNNYPATH = "videos/big_buck_bunny_1_minute.mp4";

	private MediaPlayer player;

	private final ButtonPanel btnPanel;

	public VideoPanel () {
		super();
		
		setPadding(new Insets(5));

		// Create a panel which will contain the media component
		BorderPane contentPanel = new BorderPane();
		contentPanel.setPrefSize(600, 400);

		// Create and configure a panel for the buttons
		btnPanel = new ButtonPanel();
		for (Node n : btnPanel.getChildren())
			n.setDisable(true);
		
		setVideo(BUNNYPATH);

		setBottom(btnPanel);
		
		// Set CSS id
		setId("videoPane");
	}

	public void start() {
		for (Node n : btnPanel.getChildren())
			n.setDisable(false);

		player.play();
	}

	public void stop() {
		for (Node n : btnPanel.getChildren())
			n.setDisable(true);
		
		player.dispose();
	}
	
	// Sets the requested video to be displayed by this component
	public void setVideo(String videoPath) {
		
		btnPanel.reset();
		
		BorderPane contentPanel = new BorderPane();
		contentPanel.setPrefSize(600, 400);

		Media video = new Media(new URLString(videoPath).getURL());

		player = new MediaPlayer(video);

		MediaView mediaView = new MediaView(player);
		mediaView.setFitWidth(contentPanel.getPrefWidth());
		mediaView.setFitHeight(contentPanel.getPrefHeight());

		contentPanel.setCenter(mediaView);

		setCenter(contentPanel);
	}

	/**
	 * Class used to control the video player with buttons to perform various functions.
	 * 
	 * @author Luke Tudor
	 */
	private class ButtonPanel extends FlowPane implements EventHandler<ActionEvent> {

		private static final String MUTEXT = "Mute";
		private static final String UMTEXT = "Unmute";
		private static final String PATEXT = "Pause";
		private static final String PLTEXT = "Play";
		private static final String STTEXT = "Stop";

		private Button play;
		private Button mute;
		private Button stop;

		private ButtonPanel() {

			setAlignment(Pos.CENTER);
			setPadding(new Insets(5));
			setHgap(5);

			play = new Button(PATEXT);
			mute = new Button(MUTEXT);
			stop = new Button(STTEXT);

			Button[] buttons = new Button[]{play, mute, stop};
			for(Button btn : buttons) {
				btn.setOnAction(this);
				getChildren().add(btn);
			}
		}

		@Override
		public void handle(ActionEvent e) {
			if (e.getSource() == play) {
				if (play.getText().equals(PLTEXT)){
					player.play();
					play.setText(PATEXT);
				} else {
					player.pause();
					play.setText(PLTEXT);
				}
			} else if (e.getSource() == mute){
				if (player.isMute()) {
					mute.setText(MUTEXT);
					player.setMute(false);
				} else { 
					mute.setText(UMTEXT);
					player.setMute(true);
				}
			} else {
				player.stop();
				play.setText(PLTEXT);
			}
		}
		
		private void reset() {
			play.setText(PATEXT);
			mute.setText(MUTEXT);
		}
	}
}
