package com.giousa.tools.json;

public final class JsonStringUtils {

  private JsonStringUtils() {

  }

  public static String reverseFirstLetter(String s) {
    if (s == null || s.length() <= 0) {
      return "";
    }
    char[] arr = s.toCharArray();
    if (arr[0] >= 'a' && arr[0] <= 'z') {
      arr[0] += 'A' - 'a';
    }
    return new String(arr);
  }

  public static String dealMiddleLine(String s) {
    if (s == null || s.length() <= 0) {
      return "";
    }
    char[] arr = s.toCharArray();
    boolean f = false;
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == '_') {
        f = true;
      } else {
        if (f) {
          if (arr[i] >= 'a' && arr[i] <= 'z') {
            arr[i] += 'A' - 'a';
            stringBuilder.append(arr[i]);
            f = false;
            continue;
          }
        }
        stringBuilder.append(arr[i]);
      }
    }
    return stringBuilder.toString();
  }
}
