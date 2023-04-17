package com.oneroom.webapp.util;

import java.util.Random;

public class RandomNicknameGenerator {
  private static final String[] adjectives = {
    "Happy", "Lucky", "Brave", "Clever", "Gentle", "Eager",
    "Glorious", "Jolly", "Kind", "Naughty", "Polite", "Shy"
  };
  private static final String[] nouns = {
    "Squirrel", "Giraffe", "Penguin", "Kangaroo", "Hedgehog", "Elephant",
    "Octopus", "Lion", "Butterfly", "Koala", "Ostrich", "Panda"
  };

  private static final Random random = new Random();

  public static String generate() {
    String adjective = adjectives[random.nextInt(adjectives.length)];
    String noun = nouns[random.nextInt(nouns.length)];
    return adjective + noun;
  }
} 