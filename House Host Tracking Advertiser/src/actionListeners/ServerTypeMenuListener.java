package actionListeners;

import panels.CentralPanel;
import source.StartupSettings;

public class ServerTypeMenuListener {

	public static void setCommunityAdvertisementsText() {
		//If the user selects RS3 in the server drop-down menu, the advertisements text in the configuration file dynamically changes to "Altar" friends chat
		if (CentralPanel.serverType.getSelectedItem().toString().startsWith("RS3")) {
			AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)07 Altar", "Altar"));
			AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)Clan", "Friends"));
			AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)cc", "fc"));
			AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)07 Altar", "Altar"));
			AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)Clan", "Friends"));
			AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)cc", "fc"));
			AdvertisementsListener.boneJobAdverts.setText(AdvertisementsListener.boneJobAdverts.getText().replaceAll("(?i)Clan", "Friends"));
			AdvertisementsListener.boneJobAdverts.setText(AdvertisementsListener.boneJobAdverts.getText().replaceAll("(?i)cc", "fc"));
		}
		//If the user selects OSRS in the server drop-down menu, the advertisements text in the configuration file dynamically changes to "07 Altar" clan chat
		else {
			AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)07 Altar", "Altar"));
			AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)Altar", "07 Altar"));
			AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)Friends", "Clan"));
			AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)fc", "cc"));
			AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)07 Altar", "Altar"));
			AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)Altar", "07 Altar"));
			AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)Friends", "Clan"));
			AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)fc", "cc"));
			//Corrects any possible mistakes made in No Host Advertisement by the dynamic changes
			if (AdvertisementsListener.noHostAdvert.getText().toLowerCase().contains(" g 07 altar") || AdvertisementsListener.noHostAdvert.getText().toLowerCase().contains("g07 altar") || AdvertisementsListener.noHostAdvert.getText().toLowerCase().contains("gilded 07 altar") || AdvertisementsListener.noHostAdvert.getText().toLowerCase().contains("gilded07 altar") || AdvertisementsListener.noHostAdvert.getText().toLowerCase().contains("discord.gg/07 altar")) {
				AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i) g 07 altar", " G Altar"));
				AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)g07 altar", "GAltar"));
				AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)gilded 07 altar", "Gilded Altar"));
				AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)gilded07 altar", "GildedAltar"));
				AdvertisementsListener.noHostAdvert.setText(AdvertisementsListener.noHostAdvert.getText().replaceAll("(?i)discord.gg/07 altar", "Discord.gg/altar"));
			}
			//Corrects any possible mistakes made in Community Advertisements by the dynamic changes
			if (AdvertisementsListener.communityAdverts.getText().toLowerCase().contains(" g 07 altar") || AdvertisementsListener.communityAdverts.getText().toLowerCase().contains("g07 altar") || AdvertisementsListener.communityAdverts.getText().toLowerCase().contains("gilded 07 altar") || AdvertisementsListener.communityAdverts.getText().toLowerCase().contains("gilded07 altar") || AdvertisementsListener.communityAdverts.getText().toLowerCase().contains("discord.gg/07 altar")) {
				AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i) g 07 altar", " G Altar"));
				AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)g07 altar", "GAltar"));
				AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)gilded 07 altar", "Gilded Altar"));
				AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)gilded07 altar", "GildedAltar"));
				AdvertisementsListener.communityAdverts.setText(AdvertisementsListener.communityAdverts.getText().replaceAll("(?i)discord.gg/07 altar", "Discord.gg/altar"));
			}
			//Sets the appropriate text depending on whether it is Old School RuneScape or RuneScape 3
			AdvertisementsListener.boneJobAdverts.setText(AdvertisementsListener.boneJobAdverts.getText().replaceAll("(?i)Friends", "Clan"));
			AdvertisementsListener.boneJobAdverts.setText(AdvertisementsListener.boneJobAdverts.getText().replaceAll("(?i)fc", "cc"));
		}
		//Updates the text in the configuration file and settings
		StartupSettings.noHostMessage = AdvertisementsListener.noHostAdvert.getText();
		StartupSettings.communityMessages = AdvertisementsListener.communityAdverts.getText();
		StartupSettings.boneJobMessages = AdvertisementsListener.boneJobAdverts.getText();
	}
}