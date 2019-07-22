package com.macys.jbehaveconfig.com;

import java.util.Arrays;
import java.util.List;

import net.serenitybdd.jbehave.SerenityStories;

public class jbehaverun extends SerenityStories {
	
	
	public List<String> storyPaths() {
        return Arrays.asList("./stories/SSCS135.story");
    }

}