package com.chatapp.factory;

import java.net.MalformedURLException;
import java.net.URL;

import com.chatapp.model.Picture;

public class PorfilePictureFactory {

	public static  Picture getRandomPicture(){

		try {
			return new Picture(
					new URL("https://randomuser.me/api/portraits/women/44.jpg"), 
					new URL("https://randomuser.me/api/portraits/med/women/44.jpg"), 
					new URL("https://randomuser.me/api/portraits/thumb/women/44.jpg")
					);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Picture();
	}


}
