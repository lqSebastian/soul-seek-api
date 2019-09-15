package com.soulseek.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MusicController {

	@RequestMapping(value = "/song", method = RequestMethod.GET)
	public String getYoutubeAudio(@RequestParam(defaultValue = "senses%20fail%20bloody%20romance") String song) {
		String output = null;

		URL url = getClass().getResource("youtube-dl.exe");

		// Execute command
		String command = url.getPath() + " \"ytsearch:" + song + "\"  --get-id";

		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((output = input.readLine()) != null) {
				System.out.println(output);
				return output;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

}
